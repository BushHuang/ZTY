package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/ServerTypeTag;", "", "serverType", "", "(Ljava/lang/String;)V", "getServerType", "()Ljava/lang/String;", "setServerType", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ServerTypeTag {
    private String serverType;

    public ServerTypeTag(String str) {
        this.serverType = str;
    }

    public static ServerTypeTag copy$default(ServerTypeTag serverTypeTag, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serverTypeTag.serverType;
        }
        return serverTypeTag.copy(str);
    }

    public final String getServerType() {
        return this.serverType;
    }

    public final ServerTypeTag copy(String serverType) {
        return new ServerTypeTag(serverType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ServerTypeTag) && Intrinsics.areEqual(this.serverType, ((ServerTypeTag) other).serverType);
    }

    public final String getServerType() {
        return this.serverType;
    }

    public int hashCode() {
        String str = this.serverType;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final void setServerType(String str) {
        this.serverType = str;
    }

    public String toString() {
        return "ServerTypeTag(serverType=" + ((Object) this.serverType) + ')';
    }
}
