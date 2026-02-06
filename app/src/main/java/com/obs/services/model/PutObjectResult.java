package com.obs.services.model;

import java.util.Map;

public class PutObjectResult extends HeaderResponse {
    private String bucketName;
    private String etag;
    private String objectKey;
    private String objectUrl;
    private StorageClassEnum storageClass;
    private String versionId;

    public PutObjectResult(String str, String str2, String str3, String str4, StorageClassEnum storageClassEnum, String str5) {
        this.bucketName = str;
        this.objectKey = str2;
        this.etag = str3;
        this.versionId = str4;
        this.storageClass = storageClassEnum;
        this.objectUrl = str5;
    }

    public PutObjectResult(String str, String str2, String str3, String str4, String str5, Map<String, Object> map, int i) {
        this.bucketName = str;
        this.objectKey = str2;
        this.etag = str3;
        this.versionId = str4;
        this.objectUrl = str5;
        this.responseHeaders = map;
        this.statusCode = i;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getEtag() {
        return this.etag;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public StorageClassEnum getObjectStorageClass() {
        return this.storageClass;
    }

    public String getObjectUrl() {
        return this.objectUrl;
    }

    public String getVersionId() {
        return this.versionId;
    }

    @Override
    public String toString() {
        return "PutObjectResult [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", etag=" + this.etag + ", versionId=" + this.versionId + ", storageClass=" + this.storageClass + ", objectUrl=" + this.objectUrl + "]";
    }
}
