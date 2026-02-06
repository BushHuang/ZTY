package com.obs.services.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OefExceptionMessage {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("request_id")
    private String request_id;

    public OefExceptionMessage() {
    }

    public OefExceptionMessage(String str, String str2, String str3) {
        this.message = str;
        this.code = str2;
        this.request_id = str3;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setRequest_id(String str) {
        this.request_id = str;
    }

    public String toString() {
        return "OefExceptionMessage [message=" + this.message + ", code=" + this.code + ", request_id" + this.request_id + "]";
    }
}
