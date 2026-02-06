package com.xuehai.provider.core.dto;

public class CPVDControlApp {
    private boolean isShow;
    private String packageName;

    public String getPackageName() {
        return this.packageName;
    }

    public boolean isShow() {
        return this.isShow;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }
}
