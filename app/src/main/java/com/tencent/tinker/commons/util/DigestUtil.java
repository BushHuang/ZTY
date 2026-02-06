package com.tencent.tinker.commons.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public final class DigestUtil {
    private DigestUtil() {
        throw new UnsupportedOperationException();
    }

    public static long getCRC32(File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            long crc32 = getCRC32(bufferedInputStream);
            IOHelper.closeQuietly(bufferedInputStream);
            return crc32;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            IOHelper.closeQuietly(bufferedInputStream2);
            throw th;
        }
    }

    public static long getCRC32(InputStream inputStream) throws IOException {
        CRC32 crc32 = new CRC32();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return crc32.getValue();
            }
            crc32.update(bArr, 0, i);
        }
    }

    public static long getCRC32(byte[] bArr, int i, int i2) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, i, i2);
        return crc32.getValue();
    }
}
