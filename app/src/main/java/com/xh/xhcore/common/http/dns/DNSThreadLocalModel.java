package com.xh.xhcore.common.http.dns;

import com.xh.xhcore.common.http.dns.interceptor.HttpDNSResult;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ%\u0010!\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003Je\u0010'\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010(\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0004J\u0006\u0010,\u001a\u00020\u0004J\t\u0010-\u001a\u00020\u0005HÖ\u0001J\u0006\u0010.\u001a\u00020/J\u0016\u00100\u001a\u00020/2\u0006\u0010+\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0005J\t\u00102\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001c\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00063"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSThreadLocalModel;", "", "map", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "followUpConditionMatch", "", "allowBury", "localDNSResult", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "httpDNSResult", "networkRetryTimes", "(Ljava/util/HashMap;ZZLcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;I)V", "getAllowBury", "()Z", "setAllowBury", "(Z)V", "getFollowUpConditionMatch", "setFollowUpConditionMatch", "getHttpDNSResult", "()Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "setHttpDNSResult", "(Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;)V", "getLocalDNSResult", "setLocalDNSResult", "getMap", "()Ljava/util/HashMap;", "getNetworkRetryTimes", "()I", "setNetworkRetryTimes", "(I)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "getCurrentType", "hostName", "getHostNameWhenRetry", "hashCode", "increaseNetworkRetryTimes", "", "setCurrentType", "type", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DNSThreadLocalModel {
    private boolean allowBury;
    private boolean followUpConditionMatch;
    private HttpDNSResult httpDNSResult;
    private HttpDNSResult localDNSResult;
    private final HashMap<String, Integer> map;
    private int networkRetryTimes;

    public DNSThreadLocalModel() {
        this(null, false, false, null, null, 0, 63, null);
    }

    public DNSThreadLocalModel(HashMap<String, Integer> map, boolean z, boolean z2, HttpDNSResult httpDNSResult, HttpDNSResult httpDNSResult2, int i) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.map = map;
        this.followUpConditionMatch = z;
        this.allowBury = z2;
        this.localDNSResult = httpDNSResult;
        this.httpDNSResult = httpDNSResult2;
        this.networkRetryTimes = i;
    }

    public DNSThreadLocalModel(LinkedHashMap linkedHashMap, boolean z, boolean z2, HttpDNSResult httpDNSResult, HttpDNSResult httpDNSResult2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new LinkedHashMap() : linkedHashMap, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? true : z2, (i2 & 8) != 0 ? null : httpDNSResult, (i2 & 16) != 0 ? null : httpDNSResult2, (i2 & 32) != 0 ? -1 : i);
    }

    public static DNSThreadLocalModel copy$default(DNSThreadLocalModel dNSThreadLocalModel, HashMap map, boolean z, boolean z2, HttpDNSResult httpDNSResult, HttpDNSResult httpDNSResult2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            map = dNSThreadLocalModel.map;
        }
        if ((i2 & 2) != 0) {
            z = dNSThreadLocalModel.followUpConditionMatch;
        }
        boolean z3 = z;
        if ((i2 & 4) != 0) {
            z2 = dNSThreadLocalModel.allowBury;
        }
        boolean z4 = z2;
        if ((i2 & 8) != 0) {
            httpDNSResult = dNSThreadLocalModel.localDNSResult;
        }
        HttpDNSResult httpDNSResult3 = httpDNSResult;
        if ((i2 & 16) != 0) {
            httpDNSResult2 = dNSThreadLocalModel.httpDNSResult;
        }
        HttpDNSResult httpDNSResult4 = httpDNSResult2;
        if ((i2 & 32) != 0) {
            i = dNSThreadLocalModel.networkRetryTimes;
        }
        return dNSThreadLocalModel.copy(map, z3, z4, httpDNSResult3, httpDNSResult4, i);
    }

    public final HashMap<String, Integer> component1() {
        return this.map;
    }

    public final boolean getFollowUpConditionMatch() {
        return this.followUpConditionMatch;
    }

    public final boolean getAllowBury() {
        return this.allowBury;
    }

    public final HttpDNSResult getLocalDNSResult() {
        return this.localDNSResult;
    }

    public final HttpDNSResult getHttpDNSResult() {
        return this.httpDNSResult;
    }

    public final int getNetworkRetryTimes() {
        return this.networkRetryTimes;
    }

    public final DNSThreadLocalModel copy(HashMap<String, Integer> map, boolean followUpConditionMatch, boolean allowBury, HttpDNSResult localDNSResult, HttpDNSResult httpDNSResult, int networkRetryTimes) {
        Intrinsics.checkNotNullParameter(map, "map");
        return new DNSThreadLocalModel(map, followUpConditionMatch, allowBury, localDNSResult, httpDNSResult, networkRetryTimes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DNSThreadLocalModel)) {
            return false;
        }
        DNSThreadLocalModel dNSThreadLocalModel = (DNSThreadLocalModel) other;
        return Intrinsics.areEqual(this.map, dNSThreadLocalModel.map) && this.followUpConditionMatch == dNSThreadLocalModel.followUpConditionMatch && this.allowBury == dNSThreadLocalModel.allowBury && Intrinsics.areEqual(this.localDNSResult, dNSThreadLocalModel.localDNSResult) && Intrinsics.areEqual(this.httpDNSResult, dNSThreadLocalModel.httpDNSResult) && this.networkRetryTimes == dNSThreadLocalModel.networkRetryTimes;
    }

    public final boolean getAllowBury() {
        return this.allowBury;
    }

    public final int getCurrentType(String hostName) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        Integer num = this.map.get(hostName);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public final boolean getFollowUpConditionMatch() {
        return this.followUpConditionMatch;
    }

    public final String getHostNameWhenRetry() {
        if (this.map.size() != 1) {
            return "";
        }
        Set<String> setKeySet = this.map.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "map.keys");
        return (String) CollectionsKt.first(setKeySet);
    }

    public final HttpDNSResult getHttpDNSResult() {
        return this.httpDNSResult;
    }

    public final HttpDNSResult getLocalDNSResult() {
        return this.localDNSResult;
    }

    public final HashMap<String, Integer> getMap() {
        return this.map;
    }

    public final int getNetworkRetryTimes() {
        return this.networkRetryTimes;
    }

    public int hashCode() {
        int iHashCode = this.map.hashCode() * 31;
        boolean z = this.followUpConditionMatch;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.allowBury;
        int i3 = (i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        HttpDNSResult httpDNSResult = this.localDNSResult;
        int iHashCode2 = (i3 + (httpDNSResult == null ? 0 : httpDNSResult.hashCode())) * 31;
        HttpDNSResult httpDNSResult2 = this.httpDNSResult;
        return ((iHashCode2 + (httpDNSResult2 != null ? httpDNSResult2.hashCode() : 0)) * 31) + this.networkRetryTimes;
    }

    public final void increaseNetworkRetryTimes() {
        this.networkRetryTimes++;
    }

    public final void setAllowBury(boolean z) {
        this.allowBury = z;
    }

    public final void setCurrentType(String hostName, int type) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        this.map.put(hostName, Integer.valueOf(type));
    }

    public final void setFollowUpConditionMatch(boolean z) {
        this.followUpConditionMatch = z;
    }

    public final void setHttpDNSResult(HttpDNSResult httpDNSResult) {
        this.httpDNSResult = httpDNSResult;
    }

    public final void setLocalDNSResult(HttpDNSResult httpDNSResult) {
        this.localDNSResult = httpDNSResult;
    }

    public final void setNetworkRetryTimes(int i) {
        this.networkRetryTimes = i;
    }

    public String toString() {
        return "DNSThreadLocalModel(map=" + this.map + ", followUpConditionMatch=" + this.followUpConditionMatch + ", allowBury=" + this.allowBury + ", localDNSResult=" + this.localDNSResult + ", httpDNSResult=" + this.httpDNSResult + ", networkRetryTimes=" + this.networkRetryTimes + ')';
    }
}
