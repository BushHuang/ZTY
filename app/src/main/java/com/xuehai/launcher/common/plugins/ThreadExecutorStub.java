package com.xuehai.launcher.common.plugins;

import com.xuehai.launcher.common.plugins.ThreadExecutorStub$coroutineDispatcher$2;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/xuehai/launcher/common/plugins/ThreadExecutorStub;", "", "executorService", "Ljava/util/concurrent/ThreadPoolExecutor;", "(Ljava/util/concurrent/ThreadPoolExecutor;)V", "coroutineDispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getCoroutineDispatcher", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "coroutineDispatcher$delegate", "Lkotlin/Lazy;", "getExecutorService", "()Ljava/util/concurrent/ThreadPoolExecutor;", "rxScheduler", "Lio/reactivex/Scheduler;", "getRxScheduler", "()Lio/reactivex/Scheduler;", "rxScheduler$delegate", "execute", "", "runnable", "Ljava/lang/Runnable;", "isOnThread", "", "printThreadInfo", "tag", "", "remove", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThreadExecutorStub {

    private final Lazy coroutineDispatcher;
    private final ThreadPoolExecutor executorService;

    private final Lazy rxScheduler;

    public ThreadExecutorStub(ThreadPoolExecutor threadPoolExecutor) {
        Intrinsics.checkNotNullParameter(threadPoolExecutor, "executorService");
        this.executorService = threadPoolExecutor;
        this.rxScheduler = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<Scheduler>() {
            {
                super(0);
            }

            @Override
            public final Scheduler invoke() {
                return Schedulers.from(this.this$0.getExecutorService());
            }
        });
        this.coroutineDispatcher = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ThreadExecutorStub$coroutineDispatcher$2.AnonymousClass1>() {
            {
                super(0);
            }

            @Override
            public final AnonymousClass1 invoke() {
                final ThreadExecutorStub threadExecutorStub = this.this$0;
                return new ExecutorCoroutineDispatcher() {
                    @Override
                    public void close() {
                    }

                    @Override
                    public void mo1709dispatch(CoroutineContext context, Runnable block) {
                        Intrinsics.checkNotNullParameter(context, "context");
                        Intrinsics.checkNotNullParameter(block, "block");
                        threadExecutorStub.getExecutorService().execute(block);
                    }

                    @Override
                    public Executor getExecutor() {
                        return threadExecutorStub.getExecutorService();
                    }

                    @Override
                    public String toString() {
                        String string = threadExecutorStub.getExecutorService().toString();
                        Intrinsics.checkNotNullExpressionValue(string, "executorService.toString()");
                        return string;
                    }
                };
            }
        });
    }

    public final void execute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        remove(runnable);
        this.executorService.execute(runnable);
    }

    public final ExecutorCoroutineDispatcher getCoroutineDispatcher() {
        return (ExecutorCoroutineDispatcher) this.coroutineDispatcher.getValue();
    }

    public final ThreadPoolExecutor getExecutorService() {
        return this.executorService;
    }

    public final Scheduler getRxScheduler() {
        return (Scheduler) this.rxScheduler.getValue();
    }

    public final boolean isOnThread() {
        ThreadFactory threadFactory = this.executorService.getThreadFactory();
        if (threadFactory instanceof DefaultThreadFactory) {
            return Intrinsics.areEqual(Thread.currentThread().getThreadGroup().getName(), ((DefaultThreadFactory) threadFactory).getName());
        }
        return false;
    }

    public final void printThreadInfo(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
    }

    public final void remove(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.executorService.remove(runnable);
    }
}
