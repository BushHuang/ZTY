package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import android.util.Log;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.util.PlatformUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/ResponseSignCheckInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ResponseSignCheckInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        BufferedSource bufferedSourceSource;
        Buffer bufferClone;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response responseProceed = chain.proceed(chain.request());
        if (XHAppConfigProxy.getInstance().useV2Sign() && XHAppConfigProxy.getInstance().enableLogoutWhenSignError() && responseProceed.code() == 403) {
            try {
                Result.Companion companion = Result.INSTANCE;
                ResponseBody responseBodyBody = responseProceed.body();
                String string = null;
                if (responseBodyBody == null || (bufferedSourceSource = responseBodyBody.source()) == null) {
                    bufferedSourceSource = null;
                } else {
                    bufferedSourceSource.request(Long.MAX_VALUE);
                }
                Buffer buffer = bufferedSourceSource == null ? null : bufferedSourceSource.buffer();
                if (buffer != null && (bufferClone = buffer.clone()) != null) {
                    string = bufferClone.readString(Charset.forName("UTF-8"));
                }
                if (string != null) {
                    int iOptInt = new JSONObject(string).optInt("code");
                    String strOptString = new JSONObject(string).optString("msg");
                    switch (iOptInt) {
                        case 403001:
                            Log.d("ResponseSign", "无效签名，请重新登录");
                            PlatformUtil.INSTANCE.notifyLogoutPlatform(Integer.valueOf(iOptInt), strOptString);
                            break;
                        case 403002:
                            Log.d("ResponseSign", "签名过期，请重新登录");
                            PlatformUtil.INSTANCE.notifyLogoutPlatform(Integer.valueOf(iOptInt), strOptString);
                            break;
                        case 403003:
                            Log.d("ResponseSign", "签名异常，请重新登录");
                            PlatformUtil.INSTANCE.notifyLogoutPlatform(Integer.valueOf(iOptInt), strOptString);
                            break;
                    }
                }
                Result.m228constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m228constructorimpl(ResultKt.createFailure(th));
            }
        }
        Intrinsics.checkNotNullExpressionValue(responseProceed, "response");
        return responseProceed;
    }
}
