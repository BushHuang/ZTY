package com.obs.services.model;

import java.util.Date;

public class CopyObjectRequest extends PutObjectBasicRequest {
    private String ifMatchTag;
    private Date ifModifiedSince;
    private String ifNoneMatchTag;
    private Date ifUnmodifiedSince;
    private ObjectMetadata newObjectMetadata;
    private boolean replaceMetadata;
    private String sourceBucketName;
    private String sourceObjectKey;
    private SseCHeader sseCHeaderSource;
    private String versionId;

    public CopyObjectRequest() {
    }

    public CopyObjectRequest(String str, String str2, String str3, String str4) {
        this.sourceBucketName = str;
        this.sourceObjectKey = str2;
        this.bucketName = str3;
        this.objectKey = str4;
    }

    public String getDestinationBucketName() {
        return this.bucketName;
    }

    public String getDestinationObjectKey() {
        return this.objectKey;
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

    public ObjectMetadata getNewObjectMetadata() {
        return this.newObjectMetadata;
    }

    public String getSourceBucketName() {
        return this.sourceBucketName;
    }

    public String getSourceObjectKey() {
        return this.sourceObjectKey;
    }

    @Deprecated
    public SseCHeader getSseCHeaderDestination() {
        return this.sseCHeader;
    }

    public SseCHeader getSseCHeaderSource() {
        return this.sseCHeaderSource;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public boolean isReplaceMetadata() {
        return this.replaceMetadata;
    }

    public void setDestinationBucketName(String str) {
        this.bucketName = str;
    }

    public void setDestinationObjectKey(String str) {
        this.objectKey = str;
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

    public void setNewObjectMetadata(ObjectMetadata objectMetadata) {
        this.newObjectMetadata = objectMetadata;
    }

    public void setReplaceMetadata(boolean z) {
        this.replaceMetadata = z;
    }

    public void setSourceBucketName(String str) {
        this.sourceBucketName = str;
    }

    public void setSourceObjectKey(String str) {
        this.sourceObjectKey = str;
    }

    @Deprecated
    public void setSseCHeaderDestination(SseCHeader sseCHeader) {
        this.sseCHeader = sseCHeader;
    }

    public void setSseCHeaderSource(SseCHeader sseCHeader) {
        this.sseCHeaderSource = sseCHeader;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        return "CopyObjectRequest [sourceBucketName=" + this.sourceBucketName + ", sourceObjectKey=" + this.sourceObjectKey + ", destinationBucketName=" + this.bucketName + ", destinationObjectKey=" + this.objectKey + ", newObjectMetadata=" + this.newObjectMetadata + ", replaceMetadata=" + this.replaceMetadata + ", ifModifiedSince=" + this.ifModifiedSince + ", ifUnmodifiedSince=" + this.ifUnmodifiedSince + ", ifMatchTag=" + this.ifMatchTag + ", ifNoneMatchTag=" + this.ifNoneMatchTag + ", versionId=" + this.versionId + ", sseKmsHeader=" + this.sseKmsHeader + ", sseCHeaderSource=" + this.sseCHeaderSource + ", sseCHeaderDestination=" + this.sseCHeader + ", acl=" + this.acl + ", successRedirectLocation=" + this.successRedirectLocation + "]";
    }
}
