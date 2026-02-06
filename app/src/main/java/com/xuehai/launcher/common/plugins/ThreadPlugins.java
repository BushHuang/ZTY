package com.xuehai.launcher.common.plugins;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import io.reactivex.Scheduler;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0007J\b\u0010+\u001a\u00020*H\u0007J\b\u0010,\u001a\u00020*H\u0007J\b\u0010-\u001a\u00020*H\u0007J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0007J\u0010\u00102\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0007J\u0010\u00103\u001a\u00020/2\u0006\u00104\u001a\u00020\u0004H\u0007J\b\u00105\u001a\u00020*H\u0007J\u0010\u00106\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0007J\u0010\u00107\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0007J\u0010\u00108\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0007J\u0010\u00109\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0007J\u001a\u0010:\u001a\u00020/2\u0006\u00100\u001a\u0002012\b\b\u0002\u0010;\u001a\u00020<H\u0007J\u001a\u0010=\u001a\u00020/2\u0006\u00100\u001a\u0002012\b\b\u0002\u0010;\u001a\u00020<H\u0007J\u001a\u0010>\u001a\u00020/2\u0006\u00104\u001a\u00020\u00042\b\b\u0002\u0010;\u001a\u00020<H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0014\u0010\bR\u001b\u0010\u0016\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0017\u0010\bR\u001b\u0010\u0019\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\n\u001a\u0004\b\u001a\u0010\u0011R\u001b\u0010\u001c\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001d\u0010\bR\u001b\u0010\u001f\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\n\u001a\u0004\b \u0010\bR\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010$\u001a\u00020#8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b%\u0010\u0002R\u0018\u0010&\u001a\u00020'8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b(\u0010\u0002¨\u0006?"}, d2 = {"Lcom/xuehai/launcher/common/plugins/ThreadPlugins;", "", "()V", "CPU_COUNT", "", "fileExecutorStub", "Lcom/xuehai/launcher/common/plugins/ThreadExecutorStub;", "getFileExecutorStub", "()Lcom/xuehai/launcher/common/plugins/ThreadExecutorStub;", "fileExecutorStub$delegate", "Lkotlin/Lazy;", "installExecutorStub", "getInstallExecutorStub", "installExecutorStub$delegate", "ioDispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getIoDispatcher", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "ioDispatcher$delegate", "ioExecutorStub", "getIoExecutorStub", "ioExecutorStub$delegate", "logExecutorStub", "getLogExecutorStub", "logExecutorStub$delegate", "multiDispatcher", "getMultiDispatcher", "multiDispatcher$delegate", "multiExecutorStub", "getMultiExecutorStub", "multiExecutorStub$delegate", "requestExecutorStub", "getRequestExecutorStub", "requestExecutorStub$delegate", "uiHandler", "Landroid/os/Handler;", "workHandler", "getWorkHandler$annotations", "workThread", "Landroid/os/HandlerThread;", "getWorkThread$annotations", "fileScheduler", "Lio/reactivex/Scheduler;", "installScheduler", "ioScheduler", "multiScheduler", "removeUICallbacks", "", "runnable", "Ljava/lang/Runnable;", "removeWorkCallbacks", "removeWorkMessages", "what", "requestScheduler", "runInFileThread", "runInInstallThread", "runInIoThread", "runInLogThread", "runInUIThread", "delay", "", "runInWorkThread", "sendWorkMessage", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThreadPlugins {
    private static volatile Handler workHandler;
    private static volatile HandlerThread workThread;
    public static final ThreadPlugins INSTANCE = new ThreadPlugins();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final Lazy requestExecutorStub = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ThreadExecutorStub>() {
        @Override
        public final ThreadExecutorStub invoke() {
            return new ThreadExecutorStub(new ThreadPoolExecutor(0, ThreadPlugins.CPU_COUNT * 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("launcher_request")));
        }
    });

    private static final Lazy multiExecutorStub = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ThreadExecutorStub>() {
        @Override
        public final ThreadExecutorStub invoke() {
            return new ThreadExecutorStub(new ThreadPoolExecutor(0, ThreadPlugins.CPU_COUNT * 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("launcher_multi")));
        }
    });

    private static final Lazy multiDispatcher = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ExecutorCoroutineDispatcher>() {
        @Override
        public final ExecutorCoroutineDispatcher invoke() {
            return ThreadPlugins.INSTANCE.getMultiExecutorStub().getCoroutineDispatcher();
        }
    });

    private static final Lazy fileExecutorStub = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ThreadExecutorStub>() {
        @Override
        public final ThreadExecutorStub invoke() {
            return new ThreadExecutorStub(new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("launcher_file")));
        }
    });

    private static final Lazy ioExecutorStub = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ThreadExecutorStub>() {
        @Override
        public final ThreadExecutorStub invoke() {
            return new ThreadExecutorStub(new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("launcher_io")));
        }
    });

    private static final Lazy ioDispatcher = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ExecutorCoroutineDispatcher>() {
        @Override
        public final ExecutorCoroutineDispatcher invoke() {
            return ThreadPlugins.INSTANCE.getIoExecutorStub().getCoroutineDispatcher();
        }
    });

    private static final Lazy logExecutorStub = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ThreadExecutorStub>() {
        @Override
        public final ThreadExecutorStub invoke() {
            return new ThreadExecutorStub(new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("launcher_log")));
        }
    });

    private static final Lazy installExecutorStub = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ThreadExecutorStub>() {
        @Override
        public final ThreadExecutorStub invoke() {
            return new ThreadExecutorStub(new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("launcher_install")));
        }
    });
    private static final Handler uiHandler = new Handler(Looper.getMainLooper());

    static {
        HandlerThread handlerThread = new HandlerThread("launcher_work_thread");
        handlerThread.start();
        workThread = handlerThread;
        workHandler = new Handler(workThread.getLooper());
    }

    private ThreadPlugins() {
    }

    @JvmStatic
    public static final Scheduler fileScheduler() {
        Scheduler rxScheduler = INSTANCE.getFileExecutorStub().getRxScheduler();
        Intrinsics.checkNotNullExpressionValue(rxScheduler, "fileExecutorStub.rxScheduler");
        return rxScheduler;
    }

    private final ThreadExecutorStub getIoExecutorStub() {
        return (ThreadExecutorStub) ioExecutorStub.getValue();
    }

    private final ThreadExecutorStub getLogExecutorStub() {
        return (ThreadExecutorStub) logExecutorStub.getValue();
    }

    private final ThreadExecutorStub getRequestExecutorStub() {
        return (ThreadExecutorStub) requestExecutorStub.getValue();
    }

    @JvmStatic
    private static void getWorkHandler$annotations() {
    }

    @JvmStatic
    private static void getWorkThread$annotations() {
    }

    @JvmStatic
    public static final Scheduler installScheduler() {
        Scheduler rxScheduler = INSTANCE.getInstallExecutorStub().getRxScheduler();
        Intrinsics.checkNotNullExpressionValue(rxScheduler, "installExecutorStub.rxScheduler");
        return rxScheduler;
    }

    @JvmStatic
    public static final Scheduler ioScheduler() {
        Scheduler rxScheduler = INSTANCE.getIoExecutorStub().getRxScheduler();
        Intrinsics.checkNotNullExpressionValue(rxScheduler, "ioExecutorStub.rxScheduler");
        return rxScheduler;
    }

    @JvmStatic
    public static final Scheduler multiScheduler() {
        Scheduler rxScheduler = INSTANCE.getMultiExecutorStub().getRxScheduler();
        Intrinsics.checkNotNullExpressionValue(rxScheduler, "multiExecutorStub.rxScheduler");
        return rxScheduler;
    }

    @JvmStatic
    public static final void removeUICallbacks(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        uiHandler.removeCallbacks(runnable);
    }

    @JvmStatic
    public static final void removeWorkCallbacks(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        workHandler.removeCallbacks(runnable);
    }

    @JvmStatic
    public static final void removeWorkMessages(int what) {
        workHandler.removeMessages(what);
    }

    @JvmStatic
    public static final Scheduler requestScheduler() {
        Scheduler rxScheduler = INSTANCE.getRequestExecutorStub().getRxScheduler();
        Intrinsics.checkNotNullExpressionValue(rxScheduler, "requestExecutorStub.rxScheduler");
        return rxScheduler;
    }

    @JvmStatic
    public static final void runInFileThread(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        INSTANCE.getFileExecutorStub().execute(runnable);
    }

    @JvmStatic
    public static final void runInInstallThread(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        INSTANCE.getInstallExecutorStub().execute(runnable);
    }

    @JvmStatic
    public static final void runInIoThread(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        INSTANCE.getIoExecutorStub().execute(runnable);
    }

    @JvmStatic
    public static final void runInLogThread(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        INSTANCE.getLogExecutorStub().execute(runnable);
    }

    @JvmStatic
    public static final void runInUIThread(Runnable runnable, long delay) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        uiHandler.removeCallbacks(runnable);
        uiHandler.postDelayed(runnable, delay);
    }

    public static void runInUIThread$default(Runnable runnable, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        runInUIThread(runnable, j);
    }

    @JvmStatic
    public static final void runInWorkThread(Runnable runnable, long delay) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        workHandler.removeCallbacks(runnable);
        workHandler.postDelayed(runnable, delay);
    }

    public static void runInWorkThread$default(Runnable runnable, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        runInWorkThread(runnable, j);
    }

    @JvmStatic
    public static final void sendWorkMessage(int what, long delay) {
        removeWorkMessages(what);
        workHandler.sendEmptyMessageDelayed(what, delay);
    }

    public static void sendWorkMessage$default(int i, long j, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j = 0;
        }
        sendWorkMessage(i, j);
    }

    public final ThreadExecutorStub getFileExecutorStub() {
        return (ThreadExecutorStub) fileExecutorStub.getValue();
    }

    public final ThreadExecutorStub getInstallExecutorStub() {
        return (ThreadExecutorStub) installExecutorStub.getValue();
    }

    public final ExecutorCoroutineDispatcher getIoDispatcher() {
        return (ExecutorCoroutineDispatcher) ioDispatcher.getValue();
    }

    public final ExecutorCoroutineDispatcher getMultiDispatcher() {
        return (ExecutorCoroutineDispatcher) multiDispatcher.getValue();
    }

    public final ThreadExecutorStub getMultiExecutorStub() {
        return (ThreadExecutorStub) multiExecutorStub.getValue();
    }
}
