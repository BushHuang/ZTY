package com.obs.services.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteObjectsRequest {
    private String bucketName;
    private List<KeyAndVersion> keyAndVersions;
    private boolean quiet;

    public DeleteObjectsRequest() {
    }

    public DeleteObjectsRequest(String str) {
        this.bucketName = str;
    }

    public DeleteObjectsRequest(String str, boolean z, KeyAndVersion[] keyAndVersionArr) {
        this.bucketName = str;
        this.quiet = z;
        setKeyAndVersions(keyAndVersionArr);
    }

    public KeyAndVersion addKeyAndVersion(String str) {
        return addKeyAndVersion(str, null);
    }

    public KeyAndVersion addKeyAndVersion(String str, String str2) {
        KeyAndVersion keyAndVersion = new KeyAndVersion(str, str2);
        getKeyAndVersionsList().add(keyAndVersion);
        return keyAndVersion;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public KeyAndVersion[] getKeyAndVersions() {
        return (KeyAndVersion[]) getKeyAndVersionsList().toArray(new KeyAndVersion[getKeyAndVersionsList().size()]);
    }

    public List<KeyAndVersion> getKeyAndVersionsList() {
        if (this.keyAndVersions == null) {
            this.keyAndVersions = new ArrayList();
        }
        return this.keyAndVersions;
    }

    public boolean isQuiet() {
        return this.quiet;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setKeyAndVersions(KeyAndVersion[] keyAndVersionArr) {
        if (keyAndVersionArr == null || keyAndVersionArr.length <= 0) {
            return;
        }
        this.keyAndVersions = new ArrayList(Arrays.asList(keyAndVersionArr));
    }

    public void setQuiet(boolean z) {
        this.quiet = z;
    }

    public String toString() {
        return "DeleteObjectsRequest [bucketName=" + this.bucketName + ", quiet=" + this.quiet + ", keyAndVersions=" + this.keyAndVersions + "]";
    }
}
