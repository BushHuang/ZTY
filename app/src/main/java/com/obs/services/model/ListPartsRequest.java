package com.obs.services.model;

public class ListPartsRequest {
    private String bucketName;
    private String key;
    private Integer maxParts;
    private Integer partNumberMarker;
    private String uploadId;

    public ListPartsRequest() {
    }

    public ListPartsRequest(String str, String str2) {
        this.bucketName = str;
        this.key = str2;
    }

    public ListPartsRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.key = str2;
        this.uploadId = str3;
    }

    public ListPartsRequest(String str, String str2, String str3, Integer num) {
        this.bucketName = str;
        this.key = str2;
        this.uploadId = str3;
        this.maxParts = num;
    }

    public ListPartsRequest(String str, String str2, String str3, Integer num, Integer num2) {
        this.bucketName = str;
        this.key = str2;
        this.uploadId = str3;
        this.maxParts = num;
        this.partNumberMarker = num2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getKey() {
        return this.key;
    }

    public Integer getMaxParts() {
        return this.maxParts;
    }

    public Integer getPartNumberMarker() {
        return this.partNumberMarker;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setMaxParts(Integer num) {
        this.maxParts = num;
    }

    public void setPartNumberMarker(Integer num) {
        this.partNumberMarker = num;
    }

    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public String toString() {
        return "ListPartsRequest [bucketName=" + this.bucketName + ", key=" + this.key + ", uploadId=" + this.uploadId + ", maxParts=" + this.maxParts + ", partNumberMarker=" + this.partNumberMarker + "]";
    }
}
