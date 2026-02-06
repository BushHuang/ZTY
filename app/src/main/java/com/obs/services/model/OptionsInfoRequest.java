package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class OptionsInfoRequest {
    private String origin;
    private List<String> requestHeaders;
    private List<String> requestMethod;

    public String getOrigin() {
        return this.origin;
    }

    public List<String> getRequestHeaders() {
        if (this.requestHeaders == null) {
            this.requestHeaders = new ArrayList();
        }
        return this.requestHeaders;
    }

    public List<String> getRequestMethod() {
        if (this.requestMethod == null) {
            this.requestMethod = new ArrayList();
        }
        return this.requestMethod;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public void setRequestHeaders(List<String> list) {
        this.requestHeaders = list;
    }

    public void setRequestMethod(List<String> list) {
        this.requestMethod = list;
    }

    public String toString() {
        return "OptionsInfoRequest [origin=" + this.origin + ", requestMethod=" + this.requestMethod + ", requestHeaders=" + this.requestHeaders + "]";
    }
}
