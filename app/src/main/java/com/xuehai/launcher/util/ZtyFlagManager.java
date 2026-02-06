package com.xuehai.launcher.util;

import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.plugins.TaskPlugins;
import com.xuehai.provider.core.XHContentProvider;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\rJ\u0006\u0010\u0019\u001a\u00020\u0012J\u0006\u0010\u001a\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xuehai/launcher/util/ZtyFlagManager;", "", "()V", "TAG", "", "WAKEUP_INTERRUPT", "", "clearFlagTask", "Ljava/lang/Runnable;", "markErrorTask", "startZtyFailedCount", "ztyWakeupFlag", "clearFlag", "", "increase", "markStartZtyFailed", "markZtyError", "isError", "", "postClear", "delay", "", "postMarkError", "replaceIfExists", "printAllFlag", "startZtyEnable", "stopMarkErrorTask", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ZtyFlagManager {
    private static final int WAKEUP_INTERRUPT = 30;
    private static int startZtyFailedCount;
    private static int ztyWakeupFlag;
    public static final ZtyFlagManager INSTANCE = new ZtyFlagManager();
    private static final String TAG = "ZtyFlagManager[MDM]";
    private static final Runnable markErrorTask = new Runnable() {
        @Override
        public final void run() {
            ZtyFlagManager.m184markErrorTask$lambda0();
        }
    };
    private static final Runnable clearFlagTask = new Runnable() {
        @Override
        public final void run() {
            ZtyFlagManager.m183clearFlagTask$lambda1();
        }
    };

    private ZtyFlagManager() {
    }

    private static final void m183clearFlagTask$lambda1() {
        INSTANCE.clearFlag();
    }

    private static final void m184markErrorTask$lambda0() {
        INSTANCE.increase();
    }

    public static void postClear$default(ZtyFlagManager ztyFlagManager, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 10000;
        }
        ztyFlagManager.postClear(j);
    }

    public static void postMarkError$default(ZtyFlagManager ztyFlagManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        ztyFlagManager.postMarkError(z);
    }

    public final void clearFlag() {
        ztyWakeupFlag = 0;
        startZtyFailedCount = 0;
        XHContentProvider.INSTANCE.clearFlag(BaseApplication.INSTANCE.getInstance().getDeviceProtectedStorageContext());
        MdmLog.i(TAG, "clear all flag");
    }

    public final void increase() {
        ztyWakeupFlag++;
        MdmLog.i(TAG, "ztyWakeupFlag increase: " + ztyWakeupFlag);
    }

    public final void markStartZtyFailed() {
        startZtyFailedCount++;
        MdmLog.i(TAG, "启动智通平台失败: " + startZtyFailedCount);
    }

    public final void markZtyError(boolean isError) {
        if (isError) {
            markStartZtyFailed();
        }
    }

    public final void postClear(long delay) {
        TaskPlugins.postTask(clearFlagTask, delay);
    }

    public final void postMarkError(boolean replaceIfExists) {
        MdmLog.i(TAG, "postMarkError");
        TaskPlugins.postTask(markErrorTask, 20000L);
    }

    public final void printAllFlag() {
        MdmLog.i(TAG, "ztyWakeupFlag: " + ztyWakeupFlag + "; startZtyFailedCount: " + startZtyFailedCount);
    }

    public final boolean startZtyEnable() {
        return ztyWakeupFlag <= 30;
    }

    public final void stopMarkErrorTask() {
        MdmLog.i(TAG, "removeMarkError");
        TaskPlugins.removeTask(markErrorTask);
    }
}
