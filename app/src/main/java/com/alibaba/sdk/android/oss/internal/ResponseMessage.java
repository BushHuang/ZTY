package com.alibaba.sdk.android.oss.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import okhttp3.Response;

public class ResponseMessage extends HttpMessage {
    private RequestMessage request;
    private Response response;
    private int statusCode;

    @Override
    public void addHeader(String str, String str2) {
        super.addHeader(str, str2);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public InputStream getContent() {
        return super.getContent();
    }

    @Override
    public long getContentLength() {
        return super.getContentLength();
    }

    @Override
    public Map getHeaders() {
        return super.getHeaders();
    }

    public RequestMessage getRequest() {
        return this.request;
    }

    public Response getResponse() {
        return this.response;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public String getStringBody() {
        return super.getStringBody();
    }

    @Override
    public void setContent(InputStream inputStream) {
        super.setContent(inputStream);
    }

    @Override
    public void setContentLength(long j) {
        super.setContentLength(j);
    }

    @Override
    public void setHeaders(Map map) {
        super.setHeaders(map);
    }

    public void setRequest(RequestMessage requestMessage) {
        this.request = requestMessage;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    @Override
    public void setStringBody(String str) {
        super.setStringBody(str);
    }
}
