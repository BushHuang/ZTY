package com.xh.xhcore.common.http.dns.interceptor;

import com.xh.xhcore.common.http.dns.DNSBuryUtil;
import com.xh.xhcore.common.http.dns.DNSLookupTypeManager;
import com.xh.xhcore.common.http.dns.DNSManager;
import com.xh.xhcore.common.http.dns.interceptor.DNSInterceptor;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.request.RequestOkHttp;
import com.xh.xhcore.common.util.JsonUtil;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSInterceptor;", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor;", "()V", "ACCOUNT_ID", "", "SERVER_IP", "intercept", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "chain", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor$Chain;", "isIpsNullOrEmpty", "", "httpDNSResult", "loadHttpDNS", "hostName", "loadLocalDNSFail", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HttpDNSInterceptor implements DNSInterceptor {
    public static final String TAG = "HttpDNSInterceptor";
    private final String SERVER_IP = "203.107.1.1";
    private final String ACCOUNT_ID = "103172";

    private final boolean isIpsNullOrEmpty(HttpDNSResult httpDNSResult) {
        List<String> ips;
        boolean z = false;
        if (httpDNSResult != null && (ips = httpDNSResult.getIps()) != null && !ips.isEmpty()) {
            z = true;
        }
        return !z;
    }

    private final HttpDNSResult loadHttpDNS(final String hostName, final boolean loadLocalDNSFail) {
        if (!DNSBuryUtil.isHostNameNeedHttpDNS(hostName)) {
            return null;
        }
        String str = "http://" + this.SERVER_IP + '/' + this.ACCOUNT_ID + "/d?host=" + hostName;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            ((RequestOkHttp) ((RequestOkHttp) ((RequestOkHttp) ((RequestOkHttp) new RequestOkHttp().setUrl(str)).setMethod("POST")).setTimeout(3000L)).setIsAsync(false)).setCallback(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(e, "e");
                    e.printStackTrace();
                    String str2 = hostName;
                    int i = loadLocalDNSFail ? 4 : 7;
                    String message = e.getMessage();
                    if (message == null) {
                        message = "";
                    }
                    DNSBuryUtil.buryDNSEvent(str2, i, message);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    String strString;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    ResponseBody responseBodyBody = response.body();
                    String str2 = "";
                    if (responseBodyBody != null && (strString = responseBodyBody.string()) != null) {
                        str2 = strString;
                    }
                    ?? r5 = (HttpDNSResult) JsonUtil.parseObject(str2, HttpDNSResult.class);
                    if (HttpDNSInterceptor.this.isIpsNullOrEmpty(r5)) {
                        DNSBuryUtil.buryDNSEvent(hostName, loadLocalDNSFail ? 2 : 3, str2);
                        objectRef.element = null;
                    } else {
                        r5.setLookupType(2);
                        r5.setQueryTime(System.nanoTime());
                        r5.setIps(CollectionsKt.distinct(r5.getIps()));
                        HttpDNSResult localDNSResult = DNSLookupTypeManager.getLocalDNSResult();
                        if (localDNSResult != null && !loadLocalDNSFail) {
                            Intrinsics.checkNotNull(r5);
                            if (!r5.isResultIPsEquals(localDNSResult)) {
                                DNSBuryUtil.buryDNSEvent$default(hostName, 8, null, 4, null);
                            }
                        }
                        objectRef.element = r5;
                        DNSLookupTypeManager.setHttpDNSResult(objectRef.element);
                    }
                    LogUtils.Companion companion = LogUtils.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("t = ");
                    String str3 = r5;
                    if (r5 == 0) {
                        str3 = "啥都没有";
                    }
                    sb.append((Object) str3);
                    sb.append(" and ips = ");
                    HttpDNSResult httpDNSResult = objectRef.element;
                    sb.append(httpDNSResult == null ? null : httpDNSResult.getIps());
                    sb.append(" and host = ");
                    HttpDNSResult httpDNSResult2 = objectRef.element;
                    sb.append((Object) (httpDNSResult2 != null ? httpDNSResult2.getHost() : null));
                    companion.e(sb.toString());
                }
            }).setCallerOkHttpClient(new OkHttpClient()).request();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (HttpDNSResult) objectRef.element;
    }

    @Override
    public HttpDNSResult intercept(DNSInterceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        String strRequestHostName = chain.requestHostName();
        DNSManager.INSTANCE.getInstance().setDNSCache(strRequestHostName, null);
        HttpDNSResult httpDNSResultLoadHttpDNS = loadHttpDNS(strRequestHostName, chain.getIsLocalDNSFail());
        if (httpDNSResultLoadHttpDNS != null) {
            DNSManager.INSTANCE.getInstance().setDNSCache(strRequestHostName, httpDNSResultLoadHttpDNS);
        }
        LogUtils.Companion.e$default(LogUtils.INSTANCE, "HttpDNSInterceptor", "hostName = " + strRequestHostName + " and dnsResult = " + httpDNSResultLoadHttpDNS, null, 4, null);
        return httpDNSResultLoadHttpDNS;
    }
}
