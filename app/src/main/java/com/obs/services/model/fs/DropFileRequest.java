package com.obs.services.model.fs;

public class DropFileRequest {
    private String bucketName;
    private String objectKey;
    private String versionId;

    public DropFileRequest() {
    }

    public DropFileRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public DropFileRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.versionId = str3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        return "DropFileRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", versionId=" + this.versionId + "]";
    }
}
