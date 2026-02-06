package com.obs.services.model.fs;

public class TruncateFileRequest {
    private String bucketName;
    private long newLength;
    private String objectKey;

    public TruncateFileRequest() {
    }

    public TruncateFileRequest(String str, String str2, long j) {
        this.bucketName = str;
        this.objectKey = str2;
        this.newLength = j;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public long getNewLength() {
        return this.newLength;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setNewLength(long j) {
        this.newLength = j;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }
}
