package com.obs.services.model;

public class SseKmsHeader {
    private String context;
    private ServerEncryption encryption;
    private String kmsKeyId;
    private String projectId;
    private SSEAlgorithmEnum sseAlgorithm = SSEAlgorithmEnum.KMS;

    @Deprecated
    public String getContext() {
        return this.context;
    }

    @Deprecated
    public ServerEncryption getEncryption() {
        return this.encryption;
    }

    public String getKmsKeyId() {
        return this.kmsKeyId;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public SSEAlgorithmEnum getSSEAlgorithm() {
        return this.sseAlgorithm;
    }

    @Deprecated
    public void setContext(String str) {
        this.context = str;
    }

    @Deprecated
    public void setEncryption(ServerEncryption serverEncryption) {
        this.encryption = serverEncryption;
    }

    public void setKmsKeyId(String str) {
        this.kmsKeyId = str;
    }

    public void setProjectId(String str) {
        this.projectId = str;
    }

    public String toString() {
        return "SseKmsHeader [encryption=" + this.encryption + ", kmsKeyId=" + this.kmsKeyId + ", context=" + this.context + "]";
    }
}
