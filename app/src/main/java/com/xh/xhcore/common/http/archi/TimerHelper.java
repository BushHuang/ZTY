package com.xh.xhcore.common.http.archi;

import java.util.Timer;
import java.util.TimerTask;

@Deprecated
public class TimerHelper {
    private Timer autoRefreshTimer = null;
    private Long mTimeDelay;
    private Long mTimerDuration;
    private Runnable runnable;

    public TimerHelper(Long l, Long l2, Runnable runnable) {
        this.mTimeDelay = l;
        this.mTimerDuration = l2;
        this.runnable = runnable;
    }

    public void start() {
        Timer timer = new Timer();
        this.autoRefreshTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerHelper.this.runnable.run();
            }
        }, this.mTimeDelay.longValue(), this.mTimerDuration.longValue());
    }

    public void stop() {
        this.autoRefreshTimer.cancel();
        this.autoRefreshTimer.purge();
        this.autoRefreshTimer = null;
    }
}
