package com.obs.services.model;

public class RestoreObjectResult extends HeaderResponse {
    private String bucketName;
    private String objectKey;
    private String versionId;

    public RestoreObjectResult(String str, String str2, String str3) {
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
}
