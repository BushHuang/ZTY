package com.obs.services.internal.task;

import com.obs.services.model.ProgressStatus;
import com.obs.services.model.UploadProgressStatus;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UploadTaskProgressStatus implements UploadProgressStatus {
    private final long progressInterval;
    private final Date startDate;
    private ConcurrentHashMap<String, ProgressStatus> taskTable = new ConcurrentHashMap<>();
    private AtomicLong totalSize = new AtomicLong();
    private AtomicLong totalMilliseconds = new AtomicLong();
    private AtomicLong endingTaskSize = new AtomicLong();
    private AtomicInteger execTaskNum = new AtomicInteger();
    private AtomicInteger succeedTaskNum = new AtomicInteger();
    private AtomicInteger failTaskNum = new AtomicInteger();
    private AtomicInteger totalTaskNum = new AtomicInteger();
    private AtomicLong taskTagSize = new AtomicLong();

    public UploadTaskProgressStatus(long j, Date date) {
        this.progressInterval = j;
        this.startDate = date;
    }

    public void addEndingTaskSize(long j) {
        this.endingTaskSize.addAndGet(j);
    }

    public void addTotalSize(long j) {
        this.totalSize.addAndGet(j);
    }

    public void execTaskIncrement() {
        this.execTaskNum.incrementAndGet();
    }

    public void failTaskIncrement() {
        this.failTaskNum.incrementAndGet();
    }

    @Override
    public double getAverageSpeed() {
        if (this.totalMilliseconds.get() <= 0) {
            return -1.0d;
        }
        double transferredSize = getTransferredSize();
        Double.isNaN(transferredSize);
        double d = this.totalMilliseconds.get();
        Double.isNaN(d);
        return (transferredSize * 1000.0d) / d;
    }

    public long getEndingTaskSize() {
        return this.endingTaskSize.get();
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
    public double getInstantaneousSpeed() {
        ConcurrentHashMap<String, ProgressStatus> concurrentHashMap = this.taskTable;
        if (concurrentHashMap == null) {
            return -1.0d;
        }
        long j = 0;
        Iterator<Map.Entry<String, ProgressStatus>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            double d = j;
            double instantaneousSpeed = it.next().getValue().getInstantaneousSpeed();
            Double.isNaN(d);
            j = (long) (d + instantaneousSpeed);
        }
        return j;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public int getSucceedTaskNum() {
        return this.succeedTaskNum.get();
    }

    @Override
    public ProgressStatus getTaskStatus(String str) {
        return this.taskTable.get(str);
    }

    @Override
    public ConcurrentHashMap<String, ProgressStatus> getTaskTable() {
        return new ConcurrentHashMap<>(this.taskTable);
    }

    public long getTaskTagSize() {
        return this.taskTagSize.get();
    }

    public long getTotalMilliseconds() {
        return this.totalMilliseconds.get();
    }

    @Override
    public long getTotalSize() {
        if (getTotalTaskNum() <= 0) {
            return -1L;
        }
        return this.totalSize.get();
    }

    @Override
    public int getTotalTaskNum() {
        return this.totalTaskNum.get();
    }

    @Override
    public long getTransferredSize() {
        long transferredBytes = this.endingTaskSize.get();
        Iterator it = new ConcurrentHashMap(this.taskTable).entrySet().iterator();
        while (it.hasNext()) {
            transferredBytes += ((ProgressStatus) ((Map.Entry) it.next()).getValue()).getTransferredBytes();
        }
        return transferredBytes;
    }

    public boolean isRefreshprogress() {
        if (this.progressInterval <= 0) {
            return false;
        }
        long transferredSize = getTransferredSize();
        long taskTagSize = getTaskTagSize();
        System.out.println("[UploadTaskProgressStatus]transferredSize:" + transferredSize + ", taskTagSize:" + taskTagSize + ", progressInterval" + this.progressInterval);
        if (transferredSize - taskTagSize < this.progressInterval) {
            return false;
        }
        setTaskTagSize(transferredSize);
        return true;
    }

    public void putTaskTable(String str, ProgressStatus progressStatus) {
        this.taskTable.put(str, progressStatus);
    }

    public void removeTaskTable(String str) {
        ConcurrentHashMap<String, ProgressStatus> concurrentHashMap = this.taskTable;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void setEndingTaskSize(long j) {
        this.endingTaskSize = new AtomicLong(j);
    }

    public void setTaskTable(ConcurrentHashMap<String, ProgressStatus> concurrentHashMap) {
        this.taskTable = concurrentHashMap;
    }

    public void setTaskTagSize(long j) {
        this.taskTagSize = new AtomicLong(j);
    }

    public void setTotalMilliseconds(long j) {
        this.totalMilliseconds = new AtomicLong(j);
    }

    public void setTotalSize(long j) {
        this.totalSize = new AtomicLong(j);
    }

    public void setTotalTaskNum(int i) {
        this.totalTaskNum.set(i);
    }

    public void succeedTaskIncrement() {
        this.succeedTaskNum.incrementAndGet();
    }
}
