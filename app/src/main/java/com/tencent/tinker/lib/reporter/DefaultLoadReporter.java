package com.tencent.tinker.lib.reporter;

import android.content.Context;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class DefaultLoadReporter implements LoadReporter {
    private static final String TAG = "Tinker.DefaultLoadReporter";
    protected final Context context;

    public DefaultLoadReporter(Context context) {
        this.context = context;
    }

    public void checkAndCleanPatch() {
        SharePatchInfo sharePatchInfo;
        Tinker tinkerWith = Tinker.with(this.context);
        if (tinkerWith.isMainProcess()) {
            TinkerLoadResult tinkerLoadResultIfPresent = tinkerWith.getTinkerLoadResultIfPresent();
            if (tinkerLoadResultIfPresent.versionChanged && (sharePatchInfo = tinkerLoadResultIfPresent.patchInfo) != null && !ShareTinkerInternals.isNullOrNil(sharePatchInfo.oldVersion)) {
                TinkerLog.w("Tinker.DefaultLoadReporter", "checkAndCleanPatch, oldVersion %s is not null, try kill all other process", sharePatchInfo.oldVersion);
                ShareTinkerInternals.killAllOtherProcess(this.context);
            }
        }
        tinkerWith.cleanPatch();
    }

    @Override
    public void onLoadException(Throwable th, int i) throws Throwable {
        if (i == -4) {
            TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load unCatch exception: %s", th);
            ShareTinkerInternals.setTinkerDisableWithSharedPreferences(this.context);
            TinkerLog.i("Tinker.DefaultLoadReporter", "unCaught exception disable tinker forever with sp", new Object[0]);
            String strCheckTinkerLastUncaughtCrash = SharePatchFileUtil.checkTinkerLastUncaughtCrash(this.context);
            if (!ShareTinkerInternals.isNullOrNil(strCheckTinkerLastUncaughtCrash)) {
                SharePatchFileUtil.safeDeleteFile(SharePatchFileUtil.getPatchLastCrashFile(this.context));
                TinkerLog.e("Tinker.DefaultLoadReporter", "tinker uncaught real exception:" + strCheckTinkerLastUncaughtCrash, new Object[0]);
            }
        } else if (i == -3) {
            if (th.getMessage().contains("checkResInstall failed")) {
                TinkerLog.e("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: tinker res check fail:" + th.getMessage(), new Object[0]);
            } else {
                TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load resource exception: %s", th);
            }
            ShareTinkerInternals.setTinkerDisableWithSharedPreferences(this.context);
            TinkerLog.i("Tinker.DefaultLoadReporter", "res exception disable tinker forever with sp", new Object[0]);
        } else if (i == -2) {
            if (th.getMessage().contains("checkDexInstall failed")) {
                TinkerLog.e("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: tinker dex check fail:" + th.getMessage(), new Object[0]);
            } else {
                TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load dex exception: %s", th);
            }
            ShareTinkerInternals.setTinkerDisableWithSharedPreferences(this.context);
            TinkerLog.i("Tinker.DefaultLoadReporter", "dex exception disable tinker forever with sp", new Object[0]);
        } else if (i == -1) {
            TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load unknown exception: %s", th);
        }
        TinkerLog.e("Tinker.DefaultLoadReporter", "tinker load exception, welcome to submit issue to us: https://github.com/Tencent/tinker/issues", new Object[0]);
        TinkerLog.printErrStackTrace("Tinker.DefaultLoadReporter", th, "tinker load exception", new Object[0]);
        Tinker.with(this.context).setTinkerDisable();
        checkAndCleanPatch();
    }

    @Override
    public void onLoadFileMd5Mismatch(File file, int i) {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch load Reporter onLoadFileMd5Mismatch: patch file md5 mismatch file: %s, fileType: %d", file.getAbsolutePath(), Integer.valueOf(i));
        checkAndCleanPatch();
    }

    @Override
    public void onLoadFileNotFound(File file, int i, boolean z) {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadFileNotFound: patch file not found: %s, fileType: %d, isDirectory: %b", file.getAbsolutePath(), Integer.valueOf(i), Boolean.valueOf(z));
        if (i == 4) {
            retryPatch();
        } else {
            checkAndCleanPatch();
        }
    }

    @Override
    public void onLoadInterpret(int i, Throwable th) {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret: type: %d, throwable: %s", Integer.valueOf(i), th);
        if (i == 0) {
            TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret ok", new Object[0]);
        } else if (i == 1) {
            TinkerLog.e("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret fail, can get instruction set from existed oat file", new Object[0]);
        } else if (i == 2) {
            TinkerLog.e("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret fail, command line to interpret return error", new Object[0]);
        }
        retryPatch();
    }

    @Override
    public void onLoadPackageCheckFail(File file, int i) {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadPackageCheckFail: load patch package check fail file path: %s, errorCode: %d", file.getAbsolutePath(), Integer.valueOf(i));
        checkAndCleanPatch();
    }

    @Override
    public void onLoadPatchInfoCorrupted(String str, String str2, File file) {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadPatchInfoCorrupted: patch info file damage: %s, from version: %s to version: %s", file.getAbsolutePath(), str, str2);
        checkAndCleanPatch();
    }

    @Override
    public void onLoadPatchListenerReceiveFail(File file, int i) {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadPatchListenerReceiveFail: patch receive fail: %s, code: %d", file.getAbsolutePath(), Integer.valueOf(i));
    }

    @Override
    public void onLoadPatchVersionChanged(String str, String str2, File file, String str3) throws Throwable {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadPatchVersionChanged: patch version change from " + str + " to " + str2, new Object[0]);
        if (str == null || str2 == null || str.equals(str2) || !Tinker.with(this.context).isMainProcess()) {
            return;
        }
        UpgradePatchRetry.getInstance(this.context).onPatchResetMaxCheck(str2);
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                String name = file2.getName();
                if (file2.isDirectory() && !name.equals(str3)) {
                    SharePatchFileUtil.deleteDir(file2);
                }
            }
        }
    }

    @Override
    public void onLoadResult(File file, int i, long j) {
        TinkerLog.i("Tinker.DefaultLoadReporter", "patch loadReporter onLoadResult: patch load result, path:%s, code: %d, cost: %dms", file.getAbsolutePath(), Integer.valueOf(i), Long.valueOf(j));
    }

    public boolean retryPatch() {
        File file;
        Tinker tinkerWith = Tinker.with(this.context);
        if (!tinkerWith.isMainProcess() || (file = tinkerWith.getTinkerLoadResultIfPresent().patchVersionFile) == null || !UpgradePatchRetry.getInstance(this.context).onPatchListenerCheck(SharePatchFileUtil.getMD5(file))) {
            return false;
        }
        TinkerLog.i("Tinker.DefaultLoadReporter", "try to repair oat file on patch process", new Object[0]);
        TinkerInstaller.onReceiveUpgradePatch(this.context, file.getAbsolutePath());
        return true;
    }
}
