package com.tencent.tinker.loader.shareutil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SharePatchFileUtil {
    private static final String TAG = "Tinker.PatchFileUtil";
    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static boolean checkIfMd5Valid(String str) {
        return str != null && str.length() == 32;
    }

    public static boolean checkResourceArscMd5(File file, String str) {
        ZipFile zipFile;
        ZipEntry entry;
        ZipFile zipFile2 = null;
        InputStream inputStream = null;
        try {
            zipFile = new ZipFile(file);
            try {
                entry = zipFile.getEntry("resources.arsc");
            } catch (Throwable th) {
                th = th;
                zipFile2 = zipFile;
                try {
                    Log.i("Tinker.PatchFileUtil", "checkResourceArscMd5 throwable:" + th.getMessage());
                    return false;
                } finally {
                    closeZip(zipFile2);
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (entry == null) {
            Log.i("Tinker.PatchFileUtil", "checkResourceArscMd5 resources.arsc not found");
            closeZip(zipFile);
            return false;
        }
        try {
            inputStream = zipFile.getInputStream(entry);
            String md5 = getMD5(inputStream);
            if (md5 != null) {
                if (md5.equals(str)) {
                    closeZip(zipFile);
                    return true;
                }
            }
            closeZip(zipFile);
            return false;
        } finally {
            closeQuietly(inputStream);
        }
    }

    public static String checkTinkerLastUncaughtCrash(Context context) throws Throwable {
        BufferedReader bufferedReader;
        Object obj;
        File patchLastCrashFile = getPatchLastCrashFile(context);
        Object obj2 = null;
        if (!isLegalFile(patchLastCrashFile)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(patchLastCrashFile)));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            closeQuietly(bufferedReader);
                            return stringBuffer.toString();
                        }
                        stringBuffer.append(line);
                        stringBuffer.append("\n");
                    } catch (Exception e) {
                        e = e;
                        Log.e("Tinker.PatchFileUtil", "checkTinkerLastUncaughtCrash exception: " + e);
                        closeQuietly(bufferedReader);
                        return null;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                closeQuietly(obj2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            obj2 = obj;
            closeQuietly(obj2);
            throw th;
        }
    }

    public static void closeQuietly(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof Closeable) {
                ((Closeable) obj).close();
            } else if (Build.VERSION.SDK_INT >= 19 && (obj instanceof AutoCloseable)) {
                ((AutoCloseable) obj).close();
            } else {
                if (!(obj instanceof ZipFile)) {
                    throw new IllegalArgumentException("obj: " + obj + " cannot be closed.");
                }
                ((ZipFile) obj).close();
            }
        } catch (Throwable unused) {
        }
    }

    public static void closeZip(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e) {
                Log.w("Tinker.PatchFileUtil", "Failed to close resource", e);
            }
        }
    }

    public static void copyFileUsingStream(File file, File file2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        if (!isLegalFile(file) || file2 == null || file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            return;
        }
        File parentFile = file2.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2, false);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    closeQuietly(fileInputStream);
                    closeQuietly(fileOutputStream);
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            closeQuietly(fileInputStream2);
            closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public static final boolean deleteDir(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            safeDeleteFile(file);
            return true;
        }
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return true;
        }
        for (File file2 : fileArrListFiles) {
            deleteDir(file2);
        }
        safeDeleteFile(file);
        return true;
    }

    public static final boolean deleteDir(String str) {
        if (str == null) {
            return false;
        }
        return deleteDir(new File(str));
    }

    public static void ensureFileDirectory(File file) {
        if (file == null) {
            return;
        }
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            return;
        }
        parentFile.mkdirs();
    }

    public static long getFileOrDirectorySize(File file) {
        long fileOrDirectorySize = 0;
        if (file != null && file.exists()) {
            if (file.isFile()) {
                return file.length();
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    fileOrDirectorySize += file2.isDirectory() ? getFileOrDirectorySize(file2) : file2.length();
                }
            }
        }
        return fileOrDirectorySize;
    }

    public static String getMD5(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            closeQuietly(fileInputStream2);
            throw th;
        }
        try {
            try {
                String md5 = getMD5(fileInputStream);
                closeQuietly(fileInputStream);
                return md5;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                closeQuietly(fileInputStream2);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            Log.e("Tinker.PatchFileUtil", e.getMessage());
            closeQuietly(fileInputStream);
            return null;
        }
    }

    public static final String getMD5(InputStream inputStream) throws NoSuchAlgorithmException {
        int i;
        if (inputStream == null) {
            return null;
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder(32);
            byte[] bArr = new byte[102400];
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i2);
            }
            for (byte b : messageDigest.digest()) {
                sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getMD5(byte[] bArr) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = new char[bArrDigest.length * 2];
            int i = 0;
            for (byte b : bArrDigest) {
                int i2 = i + 1;
                cArr[i] = hexDigits[(b >>> 4) & 15];
                i = i2 + 1;
                cArr[i2] = hexDigits[b & 15];
            }
            return new String(cArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static File getPatchDirectory(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        return new File(applicationInfo.dataDir, "tinker");
    }

    public static File getPatchInfoFile(String str) {
        return new File(str + "/patch.info");
    }

    public static File getPatchInfoLockFile(String str) {
        return new File(str + "/info.lock");
    }

    public static File getPatchLastCrashFile(Context context) {
        File patchTempDirectory = getPatchTempDirectory(context);
        if (patchTempDirectory == null) {
            return null;
        }
        return new File(patchTempDirectory, "tinker_last_crash");
    }

    public static File getPatchTempDirectory(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        return new File(applicationInfo.dataDir, "tinker_temp");
    }

    public static String getPatchVersionDirectory(String str) {
        if (str == null || str.length() != 32) {
            return null;
        }
        return "patch-" + str.substring(0, 8);
    }

    public static String getPatchVersionFile(String str) {
        if (str == null || str.length() != 32) {
            return null;
        }
        return getPatchVersionDirectory(str) + ".apk";
    }

    public static final boolean isLegalFile(File file) {
        return file != null && file.exists() && file.canRead() && file.isFile() && file.length() > 0;
    }

    public static boolean isRawDexFile(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(".dex");
    }

    public static String loadDigestes(JarFile jarFile, JarEntry jarEntry) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bufferedInputStream = null;
        try {
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            byte[] bArr = new byte[16384];
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            while (true) {
                try {
                    int i = bufferedInputStream2.read(bArr);
                    if (i <= 0) {
                        closeQuietly(bufferedInputStream2);
                        return sb.toString();
                    }
                    sb.append(new String(bArr, 0, i));
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    closeQuietly(bufferedInputStream);
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String optimizedPathFor(File file, File file2) {
        if (!ShareTinkerInternals.isAfterAndroidO()) {
            String name = file.getName();
            if (!name.endsWith(".dex")) {
                int iLastIndexOf = name.lastIndexOf(".");
                if (iLastIndexOf < 0) {
                    name = name + ".dex";
                } else {
                    StringBuilder sb = new StringBuilder(iLastIndexOf + 4);
                    sb.append((CharSequence) name, 0, iLastIndexOf);
                    sb.append(".dex");
                    name = sb.toString();
                }
            }
            return new File(file2, name).getPath();
        }
        try {
            String currentInstructionSet = ShareTinkerInternals.getCurrentInstructionSet();
            File parentFile = file.getParentFile();
            String name2 = file.getName();
            int iLastIndexOf2 = name2.lastIndexOf(46);
            if (iLastIndexOf2 > 0) {
                name2 = name2.substring(0, iLastIndexOf2);
            }
            return parentFile.getAbsolutePath() + "/oat/" + currentInstructionSet + "/" + name2 + ".odex";
        } catch (Exception e) {
            throw new TinkerRuntimeException("getCurrentInstructionSet fail:", e);
        }
    }

    public static final boolean safeDeleteFile(File file) {
        boolean zDelete = true;
        if (file == null) {
            return true;
        }
        if (file.exists()) {
            Log.i("Tinker.PatchFileUtil", "safeDeleteFile, try to delete path: " + file.getPath());
            zDelete = file.delete();
            if (!zDelete) {
                Log.e("Tinker.PatchFileUtil", "Failed to delete file, try to delete when exit. path: " + file.getPath());
                file.deleteOnExit();
            }
        }
        return zDelete;
    }

    public static final boolean shouldAcceptEvenIfIllegal(File file) {
        return (("vivo".equalsIgnoreCase(Build.MANUFACTURER) || "oppo".equalsIgnoreCase(Build.MANUFACTURER) || "meizu".equalsIgnoreCase(Build.MANUFACTURER)) || (Build.VERSION.SDK_INT >= 29 || ((Build.VERSION.SDK_INT >= 28 && Build.VERSION.PREVIEW_SDK_INT != 0) || ShareTinkerInternals.isArkHotRuning()))) && (!file.exists() || (file.length() > 0L ? 1 : (file.length() == 0L ? 0 : -1)) == 0);
    }

    public static boolean verifyDexFileMd5(File file, String str) {
        return verifyDexFileMd5(file, "classes.dex", str);
    }

    public static boolean verifyDexFileMd5(File file, String str, String str2) throws Throwable {
        ZipFile zipFile;
        String md5;
        if (file == null || str2 == null || str == null) {
            return false;
        }
        if (isRawDexFile(file.getName())) {
            md5 = getMD5(file);
        } else {
            ZipFile zipFile2 = null;
            InputStream inputStream = null;
            try {
                zipFile = new ZipFile(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                ZipEntry entry = zipFile.getEntry(str);
                if (entry == null) {
                    Log.e("Tinker.PatchFileUtil", "There's no entry named: classes.dex in " + file.getAbsolutePath());
                    closeZip(zipFile);
                    return false;
                }
                try {
                    inputStream = zipFile.getInputStream(entry);
                    String md52 = getMD5(inputStream);
                    closeQuietly(inputStream);
                    md5 = md52;
                } catch (Throwable th2) {
                    try {
                        Log.e("Tinker.PatchFileUtil", "exception occurred when get md5: " + file.getAbsolutePath(), th2);
                        md5 = "";
                    } finally {
                        closeQuietly(inputStream);
                    }
                }
                closeZip(zipFile);
            } catch (Throwable th3) {
                th = th3;
                zipFile2 = zipFile;
                try {
                    Log.e("Tinker.PatchFileUtil", "Bad dex jar file: " + file.getAbsolutePath(), th);
                    return false;
                } finally {
                    closeZip(zipFile2);
                }
            }
        }
        return str2.equals(md5);
    }

    public static boolean verifyFileMd5(File file, String str) {
        String md5;
        if (str == null || (md5 = getMD5(file)) == null) {
            return false;
        }
        return str.equals(md5);
    }
}
