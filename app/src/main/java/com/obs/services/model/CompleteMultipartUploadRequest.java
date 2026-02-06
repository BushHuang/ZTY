package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class CompleteMultipartUploadRequest {
    private String bucketName;
    private String objectKey;
    private List<PartEtag> partEtag;
    private String uploadId;

    public CompleteMultipartUploadRequest() {
    }

    public CompleteMultipartUploadRequest(String str, String str2, String str3, List<PartEtag> list) {
        this.uploadId = str3;
        this.bucketName = str;
        this.objectKey = str2;
        this.partEtag = list;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public List<PartEtag> getPartEtag() {
        if (this.partEtag == null) {
            this.partEtag = new ArrayList();
        }
        return this.partEtag;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setPartEtag(List<PartEtag> list) {
        this.partEtag = list;
    }

    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public String toString() {
        return "CompleteMultipartUploadRequest [uploadId=" + this.uploadId + ", bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", partEtag=" + this.partEtag + "]";
    }
}
