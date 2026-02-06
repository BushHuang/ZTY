package com.obs.services.internal.io;

import com.obs.services.internal.ServiceException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public class MayRepeatableInputStream extends FilterInputStream {
    private FileChannel fileChannel;
    private long markPos;
    private InputStream originInputStream;

    private static class SdkBufferedInputStream extends BufferedInputStream {
        public SdkBufferedInputStream(InputStream inputStream, int i) {
            super(inputStream, i);
        }

        public void tearDown() {
            this.count = 0;
            this.markpos = -1;
            this.marklimit = 0;
            this.pos = 0;
        }
    }

    public MayRepeatableInputStream(InputStream inputStream, int i) {
        super(inputStream);
        init(i);
        this.originInputStream = inputStream;
    }

    private void init(int i) {
        if (this.in instanceof FileInputStream) {
            FileChannel channel = ((FileInputStream) this.in).getChannel();
            this.fileChannel = channel;
            try {
                this.markPos = channel.position();
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid FileInputStream", e);
            }
        }
        if (i > 0) {
            this.in = new SdkBufferedInputStream(this.in, i);
        }
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
        super.close();
        abortIfNeeded();
    }

    @Override
    public void mark(int i) {
        abortIfNeeded();
        try {
            if (this.fileChannel != null) {
                this.markPos = this.fileChannel.position();
            } else if (this.originInputStream instanceof ByteArrayInputStream) {
                ((ByteArrayInputStream) this.originInputStream).mark(i);
            }
        } catch (IOException e) {
            throw new ServiceException("Failed to mark the file position", e);
        }
    }

    @Override
    public final boolean markSupported() {
        return this.fileChannel != null || (this.originInputStream instanceof ByteArrayInputStream);
    }

    @Override
    public int read() throws IOException {
        abortIfNeeded();
        return super.read();
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        abortIfNeeded();
        return super.read(bArr, i, i2);
    }

    @Override
    public void reset() throws IOException {
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel != null) {
            fileChannel.position(this.markPos);
            if (this.in instanceof SdkBufferedInputStream) {
                ((SdkBufferedInputStream) this.in).tearDown();
                return;
            }
            return;
        }
        if (!(this.originInputStream instanceof ByteArrayInputStream)) {
            throw new UnrecoverableIOException("UnRepeatable");
        }
        if (this.in instanceof SdkBufferedInputStream) {
            ((SdkBufferedInputStream) this.in).tearDown();
        }
        ((ByteArrayInputStream) this.originInputStream).reset();
    }

    @Override
    public long skip(long j) throws IOException {
        abortIfNeeded();
        return super.skip(j);
    }
}
