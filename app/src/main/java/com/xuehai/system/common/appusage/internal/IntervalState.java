package com.xuehai.system.common.appusage.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\bH\u0002J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/xuehai/system/common/appusage/internal/IntervalState;", "", "beginTime", "", "endTime", "(JJ)V", "packageStats", "", "", "Lcom/xuehai/system/common/appusage/internal/UsageStats;", "getPackageStats", "()Ljava/util/Map;", "getOrCreateUsageStats", "packageName", "update", "", "className", "timeStamp", "eventType", "", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IntervalState {
    private final long beginTime;
    private final long endTime;
    private final Map<String, UsageStats> packageStats = new LinkedHashMap();

    public IntervalState(long j, long j2) {
        this.beginTime = j;
        this.endTime = j2;
    }

    private final UsageStats getOrCreateUsageStats(String packageName) {
        UsageStats usageStats = this.packageStats.get(packageName);
        if (usageStats != null) {
            return usageStats;
        }
        UsageStats usageStats2 = new UsageStats(packageName, this.beginTime, this.endTime, null, 8, null);
        this.packageStats.put(usageStats2.getPackageName(), usageStats2);
        return usageStats2;
    }

    public final Map<String, UsageStats> getPackageStats() {
        return this.packageStats;
    }

    public final void update(String packageName, String className, long timeStamp, int eventType) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (eventType == 1) {
            for (Map.Entry<String, UsageStats> entry : this.packageStats.entrySet()) {
                if (!Intrinsics.areEqual(entry.getKey(), packageName)) {
                    entry.getValue().receive(entry.getValue().getCurrentTiming().getClassName(), timeStamp, 2);
                }
            }
        } else if (eventType == 15 || eventType == 16) {
            Iterator<Map.Entry<String, UsageStats>> it = this.packageStats.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().receive("", timeStamp, eventType);
            }
        }
        if (eventType == 1 || eventType == 2 || eventType == 3 || eventType == 4 || eventType == 23) {
            getOrCreateUsageStats(packageName).receive(className, timeStamp, eventType);
        }
    }
}
