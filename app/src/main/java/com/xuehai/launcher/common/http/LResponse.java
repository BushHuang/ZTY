package com.xuehai.launcher.common.http;

import com.xuehai.launcher.common.constants.error.RestFulError;
import com.xuehai.launcher.common.util.LauncherUtil;
import com.xuehai.system.common.log.MdmLog;
import java.util.Map;
import org.json.JSONException;

public class LResponse {
    private int code;
    private Map<String, Object> headers;
    private String message;
    private LRequest request;
    private String responseBody;
    private RestFulError restFulError;

    public LResponse(LRequest lRequest) {
        this.request = lRequest;
    }

    public LResponse copyFrom(Throwable th) {
        setCode(-1);
        setResponseBody(th.getMessage());
        return this;
    }

    public int getCode() {
        return this.code;
    }

    public Map<String, Object> getHeaders() {
        return this.headers;
    }

    public String getMessage() {
        return this.message;
    }

    public LRequest getRequest() {
        return this.request;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public RestFulError getRestFulError() {
        return this.restFulError;
    }

    public boolean isFailure() {
        return !isSuccessful();
    }

    public boolean isSessionFailed() {
        return 107000401 == this.code;
    }

    public boolean isSuccessful() {
        int i = this.code;
        return i >= 200 && i < 300;
    }

    public void log() {
        StringBuilder sb = new StringBuilder();
        sb.append("url: ");
        sb.append(this.request.getUrl());
        sb.append("\nresponseBody: ");
        sb.append(this.responseBody);
        sb.append("\nmessage: ");
        sb.append(this.message);
        sb.append("\nheaders: ");
        try {
            sb.append(LauncherUtil.mapToJson(this.headers).toString(3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MdmLog.v("Http[MDM]", sb.toString());
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setHeaders(Map<String, Object> map) {
        this.headers = map;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setRequest(LRequest lRequest) {
        this.request = lRequest;
    }

    public void setResponseBody(String str) {
        this.responseBody = str;
    }

    public void setRestFulError(RestFulError restFulError) {
        this.restFulError = restFulError;
    }
}
