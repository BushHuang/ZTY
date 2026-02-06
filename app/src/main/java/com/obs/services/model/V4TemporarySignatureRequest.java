package com.obs.services.model;

import java.util.Date;

@Deprecated
public class V4TemporarySignatureRequest extends TemporarySignatureRequest {
    public V4TemporarySignatureRequest() {
    }

    public V4TemporarySignatureRequest(HttpMethodEnum httpMethodEnum, long j) {
        super(httpMethodEnum, null, null, null, j);
    }

    public V4TemporarySignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2, SpecialParamEnum specialParamEnum, long j) {
        super(httpMethodEnum, str, str2, specialParamEnum, j, null);
    }

    public V4TemporarySignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2, SpecialParamEnum specialParamEnum, long j, Date date) {
        super(httpMethodEnum, str, str2, specialParamEnum, j, date);
    }
}
