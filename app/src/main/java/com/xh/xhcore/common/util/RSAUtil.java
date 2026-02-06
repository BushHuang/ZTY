package com.xh.xhcore.common.util;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.io.IOUtils;

public class RSAUtil {
    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";

    public static String base64AndroidSdkEncode(String str) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Map<String, String> createKeys(int i) throws NoSuchAlgorithmException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(i);
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            String strEncodeToString = Base64.encodeToString(keyPairGenerateKeyPair.getPublic().getEncoded(), 8);
            String strEncodeToString2 = Base64.encodeToString(keyPairGenerateKeyPair.getPrivate().getEncoded(), 8);
            HashMap map = new HashMap();
            map.put("publicKey", strEncodeToString);
            map.put("privateKey", strEncodeToString2);
            return map;
        } catch (NoSuchAlgorithmException unused) {
            throw new IllegalArgumentException("No such algorithm-->[RSA]");
        }
    }

    public static RSAPrivateKey getPrivateKey(String str) throws Exception {
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str.getBytes("UTF-8"), 8)));
    }

    public static RSAPublicKey getPublicKey(String str) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 8)));
    }

    public static String privateDecrypt(String str, RSAPrivateKey rSAPrivateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, rSAPrivateKey);
            return new String(rsaSplitCodec(cipher, 2, Base64.decode(str, 8), rSAPrivateKey.getModulus().bitLength()), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + str + "]时遇到异常", e);
        }
    }

    public static String privateEncrypt(String str, RSAPrivateKey rSAPrivateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, rSAPrivateKey);
            return Base64.encodeToString(rsaSplitCodec(cipher, 1, str.getBytes("UTF-8"), rSAPrivateKey.getModulus().bitLength()), 8);
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + str + "]时遇到异常", e);
        }
    }

    public static String publicDecrypt(String str, RSAPublicKey rSAPublicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, rSAPublicKey);
            return new String(rsaSplitCodec(cipher, 2, Base64.decode(str, 8), rSAPublicKey.getModulus().bitLength()), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + str + "]时遇到异常", e);
        }
    }

    public static String publicEncrypt(String str, RSAPublicKey rSAPublicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, rSAPublicKey);
            return Base64.encodeToString(rsaSplitCodec(cipher, 1, str.getBytes("UTF-8"), rSAPublicKey.getModulus().bitLength()), 8);
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + str + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int i, byte[] bArr, int i2) {
        int i3 = i == 2 ? i2 / 8 : (i2 / 8) - 11;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i4 = 0;
        int i5 = 0;
        while (bArr.length > i4) {
            try {
                byte[] bArrDoFinal = bArr.length - i4 > i3 ? cipher.doFinal(bArr, i4, i3) : cipher.doFinal(bArr, i4, bArr.length - i4);
                byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                i5++;
                i4 = i5 * i3;
            } catch (Exception e) {
                throw new RuntimeException("加解密阀值为[" + i3 + "]的数据时发生异常", e);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
        return byteArray;
    }
}
