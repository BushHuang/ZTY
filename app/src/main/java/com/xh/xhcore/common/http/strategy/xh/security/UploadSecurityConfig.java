package com.xh.xhcore.common.http.strategy.xh.security;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u001e\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/UploadSecurityConfig;", "", "securityType", "", "(Ljava/lang/String;)V", "conditions", "(Ljava/lang/String;Ljava/lang/String;)V", "<set-?>", "getConditions", "()Ljava/lang/String;", "isPrivate", "", "()Z", "getSecurityType$annotations", "()V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UploadSecurityConfig {
    private String conditions;
    private String securityType;

    public UploadSecurityConfig(String str) {
        Intrinsics.checkNotNullParameter(str, "securityType");
        this.securityType = "public";
        this.conditions = "";
        this.securityType = str;
    }

    public UploadSecurityConfig(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "securityType");
        Intrinsics.checkNotNullParameter(str2, "conditions");
        this.securityType = "public";
        this.conditions = "";
        this.securityType = str;
        this.conditions = str2;
    }

    private static void getSecurityType$annotations() {
    }

    public final String getConditions() {
        return this.conditions;
    }

    public final boolean isPrivate() {
        return Intrinsics.areEqual("private", this.securityType);
    }
}
