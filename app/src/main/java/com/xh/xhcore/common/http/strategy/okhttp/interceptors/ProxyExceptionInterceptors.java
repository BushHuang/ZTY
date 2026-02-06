package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import java.io.IOException;
import java.net.InetSocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.Route;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/ProxyExceptionInterceptors;", "Lokhttp3/Interceptor;", "()V", "getSocketAddressString", "", "connection", "Lokhttp3/Connection;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProxyExceptionInterceptors implements Interceptor {
    private final String getSocketAddressString(Connection connection) {
        if (connection == null) {
            return "";
        }
        InetSocketAddress inetSocketAddressSocketAddress = connection.route().socketAddress();
        inetSocketAddressSocketAddress.getAddress().getHostAddress();
        inetSocketAddressSocketAddress.getPort();
        return "";
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Connection connection = chain.connection();
        if (connection != null) {
            Route route = connection.route();
            RequestTagsUtil.putProxyMessageTag(chain, route == null ? null : route.proxy());
        }
        Response responseProceed = chain.proceed(chain.request());
        Intrinsics.checkNotNullExpressionValue(responseProceed, "proceedResult");
        return responseProceed;
    }
}
