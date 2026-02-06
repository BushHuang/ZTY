package com.obs.services.model.fs;

public class SetBucketFSStatusRequest {
    private String bucketName;
    private FSStatusEnum status;

    public SetBucketFSStatusRequest() {
    }

    public SetBucketFSStatusRequest(String str, FSStatusEnum fSStatusEnum) {
        this.bucketName = str;
        setStatus(fSStatusEnum);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public FSStatusEnum getStatus() {
        return this.status;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setStatus(FSStatusEnum fSStatusEnum) {
        this.status = fSStatusEnum;
    }
}
