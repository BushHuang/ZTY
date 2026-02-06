package com.obs.services.model;

public class InitiateMultipartUploadResult extends HeaderResponse {
    private String bucketName;
    private String objectKey;
    private String uploadId;

    public InitiateMultipartUploadResult(String str, String str2, String str3) {
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

    @Override
    public String toString() {
        return "InitiateMultipartUploadResult [uploadId=" + this.uploadId + ", bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + "]";
    }
}
