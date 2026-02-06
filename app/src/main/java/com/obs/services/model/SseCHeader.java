package com.obs.services.model;

import java.io.UnsupportedEncodingException;

public class SseCHeader {
    private ServerAlgorithm algorithm;
    private SSEAlgorithmEnum sseAlgorithm = SSEAlgorithmEnum.AES256;
    private byte[] sseCKey;
    private String sseCKeyBase64;

    @Deprecated
    public ServerAlgorithm getAlgorithm() {
        return this.algorithm;
    }

    public SSEAlgorithmEnum getSSEAlgorithm() {
        return this.sseAlgorithm;
    }

    public byte[] getSseCKey() {
        return this.sseCKey;
    }

    public String getSseCKeyBase64() {
        return this.sseCKeyBase64;
    }

    @Deprecated
    public void setAlgorithm(ServerAlgorithm serverAlgorithm) {
        this.algorithm = serverAlgorithm;
    }

    @Deprecated
    public void setSseCKey(String str) {
        if (str != null) {
            try {
                this.sseCKey = str.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("fail to read sseCkey", e);
            }
        }
    }

    public void setSseCKey(byte[] bArr) {
        this.sseCKey = bArr;
    }

    public void setSseCKeyBase64(String str) {
        this.sseCKeyBase64 = str;
    }

    public String toString() {
        return "SseCHeader [algorithm=" + this.algorithm + ", sseCKey=" + this.sseCKey + ", sseCKeyBase64=" + this.sseCKeyBase64 + "]";
    }
}
