package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.config.msg.CollectConnectSocketMessage;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/CollectIPInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "logTraceIdFromRequest", "", "request", "Lokhttp3/Request;", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CollectIPInterceptor implements Interceptor {
    private static final String OK_HTTP_RETURN_MSG_ID_KEY = "traceId = ";
    private static final String OK_HTTP_RETURN_MSG_NO_ID = "traceId = none";

    private final void logTraceIdFromRequest(Request request) {
        if (request != null) {
            String strHeader = request.header("X-B3-TraceId");
            LogUtils.INSTANCE.d(strHeader != null ? Intrinsics.stringPlus("traceId = ", strHeader) : "traceId = none");
        }
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        try {
            Connection connection = chain.connection();
            if (connection != null) {
                Socket socket = connection.socket();
                String str = "Unknown Socket Info }, ";
                if (socket != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Socket { Connect IP : ");
                    Serializable inetAddress = socket.getInetAddress();
                    if (inetAddress == null) {
                        inetAddress = "unKnown IP Address";
                    }
                    sb.append(inetAddress);
                    sb.append(" ，Socket Info : ");
                    sb.append(socket.getLocalSocketAddress());
                    sb.append(" ，Remote Socket Address : ");
                    sb.append(socket.getRemoteSocketAddress());
                    sb.append(" }, ");
                    String string = sb.toString();
                    if (string != null) {
                        str = string;
                    }
                }
                String strStringPlus = Intrinsics.stringPlus(str, StringsKt.replace$default(connection.toString(), "=", ": ", false, 4, (Object) null));
                RequestTagsUtil.putTag(chain, (Class<CollectConnectSocketMessage>) CollectConnectSocketMessage.class, new CollectConnectSocketMessage(strStringPlus));
                LogUtils.Companion.d$default(LogUtils.INSTANCE, "socketFullMsg", strStringPlus, null, 4, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response responseProceed = chain.proceed(request);
        logTraceIdFromRequest(request);
        Intrinsics.checkNotNullExpressionValue(responseProceed, "response");
        return responseProceed;
    }
}
