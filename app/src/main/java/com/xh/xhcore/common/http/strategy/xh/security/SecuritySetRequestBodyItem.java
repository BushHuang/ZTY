package com.xh.xhcore.common.http.strategy.xh.security;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/SecuritySetRequestBodyItem;", "", "url", "", "type", "conditions", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "condSign", "getCondSign", "()Ljava/lang/String;", "setCondSign", "(Ljava/lang/String;)V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SecuritySetRequestBodyItem {
    private String condSign;
    private final String conditions;
    private final String type;
    private final String url;

    public SecuritySetRequestBodyItem() {
        this(null, null, null, 7, null);
    }

    public SecuritySetRequestBodyItem(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "type");
        Intrinsics.checkNotNullParameter(str3, "conditions");
        this.url = str;
        this.type = str2;
        this.conditions = str3;
        this.condSign = "";
        String strComputeUploadSecuritySign = ConstSecurity.INSTANCE.computeUploadSecuritySign(this.type, this.conditions);
        Intrinsics.checkNotNullExpressionValue(strComputeUploadSecuritySign, "computeUploadSecuritySign(type, conditions)");
        this.condSign = strComputeUploadSecuritySign;
    }

    public SecuritySetRequestBodyItem(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "private" : str2, (i & 4) != 0 ? "" : str3);
    }

    public final String getCondSign() {
        return this.condSign;
    }

    public final void setCondSign(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.condSign = str;
    }
}
