package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0005\"\u0004\b\u0006\u0010\u0004¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/ExternalLinkTag;", "", "isExternalLink", "", "(Z)V", "()Z", "setExternalLink", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExternalLinkTag {
    private boolean isExternalLink;

    public ExternalLinkTag() {
        this(false, 1, null);
    }

    public ExternalLinkTag(boolean z) {
        this.isExternalLink = z;
    }

    public ExternalLinkTag(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public static ExternalLinkTag copy$default(ExternalLinkTag externalLinkTag, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = externalLinkTag.isExternalLink;
        }
        return externalLinkTag.copy(z);
    }

    public final boolean getIsExternalLink() {
        return this.isExternalLink;
    }

    public final ExternalLinkTag copy(boolean isExternalLink) {
        return new ExternalLinkTag(isExternalLink);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ExternalLinkTag) && this.isExternalLink == ((ExternalLinkTag) other).isExternalLink;
    }

    public int hashCode() {
        boolean z = this.isExternalLink;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public final boolean isExternalLink() {
        return this.isExternalLink;
    }

    public final void setExternalLink(boolean z) {
        this.isExternalLink = z;
    }

    public String toString() {
        return "ExternalLinkTag(isExternalLink=" + this.isExternalLink + ')';
    }
}
