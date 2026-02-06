package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.ReflectUtil;
import java.lang.ref.Reference;
import java.util.Deque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/ConnectionReuseInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConnectionReuseInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Connection connection = chain.connection();
        if (connection != null && (connection instanceof RealConnection)) {
            RealConnection realConnection = (RealConnection) connection;
            realConnection.allocations.size();
            Iterator<Reference<StreamAllocation>> it = realConnection.allocations.iterator();
            while (it.hasNext()) {
                LogUtils.INSTANCE.d(Intrinsics.stringPlus("streamAllocation = ", it.next().get()));
            }
            int i = realConnection.allocationLimit;
            Object field = ReflectUtil.getField(connection, "connectionPool");
            if (field == null) {
                throw new NullPointerException("null cannot be cast to non-null type okhttp3.ConnectionPool");
            }
            ConnectionPool connectionPool = (ConnectionPool) field;
            LogUtils.INSTANCE.d("allocations.size = " + realConnection.allocations.size() + " allocationLimit = " + realConnection.allocationLimit + " fieldConnectionPool connectionSize = " + connectionPool.connectionCount());
            Object field2 = ReflectUtil.getField(connectionPool, "connections");
            if (field2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.Deque<okhttp3.internal.connection.RealConnection>");
            }
            for (RealConnection realConnection2 : (Deque) field2) {
                LogUtils.INSTANCE.d(Intrinsics.stringPlus("realConnection = ", realConnection2));
                if (Intrinsics.areEqual(connection, realConnection2)) {
                    LogUtils.INSTANCE.d(Intrinsics.stringPlus("reuse connnection = ", realConnection2));
                }
            }
        }
        Response responseProceed = chain.proceed(chain.request());
        Intrinsics.checkNotNullExpressionValue(responseProceed, "proceedResult");
        return responseProceed;
    }
}
