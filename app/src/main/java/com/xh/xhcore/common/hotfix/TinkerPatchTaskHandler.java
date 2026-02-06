package com.xh.xhcore.common.hotfix;

import android.os.Looper;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/hotfix/TinkerPatchTaskHandler;", "Lcom/xh/xhcore/common/hotfix/PoolHandler;", "looper", "Landroid/os/Looper;", "runnable", "Ljava/lang/Runnable;", "(Landroid/os/Looper;Ljava/lang/Runnable;)V", "getLoopIntervalMillis", "", "currentPoolCount", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TinkerPatchTaskHandler extends PoolHandler {
    public TinkerPatchTaskHandler(Looper looper, Runnable runnable) {
        super(looper, runnable);
        Intrinsics.checkNotNullParameter(looper, "looper");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
    }

    @Override
    public long getLoopIntervalMillis(int currentPoolCount) {
        int i = currentPoolCount % 4;
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? TimeUnit.HOURS.toMillis(4L) : TimeUnit.HOURS.toMillis(4L) : TimeUnit.HOURS.toMillis(2L) : TimeUnit.HOURS.toMillis(1L) : TimeUnit.HOURS.toMillis(6L);
    }
}
