package com.obs.services.model;

import com.obs.services.internal.utils.ServiceUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PolicyTempSignatureRequest extends AbstractTemporarySignatureRequest {
    private List<PolicyConditionItem> conditions;
    private long expires;
    private Date expiryDate;

    public PolicyTempSignatureRequest() {
        this.expires = 300L;
    }

    public PolicyTempSignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2) {
        super(httpMethodEnum, str, str2);
        this.expires = 300L;
    }

    public PolicyTempSignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2, long j) {
        super(httpMethodEnum, str, str2);
        this.expires = 300L;
        this.expires = j;
    }

    public PolicyTempSignatureRequest(HttpMethodEnum httpMethodEnum, String str, String str2, Date date) {
        super(httpMethodEnum, str, str2);
        this.expires = 300L;
        this.expiryDate = date;
    }

    public String generatePolicy() {
        Date date = new Date();
        SimpleDateFormat expirationDateFormat = ServiceUtils.getExpirationDateFormat();
        Date date2 = this.expiryDate;
        if (date2 == null) {
            long time = date.getTime();
            long j = this.expires;
            if (j <= 0) {
                j = 300;
            }
            date2 = new Date(time + (j * 1000));
        }
        String str = expirationDateFormat.format(date2);
        StringBuilder sb = new StringBuilder();
        sb.append("{\"expiration\":");
        sb.append("\"");
        sb.append(str);
        sb.append("\",");
        sb.append("\"conditions\":[");
        List<PolicyConditionItem> list = this.conditions;
        if (list != null && !list.isEmpty()) {
            sb.append(ServiceUtils.join(this.conditions, ","));
        }
        sb.append("]}");
        return sb.toString();
    }

    public List<PolicyConditionItem> getConditions() {
        return this.conditions;
    }

    public long getExpires() {
        return this.expires;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setConditions(List<PolicyConditionItem> list) {
        this.conditions = list;
    }

    public void setExpires(long j) {
        this.expires = j;
    }

    public void setExpiryDate(Date date) {
        this.expiryDate = date;
    }
}
