package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import android.net.Uri;
import android.util.Log;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.BaseMicroServer;
import com.xh.xhcore.common.http.XHMicroServer;
import com.xh.xhcore.common.http.archi.CommonUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/RequestHeadHandlerInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "ipOrDomain", "", "url", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RequestHeadHandlerInterceptor implements Interceptor {
    private static final List<String> DOMAIN_WHITE_LIST = CollectionsKt.mutableListOf("yunzuoye.net", "ztytech.com", "100fen.com", "47.111.87.154", "47.110.67.143", "47.110.64.133", "47.111.186.140", "xht.com", "192.168.10.47", "xh.com", "192.168.5.66");

    private final String ipOrDomain(String url) {
        if (url == null) {
            return null;
        }
        String host = Uri.parse(url).getHost();
        return !CommonUtil.isIP(host) ? PublicSuffixDatabase.get().getEffectiveTldPlusOne(host) : host;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        if (!XHAppConfigProxy.getInstance().enableDomainWhiteList()) {
            Response responseProceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(responseProceed, "chain.proceed(originRequest)");
            return responseProceed;
        }
        ArrayList domainWhiteList = XHAppConfigProxy.getInstance().getDomainWhiteList();
        if (domainWhiteList == null) {
            domainWhiteList = new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DOMAIN_WHITE_LIST);
        arrayList.addAll(domainWhiteList);
        ExternalLinkTag externalLinkTag = new ExternalLinkTag(true);
        String string = request.url().url().toString();
        Intrinsics.checkNotNullExpressionValue(string, "originRequest.url().url().toString()");
        if (CollectionsKt.contains(arrayList, ipOrDomain(string))) {
            externalLinkTag.setExternalLink(false);
        } else {
            ServerTypeTag serverTypeTag = (ServerTypeTag) request.tag(ServerTypeTag.class);
            if ((serverTypeTag == null ? null : serverTypeTag.getServerType()) != null) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    BaseMicroServer xHMicroServer = XHMicroServer.getInstance();
                    String serverType = serverTypeTag.getServerType();
                    Intrinsics.checkNotNull(serverType);
                    if (ipOrDomain(xHMicroServer.getServerUrl(serverType).getUrl()) != null) {
                        externalLinkTag.setExternalLink(false);
                    }
                    Result.m228constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m228constructorimpl(ResultKt.createFailure(th));
                }
            }
        }
        Log.e("ExternalLinkTag", Intrinsics.stringPlus("ExternalLinkTag=", Boolean.valueOf(externalLinkTag.isExternalLink())));
        Request.Builder builderTag = request.newBuilder().tag(ExternalLinkTag.class, externalLinkTag);
        if (externalLinkTag.isExternalLink()) {
            builderTag.removeHeader("Authorization");
        }
        Response responseProceed2 = chain.proceed(builderTag.build());
        Intrinsics.checkNotNullExpressionValue(responseProceed2, "chain.proceed(newRequest)");
        return responseProceed2;
    }
}
