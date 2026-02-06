package com.xh.xhcore.common.http.strategy.xh.upload.aksk;

import android.os.Build;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.security.Key;
import org.apache.commons.codec.Charsets;

public class AesEncryptionAlgorithm implements EncryptionAlgorithm {
    private final Key decryptKey;
    private final Key encryptKey;

    public AesEncryptionAlgorithm(Key key, Key key2) {
        this.encryptKey = key;
        this.decryptKey = key2;
    }

    @Override
    public String decrypt(String str) throws Exception {
        if (str == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return new String(AesEncryptUtils.aesDecryptByBytes(AesEncryptUtils.base64Decode(str), this.decryptKey), Charsets.UTF_8);
        }
        LogUtils.w("当前解密算法要求API至少为19");
        return null;
    }

    @Override
    public byte[] encrypt(byte[] bArr) throws Exception {
        if (Build.VERSION.SDK_INT >= 21) {
            return AesEncryptUtils.aesEncryptToBytes(bArr, this.encryptKey);
        }
        LogUtils.w("当前解密算法要求API至少为21");
        return null;
    }
}
