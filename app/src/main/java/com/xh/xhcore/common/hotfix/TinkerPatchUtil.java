package com.xh.xhcore.common.hotfix;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.MessageQueue;
import android.text.TextUtils;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.xh.logutils.CrashOrErrorStatisticsInfo;
import com.xh.view.HandlerThreadManager;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.hotfix.tinker.reporter.SampleTinkerReport;
import com.xh.xhcore.common.hotfix.tinker.util.TinkerManager;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.LooperUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xuehai.platform.hotfix.HotfixPatchInfoManager;
import com.xuehai.platform.hotfix.PatchInfo;
import java.util.concurrent.TimeUnit;

public class TinkerPatchUtil {
    private static final String FETCH_PATCH_LAST_CHECK_MILLIS_SP_KEY = "fetch_patch_last_check_millis";
    public static final String PATCH_CONFIG_SP_FILE_NAME = "patch_config";
    private static final String TAG = "TinkerPatchUtil";
    private static Application mApplication;
    private static TinkerPatchTaskHandler tinkerPatchTaskHandler;

    private static void fetchPatchUpdate(SharedPreferences sharedPreferences, String str) {
        LogUtils.d("TinkerPatchUtil", "fetchPatchUpdate from tinker patch server");
        sharedPreferences.edit().putLong("fetch_patch_last_check_millis", System.currentTimeMillis()).apply();
        TinkerManager.downloadPatch(str);
    }

