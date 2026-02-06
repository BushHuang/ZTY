package com.obs.services.model;

public class ListMultipartUploadsRequest {
    private String bucketName;
    private String delimiter;
    private String keyMarker;
    private Integer maxUploads;
    private String prefix;
    private String uploadIdMarker;

    public ListMultipartUploadsRequest() {
    }

    public ListMultipartUploadsRequest(String str) {
        this.bucketName = str;
    }

    public ListMultipartUploadsRequest(String str, Integer num) {
        this.bucketName = str;
        this.maxUploads = num;
    }

    public ListMultipartUploadsRequest(String str, String str2, String str3, Integer num, String str4, String str5) {
        this.bucketName = str;
        this.prefix = str2;
        this.delimiter = str3;
        this.maxUploads = num;
        this.keyMarker = str4;
        this.uploadIdMarker = str5;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public String getKeyMarker() {
        return this.keyMarker;
    }

    public Integer getMaxUploads() {
        return this.maxUploads;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getUploadIdMarker() {
        return this.uploadIdMarker;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    public void setKeyMarker(String str) {
        this.keyMarker = str;
    }

    public void setMaxUploads(Integer num) {
        this.maxUploads = num;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setUploadIdMarker(String str) {
        this.uploadIdMarker = str;
    }

    public String toString() {
        return "ListMultipartUploadsRequest [bucketName=" + this.bucketName + ", prefix=" + this.prefix + ", delimiter=" + this.delimiter + ", maxUploads=" + this.maxUploads + ", keyMarker=" + this.keyMarker + ", uploadIdMarker=" + this.uploadIdMarker + "]";
    }
}
