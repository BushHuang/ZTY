package com.obs.services.model;

import java.io.InputStream;

@Deprecated
public class S3Object {

    @Deprecated
    public static final String GLACIER = "GLACIER";

    @Deprecated
    public static final String STANDARD = "STANDARD";

    @Deprecated
    public static final String STANDARD_IA = "STANDARD_IA";
    protected String bucketName;
    protected ObjectMetadata metadata;
    protected InputStream objectContent;
    protected String objectKey;
    protected Owner owner;

    public String getBucketName() {
        return this.bucketName;
    }

    public ObjectMetadata getMetadata() {
        if (this.metadata == null) {
            this.metadata = new ObjectMetadata();
        }
        return this.metadata;
    }

    public InputStream getObjectContent() {
        return this.objectContent;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    public void setObjectContent(InputStream inputStream) {
        this.objectContent = inputStream;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String toString() {
        return "ObsObject [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", owner=" + this.owner + ", metadata=" + this.metadata + ", objectContent=" + this.objectContent + "]";
    }
}
