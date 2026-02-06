package com.xh.xhcore.common.http.strategy.xh.config.msg;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/config/msg/CollectConnectSocketMessage;", "Lcom/xh/xhcore/common/http/strategy/xh/config/msg/INetworkErrorMessage;", "msg", "", "(Ljava/lang/String;)V", "getMsg", "()Ljava/lang/String;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CollectConnectSocketMessage implements INetworkErrorMessage {
    private final String msg;

    public CollectConnectSocketMessage() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public CollectConnectSocketMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "msg");
        this.msg = str;
    }

    public CollectConnectSocketMessage(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public final String getMsg() {
        return this.msg;
    }
}
