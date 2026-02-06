package com.xh.xhcore.common.http.strategy.xh.upload.aksk;

import android.util.Base64;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.text.Charsets;

public class AesEncryptUtils {
    public static final String AES = "AES";
    private static final String ALGORITHM = "AES/GCM/NoPadding";

    private AesEncryptUtils() {
    }

    public static byte[] aesDecryptByBytes(byte[] bArr, Key key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        int i = byteBufferWrap.getInt();
        if (i < 12 || i >= 16) {
            throw new IllegalArgumentException("invalid iv length");
        }
        byte[] bArr2 = new byte[i];
        byteBufferWrap.get(bArr2);
        byte[] bArr3 = new byte[byteBufferWrap.remaining()];
        byteBufferWrap.get(bArr3);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(2, key, new GCMParameterSpec(128, bArr2));
        return cipher.doFinal(bArr3);
    }

    public static byte[] aesEncryptToBytes(byte[] bArr, Key key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] bArr2 = new byte[12];
        ThreadLocalRandom.current().nextBytes(bArr2);
        cipher.init(1, key, new GCMParameterSpec(128, bArr2));
        byte[] bArrDoFinal = cipher.doFinal(bArr);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16 + bArrDoFinal.length);
        byteBufferAllocate.putInt(12);
        byteBufferAllocate.put(bArr2);
        byteBufferAllocate.put(bArrDoFinal);
        return byteBufferAllocate.array();
    }

    public static byte[] base64Decode(String str) {
        return Base64.decode(str, 0);
    }

    public static String base64Encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    public static Key loadAesKey(String str) throws NoSuchAlgorithmException {
        KeyGenerator.getInstance("AES").init(128);
        return new SecretKeySpec(str.getBytes(Charsets.UTF_8), "AES");
    }
}
