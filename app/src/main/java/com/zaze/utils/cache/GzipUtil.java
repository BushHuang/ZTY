package com.zaze.utils.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
    public static byte[] compress(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static void compressFile(InputStream inputStream, OutputStream outputStream) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        Exception e;
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            try {
                try {
                    gZIPOutputStream = new GZIPOutputStream(outputStream);
                } catch (Exception e2) {
                    gZIPOutputStream = null;
                    e = e2;
                } catch (Throwable th) {
                    th = th;
                    if (0 != 0) {
                        try {
                            gZIPOutputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            throw th;
                        }
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            gZIPOutputStream.write(bArr, 0, i);
                        }
                    }
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void compressFile(String str, String str2) throws Throwable {
        try {
            compressFile(new FileInputStream(str), new FileOutputStream(str2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static byte[] uncompress(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        byte[] bArr2 = new byte[256];
        while (true) {
            int i = gZIPInputStream.read(bArr2);
            if (i < 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr2, 0, i);
        }
    }
}
