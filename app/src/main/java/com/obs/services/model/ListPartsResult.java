package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class ListPartsResult extends HeaderResponse {
    private String bucket;
    private Owner initiator;
    private boolean isTruncated;
    private String key;
    private Integer maxParts;
    private List<Multipart> multipartList;
    private String nextPartNumberMarker;
    private Owner owner;
    private String partNumberMarker;
    private StorageClassEnum storageClass;
    private String uploadId;

    public ListPartsResult(String str, String str2, String str3, Owner owner, Owner owner2, StorageClassEnum storageClassEnum, List<Multipart> list, Integer num, boolean z, String str4, String str5) {
        this.bucket = str;
        this.key = str2;
        this.uploadId = str3;
        this.initiator = owner;
        this.owner = owner2;
        this.storageClass = storageClassEnum;
        this.multipartList = list;
        this.maxParts = num;
        this.isTruncated = z;
        this.partNumberMarker = str4;
        this.nextPartNumberMarker = str5;
    }

    public String getBucket() {
        return this.bucket;
    }

    public Owner getInitiator() {
        return this.initiator;
    }

    public String getKey() {
        return this.key;
    }

    public Integer getMaxParts() {
        return this.maxParts;
    }

    public List<Multipart> getMultipartList() {
        if (this.multipartList == null) {
            this.multipartList = new ArrayList();
        }
        return this.multipartList;
    }

    public String getNextPartNumberMarker() {
        return this.nextPartNumberMarker;
    }

    public StorageClassEnum getObjectStorageClass() {
        return this.storageClass;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public String getPartNumberMarker() {
        return this.partNumberMarker;
    }

    @Deprecated
    public String getStorageClass() {
        StorageClassEnum storageClassEnum = this.storageClass;
        if (storageClassEnum == null) {
            return null;
        }
        return storageClassEnum.getCode();
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    @Override
    public String toString() {
        return "ListPartsResult [bucket=" + this.bucket + ", key=" + this.key + ", uploadId=" + this.uploadId + ", initiator=" + this.initiator + ", owner=" + this.owner + ", storageClass=" + this.storageClass + ", multipartList=" + this.multipartList + ", maxParts=" + this.maxParts + ", isTruncated=" + this.isTruncated + ", partNumberMarker=" + this.partNumberMarker + ", nextPartNumberMarker=" + this.nextPartNumberMarker + "]";
    }
}
