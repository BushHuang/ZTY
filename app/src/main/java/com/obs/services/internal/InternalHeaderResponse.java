package com.obs.services.internal;

import java.util.Map;

public class InternalHeaderResponse {
    protected Map<String, Object> responseHeaders;
    protected int statusCode;

    protected void setResponseHeaders(Map<String, Object> map) {
        this.responseHeaders = map;
    }

    protected void setStatusCode(int i) {
        this.statusCode = i;
    }
}
