package com.obs.services.internal;

import com.obs.services.internal.ProgressManager;
import com.obs.services.model.ProgressListener;
import java.util.Date;
import java.util.List;

public class SimpleProgressManager extends ProgressManager {
    protected long newlyTransferredBytes;
    protected long transferredBytes;

    public SimpleProgressManager(long j, long j2, ProgressListener progressListener, long j3) {
        super(j, progressListener, j3);
        this.transferredBytes = j2 < 0 ? 0L : j2;
    }

    @Override
    protected void doProgressChanged(int i) {
        long j = i;
        this.transferredBytes += j;
        this.newlyTransferredBytes += j;
        Date date = new Date();
        List<ProgressManager.BytesUnit> listCreateCurrentInstantaneousBytes = createCurrentInstantaneousBytes(j, date);
        this.lastInstantaneousBytes = listCreateCurrentInstantaneousBytes;
        if (this.newlyTransferredBytes >= this.intervalBytes) {
            if (this.transferredBytes < this.totalBytes || this.totalBytes == -1) {
                DefaultProgressStatus defaultProgressStatus = new DefaultProgressStatus(this.newlyTransferredBytes, this.transferredBytes, this.totalBytes, date.getTime() - this.lastCheckpoint.getTime(), date.getTime() - this.startCheckpoint.getTime());
                defaultProgressStatus.setInstantaneousBytes(listCreateCurrentInstantaneousBytes);
                this.progressListener.progressChanged(defaultProgressStatus);
                this.newlyTransferredBytes = 0L;
                this.lastCheckpoint = date;
            }
        }
    }

    @Override
    public void progressEnd() {
        if (this.progressListener == null) {
            return;
        }
        Date date = new Date();
        this.progressListener.progressChanged(new DefaultProgressStatus(this.newlyTransferredBytes, this.transferredBytes, this.totalBytes, date.getTime() - this.lastCheckpoint.getTime(), date.getTime() - this.startCheckpoint.getTime()));
    }
}
