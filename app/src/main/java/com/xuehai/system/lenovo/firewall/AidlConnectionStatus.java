package com.xuehai.system.lenovo.firewall;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0011R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/xuehai/system/lenovo/firewall/AidlConnectionStatus;", "", "timeOutTime", "", "(J)V", "mCountDownLatch", "Ljava/util/concurrent/CountDownLatch;", "mCurrentStatus", "", "getMCurrentStatus", "()I", "setMCurrentStatus", "(I)V", "mHandler", "Landroid/os/Handler;", "mRunnable", "Lkotlin/Function0;", "", "connectionStart", "connectionSuccess", "connectionTimeout", "isConnecting", "", "monitorLatelyFinish", "Companion", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AidlConnectionStatus {
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_CONNECTION_FAIL = 3;
    public static final int STATUS_CONNECTION_SUCCESS = 2;
    public static final int STATUS_UNKNOWN = 0;
    private CountDownLatch mCountDownLatch;
    private int mCurrentStatus;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Function0<Unit> mRunnable = new Function0<Unit>() {
        {
            super(0);
        }

        @Override
        public Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        public final void invoke2() {
            this.this$0.connectionTimeout();
        }
    };
    private final long timeOutTime;

    public AidlConnectionStatus(long j) {
        this.timeOutTime = j;
    }

    private static final void m213connectionStart$lambda0(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "$tmp0");
        function0.invoke();
    }

    private static final void m214connectionSuccess$lambda1(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "$tmp0");
        function0.invoke();
    }

    private final void connectionTimeout() {
        this.mCurrentStatus = 3;
        CountDownLatch countDownLatch = this.mCountDownLatch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public final void connectionStart() {
        Handler handler = this.mHandler;
        final Function0<Unit> function0 = this.mRunnable;
        handler.postDelayed(new Runnable() {
            @Override
            public final void run() {
                AidlConnectionStatus.m213connectionStart$lambda0(function0);
            }
        }, this.timeOutTime);
        this.mCurrentStatus = 1;
    }

    public final void connectionSuccess() {
        Handler handler = this.mHandler;
        final Function0<Unit> function0 = this.mRunnable;
        handler.removeCallbacks(new Runnable() {
            @Override
            public final void run() {
                AidlConnectionStatus.m214connectionSuccess$lambda1(function0);
            }
        });
        this.mCurrentStatus = 2;
        CountDownLatch countDownLatch = this.mCountDownLatch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public final int getMCurrentStatus() {
        return this.mCurrentStatus;
    }

    public final boolean isConnecting() {
        return this.mCurrentStatus == 1;
    }

    public final void monitorLatelyFinish() throws InterruptedException {
        if (!Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread()) && isConnecting()) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.mCountDownLatch = countDownLatch;
            if (countDownLatch != null) {
                countDownLatch.await(this.timeOutTime, TimeUnit.MILLISECONDS);
            }
            this.mCountDownLatch = null;
        }
    }

    public final void setMCurrentStatus(int i) {
        this.mCurrentStatus = i;
    }
}
