package com.xuehai.provider.core.db;

public class CPVDApp {
    public static final int START_BY_STUDY = 1;
    public static final int START_BY_ZTY = 0;
    private String appName;
    private String pkgName;
    private long versionCode;
    private String versionName;
    private boolean isForced = true;

    @Deprecated
    private boolean isLocked = false;
    private int startFrom = 0;

    public String getAppName() {
        return this.appName;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public int getStartFrom() {
        return this.startFrom;
    }

    public long getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public boolean isForced() {
        return this.isForced;
    }

    @Deprecated
    public boolean isLocked() {
        return this.isLocked;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setForced(boolean z) {
        this.isForced = z;
    }

    @Deprecated
    public void setLocked(boolean z) {
        this.isLocked = z;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public void setStartFrom(int i) {
        this.startFrom = i;
    }

    public void setVersionCode(long j) {
        this.versionCode = j;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public String toString() {
        return "CPVDApp{pkgName='" + this.pkgName + "', appName='" + this.appName + "', versionCode=" + this.versionCode + ", versionName='" + this.versionName + "', isForced=" + this.isForced + ", startFrom=" + this.startFrom + '}';
    }
}
