package com.obs.services.model;

import java.util.Date;

@Deprecated
public class V4PostSignatureRequest extends PostSignatureRequest {
    public V4PostSignatureRequest() {
    }

    public V4PostSignatureRequest(long j, String str, String str2) {
        super(j, str, str2);
    }

    public V4PostSignatureRequest(long j, Date date, String str, String str2) {
        super(j, date, str, str2);
    }

    public V4PostSignatureRequest(Date date, String str, String str2) {
        super(date, str, str2);
    }

    public V4PostSignatureRequest(Date date, Date date2, String str, String str2) {
        super(date, date2, str, str2);
    }
}
