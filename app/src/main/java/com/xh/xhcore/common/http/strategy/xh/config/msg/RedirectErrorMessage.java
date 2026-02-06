package com.xh.xhcore.common.http.strategy.xh.config.msg;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/config/msg/RedirectErrorMessage;", "Lcom/xh/xhcore/common/http/strategy/xh/config/msg/INetworkErrorMessage;", "msg", "", "(Ljava/lang/String;)V", "getMsg", "()Ljava/lang/String;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RedirectErrorMessage implements INetworkErrorMessage {
    private final String msg;

    public RedirectErrorMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "msg");
        this.msg = str;
    }

    public final String getMsg() {
        return this.msg;
    }
}
