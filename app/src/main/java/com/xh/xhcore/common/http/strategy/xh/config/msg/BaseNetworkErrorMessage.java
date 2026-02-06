package com.xh.xhcore.common.http.strategy.xh.config.msg;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "replaced by INetworkErrorMessage")
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/config/msg/BaseNetworkErrorMessage;", "", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseNetworkErrorMessage {
    private final String message;

    public BaseNetworkErrorMessage() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BaseNetworkErrorMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        this.message = str;
    }

    public BaseNetworkErrorMessage(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public final String getMessage() {
        return this.message;
    }

    public String toString() {
        return this.message;
    }
}
