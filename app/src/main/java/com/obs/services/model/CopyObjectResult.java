package com.obs.services.model;

import java.util.Date;

public class CopyObjectResult extends HeaderResponse {
    private String copySourceVersionId;
    private String etag;
    private Date lastModified;
    private StorageClassEnum storageClass;
    private String versionId;

    public CopyObjectResult(String str, Date date, String str2, String str3, StorageClassEnum storageClassEnum) {
        this.etag = str;
        this.lastModified = date;
        this.versionId = str2;
        this.copySourceVersionId = str3;
        this.storageClass = storageClassEnum;
    }

    public String getCopySourceVersionId() {
        return this.copySourceVersionId;
    }

    public String getEtag() {
        return this.etag;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public StorageClassEnum getObjectStorageClass() {
        return this.storageClass;
    }

    public String getVersionId() {
        return this.versionId;
    }

    @Override
    public String toString() {
        return "CopyObjectResult [etag=" + this.etag + ", lastModified=" + this.lastModified + ", versionId=" + this.versionId + ", copySourceVersionId=" + this.copySourceVersionId + ", storageClass=" + this.storageClass + "]";
    }
}
