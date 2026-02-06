package com.xh.xhcore.common.http;

public class XHMicroServiceReqBean {
    private String Desc;
    private int iSchoolId;
    private long iUpdateTime;
    private int iUserId;
    private int iVersion;
    private String[] microServiceUrls;
    private String sServiceType;
    private String sUserType;

    public String getDesc() {
        return this.Desc;
    }

    public String[] getMicroServiceUrls() {
        return this.microServiceUrls;
    }

    public int getiSchoolId() {
        return this.iSchoolId;
    }

    public long getiUpdateTime() {
        return this.iUpdateTime;
    }

    public int getiUserId() {
        return this.iUserId;
    }

    public int getiVersion() {
        return this.iVersion;
    }

    public String getsServiceType() {
        return this.sServiceType;
    }

    public String getsUserType() {
        return this.sUserType;
    }

    public void setDesc(String str) {
        this.Desc = str;
    }

    public void setMicroServiceUrls(String[] strArr) {
        this.microServiceUrls = strArr;
    }

    public void setiSchoolId(int i) {
        this.iSchoolId = i;
    }

    public void setiUpdateTime(long j) {
        this.iUpdateTime = j;
    }

    public void setiUserId(int i) {
        this.iUserId = i;
    }

    public void setiVersion(int i) {
        this.iVersion = i;
    }

    public void setsServiceType(String str) {
        this.sServiceType = str;
    }

    public void setsUserType(String str) {
        this.sUserType = str;
    }
}
