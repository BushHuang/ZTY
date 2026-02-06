package com.obs.services.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ObsBucket extends S3Bucket {
    public ObsBucket() {
    }

    public ObsBucket(String str, String str2) {
        this.bucketName = str;
        this.location = str2;
    }

    @Override
    public AccessControlList getAcl() {
        return this.acl;
    }

    @Override
    public String getBucketName() {
        return this.bucketName;
    }

    @Override
    public StorageClassEnum getBucketStorageClass() {
        return this.storageClass;
    }

    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    @Deprecated
    public Map<String, Object> getMetadata() {
        if (this.metadata == null) {
            this.metadata = new HashMap();
        }
        return this.metadata;
    }

    @Override
    public Owner getOwner() {
        return this.owner;
    }

    @Override
    @Deprecated
    public String getStorageClass() {
        if (this.storageClass != null) {
            return this.storageClass.getCode();
        }
        return null;
    }

    @Override
    public void setAcl(AccessControlList accessControlList) {
        this.acl = accessControlList;
    }

    @Override
    public void setBucketName(String str) {
        this.bucketName = str;
    }

    @Override
    public void setBucketStorageClass(StorageClassEnum storageClassEnum) {
        this.storageClass = storageClassEnum;
    }

    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public void setLocation(String str) {
        this.location = str;
    }

    @Override
    @Deprecated
    public void setMetadata(Map<String, Object> map) {
        this.metadata = map;
    }

    @Override
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    @Deprecated
    public void setStorageClass(String str) {
        this.storageClass = StorageClassEnum.getValueFromCode(str);
    }

    @Override
    public String toString() {
        return "ObsBucket [bucketName=" + this.bucketName + ", owner=" + this.owner + ", creationDate=" + this.creationDate + ", location=" + this.location + ", storageClass=" + this.storageClass + ", metadata=" + this.metadata + ", acl=" + this.acl + "]";
    }
}
