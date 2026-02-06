package com.xh.xhcore.common.statistic.usage;

import com.xh.xhcore.common.http.strategy.LogUtils;

public class AppStatistics {
    private long appUsageDuration;

    public AppStatistics(long j, long j2) {
        long j3 = (j - j2) / 1000000;
        this.appUsageDuration = j3;
        if (j3 <= 0) {
            LogUtils.e("appUsageDuration is below zero!");
        }
    }

    public long getAppUsageDuration() {
        return this.appUsageDuration;
    }

    public AppStatistics setAppUsageDuration(long j) {
        this.appUsageDuration = j;
        return this;
    }
}
