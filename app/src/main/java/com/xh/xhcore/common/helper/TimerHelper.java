package com.xh.xhcore.common.helper;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\bH&J\u0006\u0010\n\u001a\u00020\u0006J\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/helper/TimerHelper;", "", "()V", "autoRefreshTimer", "Ljava/util/Timer;", "doTimerAction", "", "getTimerDelay", "", "getTimerDuration", "restart", "start", "stop", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class TimerHelper {
    private Timer autoRefreshTimer;

    public abstract void doTimerAction();

    public abstract long getTimerDelay();

    public abstract long getTimerDuration();

    public final void restart() throws NoSuchMethodError {
        stop();
        start();
    }

    public final void start() throws NoSuchMethodError {
        this.autoRefreshTimer = new Timer();
        long timerDelay = getTimerDelay();
        LogUtils.INSTANCE.d("this = " + this + " timerDelay = " + timerDelay);
        Timer timer = this.autoRefreshTimer;
        if (timer == null) {
            return;
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerHelper.this.doTimerAction();
            }
        }, timerDelay, getTimerDuration());
    }

    public final void stop() throws NoSuchMethodError {
        Timer timer = this.autoRefreshTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = this.autoRefreshTimer;
        if (timer2 != null) {
            timer2.purge();
        }
        this.autoRefreshTimer = null;
    }
}
