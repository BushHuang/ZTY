package com.analysys.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.analysys.ab;
import com.analysys.ae;
import com.analysys.aj;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommonUtils {
    private static String certName;
    private static Boolean sIsMainProcess;

    public static boolean checkClass(String str, String str2) {
        try {
            Class.forName(str + "." + str2);
            return true;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return false;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Object objInvoke = Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str);
                if (objInvoke instanceof Integer) {
                    if (((Integer) objInvoke).intValue() == 0) {
                        return true;
                    }
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        }
        return false;
    }

    public static String checkUrl(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String strTrim = str.trim();
        int iLastIndexOf = strTrim.lastIndexOf("/");
        return (iLastIndexOf == -1 || iLastIndexOf != strTrim.length() + (-1)) ? strTrim : checkUrl(strTrim.substring(0, strTrim.length() - 1));
    }

    public static void clearEmptyValue(Map<String, Object> map) {
        if (isEmpty(map)) {
            return;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            Object obj = map.get(next);
            if (isEmpty(next)) {
                it.remove();
            } else if (isInvalidValue(obj)) {
                it.remove();
            }
        }
    }

    public static String dbDecrypt(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                String str2 = new String(Base64.decode(str.getBytes(), 0));
                if (!TextUtils.isEmpty(str2)) {
                    if (!isEmpty(new JSONObject(str2))) {
                        return str2;
                    }
                }
            } catch (Throwable unused) {
            }
            int length = str.length();
            int i = length - (length / 10);
            String strSubstring = str.substring(0, i);
            return new String(Base64.decode(String.valueOf(new StringBuffer(str.substring(i, length) + strSubstring).reverse()), 2));
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    public static String dbEncrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strValueOf = String.valueOf(new StringBuffer(new String(Base64.encode(str.getBytes(), 2))).reverse());
        int length = strValueOf.length();
        int i = length / 10;
        String strSubstring = strValueOf.substring(0, i);
        return strValueOf.substring(i, length) + strSubstring;
    }

    public static <String, T> Map<String, T> deepCopy(Map<String, T> map) {
        try {
            return map == null ? new HashMap() : new HashMap(map);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return new HashMap();
        }
    }

    public static String getAndroidID(Context context) {
        try {
            return Settings.System.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    public static String getAppKey(Context context) {
        try {
            return SharedUtil.getString(context, "appKey", null);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    public static Application getApplication() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (objInvoke != null) {
                return (Application) objInvoke;
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return null;
    }

    public static long getCalibrationTimeMillis(Context context) {
        if (!Constants.isTimeCheck) {
            return System.currentTimeMillis();
        }
        if (!isMainProcess(context)) {
            String string = SharedUtil.getString(context, "diffTime", "");
            if (!TextUtils.isEmpty(string)) {
                Constants.diffTime = parseLong(string, 0L);
            }
        }
        return System.currentTimeMillis() + Constants.diffTime;
    }

    public static String getCarrierName(Context context) {
        TelephonyManager telephonyManager;
        try {
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                return null;
            }
            String subscriberId = telephonyManager.getSubscriberId();
            if (isEmpty(subscriberId)) {
                return null;
            }
            if (!subscriberId.startsWith("46000") && !subscriberId.startsWith("46002")) {
                if (subscriberId.startsWith("46001")) {
                    return "中国联通";
                }
                if (subscriberId.startsWith("46003")) {
                    return "中国电信";
                }
                return null;
            }
            return "中国移动";
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return null;
        }
    }

    public static String getChannel(Context context) {
        String string = "";
        try {
            string = SharedUtil.getString(context, "appChannel", null);
            if (isEmpty(string)) {
                string = getManifestData(context, "ANALYSYS_CHANNEL");
                if (!isEmpty(string)) {
                    SharedUtil.setString(context, "appChannel", string);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return string;
    }

    public static String getClassPath(String str) {
        return str.substring(0, str.lastIndexOf("."));
    }

    public static String getDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static Object getDebugMode(Context context) {
        int i = SharedUtil.getInt(context, "serviceDebug", -1);
        if (i != -1) {
            return Integer.valueOf(i);
        }
        int i2 = SharedUtil.getInt(context, "userDebug", -1);
        if (i2 != -1) {
            return Integer.valueOf(i2);
        }
        return 0;
    }

    public static String getFirstStartTime(Context context) {
        String string = SharedUtil.getString(context, "firstStartTime", "");
        if (!isEmpty(string)) {
            return string;
        }
        String time = getTime();
        SharedUtil.setString(context, "firstStartTime", time);
        return time;
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager;
        try {
            return (!checkPermission(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) ? "" : telephonyManager.getDeviceId();
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return "";
        }
    }

    @Deprecated
    public static String getIdFile(Context context, String str) {
        if (context != null) {
            try {
                if (context.getFilesDir() != null) {
                    String file = readFile(context.getFilesDir().getPath() + "id.e");
                    if (!TextUtils.isEmpty(file)) {
                        return new JSONObject(file).optString(str);
                    }
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return null;
    }

    public static String getLaunchSource() {
        int i = Constants.sourceNum;
        return i != 1 ? i != 2 ? i != 3 ? "0" : "url" : "msg" : "icon";
    }

    public static Object getMac(Context context) {
        try {
            return Build.VERSION.SDK_INT < 23 ? getMacBySystemInterface(context) : Build.VERSION.SDK_INT == 23 ? getMacByFileAndJavaAPI(context) : getMacByJavaAPI();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return "";
        }
    }

    private static String getMacByFileAndJavaAPI(Context context) throws Throwable {
        String macShell = getMacShell();
        return !isEmpty(macShell) ? macShell : getMacByJavaAPI();
    }

    private static String getMacByJavaAPI() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
            if ("wlan0".equals(networkInterfaceNextElement.getName()) || "eth0".equals(networkInterfaceNextElement.getName())) {
                byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                if (hardwareAddress != null && hardwareAddress.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString().toLowerCase(Locale.getDefault());
                }
            }
        }
        return "";
    }

    private static String getMacBySystemInterface(Context context) {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        return (context == null || !checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? "" : connectionInfo.getMacAddress();
    }

    private static String getMacShell() throws Throwable {
        String[] strArr = {"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
        for (int i = 0; i < 3; i++) {
            String strReaMac = reaMac(strArr[i]);
            if (strReaMac != null) {
                return strReaMac;
            }
        }
        return "";
    }

    public static String getManifestData(Context context, String str) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (TextUtils.isEmpty(str) || applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get(str)) == null) {
                return null;
            }
            return obj instanceof String ? (String) obj : obj instanceof Number ? String.valueOf(obj) : obj instanceof Boolean ? String.valueOf(((Boolean) obj).booleanValue()) : obj.toString();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return null;
    }

    public static String getMethod(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static String getMould(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        InputStream inputStreamOpen = null;
        try {
            inputStreamOpen = context.getResources().getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            String str2 = new String(bArr);
            ae.a(inputStreamOpen);
            return str2;
        } catch (Throwable th) {
            try {
                ExceptionUtil.exceptionPrint(th);
                ae.a(inputStreamOpen);
                return "";
            } catch (Throwable th2) {
                ae.a(inputStreamOpen);
                throw th2;
            }
        }
    }

    public static String getProcessName() {
        try {
            ActivityManager activityManager = (ActivityManager) AnalysysUtil.getContext().getSystemService("activity");
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
            if (runningAppProcesses == null) {
                return "";
            }
            String str = "";
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid() && runningAppProcessInfo.processName != null) {
                    str = runningAppProcessInfo.processName;
                }
            }
            return str;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return "";
        }
    }

    public static SSLSocketFactory getSSLSocketFactory(Context context) {
        if (TextUtils.isEmpty(certName)) {
            certName = getManifestData(context, "ANALYSYS_KEYSTONE");
        }
        if (TextUtils.isEmpty(certName)) {
            return null;
        }
        return getUserSSLSocketFactory(context, certName);
    }

    public static Object getScreenHeight(Context context) {
        int i;
        if (context instanceof Activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
        } else {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics2);
                i = displayMetrics2.heightPixels;
            } else {
                i = -1;
            }
        }
        if (i == -1) {
            i = context.getResources().getDisplayMetrics().heightPixels;
        }
        return Integer.valueOf(i);
    }

    public static Object getScreenWidth(Context context) {
        int i;
        if (context instanceof Activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
        } else {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics2);
                i = displayMetrics2.widthPixels;
            } else {
                i = -1;
            }
        }
        if (i == -1) {
            i = context.getResources().getDisplayMetrics().widthPixels;
        }
        return Integer.valueOf(i);
    }

    public static String getSpvInfo(Context context) {
        try {
            return new String(Base64.encodeToString(("Android|" + getAppKey(context) + "|4.5.2|" + SharedUtil.getString(context, "serviceHash", null) + "|" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName).getBytes(), 2));
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        return simpleDateFormat.format(new Date(getCalibrationTimeMillis(AnalysysUtil.getContext())));
    }

    public static String getUrl(Context context) {
        String string = SharedUtil.getString(context, "serviceUrl", null);
        if (!isEmpty(string)) {
            return string;
        }
        String string2 = SharedUtil.getString(context, "userUrl", null);
        if (isEmpty(string2)) {
            return null;
        }
        return string2;
    }

    public static String getUserId(Context context) {
        return ab.c();
    }

    private static SSLSocketFactory getUserSSLSocketFactory(Context context, String str) {
        try {
            AssetManager assets = context.getAssets();
            if (assets != null) {
                Certificate certificateGenerateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new BufferedInputStream(assets.open(str)));
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null);
                keyStore.setCertificateEntry("cert", certificateGenerateCertificate);
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, trustManagers, null);
                return sSLContext.getSocketFactory();
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return null;
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return "";
        }
    }

    public static boolean isAutoCollect(Context context, String str) {
        Bundle bundle;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                return true;
            }
            return bundle.getBoolean(str);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return true;
        }
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        try {
            if (obj instanceof String) {
                String string = obj.toString();
                return TextUtils.isEmpty(string) || TextUtils.isEmpty(string.trim());
            }
            if (obj instanceof JSONObject) {
                return ((JSONObject) obj).length() < 1;
            }
            if (obj instanceof JSONArray) {
                return ((JSONArray) obj).length() < 1;
            }
            if (obj instanceof Map) {
                return ((Map) obj).isEmpty();
            }
            if (obj instanceof List) {
                return ((List) obj).isEmpty();
            }
            if (obj instanceof Set) {
                return ((Set) obj).isEmpty();
            }
            return false;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return true;
        }
    }

    public static boolean isFirst(Context context) {
        if (isEmpty(context)) {
            return false;
        }
        return isEmpty(SharedUtil.getString(context, "firstStartTime", null));
    }

    public static Object isFirstDay(Context context) {
        String day = getDay();
        String string = SharedUtil.getString(context, "$is_first_day", null);
        if (!isEmpty(string)) {
            return Boolean.valueOf(string.equals(day));
        }
        SharedUtil.setString(context, "$is_first_day", day);
        return true;
    }

    public static boolean isFirstStart(Context context) {
        if (isEmpty(context) || !isEmpty(SharedUtil.getString(context, "firstStartTime", null))) {
            return false;
        }
        getFirstStartTime(context);
        return true;
    }

    private static boolean isInvalidValue(Object obj) {
        return isEmpty(obj) || "unknown".equalsIgnoreCase(obj.toString()) || "null".equalsIgnoreCase(obj.toString());
    }

    public static boolean isMainProcess(Context context) {
        Boolean bool = sIsMainProcess;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
        if (runningAppProcesses == null) {
            return false;
        }
        String str = "";
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid() && runningAppProcessInfo.processName != null) {
                str = runningAppProcessInfo.processName;
            }
        }
        String packageName = context.getApplicationInfo() != null ? context.getApplicationInfo().processName : null;
        if (packageName == null) {
            packageName = context.getPackageName();
        }
        Boolean boolValueOf = Boolean.valueOf(packageName.equals(str));
        sIsMainProcess = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static Map<String, Object> jsonToMap(JSONObject jSONObject) {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next != null) {
                String string = next.toString();
                map.put(string, jSONObject.opt(string));
            }
        }
        return map;
    }

    public static void mergeJson(JSONObject jSONObject, JSONObject jSONObject2) {
        if (isEmpty(jSONObject) || jSONObject2 == null) {
            return;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            jSONObject2.put(next, jSONObject.opt(next));
        }
    }

    public static String messageUnzip(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String strA = aj.a(Base64.decode(str, 0));
        return isEmpty(strA) ? str : strA;
    }

    public static String messageZip(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String str2 = new String(Base64.encode(aj.a(str), 0));
        return isEmpty(str2) ? "" : str2;
    }

    public static float parseFloat(String str, float f) {
        try {
            return TextUtils.isEmpty(str) ? f : Float.parseFloat(str);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return f;
        }
    }

    public static int parseInt(String str, int i) {
        try {
            return TextUtils.isEmpty(str) ? i : Integer.parseInt(str);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return i;
        }
    }

    public static long parseLong(String str, long j) {
        try {
            return TextUtils.isEmpty(str) ? j : Long.parseLong(str);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return j;
        }
    }

    public static void pushToJSON(JSONObject jSONObject, String str, Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof String) {
                String strValueOf = String.valueOf(obj);
                if (!TextUtils.isEmpty(strValueOf) && !"unknown".equalsIgnoreCase(strValueOf) && !jSONObject.has(str)) {
                    jSONObject.put(str, obj);
                }
            } else {
                jSONObject.put(str, obj);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private static String reaMac(String str) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader = new FileReader(str);
        try {
            bufferedReader = new BufferedReader(fileReader, 1024);
            try {
                String line = bufferedReader.readLine();
                ae.a(fileReader, bufferedReader);
                return line;
            } catch (Throwable th2) {
                th = th2;
                ae.a(fileReader, bufferedReader);
                throw th;
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }

    private static synchronized String readFile(String str) {
        RandomAccessFile randomAccessFile;
        FileChannel channel;
        File file;
        FileLock fileLockLock;
        if (isEmpty(str)) {
            return null;
        }
        try {
            file = new File(str);
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
            channel = null;
        }
        if (!file.exists()) {
            ae.a(null);
            ae.a(null);
            return null;
        }
        randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            channel = randomAccessFile.getChannel();
            try {
                fileLockLock = channel.lock(0L, Long.MAX_VALUE, true);
            } catch (Throwable th2) {
                th = th2;
                try {
                    ExceptionUtil.exceptionThrow(th);
                    ae.a(channel);
                    ae.a(randomAccessFile);
                    return null;
                } catch (Throwable th3) {
                    ae.a(channel);
                    ae.a(randomAccessFile);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            channel = null;
        }
        if (fileLockLock == null || !fileLockLock.isValid()) {
            ae.a(channel);
            ae.a(randomAccessFile);
            return null;
        }
        randomAccessFile.seek(0L);
        byte[] bArr = new byte[(int) randomAccessFile.length()];
        randomAccessFile.read(bArr);
        String str2 = new String(bArr, "utf-8");
        ae.a(channel);
        ae.a(randomAccessFile);
        return str2;
    }

    public static String readStream(InputStream inputStream) {
        StringBuffer stringBuffer;
        Throwable th;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            stringBuffer = new StringBuffer();
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuffer.append(line);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ExceptionUtil.exceptionThrow(th);
                        ae.a(inputStream);
                        return String.valueOf(stringBuffer);
                    } catch (Throwable th3) {
                        ae.a(inputStream);
                        throw th3;
                    }
                }
            }
            bufferedReader.close();
            ae.a(inputStream);
        } catch (Throwable th4) {
            stringBuffer = null;
            th = th4;
        }
        return String.valueOf(stringBuffer);
    }

    public static Object reflexStaticMethod(String str, String str2, Class[] clsArr, Object... objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    public static Object reflexUtils(String str, String str2) {
        try {
            Class<?> cls = Class.forName(str);
            Method declaredMethod = cls.getDeclaredMethod(str2, new Class[0]);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(cls.newInstance(), new Object[0]);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    public static Object reflexUtils(String str, String str2, Class[] clsArr, Object... objArr) {
        try {
            Class<?> cls = Class.forName(str);
            Method declaredMethod = cls.getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(cls.newInstance(), objArr);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    @Deprecated
    public static void setIdFile(Context context, String str, String str2) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String str3 = context.getFilesDir().getPath() + "id.e";
                String file = readFile(str3);
                JSONObject jSONObject = !TextUtils.isEmpty(file) ? new JSONObject(file) : new JSONObject();
                if (TextUtils.isEmpty(str2)) {
                    jSONObject.remove(str);
                } else {
                    jSONObject.put(str, str2);
                }
                writeFile(str3, String.valueOf(jSONObject));
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
    }

    public static String timeConversion(long j) {
        return j == 0 ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date(j));
    }

    public static List<String> toList(String str) {
        ArrayList arrayList = new ArrayList();
        if (!isEmpty(str)) {
            if (str.contains("$$")) {
                Collections.addAll(arrayList, str.split("\\$\\$"));
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static Set<String> toSet(String str) {
        if (isEmpty(str)) {
            return null;
        }
        HashSet hashSet = new HashSet();
        if (str.contains("$$")) {
            Collections.addAll(hashSet, str.split("\\$\\$"));
            return hashSet;
        }
        hashSet.add(str);
        return hashSet;
    }

    public static String toString(List<String> list) {
        String str = "";
        if (list != null && !list.isEmpty()) {
            for (String str2 : list) {
                str = isEmpty(str) ? str2 : str + "$$" + str2;
            }
        }
        return str;
    }

    public static String toString(Set<String> set) {
        String str = "";
        if (set != null && !set.isEmpty()) {
            for (String str2 : set) {
                str = isEmpty(str) ? str2 : str + "$$" + str2;
            }
        }
        return str;
    }

    private static synchronized void writeFile(String str, String str2) {
        FileChannel fileChannel;
        File file;
        if (isEmpty(str)) {
            return;
        }
        FileChannel channel = null;
        try {
            file = new File(str);
        } catch (Throwable th) {
            th = th;
            fileChannel = null;
        }
        if (TextUtils.isEmpty(str2)) {
            file.delete();
            ae.a(null);
            ae.a(null);
            return;
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            channel = randomAccessFile.getChannel();
            FileLock fileLockLock = channel.lock(0L, Long.MAX_VALUE, false);
            if (fileLockLock != null && fileLockLock.isValid()) {
                channel.truncate(0L);
                channel.write(ByteBuffer.wrap(str2.getBytes()));
                fileLockLock.release();
            }
            ae.a(randomAccessFile);
            ae.a(channel);
        } catch (Throwable th2) {
            FileChannel fileChannel2 = channel;
            channel = randomAccessFile;
            th = th2;
            fileChannel = fileChannel2;
            try {
                ExceptionUtil.exceptionThrow(th);
                ae.a(channel);
                ae.a(fileChannel);
            } catch (Throwable th3) {
                ae.a(channel);
                ae.a(fileChannel);
                throw th3;
            }
        }
    }
}
