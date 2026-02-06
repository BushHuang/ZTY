package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestSignUtil;
import com.xh.xhcore.jni.XHCoreJniCache;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/RequestSignInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RequestSignInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static final void m19intercept$lambda1(String str) {
        if (str.length() < 2000) {
            LogUtils.Companion.d$default(LogUtils.INSTANCE, "tag_request", Intrinsics.stringPlus("acSignature = ", str), null, 4, null);
        }
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String string;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        String string2 = request.url().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "request.url().toString()");
        Buffer buffer = new Buffer();
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody == null) {
            string = "";
        } else {
            requestBodyBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType mediaTypeContentType = requestBodyBody.contentType();
            if (mediaTypeContentType != null) {
                charset = mediaTypeContentType.charset(UTF8);
            }
            string = buffer.readString(charset);
            Intrinsics.checkNotNullExpressionValue(string, "buffer.readString(charset)");
        }
        String strAddRestfulVerifyData = RequestSignUtil.addRestfulVerifyData(request.method(), string2, XHCoreJniCache.getXhSignatureKey(), string, request.headers().get("Authorization"), new RequestSignUtil.SignListener() {
            @Override
            public final void onSignatureBodyReady(String str) {
                RequestSignInterceptor.m19intercept$lambda1(str);
            }
        });
        Intrinsics.checkNotNullExpressionValue(strAddRestfulVerifyData, "addRestfulVerifyData(req…)\n            }\n        }");
        Response responseProceed = chain.proceed(request.newBuilder().url(strAddRestfulVerifyData).build());
        Intrinsics.checkNotNullExpressionValue(responseProceed, "chain.proceed(request.ne…er().url(newUrl).build())");
        return responseProceed;
    }
}
