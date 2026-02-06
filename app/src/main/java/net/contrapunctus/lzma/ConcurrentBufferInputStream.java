package net.contrapunctus.lzma;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.util.concurrent.ArrayBlockingQueue;

class ConcurrentBufferInputStream extends InputStream {
    private static final boolean DEBUG;
    private static final PrintStream dbg = System.err;
    protected boolean eof;
    protected ArrayBlockingQueue<byte[]> q;
    protected byte[] buf = null;
    protected int next = 0;

    static {
        String property;
        try {
            property = System.getProperty("DEBUG_ConcurrentBuffer");
        } catch (SecurityException unused) {
            property = null;
        }
        DEBUG = property != null;
    }

    ConcurrentBufferInputStream(ArrayBlockingQueue<byte[]> arrayBlockingQueue) {
        this.eof = false;
        if (DEBUG) {
            dbg.printf("%s << %s%n", this, arrayBlockingQueue);
        }
        this.q = arrayBlockingQueue;
        this.eof = false;
    }

    static InputStream create(ArrayBlockingQueue<byte[]> arrayBlockingQueue) {
        return new ConcurrentBufferInputStream(arrayBlockingQueue);
    }

    protected byte[] guarded_take() throws IOException {
        try {
            return this.q.take();
        } catch (InterruptedException e) {
            throw new InterruptedIOException(e.getMessage());
        }
    }

    protected boolean prepareAndCheckEOF() throws IOException {
        if (this.eof) {
            return true;
        }
        byte[] bArr = this.buf;
        if (bArr == null || this.next >= bArr.length) {
            byte[] bArrGuarded_take = guarded_take();
            this.buf = bArrGuarded_take;
            this.next = 0;
            if (bArrGuarded_take.length == 0) {
                this.eof = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public int read() throws IOException {
        if (prepareAndCheckEOF()) {
            return -1;
        }
        byte[] bArr = this.buf;
        int i = this.next;
        byte b = bArr[i];
        this.next = i + 1;
        return b & 255;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (prepareAndCheckEOF()) {
            return -1;
        }
        int length = this.buf.length - this.next;
        if (i2 >= length) {
            i2 = length;
        }
        System.arraycopy(this.buf, this.next, bArr, i, i2);
        this.next += i2;
        return i2;
    }

    public String toString() {
        return String.format("cbIn@%x", Integer.valueOf(hashCode()));
    }
}
