package com.obs.services.model;

import com.obs.services.internal.InternalHeaderResponse;
import java.util.Map;
import java.util.TreeMap;

public class HeaderResponse extends InternalHeaderResponse {
    public String getRequestId() {
        Object obj = getResponseHeaders().get("request-id");
        return obj == null ? "" : obj.toString();
    }

    public Map<String, Object> getResponseHeaders() {
        if (this.responseHeaders == null) {
            this.responseHeaders = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        }
        return this.responseHeaders;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String toString() {
        return "HeaderResponse [responseHeaders=" + this.responseHeaders + ", statusCode=" + this.statusCode + "]";
    }
}
