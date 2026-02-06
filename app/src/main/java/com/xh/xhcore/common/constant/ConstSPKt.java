package com.xh.xhcore.common.constant;

import com.xh.xhcore.common.config.XmConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0010\u0010\u0004\u001a\u00020\u00018\u0006X\u0087D¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"HTTP_PROXY_FILE_NAME", "", "getHTTP_PROXY_FILE_NAME", "()Ljava/lang/String;", "SP_KEY_NETWORK_PROXY", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ConstSPKt {
    private static final String HTTP_PROXY_FILE_NAME = Intrinsics.stringPlus(XmConfig.getHttpProxyDirV2(), "/HttpProxy.ini");
    public static final String SP_KEY_NETWORK_PROXY = "sp_key_network_proxy";

    public static final String getHTTP_PROXY_FILE_NAME() {
        return HTTP_PROXY_FILE_NAME;
    }
}
