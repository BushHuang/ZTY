package com.obs.services.model;

import java.util.Date;
import java.util.Map;

public class GetObjectRequest {
    private String bucketName;
    private CacheOptionEnum cacheOption;
    private String ifMatchTag;
    private Date ifModifiedSince;
    private String ifNoneMatchTag;
    private Date ifUnmodifiedSince;
    private String imageProcess;
    private String objectKey;
    private long progressInterval = 102400;
    private ProgressListener progressListener;
    private Long rangeEnd;
    private Long rangeStart;
    private ObjectRepleaceMetadata replaceMetadata;
    private Map<String, String> requestParameters;
    private SseCHeader sseCHeader;
    private long ttl;
    private String versionId;

    public GetObjectRequest() {
    }

    public GetObjectRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public GetObjectRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.versionId = str3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public CacheOptionEnum getCacheOption() {
        return this.cacheOption;
    }

    public String getIfMatchTag() {
        return this.ifMatchTag;
    }

    public Date getIfModifiedSince() {
        return this.ifModifiedSince;
    }

    public String getIfNoneMatchTag() {
        return this.ifNoneMatchTag;
    }

    public Date getIfUnmodifiedSince() {
        return this.ifUnmodifiedSince;
    }

    public String getImageProcess() {
        return this.imageProcess;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public long getProgressInterval() {
        return this.progressInterval;
    }

    public ProgressListener getProgressListener() {
        return this.progressListener;
    }

    public Long getRangeEnd() {
        return this.rangeEnd;
    }

    public Long getRangeStart() {
        return this.rangeStart;
    }

    public ObjectRepleaceMetadata getReplaceMetadata() {
        return this.replaceMetadata;
    }

    public Map<String, String> getRequestParameters() {
        return this.requestParameters;
    }

    public SseCHeader getSseCHeader() {
        return this.sseCHeader;
    }

    public long getTtl() {
        return this.ttl;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCacheOption(CacheOptionEnum cacheOptionEnum) {
        this.cacheOption = cacheOptionEnum;
    }

    public void setIfMatchTag(String str) {
        this.ifMatchTag = str;
    }

    public void setIfModifiedSince(Date date) {
        this.ifModifiedSince = date;
    }

    public void setIfNoneMatchTag(String str) {
        this.ifNoneMatchTag = str;
    }

    public void setIfUnmodifiedSince(Date date) {
        this.ifUnmodifiedSince = date;
    }

    public void setImageProcess(String str) {
        this.imageProcess = str;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setProgressInterval(long j) {
        this.progressInterval = j;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public void setRangeEnd(Long l) {
        this.rangeEnd = l;
    }

    public void setRangeStart(Long l) {
        this.rangeStart = l;
    }

    public void setReplaceMetadata(ObjectRepleaceMetadata objectRepleaceMetadata) {
        this.replaceMetadata = objectRepleaceMetadata;
    }

    public void setRequestParameters(Map<String, String> map) {
        this.requestParameters = map;
    }

    public void setSseCHeader(SseCHeader sseCHeader) {
        this.sseCHeader = sseCHeader;
    }

    public void setTtl(long j) {
        if (j < 0 || j > 259200) {
            j = 86400;
        }
        this.ttl = j;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        return "GetObjectRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", rangeStart=" + this.rangeStart + ", rangeEnd=" + this.rangeEnd + ", versionId=" + this.versionId + ", replaceMetadata=" + this.replaceMetadata + ", sseCHeader=" + this.sseCHeader + ", ifModifiedSince=" + this.ifModifiedSince + ", ifUnmodifiedSince=" + this.ifUnmodifiedSince + ", ifMatchTag=" + this.ifMatchTag + ", ifNoneMatchTag=" + this.ifNoneMatchTag + ", imageProcess=" + this.imageProcess + "]";
    }
}
