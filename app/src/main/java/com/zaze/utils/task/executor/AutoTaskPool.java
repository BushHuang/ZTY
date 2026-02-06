package com.zaze.utils.task.executor;

import com.zaze.utils.log.ZLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AutoTaskPool extends FilterTaskPool {
    private boolean isRunning;

    private AutoTaskPool(TaskPool taskPool) {
        super(taskPool);
        this.isRunning = false;
        this.executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "AutoTaskPool");
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                return thread;
            }
        });
    }

    public static AutoTaskPool newInstance(TaskPool taskPool) {
        return new AutoTaskPool(taskPool);
    }

    @Override
    public boolean executeTask() {
        if (this.isRunning) {
            return true;
        }
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (TaskPool.needLog) {
                    ZLog.i("Task[ZZ]", "AutoTaskPool start", new Object[0]);
                }
                AutoTaskPool.this.isRunning = true;
                while (!AutoTaskPool.this.taskPool.isEmpty()) {
                    AutoTaskPool.super.executeTask();
                }
                AutoTaskPool.this.isRunning = false;
                if (TaskPool.needLog) {
                    ZLog.d("Task[ZZ]", "AutoTaskPool end", new Object[0]);
                }
            }
        });
        return true;
    }
}
