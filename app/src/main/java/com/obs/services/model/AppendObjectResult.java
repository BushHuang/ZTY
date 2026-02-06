package com.obs.services.model;

public class AppendObjectResult extends HeaderResponse {
    private String bucketName;
    private String etag;
    private long nextPosition;
    private String objectKey;
    private String objectUrl;
    private StorageClassEnum storageClass;

    public AppendObjectResult(String str, String str2, String str3, long j, StorageClassEnum storageClassEnum, String str4) {
        this.nextPosition = -1L;
        this.bucketName = str;
        this.objectKey = str2;
        this.nextPosition = j;
        this.etag = str3;
        this.storageClass = storageClassEnum;
        this.objectUrl = str4;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getEtag() {
        return this.etag;
    }

    public long getNextPosition() {
        return this.nextPosition;
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

    @Override
    public String toString() {
        return "AppendObjectResult [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", etag=" + this.etag + ", nextPosition=" + this.nextPosition + ", storageClass=" + this.storageClass + ", objectUrl=" + this.objectUrl + "]";
    }
}
