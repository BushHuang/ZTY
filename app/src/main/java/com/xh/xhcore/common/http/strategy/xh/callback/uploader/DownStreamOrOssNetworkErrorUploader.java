package com.xh.xhcore.common.http.strategy.xh.callback.uploader;

import android.net.wifi.WifiInfo;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestTagsUtil;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.util.WifiUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0001H\u0016J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bR\u001d\u0010\u0002\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/DownStreamOrOssNetworkErrorUploader;", "Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/BaseNetworkErrorUploader;", "xhBaseRequestProxy", "Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;", "(Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;)V", "getXhBaseRequestProxy", "()Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;", "allowUploadNetworkErrorLog", "", "fillHttpDetail", "getClientTraceId", "", "getConnectedWifiInfo", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DownStreamOrOssNetworkErrorUploader extends BaseNetworkErrorUploader {
    private final XHBaseRequestProxy<?, ?, ?> xhBaseRequestProxy;

    public DownStreamOrOssNetworkErrorUploader(XHBaseRequestProxy<?, ?, ?> xHBaseRequestProxy) {
        Intrinsics.checkNotNullParameter(xHBaseRequestProxy, "xhBaseRequestProxy");
        this.xhBaseRequestProxy = xHBaseRequestProxy;
    }

    @Override
    public boolean allowUploadNetworkErrorLog() {
        return !RequestTagsUtil.isUserCancelNetwork(this.xhBaseRequestProxy) && RequestTagsUtil.allowUploadNetworkErrorLogFromConfig(this.xhBaseRequestProxy);
    }

    @Override
    public BaseNetworkErrorUploader fillHttpDetail() {
        getBasicHttpDetailMap().put("X-B3-TraceId", getClientTraceId());
        getBasicHttpDetailMap().put("SSID", WifiUtil.getConnectedWifiSsid());
        getBasicHttpDetailMap().put("BSSID", WifiUtil.getConnectedWifiBssid());
        LinkedHashMap<String, Object> basicHttpDetailMap = getBasicHttpDetailMap();
        WifiInfo connectedWifiInfo = WifiUtil.getConnectedWifiInfo();
        basicHttpDetailMap.put("RSSI", connectedWifiInfo == null ? "" : Integer.valueOf(connectedWifiInfo.getRssi()));
        getBasicHttpDetailMap().put("Frequency", Float.valueOf(WifiUtil.getComputedConnectWifiFrequency()));
        getBasicHttpDetailMap().put("url", String.valueOf(this.xhBaseRequestProxy.getBaseRequest().getRequestUrl()));
        getBasicHttpDetailMap().put("requestHost", String.valueOf(this.xhBaseRequestProxy.getBaseRequest().getRequestHost()));
        getBasicHttpDetailMap().put("requestMethod", String.valueOf(this.xhBaseRequestProxy.getBaseRequest().getMethod()));
        LinkedHashMap<String, Object> basicHttpDetailMap2 = getBasicHttpDetailMap();
        Map requestHeadersMap = this.xhBaseRequestProxy.getBaseRequest().getRequestHeadersMap();
        Intrinsics.checkNotNullExpressionValue(requestHeadersMap, "xhBaseRequestProxy.baseRequest.requestHeadersMap");
        basicHttpDetailMap2.put("requestHeaders", requestHeadersMap);
        getBasicHttpDetailMap().put("requestBody", String.valueOf(this.xhBaseRequestProxy.getBaseRequest().getRequestBodyString()));
        return this;
    }

    public final String getClientTraceId() {
        String requestHeader = this.xhBaseRequestProxy.getBaseRequest().getRequestHeader("X-B3-TraceId");
        Intrinsics.checkNotNullExpressionValue(requestHeader, "xhBaseRequestProxy.baseR…stHeader(CLIENT_TRACE_ID)");
        return requestHeader;
    }

    public final String getConnectedWifiInfo() {
        String string;
        WifiInfo connectedWifiInfo = WifiUtil.getConnectedWifiInfo();
        return (connectedWifiInfo == null || (string = connectedWifiInfo.toString()) == null) ? "no connected wifi info" : string;
    }

    public final XHBaseRequestProxy<?, ?, ?> getXhBaseRequestProxy() {
        return this.xhBaseRequestProxy;
    }
}
