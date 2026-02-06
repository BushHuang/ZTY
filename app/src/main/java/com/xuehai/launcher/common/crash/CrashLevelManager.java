package com.xuehai.launcher.common.crash;

import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.plugins.TaskPlugins;
import com.xuehai.launcher.common.util.LauncherSPUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000bJ\u0010\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u000bJ\b\u0010\u0014\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xuehai/launcher/common/crash/CrashLevelManager;", "", "()V", "KEY_CRASH_APP_VERSION_CODE", "", "KEY_CRASH_FLAG", "KEY_CRASH_TIME", "SERIOUS_CRASH_FLAG", "", "flag", "clear", "", "initLevel", "isSeriousCrash", "", "levelUp", "postClear", "delay", "", "simulateFatalCrash", "updateFlag", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CrashLevelManager {
    public static final CrashLevelManager INSTANCE = new CrashLevelManager();
    private static final String KEY_CRASH_APP_VERSION_CODE = "crash_app_version_code";
    private static final String KEY_CRASH_FLAG = "crash_flag";
    private static final String KEY_CRASH_TIME = "crash_time";
    public static final int SERIOUS_CRASH_FLAG = 3;
    private static int flag;

    private CrashLevelManager() {
    }

    private final void clear() {
        flag = 0;
        updateFlag();
    }

    public static void postClear$default(CrashLevelManager crashLevelManager, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 60000;
        }
        crashLevelManager.postClear(j);
    }

    private static final void m57postClear$lambda0() {
        INSTANCE.clear();
    }

    private final void updateFlag() {
        LauncherSPUtil.commit("crash_app_version_code", Integer.valueOf(ClientConfig.INSTANCE.getAppVersionCode()));
        LauncherSPUtil.commit("crash_time", Long.valueOf(System.currentTimeMillis()));
        LauncherSPUtil.commit("crash_flag", Integer.valueOf(flag));
        CrashLog.e("崩溃管理: time " + System.currentTimeMillis() + ", flag >> " + flag);
    }

    public final void initLevel() {
        int iIntValue = 0;
        Integer num = (Integer) LauncherSPUtil.get("crash_app_version_code", 0);
        int appVersionCode = ClientConfig.INSTANCE.getAppVersionCode();
        if (num != null && num.intValue() == appVersionCode) {
            Object obj = LauncherSPUtil.get("crash_flag", 0);
            Intrinsics.checkNotNullExpressionValue(obj, "{\n                Launch…SH_FLAG, 0)\n            }");
            iIntValue = ((Number) obj).intValue();
        }
        flag = iIntValue;
    }

    public final boolean isSeriousCrash() {
        return flag >= 3;
    }

    public final void levelUp() {
        flag++;
        updateFlag();
    }

    public final void postClear(long delay) {
        TaskPlugins.postTask(new Runnable() {
            @Override
            public final void run() {
                CrashLevelManager.m57postClear$lambda0();
            }
        }, delay);
    }

    public final void simulateFatalCrash() {
        CrashLog.e("模拟崩溃，进入安全模式: time " + System.currentTimeMillis() + ", old-flag >> " + flag);
        flag = 3;
        updateFlag();
    }
}
