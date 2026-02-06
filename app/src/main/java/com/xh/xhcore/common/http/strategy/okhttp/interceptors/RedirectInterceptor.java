package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.strategy.xh.config.msg.RedirectErrorMessage;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00072\u00020\u0001:\u0002\u0007\bB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/RedirectInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "DelegateIOException", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RedirectInterceptor implements Interceptor {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/RedirectInterceptor$Companion;", "", "()V", "getRedirectString", "", "priorRequestString", "currentRequestString", "priorRequest", "Lokhttp3/Request;", "proceedRequest", "priorResponse", "Lokhttp3/Response;", "proceedResult", "getRequestString", "request", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String getRedirectString(String priorRequestString, String currentRequestString) {
            StringBuilder sb = new StringBuilder("重定向信息: [");
            sb.append("{ priorRequest: " + priorRequestString + " }");
            sb.append("\r\n");
            sb.append("{ currentRequest: " + currentRequestString + " }");
            sb.append("]");
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "redirectSb.toString()");
            return string;
        }

        private final String getRedirectString(Response priorResponse, Response proceedResult) {
            Response responseNetworkResponse;
            Request request = priorResponse.request();
            Request request2 = null;
            if (proceedResult != null && (responseNetworkResponse = proceedResult.networkResponse()) != null) {
                request2 = responseNetworkResponse.request();
            }
            return getRedirectString(request, request2);
        }

        private final String getRequestString(Request request) {
            StringBuilder sb = new StringBuilder();
            if (request != null) {
                sb.append(Intrinsics.stringPlus("method: ", request.method()));
                sb.append(",");
                sb.append(Intrinsics.stringPlus("url: ", request.url()));
                sb.append(";");
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "requestSb.toString()");
            return string;
        }

        @JvmStatic
        public final String getRedirectString(Request priorRequest, Request proceedRequest) {
            return getRedirectString(getRequestString(priorRequest), getRequestString(proceedRequest));
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/RedirectInterceptor$DelegateIOException;", "Ljava/io/IOException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class DelegateIOException extends IOException {
        public DelegateIOException(String str, Throwable th) {
            super(str, th);
        }
    }

    @JvmStatic
    public static final String getRedirectString(Request request, Request request2) {
        return INSTANCE.getRedirectString(request, request2);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Intrinsics.checkNotNullParameter(chain, "chain");
        try {
            Response responseProceed = chain.proceed(chain.request());
            Response responsePriorResponse = responseProceed.priorResponse();
            if (responsePriorResponse != null && responsePriorResponse.isRedirect()) {
                RequestTagsUtil.putTag(chain, (Class<RedirectErrorMessage>) RedirectErrorMessage.class, new RedirectErrorMessage(INSTANCE.getRedirectString(responsePriorResponse, responseProceed)));
            }
            Intrinsics.checkNotNullExpressionValue(responseProceed, "proceedResult");
            return responseProceed;
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw e;
            }
            String string = chain.request().toString();
            Intrinsics.checkNotNullExpressionValue(string, "chain.request().toString()");
            throw new DelegateIOException(string, e);
        }
    }
}
