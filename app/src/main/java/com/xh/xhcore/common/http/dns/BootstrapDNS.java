package com.xh.xhcore.common.http.dns;

import android.text.TextUtils;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.DNSLookUpUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Dns;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001b\u0010\b\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/http/dns/BootstrapDNS;", "Lokhttp3/Dns;", "forceIp", "", "(Ljava/lang/String;)V", "getForceIp", "()Ljava/lang/String;", "setForceIp", "realDNS", "getRealDNS", "()Lokhttp3/Dns;", "realDNS$delegate", "Lkotlin/Lazy;", "lookup", "", "Ljava/net/InetAddress;", "hostname", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BootstrapDNS implements Dns {
    public static final String TAG = "BootstrapDNS";
    private String forceIp;

    private final Lazy realDNS;

    public BootstrapDNS() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BootstrapDNS(String str) {
        Intrinsics.checkNotNullParameter(str, "forceIp");
        this.forceIp = str;
        this.realDNS = LazyKt.lazy(new Function0<Dns>() {
            @Override
            public final Dns invoke() {
                return XHAppConfigProxy.getInstance().enableExtendDNS() ? new XhOkHttpDNS() : new OptimizeLocalDNS();
            }
        });
    }

    public BootstrapDNS(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public final String getForceIp() {
        return this.forceIp;
    }

    public final Dns getRealDNS() {
        return (Dns) this.realDNS.getValue();
    }

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        LogUtils.Companion.e$default(LogUtils.INSTANCE, "BootstrapDNS", "hostname => " + hostname + " and forceIp => " + this.forceIp, null, 4, null);
        if (!TextUtils.isEmpty(this.forceIp)) {
            return DNSLookUpUtil.Companion.loadLocalDNS$default(DNSLookUpUtil.INSTANCE, this.forceIp, 0L, 2, null);
        }
        List<InetAddress> listLookup = getRealDNS().lookup(hostname);
        Intrinsics.checkNotNullExpressionValue(listLookup, "{\n            realDNS.lookup(hostname)\n        }");
        return listLookup;
    }

    public final void setForceIp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.forceIp = str;
    }
}
