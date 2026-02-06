package com.analysys.aesencrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {

    public static final byte[] f12a = "Analysys_315$CBC".getBytes();

    public static String a(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, secretKeySpec);
            return a(cipher.doFinal(str.getBytes()));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(charArray[(bArr[i] >> 4) & 15]);
            sb.append(charArray[bArr[i] & 15]);
        }
        return sb.toString();
    }

    public static String b(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(f12a));
            return a(cipher.doFinal(str2.getBytes()));
        } catch (Throwable unused) {
            return null;
        }
    }
}
