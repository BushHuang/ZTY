package com.obs.services.model;

import java.util.List;

public class BucketMetadataInfoRequest extends OptionsInfoRequest {
    protected String bucketName;
    private List<String> requestHeaders;

    public BucketMetadataInfoRequest() {
    }

    public BucketMetadataInfoRequest(String str) {
        this.bucketName = str;
    }

    public BucketMetadataInfoRequest(String str, String str2, List<String> list) {
        this.bucketName = str;
        setOrigin(str2);
        this.requestHeaders = list;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    @Override
    public String toString() {
        return "BucketMetadataInfoRequest [bucketName=" + this.bucketName + ", requestHeaders=" + this.requestHeaders + "]";
    }
}
