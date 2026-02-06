package com.obs.services.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListVersionsResult extends HeaderResponse {
    private String bucketName;
    private List<String> commonPrefixes;
    private String delimiter;
    private boolean isAppendable;
    private boolean isTruncated;
    private String keyMarker;
    private String location;
    private String maxKeys;
    private String nextKeyMarker;
    private String nextVersionIdMarker;
    private String prefix;
    private String versionIdMarker;
    private VersionOrDeleteMarker[] versions;

    public ListVersionsResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, VersionOrDeleteMarker[] versionOrDeleteMarkerArr, List<String> list, String str8, String str9) {
        this.bucketName = str;
        this.prefix = str2;
        this.keyMarker = str3;
        this.nextKeyMarker = str4;
        this.versionIdMarker = str5;
        this.nextVersionIdMarker = str6;
        this.maxKeys = str7;
        this.isTruncated = z;
        this.versions = versionOrDeleteMarkerArr;
        this.commonPrefixes = list;
        this.location = str8;
        this.delimiter = str9;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public List<String> getCommonPrefixes() {
        if (this.commonPrefixes == null) {
            this.commonPrefixes = new ArrayList();
        }
        return this.commonPrefixes;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public String getKeyMarker() {
        return this.keyMarker;
    }

    public String getLocation() {
        return this.location;
    }

    public String getMaxKeys() {
        return this.maxKeys;
    }

    public String getNextKeyMarker() {
        return this.nextKeyMarker;
    }

    public String getNextVersionIdMarker() {
        return this.nextVersionIdMarker;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getVersionIdMarker() {
        return this.versionIdMarker;
    }

    public VersionOrDeleteMarker[] getVersions() {
        return this.versions;
    }

    public boolean isAppendable() {
        return this.isAppendable;
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    @Override
    public String toString() {
        return "ListVersionsResult [bucketName=" + this.bucketName + ", prefix=" + this.prefix + ", keyMarker=" + this.keyMarker + ", nextKeyMarker=" + this.nextKeyMarker + ", versionIdMarker=" + this.versionIdMarker + ", nextVersionIdMarker=" + this.nextVersionIdMarker + ", maxKeys=" + this.maxKeys + ", isTruncated=" + this.isTruncated + ", versions=" + Arrays.toString(this.versions) + ", commonPrefixes=" + this.commonPrefixes + ", location=" + this.location + ", delimiter=" + this.delimiter + "]";
    }
}
