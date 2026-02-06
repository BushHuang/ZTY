package com.obs.services.model;

import java.util.HashMap;
import java.util.Map;

public class TemporarySignatureResponse {
    private Map<String, String> actualSignedRequestHeaders;
    private String signedUrl;

    public TemporarySignatureResponse(String str) {
        this.signedUrl = str;
    }

    public Map<String, String> getActualSignedRequestHeaders() {
        if (this.actualSignedRequestHeaders == null) {
            this.actualSignedRequestHeaders = new HashMap();
        }
        return this.actualSignedRequestHeaders;
    }

    public String getSignedUrl() {
        return this.signedUrl;
    }

    public String toString() {
        return "TemporarySignatureResponse [signedUrl=" + this.signedUrl + ", actualSignedRequestHeaders=" + this.actualSignedRequestHeaders + "]";
    }
}
