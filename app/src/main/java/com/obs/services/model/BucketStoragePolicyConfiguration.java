package com.obs.services.model;

public class BucketStoragePolicyConfiguration extends HeaderResponse {
    private StorageClassEnum storageClass;

    public BucketStoragePolicyConfiguration() {
    }

    public BucketStoragePolicyConfiguration(StorageClassEnum storageClassEnum) {
        this.storageClass = storageClassEnum;
    }

    @Deprecated
    public BucketStoragePolicyConfiguration(String str) {
        this.storageClass = StorageClassEnum.getValueFromCode(str);
    }

    public StorageClassEnum getBucketStorageClass() {
        return this.storageClass;
    }

    @Deprecated
    public String getStorageClass() {
        StorageClassEnum storageClassEnum = this.storageClass;
        if (storageClassEnum != null) {
            return storageClassEnum.getCode();
        }
        return null;
    }

    public void setBucketStorageClass(StorageClassEnum storageClassEnum) {
        this.storageClass = storageClassEnum;
    }

    @Deprecated
    public void setStorageClass(String str) {
        this.storageClass = StorageClassEnum.getValueFromCode(str);
    }

    @Override
    public String toString() {
        return "BucketStoragePolicyConfiguration [storageClass=" + this.storageClass + "]";
    }
}
