package com.xh.xhcore.common.http.dns.interceptor;

import com.xh.xhcore.common.http.dns.DNSManager;
import com.xh.xhcore.common.http.dns.interceptor.DNSInterceptor;
import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/http/dns/interceptor/CacheDNSInterceptor;", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "intercept", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "chain", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor$Chain;", "loadCacheDNS", "hostName", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CacheDNSInterceptor implements DNSInterceptor {
    private final String TAG = "CacheDNSInterceptor";

    private final HttpDNSResult loadCacheDNS(String hostName) {
        HttpDNSResult dNSCache = DNSManager.INSTANCE.getInstance().getDNSCache(hostName);
        if (dNSCache == null) {
            return null;
        }
        double queryTime = dNSCache.getQueryTime();
        double ttl = dNSCache.getTtl();
        double dPow = Math.pow(10.0d, 9.0d);
        Double.isNaN(ttl);
        Double.isNaN(queryTime);
        if (queryTime + (ttl * dPow) > System.nanoTime()) {
            return dNSCache;
        }
        return null;
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override
    public HttpDNSResult intercept(DNSInterceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        String strRequestHostName = chain.requestHostName();
        LogUtils.Companion.e$default(LogUtils.INSTANCE, this.TAG, Intrinsics.stringPlus("hostName = ", strRequestHostName), null, 4, null);
        HttpDNSResult httpDNSResultLoadCacheDNS = loadCacheDNS(strRequestHostName);
        return httpDNSResultLoadCacheDNS == null ? chain.proceed(strRequestHostName) : httpDNSResultLoadCacheDNS;
    }
}
