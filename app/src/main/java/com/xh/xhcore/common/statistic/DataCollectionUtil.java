package com.xh.xhcore.common.statistic;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.xh.xhcore.common.adaptation.TextViewAdaptationUtil;
import com.xh.xhcore.common.base.BaseActivityLifecycleCallbacks;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.strategy.BuryStrategyManager;
import com.xh.xhcore.common.statistic.usage.AppUsageDurationManager;
import com.xh.xhcore.common.util.AppLockUtil;
import com.xh.xhcore.common.util.DensityUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHLog;
import java.io.File;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class DataCollectionUtil {
    private static final String TAG = "DataCollectionUtil";
    private static int count;
    public static Boolean isOnTop = false;
    private static final Set<Class<?>> mIgnoreCheckAppLock = new HashSet();

    static int access$008() {
        int i = count;
        count = i + 1;
        return i;
    }

    static int access$010() {
        int i = count;
        count = i - 1;
        return i;
    }

    public static void dumpBury() {
        ThreadManager.getDISK_IO().execute(new Runnable() {
            @Override
            public final void run() {
                DataCollectionUtil.dumpBuryDirectly();
            }
        });
    }

    public static void dumpBuryDirectly() {
        if (AppBury.getInstance().isBuryEventsEmpty()) {
            return;
        }
        AppBury.getInstance().appendBuryEventToFile(getBuryFile());
        XHLog.i("DataCollectionUtil", "成功记录埋点数据到文件 : " + XhBaseApplication.sPackageName + " buryEventNames = " + AppBury.getInstance().getBuryEventNames());
        AppBury.getInstance().clearBuryEvents();
    }

    public static File getBuryFile() {
        return new File(getLogFilePath(ConstStatistics.getBURY_LOG_PATH(), "bury"));
    }

    private static String getLogFilePath(String str, String str2) {
        return String.format(Locale.getDefault(), "%s/%s_%s_%s.log", str, str2, XhBaseApplication.sPackageName.replace(".", "_"), XhBaseApplication.sVersionName.replace(".", "_"));
    }

    public static void listenerAppLifecycle(Application application, Set<Class<?>> set) {
        mIgnoreCheckAppLock.clear();
        if (set != null) {
            mIgnoreCheckAppLock.addAll(set);
        }
        application.registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                TextViewAdaptationUtil.setIsOrientationPortrait(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
                if (DataCollectionUtil.mIgnoreCheckAppLock.contains(activity.getClass())) {
                    LogUtils.d("DataCollectionUtil", activity.getLocalClassName() + " 被标记为不检测，不触发应用锁检测");
                } else {
                    AppLockUtil.whenActivityOnResume(activity);
                }
                TextViewAdaptationUtil.setIsOrientationPortrait(activity);
                if (XHAppConfigProxy.getInstance().isDensityModeWidth()) {
                    DensityUtil.applyAdaptationDensity(activity);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (DataCollectionUtil.count == 0) {
                    DataCollectionUtil.whenOnTop(activity);
                }
                DataCollectionUtil.access$008();
            }

            @Override
            public void onActivityStopped(Activity activity) {
                DataCollectionUtil.access$010();
                if (DataCollectionUtil.count == 0) {
                    DataCollectionUtil.whenOnBackground();
                }
                DataCollectionUtil.dumpBury();
            }
        });
    }

    @Deprecated
    public static void setBury(int i, String str) {
        setBury(i, str, null);
    }

    @Deprecated
    public static void setBury(int i, String str, String str2) {
        setBury(new BuryEvent(i, str, str2));
    }

    @Deprecated
    public static void setBury(BuryEvent buryEvent) {
        BuryStrategyManager.getInstance().bury(buryEvent);
    }

    public static void setBury(String str, Map<String, Object> map) {
        BuryStrategyManager.getInstance().bury(str, map);
    }

    private static void whenOnBackground() {
        LogUtils.d("DataCollectionUtil", ">>>应用切换到后台 : " + XhBaseApplication.sPackageName + " isProcessBeingKilling = " + XHAppUtil.isProcessBeingKilling);
        isOnTop = false;
        if (XHAppUtil.isProcessBeingKilling.booleanValue()) {
            return;
        }
        AppUsageDurationManager.getInstance().readAndSaveStatisticsInfo();
    }

    private static void whenOnTop(Activity activity) {
        LogUtils.d("DataCollectionUtil", ">>>应用切换到前台 : " + XhBaseApplication.sPackageName);
        isOnTop = true;
        AppLockUtil.whenAppOnTop();
        XhBaseApplication.getXhBaseApplication().startLogcatService();
        AppUsageDurationManager.getInstance().updateOnTopStartTime();
        TopAppManager.getInstance().updateTopApp();
    }
}
