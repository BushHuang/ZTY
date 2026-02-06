package com.xh.xhcore.common.http.strategy.xh.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/config/AllowUploadNetworkError;", "", "allow", "", "(Z)V", "getAllow", "()Z", "setAllow", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AllowUploadNetworkError {
    private boolean allow;

    public AllowUploadNetworkError() {
        this(false, 1, null);
    }

    public AllowUploadNetworkError(boolean z) {
        this.allow = z;
    }

    public AllowUploadNetworkError(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public static AllowUploadNetworkError copy$default(AllowUploadNetworkError allowUploadNetworkError, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = allowUploadNetworkError.allow;
        }
        return allowUploadNetworkError.copy(z);
    }

    public final boolean getAllow() {
        return this.allow;
    }

    public final AllowUploadNetworkError copy(boolean allow) {
        return new AllowUploadNetworkError(allow);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AllowUploadNetworkError) && this.allow == ((AllowUploadNetworkError) other).allow;
    }

    public final boolean getAllow() {
        return this.allow;
    }

    public int hashCode() {
        boolean z = this.allow;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public final void setAllow(boolean z) {
        this.allow = z;
    }

    public String toString() {
        return "AllowUploadNetworkError(allow=" + this.allow + ')';
    }
}
