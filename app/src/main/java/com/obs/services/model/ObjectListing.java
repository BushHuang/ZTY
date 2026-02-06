package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectListing extends HeaderResponse {
    private String bucketName;
    private List<String> commonPrefixes;
    private String delimiter;
    private String location;
    private String marker;
    private int maxKeys;
    private String nextMarker;
    private List<ObsObject> objectSummaries;
    private String prefix;
    private boolean truncated;

    public ObjectListing(List<ObsObject> list, List<String> list2, String str, boolean z, String str2, String str3, int i, String str4, String str5, String str6) {
        this.objectSummaries = list;
        this.commonPrefixes = list2;
        this.bucketName = str;
        this.truncated = z;
        this.prefix = str2;
        this.marker = str3;
        this.maxKeys = i;
        this.delimiter = str4;
        this.nextMarker = str5;
        this.location = str6;
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

    public String getLocation() {
        return this.location;
    }

    public String getMarker() {
        return this.marker;
    }

    public int getMaxKeys() {
        return this.maxKeys;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    @Deprecated
    public List<S3Object> getObjectSummaries() {
        ArrayList arrayList = new ArrayList(this.objectSummaries.size());
        arrayList.addAll(this.objectSummaries);
        return arrayList;
    }

    public List<ObsObject> getObjects() {
        if (this.objectSummaries == null) {
            this.objectSummaries = new ArrayList();
        }
        return this.objectSummaries;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public boolean isTruncated() {
        return this.truncated;
    }

    @Override
    public String toString() {
        return "ObjectListing [objectSummaries=" + this.objectSummaries + ", commonPrefixes=" + this.commonPrefixes + ", bucketName=" + this.bucketName + ", truncated=" + this.truncated + ", prefix=" + this.prefix + ", marker=" + this.marker + ", maxKeys=" + this.maxKeys + ", delimiter=" + this.delimiter + ", nextMarker=" + this.nextMarker + ", location=" + this.location + "]";
    }
}
