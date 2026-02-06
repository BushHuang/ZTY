package com.xuehai.launcher.common.plugins;

import android.util.Log;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/xuehai/launcher/common/plugins/DefaultThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "name", "", "(Ljava/lang/String;)V", "group", "Ljava/lang/ThreadGroup;", "getName", "()Ljava/lang/String;", "newThread", "Ljava/lang/Thread;", "r", "Ljava/lang/Runnable;", "DefaultPoolExecutor", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultThreadFactory implements ThreadFactory {
    private ThreadGroup group;
    private final String name;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB=\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/xuehai/launcher/common/plugins/DefaultThreadFactory$DefaultPoolExecutor;", "Ljava/util/concurrent/ThreadPoolExecutor;", "corePoolSize", "", "maximumPoolSize", "keepAliveTime", "", "unit", "Ljava/util/concurrent/TimeUnit;", "workQueue", "Ljava/util/concurrent/BlockingQueue;", "Ljava/lang/Runnable;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "(IIJLjava/util/concurrent/TimeUnit;Ljava/util/concurrent/BlockingQueue;Ljava/util/concurrent/ThreadFactory;)V", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultPoolExecutor extends ThreadPoolExecutor {
        private static final int CPU_COUNT;

        public static final Companion INSTANCE = new Companion(null);
        private static final int INIT_THREAD_COUNT;
        private static DefaultPoolExecutor INSTANCE = null;
        private static final int MAX_THREAD_COUNT;
        private static final long SURPLUS_THREAD_LIFE = 30;

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/plugins/DefaultThreadFactory$DefaultPoolExecutor$Companion;", "", "()V", "CPU_COUNT", "", "INIT_THREAD_COUNT", "INSTANCE", "Lcom/xuehai/launcher/common/plugins/DefaultThreadFactory$DefaultPoolExecutor;", "MAX_THREAD_COUNT", "SURPLUS_THREAD_LIFE", "", "getInstance", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final DefaultPoolExecutor getInstance() {
                DefaultPoolExecutor defaultPoolExecutor = DefaultPoolExecutor.INSTANCE;
                if (defaultPoolExecutor != null) {
                    return defaultPoolExecutor;
                }
                DefaultPoolExecutor defaultPoolExecutor2 = new DefaultPoolExecutor(DefaultPoolExecutor.INIT_THREAD_COUNT, DefaultPoolExecutor.MAX_THREAD_COUNT, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue(64), new DefaultThreadFactory(null, 1, 0 == true ? 1 : 0), 0 == true ? 1 : 0);
                Companion companion = DefaultPoolExecutor.INSTANCE;
                DefaultPoolExecutor.INSTANCE = defaultPoolExecutor2;
                return defaultPoolExecutor2;
            }
        }

        static {
            int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
            CPU_COUNT = iAvailableProcessors;
            int i = iAvailableProcessors + 1;
            INIT_THREAD_COUNT = i;
            MAX_THREAD_COUNT = i;
        }

        private DefaultPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
            super(i, i2, j, timeUnit, blockingQueue, threadFactory, new RejectedExecutionHandler() {
                @Override
                public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                    Log.e("", "Task rejected, too many task!");
                }
            });
        }

        public DefaultPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue blockingQueue, ThreadFactory threadFactory, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, j, timeUnit, blockingQueue, threadFactory);
        }

        @JvmStatic
        public static final DefaultPoolExecutor getInstance() {
            return INSTANCE.getInstance();
        }
    }

    public DefaultThreadFactory() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DefaultThreadFactory(String str) {
        this.name = str;
        SecurityManager securityManager = System.getSecurityManager();
        ThreadGroup threadGroup = securityManager != null ? securityManager.getThreadGroup() : null;
        if (threadGroup == null) {
            threadGroup = Thread.currentThread().getThreadGroup();
            Intrinsics.checkNotNullExpressionValue(threadGroup, "currentThread().threadGroup");
        }
        this.group = threadGroup;
    }

    public DefaultThreadFactory(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "DefaultFactory" : str);
    }

    public final String getName() {
        return this.name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(this.group, r, this.name, 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
