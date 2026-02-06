package com.xh.xhcore.common.http.strategy.xh.security;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/ExchangeTempUrlRequestBodyItem;", "", "url", "", "conditions", "condSign", "expireTime", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getCondSign", "()Ljava/lang/String;", "getConditions", "getExpireTime", "()I", "getUrl", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExchangeTempUrlRequestBodyItem {
    private final String condSign;
    private final String conditions;
    private final int expireTime;
    private final String url;

    public ExchangeTempUrlRequestBodyItem() {
        this(null, null, null, 0, 15, null);
    }

    public ExchangeTempUrlRequestBodyItem(String str, String str2, String str3, int i) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "conditions");
        Intrinsics.checkNotNullParameter(str3, "condSign");
        this.url = str;
        this.conditions = str2;
        this.condSign = str3;
        this.expireTime = i;
    }

    public ExchangeTempUrlRequestBodyItem(String str, String str2, String str3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? 0 : i);
    }

    public final String getCondSign() {
        return this.condSign;
    }

    public final String getConditions() {
        return this.conditions;
    }

    public final int getExpireTime() {
        return this.expireTime;
    }

    public final String getUrl() {
        return this.url;
    }
}
