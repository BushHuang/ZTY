package com.huawei.secure.android.common.encrypt.hash;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import com.huawei.secure.android.common.encrypt.utils.b;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SHA {

    private static final String f391a = "SHA";
    private static final String b = "SHA-256";
    private static final String c = "";
    private static final String[] d = {"SHA-256", "SHA-384", "SHA-512"};

    private SHA() {
    }

    private static boolean a(String str) {
        for (String str2 : d) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String sha256Encrypt(String str) {
        return shaEncrypt(str, "SHA-256");
    }

    public static String shaEncrypt(String str, String str2) throws NoSuchAlgorithmException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(f391a, "content or algorithm is null.");
            return "";
        }
        if (!a(str2)) {
            b.b(f391a, "algorithm is not safe or legal");
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes("UTF-8"));
            return HexUtil.byteArray2HexStr(messageDigest.digest());
        } catch (UnsupportedEncodingException unused) {
            b.b(f391a, "Error in generate SHA UnsupportedEncodingException");
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            b.b(f391a, "Error in generate SHA NoSuchAlgorithmException");
            return "";
        }
    }

    public static boolean validateSHA(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return false;
        }
        return str2.equals(shaEncrypt(str, str3));
    }

    public static boolean validateSHA256(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str2.equals(sha256Encrypt(str));
    }
}
