package com.huawei.secure.android.common.encrypt.rsa;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.b;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

public abstract class RSASign {

    private static final String f397a = "SHA256WithRSA";
    private static final String b = "SHA256WithRSA/PSS";
    private static final String c = "RSASign";
    private static final String d = "UTF-8";
    private static final String e = "";

    private static String a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(c, "sign content or key is null");
            return "";
        }
        PrivateKey privateKey = EncryptUtil.getPrivateKey(str2);
        return z ? newSign(str, privateKey) : sign(str, privateKey);
    }

    private static String a(String str, PrivateKey privateKey, boolean z) {
        try {
            return Base64.encodeToString(sign(str.getBytes("UTF-8"), privateKey, z), 0);
        } catch (UnsupportedEncodingException e2) {
            b.b(c, "sign UnsupportedEncodingException: " + e2.getMessage());
            return "";
        }
    }

    private static boolean a(String str, String str2, String str3, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2)) {
            b.b(c, "content or public key or sign value is null");
            return false;
        }
        RSAPublicKey publicKey = EncryptUtil.getPublicKey(str3);
        return z ? newVerifySign(str, str2, publicKey) : verifySign(str, str2, publicKey);
    }

    private static boolean a(String str, String str2, PublicKey publicKey, boolean z) {
        try {
            return verifySign(str.getBytes("UTF-8"), Base64.decode(str2, 0), publicKey, z);
        } catch (UnsupportedEncodingException e2) {
            b.b(c, "verifySign UnsupportedEncodingException: " + e2.getMessage());
            return false;
        } catch (Exception e3) {
            b.b(c, "base64 decode Exception : " + e3.getMessage());
            return false;
        }
    }

    public static boolean isBuildVersionHigherThan23() {
        return Build.VERSION.SDK_INT > 23;
    }

    public static String newSign(String str, String str2) {
        if (isBuildVersionHigherThan23()) {
            return a(str, str2, true);
        }
        b.b(c, "sdk version is too low");
        return "";
    }

    public static String newSign(String str, PrivateKey privateKey) {
        if (isBuildVersionHigherThan23()) {
            return a(str, privateKey, true);
        }
        b.b(c, "sdk version is too low");
        return "";
    }

    public static boolean newVerifySign(String str, String str2, String str3) {
        if (isBuildVersionHigherThan23()) {
            return a(str, str2, str3, true);
        }
        b.b(c, "sdk version is too low");
        return false;
    }

    public static boolean newVerifySign(String str, String str2, PublicKey publicKey) {
        if (isBuildVersionHigherThan23()) {
            return a(str, str2, publicKey, true);
        }
        b.b(c, "sdk version is too low");
        return false;
    }

    @Deprecated
    public static String sign(String str, String str2) {
        return a(str, str2, false);
    }

    @Deprecated
    public static String sign(String str, PrivateKey privateKey) {
        return a(str, privateKey, false);
    }

    public static byte[] sign(byte[] bArr, PrivateKey privateKey, boolean z) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidAlgorithmParameterException {
        Signature signature;
        byte[] bArr2 = new byte[0];
        if (bArr == null || privateKey == null || !RSAEncrypt.isPrivateKeyLengthRight((RSAPrivateKey) privateKey)) {
            b.b(c, "content or privateKey is null , or length is too short");
            return bArr2;
        }
        try {
            if (z) {
                signature = Signature.getInstance("SHA256WithRSA/PSS");
                signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
            } else {
                signature = Signature.getInstance("SHA256WithRSA");
            }
            signature.initSign(privateKey);
            signature.update(bArr);
            return signature.sign();
        } catch (InvalidAlgorithmParameterException e2) {
            b.b(c, "sign InvalidAlgorithmParameterException: " + e2.getMessage());
            return bArr2;
        } catch (InvalidKeyException e3) {
            b.b(c, "sign InvalidKeyException: " + e3.getMessage());
            return bArr2;
        } catch (NoSuchAlgorithmException e4) {
            b.b(c, "sign NoSuchAlgorithmException: " + e4.getMessage());
            return bArr2;
        } catch (SignatureException e5) {
            b.b(c, "sign SignatureException: " + e5.getMessage());
            return bArr2;
        } catch (Exception e6) {
            b.b(c, "sign Exception: " + e6.getMessage());
            return bArr2;
        }
    }

    @Deprecated
    public static boolean verifySign(String str, String str2, String str3) {
        return a(str, str2, str3, false);
    }

    @Deprecated
    public static boolean verifySign(String str, String str2, PublicKey publicKey) {
        return a(str, str2, publicKey, false);
    }

    public static boolean verifySign(byte[] bArr, byte[] bArr2, PublicKey publicKey, boolean z) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidAlgorithmParameterException {
        Signature signature;
        if (bArr == null || publicKey == null || bArr2 == null || !RSAEncrypt.isPublicKeyLengthRight((RSAPublicKey) publicKey)) {
            b.b(c, "content or publicKey is null , or length is too short");
            return false;
        }
        try {
            if (z) {
                signature = Signature.getInstance("SHA256WithRSA/PSS");
                signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
            } else {
                signature = Signature.getInstance("SHA256WithRSA");
            }
            signature.initVerify(publicKey);
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (GeneralSecurityException e2) {
            b.b(c, "check sign exception: " + e2.getMessage());
            return false;
        } catch (Exception e3) {
            b.b(c, "exception : " + e3.getMessage());
            return false;
        }
    }
}
