package com.obs.services.model;

import java.util.Date;

public class VersionOrDeleteMarker {
    private boolean appendable;
    private String bucketName;
    private String etag;
    private boolean isDeleteMarker;
    private boolean isLatest;
    private String key;
    private Date lastModified;
    private Owner owner;
    private long size;
    private StorageClassEnum storageClass;
    private String versionId;

    public VersionOrDeleteMarker(String str, String str2, String str3, boolean z, Date date, Owner owner, String str4, long j, StorageClassEnum storageClassEnum, boolean z2, boolean z3) {
        this.bucketName = str;
        this.key = str2;
        this.versionId = str3;
        this.isLatest = z;
        this.lastModified = date;
        this.owner = owner;
        this.etag = str4;
        this.size = j;
        this.storageClass = storageClassEnum;
        this.isDeleteMarker = z2;
        this.appendable = z3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getEtag() {
        return this.etag;
    }

    public String getKey() {
        return this.key;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public String getObjectKey() {
        return this.key;
    }

    public StorageClassEnum getObjectStorageClass() {
        return this.storageClass;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public long getSize() {
        return this.size;
    }

    @Deprecated
    public String getStorageClass() {
        StorageClassEnum storageClassEnum = this.storageClass;
        if (storageClassEnum != null) {
            return storageClassEnum.getCode();
        }
        return null;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public boolean isAppendable() {
        return this.appendable;
    }

    public boolean isDeleteMarker() {
        return this.isDeleteMarker;
    }

    public boolean isLatest() {
        return this.isLatest;
    }

    public String toString() {
        return "VersionOrDeleteMarker [bucketName=" + this.bucketName + ", key=" + this.key + ", versionId=" + this.versionId + ", isLatest=" + this.isLatest + ", lastModified=" + this.lastModified + ", owner=" + this.owner + ", etag=" + this.etag + ", size=" + this.size + ", storageClass=" + this.storageClass + ", isDeleteMarker=" + this.isDeleteMarker + ", appendable=" + this.appendable + "]";
    }
}
