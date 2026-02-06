package com.obs.services.model;

import java.util.List;

public class BucketMetadataInfoResult extends OptionsInfoResult {
    private AvailableZoneEnum availableZone;
    private String epid;
    private String location;
    private String obsVersion;
    private StorageClassEnum storageClass;

    public BucketMetadataInfoResult(String str, List<String> list, int i, List<String> list2, List<String> list3, StorageClassEnum storageClassEnum, String str2, String str3) {
        super(str, list, i, list2, list3);
        this.storageClass = storageClassEnum;
        this.location = str2;
        this.obsVersion = str3;
    }

    public BucketMetadataInfoResult(String str, List<String> list, int i, List<String> list2, List<String> list3, StorageClassEnum storageClassEnum, String str2, String str3, AvailableZoneEnum availableZoneEnum) {
        this(str, list, i, list2, list3, storageClassEnum, str2, str3);
        this.availableZone = availableZoneEnum;
    }

    public BucketMetadataInfoResult(String str, List<String> list, int i, List<String> list2, List<String> list3, StorageClassEnum storageClassEnum, String str2, String str3, AvailableZoneEnum availableZoneEnum, String str4) {
        this(str, list, i, list2, list3, storageClassEnum, str2, str3);
        this.availableZone = availableZoneEnum;
        this.epid = str4;
    }

    public AvailableZoneEnum getAvailableZone() {
        return this.availableZone;
    }

    public StorageClassEnum getBucketStorageClass() {
        return this.storageClass;
    }

    @Deprecated
    public String getDefaultStorageClass() {
        StorageClassEnum storageClassEnum = this.storageClass;
        if (storageClassEnum == null) {
            return null;
        }
        return storageClassEnum.getCode();
    }

    public String getEpid() {
        return this.epid;
    }

    public String getLocation() {
        return this.location;
    }

    public String getObsVersion() {
        return this.obsVersion;
    }

    @Override
    public String toString() {
        return "BucketMetadataInfoResult [storageClass=" + this.storageClass + ", location=" + this.location + ", obsVersion=" + this.obsVersion + "]";
    }
}
