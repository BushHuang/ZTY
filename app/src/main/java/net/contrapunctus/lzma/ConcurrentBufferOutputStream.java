package net.contrapunctus.lzma;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ArrayBlockingQueue;

class ConcurrentBufferOutputStream extends OutputStream {
    static final int BUFSIZE = 16384;
    private static final boolean DEBUG;
    static final int QUEUESIZE = 4096;
    private static final PrintStream dbg = System.err;
    protected ArrayBlockingQueue<byte[]> q;

    static {
        String property;
        try {
            property = System.getProperty("DEBUG_ConcurrentBuffer");
        } catch (SecurityException unused) {
            property = null;
        }
        DEBUG = property != null;
    }

    ConcurrentBufferOutputStream(ArrayBlockingQueue<byte[]> arrayBlockingQueue) {
        if (DEBUG) {
            dbg.printf("%s >> %s%n", this, arrayBlockingQueue);
        }
        this.q = arrayBlockingQueue;
    }

    static OutputStream create(ArrayBlockingQueue<byte[]> arrayBlockingQueue) {
        return new BufferedOutputStream(new ConcurrentBufferOutputStream(arrayBlockingQueue), 16384);
    }

    static ArrayBlockingQueue<byte[]> newQueue() {
        return new ArrayBlockingQueue<>(4096);
    }

    @Override
    public void close() throws InterruptedException, IOException {
        if (DEBUG) {
            dbg.printf("%s closed%n", this);
        }
        guarded_put(new byte[0]);
    }

    protected void guarded_put(byte[] bArr) throws InterruptedException, IOException {
        try {
            this.q.put(bArr);
        } catch (InterruptedException e) {
            throw new InterruptedIOException(e.getMessage());
        }
    }

    public String toString() {
        return String.format("cbOut@%x", Integer.valueOf(hashCode()));
    }

    @Override
    public void write(int i) throws InterruptedException, IOException {
        guarded_put(new byte[]{(byte) (i & 255)});
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        guarded_put(bArr2);
    }
}
