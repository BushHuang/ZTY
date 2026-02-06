package com.obs.services.model;

public class GetObjectMetadataRequest {
    private String bucketName;
    private String objectKey;
    private SseCHeader sseCHeader;
    private String versionId;

    public GetObjectMetadataRequest() {
    }

    public GetObjectMetadataRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public GetObjectMetadataRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.versionId = str3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public SseCHeader getSseCHeader() {
        return this.sseCHeader;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setSseCHeader(SseCHeader sseCHeader) {
        this.sseCHeader = sseCHeader;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        return "GetObjectMetadataRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", versionId=" + this.versionId + ", sseCHeader=" + this.sseCHeader + "]";
    }
}
