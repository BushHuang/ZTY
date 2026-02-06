package com.xh.xhcore.common.hotfix;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0004J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0016J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\fJ\u0006\u0010\u0016\u001a\u00020\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xh/xhcore/common/hotfix/TaskHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "runnable", "Ljava/lang/Runnable;", "(Landroid/os/Looper;Ljava/lang/Runnable;)V", "messageObject", "", "getMessageObject", "()Ljava/lang/Object;", "clear", "", "getLoopMessage", "Landroid/os/Message;", "kotlin.jvm.PlatformType", "handleMessage", "msg", "startDelay", "delay", "", "startNow", "stop", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class TaskHandler extends Handler {
    private final Object messageObject;
    private final Runnable runnable;

    public TaskHandler(Looper looper, Runnable runnable) {
        super(looper);
        Intrinsics.checkNotNullParameter(looper, "looper");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.runnable = runnable;
        this.messageObject = new Object();
    }

    protected void clear() {
        removeMessages(0, this.messageObject);
    }

    protected final Message getLoopMessage() {
        return Message.obtain(this, 0, this.messageObject);
    }

    public final Object getMessageObject() {
        return this.messageObject;
    }

    @Override
    public void handleMessage(Message msg) {
        LogUtils.INSTANCE.d("[run the task]");
        this.runnable.run();
    }

    public final void startDelay(long delay) {
        clear();
        sendMessageDelayed(getLoopMessage(), delay);
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("[startDelay] delay = ", Long.valueOf(delay)));
    }

    public final void startNow() {
        clear();
        sendMessage(getLoopMessage());
        LogUtils.INSTANCE.d("[startNow]");
    }

    public final void stop() {
        clear();
    }
}
