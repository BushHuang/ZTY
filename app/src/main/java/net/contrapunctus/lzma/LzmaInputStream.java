package net.contrapunctus.lzma;

import SevenZip.Compression.LZMA.Decoder;
import SevenZip.Compression.LZMA.Encoder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class LzmaInputStream extends FilterInputStream {
    private static final boolean DEBUG;
    private static final PrintStream dbg = System.err;
    protected DecoderThread dth;

    static {
        String property;
        try {
            property = System.getProperty("DEBUG_LzmaStreams");
        } catch (SecurityException unused) {
            property = null;
        }
        DEBUG = property != null;
    }

    public LzmaInputStream(InputStream inputStream) {
        super(null);
        DecoderThread decoderThread = new DecoderThread(inputStream);
        this.dth = decoderThread;
        this.in = ConcurrentBufferInputStream.create(decoderThread.q);
        if (DEBUG) {
            dbg.printf("%s << %s (%s)%n", this, this.in, this.dth.q);
        }
        this.dth.start();
    }

    public static void main(String[] strArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        printStream.print("I will try decoding this text.");
        printStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        Encoder encoder = new Encoder();
        encoder.SetEndMarkerMode(true);
        encoder.WriteCoderProperties(byteArrayOutputStream2);
        encoder.Code(byteArrayInputStream, byteArrayOutputStream2, -1L, -1L, null);
        byte[] byteArray = byteArrayOutputStream2.toByteArray();
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byteArray);
        Decoder decoder = new Decoder();
        byte[] bArr = new byte[5];
        byteArrayInputStream2.read(bArr, 0, 5);
        decoder.SetDecoderProperties(bArr);
        decoder.Code(byteArrayInputStream2, System.out, -1L);
        System.out.println();
        System.out.println(new BufferedReader(new InputStreamReader(new LzmaInputStream(new ByteArrayInputStream(byteArray)))).readLine());
    }

    @Override
    public void close() throws IOException {
        if (DEBUG) {
            dbg.printf("%s closed%n", this);
        }
        super.close();
    }

    public String toString() {
        return String.format("lzmaIn@%x", Integer.valueOf(hashCode()));
    }
}
