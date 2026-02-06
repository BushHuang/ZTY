package net.contrapunctus.lzma;

import SevenZip.Compression.LZMA.Encoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class LzmaOutputStream extends FilterOutputStream {
    private static final boolean DEBUG;
    private static final PrintStream dbg = System.err;
    protected EncoderThread eth;

    static {
        String property;
        try {
            property = System.getProperty("DEBUG_LzmaStreams");
        } catch (SecurityException unused) {
            property = null;
        }
        DEBUG = property != null;
    }

    public LzmaOutputStream(OutputStream outputStream) {
        this(outputStream, EncoderThread.DEFAULT_DICT_SZ_POW2, null);
    }

    public LzmaOutputStream(OutputStream outputStream, Integer num, Integer num2) {
        super(null);
        EncoderThread encoderThread = new EncoderThread(outputStream, num, num2);
        this.eth = encoderThread;
        this.out = ConcurrentBufferOutputStream.create(encoderThread.q);
        if (DEBUG) {
            dbg.printf("%s >> %s (%s)%n", this, this.out, this.eth.q);
        }
        this.eth.start();
    }

    public static void main(String[] strArr) throws IOException {
        PrintStream printStream = new PrintStream(new LzmaOutputStream(new OutputStream() {
            @Override
            public void write(int i) {
                System.out.printf("%02x ", Integer.valueOf(i));
            }
        }));
        printStream.print("Hello hello hello, world!");
        printStream.print("This is the best test.");
        printStream.close();
        System.out.println();
        System.out.println("TRADITIONAL WAY:");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream2 = new PrintStream(byteArrayOutputStream);
        printStream2.print("Hello hello hello, world!");
        printStream2.print("This is the best test.");
        printStream2.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        Encoder encoder = new Encoder();
        encoder.SetEndMarkerMode(true);
        encoder.SetDictionarySize(1048576);
        encoder.WriteCoderProperties(byteArrayOutputStream2);
        encoder.Code(byteArrayInputStream, byteArrayOutputStream2, -1L, -1L, null);
        for (byte b : byteArrayOutputStream2.toByteArray()) {
            System.out.printf("%02x ", Byte.valueOf(b));
        }
        System.out.println();
    }

    @Override
    public void close() throws IOException {
        if (DEBUG) {
            dbg.printf("%s closed%n", this);
        }
        this.out.close();
        try {
            this.eth.join();
            if (DEBUG) {
                dbg.printf("%s joined %s%n", this, this.eth);
            }
            if (this.eth.exn != null) {
                throw this.eth.exn;
            }
        } catch (InterruptedException e) {
            throw new InterruptedIOException(e.getMessage());
        }
    }

    public String toString() {
        return String.format("lzmaOut@%x", Integer.valueOf(hashCode()));
    }

    @Override
    public void write(int i) throws IOException {
        if (this.eth.exn != null) {
            throw this.eth.exn;
        }
        this.out.write(i);
    }
}
