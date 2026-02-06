package com.obs.services.model;

import java.io.InputStream;

public class ObsObject extends S3Object {
    @Override
    public String getBucketName() {
        return this.bucketName;
    }

    @Override
    public ObjectMetadata getMetadata() {
        if (this.metadata == null) {
            this.metadata = new ObjectMetadata();
        }
        return this.metadata;
    }

    @Override
    public InputStream getObjectContent() {
        return this.objectContent;
    }

    @Override
    public String getObjectKey() {
        return this.objectKey;
    }

    @Override
    public Owner getOwner() {
        return this.owner;
    }

    @Override
    public void setBucketName(String str) {
        this.bucketName = str;
    }

    @Override
    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    @Override
    public void setObjectContent(InputStream inputStream) {
        this.objectContent = inputStream;
    }

    @Override
    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    @Override
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ObsObject [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", owner=" + this.owner + ", metadata=" + this.metadata + ", objectContent=" + this.objectContent + "]";
    }
}
