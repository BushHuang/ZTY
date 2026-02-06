package com.obs.services.model;

public class AbortMultipartUploadRequest {
    private String bucketName;
    private String objectKey;
    private String uploadId;

    public AbortMultipartUploadRequest() {
    }

    public AbortMultipartUploadRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadId = str3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public String toString() {
        return "AbortMultipartUploadRequest [uploadId=" + this.uploadId + ", bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + "]";
    }
}
