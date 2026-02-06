package com.xh.xhcore.common.http.dns.interceptor;

import com.xh.xhcore.common.http.dns.DNSBuryUtil;
import com.xh.xhcore.common.http.dns.DNSLookupTypeManager;
import com.xh.xhcore.common.http.dns.DNSManager;
import com.xh.xhcore.common.http.dns.interceptor.DNSInterceptor;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.DNSLookUpUtil;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/http/dns/interceptor/LocalDNSInterceptor;", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "intercept", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "chain", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor$Chain;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocalDNSInterceptor implements DNSInterceptor {
    private final String TAG = "LocalDNSInterceptor";

    public final String getTAG() {
        return this.TAG;
    }

    @Override
    public HttpDNSResult intercept(DNSInterceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        String strRequestHostName = chain.requestHostName();
        DNSManager.INSTANCE.getInstance().setDNSCache(strRequestHostName, null);
        ArrayList arrayList = new ArrayList();
        LogUtils.Companion.e$default(LogUtils.INSTANCE, this.TAG, Intrinsics.stringPlus("hostName = ", strRequestHostName), null, 4, null);
        List listLoadLocalDNS$default = DNSLookUpUtil.Companion.loadLocalDNS$default(DNSLookUpUtil.INSTANCE, strRequestHostName, 0L, 2, null);
        if (!(!listLoadLocalDNS$default.isEmpty())) {
            DNSBuryUtil.buryDNSEvent$default(strRequestHostName, 1, null, 4, null);
            chain.setLocalDNSFail(true);
            return chain.proceed(strRequestHostName);
        }
        Iterator it = listLoadLocalDNS$default.iterator();
        while (it.hasNext()) {
            String hostAddress = ((InetAddress) it.next()).getHostAddress();
            Intrinsics.checkNotNullExpressionValue(hostAddress, "it.hostAddress");
            arrayList.add(hostAddress);
        }
        HttpDNSResult httpDNSResult = new HttpDNSResult(strRequestHostName, arrayList, 0L, 0L, 1, 8, null);
        DNSLookupTypeManager.setLocalDNSResult(httpDNSResult);
        DNSManager.INSTANCE.getInstance().setDNSCache(strRequestHostName, httpDNSResult);
        return httpDNSResult;
    }
}
