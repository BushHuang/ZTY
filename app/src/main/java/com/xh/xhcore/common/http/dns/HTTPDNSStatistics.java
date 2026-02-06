package com.xh.xhcore.common.http.dns;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/http/dns/HTTPDNSStatistics;", "", "host", "", "type", "", "responseMsg", "(Ljava/lang/String;ILjava/lang/String;)V", "getHost", "()Ljava/lang/String;", "getResponseMsg", "getType", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HTTPDNSStatistics {
    private final String host;
    private final String responseMsg;
    private final int type;

    public HTTPDNSStatistics(String str, int i, String str2) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(str2, "responseMsg");
        this.host = str;
        this.type = i;
        this.responseMsg = str2;
    }

    public HTTPDNSStatistics(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? "" : str2);
    }

    public static HTTPDNSStatistics copy$default(HTTPDNSStatistics hTTPDNSStatistics, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = hTTPDNSStatistics.host;
        }
        if ((i2 & 2) != 0) {
            i = hTTPDNSStatistics.type;
        }
        if ((i2 & 4) != 0) {
            str2 = hTTPDNSStatistics.responseMsg;
        }
        return hTTPDNSStatistics.copy(str, i, str2);
    }

    public final String getHost() {
        return this.host;
    }

    public final int getType() {
        return this.type;
    }

    public final String getResponseMsg() {
        return this.responseMsg;
    }

    public final HTTPDNSStatistics copy(String host, int type, String responseMsg) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(responseMsg, "responseMsg");
        return new HTTPDNSStatistics(host, type, responseMsg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HTTPDNSStatistics)) {
            return false;
        }
        HTTPDNSStatistics hTTPDNSStatistics = (HTTPDNSStatistics) other;
        return Intrinsics.areEqual(this.host, hTTPDNSStatistics.host) && this.type == hTTPDNSStatistics.type && Intrinsics.areEqual(this.responseMsg, hTTPDNSStatistics.responseMsg);
    }

    public final String getHost() {
        return this.host;
    }

    public final String getResponseMsg() {
        return this.responseMsg;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        return (((this.host.hashCode() * 31) + this.type) * 31) + this.responseMsg.hashCode();
    }

    public String toString() {
        return "HTTPDNSStatistics(host=" + this.host + ", type=" + this.type + ", responseMsg=" + this.responseMsg + ')';
    }
}
