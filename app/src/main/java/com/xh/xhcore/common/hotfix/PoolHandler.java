package com.xh.xhcore.common.hotfix;

import android.os.Looper;
import android.os.Message;
import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xh/xhcore/common/hotfix/PoolHandler;", "Lcom/xh/xhcore/common/hotfix/TaskHandler;", "looper", "Landroid/os/Looper;", "runnable", "Ljava/lang/Runnable;", "(Landroid/os/Looper;Ljava/lang/Runnable;)V", "currentPoolCount", "", "clear", "", "getLoopIntervalMillis", "", "handleMessage", "msg", "Landroid/os/Message;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PoolHandler extends TaskHandler {
    private int currentPoolCount;

    public PoolHandler(Looper looper, Runnable runnable) {
        super(looper, runnable);
        Intrinsics.checkNotNullParameter(looper, "looper");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.currentPoolCount = 1;
    }

    @Override
    protected void clear() {
        super.clear();
        this.currentPoolCount = 1;
    }

    public abstract long getLoopIntervalMillis(int currentPoolCount);

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int i = this.currentPoolCount;
        this.currentPoolCount = i + 1;
        long loopIntervalMillis = getLoopIntervalMillis(i);
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("[handleMessage] loopIntervalMillis = ", Long.valueOf(loopIntervalMillis)));
        sendMessageDelayed(getLoopMessage(), loopIntervalMillis);
    }
}
