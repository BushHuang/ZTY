package com.xuehai.system.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    public static String getMD5(File file) throws NoSuchAlgorithmException, IOException {
        if (file != null && file.isFile()) {
            byte[] bArr = new byte[1024];
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                FileInputStream fileInputStream = new FileInputStream(file);
                while (true) {
                    int i = fileInputStream.read(bArr, 0, 1024);
                    if (i == -1) {
                        fileInputStream.close();
                        return byteArrayToHex(messageDigest.digest());
                    }
                    messageDigest.update(bArr, 0, i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            return getMD5(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMD5(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = null;
        if (bArr == null) {
            return null;
        }
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
        } catch (NoSuchAlgorithmException unused) {
            System.exit(-1);
        }
        return byteArrayToHex(messageDigest.digest());
    }
}
