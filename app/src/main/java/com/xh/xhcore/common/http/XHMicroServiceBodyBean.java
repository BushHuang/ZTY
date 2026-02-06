package com.xh.xhcore.common.http;

public class XHMicroServiceBodyBean {
    private int iMode;
    private int iSchoolId;
    private int iServerId;
    private int iServerPort;
    private String sDirLocation;
    private String sServerIp;
    private String sServiceType;
    private int useNum;

    public XHMicroServiceBodyBean() {
    }

    public XHMicroServiceBodyBean(int i, int i2, int i3, String str, String str2, String str3, int i4) {
        this.iSchoolId = i;
        this.iServerId = i2;
        this.iServerPort = i3;
        this.sDirLocation = str;
        this.sServerIp = str2;
        this.sServiceType = str3;
        this.useNum = i4;
    }

    public int getISchoolId() {
        return this.iSchoolId;
    }

    public int getIServerId() {
        return this.iServerId;
    }

    public int getIServerPort() {
        return this.iServerPort;
    }

    public String getSDirLocation() {
        String str = this.sDirLocation;
        return str != null ? str : "";
    }

    public String getSServerIp() {
        String str = this.sServerIp;
        return str != null ? str : "";
    }

    public String getSServiceType() {
        String str = this.sServiceType;
        return str != null ? str : "";
    }

    public int getUseNum() {
        return this.useNum;
    }

    public int getiMode() {
        return this.iMode;
    }

    public void setISchoolId(int i) {
        this.iSchoolId = i;
    }

    public void setIServerId(int i) {
        this.iServerId = i;
    }

    public void setIServerPort(int i) {
        this.iServerPort = i;
    }

    public void setSDirLocation(String str) {
        this.sDirLocation = str;
    }

    public XHMicroServiceBodyBean setSServerIp(String str) {
        this.sServerIp = str;
        return this;
    }

    public void setSServiceType(String str) {
        this.sServiceType = str;
    }

    public void setUseNum(int i) {
        this.useNum = i;
    }

    public void setiMode(int i) {
        this.iMode = i;
    }

    public String toString() {
        return "XHMicroServiceBodyBean{iSchoolId = " + this.iSchoolId + ",iServerId = " + this.iServerId + ",iServerPort = " + this.iServerPort + ",sDirLocation = " + getSDirLocation() + ",sServerIp = " + getSServerIp() + ",sServiceType = " + getSServiceType() + ",iMode = " + this.iMode + ",useNum = " + this.useNum + "}";
    }
}
