package com.obs.services.model;

import java.util.Date;

public class MultipartUpload {
    private String bucketName;
    private Date initiatedDate;
    private Owner initiator;
    private String objectKey;
    private Owner owner;
    private StorageClassEnum storageClass;
    private String uploadId;

    public MultipartUpload(String str, String str2, String str3, Date date, StorageClassEnum storageClassEnum, Owner owner, Owner owner2) {
        this.uploadId = str;
        this.bucketName = str2;
        this.objectKey = str3;
        this.initiatedDate = date;
        this.storageClass = storageClassEnum;
        this.owner = owner;
        this.initiator = owner2;
    }

    public MultipartUpload(String str, String str2, Date date, StorageClassEnum storageClassEnum, Owner owner, Owner owner2) {
        this.uploadId = str;
        this.objectKey = str2;
        this.initiatedDate = date;
        this.storageClass = storageClassEnum;
        this.owner = owner;
        this.initiator = owner2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public Date getInitiatedDate() {
        return this.initiatedDate;
    }

    public Owner getInitiator() {
        return this.initiator;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public StorageClassEnum getObjectStorageClass() {
        return this.storageClass;
    }

    public Owner getOwner() {
        return this.owner;
    }

    @Deprecated
    public String getStorageClass() {
        StorageClassEnum storageClassEnum = this.storageClass;
        if (storageClassEnum != null) {
            return storageClassEnum.getCode();
        }
        return null;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }
}
