package com.obs.services.model;

public class ListObjectsRequest {
    private String bucketName;
    private String delimiter;
    private int listTimeout;
    private String marker;
    private int maxKeys;
    private String prefix;

    public ListObjectsRequest() {
    }

    public ListObjectsRequest(String str) {
        this.bucketName = str;
    }

    public ListObjectsRequest(String str, int i) {
        this.bucketName = str;
        this.maxKeys = i;
    }

    public ListObjectsRequest(String str, String str2, String str3, String str4, int i) {
        this.bucketName = str;
        this.prefix = str2;
        this.marker = str3;
        this.delimiter = str4;
        this.maxKeys = i;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public int getListTimeout() {
        return this.listTimeout;
    }

    public String getMarker() {
        return this.marker;
    }

    public int getMaxKeys() {
        return this.maxKeys;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    public void setListTimeout(int i) {
        this.listTimeout = i;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public void setMaxKeys(int i) {
        this.maxKeys = i;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public String toString() {
        return "ListObjectsRequest [bucketName=" + this.bucketName + ", prefix=" + this.prefix + ", marker=" + this.marker + ", maxKeys=" + this.maxKeys + ", delimiter=" + this.delimiter + ", listTimeout=" + this.listTimeout + "]";
    }
}
