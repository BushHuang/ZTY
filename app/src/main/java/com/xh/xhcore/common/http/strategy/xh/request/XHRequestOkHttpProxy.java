package com.xh.xhcore.common.http.strategy.xh.request;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.xh.networkclient.bean.FileRes;
import com.xh.networkclient.common.CommonUtils;
import com.xh.xhcore.common.config.ServerNetworkConfig;
import com.xh.xhcore.common.http.XHErrorCodeUtil;
import com.xh.xhcore.common.http.XHHttpUtil;
import com.xh.xhcore.common.http.XHMicroServer;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.XhJsonParse;
import com.xh.xhcore.common.http.archi.ThreadUtil;
import com.xh.xhcore.common.http.strategy.okhttp.request.IOkHttpRequest;
import com.xh.xhcore.common.http.strategy.okhttp.request.RequestOkHttp;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.Interceptor;
import org.json.JSONException;
import org.json.JSONObject;

public class XHRequestOkHttpProxy extends XHRequestProxy<XHRequestOkHttpProxy, RequestOkHttp> implements IOkHttpRequest<XHRequestOkHttpProxy> {
    private int classId;
    private String frontAppendWithServerTypeUrl;

    private class XHRequestCallbackInC extends XHBaseRequestProxy<XHRequestOkHttpProxy, RequestOkHttp, XHRequestCallBack.HttpCallBack>.XHBaseRequestCallbackInC {
        private XHRequestCallbackInC() {
            super();
        }

