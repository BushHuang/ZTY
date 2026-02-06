package com.xuehai.provider.core.dto;

import android.text.TextUtils;

public class CPVDCache {
    private long id;
    private String key;
    private String value;

    public CPVDCache() {
    }

    public CPVDCache(long j, String str, String str2) {
        this.id = j;
        this.key = str;
        this.value = str2;
    }

    public CPVDCache(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CPVDCache)) {
            return super.equals(obj);
        }
        CPVDCache cPVDCache = (CPVDCache) obj;
        return TextUtils.equals(this.key, cPVDCache.key) && TextUtils.equals(this.value, cPVDCache.value);
    }

    public long getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        return "CPVDCache{id=" + this.id + ", key='" + this.key + "', value='" + this.value + "'}";
    }
}
