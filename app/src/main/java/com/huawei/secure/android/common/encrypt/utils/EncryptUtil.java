package com.huawei.secure.android.common.encrypt.utils;

import android.os.Build;
import android.util.Base64;
import android.util.Log;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;

public class EncryptUtil {

    private static final String f399a = "EncryptUtil";
    private static final String b = "RSA";
    private static boolean c = false;
    private static boolean d = true;

    private static SecureRandom a() {
        b.a("EncryptUtil", "generateSecureRandomNew ");
        SecureRandom instanceStrong = null;
        try {
            instanceStrong = Build.VERSION.SDK_INT >= 26 ? SecureRandom.getInstanceStrong() : SecureRandom.getInstance("SHA1PRNG");
            AESEngine aESEngine = new AESEngine();
            byte[] bArr = new byte[32];
            instanceStrong.nextBytes(bArr);
            return new SP800SecureRandomBuilder(instanceStrong, true).setEntropyBitsRequired(384).buildCTR(aESEngine, 256, bArr, false);
        } catch (NoSuchAlgorithmException unused) {
            b.b("EncryptUtil", "NoSuchAlgorithmException");
            return instanceStrong;
        } catch (Throwable th) {
            if (d) {
                b.b("EncryptUtil", "exception : " + th.getMessage() + " , you should implementation bcprov-jdk15on library");
                d = false;
            }
            return instanceStrong;
        }
    }

    private static byte[] a(int i) {
        SecureRandom secureRandomA = a();
        if (secureRandomA == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        secureRandomA.nextBytes(bArr);
        return bArr;
    }

    public static SecureRandom genSecureRandom() {
        if (c) {
            return a();
        }
        SecureRandom instanceStrong = null;
        try {
            instanceStrong = Build.VERSION.SDK_INT >= 26 ? SecureRandom.getInstanceStrong() : SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException unused) {
            b.b("EncryptUtil", "genSecureRandom: NoSuchAlgorithmException");
        }
        return instanceStrong;
    }

    public static byte[] generateSecureRandom(int i) {
        if (c) {
            return a(i);
        }
        byte[] bArr = new byte[i];
        try {
            (Build.VERSION.SDK_INT >= 26 ? SecureRandom.getInstanceStrong() : SecureRandom.getInstance("SHA1PRNG")).nextBytes(bArr);
            return bArr;
        } catch (NoSuchAlgorithmException unused) {
            Log.e("EncryptUtil", "getSecureRandomBytes: NoSuchAlgorithmException");
            return new byte[0];
        }
    }

    public static String generateSecureRandomStr(int i) {
        return HexUtil.byteArray2HexStr(generateSecureRandom(i));
    }

    public static PrivateKey getPrivateKey(String str) {
        try {
            try {
                return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                b.b("EncryptUtil", "load Key Exception:" + e.getMessage());
                return null;
            }
        } catch (IllegalArgumentException unused) {
            b.b("EncryptUtil", "base64 decode IllegalArgumentException");
            return null;
        } catch (Exception e2) {
            b.b("EncryptUtil", "base64 decode Exception" + e2.getMessage());
            return null;
        }
    }

    public static RSAPublicKey getPublicKey(String str) {
        try {
            try {
                return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                b.b("EncryptUtil", "load Key Exception:" + e.getMessage());
                return null;
            }
        } catch (IllegalArgumentException unused) {
            b.b("EncryptUtil", "base64 decode IllegalArgumentException");
            return null;
        } catch (Exception e2) {
            b.b("EncryptUtil", "base64 decode Exception" + e2.getMessage());
            return null;
        }
    }

    public static boolean isBouncycastleFlag() {
        return c;
    }

    public static void setBouncycastleFlag(boolean z) {
        b.c("EncryptUtil", "setBouncycastleFlag: " + z);
        c = z;
    }
}
