package com.xuehai.launcher.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LauncherAesUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";
    private static final String SALTPRE = "TEST";
    private static final String SECRET_KEY = "453DA222330F6F421E7DABF2CAB6AECC";

    public static String decrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, getSecretKey());
        return new String(cipher.doFinal(parseHexStr2Byte(str)), "UTF-8");
    }

    public static String encrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte[] bytes = str.getBytes("UTF-8");
        cipher.init(1, getSecretKey());
        return parseByte2HexStr(cipher.doFinal(bytes));
    }

    private static SecretKeySpec getSecretKey() throws Exception {
        return new SecretKeySpec(parseHexStr2Byte("453DA222330F6F421E7DABF2CAB6AECC"), "AES");
    }

    public static void main(String[] strArr) throws Exception {
        String strEncrypt = encrypt("contentalisbfgpasjhodfhpiwUHEFPHnwsdncfPAWUIBEG[  O2UH3EF[O   wheo[fhnhe:OSdbjngpahewgpoauwepofhbao[WHEFOHNKDBN;POWHBEGF[HWbefoh");
        System.out.println(strEncrypt);
        System.out.println(decrypt(strEncrypt));
    }

    public static String parseByte2HexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String str) throws NumberFormatException {
        if (str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }
}
