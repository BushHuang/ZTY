package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.util.DateUtil;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/DateRefreshInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DateRefreshInterceptor implements Interceptor {
    public static final String RESPONSE_HEADER_KEY_DATE = "Date";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String strHeader;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response responseProceed = chain.proceed(chain.request());
        if (responseProceed != null) {
            String strHost = responseProceed.request().url().host();
            Intrinsics.checkNotNullExpressionValue(strHost, "it.request().url().host()");
            if (StringsKt.contains$default((CharSequence) strHost, (CharSequence) "yunzuoye.net", false, 2, (Object) null) && (strHeader = responseProceed.header("Date")) != null) {
                DateUtil.setCurrentServerTime(strHeader);
            }
        }
        Intrinsics.checkNotNullExpressionValue(responseProceed, "response");
        return responseProceed;
    }
}
