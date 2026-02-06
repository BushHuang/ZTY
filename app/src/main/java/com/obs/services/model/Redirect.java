package com.obs.services.model;

public class Redirect {
    private String hostName;
    private String httpRedirectCode;
    private ProtocolEnum protocol;
    private String replaceKeyPrefixWith;
    private String replaceKeyWith;

    public String getHostName() {
        return this.hostName;
    }

    public String getHttpRedirectCode() {
        return this.httpRedirectCode;
    }

    @Deprecated
    public String getProtocol() {
        ProtocolEnum protocolEnum = this.protocol;
        if (protocolEnum != null) {
            return protocolEnum.getCode();
        }
        return null;
    }

    public ProtocolEnum getRedirectProtocol() {
        return this.protocol;
    }

    public String getReplaceKeyPrefixWith() {
        return this.replaceKeyPrefixWith;
    }

    public String getReplaceKeyWith() {
        return this.replaceKeyWith;
    }

    public void setHostName(String str) {
        this.hostName = str;
    }

    public void setHttpRedirectCode(String str) {
        this.httpRedirectCode = str;
    }

    @Deprecated
    public void setProtocol(String str) {
        this.protocol = ProtocolEnum.getValueFromCode(str);
    }

    public void setRedirectProtocol(ProtocolEnum protocolEnum) {
        this.protocol = protocolEnum;
    }

    public void setReplaceKeyPrefixWith(String str) {
        this.replaceKeyPrefixWith = str;
    }

    public void setReplaceKeyWith(String str) {
        this.replaceKeyWith = str;
    }

    public String toString() {
        return "RedirectRule [protocol=" + this.protocol + ", hostName=" + this.hostName + ", replaceKeyPrefixWith=" + this.replaceKeyPrefixWith + ", replaceKeyWith=" + this.replaceKeyWith + ", httpRedirectCode=" + this.httpRedirectCode + "]";
    }
}
