package com.xuehai.system.common.appusage.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ \u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u00032\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0014J\u0006\u0010$\u001a\u00020\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u0018¨\u0006%"}, d2 = {"Lcom/xuehai/system/common/appusage/internal/UsageStats;", "", "packageName", "", "beginTimeStamp", "", "endTimeStamp", "pool", "", "Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "(Ljava/lang/String;JJ[Lcom/xuehai/system/common/appusage/internal/TimingActivity;)V", "getBeginTimeStamp", "()J", "currentTiming", "getCurrentTiming", "()Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "setCurrentTiming", "(Lcom/xuehai/system/common/appusage/internal/TimingActivity;)V", "getEndTimeStamp", "index", "", "lastTimeUsed", "getLastTimeUsed", "setLastTimeUsed", "(J)V", "getPackageName", "()Ljava/lang/String;", "[Lcom/xuehai/system/common/appusage/internal/TimingActivity;", "totalTimeInForeground", "getTotalTimeInForeground", "setTotalTimeInForeground", "receive", "", "className", "timeStamp", "eventType", "take", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UsageStats {
    private final long beginTimeStamp;
    private TimingActivity currentTiming;
    private final long endTimeStamp;
    private int index;
    private long lastTimeUsed;
    private final String packageName;
    private final TimingActivity[] pool;
    private long totalTimeInForeground;

    public UsageStats(String str, long j, long j2, TimingActivity[] timingActivityArr) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(timingActivityArr, "pool");
        this.packageName = str;
        this.beginTimeStamp = j;
        this.endTimeStamp = j2;
        this.pool = timingActivityArr;
        this.index = -1;
        this.currentTiming = new TimingActivity(this) {
            {
                super(this, null, 0L, 2, null);
            }

            @Override
            public TimingActivity receive(String className, long timeStamp, int eventType) {
                return (eventType == 1 || eventType == 4) ? new TimingActivity(getStats(), className, timeStamp) : this;
            }
        };
    }

    public UsageStats(String str, long j, long j2, TimingActivity[] timingActivityArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, j2, (i & 8) != 0 ? new TimingActivity[]{new TimingActivity(new UsageStats("", 0L, 0L, new TimingActivity[0]), null, 0L, 6, null), new TimingActivity(new UsageStats("", 0L, 0L, new TimingActivity[0]), null, 0L, 6, null)} : timingActivityArr);
    }

    public final long getBeginTimeStamp() {
        return this.beginTimeStamp;
    }

    public final TimingActivity getCurrentTiming() {
        return this.currentTiming;
    }

    public final long getEndTimeStamp() {
        return this.endTimeStamp;
    }

    public final long getLastTimeUsed() {
        return this.lastTimeUsed;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getTotalTimeInForeground() {
        return this.totalTimeInForeground;
    }

    public final void receive(String className, long timeStamp, int eventType) {
        this.lastTimeUsed = timeStamp;
        this.currentTiming = this.currentTiming.receive(className, timeStamp, eventType);
    }

    public final void setCurrentTiming(TimingActivity timingActivity) {
        Intrinsics.checkNotNullParameter(timingActivity, "<set-?>");
        this.currentTiming = timingActivity;
    }

    public final void setLastTimeUsed(long j) {
        this.lastTimeUsed = j;
    }

    public final void setTotalTimeInForeground(long j) {
        this.totalTimeInForeground = j;
    }

    public final TimingActivity take() {
        int i = this.index + 1;
        this.index = i;
        int i2 = i % 2;
        this.index = i2;
        return this.pool[i2];
    }
}
