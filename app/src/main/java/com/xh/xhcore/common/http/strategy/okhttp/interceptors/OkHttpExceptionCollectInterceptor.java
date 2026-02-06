package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.dns.DNSLookupTypeManager;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.config.msg.OkHttpExceptionErrorMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/OkHttpExceptionCollectInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OkHttpExceptionCollectInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        DNSLookupTypeManager.increaseNetworkRetryTimes();
        try {
            Response responseProceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(responseProceed, "chain.proceed(request)");
            return responseProceed;
        } catch (Exception e) {
            String string = e.toString();
            OkHttpExceptionErrorMessage okHttpExceptionErrorMessage = (OkHttpExceptionErrorMessage) RequestTagsUtil.getTag(chain, OkHttpExceptionErrorMessage.class);
            if (okHttpExceptionErrorMessage == null) {
                okHttpExceptionErrorMessage = new OkHttpExceptionErrorMessage();
            }
            okHttpExceptionErrorMessage.putOpt(string, "networkRetryTimes = " + DNSLookupTypeManager.getNetworkRetryTimes() + " \n" + LogUtils.INSTANCE.getStackTraceString(e));
            RequestTagsUtil.putTag(chain, (Class<OkHttpExceptionErrorMessage>) OkHttpExceptionErrorMessage.class, okHttpExceptionErrorMessage);
            throw e;
        }
    }
}
