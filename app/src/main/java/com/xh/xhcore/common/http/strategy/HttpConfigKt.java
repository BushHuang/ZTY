package com.xh.xhcore.common.http.strategy;

import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.constant.ConstSPKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003¨\u0006\u0006"}, d2 = {"clearHttpProxy", "", "getHttpProxy", "", "setHttpProxy", "proxy", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class HttpConfigKt {
    public static final void clearHttpProxy() {
        XmConfig.XMPropertiesUtil.clear(ConstSPKt.getHTTP_PROXY_FILE_NAME());
    }

    public static final String getHttpProxy() {
        return XmConfig.XMPropertiesUtil.getProperty(ConstSPKt.getHTTP_PROXY_FILE_NAME(), ConstSPKt.SP_KEY_NETWORK_PROXY);
    }

    public static final void setHttpProxy(String str) {
        Intrinsics.checkNotNullParameter(str, "proxy");
        XmConfig.XMPropertiesUtil.setProperty(ConstSPKt.getHTTP_PROXY_FILE_NAME(), ConstSPKt.SP_KEY_NETWORK_PROXY, str);
    }
}
