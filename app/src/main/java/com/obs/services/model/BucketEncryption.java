package com.obs.services.model;

public class BucketEncryption extends HeaderResponse {
    private String kmsKeyId;
    private SSEAlgorithmEnum sseAlgorithm;

    public BucketEncryption() {
    }

    public BucketEncryption(SSEAlgorithmEnum sSEAlgorithmEnum) {
        this.sseAlgorithm = sSEAlgorithmEnum;
    }

    public String getKmsKeyId() {
        return this.kmsKeyId;
    }

    public SSEAlgorithmEnum getSseAlgorithm() {
        return this.sseAlgorithm;
    }

    public void setKmsKeyId(String str) {
        this.kmsKeyId = str;
    }

    public void setSseAlgorithm(SSEAlgorithmEnum sSEAlgorithmEnum) {
        this.sseAlgorithm = sSEAlgorithmEnum;
    }

    @Override
    public String toString() {
        return "BucketEncryption [sseAlgorithm=" + this.sseAlgorithm + ", kmsKeyId=" + this.kmsKeyId + "]";
    }
}
