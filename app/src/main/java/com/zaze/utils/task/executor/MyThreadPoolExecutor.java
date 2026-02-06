package com.zaze.utils.task.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    private int executeNum;

    public MyThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i, i2, j, timeUnit, blockingQueue);
        this.executeNum = 0;
    }

    public MyThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, blockingQueue, rejectedExecutionHandler);
        this.executeNum = 0;
    }

    public MyThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory);
        this.executeNum = 0;
    }

    public MyThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
        this.executeNum = 0;
    }

    @Override
    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        this.executeNum--;
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        this.executeNum++;
    }

    public int getExecuteNum() {
        return this.executeNum;
    }

    @Override
    public void setCorePoolSize(int i) {
        super.setCorePoolSize(i);
    }

    public void setExecuteNum(int i) {
        this.executeNum = i;
    }

    @Override
    public void setMaximumPoolSize(int i) {
        super.setMaximumPoolSize(i);
    }
}
