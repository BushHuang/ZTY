package com.xh.xhcore.common.http.dns;

import android.text.TextUtils;
import com.xh.xhcore.common.http.dns.chain.RealDNSInterceptorChain;
import com.xh.xhcore.common.http.dns.interceptor.CacheDNSInterceptor;
import com.xh.xhcore.common.http.dns.interceptor.HttpDNSInterceptor;
import com.xh.xhcore.common.http.dns.interceptor.HttpDNSResult;
import com.xh.xhcore.common.http.dns.interceptor.LocalDNSInterceptor;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.DNSLookUpUtil;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0005J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSManager;", "", "()V", "hostManager", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "getHostManager", "()Ljava/util/concurrent/ConcurrentHashMap;", "getArrayWithInterceptorChain", "hostName", "index", "", "getDNSCache", "getDNSResult", "getInetAddress", "", "Ljava/net/InetAddress;", "dnsResult", "setDNSCache", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DNSManager {
    public static final String TAG = "DNSManager";
    private final ConcurrentHashMap<String, HttpDNSResult> hostManager;

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DNSManager> instance$delegate = LazyKt.lazy(new Function0<DNSManager>() {
        @Override
        public final DNSManager invoke() {
            return new DNSManager(null);
        }
    });

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\u00020\u00068FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSManager$Companion;", "", "()V", "TAG", "", "instance", "Lcom/xh/xhcore/common/http/dns/DNSManager;", "getInstance$annotations", "getInstance", "()Lcom/xh/xhcore/common/http/dns/DNSManager;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final DNSManager getInstance() {
            return (DNSManager) DNSManager.instance$delegate.getValue();
        }
    }

    private DNSManager() {
        this.hostManager = new ConcurrentHashMap<>();
    }

    public DNSManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final HttpDNSResult getArrayWithInterceptorChain(String hostName, int index) {
        LogUtils.Companion.e$default(LogUtils.INSTANCE, "DNSManager", "Start DNS Lookup =====> hostName = " + hostName + " and now index is " + index, null, 4, null);
        if (DNSLookUpUtil.INSTANCE.checkIp(hostName)) {
            LogUtils.Companion.e$default(LogUtils.INSTANCE, "DNSManager", Intrinsics.stringPlus("Start DNS Lookup =====> and hostName is IpAddress = ", hostName), null, 4, null);
            return new HttpDNSResult(hostName, CollectionsKt.listOf(hostName), 0L, 0L, -1, 8, null);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CacheDNSInterceptor());
        arrayList.add(new LocalDNSInterceptor());
        arrayList.add(new HttpDNSInterceptor());
        try {
            return new RealDNSInterceptorChain(arrayList, hostName, index).proceed(hostName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HttpDNSResult getDNSResult$default(DNSManager dNSManager, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return dNSManager.getDNSResult(str, i);
    }

    public static final DNSManager getInstance() {
        return INSTANCE.getInstance();
    }

    public final HttpDNSResult getDNSCache(String hostName) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        return this.hostManager.get(hostName);
    }

    public final HttpDNSResult getDNSResult(String hostName, int index) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        return getArrayWithInterceptorChain(hostName, index);
    }

    public final ConcurrentHashMap<String, HttpDNSResult> getHostManager() {
        return this.hostManager;
    }

    public final List<InetAddress> getInetAddress(HttpDNSResult dnsResult) {
        String string;
        ArrayList arrayList = new ArrayList();
        if (dnsResult != null) {
            Iterator<T> it = dnsResult.getIps().iterator();
            while (it.hasNext()) {
                arrayList.addAll(DNSLookUpUtil.Companion.loadLocalDNS$default(DNSLookUpUtil.INSTANCE, (String) it.next(), 0L, 2, null));
            }
        }
        LogUtils.Companion companion = LogUtils.INSTANCE;
        String str = "DNSResult is null!!!";
        if (dnsResult != null && (string = dnsResult.toString()) != null) {
            str = string;
        }
        LogUtils.Companion.e$default(companion, "DNSManager", Intrinsics.stringPlus("End DNS Lookup =====> ", str), null, 4, null);
        return arrayList;
    }

    public final void setDNSCache(String hostName, HttpDNSResult dnsResult) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        if (dnsResult == null || TextUtils.isEmpty(dnsResult.getHost())) {
            return;
        }
        getHostManager().put(hostName, dnsResult);
    }
}
