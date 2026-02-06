package com.xuehai.launcher.common.util;

import com.xuehai.launcher.common.base.BaseApplication;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base32;

public class TotpUtils {
    private static final int CODE_DIGITS = 6;
    private static final int TIME_STEP = 60;

    public static String createDeviceRandomText(int i) {
        return SecretUtil.createRandomText(i, SecretUtil.createRandomText(i, "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ") + BaseApplication.getInstance().getDeviceId()).toUpperCase();
    }

    private static String generateTOTP(String str, int i, int i2) throws NoSuchAlgorithmException, InvalidKeyException {
        if (i2 < 1 || i2 > 18) {
            throw new UnsupportedOperationException("不支持" + i2 + "位数的动态口令");
        }
        byte[] bArrHmacSha = hmacSha(ByteBuffer.allocate(8).putLong(getCurrentInterval(i)).array(), str);
        int i3 = bArrHmacSha[bArrHmacSha.length - 1] & 15;
        return leftPadding(Long.toString(((bArrHmacSha[i3 + 3] & 255) | (((bArrHmacSha[i3 + 2] & 255) << 8) | (((bArrHmacSha[i3] & 127) << 24) | ((bArrHmacSha[i3 + 1] & 255) << 16)))) % Long.parseLong(rightPadding("1", i2 + 1))), i2);
    }

    private static long getCurrentInterval(int i) {
        if (i < 0) {
            i = 60;
        }
        return (System.currentTimeMillis() / 1000) / i;
    }

    private static byte[] hmacSha(byte[] bArr, String str) throws NoSuchAlgorithmException, InvalidKeyException {
        try {
            byte[] bArrDecode = new Base32().decode(str);
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(bArrDecode, "HmacSHA1"));
            return mac.doFinal(bArr);
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    private static String leftPadding(String str, int i) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < i) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    private static String rightPadding(String str, int i) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < i) {
            sb.append("0");
        }
        return sb.toString();
    }

    public static boolean verify(String str, String str2, int i) {
        return verify(str, str2, i, 6);
    }

    public static boolean verify(String str, String str2, int i, int i2) {
        for (int i3 = 0; i3 <= 1; i3++) {
            if (generateTOTP(str, i, i2).equals(str2)) {
                return true;
            }
        }
        return false;
    }
}
