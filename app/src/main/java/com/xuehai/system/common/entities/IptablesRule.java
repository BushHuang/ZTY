package com.xuehai.system.common.entities;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u0004\u0018\u00010\u0003R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/xuehai/system/common/entities/IptablesRule;", "", "rule", "", "(Ljava/lang/String;)V", "application", "getApplication", "()Ljava/lang/String;", "ipAddress", "getIpAddress", "portNumber", "getPortNumber", "getUrl", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IptablesRule {
    private final String application;
    private final String ipAddress;
    private final String portNumber;

    public IptablesRule(String str) {
        Intrinsics.checkNotNullParameter(str, "rule");
        List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{";"}, false, 0, 6, (Object) null);
        List listSplit$default2 = StringsKt.split$default((CharSequence) listSplit$default.get(0), new String[]{":"}, false, 0, 6, (Object) null);
        if (listSplit$default2.size() == 2) {
            this.ipAddress = (String) listSplit$default2.get(0);
            this.portNumber = (String) listSplit$default2.get(1);
        } else {
            this.ipAddress = null;
            this.portNumber = null;
        }
        this.application = listSplit$default.size() >= 3 ? (String) listSplit$default.get(2) : (String) null;
    }

    public final String getApplication() {
        return this.application;
    }

    public final String getIpAddress() {
        return this.ipAddress;
    }

    public final String getPortNumber() {
        return this.portNumber;
    }

    public final String getUrl() {
        if (this.ipAddress == null || this.portNumber == null) {
            return (String) null;
        }
        return this.ipAddress + ':' + this.portNumber;
    }
}
