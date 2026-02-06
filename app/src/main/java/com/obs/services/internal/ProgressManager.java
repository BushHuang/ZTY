package com.obs.services.internal;

import com.obs.services.model.ProgressListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ProgressManager {
    protected final long intervalBytes;
    protected Date lastCheckpoint;
    protected volatile List<BytesUnit> lastInstantaneousBytes;
    protected final ProgressListener progressListener;
    protected Date startCheckpoint;
    protected final long totalBytes;

    static class BytesUnit {
        long bytes;
        Date dateTime;

        BytesUnit(Date date, long j) {
            this.dateTime = date;
            this.bytes = j;
        }
    }

    public ProgressManager(long j, ProgressListener progressListener, long j2) {
        this.totalBytes = j;
        this.progressListener = progressListener;
        Date date = new Date();
        this.startCheckpoint = date;
        this.lastCheckpoint = date;
        this.intervalBytes = j2;
    }

    protected List<BytesUnit> createCurrentInstantaneousBytes(long j, Date date) {
        ArrayList arrayList = new ArrayList();
        List<BytesUnit> list = this.lastInstantaneousBytes;
        if (list != null) {
            for (BytesUnit bytesUnit : list) {
                if (date.getTime() - bytesUnit.dateTime.getTime() < 1000) {
                    arrayList.add(bytesUnit);
                }
            }
        }
        arrayList.add(new BytesUnit(date, j));
        return arrayList;
    }

    protected abstract void doProgressChanged(int i);

    public final void progressChanged(int i) {
        if (this.progressListener == null || i <= 0) {
            return;
        }
        doProgressChanged(i);
    }

    public abstract void progressEnd();

    public void progressStart() {
        Date date = new Date();
        this.startCheckpoint = date;
        this.lastCheckpoint = date;
    }
}
