package com.zaze.utils.task.executor;

import com.zaze.utils.log.ZLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncTaskPool extends FilterTaskPool {
    private AsyncTaskPool(TaskPool taskPool) {
        super(taskPool);
        this.executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "AsyncTaskPool");
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                return thread;
            }
        });
    }

    public static AsyncTaskPool newInstance(TaskPool taskPool) {
        return new AsyncTaskPool(taskPool);
    }

    @Override
    public boolean executeTask() {
        if (isEmpty()) {
            return false;
        }
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (TaskPool.needLog) {
                    ZLog.i("Task[ZZ]", "AsyncTaskPool", new Object[0]);
                }
                if (AsyncTaskPool.this.isStop) {
                    return;
                }
                AsyncTaskPool.super.executeTask();
            }
        });
        return true;
    }
}
