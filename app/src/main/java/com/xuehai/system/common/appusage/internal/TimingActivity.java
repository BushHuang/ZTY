package com.xuehai.system.common.appusage.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001 B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\"\u0010\u001b\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J&\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006!"}, d2 = {"Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "", "stats", "Lcom/xuehai/system/common/appusage/internal/UsageStats;", "className", "", "startTimeStamp", "", "(Lcom/xuehai/system/common/appusage/internal/UsageStats;Ljava/lang/String;J)V", "getClassName", "()Ljava/lang/String;", "setClassName", "(Ljava/lang/String;)V", "getStartTimeStamp", "()J", "setStartTimeStamp", "(J)V", "getStats", "()Lcom/xuehai/system/common/appusage/internal/UsageStats;", "setStats", "(Lcom/xuehai/system/common/appusage/internal/UsageStats;)V", "status", "Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus;", "getStatus", "()Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus;", "setStatus", "(Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus;)V", "receive", "timeStamp", "eventType", "", "reset", "ActivityStatus", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class TimingActivity {
    private String className;
    private long startTimeStamp;
    private UsageStats stats;
    private ActivityStatus status;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus;", "", "(Ljava/lang/String;I)V", "next", "eventType", "", "timeStamp", "", "timing", "Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "Resumed", "Paused", "Stopped", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ActivityStatus {
        public static final ActivityStatus Resumed = new Resumed("Resumed", 0);
        public static final ActivityStatus Paused = new Paused("Paused", 1);
        public static final ActivityStatus Stopped = new Stopped("Stopped", 2);
        private static final ActivityStatus[] $VALUES = $values();

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus$Paused;", "Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus;", "next", "eventType", "", "timeStamp", "", "timing", "Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        static final class Paused extends ActivityStatus {
            Paused(String str, int i) {
                super(str, i, null);
            }

            @Override
            public ActivityStatus next(int eventType, long timeStamp, TimingActivity timing) {
                Intrinsics.checkNotNullParameter(timing, "timing");
                if (eventType != 1 && eventType != 4) {
                    return eventType == 23 ? ActivityStatus.Stopped : this;
                }
                timing.setStartTimeStamp(timeStamp);
                return ActivityStatus.Resumed;
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus$Resumed;", "Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus;", "next", "eventType", "", "timeStamp", "", "timing", "Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        static final class Resumed extends ActivityStatus {
            Resumed(String str, int i) {
                super(str, i, null);
            }

            @Override
            public ActivityStatus next(int eventType, long timeStamp, TimingActivity timing) {
                Intrinsics.checkNotNullParameter(timing, "timing");
                if (eventType == 2 || eventType == 3 || eventType == 16) {
                    long startTimeStamp = timeStamp - timing.getStartTimeStamp();
                    UsageStats stats = timing.getStats();
                    stats.setTotalTimeInForeground(stats.getTotalTimeInForeground() + startTimeStamp);
                    timing.setStartTimeStamp(timeStamp);
                    if (IntervalStateKt._IntervalState_shouldDebugPackage(timing.getStats().getPackageName())) {
                        System.out.println((Object) (timing.getStartTimeStamp() + " +" + startTimeStamp + '/' + timing.getStats().getTotalTimeInForeground() + ' ' + timing.getClassName()));
                    }
                    return ActivityStatus.Paused;
                }
                if (eventType != 23) {
                    timing.setStartTimeStamp(timeStamp);
                    return this;
                }
                long startTimeStamp2 = timeStamp - timing.getStartTimeStamp();
                UsageStats stats2 = timing.getStats();
                stats2.setTotalTimeInForeground(stats2.getTotalTimeInForeground() + startTimeStamp2);
                timing.setStartTimeStamp(timeStamp);
                if (IntervalStateKt._IntervalState_shouldDebugPackage(timing.getStats().getPackageName())) {
                    System.out.println((Object) (timing.getStartTimeStamp() + " +" + startTimeStamp2 + '/' + timing.getStats().getTotalTimeInForeground() + ' ' + timing.getClassName()));
                }
                return ActivityStatus.Stopped;
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus$Stopped;", "Lcom/xuehai/system/common/appusage/internal/TimingActivity$ActivityStatus;", "next", "eventType", "", "timeStamp", "", "timing", "Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        static final class Stopped extends ActivityStatus {
            Stopped(String str, int i) {
                super(str, i, null);
            }

            @Override
            public ActivityStatus next(int eventType, long timeStamp, TimingActivity timing) {
                Intrinsics.checkNotNullParameter(timing, "timing");
                if (eventType != 1 && eventType != 4) {
                    return this;
                }
                timing.setStartTimeStamp(timeStamp);
                return ActivityStatus.Resumed;
            }
        }

        private static final ActivityStatus[] $values() {
            return new ActivityStatus[]{Resumed, Paused, Stopped};
        }

        private ActivityStatus(String str, int i) {
        }

        public ActivityStatus(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i);
        }

        public static ActivityStatus valueOf(String str) {
            return (ActivityStatus) Enum.valueOf(ActivityStatus.class, str);
        }

        public static ActivityStatus[] values() {
            return (ActivityStatus[]) $VALUES.clone();
        }

        public abstract ActivityStatus next(int eventType, long timeStamp, TimingActivity timing);
    }

    public TimingActivity(UsageStats usageStats, String str, long j) {
        Intrinsics.checkNotNullParameter(usageStats, "stats");
        this.stats = usageStats;
        this.className = str;
        this.startTimeStamp = j;
        this.status = ActivityStatus.Resumed;
    }

    public TimingActivity(UsageStats usageStats, String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(usageStats, (i & 2) != 0 ? null : str, (i & 4) != 0 ? 0L : j);
    }

    private final TimingActivity reset(UsageStats stats, String className, long startTimeStamp) {
        this.stats = stats;
        this.className = className;
        this.startTimeStamp = startTimeStamp;
        this.status = ActivityStatus.Resumed;
        return this;
    }

    static TimingActivity reset$default(TimingActivity timingActivity, UsageStats usageStats, String str, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reset");
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        return timingActivity.reset(usageStats, str, j);
    }

    public final String getClassName() {
        return this.className;
    }

    public final long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public final UsageStats getStats() {
        return this.stats;
    }

    public final ActivityStatus getStatus() {
        return this.status;
    }

    public TimingActivity receive(String className, long timeStamp, int eventType) {
        if (Intrinsics.areEqual(this.className, className) || eventType == 16 || eventType == 15) {
            this.status = this.status.next(eventType, timeStamp, this);
            return this;
        }
        if (eventType != 1 && eventType != 4) {
            return this;
        }
        if (this.status == ActivityStatus.Resumed) {
            long j = this.startTimeStamp;
            if (timeStamp - j < 10000) {
                long j2 = timeStamp - j;
                UsageStats usageStats = this.stats;
                usageStats.setTotalTimeInForeground(usageStats.getTotalTimeInForeground() + j2);
                if (IntervalStateKt._IntervalState_shouldDebugPackage(this.stats.getPackageName())) {
                    System.out.println((Object) (this.startTimeStamp + " +" + j2 + '/' + this.stats.getTotalTimeInForeground() + ' ' + className));
                }
            }
        }
        return this.stats.take().reset(this.stats, className, timeStamp);
    }

    public final void setClassName(String str) {
        this.className = str;
    }

    public final void setStartTimeStamp(long j) {
        this.startTimeStamp = j;
    }

    public final void setStats(UsageStats usageStats) {
        Intrinsics.checkNotNullParameter(usageStats, "<set-?>");
        this.stats = usageStats;
    }

    public final void setStatus(ActivityStatus activityStatus) {
        Intrinsics.checkNotNullParameter(activityStatus, "<set-?>");
        this.status = activityStatus;
    }
}
