package com.xh.xhcore.common.hotfix.tinker.util;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.xh.xhcore.common.hotfix.TinkerAliyunPatchUtil;
import com.xh.xhcore.common.hotfix.TinkerPatchUtil;
import com.xh.xhcore.common.hotfix.UploadLoadReporter;
import com.xh.xhcore.common.hotfix.UploadPatchListener;
import com.xh.xhcore.common.hotfix.UploadPatchReporter;
import com.xh.xhcore.common.hotfix.tinker.crash.SampleUncaughtExceptionHandler;
import com.xh.xhcore.common.hotfix.tinker.service.SampleResultService;
import com.xh.xhcore.common.hotfix.tinker.util.Utils;
import com.xh.xhcore.common.util.XHAppUtil;

public class TinkerManager {
    public static final String PATCH_VERSON_SP_KEY = XHAppUtil.getTinkerId() + "_patchVersion";
    private static final String TAG = "Tinker.TinkerManager";
    private static ApplicationLike applicationLike = null;
    private static boolean isInstalled = false;
    private static boolean sPatchRollbackOnScreenOff = false;
    private static SampleUncaughtExceptionHandler uncaughtExceptionHandler;

    private static void cleanAll() {
        savePatchVersion("");
        Tinker.with(applicationLike.getApplication()).cleanPatch();
    }

    public static void downloadPatch(String str) {
        TinkerAliyunPatchUtil.downloadPatchWithAliyunObjectKey(str, TinkerAliyunPatchUtil.generateAliyunObjectKey(str));
    }

    public static ApplicationLike getTinkerApplicationLike() {
        return applicationLike;
    }

    public static void initFastCrashProtect() {
        if (uncaughtExceptionHandler == null) {
            SampleUncaughtExceptionHandler sampleUncaughtExceptionHandler = new SampleUncaughtExceptionHandler();
            uncaughtExceptionHandler = sampleUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(sampleUncaughtExceptionHandler);
        }
    }

    public static void installTinker(ApplicationLike applicationLike2) {
        if (isInstalled) {
            TinkerLog.w("Tinker.TinkerManager", "install tinker, but has installed, ignore", new Object[0]);
        } else {
            TinkerInstaller.install(applicationLike2, new UploadLoadReporter(applicationLike2.getApplication()), new UploadPatchReporter(applicationLike2.getApplication()), new UploadPatchListener(applicationLike2.getApplication()), SampleResultService.class, new UpgradePatch());
            isInstalled = true;
        }
    }

    public static String loadPatchVersion() {
        return TinkerPatchUtil.getPatchConfigSP().getString(PATCH_VERSON_SP_KEY, "");
    }

    public static void onPatchRollback() {
        if (TextUtils.isEmpty(loadPatchVersion())) {
            return;
        }
        if (sPatchRollbackOnScreenOff) {
            final Application application = applicationLike.getApplication();
            new Utils.ScreenState(application, new Utils.ScreenState.IOnScreenOff() {
                @Override
                public final void onScreenOff() {
                    TinkerManager.rollbackPatchDirectly(application);
                }
            });
        }
        cleanAll();
    }

    private static void rollbackPatchDirectly(Context context) {
        Tinker tinkerWith = Tinker.with(context);
        ShareTinkerInternals.killAllOtherProcess(context);
        tinkerWith.cleanPatch();
        SampleResultService.restartProcess(applicationLike.getApplication());
    }

    public static void sampleInstallTinker(ApplicationLike applicationLike2) {
        if (isInstalled) {
            TinkerLog.w("Tinker.TinkerManager", "install tinker, but has installed, ignore", new Object[0]);
        } else {
            TinkerInstaller.install(applicationLike2);
            isInstalled = true;
        }
    }

    public static void savePatchVersion(String str) {
        TinkerPatchUtil.getPatchConfigSP().edit().putString(PATCH_VERSON_SP_KEY, str).apply();
    }

    public static void setPatchRestartOnScreenOff(boolean z) {
        SampleResultService.patchRestartOnScreenOff = z;
    }

    public static void setPatchRollbackOnScreenOff(boolean z) {
        sPatchRollbackOnScreenOff = z;
    }

    public static void setTinkerApplicationLike(ApplicationLike applicationLike2) {
        applicationLike = applicationLike2;
    }

    public static void setUpgradeRetryEnable(boolean z) {
        UpgradePatchRetry.getInstance(applicationLike.getApplication()).setRetryEnable(z);
    }
}
