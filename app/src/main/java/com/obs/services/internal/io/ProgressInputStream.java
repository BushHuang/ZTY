package com.obs.services.internal.io;

import com.obs.services.internal.ProgressManager;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProgressInputStream extends FilterInputStream {
    private boolean endFlag;
    private ProgressManager progressManager;
    private boolean readFlag;

    public ProgressInputStream(InputStream inputStream, ProgressManager progressManager) {
        this(inputStream, progressManager, true);
    }

    public ProgressInputStream(InputStream inputStream, ProgressManager progressManager, boolean z) {
        super(inputStream);
        this.progressManager = progressManager;
        this.endFlag = z;
    }

    protected final void abortIfNeeded() {
        if (Thread.interrupted()) {
            throw new RuntimeException("Abort io due to thread interrupted");
        }
    }

    @Override
    public int available() throws IOException {
        abortIfNeeded();
        return super.available();
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
            abortIfNeeded();
        } finally {
            if (this.endFlag) {
                this.progressManager.progressEnd();
            }
        }
    }

    @Override
    public void mark(int i) {
        abortIfNeeded();
    }

    @Override
    public final boolean markSupported() {
        return false;
    }

    @Override
    public int read() throws IOException {
        abortIfNeeded();
        return super.read();
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        abortIfNeeded();
        if (!this.readFlag) {
            this.readFlag = true;
            this.progressManager.progressStart();
        }
        int i3 = super.read(bArr, i, i2);
        this.progressManager.progressChanged(i3);
        return i3;
    }

    @Override
    public void reset() throws IOException {
        throw new UnrecoverableIOException("UnRepeatable");
    }

    @Override
    public long skip(long j) throws IOException {
        abortIfNeeded();
        return super.skip(j);
    }
}
