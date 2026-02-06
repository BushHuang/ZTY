package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class DeleteObjectsResult extends HeaderResponse {
    private List<DeleteObjectResult> deletedObjectResults;
    private List<ErrorResult> errorResults;

    public class DeleteObjectResult {
        private boolean deleteMarker;
        private String deleteMarkerVersion;
        private String objectKey;
        private String version;

        public DeleteObjectResult(String str, String str2, boolean z, String str3) {
            this.objectKey = str;
            this.version = str2;
            this.deleteMarker = z;
            this.deleteMarkerVersion = str3;
        }

        public String getDeleteMarkerVersion() {
            return this.deleteMarkerVersion;
        }

        public String getObjectKey() {
            return this.objectKey;
        }

        public String getVersion() {
            return this.version;
        }

        public boolean isDeleteMarker() {
            return this.deleteMarker;
        }

        public String toString() {
            return "DeleteObjectResult [objectKey=" + this.objectKey + ", version=" + this.version + ", deleteMarker=" + this.deleteMarker + ", deleteMarkerVersion=" + this.deleteMarkerVersion + "]";
        }
    }

    public class ErrorResult {
        private String errorCode;
        private String message;
        private String objectKey;
        private String version;

        public ErrorResult(String str, String str2, String str3, String str4) {
            this.objectKey = str;
            this.version = str2;
            this.errorCode = str3;
            this.message = str4;
        }

        public String getErrorCode() {
            return this.errorCode;
        }

        public String getMessage() {
            return this.message;
        }

        public String getObjectKey() {
            return this.objectKey;
        }

        public String getVersion() {
            return this.version;
        }

        public String toString() {
            return "ErrorResult [objectKey=" + this.objectKey + ", version=" + this.version + ", errorCode=" + this.errorCode + ", message=" + this.message + "]";
        }
    }

    public DeleteObjectsResult() {
    }

    public DeleteObjectsResult(List<DeleteObjectResult> list, List<ErrorResult> list2) {
        this.deletedObjectResults = list;
        this.errorResults = list2;
    }

    public List<DeleteObjectResult> getDeletedObjectResults() {
        if (this.deletedObjectResults == null) {
            this.deletedObjectResults = new ArrayList();
        }
        return this.deletedObjectResults;
    }

    public List<ErrorResult> getErrorResults() {
        if (this.errorResults == null) {
            this.errorResults = new ArrayList();
        }
        return this.errorResults;
    }

    @Override
    public String toString() {
        return "DeleteObjectsResult [deletedObjectResults=" + this.deletedObjectResults + ", errorResults=" + this.errorResults + "]";
    }
}
