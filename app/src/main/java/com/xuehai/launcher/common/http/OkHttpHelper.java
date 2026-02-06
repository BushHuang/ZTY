package com.xuehai.launcher.common.http;

import com.xuehai.launcher.common.constants.error.RestFulError;
import com.zaze.utils.JsonUtil;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0003J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0007J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/common/http/OkHttpHelper;", "", "()V", "buildRequest", "Lokhttp3/Request;", "request", "Lcom/xuehai/launcher/common/http/LRequest;", "buildRequestBody", "Lokhttp3/RequestBody;", "requestBody", "Lcom/xuehai/launcher/common/http/LRequestBody;", "copyResponse", "Lcom/xuehai/launcher/common/http/LResponse;", "from", "Lokhttp3/Response;", "to", "getRequestClient", "Lokhttp3/OkHttpClient;", "defaultClient", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OkHttpHelper {
    public static final OkHttpHelper INSTANCE = new OkHttpHelper();

    private OkHttpHelper() {
    }

    @JvmStatic
    public static final Request buildRequest(LRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        Request.Builder builder = new Request.Builder();
        builder.url(request.getUrl()).method(request.getMethod(), buildRequestBody(request.getBody()));
        Map<String, Object> map = request.getHeader().headers;
        Intrinsics.checkNotNullExpressionValue(map, "request.header.headers");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue().toString());
        }
        Request requestBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(requestBuild, "builder.build()");
        return requestBuild;
    }

    @JvmStatic
    private static final RequestBody buildRequestBody(LRequestBody requestBody) {
        if (requestBody != null) {
            return RequestBody.create(MediaType.parse(requestBody.getMediaType().getMediaType()), requestBody.getContent());
        }
        return null;
    }

    @JvmStatic
    public static final LResponse copyResponse(Response from, LResponse to) {
        String strString;
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        to.setCode(from.code());
        ResponseBody responseBodyBody = from.body();
        if (responseBodyBody != null) {
            try {
                strString = responseBodyBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            strString = null;
        }
        to.setResponseBody(strString);
        if (!from.isSuccessful()) {
            to.setRestFulError((RestFulError) JsonUtil.parseJson(strString, RestFulError.class));
        }
        return to;
    }

    public final OkHttpClient getRequestClient(OkHttpClient defaultClient, LRequest request) {
        Intrinsics.checkNotNullParameter(defaultClient, "defaultClient");
        Intrinsics.checkNotNullParameter(request, "request");
        if (!request.hasTimeout()) {
            return defaultClient;
        }
        OkHttpClient.Builder builderNewBuilder = defaultClient.newBuilder();
        if (request.getConnectTimeout() > 0) {
            builderNewBuilder.connectTimeout(request.getConnectTimeout(), TimeUnit.MILLISECONDS);
        }
        if (request.getReadTimeout() > 0) {
            builderNewBuilder.readTimeout(request.getReadTimeout(), TimeUnit.MILLISECONDS);
        }
        if (request.getWriteTimeout() > 0) {
            builderNewBuilder.writeTimeout(request.getWriteTimeout(), TimeUnit.MILLISECONDS);
        }
        OkHttpClient okHttpClientBuild = builderNewBuilder.build();
        Intrinsics.checkNotNullExpressionValue(okHttpClientBuild, "builder.build()");
        return okHttpClientBuild;
    }
}
