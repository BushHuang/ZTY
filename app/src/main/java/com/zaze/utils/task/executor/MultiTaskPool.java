package com.zaze.utils.task.executor;

import com.zaze.utils.log.ZLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MultiTaskPool extends FilterTaskPool {
    private static final int DEFAULT = 2;
    private static final long KEEP_ALIVE_TIME = 60;
    private static final int MAX = 6;
    private static final int MIN = 1;
    private MyThreadPoolExecutor multiExecutor;
    private int multiNum;
    private int notifyCount;

    private MultiTaskPool(TaskPool taskPool) {
        super(taskPool);
        this.notifyCount = 1;
        this.multiNum = 2;
        MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor(1, 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "MultiTaskPool");
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                return thread;
            }
        });
        this.multiExecutor = myThreadPoolExecutor;
        myThreadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    private void execute(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.multiExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (MultiTaskPool.this.isStop) {
                        return;
                    }
                    if (TaskPool.needLog) {
                        ZLog.i("Task[ZZ]", "MultiTaskPool", new Object[0]);
                    }
                    MultiTaskPool.super.executeTask();
                }
            });
        }
    }

    public static MultiTaskPool newInstance(TaskPool taskPool) {
        return new MultiTaskPool(taskPool);
    }

    private int transNum(int i) {
        if (i == -2) {
            return -2;
        }
        if (i == -1) {
            return 2;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        int i2 = 3;
        if (i != 3) {
            i2 = 4;
            if (i != 4) {
                i2 = 5;
                if (i != 5) {
                    return 6;
                }
            }
        }
        return i2;
    }

    @Override
    public boolean executeTask() {
        execute(this.multiNum);
        return true;
    }

    public void notifyExecute() {
        execute(this.notifyCount);
    }

    public void setMultiNum(int i) {
        int iTransNum = transNum(i);
        if (iTransNum != -2) {
            this.multiNum = iTransNum;
        }
        this.multiExecutor.setMaximumPoolSize(this.multiNum);
    }

    public void setNotifyCount(int i) {
        int iTransNum = transNum(i);
        if (iTransNum != -2) {
            this.notifyCount = iTransNum;
        }
    }
}