    private static void fetchPatchUpdateInterval(String str) {
        SharedPreferences patchConfigSP = getPatchConfigSP();
        long j = patchConfigSP.getLong("fetch_patch_last_check_millis", 0L);
        if (j == 0) {
            fetchPatchUpdate(patchConfigSP, str);
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        LogUtils.d("TinkerPatchUtil", "afterLastCheckMillis = " + jCurrentTimeMillis);
        if (jCurrentTimeMillis > TimeUnit.MINUTES.toMillis(30L)) {
            fetchPatchUpdate(patchConfigSP, str);
        }
    }

    private static void fetchPatchUpdateWithMinimize() {
        LooperUtil.addIdleHandlerToMain(new MessageQueue.IdleHandler() {
            @Override
            public final boolean queueIdle() {
                return TinkerPatchUtil.lambda$fetchPatchUpdateWithMinimize$0();
            }
        });
    }

    public static SharedPreferences getPatchConfigSP() {
        return XhBaseApplication.getXhBaseApplication().getSharedPreferences("patch_config", 0);
    }

    private static String getTinkerLoadResultString() {
        TinkerLoadResult tinkerLoadResultIfPresent = Tinker.with(mApplication).getTinkerLoadResultIfPresent();
        if (tinkerLoadResultIfPresent == null) {
            return "tinkerLoaderResult is null";
        }
        return "loadCode is " + tinkerLoadResultIfPresent.loadCode;
    }

    public static TinkerPatchTaskHandler getTinkerPatchTaskHandler() {
        if (tinkerPatchTaskHandler == null) {
            tinkerPatchTaskHandler = new TinkerPatchTaskHandler(HandlerThreadManager.getAppHandler().getLooper(), new Runnable() {
                @Override
                public final void run() {
                    TinkerPatchUtil.tryFetchUpdate();
                }
            });
        }
        return tinkerPatchTaskHandler;
    }

    public static void initTinkerPatch(Application application) {
        mApplication = application;
        if (XHAppConfigProxy.getInstance().getTinkerConfig().isTinkerEnable()) {
            ApplicationLike tinkerPatchApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
            TinkerManager.setTinkerApplicationLike(tinkerPatchApplicationLike);
            SampleTinkerReport.setReporter(new SampleTinkerReport.Reporter() {
                @Override
                public void onReport(int i) {
                    CrashOrErrorStatisticsInfo.appendToExtraUploadMessage("TinkerPatchUtil", "key=" + i);
                }

                @Override
                public void onReport(String str) {
                    CrashOrErrorStatisticsInfo.appendToExtraUploadMessage("TinkerPatchUtil", "message=" + str);
                }
            });
            TinkerManager.initFastCrashProtect();
            TinkerManager.setUpgradeRetryEnable(true);
            TinkerManager.setPatchRollbackOnScreenOff(XHAppConfigProxy.getInstance().getTinkerConfig().isPatchRollbackOnScreenOff());
            TinkerManager.setPatchRestartOnScreenOff(XHAppConfigProxy.getInstance().getTinkerConfig().isPatchRestartOnScreenOff());
            TinkerManager.installTinker(tinkerPatchApplicationLike);
            Tinker.with(tinkerPatchApplicationLike.getApplication());
            if (ShareTinkerInternals.isInMainProcess(application)) {
                fetchPatchUpdateWithMinimize();
                PatchUpdateBroadcastUtil.registerPatchUpdateReceiver(application);
            }
            CrashOrErrorStatisticsInfo.extraUploadMessage.put("tinkerPatchVersion", TinkerManager.loadPatchVersion());
            CrashOrErrorStatisticsInfo.extraUploadMessage.put("tinkerLoadResult", getTinkerLoadResultString());
        }
    }

    public static boolean isPatchAddedOrUpdated(String str, String str2) {
        return !TextUtils.isEmpty(str2) && str2.compareTo(str) > 0;
    }

    private static boolean isPatchUpdateFetchNeeded(String str, String str2) {
        boolean zIsPatchAddedOrUpdated = isPatchAddedOrUpdated(str, str2);
        boolean z = !TextUtils.isEmpty(str2) && str2.equals(str);
        if (zIsPatchAddedOrUpdated) {
            return true;
        }
        return z && !isTinkerLoadSuccess();
    }

    private static boolean isTinkerLoadSuccess() {
        TinkerLoadResult tinkerLoadResultIfPresent = Tinker.with(mApplication).getTinkerLoadResultIfPresent();
        return tinkerLoadResultIfPresent != null && tinkerLoadResultIfPresent.loadCode == 0;
    }

    static boolean lambda$fetchPatchUpdateWithMinimize$0() {
        getTinkerPatchTaskHandler().startNow();
        return false;
    }

    private static void tryFetchUpdate() {
        String strLoadPatchVersion = TinkerManager.loadPatchVersion();
        LogUtils.d("TinkerPatchUtil", "curPatchVersion = " + strLoadPatchVersion);
        tryFetchUpdateInner(strLoadPatchVersion, HotfixPatchInfoManager.queryPatchInfo(XHAppUtil.getPackageName()));
    }

    static void tryFetchUpdateInner(String str, PatchInfo patchInfo) {
        if (patchInfo == null) {
            LogUtils.d("TinkerPatchUtil", "patchInfo is null");
            return;
        }
        LogUtils.d("TinkerPatchUtil", "patchInfo = " + patchInfo.toString());
        int versionCode = XHAppUtil.getVersionCode();
        LogUtils.d("TinkerPatchUtil", "curAppVersionCode = " + versionCode);
        if (versionCode == patchInfo.getVersion()) {
            if (!patchInfo.isActive()) {
                if (patchInfo.isSuspend()) {
                    LogUtils.d("TinkerPatchUtil", "latest patch is suspend, do nothing");
                    return;
                } else if (!patchInfo.isRollback()) {
                    LogUtils.e("TinkerPatchUtil", "patch state error!");
                    return;
                } else {
                    LogUtils.d("TinkerPatchUtil", "patch rollback");
                    TinkerManager.onPatchRollback();
                    return;
                }
            }
            try {
                String patchId = patchInfo.getPatchId();
                LogUtils.d("TinkerPatchUtil", "latestPatchVersion = " + patchId);
                if (isPatchUpdateFetchNeeded(str, patchId)) {
                    fetchPatchUpdateInterval(patchId);
                }
            } catch (NumberFormatException e) {
                LogUtils.e(e);
            }
        }
    }
}
