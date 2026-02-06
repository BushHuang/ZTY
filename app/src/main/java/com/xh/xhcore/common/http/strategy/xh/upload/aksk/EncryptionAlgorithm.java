package com.xh.xhcore.common.http.strategy.xh.upload.aksk;

public interface EncryptionAlgorithm {
    String decrypt(String str) throws Exception;

    byte[] encrypt(byte[] bArr) throws Exception;
}
