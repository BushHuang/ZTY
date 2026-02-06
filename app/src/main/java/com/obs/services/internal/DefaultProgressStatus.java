package com.obs.services.internal;

import com.obs.services.internal.ProgressManager;
import com.obs.services.model.ProgressStatus;
import java.util.Iterator;
import java.util.List;

public class DefaultProgressStatus implements ProgressStatus {
    private List<ProgressManager.BytesUnit> instantaneousBytes;
    private final long intervalMilliseconds;
    private final long newlyTransferredBytes;
    private final long totalBytes;
    private final long totalMilliseconds;
    private final long transferredBytes;

    public DefaultProgressStatus(long j, long j2, long j3, long j4, long j5) {
        this.newlyTransferredBytes = j;
        this.transferredBytes = j2;
        this.totalBytes = j3;
        this.intervalMilliseconds = j4;
        this.totalMilliseconds = j5;
    }

    @Override
    public double getAverageSpeed() {
        long j = this.totalMilliseconds;
        if (j <= 0) {
            return -1.0d;
        }
        double d = this.transferredBytes;
        Double.isNaN(d);
        double d2 = j;
        Double.isNaN(d2);
        return (d * 1000.0d) / d2;
    }

    @Override
    public double getInstantaneousSpeed() {
        List<ProgressManager.BytesUnit> list = this.instantaneousBytes;
        long j = 0;
        if (list != null) {
            Iterator<ProgressManager.BytesUnit> it = list.iterator();
            while (it.hasNext()) {
                j += it.next().bytes;
            }
            return j;
        }
        long j2 = this.intervalMilliseconds;
        if (j2 <= 0) {
            return -1.0d;
        }
        double d = this.newlyTransferredBytes;
        Double.isNaN(d);
        double d2 = j2;
        Double.isNaN(d2);
        return (d * 1000.0d) / d2;
    }

    @Override
    public long getNewlyTransferredBytes() {
        return this.newlyTransferredBytes;
    }

    @Override
    public long getTotalBytes() {
        return this.totalBytes;
    }

    @Override
    public int getTransferPercentage() {
        long j = this.totalBytes;
        if (j < 0) {
            return -1;
        }
        if (j == 0) {
            return 100;
        }
        return (int) ((this.transferredBytes * 100) / j);
    }

    @Override
    public long getTransferredBytes() {
        return this.transferredBytes;
    }

    public void setInstantaneousBytes(List<ProgressManager.BytesUnit> list) {
        this.instantaneousBytes = list;
    }
}
