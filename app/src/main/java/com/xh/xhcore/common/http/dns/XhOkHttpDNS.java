package com.xh.xhcore.common.http.dns;

import com.xh.xhcore.common.http.dns.interceptor.HttpDNSResult;
import com.xh.xhcore.common.util.DNSLookUpUtil;
import java.net.InetAddress;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Dns;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/http/dns/XhOkHttpDNS;", "Lokhttp3/Dns;", "()V", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XhOkHttpDNS implements Dns {
    @Override
    public List<InetAddress> lookup(String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        int currentType = DNSLookupTypeManager.getCurrentType(hostname);
        if (currentType > 1) {
            return CollectionsKt.toMutableList((Collection) DNSLookUpUtil.Companion.loadLocalDNS$default(DNSLookUpUtil.INSTANCE, hostname, 0L, 2, null));
        }
        HttpDNSResult dNSResult = DNSManager.INSTANCE.getInstance().getDNSResult(hostname, currentType + 1);
        DNSLookupTypeManager.setCurrentType(hostname, dNSResult == null ? 2 : dNSResult.getLookupType());
        return DNSManager.INSTANCE.getInstance().getInetAddress(dNSResult);
    }
}
