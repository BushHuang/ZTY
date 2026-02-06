package com.xh.xhcore.common.http.strategy.xh;

import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xh.networkclient.common.CommonUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.ServerNetworkConfig;
import com.xh.xhcore.common.helper.ServerNetworkConfigHelper;
import com.xh.xhcore.common.http.XHErrorCodeUtil;
import com.xh.xhcore.common.http.XHHttpUtil;
import com.xh.xhcore.common.http.archi.CommonUtil;
import com.xh.xhcore.common.http.archi.ThreadUtil;
import com.xh.xhcore.common.http.strategy.BaseRequest;
import com.xh.xhcore.common.http.strategy.IRequest;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.exception.HttpUrlNullPointerException;
import com.xh.xhcore.common.http.strategy.okhttp.HttpDetailCollector;
import com.xh.xhcore.common.http.strategy.okhttp.HttpEventListener;
import com.xh.xhcore.common.http.strategy.okhttp.callback.BaseCallback;
import com.xh.xhcore.common.http.strategy.okhttp.ignore.TagRequestIdentity;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestTagsUtil;
import com.xh.xhcore.common.http.strategy.okhttp.request.BaseRequestOkHttp;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.callback.BaseNetworkFailedHandlerCallback;
import com.xh.xhcore.common.http.strategy.xh.callback.uploader.DownStreamOrOssNetworkErrorUploader;
import com.xh.xhcore.common.http.strategy.xh.callback.uploader.OkHttpNetworkErrorUploader;
import com.xh.xhcore.common.http.strategy.xh.config.ResponseSignTag;
import com.xh.xhcore.common.http.strategy.xh.config.UserCancelNetwork;
import com.xh.xhcore.common.http.strategy.xh.config.msg.ProxyErrorMessage;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestUtil;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHNetworkUtil;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public abstract class XHBaseRequestProxy<T extends XHBaseRequestProxy, R extends BaseRequest, C extends BaseNetworkFailedHandlerCallback> implements IRequest<T> {
    protected R baseRequest;
    private C callback;
    private int id;

    @Deprecated
    protected String requestJsonParams;
    private boolean needAddDefaultHeaders = true;
    protected boolean exchangeToMainThread = true;
    private DownStreamOrOssNetworkErrorUploader downStreamOrOssNetworkErrorUploader = new DownStreamOrOssNetworkErrorUploader(this);

    public abstract class XHBaseRequestCallbackInC extends BaseCallback {
        protected Headers responseHeaders;
        protected String responseBodyString = "";
        protected int fileServerStatus = CommonUtils.fsSuccessCode;
        private OkHttpNetworkErrorUploader okHttpNetworkErrorUploader = new OkHttpNetworkErrorUploader(this);

        public XHBaseRequestCallbackInC() {
        }

        private void callOnEvent() {
            this.okHttpNetworkErrorUploader.setCode(responseCodeToBusinessCode());
            setProxyDescription();
            onEvent();
        }

        private String getUrlHost(String str) {
            Matcher matcher = Pattern.compile("(http[s]?://)?([\\w\\d.]*)(:\\d+)?(?=/)").matcher(str);
            return matcher.find() ? matcher.group(2) : str;
        }

        private void handleSocketTimeoutOrConnectException() {
            cancelCall();
            try {
                if (isPingUrlServerSuccess()) {
                    setCodeAndDescription(107004000);
                } else {
                    setCodeAndDescription(107004001);
                }
            } catch (Exception e) {
                this.okHttpNetworkErrorUploader.appendErrorMessage(e);
                setCodeAndDescription(107001201);
                e.printStackTrace();
            }
        }

        private void initFromResponse(Response response) {
            this.okHttpNetworkErrorUploader.setCode(response.code());
            this.responseHeaders = response.headers();
        }

        private boolean isPingUrlServerSuccess() throws InterruptedException, IOException {
            if (!TextUtils.isEmpty(getRequestUrl())) {
                String ipAddress = getIpAddress(getUrlHost(getRequestUrl()));
                if (!TextUtils.isEmpty(ipAddress)) {
                    return XHHttpUtil.pingAddress(ipAddress, 1, 2);
                }
            }
            return false;
        }

        private int responseCodeToBusinessCode() {
            if (getCode() >= 107000000) {
                return getCode();
            }
            if (isResponseSuccessful(getCode())) {
                return 107000000;
            }
            return getCode() + 107000000;
        }

        private void sendIOException(IOException iOException) {
            this.okHttpNetworkErrorUploader.appendErrorMessage(iOException);
            if (isUserCancelNetwork()) {
                setCodeAndDescription(107001301);
            } else if ((iOException instanceof SocketTimeoutException) || (iOException instanceof ConnectException)) {
                handleSocketTimeoutOrConnectException();
            } else if (iOException instanceof UnknownHostException) {
                setCodeAndDescription(107000006);
            } else if (iOException instanceof HttpUrlNullPointerException) {
                setCodeAndDescription(107001016);
            } else {
                setCode(107001202);
                setDescription("网络IO异常, 类型: " + iOException.getClass().getSimpleName() + " 原因: " + iOException.getMessage());
            }
            callOnEvent();
        }

        private void setCode(int i) {
            this.okHttpNetworkErrorUploader.setCode(i);
        }

        private void setDescription(String str) {
            this.okHttpNetworkErrorUploader.setDescription(str);
        }

        private void setProxyDescription() {
            RequestTagsUtil.putProxyMessageTag(getCall());
            ProxyErrorMessage proxyErrorMessage = (ProxyErrorMessage) RequestTagsUtil.getTag(getRequest(), ProxyErrorMessage.class);
            if (proxyErrorMessage == null || TextUtils.isEmpty(proxyErrorMessage.getMsg())) {
                return;
            }
            this.okHttpNetworkErrorUploader.setProxyDescription(proxyErrorMessage.getMsg());
        }

        protected void deleteTempFile() {
        }

        protected final void failedInnerDefault() {
            XHBaseRequestProxy.this.lambda$failedWithThreadChecked$0$XHBaseRequestProxy(getCode(), getOkHttpNetworkErrorUploader().getTheDescription(getCode()));
        }

        protected final void failedUploadInnerDefault() {
            if (XHBaseRequestProxy.this.getCallback() != null) {
                XHBaseRequestProxy.this.getCallback().failedUploadInner(getCode(), "", new Throwable(), getOkHttpNetworkErrorUploader());
            }
        }

        public int getCode() {
            return this.okHttpNetworkErrorUploader.getCode();
        }

        protected String getContentEncodingFromResponseHeader() {
            return this.responseHeaders.get("Content-Encoding");
        }

        public String getDescription() {
            return this.okHttpNetworkErrorUploader.getDescription();
        }

        protected final Throwable getFailedThrowable() {
            if (isBusinessSuccessful()) {
                return null;
            }
            return new Throwable();
        }

        public HttpEventListener getHttpEventListener() {
            if (getCall() != null) {
                return OkHttpNetworkErrorUploader.reflectHttpEventListener(getCall());
            }
            return null;
        }

        public String getIpAddress(String str) {
            try {
                return InetAddress.getByName(str).getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return "";
            }
        }

        protected String getMd5FromResponseHeader() {
            return this.responseHeaders.get("ETag");
        }

        public OkHttpNetworkErrorUploader getOkHttpNetworkErrorUploader() {
            return this.okHttpNetworkErrorUploader;
        }

        public abstract String getResponseBodyString();

        public XHBaseRequestProxy getXHRequestProxy() {
            return XHBaseRequestProxy.this;
        }

        protected void gzipUnCompressResponseBodyString() {
        }

        protected boolean isBusinessSuccessful() {
            return getCode() == CommonUtils.baseCode;
        }

        protected final boolean isUserCancelNetwork() {
            return RequestTagsUtil.isUserCancelNetwork(XHBaseRequestProxy.this);
        }

        protected abstract void onEvent();

        @Override
        public void onFailure(Call call, IOException iOException) {
            super.onFailure(call, iOException);
            deleteTempFile();
            sendIOException(iOException);
        }

        @Override
        public final void onResponse(Call call, Response response) throws IOException {
            super.onResponse(call, response);
            initFromResponse(response);
            try {
                readResponseBodyContent(response);
                gzipUnCompressResponseBodyString();
                verifyFileMd5();
                updateFileServerStatus();
                deleteTempFile();
                callOnEvent();
            } catch (IOException e) {
                LogUtils.i("[readResponseBodyContent] stackTrace ", e);
                deleteTempFile();
                sendIOException(e);
            }
        }

        protected void readResponseBodyContent(Response response) throws IOException {
            if (response.body() != null) {
                this.responseBodyString = HttpDetailCollector.getResponseBodyStringBuilder(response.headers(), response.body()).toString();
            }
        }

        public final void setCodeAndDescription(int i) {
            setCode(i);
            setDescription(XHErrorCodeUtil.getErrorMsgInfo(i));
        }

        protected void updateFileServerStatus() {
        }

        protected final void uploadNetworkError() {
            uploadNetworkError(new Throwable());
        }

        protected final void uploadNetworkError(Throwable th) {
            this.okHttpNetworkErrorUploader.uploadNetworkError(th);
        }

        protected void verifyFileMd5() {
        }
    }

    protected XHBaseRequestProxy(R r) {
        this.baseRequest = r;
    }

    private boolean checkNetwork() {
        if (XHNetworkUtil.isNetworkAvailable(XhBaseApplication.mContext)) {
            return true;
        }
        failedWithThreadChecked(-108000001, XHErrorCodeUtil.getErrorMsgInfo(-108000001));
        return false;
    }

    private Callback getOkHttpCallback() {
        R r = this.baseRequest;
        if (r instanceof BaseRequestOkHttp) {
            return ((BaseRequestOkHttp) r).getCallback();
        }
        return null;
    }

    private OkHttpNetworkErrorUploader getUpStreamOkHttpNetworkErrorUploader() {
        Callback okHttpCallback = getOkHttpCallback();
        if (okHttpCallback instanceof XHBaseRequestCallbackInC) {
            return ((XHBaseRequestCallbackInC) okHttpCallback).getOkHttpNetworkErrorUploader();
        }
        return null;
    }

    @Deprecated
    private void handleRequestJsonParams() {
        JsonObject jsonObjectStringToJsonObject = JsonUtil.stringToJsonObject(this.requestJsonParams);
        if (jsonObjectStringToJsonObject == null || jsonObjectStringToJsonObject.size() <= 0) {
            return;
        }
        parseTimeoutInRequestJsonParams(jsonObjectStringToJsonObject);
        parseHeaderListInRequestJsonParams(jsonObjectStringToJsonObject);
    }

    @Override
    public T addHeader(String str, Object obj) {
        this.baseRequest.addHeader(str, obj);
        return this;
    }

    protected void applyServerNetworkConfig(ServerNetworkConfig serverNetworkConfig) {
        if (serverNetworkConfig.isConnectionKeepAliveMillisValid()) {
            this.baseRequest.setConnectionKeepAliveMillis(serverNetworkConfig.getConnectionKeepAliveMillis());
        }
    }

    public void beforeRequest() {
        handleRequestJsonParams();
        applyServerNetworkConfig(ServerNetworkConfigHelper.getServerConfig());
    }

    protected final void callProxyRequest() {
        if (this.needAddDefaultHeaders) {
            this.baseRequest.setHeaders(combineUserHeaderAndDefaultHeader());
        }
        this.baseRequest.request();
    }

    @Override
    public T cancel() {
        tag((Class<? super Class>) UserCancelNetwork.class, (Class) new UserCancelNetwork(true));
        this.baseRequest.cancel();
        return this;
    }

    protected Map<String, String> combineUserHeaderAndDefaultHeader() {
        Map<String, String> defaultHeadersMap = XHRequestUtil.getDefaultHeadersMap();
        Map<String, Object> headers = this.baseRequest.getHeaders();
        if (headers != null && headers.size() != 0) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                defaultHeadersMap.put(entry.getKey(), CommonUtil.objectToString(entry.getValue()));
            }
        }
        return defaultHeadersMap;
    }

    protected final void lambda$failedWithThreadChecked$0$XHBaseRequestProxy(int i, String str) {
        if (getCallback() != null) {
            getCallback().failedInner(i, str);
        }
    }

    public final void failedWithThreadChecked(final int i, final String str) {
        toMainThreadChecked(new Runnable() {
            @Override
            public final void run() {
                this.f$0.lambda$failedWithThreadChecked$0$XHBaseRequestProxy(i, str);
            }
        });
    }

    public R getBaseRequest() {
        return this.baseRequest;
    }

    public C getCallback() {
        return this.callback;
    }

    public String getClientTraceId() {
        OkHttpNetworkErrorUploader upStreamOkHttpNetworkErrorUploader = getUpStreamOkHttpNetworkErrorUploader();
        return upStreamOkHttpNetworkErrorUploader != null ? upStreamOkHttpNetworkErrorUploader.getClientTraceId() : "";
    }

    public DownStreamOrOssNetworkErrorUploader getDownStreamOrOssNetworkErrorUploader() {
        return this.downStreamOrOssNetworkErrorUploader;
    }

    public HttpEventListener getHttpEventListener() {
        Callback okHttpCallback = getOkHttpCallback();
        if (okHttpCallback instanceof XHBaseRequestCallbackInC) {
            return ((XHBaseRequestCallbackInC) okHttpCallback).getHttpEventListener();
        }
        return null;
    }

    @Deprecated
    public int getId() {
        return this.id;
    }

    public String getNetworkErrorDetail() {
        OkHttpNetworkErrorUploader upStreamOkHttpNetworkErrorUploader = getUpStreamOkHttpNetworkErrorUploader();
        return upStreamOkHttpNetworkErrorUploader != null ? upStreamOkHttpNetworkErrorUploader.getNetworkErrorDetail() : "";
    }

    @Deprecated
    public String getRequestJsonParams() {
        return this.requestJsonParams;
    }

    public Integer getResponseStatusCode() {
        Callback okHttpCallback = getOkHttpCallback();
        if (okHttpCallback instanceof BaseCallback) {
            return ((BaseCallback) okHttpCallback).getResponseStatusCode();
        }
        return null;
    }

    @Override
    public String getUrl() {
        return this.baseRequest.getUrl();
    }

    @Override
    public boolean isCanceled() {
        return this.baseRequest.isCanceled();
    }

    public boolean isExchangeToMainThread() {
        return this.exchangeToMainThread && this.baseRequest.isAsync();
    }

    public T needVerifyResponseSign() {
        tag((Class<? super Class>) ResponseSignTag.class, (Class) new ResponseSignTag(true));
        return this;
    }

    @Deprecated
    public void parseHeaderListInRequestJsonParams(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("headList");
        if (jsonElement == null || !jsonElement.isJsonPrimitive()) {
            return;
        }
        String asString = jsonElement.getAsString();
        if (TextUtils.isEmpty(asString)) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : JsonUtil.stringToJsonObject(asString).entrySet()) {
            this.baseRequest.addHeader(entry.getKey(), entry.getValue().getAsString());
        }
    }

    @Deprecated
    public void parseTimeoutInRequestJsonParams(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("timeout");
        if (jsonElement == null || !jsonElement.isJsonPrimitive()) {
            return;
        }
        int asInt = jsonElement.getAsInt();
        if (asInt > 1000) {
            asInt /= 1000;
        }
        setTimeoutInRequestJsonParams(asInt);
    }

    protected final void refreshId() {
        this.id = CommonUtils.getId();
    }

    public T request() {
        TagRequestIdentity tagRequestIdentity = (TagRequestIdentity) tag(TagRequestIdentity.class);
        if (tagRequestIdentity != null && !TextUtils.isEmpty(tagRequestIdentity.getRequestIdentity()) && this.baseRequest.isAlreadyRequested(tagRequestIdentity.getRequestIdentity())) {
            getCallback().onIgnore("requestIdentity is already running or waiting");
            return this;
        }
        if (!checkNetwork()) {
            return this;
        }
        beforeRequest();
        T t = (T) requestInner();
        LogUtils.d("xhNetworkExecuteStateTag", "xhNetworkStart url = " + this.baseRequest.getRequestUrl());
        return t;
    }

    protected abstract T requestInner();

    public T setCallback(C c) {
        if (c != null) {
            c.setXhBaseRequestProxy(this);
            this.callback = c;
        }
        return this;
    }

    public T setConnectTimeout(long j) {
        this.baseRequest.setConnectTimeout(j);
        return this;
    }

    public T setExchangeToMainThread(boolean z) {
        this.exchangeToMainThread = z;
        return this;
    }

    public T setForceIp(String str) {
        this.baseRequest.setForceIp(str);
        return this;
    }

    @Override
    public T setHeaders(Map<String, Object> map) {
        this.baseRequest.setHeaders(map);
        return this;
    }

    @Override
    public Object setHeaders(Map map) {
        return setHeaders((Map<String, Object>) map);
    }

    @Override
    public T setIsAsync(boolean z) {
        this.baseRequest.setIsAsync(z);
        return this;
    }

    @Override
    public T setMethod(String str) {
        this.baseRequest.setMethod(str);
        return this;
    }

    public T setNeedAddDefaultHeaders(boolean z) {
        this.needAddDefaultHeaders = z;
        return this;
    }

    public T setReadTimeout(long j) {
        this.baseRequest.setReadTimeout(j);
        return this;
    }

    public T setRequestIdentityForAntiShake(String str) {
        tag((Class<? super Class>) TagRequestIdentity.class, (Class) new TagRequestIdentity(str));
        return this;
    }

    public T setRequestJsonParams(String str) {
        this.requestJsonParams = str;
        return this;
    }

    public T setTimeout(long j) {
        this.baseRequest.setTimeout(j);
        return this;
    }

    @Deprecated
    protected void setTimeoutInRequestJsonParams(int i) {
        this.baseRequest.setReadTimeout(i * 1000);
    }

    @Override
    public T setUrl(String str) {
        this.baseRequest.setUrl(str);
        return this;
    }

    public T setWriteTimeout(long j) {
        this.baseRequest.setWriteTimeout(j);
        return this;
    }

    @Override
    public <TagType> T tag(Class<? super TagType> cls, TagType tagtype) {
        this.baseRequest.tag(cls, tagtype);
        return this;
    }

    @Override
    public <TagType> TagType tag(Class<? extends TagType> cls) {
        return (TagType) this.baseRequest.tag(cls);
    }

    @Override
    public Object tag(Class cls, Object obj) {
        return tag((Class<? super Class>) cls, (Class) obj);
    }

    protected final void toMainThreadChecked(Runnable runnable) {
        ThreadUtil.toMainThreadChecked(isExchangeToMainThread(), getCallback(), runnable);
    }
}
