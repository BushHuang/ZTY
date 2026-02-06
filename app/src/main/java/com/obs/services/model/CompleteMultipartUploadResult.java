package com.obs.services.model;

public class CompleteMultipartUploadResult extends HeaderResponse {
    private String bucketName;
    private String etag;
    private String location;
    private String objectKey;
    private String objectUrl;
    private String versionId;

    public CompleteMultipartUploadResult(String str, String str2, String str3, String str4, String str5, String str6) {
        this.bucketName = str;
        this.objectKey = str2;
        this.etag = str3;
        this.location = str4;
        this.versionId = str5;
        this.objectUrl = str6;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getEtag() {
        return this.etag;
    }

    public String getLocation() {
        return this.location;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public String getObjectUrl() {
        return this.objectUrl;
    }

    public String getVersionId() {
        return this.versionId;
    }

    @Override
    public String toString() {
        return "CompleteMultipartUploadResult [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", etag=" + this.etag + ", location=" + this.location + ", versionId=" + this.versionId + ", objectUrl=" + this.objectUrl + "]";
    }
}
