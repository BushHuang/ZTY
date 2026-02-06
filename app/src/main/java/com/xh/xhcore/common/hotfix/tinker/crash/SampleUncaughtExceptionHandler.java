package com.xh.xhcore.common.hotfix.tinker.crash;

import android.content.SharedPreferences;
import android.os.SystemClock;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.xh.xhcore.common.hotfix.tinker.reporter.SampleTinkerReport;
import com.xh.xhcore.common.hotfix.tinker.util.TinkerManager;
import com.xh.xhcore.common.hotfix.tinker.util.Utils;
import java.lang.Thread;

public class SampleUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String DALVIK_XPOSED_CRASH = "Class ref in pre-verified class resolved to unexpected implementation";
    public static final int MAX_CRASH_COUNT = 3;
    private static final long QUICK_CRASH_ELAPSE = 10000;
    private static final String TAG = "Tinker.SampleUncaughtExHandler";
    private final Thread.UncaughtExceptionHandler ueh = Thread.getDefaultUncaughtExceptionHandler();

    private boolean tinkerFastCrashProtect() throws Throwable {
        ApplicationLike tinkerApplicationLike = TinkerManager.getTinkerApplicationLike();
        if (tinkerApplicationLike != null && tinkerApplicationLike.getApplication() != null && TinkerApplicationHelper.isTinkerLoadSuccess(tinkerApplicationLike) && SystemClock.elapsedRealtime() - tinkerApplicationLike.getApplicationStartElapsedTime() < 10000) {
            String currentVersion = TinkerApplicationHelper.getCurrentVersion(tinkerApplicationLike);
            if (ShareTinkerInternals.isNullOrNil(currentVersion)) {
                return false;
            }
            SharedPreferences sharedPreferences = tinkerApplicationLike.getApplication().getSharedPreferences("tinker_share_config", 4);
            int i = sharedPreferences.getInt(currentVersion, 0) + 1;
            if (i >= 3) {
                SampleTinkerReport.onFastCrashProtect();
                TinkerApplicationHelper.cleanPatch(tinkerApplicationLike);
                TinkerLog.e("Tinker.SampleUncaughtExHandler", "tinker has fast crash more than %d, we just clean patch!", Integer.valueOf(i));
                return true;
            }
            sharedPreferences.edit().putInt(currentVersion, i).commit();
            TinkerLog.e("Tinker.SampleUncaughtExHandler", "tinker has fast crash %d times", Integer.valueOf(i));
        }
        return false;
    }

    private void tinkerPreVerifiedCrashHandler(Throwable th) {
        ApplicationLike tinkerApplicationLike = TinkerManager.getTinkerApplicationLike();
        if (tinkerApplicationLike == null || tinkerApplicationLike.getApplication() == null) {
            TinkerLog.w("Tinker.SampleUncaughtExHandler", "applicationlike is null", new Object[0]);
            return;
        }
        if (!TinkerApplicationHelper.isTinkerLoadSuccess(tinkerApplicationLike)) {
            TinkerLog.w("Tinker.SampleUncaughtExHandler", "tinker is not loaded", new Object[0]);
            return;
        }
        boolean zIsXposedExists = false;
        while (th != null) {
            if (!zIsXposedExists) {
                zIsXposedExists = Utils.isXposedExists(th);
            }
            if (zIsXposedExists) {
                if ((th instanceof IllegalAccessError) && th.getMessage().contains("Class ref in pre-verified class resolved to unexpected implementation")) {
                    SampleTinkerReport.onXposedCrash();
                    TinkerLog.e("Tinker.SampleUncaughtExHandler", "have xposed: just clean tinker", new Object[0]);
                    ShareTinkerInternals.killAllOtherProcess(tinkerApplicationLike.getApplication());
                    TinkerApplicationHelper.cleanPatch(tinkerApplicationLike);
                    ShareTinkerInternals.setTinkerDisableWithSharedPreferences(tinkerApplicationLike.getApplication());
                    return;
                }
            }
            th = th.getCause();
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) throws Throwable {
        TinkerLog.e("Tinker.SampleUncaughtExHandler", "uncaughtException:" + th.getMessage(), new Object[0]);
        tinkerFastCrashProtect();
        tinkerPreVerifiedCrashHandler(th);
        this.ueh.uncaughtException(thread, th);
    }
}
