package com.obs.services.internal.io;

import java.io.IOException;
import java.io.InputStream;

public class InterruptableInputStream extends InputStream implements InputStreamWrapper {
    private InputStream inputStream;
    private boolean interrupted = false;

    public InterruptableInputStream(InputStream inputStream) {
        this.inputStream = null;
        this.inputStream = inputStream;
    }

    private void maybeInterruptInputStream() throws IOException {
        if (this.interrupted) {
            try {
                close();
            } catch (IOException unused) {
            }
            throw new UnrecoverableIOException("Reading from input stream deliberately interrupted");
        }
    }

    @Override
    public int available() throws IOException {
        maybeInterruptInputStream();
        return this.inputStream.available();
    }

    @Override
    public void close() throws IOException {
        this.inputStream.close();
    }

    @Override
    public InputStream getWrappedInputStream() {
        return this.inputStream;
    }

    public void interrupt() {
        this.interrupted = true;
    }

    @Override
    public int read() throws IOException {
        maybeInterruptInputStream();
        return this.inputStream.read();
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        maybeInterruptInputStream();
        return this.inputStream.read(bArr, i, i2);
    }
}
