package com.obs.services;

public class HttpProxyConfiguration {
    private String domain;
    private String proxyAddr;
    private int proxyPort;
    private String proxyUname;
    private String userPaaswd;
    private String workstation;

    public HttpProxyConfiguration() {
    }

    public HttpProxyConfiguration(String str, int i, String str2, String str3, String str4) {
        this.proxyAddr = str;
        this.proxyPort = i;
        this.proxyUname = str2;
        this.userPaaswd = str3;
        this.domain = str4;
        this.workstation = str;
    }

    public HttpProxyConfiguration(String str, int i, String str2, String str3, String str4, String str5) {
        this(str, i, str2, str3, str4);
        this.workstation = this.proxyAddr;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getProxyAddr() {
        return this.proxyAddr;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public String getProxyUName() {
        return this.proxyUname;
    }

    public String getUserPaaswd() {
        return this.userPaaswd;
    }

    public String getWorkstation() {
        return this.workstation;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setProxyAddr(String str) {
        this.proxyAddr = str;
    }

    public void setProxyPort(int i) {
        this.proxyPort = i;
    }

    public void setProxyUName(String str) {
        this.proxyUname = str;
    }

    public void setUserPaaswd(String str) {
        this.userPaaswd = str;
    }

    public void setWorkstation(String str) {
        this.workstation = str;
    }
}
