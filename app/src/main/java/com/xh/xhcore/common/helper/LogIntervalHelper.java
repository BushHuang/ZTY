package com.xh.xhcore.common.helper;

import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/xh/xhcore/common/helper/LogIntervalHelper;", "", "()V", "lastLogTimeMillis", "", "getLastLogTimeMillis", "()J", "setLastLogTimeMillis", "(J)V", "logDOnceInterval", "", "interval", "message", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LogIntervalHelper {
    private long lastLogTimeMillis = System.currentTimeMillis();

    public final long getLastLogTimeMillis() {
        return this.lastLogTimeMillis;
    }

    public final synchronized void logDOnceInterval(long interval, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis > this.lastLogTimeMillis + interval) {
            this.lastLogTimeMillis = jCurrentTimeMillis;
            LogUtils.INSTANCE.d(message);
        }
    }

    public final void setLastLogTimeMillis(long j) {
        this.lastLogTimeMillis = j;
    }
}
