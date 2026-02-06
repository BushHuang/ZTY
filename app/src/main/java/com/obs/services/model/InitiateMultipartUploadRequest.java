package com.obs.services.model;

public class InitiateMultipartUploadRequest extends PutObjectBasicRequest {
    private int expires;
    private ObjectMetadata metadata;

    public InitiateMultipartUploadRequest() {
    }

    public InitiateMultipartUploadRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    @Override
    public String getBucketName() {
        return this.bucketName;
    }

    public int getExpires() {
        return this.expires;
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    @Override
    public String getObjectKey() {
        return this.objectKey;
    }

    @Deprecated
    public String getWebSiteRedirectLocation() {
        ObjectMetadata objectMetadata = this.metadata;
        if (objectMetadata != null) {
            return objectMetadata.getWebSiteRedirectLocation();
        }
        return null;
    }

    @Override
    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setExpires(int i) {
        this.expires = i;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    @Override
    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    @Deprecated
    public void setWebSiteRedirectLocation(String str) {
        ObjectMetadata objectMetadata = this.metadata;
        if (objectMetadata != null) {
            objectMetadata.setWebSiteRedirectLocation(str);
        }
    }

    public String toString() {
        return "InitiateMultipartUploadRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", acl=" + this.acl + ", sseKmsHeader=" + this.sseKmsHeader + ", sseCHeader=" + this.sseCHeader + ", metadata=" + this.metadata + ", expires=" + this.expires + "]";
    }
}
