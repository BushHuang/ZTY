package com.xh.networkclient.bean;

public class FSCommonRes {
    private String message;
    private short status;

    public String getMessage() {
        return this.message;
    }

    public short getStatus() {
        return this.status;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(short s) {
        this.status = s;
    }
}
