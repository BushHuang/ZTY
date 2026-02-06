package com.obs.services.model;

public class KeyAndVersion {
    private String key;
    private String version;

    public KeyAndVersion(String str) {
        this(str, null);
    }

    public KeyAndVersion(String str, String str2) {
        this.key = str;
        this.version = str2;
    }

    public String getKey() {
        return this.key;
    }

    public String getVersion() {
        return this.version;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return "KeyAndVersion [key=" + this.key + ", version=" + this.version + "]";
    }
}
