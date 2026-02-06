package com.obs.services.model;

import java.util.Date;

public class TemporarySignatureRequest extends AbstractTemporarySignatureRequest {
    private long expires;
    private Date requestDate;

    public TemporarySignatureRequest() {
        this.expires = 300L;
    }

    public TemporarySignatureRequest(HttpMethodEnum httpMethodEnum, long j) {
        this(httpMethodEnum, null, null, null, j);
    }

    public TemporarySignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2, SpecialParamEnum specialParamEnum, long j) {
        this(httpMethodEnum, str, str2, specialParamEnum, j, null);
    }

    public TemporarySignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2, SpecialParamEnum specialParamEnum, long j, Date date) {
        this.expires = 300L;
        this.method = httpMethodEnum;
        this.bucketName = str;
        this.objectKey = str2;
        this.specialParam = specialParamEnum;
        this.expires = j;
        this.requestDate = date;
    }

    public long getExpires() {
        return this.expires;
    }

    public Date getRequestDate() {
        return this.requestDate;
    }

    public void setExpires(long j) {
        this.expires = j;
    }

    public void setRequestDate(Date date) {
        this.requestDate = date;
    }

    public String toString() {
        return "TemporarySignatureRequest [method=" + this.method + ", bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", specialParam=" + this.specialParam + ", expires=" + this.expires + ", requestDate=" + this.requestDate + ", headers=" + getHeaders() + ", queryParams=" + getQueryParams() + "]";
    }
}
