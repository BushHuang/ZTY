package com.obs.services.model;

public class BucketQuota extends HeaderResponse {
    private long bucketQuota;

    public BucketQuota() {
    }

    public BucketQuota(long j) {
        this.bucketQuota = j;
    }

    public long getBucketQuota() {
        return this.bucketQuota;
    }

    public void setBucketQuota(long j) {
        this.bucketQuota = j;
    }

    @Override
    public String toString() {
        return "BucketQuota [bucketQuota=" + this.bucketQuota + "]";
    }
}
