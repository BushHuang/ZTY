package com.obs.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InterfaceLogBean {
    private static final String DATE_FMT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    private String interfaceType;
    private String name;
    private String product;
    private String protocolType;
    private String reqParams;
    private Date reqTime;
    private String reqTimeAsString;
    private String respParams;
    private Date respTime;
    private String respTimeAsString;
    private String resultCode;
    private String sourceAddr;
    private String targetAddr;
    private String transactionId;

    public InterfaceLogBean() {
    }

    public InterfaceLogBean(String str, String str2, String str3) {
        this.transactionId = "";
        this.interfaceType = "1";
        this.product = "Storage";
        this.protocolType = "HTTP+XML";
        this.reqTime = new Date();
        this.name = str;
        this.sourceAddr = "";
        this.targetAddr = "";
        this.reqParams = str3;
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }

    public String getName() {
        return this.name;
    }

    public String getProduct() {
        return this.product;
    }

    public String getProtocolType() {
        return this.protocolType;
    }

    public String getReqParams() {
        return this.reqParams;
    }

    public Date getReqTime() {
        return this.reqTime;
    }

    public String getReqTimeAsString() {
        return (this.reqTimeAsString != null || this.reqTime == null) ? this.reqTimeAsString : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.reqTime);
    }

    public String getRespParams() {
        return this.respParams;
    }

    public Date getRespTime() {
        return this.respTime;
    }

    public String getRespTimeAsString() {
        return (this.respTimeAsString != null || this.respTime == null) ? this.respTimeAsString : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.respTime);
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getSourceAddr() {
        return this.sourceAddr;
    }

    public String getTargetAddr() {
        return this.targetAddr;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setInterfaceType(String str) {
        this.interfaceType = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProduct(String str) {
        this.product = str;
    }

    public void setProtocolType(String str) {
        this.protocolType = str;
    }

    public void setReqParams(String str) {
        this.reqParams = str;
    }

    public void setReqTime(Date date) {
        this.reqTime = date;
    }

    public void setReqTimeAsString(String str) {
        this.reqTimeAsString = str;
    }

    public void setRespParams(String str) {
        this.respParams = str;
    }

    public void setRespTime(Date date) {
        this.respTime = date;
    }

    public void setRespTimeAsString(String str) {
        this.respTimeAsString = str;
    }

    public void setResponseInfo(String str, String str2) {
        this.respParams = str;
        this.resultCode = str2;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public void setSourceAddr(String str) {
        this.sourceAddr = str;
    }

    public void setTargetAddr(String str) {
        this.targetAddr = str;
    }

    public void setTransactionId(String str) {
        this.transactionId = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProduct());
        sb.append("|");
        sb.append(getInterfaceType());
        sb.append("|");
        sb.append(getProtocolType());
        sb.append("|");
        sb.append(getName());
        sb.append("|");
        sb.append(getSourceAddr());
        sb.append("|");
        sb.append(getTargetAddr());
        sb.append("|");
        sb.append(getTransactionId() == null ? "" : getTransactionId());
        sb.append("|");
        sb.append(getReqTimeAsString());
        sb.append("|");
        sb.append(getRespTimeAsString());
        sb.append("|");
        sb.append(getReqParams() == null ? "" : getReqParams());
        sb.append("|");
        sb.append(getRespParams() != null ? getRespParams() : "");
        sb.append("|");
        sb.append(getResultCode());
        sb.append("|");
        return sb.toString();
    }
}
