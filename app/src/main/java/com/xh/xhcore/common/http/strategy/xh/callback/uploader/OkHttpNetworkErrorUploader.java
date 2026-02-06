package com.xh.xhcore.common.http.strategy.xh.callback.uploader;

import android.text.TextUtils;
import com.xh.xhcore.common.extension.OkHttpExtensionKt;
import com.xh.xhcore.common.http.strategy.BaseRequest;
import com.xh.xhcore.common.http.strategy.okhttp.HttpEventListener;
import com.xh.xhcore.common.http.strategy.okhttp.OkHttpUtil;
import com.xh.xhcore.common.http.strategy.okhttp.ProgressRequestBody;
import com.xh.xhcore.common.http.strategy.okhttp.ProgressResponseBody;
import com.xh.xhcore.common.http.strategy.okhttp.download.BaseDownloadOkHttp;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestTagsUtil;
import com.xh.xhcore.common.http.strategy.okhttp.upload.BaseUploadOkHttp;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 -2\u00020\u0001:\u0001-B\u001d\u0012\u0016\u0010\u0002\u001a\u00120\u0003R\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0006\u0010 \u001a\u00020\u001fJ\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u001fH\u0002J\b\u0010$\u001a\u00020\bH\u0002J\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020'0&J\u0006\u0010(\u001a\u00020\u0015J\b\u0010)\u001a\u00020\u0015H\u0002J\u0012\u0010*\u001a\u00020\u001f2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R!\u0010\u0002\u001a\u00120\u0003R\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006."}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/OkHttpNetworkErrorUploader;", "Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/DownStreamOrOssNetworkErrorUploader;", "xhBaseRequestCallbackInC", "Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy$XHBaseRequestCallbackInC;", "Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;", "(Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy$XHBaseRequestCallbackInC;)V", "NO_NEED_UPLOAD_HTTP_STATUS_CODE", "", "", "getNO_NEED_UPLOAD_HTTP_STATUS_CODE", "()Ljava/util/List;", "connectionDuration", "", "getConnectionDuration", "()J", "setConnectionDuration", "(J)V", "dnsDuration", "getDnsDuration", "setDnsDuration", "httpEventMessage", "", "getHttpEventMessage", "()Ljava/lang/String;", "setHttpEventMessage", "(Ljava/lang/String;)V", "getXhBaseRequestCallbackInC", "()Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy$XHBaseRequestCallbackInC;", "allowUploadNetworkErrorLog", "", "fillDownloadMessage", "", "fillExtraNetworkErrorMessageFromTag", "fillHttpDetail", "Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/BaseNetworkErrorUploader;", "fillUploadMessage", "getHttpStatusCode", "getResponseHeadersMap", "", "", "getResponseHeadersString", "getUploadResponseBody", "reflectHttpEventListenerAndSetNetworkDuration", "call", "Lokhttp3/Call;", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OkHttpNetworkErrorUploader extends DownStreamOrOssNetworkErrorUploader {

    public static final Companion INSTANCE = new Companion(null);
    private final List<Integer> NO_NEED_UPLOAD_HTTP_STATUS_CODE;
    private long connectionDuration;
    private long dnsDuration;
    private String httpEventMessage;
    private final XHBaseRequestProxy<?, ?, ?>.XHBaseRequestCallbackInC xhBaseRequestCallbackInC;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/OkHttpNetworkErrorUploader$Companion;", "", "()V", "reflectHttpEventListener", "Lcom/xh/xhcore/common/http/strategy/okhttp/HttpEventListener;", "call", "Lokhttp3/Call;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final HttpEventListener reflectHttpEventListener(Call call) {
            if (call == null) {
                return null;
            }
            return OkHttpExtensionKt.reflectHttpEventListener(call);
        }
    }

    public OkHttpNetworkErrorUploader(XHBaseRequestProxy<?, ?, ?>.XHBaseRequestCallbackInC xHBaseRequestCallbackInC) {
        Intrinsics.checkNotNullParameter(xHBaseRequestCallbackInC, "xhBaseRequestCallbackInC");
        XHBaseRequestProxy xHRequestProxy = xHBaseRequestCallbackInC.getXHRequestProxy();
        Intrinsics.checkNotNullExpressionValue(xHRequestProxy, "xhBaseRequestCallbackInC.xhRequestProxy");
        super(xHRequestProxy);
        this.NO_NEED_UPLOAD_HTTP_STATUS_CODE = CollectionsKt.listOf(401);
        this.connectionDuration = -1L;
        this.dnsDuration = -1L;
        this.httpEventMessage = "";
        this.xhBaseRequestCallbackInC = xHBaseRequestCallbackInC;
    }

    private final void fillDownloadMessage() {
        if (this.xhBaseRequestCallbackInC.getXHRequestProxy().getBaseRequest() instanceof BaseDownloadOkHttp) {
            Response response = this.xhBaseRequestCallbackInC.getResponse();
            ResponseBody responseBodyBody = response == null ? null : response.body();
            if (responseBodyBody == null || !(responseBodyBody instanceof ProgressResponseBody)) {
                return;
            }
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBodyBody;
            if (progressResponseBody.getBaseForwardingSource() != null) {
                LinkedHashMap<String, Object> basicHttpDetailMap = getBasicHttpDetailMap();
                String mainMessage = progressResponseBody.getBaseForwardingSource().toMainMessage();
                Intrinsics.checkNotNullExpressionValue(mainMessage, "responseBody.baseForwardingSource.toMainMessage()");
                basicHttpDetailMap.put("downloadMessage", mainMessage);
            }
        }
    }

    private final void fillUploadMessage() {
        RequestBody requestBody;
        BaseRequest baseRequest = this.xhBaseRequestCallbackInC.getXHRequestProxy().getBaseRequest();
        if ((baseRequest instanceof BaseUploadOkHttp) && (requestBody = ((BaseUploadOkHttp) baseRequest).getRequestBody()) != null && (requestBody instanceof ProgressRequestBody)) {
            ProgressRequestBody progressRequestBody = (ProgressRequestBody) requestBody;
            if (progressRequestBody.getBaseForwardingSink() != null) {
                LinkedHashMap<String, Object> basicHttpDetailMap = getBasicHttpDetailMap();
                String mainMessage = progressRequestBody.getBaseForwardingSink().toMainMessage();
                Intrinsics.checkNotNullExpressionValue(mainMessage, "requestBody.baseForwardingSink.toMainMessage()");
                basicHttpDetailMap.put("uploadMessage", mainMessage);
            }
        }
    }

    private final int getHttpStatusCode() {
        if (this.xhBaseRequestCallbackInC.getResponse() == null) {
            return -1;
        }
        Response response = this.xhBaseRequestCallbackInC.getResponse();
        Intrinsics.checkNotNull(response);
        return response.code();
    }

    private final String getUploadResponseBody() {
        String responseBodyString = this.xhBaseRequestCallbackInC.getResponseBodyString();
        Intrinsics.checkNotNullExpressionValue(responseBodyString, "xhBaseRequestCallbackInC.responseBodyString");
        if (TextUtils.isEmpty(responseBodyString)) {
            return "";
        }
        if (responseBodyString.length() <= 4096) {
            return responseBodyString;
        }
        String strSubstring = responseBodyString.substring(0, 4096);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmStatic
    public static final HttpEventListener reflectHttpEventListener(Call call) {
        return INSTANCE.reflectHttpEventListener(call);
    }

    private final void reflectHttpEventListenerAndSetNetworkDuration(Call call) {
        if (call != null) {
            try {
                HttpEventListener httpEventListenerReflectHttpEventListener = OkHttpExtensionKt.reflectHttpEventListener(call);
                if (httpEventListenerReflectHttpEventListener != null) {
                    this.connectionDuration = httpEventListenerReflectHttpEventListener.getConnectionDuration();
                    this.dnsDuration = httpEventListenerReflectHttpEventListener.getDnsDuration();
                    String httpEventMessage = httpEventListenerReflectHttpEventListener.getHttpEventMessage();
                    Intrinsics.checkNotNullExpressionValue(httpEventMessage, "httpEventListener.httpEventMessage");
                    this.httpEventMessage = httpEventMessage;
                }
            } catch (Throwable th) {
                appendErrorMessage(th);
            }
        }
    }

    @Override
    public boolean allowUploadNetworkErrorLog() {
        XHBaseRequestProxy xHRequestProxy = this.xhBaseRequestCallbackInC.getXHRequestProxy();
        Intrinsics.checkNotNullExpressionValue(xHRequestProxy, "xhBaseRequestCallbackInC.xhRequestProxy");
        if (!RequestTagsUtil.isUserCancelNetwork(xHRequestProxy) && !this.NO_NEED_UPLOAD_HTTP_STATUS_CODE.contains(Integer.valueOf(getHttpStatusCode()))) {
            XHBaseRequestProxy xHRequestProxy2 = this.xhBaseRequestCallbackInC.getXHRequestProxy();
            Intrinsics.checkNotNullExpressionValue(xHRequestProxy2, "xhBaseRequestCallbackInC.xhRequestProxy");
            if (RequestTagsUtil.allowUploadNetworkErrorLogFromConfig(xHRequestProxy2)) {
                return true;
            }
        }
        return false;
    }

    public final void fillExtraNetworkErrorMessageFromTag() {
        getBasicHttpDetailMap().putAll(RequestTagsUtil.getExtraNetworkErrorMessageFromTag(this.xhBaseRequestCallbackInC.getRequest()));
    }

    @Override
    public BaseNetworkErrorUploader fillHttpDetail() {
        reflectHttpEventListenerAndSetNetworkDuration(this.xhBaseRequestCallbackInC.getCall());
        super.fillHttpDetail();
        getBasicHttpDetailMap().put("httpStatusCode", String.valueOf(getHttpStatusCode()));
        getBasicHttpDetailMap().put("responseHeaders", getResponseHeadersMap());
        getBasicHttpDetailMap().put("responseBody", getUploadResponseBody());
        getBasicHttpDetailMap().put("connectionDuration", Long.valueOf(this.connectionDuration));
        getBasicHttpDetailMap().put("dnsDuration", Long.valueOf(this.dnsDuration));
        getBasicHttpDetailMap().put("httpEventMessage", this.httpEventMessage);
        fillUploadMessage();
        fillDownloadMessage();
        fillExtraNetworkErrorMessageFromTag();
        return this;
    }

    public final long getConnectionDuration() {
        return this.connectionDuration;
    }

    public final long getDnsDuration() {
        return this.dnsDuration;
    }

    public final String getHttpEventMessage() {
        return this.httpEventMessage;
    }

    public final List<Integer> getNO_NEED_UPLOAD_HTTP_STATUS_CODE() {
        return this.NO_NEED_UPLOAD_HTTP_STATUS_CODE;
    }

    public final Map<String, Object> getResponseHeadersMap() {
        if (this.xhBaseRequestCallbackInC.getResponse() == null) {
            return new HashMap();
        }
        Response response = this.xhBaseRequestCallbackInC.getResponse();
        Intrinsics.checkNotNull(response);
        Headers headers = response.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "xhBaseRequestCallbackInC.response!!.headers()");
        return OkHttpUtil.headersToMap(headers);
    }

    public final String getResponseHeadersString() {
        return OkHttpUtil.mapToJsonString(getResponseHeadersMap());
    }

    public final XHBaseRequestProxy<?, ?, ?>.XHBaseRequestCallbackInC getXhBaseRequestCallbackInC() {
        return this.xhBaseRequestCallbackInC;
    }

    public final void setConnectionDuration(long j) {
        this.connectionDuration = j;
    }

    public final void setDnsDuration(long j) {
        this.dnsDuration = j;
    }

    public final void setHttpEventMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.httpEventMessage = str;
    }
}
