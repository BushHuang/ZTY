package com.obs.services.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class S3Bucket extends HeaderResponse {

    @Deprecated
    public static final String GLACIER = "GLACIER";

    @Deprecated
    public static final String STANDARD = "STANDARD";

    @Deprecated
    public static final String STANDARD_IA = "STANDARD_IA";
    protected AccessControlList acl;
    protected String bucketName;
    protected Date creationDate;
    protected String location;
    protected Map<String, Object> metadata = new HashMap();
    protected Owner owner;
    protected StorageClassEnum storageClass;

    public S3Bucket() {
    }

    public S3Bucket(String str, String str2) {
        this.bucketName = str;
        this.location = str2;
    }

    public AccessControlList getAcl() {
        return this.acl;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public StorageClassEnum getBucketStorageClass() {
        return this.storageClass;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getLocation() {
        return this.location;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
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

    public void setAcl(AccessControlList accessControlList) {
        this.acl = accessControlList;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setBucketStorageClass(StorageClassEnum storageClassEnum) {
        this.storageClass = storageClassEnum;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setMetadata(Map<String, Object> map) {
        this.metadata.putAll(map);
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Deprecated
    public void setStorageClass(String str) {
        this.storageClass = StorageClassEnum.getValueFromCode(str);
    }

    @Override
    public String toString() {
        return "ObsBucket [bucketName=" + this.bucketName + ", owner=" + this.owner + ", creationDate=" + this.creationDate + ", location=" + this.location + ", storageClass=" + this.storageClass + ", metadata=" + this.metadata + ", acl=" + this.acl + "]";
    }
}
