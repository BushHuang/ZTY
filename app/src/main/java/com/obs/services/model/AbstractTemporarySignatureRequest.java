package com.obs.services.model;

import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractTemporarySignatureRequest {
    protected String bucketName;
    protected Map<String, String> headers;
    protected HttpMethodEnum method;
    protected String objectKey;
    protected Map<String, Object> queryParams;
    protected SpecialParamEnum specialParam;

    public AbstractTemporarySignatureRequest() {
    }

    public AbstractTemporarySignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2) {
        this.method = httpMethodEnum;
        this.bucketName = str;
        this.objectKey = str2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public Map<String, String> getHeaders() {
        if (this.headers == null) {
            this.headers = new TreeMap();
        }
        return this.headers;
    }

    public HttpMethodEnum getMethod() {
        return this.method;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public Map<String, Object> getQueryParams() {
        if (this.queryParams == null) {
            this.queryParams = new TreeMap();
        }
        return this.queryParams;
    }

    public SpecialParamEnum getSpecialParam() {
        return this.specialParam;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public void setMethod(HttpMethodEnum httpMethodEnum) {
        this.method = httpMethodEnum;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setQueryParams(Map<String, Object> map) {
        this.queryParams = map;
    }

    public void setSpecialParam(SpecialParamEnum specialParamEnum) {
        this.specialParam = specialParamEnum;
    }
}
