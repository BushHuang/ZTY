package com.obs.services.model;

import java.util.List;

public class RestoreObjectsRequest extends AbstractBulkRequest {
    private TaskCallback<RestoreObjectResult, RestoreObjectRequest> callback;
    private int days;
    private List<KeyAndVersion> keyAndVersions;
    private String prefix;
    private RestoreTierEnum tier;
    private boolean versionRestored;

    public RestoreObjectsRequest() {
    }

    public RestoreObjectsRequest(String str) {
        super(str);
    }

    public RestoreObjectsRequest(String str, int i, RestoreTierEnum restoreTierEnum) {
        super(str);
        this.days = i;
        this.tier = restoreTierEnum;
    }

    public KeyAndVersion addKeyAndVersion(String str) {
        return addKeyAndVersion(str, null);
    }

    public KeyAndVersion addKeyAndVersion(String str, String str2) {
        KeyAndVersion keyAndVersion = new KeyAndVersion(str, str2);
        getKeyAndVersions().add(keyAndVersion);
        return keyAndVersion;
    }

    public TaskCallback<RestoreObjectResult, RestoreObjectRequest> getCallback() {
        return this.callback;
    }

    public int getDays() {
        return this.days;
    }

    public List<KeyAndVersion> getKeyAndVersions() {
        return this.keyAndVersions;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public RestoreTierEnum getRestoreTier() {
        return this.tier;
    }

    public boolean isVersionRestored() {
        return this.versionRestored;
    }

    public void setCallback(TaskCallback<RestoreObjectResult, RestoreObjectRequest> taskCallback) {
        this.callback = taskCallback;
    }

    public void setDays(int i) {
        this.days = i;
    }

    public void setKeyAndVersions(List<KeyAndVersion> list) {
        this.keyAndVersions = list;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setRestoreTier(RestoreTierEnum restoreTierEnum) {
        this.tier = restoreTierEnum;
    }

    public void setVersionRestored(boolean z) {
        this.versionRestored = z;
    }

    public String toString() {
        return "RestoreObjectsRequest [bucketName=" + this.bucketName + ", days=" + this.days + ", tier=" + this.tier + "]";
    }
}
