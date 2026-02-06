package com.xh.xhcore.common.http.strategy.okhttp.request;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.xh.xhcore.common.http.archi.CommonUtil;
import com.xh.xhcore.common.http.dns.BootstrapDNS;
import com.xh.xhcore.common.http.strategy.BaseRequest;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.exception.HttpUrlNullPointerException;
import com.xh.xhcore.common.http.strategy.okhttp.OkHttpClientManager;
import com.xh.xhcore.common.http.strategy.okhttp.OkHttpUtil;
import com.xh.xhcore.common.http.strategy.okhttp.ignore.TagRequestIdentity;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.GzipRequestInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.request.BaseRequestOkHttp;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestUtil;
import com.xh.xhcore.common.util.ReflectUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class BaseRequestOkHttp<T extends BaseRequestOkHttp> extends BaseRequest<T> implements IOkHttpRequest<T>, Cloneable {
    private Call call;
    public Callback callback;
    private OkHttpClient callerOkHttpClient;
    protected Request request;
    protected RequestBody requestBody;
    private String requestBodyMediaTypeString;
    private String requestBodyString;
    private final RequestBuilder requestBuilder = new RequestBuilder();
    private Map<String, String> formDataParts = new HashMap();
    private Map<String, String> formFields = new HashMap();
    private Map<String, String> queryParams = new HashMap();
    private final List<FormDataPartBean> formDataPartBeans = new ArrayList();
    List<Interceptor> networkInterceptors = new ArrayList();
    List<Interceptor> interceptors = new ArrayList();

    private static class FormDataPartBean {
        public RequestBody body;
        public String name;
        public String value;

        public FormDataPartBean(String str, String str2, RequestBody requestBody) {
            this.name = str;
            this.value = str2;
            this.body = requestBody;
        }
    }

    private T addHeaderToBuilder(String str, String str2) {
        this.requestBuilder.addHeader(str, str2);
        return this;
    }

    private void addInterceptors(OkHttpClient.Builder builder) {
        if (this.interceptors.size() > 0) {
            Iterator<Interceptor> it = this.interceptors.iterator();
            while (it.hasNext()) {
                builder.addInterceptor(it.next());
            }
        }
    }

    private void addNetworkInterceptors(OkHttpClient.Builder builder) {
        if (this.networkInterceptors.size() > 0) {
            Iterator<Interceptor> it = this.networkInterceptors.iterator();
            while (it.hasNext()) {
                builder.addNetworkInterceptor(it.next());
            }
        }
    }

    private void applyConnectionKeepALive(OkHttpClient okHttpClient) {
        if (this.connectionKeepAliveMillis <= 0) {
            if (this.connectionKeepAliveMillis == 0) {
                LogUtils.d("forbidding keep-alive add header Connection: close");
                addHeader("Connection", (Object) "close");
                return;
            }
            return;
        }
        try {
            ConnectionPool connectionPool = okHttpClient.connectionPool();
            long fieldLong = ReflectUtil.getFieldLong(connectionPool, "keepAliveDurationNs");
            long j = this.connectionKeepAliveMillis * 1000000;
            if (fieldLong != j) {
                LogUtils.d("wantedConnectionKeepAliveNs = " + j);
                ReflectUtil.setField(connectionPool, "keepAliveDurationNs", Long.valueOf(j));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private Request buildRequest() throws HttpUrlNullPointerException {
        this.requestBuilder.clear().setMethod(getMethod()).setUrl(getUrl());
        setHeadersToBuilder();
        for (Map.Entry<String, String> entry : this.formDataParts.entrySet()) {
            this.requestBuilder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        for (FormDataPartBean formDataPartBean : this.formDataPartBeans) {
            this.requestBuilder.addFormDataPart(formDataPartBean.name, formDataPartBean.value, formDataPartBean.body);
        }
        for (Map.Entry<String, String> entry2 : this.formFields.entrySet()) {
            this.requestBuilder.addFormField(entry2.getKey(), entry2.getValue(), false);
        }
        for (Map.Entry<String, String> entry3 : this.queryParams.entrySet()) {
            this.requestBuilder.addQueryParam(entry3.getKey(), entry3.getValue(), false);
        }
        RequestBody requestBody = this.requestBody;
        if (requestBody != null) {
            this.requestBuilder.setBody(requestBody);
        }
        return this.requestBuilder.build();
    }

    @Deprecated
    public static String getClientTraceIdValue() {
        return XHRequestUtil.getClientTraceIdValue();
    }

    protected static Gson getDefaultGson() {
        return OkHttpClientManager.getInstance().getGson();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = this.callerOkHttpClient;
        return okHttpClient != null ? okHttpClient : getDefaultOkHttpClient();
    }

    private boolean isAlreadyRequested(List<Call> list, String str) {
        Iterator<Call> it = list.iterator();
        while (it.hasNext()) {
            TagRequestIdentity tagRequestIdentity = (TagRequestIdentity) it.next().request().tag(TagRequestIdentity.class);
            if (tagRequestIdentity != null && tagRequestIdentity.getRequestIdentity().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private Call newCall(OkHttpClient okHttpClient) {
        Call callNewCall = okHttpClient.newCall(this.request);
        this.call = callNewCall;
        return callNewCall;
    }

    private void setForceIp(OkHttpClient.Builder builder) {
        if (TextUtils.isEmpty(this.forceIp)) {
            return;
        }
        builder.dns(new BootstrapDNS(this.forceIp));
    }

    private void setHeadersToBuilder() {
        setHeadersToBuilder(getHeaders());
    }

    private void setHeadersToBuilder(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String strObjectToString = CommonUtil.objectToString(entry.getValue());
            if (!TextUtils.isEmpty(key) && strObjectToString != null) {
                addHeaderToBuilder(key, strObjectToString);
            }
        }
    }

    private void setTimeout(OkHttpClient.Builder builder) {
        if (this.connectTimeout > 0) {
            LogUtils.d("connectTimeout = " + this.connectTimeout);
            builder.connectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS);
        }
        if (this.writeTimeout > 0) {
            LogUtils.d("writeTimeout = " + this.writeTimeout);
            builder.writeTimeout(this.writeTimeout, TimeUnit.MILLISECONDS);
        }
        if (this.readTimeout > 0) {
            LogUtils.d("readTimeout = " + this.readTimeout);
            builder.readTimeout(this.readTimeout, TimeUnit.MILLISECONDS);
        }
    }

    private OkHttpClient tryBuildNewClient() {
        if (this.networkInterceptors.size() == 0 && this.interceptors.size() == 0 && this.connectTimeout == 0 && this.readTimeout == 0 && this.writeTimeout == 0 && this.connectionKeepAliveMillis == -1 && TextUtils.isEmpty(this.forceIp)) {
            return getOkHttpClient();
        }
        OkHttpClient okHttpClient = getOkHttpClient();
        OkHttpClient.Builder builderNewBuilder = okHttpClient.newBuilder();
        addInterceptors(builderNewBuilder);
        addNetworkInterceptors(builderNewBuilder);
        setTimeout(builderNewBuilder);
        setForceIp(builderNewBuilder);
        applyConnectionKeepALive(okHttpClient);
        return builderNewBuilder.build();
    }

    public T addFormDataPart(String str, String str2) {
        this.formDataParts.put(str, str2);
        return this;
    }

    protected T addFormDataPart(String str, String str2, RequestBody requestBody) {
        this.formDataPartBeans.add(new FormDataPartBean(str, str2, requestBody));
        return this;
    }

    @Override
    public T addQueryParam(String str, String str2) {
        this.queryParams.put(str, str2);
        return this;
    }

    protected void callRequestAsync(OkHttpClient okHttpClient, Callback callback) {
        newCall(okHttpClient).enqueue(callback);
    }

    protected void callRequestSync(OkHttpClient okHttpClient, Callback callback) {
        try {
            callback.onResponse(this.call, newCall(okHttpClient).execute());
        } catch (IOException e) {
            callback.onFailure(this.call, e);
        }
    }

    @Override
    public T cancel() {
        Call call = this.call;
        if (call != null && !call.isCanceled()) {
            this.call.cancel();
        }
        return this;
    }

    public void clearAllQueryParams() {
        this.queryParams.clear();
    }

    protected T clearFormDataPart() {
        this.formDataPartBeans.clear();
        return this;
    }

    public T m22clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Callback getCallback() {
        return this.callback;
    }

    protected OkHttpClient getDefaultOkHttpClient() {
        return OkHttpClientManager.getInstance().getDefaultRequestClient();
    }

    public RequestBody getRequestBody() {
        Request request = this.request;
        if (request != null) {
            return request.body();
        }
        return null;
    }

    public String getRequestBodyMessage() {
        return "requestBodyString: " + this.requestBodyString + " requestBodyMediaTypeString: " + this.requestBodyMediaTypeString;
    }

    @Override
    public String getRequestBodyString() {
        return this.requestBodyString;
    }

    @Override
    public String getRequestHeader(String str) {
        return OkHttpUtil.getRequestHeader(this.request, str);
    }

    @Override
    public Map getRequestHeadersMap() {
        return OkHttpUtil.getRequestHeadersMap(this.request);
    }

    @Override
    public String getRequestHeadersString() {
        return OkHttpUtil.getRequestHeadersString(this.request);
    }

    @Override
    public String getRequestHost() {
        Request request = this.request;
        return request != null ? request.url().host() : "requestIsNull";
    }

    @Override
    public String getRequestUrl() {
        Request request = this.request;
        return request != null ? request.url().toString() : getUrl();
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    public void gzipRequestBody() {
        this.interceptors.add(new GzipRequestInterceptor());
    }

    @Override
    public boolean isAlreadyRequested(String str) {
        Dispatcher dispatcher = getDefaultOkHttpClient().dispatcher();
        return isAlreadyRequested(dispatcher.runningCalls(), str) || isAlreadyRequested(dispatcher.queuedCalls(), str);
    }

    @Override
    public boolean isCanceled() {
        Call call = this.call;
        if (call != null) {
            return call.isCanceled();
        }
        return false;
    }

    @Override
    public void request() {
        OkHttpClient okHttpClientTryBuildNewClient = tryBuildNewClient();
        try {
            this.request = buildRequest();
            if (this.isAsync) {
                callRequestAsync(okHttpClientTryBuildNewClient, getCallback());
            } else {
                callRequestSync(okHttpClientTryBuildNewClient, getCallback());
            }
        } catch (HttpUrlNullPointerException e) {
            LogUtils.i("HttpUrlNullPointerException", e);
            if (getCallback() != null) {
                getCallback().onFailure(null, e);
            }
        }
    }

    public T setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public T setCallerOkHttpClient(OkHttpClient okHttpClient) {
        this.callerOkHttpClient = okHttpClient;
        return this;
    }

    @Override
    public Object setConnectTimeout(long j) {
        return super.setConnectTimeout(j);
    }

    protected T setFormDataParts(Map<String, String> map) {
        if (map != null) {
            this.formDataParts = map;
        }
        return this;
    }

    public T setFormField(Map<String, String> map) {
        if (map != null) {
            this.formFields = map;
        }
        return this;
    }

    @Override
    public T setInterceptors(List<Interceptor> list) {
        if (list != null) {
            this.interceptors = list;
        }
        return this;
    }

    @Override
    public Object setInterceptors(List list) {
        return setInterceptors((List<Interceptor>) list);
    }

    @Override
    public T setNetworkInterceptor(final Interceptor interceptor) {
        this.networkInterceptors = new ArrayList<Interceptor>() {
            {
                add(interceptor);
            }
        };
        return this;
    }

    @Override
    public T setNetworkInterceptors(List<Interceptor> list) {
        this.networkInterceptors = list;
        return this;
    }

    @Override
    public Object setNetworkInterceptors(List list) {
        return setNetworkInterceptors((List<Interceptor>) list);
    }

    @Override
    public T setQueryParams(Map<String, String> map) {
        this.queryParams = map;
        return this;
    }

    @Override
    public Object setQueryParams(Map map) {
        return setQueryParams((Map<String, String>) map);
    }

    @Override
    public Object setReadTimeout(long j) {
        return super.setReadTimeout(j);
    }

    @Override
    public T setRequestBody(Object obj) {
        return (T) setRequestBody(CommonUtil.objectToString(obj));
    }

    @Override
    public T setRequestBody(String str) {
        return (T) setRequestBody("application/json", str);
    }

    @Override
    public T setRequestBody(String str, Object obj) {
        return (T) setRequestBody(str, CommonUtil.objectToString(obj));
    }

    @Override
    public T setRequestBody(String str, String str2) {
        this.requestBodyMediaTypeString = str;
        this.requestBodyString = str2;
        this.requestBody = RequestBody.create(MediaType.parse(str), str2);
        return this;
    }

    @Override
    public Object setTimeout(long j) {
        return super.setTimeout(j);
    }

    @Override
    public Object setWriteTimeout(long j) {
        return super.setWriteTimeout(j);
    }

    @Override
    public <TagType> T tag(Class<? super TagType> cls, TagType tagtype) {
        this.requestBuilder.tag(cls, tagtype);
        return this;
    }

    @Override
    public <TagType> TagType tag(Class<? extends TagType> cls) {
        return (TagType) this.requestBuilder.tag(cls);
    }

    @Override
    public Object tag(Class cls, Object obj) {
        return tag((Class<? super Class>) cls, (Class) obj);
    }
}
