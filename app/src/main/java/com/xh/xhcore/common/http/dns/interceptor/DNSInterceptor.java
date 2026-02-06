package com.xh.xhcore.common.http.dns.interceptor;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor;", "", "intercept", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "chain", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor$Chain;", "Chain", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DNSInterceptor {

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\nH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor$Chain;", "", "isLocalDNSFail", "", "()Z", "setLocalDNSFail", "(Z)V", "proceed", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "hostName", "", "requestHostName", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Chain {
        boolean isLocalDNSFail();

        HttpDNSResult proceed(String hostName);

        String requestHostName();

        void setLocalDNSFail(boolean z);
    }

    HttpDNSResult intercept(Chain chain);
}
