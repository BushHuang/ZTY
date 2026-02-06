package com.huawei.hms.support.api.client;

public class SubAppInfo {

    private String f352a;

    public SubAppInfo(SubAppInfo subAppInfo) {
        if (subAppInfo != null) {
            this.f352a = subAppInfo.getSubAppID();
        }
    }

    public SubAppInfo(String str) {
        this.f352a = str;
    }

    public String getSubAppID() {
        return this.f352a;
    }

    public void setSubAppInfoID(String str) {
        this.f352a = str;
    }
}
