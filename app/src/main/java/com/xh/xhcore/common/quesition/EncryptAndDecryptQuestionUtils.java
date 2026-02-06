package com.xh.xhcore.common.quesition;

import com.xh.xhcore.jni.XHCoreJniCache;

public class EncryptAndDecryptQuestionUtils {
    private static final int XH_SIGNATURE_KEY_LEN = XHCoreJniCache.getXhSignatureKey().length();

    public static String decryptContent(String str) {
        return doUnSign(doBase64Decode(str));
    }

    private static byte[] doBase64Decode(String str) {
        return Base64ForQuestion.getDecoder().decode(str.getBytes());
    }

    private static String doBase64Encode(byte[] bArr) {
        return Base64ForQuestion.getEncoder().encodeToString(bArr);
    }

    private static byte[] doSign(String str) {
        byte[] bytes = XHCoreJniCache.getXhSignatureKey().getBytes();
        byte[] bytes2 = str.getBytes();
        byte[] bArr = new byte[bytes2.length];
        for (int i = 0; i < bytes2.length; i++) {
            bArr[i] = (byte) (bytes[i % XH_SIGNATURE_KEY_LEN] ^ bytes2[i]);
        }
        return bArr;
    }

    private static String doUnSign(byte[] bArr) {
        byte[] bytes = XHCoreJniCache.getXhSignatureKey().getBytes();
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (bytes[i % XH_SIGNATURE_KEY_LEN] ^ bArr[i]);
        }
        return new String(bArr2);
    }

    public static String encryptContent(String str) {
        return doBase64Encode(doSign(str));
    }
}
