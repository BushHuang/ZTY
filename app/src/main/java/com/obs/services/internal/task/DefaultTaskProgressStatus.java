package com.obs.services.internal.task;

import com.obs.services.model.TaskProgressStatus;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultTaskProgressStatus implements TaskProgressStatus {
    private AtomicInteger execTaskNum = new AtomicInteger();
    private AtomicInteger succeedTaskNum = new AtomicInteger();
    private AtomicInteger failTaskNum = new AtomicInteger();
    private AtomicInteger totalTaskNum = new AtomicInteger();

    public void execTaskIncrement() {
        this.execTaskNum.incrementAndGet();
    }

    public void failTaskIncrement() {
        this.failTaskNum.incrementAndGet();
    }

    @Override
    public int getExecPercentage() {
        if (this.totalTaskNum.get() <= 0) {
            return -1;
        }
        return (this.execTaskNum.get() * 100) / this.totalTaskNum.get();
    }

    @Override
    public int getExecTaskNum() {
        return this.execTaskNum.get();
    }

    @Override
    public int getFailTaskNum() {
        return this.failTaskNum.get();
    }

    @Override
    public int getSucceedTaskNum() {
        return this.succeedTaskNum.get();
    }

    @Override
    public int getTotalTaskNum() {
        return this.totalTaskNum.get();
    }

    public void setTotalTaskNum(int i) {
        this.totalTaskNum.set(i);
    }

    public void succeedTaskIncrement() {
        this.succeedTaskNum.incrementAndGet();
    }
}
