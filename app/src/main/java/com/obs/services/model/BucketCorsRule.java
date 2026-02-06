package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class BucketCorsRule {
    private List<String> allowedHeader;
    private List<String> allowedMethod;
    private List<String> allowedOrigin;
    private List<String> exposeHeader;
    private String id;
    private int maxAgeSecond = Integer.MIN_VALUE;

    public List<String> getAllowedHeader() {
        List<String> list = this.allowedHeader;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.allowedHeader = arrayList;
        return arrayList;
    }

    public List<String> getAllowedMethod() {
        List<String> list = this.allowedMethod;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.allowedMethod = arrayList;
        return arrayList;
    }

    public List<String> getAllowedOrigin() {
        List<String> list = this.allowedOrigin;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.allowedOrigin = arrayList;
        return arrayList;
    }

    public List<String> getExposeHeader() {
        List<String> list = this.exposeHeader;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.exposeHeader = arrayList;
        return arrayList;
    }

    public String getId() {
        return this.id;
    }

    public int getMaxAgeSecond() {
        int i = this.maxAgeSecond;
        if (i == Integer.MIN_VALUE) {
            return 0;
        }
        return i;
    }

    public void setAllowedHeader(List<String> list) {
        this.allowedHeader = list;
    }

    public void setAllowedMethod(List<String> list) {
        this.allowedMethod = list;
    }

    public void setAllowedOrigin(List<String> list) {
        this.allowedOrigin = list;
    }

    public void setExposeHeader(List<String> list) {
        this.exposeHeader = list;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMaxAgeSecond(int i) {
        this.maxAgeSecond = i;
    }

    public String toString() {
        return "BucketCorsRule [id=" + this.id + ", maxAgeSecond=" + this.maxAgeSecond + ", allowedMethod=" + this.allowedMethod + ", allowedOrigin=" + this.allowedOrigin + ", allowedHeader=" + this.allowedHeader + ", exposeHeader=" + this.exposeHeader + "]";
    }
}
