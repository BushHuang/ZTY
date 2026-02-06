package com.xh.xhcore.common.http.strategy;

import com.xh.xhcore.common.http.strategy.BaseRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest<T extends BaseRequest> implements IRequest<T> {
    public static final String TAG_REQUEST = "tag_request";
    protected String method = "GET";
    protected String url = "";
    protected Map<String, Object> headers = new HashMap();
    protected boolean isAsync = true;
    protected long connectTimeout = 0;
    protected long readTimeout = 0;
    protected long writeTimeout = 0;
    protected long connectionKeepAliveMillis = -1;
    protected String forceIp = "";

    @Override
    public T addHeader(String str, Object obj) {
        this.headers.put(str, obj);
        return this;
    }

    public Map<String, Object> getHeaders() {
        return this.headers;
    }

    public String getMethod() {
        return this.method;
    }

    public long getReadTimeout() {
        return this.readTimeout;
    }

    public abstract String getRequestBodyString();

    public abstract String getRequestHeader(String str);

    public abstract Map getRequestHeadersMap();

    public abstract String getRequestHeadersString();

    public abstract String getRequestHost();

    public abstract String getRequestUrl();

    @Override
    public String getUrl() {
        return this.url;
    }

    public abstract boolean isAlreadyRequested(String str);

    public boolean isAsync() {
        return this.isAsync;
    }

    public abstract void request();

    public T setConnectTimeout(long j) {
        this.connectTimeout = j;
        return this;
    }

    public T setConnectionKeepAliveMillis(long j) {
        this.connectionKeepAliveMillis = j;
        return this;
    }

    public T setForceIp(String str) {
        this.forceIp = str;
        return this;
    }

    @Override
    public T setHeaders(Map<String, Object> map) {
        if (map != null) {
            this.headers = map;
        }
        return this;
    }

    @Override
    public Object setHeaders(Map map) {
        return setHeaders((Map<String, Object>) map);
    }

    @Override
    public T setIsAsync(boolean z) {
        this.isAsync = z;
        return this;
    }

    @Override
    public T setMethod(String str) {
        this.method = str;
        return this;
    }

    public T setReadTimeout(long j) {
        this.readTimeout = j;
        return this;
    }

    public T setTimeout(long j) {
        setConnectTimeout(j);
        setReadTimeout(j);
        setWriteTimeout(j);
        return this;
    }

    @Override
    public T setUrl(String str) {
        this.url = str;
        return this;
    }

    public T setWriteTimeout(long j) {
        this.writeTimeout = j;
        return this;
    }
}
