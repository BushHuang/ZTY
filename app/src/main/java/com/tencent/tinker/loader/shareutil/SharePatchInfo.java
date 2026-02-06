package com.tencent.tinker.loader.shareutil;

import android.os.Build;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SharePatchInfo {
    public static final String DEFAULT_DIR = "odex";
    public static final String FINGER_PRINT = "print";
    public static final String IS_PROTECTED_APP = "is_protected_app";
    public static final String IS_REMOVE_INTERPRET_OAT_DIR = "is_remove_interpret_oat_dir";
    public static final String IS_REMOVE_NEW_VERSION = "is_remove_new_version";
    public static final int MAX_EXTRACT_ATTEMPTS = 2;
    public static final String NEW_VERSION = "new";
    public static final String OAT_DIR = "dir";
    public static final String OLD_VERSION = "old";
    private static final String TAG = "Tinker.PatchInfo";
    public String fingerPrint;
    public boolean isProtectedApp;
    public boolean isRemoveInterpretOATDir;
    public boolean isRemoveNewVersion;
    public String newVersion;
    public String oatDir;
    public String oldVersion;

    public SharePatchInfo(String str, String str2, boolean z, boolean z2, String str3, String str4, boolean z3) {
        this.oldVersion = str;
        this.newVersion = str2;
        this.isProtectedApp = z;
        this.isRemoveNewVersion = z2;
        this.fingerPrint = str3;
        this.oatDir = str4;
        this.isRemoveInterpretOATDir = z3;
    }

    private static SharePatchInfo readAndCheckProperty(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        String property = null;
        String property2 = null;
        String property3 = null;
        String property4 = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (i < 2 && !z) {
            int i2 = i + 1;
            Properties properties = new Properties();
            try {
                try {
                } catch (IOException e) {
                    e = e;
                }
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream = null;
                    Log.w("Tinker.PatchInfo", "read property failed, e:" + e);
                    SharePatchFileUtil.closeQuietly(fileInputStream);
                    if (property != null) {
                    }
                    i = i2;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    properties.load(fileInputStream);
                    property = properties.getProperty("old");
                    property2 = properties.getProperty("new");
                    String property5 = properties.getProperty("is_protected_app");
                    z2 = (property5 == null || property5.isEmpty() || "0".equals(property5)) ? false : true;
                    String property6 = properties.getProperty("is_remove_new_version");
                    z3 = (property6 == null || property6.isEmpty() || "0".equals(property6)) ? false : true;
                    property3 = properties.getProperty("print");
                    property4 = properties.getProperty("dir");
                    String property7 = properties.getProperty("is_remove_interpret_oat_dir");
                    z4 = (property7 == null || property7.isEmpty() || "0".equals(property7)) ? false : true;
                } catch (IOException e3) {
                    e = e3;
                    Log.w("Tinker.PatchInfo", "read property failed, e:" + e);
                    SharePatchFileUtil.closeQuietly(fileInputStream);
                    if (property != null) {
                    }
                    i = i2;
                }
                SharePatchFileUtil.closeQuietly(fileInputStream);
                if (property != null && property2 != null) {
                    if ((property.equals("") || SharePatchFileUtil.checkIfMd5Valid(property)) && SharePatchFileUtil.checkIfMd5Valid(property2)) {
                        i = i2;
                        z = true;
                    } else {
                        Log.w("Tinker.PatchInfo", "path info file  corrupted:" + file.getAbsolutePath());
                    }
                }
                i = i2;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                SharePatchFileUtil.closeQuietly(fileInputStream2);
                throw th;
            }
        }
        if (z) {
            return new SharePatchInfo(property, property2, z2, z3, property3, property4, z4);
        }
        return null;
    }

    public static SharePatchInfo readAndCheckPropertyWithLock(File file, File file2) {
        ShareFileLockHelper fileLock = null;
        if (file == null || file2 == null) {
            return null;
        }
        File parentFile = file2.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            try {
                fileLock = ShareFileLockHelper.getFileLock(file2);
                return readAndCheckProperty(file);
            } catch (Exception e) {
                throw new TinkerRuntimeException("readAndCheckPropertyWithLock fail", e);
            }
        } finally {
            if (fileLock != null) {
                try {
                    fileLock.close();
                } catch (IOException e2) {
                    Log.w("Tinker.PatchInfo", "releaseInfoLock error", e2);
                }
            }
        }
    }

    private static boolean rewritePatchInfoFile(File file, SharePatchInfo sharePatchInfo) throws Throwable {
        if (file != null && sharePatchInfo != null) {
            if (ShareTinkerInternals.isNullOrNil(sharePatchInfo.fingerPrint)) {
                sharePatchInfo.fingerPrint = Build.FINGERPRINT;
            }
            if (ShareTinkerInternals.isNullOrNil(sharePatchInfo.oatDir)) {
                sharePatchInfo.oatDir = "odex";
            }
            Log.i("Tinker.PatchInfo", "rewritePatchInfoFile file path:" + file.getAbsolutePath() + " , oldVer:" + sharePatchInfo.oldVersion + ", newVer:" + sharePatchInfo.newVersion + ", isProtectedApp:" + (sharePatchInfo.isProtectedApp ? 1 : 0) + ", isRemoveNewVersion:" + (sharePatchInfo.isRemoveNewVersion ? 1 : 0) + ", fingerprint:" + sharePatchInfo.fingerPrint + ", oatDir:" + sharePatchInfo.oatDir + ", isRemoveInterpretOATDir:" + (sharePatchInfo.isRemoveInterpretOATDir ? 1 : 0));
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            int i = 0;
            boolean z = false;
            while (i < 2 && !z) {
                i++;
                Properties properties = new Properties();
                properties.put("old", sharePatchInfo.oldVersion);
                properties.put("new", sharePatchInfo.newVersion);
                properties.put("is_protected_app", sharePatchInfo.isProtectedApp ? "1" : "0");
                properties.put("is_remove_new_version", sharePatchInfo.isRemoveNewVersion ? "1" : "0");
                properties.put("print", sharePatchInfo.fingerPrint);
                properties.put("dir", sharePatchInfo.oatDir);
                properties.put("is_remove_interpret_oat_dir", sharePatchInfo.isRemoveInterpretOATDir ? "1" : "0");
                FileOutputStream fileOutputStream = null;
                try {
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                        try {
                            properties.store(fileOutputStream2, "from old version:" + sharePatchInfo.oldVersion + " to new version:" + sharePatchInfo.newVersion);
                            SharePatchFileUtil.closeQuietly(fileOutputStream2);
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            Log.w("Tinker.PatchInfo", "write property failed, e:" + e);
                            SharePatchFileUtil.closeQuietly(fileOutputStream);
                            SharePatchInfo andCheckProperty = readAndCheckProperty(file);
                            if (andCheckProperty == null) {
                            }
                            if (z) {
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            SharePatchFileUtil.closeQuietly(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
                SharePatchInfo andCheckProperty2 = readAndCheckProperty(file);
                z = andCheckProperty2 == null && andCheckProperty2.oldVersion.equals(sharePatchInfo.oldVersion) && andCheckProperty2.newVersion.equals(sharePatchInfo.newVersion);
                if (z) {
                    file.delete();
                }
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static boolean rewritePatchInfoFileWithLock(File file, SharePatchInfo sharePatchInfo, File file2) {
        if (file == null || sharePatchInfo == null || file2 == null) {
            return false;
        }
        File parentFile = file2.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        ShareFileLockHelper fileLock = null;
        try {
            try {
                fileLock = ShareFileLockHelper.getFileLock(file2);
                return rewritePatchInfoFile(file, sharePatchInfo);
            } finally {
                if (fileLock != null) {
                    try {
                        fileLock.close();
                    } catch (IOException e) {
                        Log.i("Tinker.PatchInfo", "releaseInfoLock error", e);
                    }
                }
            }
        } catch (Exception e2) {
            throw new TinkerRuntimeException("rewritePatchInfoFileWithLock fail", e2);
        }
    }
}
