package com.obs.services.model;

public class RedirectAllRequest {
    private String hostName;
    private ProtocolEnum protocol;

    public String getHostName() {
        return this.hostName;
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

    public void setHostName(String str) {
        this.hostName = str;
    }

    @Deprecated
    public void setProtocol(String str) {
        this.protocol = ProtocolEnum.getValueFromCode(str);
    }

    public void setRedirectProtocol(ProtocolEnum protocolEnum) {
        this.protocol = protocolEnum;
    }

    public String toString() {
        return "RedirectAllRequest [protocol=" + this.protocol + ", hostName=" + this.hostName + "]";
    }
}
