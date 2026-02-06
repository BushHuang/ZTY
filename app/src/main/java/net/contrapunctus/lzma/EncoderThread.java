package net.contrapunctus.lzma;

import SevenZip.Compression.LZMA.Encoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ArrayBlockingQueue;

class EncoderThread extends Thread {
    private static final boolean DEBUG;
    public static final Integer DEFAULT_DICT_SZ_POW2 = new Integer(1048576);
    private static final PrintStream dbg = System.err;
    protected Encoder enc;
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
    }

    EncoderThread(OutputStream outputStream) {
        this(outputStream, DEFAULT_DICT_SZ_POW2, null);
    }

    EncoderThread(OutputStream outputStream, Integer num, Integer num2) {
        ArrayBlockingQueue<byte[]> arrayBlockingQueueNewQueue = ConcurrentBufferOutputStream.newQueue();
        this.q = arrayBlockingQueueNewQueue;
        this.in = ConcurrentBufferInputStream.create(arrayBlockingQueueNewQueue);
        this.out = outputStream;
        Encoder encoder = new Encoder();
        this.enc = encoder;
        this.exn = null;
        encoder.SetDictionarySize(1 << (num == null ? DEFAULT_DICT_SZ_POW2 : num).intValue());
        if (num2 != null) {
            this.enc.SetNumFastBytes(num2.intValue());
        }
        if (DEBUG) {
            dbg.printf("%s << %s (%s)%n", this, this.in, this.q);
        }
    }

    @Override
    public void run() throws IOException {
        try {
            this.enc.SetEndMarkerMode(true);
            if (DEBUG) {
                dbg.printf("%s begins%n", this);
            }
            this.enc.Code(this.in, this.out, -1L, -1L, null);
            if (DEBUG) {
                dbg.printf("%s ends%n", this);
            }
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
        return String.format("Enc@%x", Integer.valueOf(hashCode()));
    }
}