        private void callFailed() throws JSONException {
            JSONObject jSONObject = new JSONObject(this.responseBodyString);
            if (jSONObject.has("msg")) {
                XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), XHRequestUtil.getErrorMsg(getCode(), jSONObject.get("msg").toString()), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
                return;
            }
            if (!jSONObject.has("traceId")) {
                XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), XHRequestUtil.getErrorMsg(getCode(), this.responseBodyString), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
                return;
            }
            XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), XHRequestUtil.getErrorMsg(getCode(), "traceId=" + jSONObject.get("traceId") + ",服务器错误！"), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
        }

        private void executeKZCallBack() {
            if (getCode() != CommonUtils.baseCode) {
                failedCallBack();
                return;
            }
            XhJsonParse xhJsonParse = new XhJsonParse();
            xhJsonParse.parseDataWithKZ(this.responseBodyString);
            if (200 == xhJsonParse.getR()) {
                XHRequestOkHttpProxy.this.getCallback().successParse(xhJsonParse.getD(), getOkHttpNetworkErrorUploader());
            } else {
                XHRequestOkHttpProxy.this.getCallback().failedUploadInner(xhJsonParse.getR(), XHRequestUtil.getErrorMsg(xhJsonParse.getR(), xhJsonParse.getI()), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
            }
        }

        private void kzFailedCallBack() throws JSONException {
            String string;
            JSONObject jSONObject = new JSONObject(this.responseBodyString);
            if (jSONObject.has("I")) {
                string = jSONObject.get("I").toString();
                if (!TextUtils.isEmpty(string)) {
                    XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), XHRequestUtil.getErrorMsg(getCode(), jSONObject.get("I").toString()), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
                    return;
                }
            } else {
                string = "";
            }
            if (jSONObject.has("M")) {
                string = jSONObject.get("M").toString();
            }
            XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), XHRequestUtil.getErrorMsg(getCode(), string), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
        }

        private void tryReportFailCallback() throws JSONException {
            if (XHRequestOkHttpProxy.this.isRestful()) {
                restfullFailedCallBack();
            } else {
                kzFailedCallBack();
            }
        }

        protected void executeRestfulCallBack() {
            if (getCode() == CommonUtils.baseCode) {
                XHRequestOkHttpProxy.this.getCallback().successParse(this.responseBodyString, getOkHttpNetworkErrorUploader());
            } else {
                failedCallBack();
            }
        }

        protected void failedCallBack() {
            if (!isUserCancelNetwork() && XHErrorCodeUtil.CODE_DESCRIPTION_MAP.containsKey(Integer.valueOf(getCode()))) {
                if (!XHHttpUtil.isNeedPolling(getCode())) {
                    XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), getDescription(), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
                    return;
                } else if (XHMicroServer.getInstance().requestAgain(XHRequestOkHttpProxy.this.getServerType(), XHRequestOkHttpProxy.this.classId)) {
                    XHRequestOkHttpProxy.this.requestInner();
                    return;
                } else {
                    XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), getDescription(), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
                    return;
                }
            }
            if (TextUtils.isEmpty(this.responseBodyString)) {
                XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), getDescription(), this.responseBodyString, new Throwable(), getOkHttpNetworkErrorUploader());
                return;
            }
            try {
                tryReportFailCallback();
            } catch (JSONException e) {
                if (!XHRequestOkHttpProxy.this.isRestful() || getCode() != 107000400) {
                    XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), getDescription(), this.responseBodyString, new Throwable(e), getOkHttpNetworkErrorUploader());
                } else {
                    if (XHRequestOkHttpProxy.this.getCallback().businessFailed(getCode(), this.responseBodyString)) {
                        return;
                    }
                    XHRequestOkHttpProxy.this.getCallback().failedUploadInner(getCode(), getDescription(), this.responseBodyString, new Throwable(e), getOkHttpNetworkErrorUploader());
                }
            }
        }

        @Override
        public String getResponseBodyString() {
            return this.responseBodyString;
        }

        @Override
        protected void gzipUnCompressResponseBodyString() {
            if (XHRequestOkHttpProxy.this.isRestful() && "gzip".equals(getContentEncodingFromResponseHeader())) {
                this.responseBodyString = XhJsonParse.gzipUnCompressString(this.responseBodyString);
            }
        }

        public void lambda$onEvent$0$XHRequestOkHttpProxy$XHRequestCallbackInC() {
            if (XHRequestOkHttpProxy.this.isRestful()) {
                executeRestfulCallBack();
            } else {
                executeKZCallBack();
            }
        }

        @Override
        protected void onEvent() {
            ThreadUtil.toMainThreadChecked(XHRequestOkHttpProxy.this.isExchangeToMainThread(), XHRequestOkHttpProxy.this.getCallback(), new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onEvent$0$XHRequestOkHttpProxy$XHRequestCallbackInC();
                }
            });
        }

        public void restfullFailedCallBack() throws JSONException {
            if (getCode() != 107000400) {
                callFailed();
            } else {
                if (XHRequestOkHttpProxy.this.getCallback().businessFailed(getCode(), this.responseBodyString)) {
                    return;
                }
                callFailed();
            }
        }

        @Override
        protected void updateFileServerStatus() throws JSONException {
            if (!TextUtils.isEmpty(XHRequestOkHttpProxy.this.getFrontAppendWithServerTypeUrl()) && XHRequestOkHttpProxy.this.getFrontAppendWithServerTypeUrl().contains(CommonUtils.fsHostPrefix)) {
                try {
                    this.fileServerStatus = ((FileRes) new Gson().fromJson(new JSONObject(this.responseBodyString).getString("httpResponse"), FileRes.class)).getStatus();
                } catch (Exception e) {
                    e.printStackTrace();
                    this.fileServerStatus = 2000;
                }
            }
            if (this.fileServerStatus == 2500) {
                setCodeAndDescription(107002502);
            } else if (this.fileServerStatus != 1000) {
                setCodeAndDescription(107002002);
            }
        }
    }

    XHRequestOkHttpProxy(RequestOkHttp requestOkHttp) {
        super(requestOkHttp);
        this.classId = 0;
    }

    @Deprecated
    private String buildUsingMethodAndBodyData() {
        String businessUrl = getBusinessUrl();
        if (needCombineBodyDataIntoUrl(((RequestOkHttp) this.baseRequest).getMethod())) {
            return XHRequestUtil.getRequestUrl(businessUrl, getBodyData());
        }
        if (TextUtils.isEmpty(getBodyData())) {
            return businessUrl;
        }
        ((RequestOkHttp) this.baseRequest).setRequestBody(getBodyData());
        return businessUrl;
    }

    @Override
    public XHRequestOkHttpProxy addQueryParam(String str, String str2) {
        ((RequestOkHttp) this.baseRequest).addQueryParam(str, str2);
        return this;
    }

    @Override
    protected void applyServerNetworkConfig(ServerNetworkConfig serverNetworkConfig) {
        super.applyServerNetworkConfig(serverNetworkConfig);
        if (serverNetworkConfig.isSocketConnectionTimeoutValid()) {
            ((RequestOkHttp) this.baseRequest).setConnectTimeout(serverNetworkConfig.getSocketConnectTimeoutMillis());
        }
        if (serverNetworkConfig.isSocketWriteTimeoutValid()) {
            ((RequestOkHttp) this.baseRequest).setWriteTimeout(serverNetworkConfig.getSocketWriteTimeoutMillis());
        }
        if (serverNetworkConfig.isSocketReadTimeoutValid()) {
            ((RequestOkHttp) this.baseRequest).setReadTimeout(serverNetworkConfig.getSocketReadTimeoutMillis());
        }
    }

    public String getFrontAppendWithServerTypeUrl() {
        return this.frontAppendWithServerTypeUrl;
    }

    public XHRequestOkHttpProxy gzipRequestBody() {
        ((RequestOkHttp) this.baseRequest).gzipRequestBody();
        return this;
    }

    public void lambda$requestInner$0$XHRequestOkHttpProxy() {
        getCallback().failedUploadInner(107001206, "", new Throwable(), getDownStreamOrOssNetworkErrorUploader());
    }

    @Override
    protected XHRequestOkHttpProxy requestInner() {
        if (!isRestful()) {
            setMethod("POST");
        }
        String strBuildUsingMethodAndBodyData = buildUsingMethodAndBodyData();
        if (TextUtils.isEmpty(getServerType())) {
            this.frontAppendWithServerTypeUrl = strBuildUsingMethodAndBodyData;
        } else {
            String actualIp = XHMicroServer.getInstance().getActualIp(getServerType(), this.classId);
            if (TextUtils.isEmpty(actualIp)) {
                ThreadUtil.toMainThreadChecked(isExchangeToMainThread(), getCallback(), new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$requestInner$0$XHRequestOkHttpProxy();
                    }
                });
                return this;
            }
            this.frontAppendWithServerTypeUrl = actualIp + strBuildUsingMethodAndBodyData;
        }
        refreshId();
        String str = this.frontAppendWithServerTypeUrl;
        if (!isRestful()) {
            ((RequestOkHttp) this.baseRequest).setRequestBody("application/x-www-form-urlencoded; charset=UTF-8", String.format(Locale.getDefault(), "K=%s&Z=%d", getBodyData(), 0));
        }
        ((RequestOkHttp) ((RequestOkHttp) this.baseRequest).setUrl(str)).setCallback(new XHRequestCallbackInC());
        callProxyRequest();
        return this;
    }

    public XHRequestOkHttpProxy setClassId(int i) {
        this.classId = i;
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setConnectTimeout(long j) {
        return super.setConnectTimeout(j);
    }

    @Override
    public XHRequestOkHttpProxy setInterceptors(List<Interceptor> list) {
        ((RequestOkHttp) this.baseRequest).setInterceptors(list);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setInterceptors(List list) {
        return setInterceptors((List<Interceptor>) list);
    }

    @Override
    public XHRequestOkHttpProxy setNetworkInterceptor(Interceptor interceptor) {
        ((RequestOkHttp) this.baseRequest).setNetworkInterceptor(interceptor);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setNetworkInterceptors(List<Interceptor> list) {
        ((RequestOkHttp) this.baseRequest).setNetworkInterceptors(list);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setNetworkInterceptors(List list) {
        return setNetworkInterceptors((List<Interceptor>) list);
    }

    @Override
    public XHRequestOkHttpProxy setQueryParams(Map<String, String> map) {
        ((RequestOkHttp) this.baseRequest).setQueryParams(map);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setQueryParams(Map map) {
        return setQueryParams((Map<String, String>) map);
    }

    @Override
    public XHRequestOkHttpProxy setReadTimeout(long j) {
        return super.setReadTimeout(j);
    }

    @Override
    public XHRequestOkHttpProxy setRequestBody(Object obj) {
        ((RequestOkHttp) this.baseRequest).setRequestBody(obj);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setRequestBody(String str) {
        ((RequestOkHttp) this.baseRequest).setRequestBody(str);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setRequestBody(String str, Object obj) {
        ((RequestOkHttp) this.baseRequest).setRequestBody(str, obj);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setRequestBody(String str, String str2) {
        ((RequestOkHttp) this.baseRequest).setRequestBody(str, str2);
        return this;
    }

    @Override
    public XHRequestOkHttpProxy setTimeout(long j) {
        return super.setTimeout(j);
    }

    @Override
    public XHRequestOkHttpProxy setWriteTimeout(long j) {
        return super.setWriteTimeout(j);
    }
}
