package com.xuehai.launcher.common.util;

import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {
    public static String byteArrayToHex(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i = i2 + 1;
            cArr2[i2] = cArr[b & 15];
        }
        return new String(cArr2).toLowerCase();
    }

    public static String getMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Build.VERSION.SDK_INT >= 19 ? getMD5(str.getBytes(StandardCharsets.UTF_8)) : getMD5(str.getBytes(Charset.forName("UTF-8")));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getMD5(byte[] bArr) throws NoSuchAlgorithmException {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return byteArrayToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMD5FromFile(File file) {
        if (file != null && file.isFile()) {
            try {
                return getMD5FromStream(new FileInputStream(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getMD5FromStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                while (true) {
                    int i = inputStream.read(bArr, 0, 1024);
                    if (i == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, i);
                }
                inputStream.close();
                return byteArrayToHex(messageDigest.digest());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return null;
        }
    }
}
