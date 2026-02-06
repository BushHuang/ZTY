package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestSignUtil;
import com.xh.xhcore.jni.XHCoreJniCache;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/RequestSignV2Interceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RequestSignV2Interceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static final void m21intercept$lambda1(String str) {
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
        String str = (StringsKt.contains$default((CharSequence) string2, (CharSequence) "?", false, 2, (Object) null) ? Intrinsics.stringPlus(string2, "&") : Intrinsics.stringPlus(string2, "?")) + "_xhsrc_=" + ((Object) XHAppConfigProxy.getInstance().getSignSource()) + "&_nonce_=" + UUID.randomUUID() + "&_uid_=" + ((Object) XhBaseApplication.getXHUserId());
        String[] signKey = XHCoreJniCache.getSignKey();
        String str2 = signKey[0];
        String str3 = signKey[1];
        if (Intrinsics.areEqual("-1", str2) || Intrinsics.areEqual("-1", str3)) {
            throw new IOException("签名异常");
        }
        if (XHCoreJniCache.isStaticKey(str2)) {
            request = request.newBuilder().header("Authorization", "").build();
        } else {
            String strHeader = request.header("Authorization");
            if (strHeader == null || Intrinsics.areEqual(strHeader, "")) {
                String[] xhStaticSignatureKey = XHCoreJniCache.getXhStaticSignatureKey();
                str2 = xhStaticSignatureKey[0];
                str3 = xhStaticSignatureKey[1];
                if (Intrinsics.areEqual("-1", str2) || Intrinsics.areEqual("-1", str3)) {
                    throw new IOException("签名异常");
                }
            }
        }
        String str4 = str2;
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
        String strAddRestfulVerifyDataV2 = RequestSignUtil.addRestfulVerifyDataV2(request.method(), str, str4, str3, string, request.headers().get("Authorization"), new RequestSignUtil.SignListener() {
            @Override
            public final void onSignatureBodyReady(String str5) {
                RequestSignV2Interceptor.m21intercept$lambda1(str5);
            }
        });
        Intrinsics.checkNotNullExpressionValue(strAddRestfulVerifyDataV2, "addRestfulVerifyDataV2(\n…)\n            }\n        }");
        Response responseProceed = chain.proceed(request.newBuilder().url(strAddRestfulVerifyDataV2).build());
        Intrinsics.checkNotNullExpressionValue(responseProceed, "chain.proceed(request.ne…er().url(newUrl).build())");
        return responseProceed;
    }
}
