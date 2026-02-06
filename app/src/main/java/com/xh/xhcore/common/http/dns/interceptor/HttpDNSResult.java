package com.xh.xhcore.common.http.dns.interceptor;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003JA\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\nHÖ\u0001J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0000J\b\u0010'\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017¨\u0006("}, d2 = {"Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "", "host", "", "ips", "", "ttl", "", "queryTime", "lookupType", "", "(Ljava/lang/String;Ljava/util/List;JJI)V", "getHost", "()Ljava/lang/String;", "getIps", "()Ljava/util/List;", "setIps", "(Ljava/util/List;)V", "getLookupType", "()I", "setLookupType", "(I)V", "getQueryTime", "()J", "setQueryTime", "(J)V", "getTtl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "isResultIPsEquals", "result", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HttpDNSResult {
    private final String host;
    private List<String> ips;
    private int lookupType;
    private long queryTime;
    private final long ttl;

    public HttpDNSResult(String str, List<String> list, long j, long j2, int i) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(list, "ips");
        this.host = str;
        this.ips = list;
        this.ttl = j;
        this.queryTime = j2;
        this.lookupType = i;
    }

    public HttpDNSResult(String str, List list, long j, long j2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list, j, (i2 & 8) != 0 ? System.nanoTime() : j2, (i2 & 16) != 0 ? 0 : i);
    }

    public static HttpDNSResult copy$default(HttpDNSResult httpDNSResult, String str, List list, long j, long j2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = httpDNSResult.host;
        }
        if ((i2 & 2) != 0) {
            list = httpDNSResult.ips;
        }
        List list2 = list;
        if ((i2 & 4) != 0) {
            j = httpDNSResult.ttl;
        }
        long j3 = j;
        if ((i2 & 8) != 0) {
            j2 = httpDNSResult.queryTime;
        }
        long j4 = j2;
        if ((i2 & 16) != 0) {
            i = httpDNSResult.lookupType;
        }
        return httpDNSResult.copy(str, list2, j3, j4, i);
    }

    public final String getHost() {
        return this.host;
    }

    public final List<String> component2() {
        return this.ips;
    }

    public final long getTtl() {
        return this.ttl;
    }

    public final long getQueryTime() {
        return this.queryTime;
    }

    public final int getLookupType() {
        return this.lookupType;
    }

    public final HttpDNSResult copy(String host, List<String> ips, long ttl, long queryTime, int lookupType) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(ips, "ips");
        return new HttpDNSResult(host, ips, ttl, queryTime, lookupType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpDNSResult)) {
            return false;
        }
        HttpDNSResult httpDNSResult = (HttpDNSResult) other;
        return Intrinsics.areEqual(this.host, httpDNSResult.host) && Intrinsics.areEqual(this.ips, httpDNSResult.ips) && this.ttl == httpDNSResult.ttl && this.queryTime == httpDNSResult.queryTime && this.lookupType == httpDNSResult.lookupType;
    }

    public final String getHost() {
        return this.host;
    }

    public final List<String> getIps() {
        return this.ips;
    }

    public final int getLookupType() {
        return this.lookupType;
    }

    public final long getQueryTime() {
        return this.queryTime;
    }

    public final long getTtl() {
        return this.ttl;
    }

    public int hashCode() {
        return (((((((this.host.hashCode() * 31) + this.ips.hashCode()) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.ttl)) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.queryTime)) * 31) + this.lookupType;
    }

    public final boolean isResultIPsEquals(HttpDNSResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        List<String> list = result.ips;
        if (!(!this.ips.isEmpty()) || list.size() != this.ips.size()) {
            return false;
        }
        HashMap map = new HashMap(list.size() + this.ips.size());
        Iterator<T> it = this.ips.iterator();
        while (it.hasNext()) {
            map.put((String) it.next(), 1);
        }
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            if (!map.containsKey((String) it2.next())) {
                return false;
            }
        }
        return true;
    }

    public final void setIps(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.ips = list;
    }

    public final void setLookupType(int i) {
        this.lookupType = i;
    }

    public final void setQueryTime(long j) {
        this.queryTime = j;
    }

    public String toString() {
        return "HttpDNSResult ==> [host = " + this.host + " , ips = " + this.ips + " , ttl = " + this.ttl + " , queryTime = " + this.queryTime + " , lookupType = " + this.lookupType + " ]";
    }
}
