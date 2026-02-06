package com.xh.xhcore.common.http.strategy.xh.security;

import com.xh.xhcore.common.constant.ConstServerBaseUrlKt;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000eH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/SecuritySetApi;", "", "()V", "SECURITY_SET_URL_SERVER", "", "getSECURITY_SET_URL_SERVER", "()Ljava/lang/String;", "request", "", "T", "securitySetRequestBodyItems", "", "Lcom/xh/xhcore/common/http/strategy/xh/security/SecuritySetRequestBodyItem;", "httpCallback", "Lcom/xh/xhcore/common/http/XHRequestCallBack$HttpCallBack;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SecuritySetApi {
    public static final SecuritySetApi INSTANCE = new SecuritySetApi();
    private static final String SECURITY_SET_URL_SERVER = Intrinsics.stringPlus(ConstServerBaseUrlKt.BASE_URL_FILE_SERVER, "/XHFileServer/file/setAcl");

    private SecuritySetApi() {
    }

    @JvmStatic
    public static final <T> void request(List<SecuritySetRequestBodyItem> securitySetRequestBodyItems, XHRequestCallBack.HttpCallBack<T> httpCallback) {
        Intrinsics.checkNotNullParameter(securitySetRequestBodyItems, "securitySetRequestBodyItems");
        Intrinsics.checkNotNullParameter(httpCallback, "httpCallback");
        ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setUrl(SECURITY_SET_URL_SERVER)).setMethod("POST")).setRequestBody(ConstSecurity.listToJsonStr(securitySetRequestBodyItems)).setCallback(httpCallback)).request();
    }

    public final String getSECURITY_SET_URL_SERVER() {
        return SECURITY_SET_URL_SERVER;
    }
}
