package com.obs.services.model;

public class CopyPartRequest {
    private Long byteRangeEnd;
    private Long byteRangeStart;
    private String destinationBucketName;
    private String destinationObjectKey;
    private int partNumber;
    private String sourceBucketName;
    private String sourceObjectKey;
    private SseCHeader sseCHeaderDestination;
    private SseCHeader sseCHeaderSource;
    private String uploadId;
    private String versionId;

    public CopyPartRequest() {
    }

    public CopyPartRequest(String str, String str2, String str3, String str4, String str5, int i) {
        this.uploadId = str;
        this.sourceBucketName = str2;
        this.sourceObjectKey = str3;
        this.destinationBucketName = str4;
        this.destinationObjectKey = str5;
        this.partNumber = i;
    }

    public Long getByteRangeEnd() {
        return this.byteRangeEnd;
    }

    public Long getByteRangeStart() {
        return this.byteRangeStart;
    }

    public String getDestinationBucketName() {
        return this.destinationBucketName;
    }

    public String getDestinationObjectKey() {
        return this.destinationObjectKey;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public String getSourceBucketName() {
        return this.sourceBucketName;
    }

    public String getSourceObjectKey() {
        return this.sourceObjectKey;
    }

    public SseCHeader getSseCHeaderDestination() {
        return this.sseCHeaderDestination;
    }

    public SseCHeader getSseCHeaderSource() {
        return this.sseCHeaderSource;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setByteRangeEnd(Long l) {
        this.byteRangeEnd = l;
    }

    public void setByteRangeStart(Long l) {
        this.byteRangeStart = l;
    }

    public void setDestinationBucketName(String str) {
        this.destinationBucketName = str;
    }

    public void setDestinationObjectKey(String str) {
        this.destinationObjectKey = str;
    }

    public void setPartNumber(int i) {
        this.partNumber = i;
    }

    public void setSourceBucketName(String str) {
        this.sourceBucketName = str;
    }

    public void setSourceObjectKey(String str) {
        this.sourceObjectKey = str;
    }

    public void setSseCHeaderDestination(SseCHeader sseCHeader) {
        this.sseCHeaderDestination = sseCHeader;
    }

    public void setSseCHeaderSource(SseCHeader sseCHeader) {
        this.sseCHeaderSource = sseCHeader;
    }

    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        return "CopyPartRequest [uploadId=" + this.uploadId + ", sourceBucketName=" + this.sourceBucketName + ", sourceObjectKey=" + this.sourceObjectKey + ", destinationBucketName=" + this.destinationBucketName + ", destinationObjectKey=" + this.destinationObjectKey + ", byteRangeStart=" + this.byteRangeStart + ", byteRangeEnd=" + this.byteRangeEnd + ", sseCHeaderSource=" + this.sseCHeaderSource + ", sseCHeaderDestination=" + this.sseCHeaderDestination + ", versionId=" + this.versionId + ", partNumber=" + this.partNumber + "]";
    }
}
