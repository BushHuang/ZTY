package org.sevenzip4j.archive.coder;

import SevenZip.Compression.LZMA.Encoder;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class LZMAOutputStream extends PipedOutputStream {
    private Encoder encoder;
    private Thread lzmaEncoderThread;
    private LZMAEncoderRunnable lzmaRunnable;

    private class LZMAEncoderRunnable extends PipedInputStream implements Runnable {
        private Throwable exception = null;
        private OutputStream outputStream;

        public LZMAEncoderRunnable(PipedOutputStream pipedOutputStream, OutputStream outputStream) throws IOException {
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try {
                LZMAOutputStream.this.encoder.Code(this, this.outputStream, -1L, -1L, null);
            } catch (Throwable th) {
                this.exception = th;
                th.printStackTrace();
            }
        }
    }

    public LZMAOutputStream(Encoder encoder, OutputStream outputStream) throws IOException {
        this.encoder = encoder;
        LZMAEncoderRunnable lZMAEncoderRunnable = new LZMAEncoderRunnable(this, outputStream);
        this.lzmaRunnable = lZMAEncoderRunnable;
        lZMAEncoderRunnable.connect(this);
        Thread thread = new Thread(this.lzmaRunnable);
        this.lzmaEncoderThread = thread;
        thread.start();
    }

    @Override
    public void close() throws InterruptedException, IOException {
        super.close();
        try {
            if (this.lzmaEncoderThread.isAlive()) {
                this.lzmaEncoderThread.join();
            } else {
                this.lzmaEncoderThread.interrupt();
            }
            if (this.lzmaRunnable.exception != null) {
                throw new IOException(this.lzmaRunnable.exception);
            }
        } catch (InterruptedException e) {
            throw new InterruptedIOException(e.getMessage());
        }
    }

    @Override
    public void write(int i) throws IOException {
        if (!this.lzmaEncoderThread.isAlive()) {
            throw new IOException("lzmaEncoderThread not alive");
        }
        super.write(i);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        for (byte b : bArr) {
            write(b);
        }
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            write(bArr[i3]);
        }
    }
}
