package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.archi.MD5Util;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.HttpDetailCollector;
import com.xh.xhcore.common.http.strategy.xh.config.ResponseSignTag;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/ResponseSignInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "verifySignMD5V1", "", "response", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ResponseSignInterceptor implements Interceptor {
    public static final String S_MD5_RESPONSE_BODY_PREFIX = "GHU^B_)+*&^%3~]";
    public static final String S_MD5_V1 = "s/md5v1";
    public static final String X_ACCEPT_SECURITY = "X-Accept-Security";
    public static final String X_CONTENT_SECURITY = "X-Content-Security";
    public static final String X_CONTENT_SIGN_TOKEN = "X-Content-Sign-Token";

    private final void verifySignMD5V1(Response response) throws IOException {
        if (response == null) {
            return;
        }
        String strHeader = response.header("X-Content-Sign-Token");
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody == null) {
            return;
        }
        Headers headers = response.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "response.headers()");
        String strEncode = MD5Util.encode(Intrinsics.stringPlus("GHU^B_)+*&^%3~]", HttpDetailCollector.getResponseBodyStringBuilder(headers, responseBodyBody)));
        LogUtils.INSTANCE.d("md5Result = " + ((Object) strEncode) + ", contentSecurityToken = " + ((Object) strHeader));
        if (!StringsKt.equals(strEncode, strHeader, true)) {
            throw new IOException("响应md5校验失败");
        }
        LogUtils.INSTANCE.d("response MD5 sign verify success");
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        ResponseSignTag responseSignTag = (ResponseSignTag) request.tag(ResponseSignTag.class);
        Response responseProceed = chain.proceed(request.newBuilder().addHeader("X-Accept-Security", "s/md5v1").build());
        String strHeader = responseProceed.header("X-Content-Security");
        if ((responseSignTag != null && responseSignTag.getNeedVerifyResponseSign()) && strHeader == null) {
            throw new IOException("响应签名校验失败");
        }
        if (strHeader != null) {
            if (StringsKt.contains$default((CharSequence) strHeader, (CharSequence) "s/md5v1", false, 2, (Object) null)) {
                verifySignMD5V1(responseProceed);
            } else {
                if (responseSignTag != null && responseSignTag.getNeedVerifyResponseSign()) {
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(responseProceed, "response");
        return responseProceed;
    }
}
