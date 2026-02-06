package com.tencent.tinker.bsdiff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BSUtil {
    public static final int BUFFER_SIZE = 8192;
    public static final int HEADER_SIZE = 32;

    public static byte[] inputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr, 0, 8192);
            if (i == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static final boolean readFromStream(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i + i3, i2 - i3);
            if (i4 < 0) {
                return false;
            }
            i3 += i4;
        }
        return true;
    }
}
