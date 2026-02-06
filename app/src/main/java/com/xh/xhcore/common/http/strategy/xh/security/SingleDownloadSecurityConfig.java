package com.xh.xhcore.common.http.strategy.xh.security;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/SingleDownloadSecurityConfig;", "Lcom/xh/xhcore/common/http/strategy/xh/security/DownloadSecurityConfig;", "urlRemoteType", "", "metadata", "", "", "(ILjava/util/Map;)V", "getUrlRemoteType", "()I", "setUrlRemoteType", "(I)V", "isRemoteTypeBox", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SingleDownloadSecurityConfig extends DownloadSecurityConfig {
    private int urlRemoteType;

    public SingleDownloadSecurityConfig() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public SingleDownloadSecurityConfig(int i, Map<String, String> map) {
        super(map);
        Intrinsics.checkNotNullParameter(map, "metadata");
        this.urlRemoteType = i;
    }

    public SingleDownloadSecurityConfig(int i, HashMap map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 1 : i, (i2 & 2) != 0 ? new HashMap() : map);
    }

    public final int getUrlRemoteType() {
        return this.urlRemoteType;
    }

    public final boolean isRemoteTypeBox() {
        return 2 == this.urlRemoteType;
    }

    public final void setUrlRemoteType(int i) {
        this.urlRemoteType = i;
    }
}
