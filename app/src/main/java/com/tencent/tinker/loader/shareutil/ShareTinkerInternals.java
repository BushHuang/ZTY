package com.tencent.tinker.loader.shareutil;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ShareTinkerInternals {
    private static final String PATCH_PROCESS_NAME = ":patch";
    private static final String TAG = "Tinker.TinkerInternals";
    private static final boolean VM_IS_ART = isVmArt(System.getProperty("java.vm.version"));
    private static final boolean VM_IS_JIT = isVmJitInternal();
    private static Boolean isPatchProcess = null;
    private static Boolean isARKHotRunning = null;
    private static String processName = null;
    private static String tinkerID = null;
    private static String currentInstructionSet = null;

    public static ShareDexDiffPatchInfo changeTestDexToClassN(ShareDexDiffPatchInfo shareDexDiffPatchInfo, int i) {
        String str;
        if (!shareDexDiffPatchInfo.rawName.startsWith("test.dex")) {
            return null;
        }
        if (i != 1) {
            str = "classes" + i + ".dex";
        } else {
            str = "classes.dex";
        }
        return new ShareDexDiffPatchInfo(str, shareDexDiffPatchInfo.path, shareDexDiffPatchInfo.destMd5InDvm, shareDexDiffPatchInfo.destMd5InArt, shareDexDiffPatchInfo.dexDiffMd5, shareDexDiffPatchInfo.oldDexCrC, shareDexDiffPatchInfo.newOrPatchedDexCrC, shareDexDiffPatchInfo.dexMode);
    }

    public static int checkPackageAndTinkerFlag(ShareSecurityCheck shareSecurityCheck, int i) {
        if (isTinkerEnabledAll(i)) {
            return 0;
        }
        HashMap<String, String> metaContentMap = shareSecurityCheck.getMetaContentMap();
        if (!isTinkerEnabledForDex(i) && metaContentMap.containsKey("assets/dex_meta.txt")) {
            return -9;
        }
        if (isTinkerEnabledForNativeLib(i) || !metaContentMap.containsKey("assets/so_meta.txt")) {
            return (isTinkerEnabledForResource(i) || !metaContentMap.containsKey("assets/res_meta.txt")) ? 0 : -9;
        }
        return -9;
    }

    public static int checkSignatureAndTinkerID(Context context, File file, ShareSecurityCheck shareSecurityCheck) {
        if (!shareSecurityCheck.verifyPatchMetaSignature(file)) {
            return -1;
        }
        String manifestTinkerID = getManifestTinkerID(context);
        if (manifestTinkerID == null) {
            return -5;
        }
        HashMap<String, String> packagePropertiesIfPresent = shareSecurityCheck.getPackagePropertiesIfPresent();
        if (packagePropertiesIfPresent == null) {
            return -2;
        }
        String str = packagePropertiesIfPresent.get("TINKER_ID");
        if (str == null) {
            return -6;
        }
        if (manifestTinkerID.equals(str)) {
            return 0;
        }
        Log.e("Tinker.TinkerInternals", "tinkerId is not equal, base is " + manifestTinkerID + ", but patch is " + str);
        return -7;
    }

    public static int checkTinkerPackage(Context context, int i, File file, ShareSecurityCheck shareSecurityCheck) {
        int iCheckSignatureAndTinkerID = checkSignatureAndTinkerID(context, file, shareSecurityCheck);
        return iCheckSignatureAndTinkerID == 0 ? checkPackageAndTinkerFlag(shareSecurityCheck, i) : iCheckSignatureAndTinkerID;
    }

    public static Properties fastGetPatchPackageMeta(File file) throws Throwable {
        ZipFile zipFile;
        InputStream inputStream;
        ZipFile zipFile2 = null;
        if (file != null && file.isFile()) {
            ?? length = file.length();
            try {
                if (length != 0) {
                    try {
                        zipFile = new ZipFile(file);
                    } catch (IOException e) {
                        e = e;
                        zipFile = null;
                    } catch (Throwable th) {
                        th = th;
                        SharePatchFileUtil.closeZip(zipFile2);
                        throw th;
                    }
                    try {
                        ZipEntry entry = zipFile.getEntry("assets/package_meta.txt");
                        if (entry == null) {
                            Log.e("Tinker.TinkerInternals", "patch meta entry not found");
                            SharePatchFileUtil.closeZip(zipFile);
                            return null;
                        }
                        try {
                            inputStream = zipFile.getInputStream(entry);
                            try {
                                Properties properties = new Properties();
                                properties.load(inputStream);
                                SharePatchFileUtil.closeQuietly(inputStream);
                                SharePatchFileUtil.closeZip(zipFile);
                                return properties;
                            } catch (Throwable th2) {
                                th = th2;
                                SharePatchFileUtil.closeQuietly(inputStream);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = null;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        Log.e("Tinker.TinkerInternals", "fastGetPatchPackageMeta exception:" + e.getMessage());
                        SharePatchFileUtil.closeZip(zipFile);
                        return null;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                zipFile2 = length;
            }
        }
        Log.e("Tinker.TinkerInternals", "patchFile is illegal");
        return null;
    }

    public static String getCurrentInstructionSet() throws Exception {
        String str = currentInstructionSet;
        if (str != null) {
            return str;
        }
        currentInstructionSet = (String) Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("getCurrentInstructionSet", new Class[0]).invoke(null, new Object[0]);
        Log.d("Tinker.TinkerInternals", "getCurrentInstructionSet:" + currentInstructionSet);
        return currentInstructionSet;
    }

    public static String getCurrentOatMode(Context context, String str) {
        return str.equals("changing") ? isInMainProcess(context) ? "odex" : "interpet" : str;
    }

    public static String getExceptionCauseString(Throwable th) {
        if (th == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        while (true) {
            try {
                Throwable cause = th.getCause();
                if (cause == null) {
                    th.printStackTrace(printStream);
                    return toVisualString(byteArrayOutputStream.toString());
                }
                th = cause;
            } finally {
                SharePatchFileUtil.closeQuietly(printStream);
            }
        }
    }

    public static String getManifestTinkerID(Context context) {
        String str = tinkerID;
        if (str != null) {
            return str;
        }
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("TINKER_ID");
            if (obj != null) {
                tinkerID = String.valueOf(obj);
            } else {
                tinkerID = null;
            }
            return tinkerID;
        } catch (Exception e) {
            Log.e("Tinker.TinkerInternals", "getManifestTinkerID exception:" + e.getMessage());
            return null;
        }
    }

    public static String getProcessName(Context context) throws Throwable {
        String str = processName;
        if (str != null) {
            return str;
        }
        String processNameInternal = getProcessNameInternal(context);
        processName = processNameInternal;
        return processNameInternal;
    }

    private static String getProcessNameInternal(Context context) throws Throwable {
        ActivityManager.RunningAppProcessInfo next;
        FileInputStream fileInputStream;
        int i;
        int iMyPid = Process.myPid();
        if (context == null || iMyPid <= 0) {
            return "";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        FileInputStream fileInputStream2 = null;
        if (activityManager != null) {
            try {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        if (next.pid == iMyPid) {
                            break;
                        }
                    }
                    if (next != null) {
                        return next.processName;
                    }
                }
            } catch (Exception e) {
                Log.e("Tinker.TinkerInternals", "getProcessNameInternal exception:" + e.getMessage());
            }
        }
        byte[] bArr = new byte[128];
        try {
            try {
                try {
                    fileInputStream = new FileInputStream("/proc/" + iMyPid + "/cmdline");
                    try {
                        i = fileInputStream.read(bArr);
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream2 = fileInputStream;
                        Log.e("Tinker.TinkerInternals", "getProcessNameInternal exception:" + e.getMessage());
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception unused2) {
        }
        if (i <= 0) {
            fileInputStream.close();
            return "";
        }
        for (int i2 = 0; i2 < i; i2++) {
            if ((bArr[i2] & 255) <= 128 && bArr[i2] > 0) {
            }
            i = i2;
            break;
        }
        String str = new String(bArr, 0, i);
        try {
            fileInputStream.close();
        } catch (Exception unused3) {
        }
        return str;
    }

    public static int getSafeModeCount(Context context) throws Throwable {
        String str = "tinker_own_config_" + getProcessName(context);
        int i = context.getSharedPreferences(str, 0).getInt("safe_mode_count_1.9.14.7", 0);
        Log.w("Tinker.TinkerInternals", "getSafeModeCount: preferName:" + str + " count:" + i);
        return i;
    }

    private static String getTinkerSwitchSPKey(Context context) {
        String manifestTinkerID = getManifestTinkerID(context);
        if (isNullOrNil(manifestTinkerID)) {
            manifestTinkerID = "@@";
        }
        return "tinker_enable_1.9.14.7_" + manifestTinkerID;
    }

    public static String getTypeString(int i) {
        switch (i) {
            case 1:
                return "patch_file";
            case 2:
                return "patch_info";
            case 3:
                return "dex";
            case 4:
                return "dex_opt";
            case 5:
                return "lib";
            case 6:
                return "resource";
            default:
                return "unknown";
        }
    }

    public static boolean isAfterAndroidO() {
        return Build.VERSION.SDK_INT > 25;
    }

    public static boolean isArkHotRuning() throws NoSuchMethodException, SecurityException {
        Boolean bool = isARKHotRunning;
        if (bool != null) {
            return bool.booleanValue();
        }
        isARKHotRunning = false;
        try {
            Method declaredMethod = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.app.ArkApplicationInfo").getDeclaredMethod("isRunningInArk", new Class[0]);
            declaredMethod.setAccessible(true);
            isARKHotRunning = (Boolean) declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException unused) {
            Log.i("Tinker.TinkerInternals", "class not found exception");
        } catch (IllegalAccessException unused2) {
            Log.i("Tinker.TinkerInternals", "illegal access exception");
        } catch (IllegalArgumentException unused3) {
            Log.i("Tinker.TinkerInternals", "illegal argument exception");
        } catch (NoSuchMethodException unused4) {
            Log.i("Tinker.TinkerInternals", "no such method exception");
        } catch (SecurityException unused5) {
            Log.i("Tinker.TinkerInternals", "security exception");
        } catch (InvocationTargetException unused6) {
            Log.i("Tinker.TinkerInternals", "invocation target exception");
        }
        return isARKHotRunning.booleanValue();
    }

    public static boolean isInMainProcess(Context context) throws Throwable {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = applicationInfo != null ? applicationInfo.processName : null;
        if (isNullOrNil(packageName)) {
            packageName = context.getPackageName();
        }
        String processName2 = getProcessName(context);
        if (processName2 == null || processName2.length() == 0) {
            processName2 = "";
        }
        return packageName.equals(processName2);
    }

    public static boolean isInPatchProcess(Context context) {
        Boolean bool = isPatchProcess;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean boolValueOf = Boolean.valueOf(getProcessName(context).endsWith(":patch"));
        isPatchProcess = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static boolean isNullOrNil(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean isSystemOTA(String str) {
        String str2 = Build.FINGERPRINT;
        if (str == null || str.equals("") || str2 == null || str2.equals("")) {
            Log.d("Tinker.TinkerInternals", "fingerprint empty:" + str + ",current:" + str2);
            return false;
        }
        if (str.equals(str2)) {
            Log.d("Tinker.TinkerInternals", "same fingerprint:" + str2);
            return false;
        }
        Log.d("Tinker.TinkerInternals", "system OTA,fingerprint not equal:" + str + "," + str2);
        return true;
    }

    public static boolean isTinkerEnableWithSharedPreferences(Context context) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences("tinker_share_config", 4).getBoolean(getTinkerSwitchSPKey(context), true);
    }

    public static boolean isTinkerEnabled(int i) {
        return i != 0;
    }

    public static boolean isTinkerEnabledAll(int i) {
        return i == 15;
    }

    public static boolean isTinkerEnabledForArkHot(int i) {
        return (i & 8) != 0;
    }

    public static boolean isTinkerEnabledForDex(int i) {
        return (i & 1) != 0;
    }

    public static boolean isTinkerEnabledForNativeLib(int i) {
        return (i & 2) != 0;
    }

    public static boolean isTinkerEnabledForResource(int i) {
        return (i & 4) != 0;
    }

    public static boolean isVmArt() {
        return VM_IS_ART || Build.VERSION.SDK_INT >= 21;
    }

    private static boolean isVmArt(String str) throws NumberFormatException {
        if (str == null) {
            return false;
        }
        Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        try {
            int i = Integer.parseInt(matcher.group(1));
            return i > 2 || (i == 2 && Integer.parseInt(matcher.group(2)) >= 1);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean isVmJit() {
        return VM_IS_JIT && Build.VERSION.SDK_INT < 24;
    }

    private static boolean isVmJitInternal() {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            String str = (String) declaredMethod.invoke(null, "dalvik.vm.usejit");
            String str2 = (String) declaredMethod.invoke(null, "dalvik.vm.usejitprofiles");
            if (!isNullOrNil(str) && isNullOrNil(str2)) {
                if (str.equals("true")) {
                    return true;
                }
            }
        } catch (Throwable th) {
            Log.e("Tinker.TinkerInternals", "isVmJitInternal ex:" + th);
        }
        return false;
    }

    public static void killAllOtherProcess(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.uid == Process.myUid() && runningAppProcessInfo.pid != Process.myPid()) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    public static void killProcessExceptMain(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.uid == Process.myUid() && !runningAppProcessInfo.processName.equals(context.getPackageName())) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    public static void setSafeModeCount(Context context, int i) throws Throwable {
        String str = "tinker_own_config_" + getProcessName(context);
        context.getSharedPreferences(str, 0).edit().putInt("safe_mode_count_1.9.14.7", i).commit();
        Log.w("Tinker.TinkerInternals", "setSafeModeCount: preferName:" + str + " count:" + i);
    }

    public static void setTinkerDisableWithSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("tinker_share_config", 4);
        sharedPreferences.edit().putBoolean(getTinkerSwitchSPKey(context), false).commit();
    }

    public static String toVisualString(String str) {
        char[] charArray;
        boolean z;
        if (str == null || (charArray = str.toCharArray()) == null) {
            return null;
        }
        int i = 0;
        while (true) {
            if (i >= charArray.length) {
                z = false;
                break;
            }
            if (charArray[i] > 127) {
                charArray[i] = 0;
                z = true;
                break;
            }
            i++;
        }
        return z ? new String(charArray, 0, i) : str;
    }
}
