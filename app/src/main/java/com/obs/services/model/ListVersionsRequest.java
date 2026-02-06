package com.obs.services.model;

public class ListVersionsRequest {
    private String bucketName;
    private String delimiter;
    private String keyMarker;
    private int listTimeout;
    private int maxKeys;
    private String prefix;
    private String versionIdMarker;

    public ListVersionsRequest() {
    }

    public ListVersionsRequest(String str) {
        this.bucketName = str;
    }

    public ListVersionsRequest(String str, int i) {
        this.bucketName = str;
        this.maxKeys = i;
    }

    public ListVersionsRequest(String str, String str2, String str3, String str4, int i) {
        this.bucketName = str;
        this.prefix = str2;
        this.keyMarker = str3;
        this.delimiter = str4;
        this.maxKeys = i;
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

    public int getListTimeout() {
        return this.listTimeout;
    }

    public int getMaxKeys() {
        return this.maxKeys;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getVersionIdMarker() {
        return this.versionIdMarker;
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

    public void setListTimeout(int i) {
        this.listTimeout = i;
    }

    public void setMaxKeys(int i) {
        this.maxKeys = i;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setVersionIdMarker(String str) {
        this.versionIdMarker = str;
    }

    public String toString() {
        return "ListVersionsRequest [bucketName=" + this.bucketName + ", prefix=" + this.prefix + ", keyMarker=" + this.keyMarker + ", versionIdMarker=" + this.versionIdMarker + ", maxKeys=" + this.maxKeys + ", delimiter=" + this.delimiter + ", listTimeout=" + this.listTimeout + "]";
    }
}
