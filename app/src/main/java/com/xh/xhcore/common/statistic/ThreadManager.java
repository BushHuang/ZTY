package com.xh.xhcore.common.statistic;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/statistic/ThreadManager;", "", "()V", "DISK_IO", "Ljava/util/concurrent/ThreadPoolExecutor;", "getDISK_IO$annotations", "getDISK_IO", "()Ljava/util/concurrent/ThreadPoolExecutor;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThreadManager {
    public static final ThreadManager INSTANCE = new ThreadManager();
    private static final ThreadPoolExecutor DISK_IO = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
        @Override
        public final Thread newThread(Runnable runnable) {
            return ThreadManager.m34DISK_IO$lambda0(runnable);
        }
    });

    private ThreadManager() {
    }

    private static final Thread m34DISK_IO$lambda0(Runnable runnable) {
        Thread thread = new Thread(runnable, "Data Collection Disk IO");
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }

    public static final ThreadPoolExecutor getDISK_IO() {
        return DISK_IO;
    }

    @JvmStatic
    public static void getDISK_IO$annotations() {
    }
}
