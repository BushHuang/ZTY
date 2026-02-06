package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class OptionsInfoResult extends HeaderResponse {
    private List<String> allowHeaders;
    private List<String> allowMethods;
    private String allowOrigin;
    private List<String> exposeHeaders;
    private int maxAge;

    public OptionsInfoResult(String str, List<String> list, int i, List<String> list2, List<String> list3) {
        this.allowOrigin = str;
        this.allowHeaders = list;
        this.maxAge = i;
        this.allowMethods = list2;
        this.exposeHeaders = list3;
    }

    public List<String> getAllowHeaders() {
        if (this.allowHeaders == null) {
            this.allowHeaders = new ArrayList();
        }
        return this.allowHeaders;
    }

    public List<String> getAllowMethods() {
        if (this.allowMethods == null) {
            this.allowMethods = new ArrayList();
        }
        return this.allowMethods;
    }

    public String getAllowOrigin() {
        return this.allowOrigin;
    }

    public List<String> getExposeHeaders() {
        if (this.exposeHeaders == null) {
            this.exposeHeaders = new ArrayList();
        }
        return this.exposeHeaders;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    @Override
    public String toString() {
        return "OptionsInfoResult [allowOrigin=" + this.allowOrigin + ", allowHeaders=" + this.allowHeaders + ", maxAge=" + this.maxAge + ", allowMethods=" + this.allowMethods + ", exposeHeaders=" + this.exposeHeaders + "]";
    }
}
