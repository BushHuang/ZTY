package com.xh.xhcore.common.http.strategy.okhttp.ignore;

import com.xh.xhcore.common.http.strategy.xh.config.msg.INetworkErrorMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/ignore/TagRequestIdentity;", "Lcom/xh/xhcore/common/http/strategy/xh/config/msg/INetworkErrorMessage;", "requestIdentity", "", "(Ljava/lang/String;)V", "getRequestIdentity", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TagRequestIdentity implements INetworkErrorMessage {
    private final String requestIdentity;

    public TagRequestIdentity(String str) {
        Intrinsics.checkNotNullParameter(str, "requestIdentity");
        this.requestIdentity = str;
    }

    public static TagRequestIdentity copy$default(TagRequestIdentity tagRequestIdentity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tagRequestIdentity.requestIdentity;
        }
        return tagRequestIdentity.copy(str);
    }

    public final String getRequestIdentity() {
        return this.requestIdentity;
    }

    public final TagRequestIdentity copy(String requestIdentity) {
        Intrinsics.checkNotNullParameter(requestIdentity, "requestIdentity");
        return new TagRequestIdentity(requestIdentity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TagRequestIdentity) && Intrinsics.areEqual(this.requestIdentity, ((TagRequestIdentity) other).requestIdentity);
    }

    public final String getRequestIdentity() {
        return this.requestIdentity;
    }

    public int hashCode() {
        return this.requestIdentity.hashCode();
    }

    public String toString() {
        return "TagRequestIdentity(requestIdentity=" + this.requestIdentity + ')';
    }
}
