package com.xh.xhcore.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class XHEncryptUtil {
    private static final String AES_ALGORITHM = "AES";
    public static String AES_Transformation = "AES/ECB/NoPadding";
    private static final String DES_ALGORITHM = "DES";
    public static String DES_Transformation = "DES/ECB/NoPadding";
    private static final String TRIPLE_DES_ALGORITHM = "DESede";
    public static String TripleDES_Transformation = "DESede/ECB/NoPadding";

    private XHEncryptUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] decrypt3DES(byte[] bArr, byte[] bArr2) {
        return desTemplate(bArr, bArr2, "DESede", TripleDES_Transformation, false);
    }

    public static byte[] decryptAES(byte[] bArr, byte[] bArr2) {
        return desTemplate(bArr, bArr2, "AES", AES_Transformation, false);
    }

    public static byte[] decryptBase64AES(byte[] bArr, byte[] bArr2) {
        return decryptAES(XHEncodeUtil.base64Decode(bArr), bArr2);
    }

    public static byte[] decryptBase64DES(byte[] bArr, byte[] bArr2) {
        return decryptDES(XHEncodeUtil.base64Decode(bArr), bArr2);
    }

    public static byte[] decryptBase64_3DES(byte[] bArr, byte[] bArr2) {
        return decrypt3DES(XHEncodeUtil.base64Decode(bArr), bArr2);
    }

    public static byte[] decryptDES(byte[] bArr, byte[] bArr2) {
        return desTemplate(bArr, bArr2, "DES", DES_Transformation, false);
    }

    public static byte[] decryptHexString3DES(String str, byte[] bArr) {
        return decrypt3DES(XHConvertUtil.hexString2Bytes(str), bArr);
    }

    public static byte[] decryptHexStringAES(String str, byte[] bArr) {
        return decryptAES(XHConvertUtil.hexString2Bytes(str), bArr);
    }

    public static byte[] decryptHexStringDES(String str, byte[] bArr) {
        return decryptDES(XHConvertUtil.hexString2Bytes(str), bArr);
    }

    public static byte[] desTemplate(byte[] bArr, byte[] bArr2, String str, String str2, boolean z) {
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
                Cipher cipher = Cipher.getInstance(str2);
                cipher.init(z ? 1 : 2, secretKeySpec, new SecureRandom());
                return cipher.doFinal(bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] encrypt3DES(byte[] bArr, byte[] bArr2) {
        return desTemplate(bArr, bArr2, "DESede", TripleDES_Transformation, true);
    }

    public static byte[] encrypt3DES2Base64(byte[] bArr, byte[] bArr2) {
        return XHEncodeUtil.base64Encode(encrypt3DES(bArr, bArr2));
    }

    public static String encrypt3DES2HexString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encrypt3DES(bArr, bArr2));
    }

    public static byte[] encryptAES(byte[] bArr, byte[] bArr2) {
        return desTemplate(bArr, bArr2, "AES", AES_Transformation, true);
    }

    public static byte[] encryptAES2Base64(byte[] bArr, byte[] bArr2) {
        return XHEncodeUtil.base64Encode(encryptAES(bArr, bArr2));
    }

    public static String encryptAES2HexString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptAES(bArr, bArr2));
    }

    public static byte[] encryptDES(byte[] bArr, byte[] bArr2) {
        return desTemplate(bArr, bArr2, "DES", DES_Transformation, true);
    }

    public static byte[] encryptDES2Base64(byte[] bArr, byte[] bArr2) {
        return XHEncodeUtil.base64Encode(encryptDES(bArr, bArr2));
    }

    public static String encryptDES2HexString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptDES(bArr, bArr2));
    }

    public static byte[] encryptHmacMD5(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacMD5");
    }

    public static String encryptHmacMD5ToString(String str, String str2) {
        return encryptHmacMD5ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacMD5ToString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptHmacMD5(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA1(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA1");
    }

    public static String encryptHmacSHA1ToString(String str, String str2) {
        return encryptHmacSHA1ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA1ToString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptHmacSHA1(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA224(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA224");
    }

    public static String encryptHmacSHA224ToString(String str, String str2) {
        return encryptHmacSHA224ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA224ToString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptHmacSHA224(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA256(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA256");
    }

    public static String encryptHmacSHA256ToString(String str, String str2) {
        return encryptHmacSHA256ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA256ToString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptHmacSHA256(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA384(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA384");
    }

    public static String encryptHmacSHA384ToString(String str, String str2) {
        return encryptHmacSHA384ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA384ToString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptHmacSHA384(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA512(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA512");
    }

    public static String encryptHmacSHA512ToString(String str, String str2) {
        return encryptHmacSHA512ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA512ToString(byte[] bArr, byte[] bArr2) {
        return XHConvertUtil.bytes2HexString(encryptHmacSHA512(bArr, bArr2));
    }

    public static byte[] encryptMD2(byte[] bArr) {
        return hashTemplate(bArr, "MD2");
    }

    public static String encryptMD2ToString(String str) {
        return encryptMD2ToString(str.getBytes());
    }

    public static String encryptMD2ToString(byte[] bArr) {
        return XHConvertUtil.bytes2HexString(encryptMD2(bArr));
    }

    public static byte[] encryptMD5(byte[] bArr) {
        return hashTemplate(bArr, "MD5");
    }

    public static byte[] encryptMD5File(File file) throws Throwable {
        FileInputStream fileInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        if (file == null) {
            return null;
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (IOException e) {
                e = e;
                fileInputStream = null;
                e.printStackTrace();
                XHCloseUtil.closeIO(fileInputStream);
                return null;
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                fileInputStream = null;
                e.printStackTrace();
                XHCloseUtil.closeIO(fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                XHCloseUtil.closeIO(closeable2);
                throw th;
            }
            try {
                DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
                while (digestInputStream.read(new byte[262144]) > 0) {
                }
                byte[] bArrDigest = digestInputStream.getMessageDigest().digest();
                XHCloseUtil.closeIO(fileInputStream);
                return bArrDigest;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                XHCloseUtil.closeIO(fileInputStream);
                return null;
            } catch (NoSuchAlgorithmException e4) {
                e = e4;
                e.printStackTrace();
                XHCloseUtil.closeIO(fileInputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            closeable2 = closeable;
            XHCloseUtil.closeIO(closeable2);
            throw th;
        }
    }

    public static byte[] encryptMD5File(String str) {
        return encryptMD5File(XHStringUtil.isSpace(str) ? null : new File(str));
    }

    public static String encryptMD5File2String(File file) {
        return XHConvertUtil.bytes2HexString(encryptMD5File(file));
    }

    public static String encryptMD5File2String(String str) {
        return encryptMD5File2String(XHStringUtil.isSpace(str) ? null : new File(str));
    }

    public static String encryptMD5ToString(String str) {
        return encryptMD5ToString(str.getBytes());
    }

    public static String encryptMD5ToString(String str, String str2) {
        return XHConvertUtil.bytes2HexString(encryptMD5((str + str2).getBytes()));
    }

    public static String encryptMD5ToString(byte[] bArr) {
        return XHConvertUtil.bytes2HexString(encryptMD5(bArr));
    }

    public static String encryptMD5ToString(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return XHConvertUtil.bytes2HexString(encryptMD5(bArr3));
    }

    public static byte[] encryptSHA1(byte[] bArr) {
        return hashTemplate(bArr, "SHA1");
    }

    public static String encryptSHA1ToString(String str) {
        return encryptSHA1ToString(str.getBytes());
    }

    public static String encryptSHA1ToString(byte[] bArr) {
        return XHConvertUtil.bytes2HexString(encryptSHA1(bArr));
    }

    public static byte[] encryptSHA224(byte[] bArr) {
        return hashTemplate(bArr, "SHA224");
    }

    public static String encryptSHA224ToString(String str) {
        return encryptSHA224ToString(str.getBytes());
    }

    public static String encryptSHA224ToString(byte[] bArr) {
        return XHConvertUtil.bytes2HexString(encryptSHA224(bArr));
    }

    public static byte[] encryptSHA256(byte[] bArr) {
        return hashTemplate(bArr, "SHA256");
    }

    public static String encryptSHA256ToString(String str) {
        return encryptSHA256ToString(str.getBytes());
    }

    public static String encryptSHA256ToString(byte[] bArr) {
        return XHConvertUtil.bytes2HexString(encryptSHA256(bArr));
    }

    public static byte[] encryptSHA384(byte[] bArr) {
        return hashTemplate(bArr, "SHA384");
    }

    public static String encryptSHA384ToString(String str) {
        return encryptSHA384ToString(str.getBytes());
    }

    public static String encryptSHA384ToString(byte[] bArr) {
        return XHConvertUtil.bytes2HexString(encryptSHA384(bArr));
    }

    public static byte[] encryptSHA512(byte[] bArr) {
        return hashTemplate(bArr, "SHA512");
    }

    public static String encryptSHA512ToString(String str) {
        return encryptSHA512ToString(str.getBytes());
    }

    public static String encryptSHA512ToString(byte[] bArr) {
        return XHConvertUtil.bytes2HexString(encryptSHA512(bArr));
    }

    private static byte[] hashTemplate(byte[] bArr, String str) throws NoSuchAlgorithmException {
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                return messageDigest.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static byte[] hmacTemplate(byte[] bArr, byte[] bArr2, String str) throws NoSuchAlgorithmException, InvalidKeyException {
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
                Mac mac = Mac.getInstance(str);
                mac.init(secretKeySpec);
                return mac.doFinal(bArr);
            } catch (InvalidKeyException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
