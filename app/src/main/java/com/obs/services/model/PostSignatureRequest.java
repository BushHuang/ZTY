package com.obs.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostSignatureRequest {
    private String bucketName;
    private List<String> conditions;
    private long expires;
    private Date expiryDate;
    private Map<String, Object> formParams;
    private String objectKey;
    private Date requestDate;

    public PostSignatureRequest() {
        this.expires = 300L;
    }

    public PostSignatureRequest(long j, String str, String str2) {
        this.expires = 300L;
        this.expires = j;
        this.bucketName = str;
        this.objectKey = str2;
    }

    public PostSignatureRequest(long j, Date date, String str, String str2) {
        this.expires = 300L;
        this.expires = j;
        this.requestDate = date;
        this.bucketName = str;
        this.objectKey = str2;
    }

    public PostSignatureRequest(Date date, String str, String str2) {
        this.expires = 300L;
        this.expiryDate = date;
        this.bucketName = str;
        this.objectKey = str2;
    }

    public PostSignatureRequest(Date date, Date date2, String str, String str2) {
        this.expires = 300L;
        this.expiryDate = date;
        this.requestDate = date2;
        this.bucketName = str;
        this.objectKey = str2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public List<String> getConditions() {
        if (this.conditions == null) {
            this.conditions = new ArrayList();
        }
        return this.conditions;
    }

    public long getExpires() {
        return this.expires;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public Map<String, Object> getFormParams() {
        if (this.formParams == null) {
            this.formParams = new HashMap();
        }
        return this.formParams;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public Date getRequestDate() {
        return this.requestDate;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setConditions(List<String> list) {
        this.conditions = list;
    }

    public void setExpires(long j) {
        this.expires = j;
    }

    public void setExpiryDate(Date date) {
        this.expiryDate = date;
    }

    public void setFormParams(Map<String, Object> map) {
        this.formParams = map;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setRequestDate(Date date) {
        this.requestDate = date;
    }

    public String toString() {
        return "PostSignatureRequest [requestDate=" + this.requestDate + ", expiryDate=" + this.expiryDate + ", bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", expires=" + this.expires + ", formParams=" + this.formParams + ", conditions=" + this.conditions + "]";
    }
}
