package net.contrapunctus.lzma;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class Benchmark {
    static final boolean $assertionsDisabled = false;
    private static final int BUFSIZE = 8192;
    private static final int EXPONENT = 18;
    private static final int ITERATIONS = 512;
    private static byte[][] data;
    private static final Random rnd = new Random(-889275714);
    private static final Checksum ck = new Adler32();

    static {
        data = (byte[][]) null;
        data = new byte[18][];
        int i = 0;
        int i2 = 1;
        while (true) {
            byte[][] bArr = data;
            if (i >= bArr.length) {
                return;
            }
            bArr[i] = new byte[i2];
            rnd.nextBytes(bArr[i]);
            i++;
            i2 *= 2;
        }
    }

    public static void doit() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        LzmaOutputStream lzmaOutputStream = new LzmaOutputStream(byteArrayOutputStream);
        int length = 0;
        for (int i = 0; i < 512; i++) {
            int iNextInt = rnd.nextInt(data.length);
            lzmaOutputStream.write(data[iNextInt]);
            Checksum checksum = ck;
            byte[][] bArr = data;
            checksum.update(bArr[iNextInt], 0, bArr[iNextInt].length);
            length += data[iNextInt].length;
        }
        lzmaOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.printf("%d bytes written, %d bytes compressed, checksum %X\n", Integer.valueOf(length), Integer.valueOf(byteArray.length), Long.valueOf(ck.getValue()));
        LzmaInputStream lzmaInputStream = new LzmaInputStream(new ByteArrayInputStream(byteArray));
        byte[] bArr2 = new byte[8192];
        ck.reset();
        int i2 = 0;
        for (int i3 = lzmaInputStream.read(bArr2); i3 > 0; i3 = lzmaInputStream.read(bArr2)) {
            i2 += i3;
            ck.update(bArr2, 0, i3);
        }
        System.out.printf("%d bytes decompressed, checksum %X\n", Integer.valueOf(i2), Long.valueOf(ck.getValue()));
    }

    public static void main(String[] strArr) throws IOException {
        long jNanoTime = System.nanoTime();
        doit();
        System.out.printf("%d us elapsed\n", Long.valueOf((System.nanoTime() - jNanoTime) / 1000));
    }
}
