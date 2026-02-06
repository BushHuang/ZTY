package com.xh.xhcore.common.http.dns;

import com.xh.xhcore.common.extension.OkHttpExtensionKt;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestTagsUtil;
import com.xh.xhcore.common.http.strategy.xh.config.msg.NetworkRedirectErrorMessage;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.UnrepeatableRequestBody;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J \u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001a\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/xh/xhcore/common/http/dns/FollowUpMarkNetworkInterceptor;", "Lokhttp3/Interceptor;", "()V", "followUpUrl", "Lokhttp3/HttpUrl;", "followUpRequest", "", "client", "Lokhttp3/OkHttpClient;", "userResponse", "Lokhttp3/Response;", "route", "Lokhttp3/Route;", "followUpWithResponseCode", "method", "", "getRedirectString", "fromUrl", "toUrl", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "retryAfter", "", "defaultDelay", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FollowUpMarkNetworkInterceptor implements Interceptor {
    private HttpUrl followUpUrl;

    private final boolean followUpRequest(OkHttpClient client, Response userResponse, Route route) throws IOException {
        if (userResponse == null) {
            return false;
        }
        int iCode = userResponse.code();
        String strMethod = userResponse.request().method();
        if (iCode == 307 || iCode == 308) {
            if (!Intrinsics.areEqual(strMethod, "GET") && !Intrinsics.areEqual(strMethod, "HEAD")) {
                return false;
            }
            Intrinsics.checkNotNullExpressionValue(strMethod, "method");
            return followUpWithResponseCode(client, userResponse, strMethod);
        }
        if (iCode == 401) {
            return client.authenticator().authenticate(route, userResponse) != null;
        }
        if (iCode == 503) {
            if (userResponse.priorResponse() != null) {
                Response responsePriorResponse = userResponse.priorResponse();
                Intrinsics.checkNotNull(responsePriorResponse);
                if (responsePriorResponse.code() == 503) {
                    return false;
                }
            }
            return retryAfter(userResponse, Integer.MAX_VALUE) == 0 && userResponse.request() != null;
        }
        if (iCode == 407) {
            return client.proxyAuthenticator().authenticate(route, userResponse) != null;
        }
        if (iCode != 408) {
            switch (iCode) {
                case 300:
                case 301:
                case 302:
                case 303:
                    Intrinsics.checkNotNullExpressionValue(strMethod, "method");
                    break;
            }
            return false;
        }
        if (!client.retryOnConnectionFailure() || (userResponse.request().body() instanceof UnrepeatableRequestBody)) {
            return false;
        }
        if (userResponse.priorResponse() != null) {
            Response responsePriorResponse2 = userResponse.priorResponse();
            Intrinsics.checkNotNull(responsePriorResponse2);
            if (responsePriorResponse2.code() == 408) {
                return false;
            }
        }
        return retryAfter(userResponse, 0) <= 0 && userResponse.request() != null;
    }

    private final boolean followUpWithResponseCode(OkHttpClient client, Response userResponse, String method) {
        String strHeader;
        HttpUrl httpUrlResolve;
        if (!client.followRedirects() || (strHeader = userResponse.header("Location")) == null || (httpUrlResolve = userResponse.request().url().resolve(strHeader)) == null) {
            return false;
        }
        this.followUpUrl = httpUrlResolve;
        Intrinsics.checkNotNull(httpUrlResolve);
        return Intrinsics.areEqual(httpUrlResolve.scheme(), userResponse.request().url().scheme()) || client.followSslRedirects();
    }

    private final String getRedirectString(HttpUrl fromUrl, HttpUrl toUrl) {
        StringBuilder sb = new StringBuilder("网络重定向信息: [");
        sb.append("{ from: " + fromUrl + " }");
        sb.append("\r\n");
        sb.append("{ toUrl: " + toUrl + " }");
        sb.append("]");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "redirectSb.toString()");
        return string;
    }

    private final int retryAfter(Response userResponse, int defaultDelay) throws NumberFormatException {
        String strHeader = userResponse.header("Retry-After");
        if (strHeader == null) {
            return defaultDelay;
        }
        if (!new Regex("\\d+").matches(strHeader)) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strHeader);
        Intrinsics.checkNotNullExpressionValue(numValueOf, "{\n            Integer.valueOf(header)\n        }");
        return numValueOf.intValue();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response responseProceed = chain.proceed(chain.request());
        try {
            Call call = chain.call();
            Intrinsics.checkNotNullExpressionValue(call, "chain.call()");
            StreamAllocation streamAllocation = OkHttpExtensionKt.getStreamAllocation(call);
            Call call2 = chain.call();
            Intrinsics.checkNotNullExpressionValue(call2, "chain.call()");
            if (followUpRequest(OkHttpExtensionKt.getOkHttpClient(call2), responseProceed, streamAllocation == null ? null : streamAllocation.route())) {
                DNSLookupTypeManager.setFollowUpConditionMatch(true);
                HttpUrl httpUrlUrl = responseProceed.request().url();
                Intrinsics.checkNotNullExpressionValue(httpUrlUrl, "response.request().url()");
                RequestTagsUtil.putTag(chain, (Class<NetworkRedirectErrorMessage>) NetworkRedirectErrorMessage.class, new NetworkRedirectErrorMessage(getRedirectString(httpUrlUrl, this.followUpUrl)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intrinsics.checkNotNullExpressionValue(responseProceed, "response");
        return responseProceed;
    }
}
