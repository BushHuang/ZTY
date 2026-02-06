package com.tencent.tinker.android.dex.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class FileUtils {
    private FileUtils() {
    }

    public static boolean hasArchiveSuffix(String str) {
        return str.endsWith(".zip") || str.endsWith(".jar") || str.endsWith(".apk");
    }

    public static byte[] readFile(File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        if (!file.exists()) {
            throw new RuntimeException(file + ": file not found");
        }
        if (!file.isFile()) {
            throw new RuntimeException(file + ": not a file");
        }
        if (!file.canRead()) {
            throw new RuntimeException(file + ": file not readable");
        }
        long length = file.length();
        int i = (int) length;
        if (i != length) {
            throw new RuntimeException(file + ": file too long");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 > 0) {
                    byteArrayOutputStream.write(bArr, 0, i2);
                } else {
                    try {
                        break;
                    } catch (Exception unused) {
                    }
                }
            }
            bufferedInputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    public static byte[] readFile(String str) throws IOException {
        return readFile(new File(str));
    }

    public static byte[] readStream(InputStream inputStream) throws IOException {
        return readStream(inputStream, 32768);
    }

    public static byte[] readStream(InputStream inputStream, int i) throws IOException {
        if (i <= 0) {
            i = 32768;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        byte[] bArr = new byte[8192];
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 <= 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }
}
