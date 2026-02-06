package com.obs.services.model;

public class DeleteObjectResult extends HeaderResponse {
    private boolean deleteMarker;
    private String objectKey;
    private String versionId;

    public DeleteObjectResult(boolean z, String str) {
        this.deleteMarker = z;
        this.versionId = str;
    }

    public DeleteObjectResult(boolean z, String str, String str2) {
        this.deleteMarker = z;
        this.objectKey = str;
        this.versionId = str2;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public boolean isDeleteMarker() {
        return this.deleteMarker;
    }

    @Override
    public String toString() {
        return "DeleteObjectResult [deleteMarker=" + this.deleteMarker + ", objectKey=" + this.objectKey + ", versionId=" + this.versionId + "]";
    }
}
