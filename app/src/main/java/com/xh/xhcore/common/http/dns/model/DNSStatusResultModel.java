package com.xh.xhcore.common.http.dns.model;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/xh/xhcore/common/http/dns/model/DNSStatusResultModel;", "", "needRetryForDns", "", "currentLastStepForwardType", "", "(ZI)V", "getCurrentLastStepForwardType", "()I", "getNeedRetryForDns", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DNSStatusResultModel {
    private final int currentLastStepForwardType;
    private final boolean needRetryForDns;

    public DNSStatusResultModel(boolean z, int i) {
        this.needRetryForDns = z;
        this.currentLastStepForwardType = i;
    }

    public static DNSStatusResultModel copy$default(DNSStatusResultModel dNSStatusResultModel, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = dNSStatusResultModel.needRetryForDns;
        }
        if ((i2 & 2) != 0) {
            i = dNSStatusResultModel.currentLastStepForwardType;
        }
        return dNSStatusResultModel.copy(z, i);
    }

    public final boolean getNeedRetryForDns() {
        return this.needRetryForDns;
    }

    public final int getCurrentLastStepForwardType() {
        return this.currentLastStepForwardType;
    }

    public final DNSStatusResultModel copy(boolean needRetryForDns, int currentLastStepForwardType) {
        return new DNSStatusResultModel(needRetryForDns, currentLastStepForwardType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DNSStatusResultModel)) {
            return false;
        }
        DNSStatusResultModel dNSStatusResultModel = (DNSStatusResultModel) other;
        return this.needRetryForDns == dNSStatusResultModel.needRetryForDns && this.currentLastStepForwardType == dNSStatusResultModel.currentLastStepForwardType;
    }

    public final int getCurrentLastStepForwardType() {
        return this.currentLastStepForwardType;
    }

    public final boolean getNeedRetryForDns() {
        return this.needRetryForDns;
    }

    public int hashCode() {
        boolean z = this.needRetryForDns;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (r0 * 31) + this.currentLastStepForwardType;
    }

    public String toString() {
        return "DNSStatusResultModel(needRetryForDns=" + this.needRetryForDns + ", currentLastStepForwardType=" + this.currentLastStepForwardType + ')';
    }
}
