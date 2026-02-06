package com.obs.services.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipartUploadListing extends HeaderResponse {
    private String bucketName;
    private String[] commonPrefixes;
    private String delimiter;
    private String keyMarker;
    private int maxUploads;
    private List<MultipartUpload> multipartTaskList;
    private String nextKeyMarker;
    private String nextUploadIdMarker;
    private String prefix;
    private boolean truncated;
    private String uploadIdMarker;

    public MultipartUploadListing(String str, String str2, String str3, String str4, String str5, String str6, int i, boolean z, List<MultipartUpload> list, String str7, String[] strArr) {
        this.bucketName = str;
        this.keyMarker = str2;
        this.uploadIdMarker = str3;
        this.nextKeyMarker = str4;
        this.nextUploadIdMarker = str5;
        this.prefix = str6;
        this.maxUploads = i;
        this.truncated = z;
        this.multipartTaskList = list;
        this.delimiter = str7;
        this.commonPrefixes = strArr;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String[] getCommonPrefixes() {
        return this.commonPrefixes;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public String getKeyMarker() {
        return this.keyMarker;
    }

    public int getMaxUploads() {
        return this.maxUploads;
    }

    public List<MultipartUpload> getMultipartTaskList() {
        if (this.multipartTaskList == null) {
            this.multipartTaskList = new ArrayList();
        }
        return this.multipartTaskList;
    }

    public String getNextKeyMarker() {
        return this.nextKeyMarker;
    }

    public String getNextUploadIdMarker() {
        return this.nextUploadIdMarker;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getUploadIdMarker() {
        return this.uploadIdMarker;
    }

    public boolean isTruncated() {
        return this.truncated;
    }

    @Override
    public String toString() {
        return "MultipartUploadListing [bucketName=" + this.bucketName + ", keyMarker=" + this.keyMarker + ", uploadIdMarker=" + this.uploadIdMarker + ", nextKeyMarker=" + this.nextKeyMarker + ", nextUploadIdMarker=" + this.nextUploadIdMarker + ", prefix=" + this.prefix + ", maxUploads=" + this.maxUploads + ", truncated=" + this.truncated + ", multipartTaskList=" + this.multipartTaskList + ", delimiter=" + this.delimiter + ", commonPrefixes=" + Arrays.toString(this.commonPrefixes) + "]";
    }
}
