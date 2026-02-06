package com.obs.services.model;

public class ReadAheadRequest {
    private String bucketName;
    private CacheOptionEnum cacheOption;
    private String prefix;
    private long ttl = 86400;

    public ReadAheadRequest(String str, String str2) {
        setBucketName(str);
        setPrefix(str2);
    }

    public ReadAheadRequest(String str, String str2, CacheOptionEnum cacheOptionEnum, long j) {
        setBucketName(str);
        setPrefix(str2);
        setCacheOption(cacheOptionEnum);
        setTtl(j);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public CacheOptionEnum getCacheOption() {
        return this.cacheOption;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public long getTtl() {
        return this.ttl;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCacheOption(CacheOptionEnum cacheOptionEnum) {
        this.cacheOption = cacheOptionEnum;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setTtl(long j) {
        if (j < 0 || j > 259200) {
            return;
        }
        this.ttl = j;
    }
}
