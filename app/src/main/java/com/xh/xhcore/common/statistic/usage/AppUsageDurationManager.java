package com.xh.xhcore.common.statistic.usage;

import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.ConstStatistics;
import com.xh.xhcore.common.statistic.DataCollectionUtil;
import com.xh.xhcore.common.util.JsonUtil;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/statistic/usage/AppUsageDurationManager;", "", "()V", "readAndSaveStatisticsInfo", "", "updateOnTopStartTime", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppUsageDurationManager {
    private static long appOnTopStartTime;

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<AppUsageDurationManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<AppUsageDurationManager>() {
        @Override
        public final AppUsageDurationManager invoke() {
            return new AppUsageDurationManager();
        }
    });

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR!\u0010\t\u001a\u00020\n8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/xh/xhcore/common/statistic/usage/AppUsageDurationManager$Companion;", "", "()V", "appOnTopStartTime", "", "getAppOnTopStartTime", "()J", "setAppOnTopStartTime", "(J)V", "instance", "Lcom/xh/xhcore/common/statistic/usage/AppUsageDurationManager;", "getInstance$annotations", "getInstance", "()Lcom/xh/xhcore/common/statistic/usage/AppUsageDurationManager;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final long getAppOnTopStartTime() {
            return AppUsageDurationManager.appOnTopStartTime;
        }

        public final AppUsageDurationManager getInstance() {
            return (AppUsageDurationManager) AppUsageDurationManager.instance$delegate.getValue();
        }

        public final void setAppOnTopStartTime(long j) {
            AppUsageDurationManager.appOnTopStartTime = j;
        }
    }

    public static final AppUsageDurationManager getInstance() {
        return INSTANCE.getInstance();
    }

    public final void readAndSaveStatisticsInfo() {
        AppStatistics appStatistics = new AppStatistics(System.nanoTime(), appOnTopStartTime);
        if (appOnTopStartTime <= 0 || appStatistics.getAppUsageDuration() <= 0) {
            return;
        }
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("appUsageDuration in Seconds = ", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(appStatistics.getAppUsageDuration()))));
        DataCollectionUtil.setBury(new BuryEvent(ConstStatistics.INSTANCE.getAPP_USAGE_TYPE(), ConstStatistics.INSTANCE.getAPP_USAGE_NAME(), JsonUtil.toJsonString(appStatistics), System.currentTimeMillis()));
    }

    public final void updateOnTopStartTime() {
        appOnTopStartTime = System.nanoTime();
    }
}
