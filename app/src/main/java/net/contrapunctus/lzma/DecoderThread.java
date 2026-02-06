package net.contrapunctus.lzma;

import SevenZip.Compression.LZMA.Decoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ArrayBlockingQueue;

class DecoderThread extends Thread {
    private static final boolean DEBUG;
    private static final PrintStream dbg = System.err;
    static final int propSize = 5;
    static final byte[] props;
    protected Decoder dec;
    protected IOException exn;
    protected InputStream in;
    protected OutputStream out;
    protected ArrayBlockingQueue<byte[]> q;

    static {
        String property;
        try {
            property = System.getProperty("DEBUG_LzmaCoders");
        } catch (SecurityException unused) {
            property = null;
        }
        DEBUG = property != null;
        props = new byte[]{93, 0, 0, 16, 0};
    }

    DecoderThread(InputStream inputStream) {
        ArrayBlockingQueue<byte[]> arrayBlockingQueueNewQueue = ConcurrentBufferOutputStream.newQueue();
        this.q = arrayBlockingQueueNewQueue;
        this.in = inputStream;
        this.out = ConcurrentBufferOutputStream.create(arrayBlockingQueueNewQueue);
        this.dec = new Decoder();
        this.exn = null;
        if (DEBUG) {
            dbg.printf("%s >> %s (%s)%n", this, this.out, this.q);
        }
    }

    @Override
    public void run() throws IOException {
        try {
            this.dec.SetDecoderProperties(props);
            if (DEBUG) {
                dbg.printf("%s begins%n", this);
            }
            this.dec.Code(this.in, this.out, -1L);
            if (DEBUG) {
                dbg.printf("%s ends%n", this);
            }
            this.in.close();
            this.out.close();
        } catch (IOException e) {
            this.exn = e;
            if (DEBUG) {
                dbg.printf("%s exception: %s%n", e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Dec@%x", Integer.valueOf(hashCode()));
    }
}
