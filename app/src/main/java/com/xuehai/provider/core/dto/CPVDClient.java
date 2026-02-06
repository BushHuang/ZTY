package com.xuehai.provider.core.dto;

public class CPVDClient {
    private String clientType;
    private String packageName;
    private long versionCode;
    private String versionName;

    public static class ClientType {
        public static final String TV = "tv";
    }

    public String getClientType() {
        return this.clientType;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public long getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setClientType(String str) {
        this.clientType = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setVersionCode(long j) {
        this.versionCode = j;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public String toString() {
        return "CPVDClient{packageName='" + this.packageName + "', versionCode=" + this.versionCode + ", versionName='" + this.versionName + "', clientType='" + this.clientType + "'}";
    }
}
