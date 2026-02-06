package com.obs.services.model;

public class DownloadFileResult {
    private ObjectMetadata objectMetadata;

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    public String toString() {
        return "DownloadFileResult [objectMetadata=" + this.objectMetadata + "]";
    }
}
