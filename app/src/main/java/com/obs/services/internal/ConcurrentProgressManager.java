package com.obs.services.internal;

import com.obs.services.internal.ProgressManager;
import com.obs.services.model.ProgressListener;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentProgressManager extends ProgressManager {
    private AtomicBoolean endFlag;
    protected AtomicLong newlyTransferredBytes;
    private AtomicBoolean startFlag;
    protected AtomicLong transferredBytes;

    public ConcurrentProgressManager(long j, long j2, ProgressListener progressListener, long j3) {
        super(j, progressListener, j3);
        this.startFlag = new AtomicBoolean(false);
        this.endFlag = new AtomicBoolean(false);
        this.transferredBytes = j2 < 0 ? new AtomicLong(0L) : new AtomicLong(j2);
        this.newlyTransferredBytes = new AtomicLong(0L);
    }

    @Override
    protected void doProgressChanged(int i) {
        long j = i;
        long jAddAndGet = this.transferredBytes.addAndGet(j);
        long jAddAndGet2 = this.newlyTransferredBytes.addAndGet(j);
        Date date = new Date();
        List<ProgressManager.BytesUnit> listCreateCurrentInstantaneousBytes = createCurrentInstantaneousBytes(j, date);
        this.lastInstantaneousBytes = listCreateCurrentInstantaneousBytes;
        if (jAddAndGet2 >= this.intervalBytes) {
            if ((jAddAndGet < this.totalBytes || this.totalBytes == -1) && this.newlyTransferredBytes.compareAndSet(jAddAndGet2, -jAddAndGet2)) {
                DefaultProgressStatus defaultProgressStatus = new DefaultProgressStatus(jAddAndGet2, jAddAndGet, this.totalBytes, date.getTime() - this.lastCheckpoint.getTime(), date.getTime() - this.startCheckpoint.getTime());
                defaultProgressStatus.setInstantaneousBytes(listCreateCurrentInstantaneousBytes);
                this.progressListener.progressChanged(defaultProgressStatus);
                this.lastCheckpoint = date;
            }
        }
    }

    @Override
    public void progressEnd() {
        if (this.progressListener == null) {
            return;
        }
        synchronized (this) {
            Date date = new Date();
            this.progressListener.progressChanged(new DefaultProgressStatus(this.newlyTransferredBytes.get(), this.transferredBytes.get(), this.totalBytes, date.getTime() - this.lastCheckpoint.getTime(), date.getTime() - this.startCheckpoint.getTime()));
        }
    }

    @Override
    public void progressStart() {
        if (this.startFlag.compareAndSet(false, true)) {
            super.progressStart();
        }
    }
}
