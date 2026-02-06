package com.xuehai.launcher.common.plugins;

import android.util.Log;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0013H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xuehai/launcher/common/plugins/StartupTask;", "Ljava/lang/Runnable;", "taskName", "", "(Ljava/lang/String;)V", "countDownLatch", "Ljava/util/concurrent/CountDownLatch;", "getCountDownLatch", "()Ljava/util/concurrent/CountDownLatch;", "setCountDownLatch", "(Ljava/util/concurrent/CountDownLatch;)V", "debugLog", "", "getDebugLog", "()Z", "setDebugLog", "(Z)V", "isStartup", "doTask", "", "run", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class StartupTask implements Runnable {
    private CountDownLatch countDownLatch;
    private boolean debugLog;
    private boolean isStartup;
    private final String taskName;

    public StartupTask() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public StartupTask(String str) {
        this.taskName = str;
    }

    public StartupTask(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }

    public abstract void doTask();

    public final CountDownLatch getCountDownLatch() {
        return this.countDownLatch;
    }

    public final boolean getDebugLog() {
        return this.debugLog;
    }

    @Override
    public void run() {
        boolean z;
        CountDownLatch countDownLatch;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
                doTask();
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
                CountDownLatch countDownLatch2 = this.countDownLatch;
                if (countDownLatch2 != null) {
                }
            }
            this.isStartup = z;
            if (this.debugLog) {
                StringBuilder sb = new StringBuilder();
                String strValueOf = this.taskName;
                if (strValueOf == null) {
                    strValueOf = String.valueOf(hashCode());
                }
                sb.append(strValueOf);
                sb.append(" >> Startup : ");
                sb.append(this.isStartup);
                sb.append(" >> ");
                sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
                sb.append("ms");
                String string = sb.toString();
                if (this.isStartup) {
                    Log.i("StartupTask", string);
                } else {
                    Log.w("StartupTask", string);
                }
            }
        } finally {
            countDownLatch = this.countDownLatch;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    public final void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public final void setDebugLog(boolean z) {
        this.debugLog = z;
    }
}
