package com.obs.services.model.fs;

public class RenameRequest {
    private String bucketName;
    private String newObjectKey;
    private String objectKey;

    public RenameRequest() {
    }

    public RenameRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.newObjectKey = str3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getNewObjectKey() {
        return this.newObjectKey;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setNewObjectKey(String str) {
        this.newObjectKey = str;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }
}
