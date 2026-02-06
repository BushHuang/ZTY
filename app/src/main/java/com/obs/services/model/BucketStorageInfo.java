package com.obs.services.model;

public class BucketStorageInfo extends HeaderResponse {
    private long objectNum;
    private long size;

    public long getObjectNumber() {
        return this.objectNum;
    }

    public long getSize() {
        return this.size;
    }

    public void setObjectNumber(long j) {
        this.objectNum = j;
    }

    public void setSize(long j) {
        this.size = j;
    }

    @Override
    public String toString() {
        return "BucketStorageInfo [size=" + this.size + ", objectNum=" + this.objectNum + "]";
    }
}
