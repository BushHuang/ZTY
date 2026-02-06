package com.tencent.tinker.lib.util;

import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.commons.util.IOHelper;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UpgradePatchRetry {
    private static final String RETRY_COUNT_PROPERTY = "times";
    private static final String RETRY_FILE_MD5_PROPERTY = "md5";
    private static final String RETRY_INFO_NAME = "patch.retry";
    private static final int RETRY_MAX_COUNT = 20;
    private static final String TAG = "Tinker.UpgradePatchRetry";
    private static final String TEMP_PATCH_NAME = "temp.apk";
    private static UpgradePatchRetry sInstance;
    private Context context;
    private boolean isRetryEnable = true;
    private int maxRetryCount = 20;
    private File retryInfoFile;
    private File tempPatchFile;

    static class RetryInfo {
        String md5;
        String times;

        RetryInfo(String str, String str2) {
            this.md5 = str;
            this.times = str2;
        }

        static RetryInfo readRetryProperty(File file) throws Throwable {
            String property;
            InputStream fileInputStream;
            Properties properties = new Properties();
            String property2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        properties.load(fileInputStream);
                        property = properties.getProperty("md5");
                        try {
                            property2 = properties.getProperty("times");
                        } catch (IOException e) {
                            e = e;
                            TinkerLog.e("Tinker.UpgradePatchRetry", "fail to readRetryProperty:" + e, new Object[0]);
                            IOHelper.closeQuietly(fileInputStream);
                            return new RetryInfo(property, property2);
                        }
                    } catch (IOException e2) {
                        e = e2;
                        property = null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    property = null;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    IOHelper.closeQuietly(property2);
                    throw th;
                }
                IOHelper.closeQuietly(fileInputStream);
                return new RetryInfo(property, property2);
            } catch (Throwable th2) {
                th = th2;
                property2 = fileInputStream;
                IOHelper.closeQuietly(property2);
                throw th;
            }
        }

        static void writeRetryProperty(File file, RetryInfo retryInfo) throws Throwable {
            if (retryInfo == null) {
                return;
            }
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            Properties properties = new Properties();
            properties.put("md5", retryInfo.md5);
            properties.put("times", retryInfo.times);
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                    try {
                        properties.store(fileOutputStream2, (String) null);
                        IOHelper.closeQuietly(fileOutputStream2);
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        TinkerLog.printErrStackTrace("Tinker.UpgradePatchRetry", e, "retry write property fail", new Object[0]);
                        IOHelper.closeQuietly(fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        IOHelper.closeQuietly(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
    }

    public UpgradePatchRetry(Context context) {
        this.retryInfoFile = null;
        this.tempPatchFile = null;
        this.context = null;
        this.context = context;
        this.retryInfoFile = new File(SharePatchFileUtil.getPatchTempDirectory(context), "patch.retry");
        this.tempPatchFile = new File(SharePatchFileUtil.getPatchTempDirectory(context), "temp.apk");
    }

    private void copyToTempFile(File file) throws Throwable {
        if (file.getAbsolutePath().equals(this.tempPatchFile.getAbsolutePath())) {
            return;
        }
        TinkerLog.w("Tinker.UpgradePatchRetry", "try copy file: %s to %s", file.getAbsolutePath(), this.tempPatchFile.getAbsolutePath());
        try {
            SharePatchFileUtil.copyFileUsingStream(file, this.tempPatchFile);
        } catch (IOException unused) {
            TinkerLog.e("Tinker.UpgradePatchRetry", "fail to copy file: %s to %s", file.getAbsolutePath(), this.tempPatchFile.getAbsolutePath());
        }
    }

    public static UpgradePatchRetry getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UpgradePatchRetry(context);
        }
        return sInstance;
    }

    public boolean onPatchListenerCheck(String str) throws Throwable {
        int i;
        if (!this.isRetryEnable) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck retry disabled, just return", new Object[0]);
            return true;
        }
        if (!this.retryInfoFile.exists()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck retry file is not exist, just return", new Object[0]);
            return true;
        }
        if (str == null) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck md5 is null, just return", new Object[0]);
            return true;
        }
        RetryInfo retryProperty = RetryInfo.readRetryProperty(this.retryInfoFile);
        if (!str.equals(retryProperty.md5) || (i = Integer.parseInt(retryProperty.times)) < this.maxRetryCount) {
            return true;
        }
        TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck, retry count %d must exceed than max retry count", Integer.valueOf(i));
        SharePatchFileUtil.safeDeleteFile(this.tempPatchFile);
        return false;
    }

    public boolean onPatchResetMaxCheck(String str) throws Throwable {
        if (!this.isRetryEnable) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck retry disabled, just return", new Object[0]);
            return true;
        }
        if (!this.retryInfoFile.exists()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck retry file is not exist, just return", new Object[0]);
            return true;
        }
        if (str == null) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck md5 is null, just return", new Object[0]);
            return true;
        }
        RetryInfo retryProperty = RetryInfo.readRetryProperty(this.retryInfoFile);
        if (str.equals(retryProperty.md5)) {
            TinkerLog.i("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck, reset max check to 1", new Object[0]);
            retryProperty.times = "1";
            RetryInfo.writeRetryProperty(this.retryInfoFile, retryProperty);
        }
        return true;
    }

    public boolean onPatchRetryLoad() {
        if (!this.isRetryEnable) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad retry disabled, just return", new Object[0]);
            return false;
        }
        if (!Tinker.with(this.context).isMainProcess()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad retry is not main process, just return", new Object[0]);
            return false;
        }
        if (!this.retryInfoFile.exists()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad retry info not exist, just return", new Object[0]);
            return false;
        }
        if (TinkerServiceInternals.isTinkerPatchServiceRunning(this.context)) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad tinker service is running, just return", new Object[0]);
            return false;
        }
        String absolutePath = this.tempPatchFile.getAbsolutePath();
        if (absolutePath == null || !new File(absolutePath).exists()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad patch file: %s is not exist, just return", absolutePath);
            return false;
        }
        TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad patch file: %s is exist, retry to patch", absolutePath);
        TinkerInstaller.onReceiveUpgradePatch(this.context, absolutePath);
        return true;
    }

    public void onPatchServiceResult() {
        if (!this.isRetryEnable) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceResult retry disabled, just return", new Object[0]);
        } else if (this.tempPatchFile.exists()) {
            SharePatchFileUtil.safeDeleteFile(this.tempPatchFile);
        }
    }

    public void onPatchServiceStart(Intent intent) throws Throwable {
        RetryInfo retryInfo;
        if (!this.isRetryEnable) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart retry disabled, just return", new Object[0]);
            return;
        }
        if (intent == null) {
            TinkerLog.e("Tinker.UpgradePatchRetry", "onPatchServiceStart intent is null, just return", new Object[0]);
            return;
        }
        String patchPathExtra = TinkerPatchService.getPatchPathExtra(intent);
        if (patchPathExtra == null) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart patch path is null, just return", new Object[0]);
            return;
        }
        File file = new File(patchPathExtra);
        String md5 = SharePatchFileUtil.getMD5(file);
        if (md5 == null) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart patch md5 is null, just return", new Object[0]);
            return;
        }
        if (this.retryInfoFile.exists()) {
            retryInfo = RetryInfo.readRetryProperty(this.retryInfoFile);
            if (retryInfo.md5 == null || retryInfo.times == null || !md5.equals(retryInfo.md5)) {
                copyToTempFile(file);
                retryInfo.md5 = md5;
                retryInfo.times = "1";
            } else {
                int i = Integer.parseInt(retryInfo.times);
                if (i >= this.maxRetryCount) {
                    SharePatchFileUtil.safeDeleteFile(this.tempPatchFile);
                    TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart retry more than max count, delete retry info file!", new Object[0]);
                    return;
                }
                retryInfo.times = String.valueOf(i + 1);
            }
        } else {
            copyToTempFile(file);
            retryInfo = new RetryInfo(md5, "1");
        }
        RetryInfo.writeRetryProperty(this.retryInfoFile, retryInfo);
    }

    public void setMaxRetryCount(int i) {
        if (i <= 0) {
            TinkerLog.e("Tinker.UpgradePatchRetry", "max count must large than 0", new Object[0]);
        } else {
            this.maxRetryCount = i;
        }
    }

    public void setRetryEnable(boolean z) {
        this.isRetryEnable = z;
    }
}
