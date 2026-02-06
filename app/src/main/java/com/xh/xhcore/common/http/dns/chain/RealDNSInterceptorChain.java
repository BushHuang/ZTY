package com.xh.xhcore.common.http.dns.chain;

import com.xh.xhcore.common.http.dns.interceptor.DNSInterceptor;
import com.xh.xhcore.common.http.dns.interceptor.HttpDNSResult;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R&\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178V@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lcom/xh/xhcore/common/http/dns/chain/RealDNSInterceptorChain;", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor$Chain;", "interceptors", "", "Lcom/xh/xhcore/common/http/dns/interceptor/DNSInterceptor;", "hostName", "", "index", "", "(Ljava/util/List;Ljava/lang/String;I)V", "getHostName", "()Ljava/lang/String;", "setHostName", "(Ljava/lang/String;)V", "getIndex", "()I", "setIndex", "(I)V", "getInterceptors", "()Ljava/util/List;", "setInterceptors", "(Ljava/util/List;)V", "value", "", "isLocalDNSFail", "()Z", "setLocalDNSFail", "(Z)V", "proceed", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "requestHostName", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RealDNSInterceptorChain implements DNSInterceptor.Chain {
    private String hostName;
    private int index;
    private List<DNSInterceptor> interceptors;
    private boolean isLocalDNSFail;

    public RealDNSInterceptorChain(List<DNSInterceptor> list, String str, int i) {
        Intrinsics.checkNotNullParameter(list, "interceptors");
        Intrinsics.checkNotNullParameter(str, "hostName");
        this.interceptors = list;
        this.hostName = str;
        this.index = i;
    }

    public RealDNSInterceptorChain(List list, String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i2 & 2) != 0 ? "" : str, i);
    }

    public final String getHostName() {
        return this.hostName;
    }

    public final int getIndex() {
        return this.index;
    }

    public final List<DNSInterceptor> getInterceptors() {
        return this.interceptors;
    }

    @Override
    public boolean getIsLocalDNSFail() {
        return this.isLocalDNSFail;
    }

    @Override
    public HttpDNSResult proceed(String hostName) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        LogUtils.INSTANCE.d("index = " + this.index + " and size = " + this.interceptors.size());
        if (this.index >= this.interceptors.size()) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        RealDNSInterceptorChain realDNSInterceptorChain = new RealDNSInterceptorChain(this.interceptors, hostName, this.index + 1);
        realDNSInterceptorChain.setLocalDNSFail(getIsLocalDNSFail());
        return this.interceptors.get(this.index).intercept(realDNSInterceptorChain);
    }

    @Override
    public String requestHostName() {
        return this.hostName;
    }

    public final void setHostName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hostName = str;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final void setInterceptors(List<DNSInterceptor> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.interceptors = list;
    }

    @Override
    public void setLocalDNSFail(boolean z) {
        this.isLocalDNSFail = z;
    }
}
