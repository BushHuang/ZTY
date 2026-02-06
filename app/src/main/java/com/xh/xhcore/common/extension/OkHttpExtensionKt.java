package com.xh.xhcore.common.extension;

import android.text.TextUtils;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.dns.BootstrapDNS;
import com.xh.xhcore.common.http.dns.DNSStateRetryInterceptor;
import com.xh.xhcore.common.http.dns.FollowUpMarkNetworkInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.HttpEventListener;
import com.xh.xhcore.common.http.strategy.okhttp.https.HttpsUtil;
import com.xh.xhcore.common.http.strategy.okhttp.https.XHHostnameVerifier;
import com.xh.xhcore.common.util.ReflectUtil;
import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0004\u001a\f\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0004\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0001\u001a\f\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0004\u001a\u0014\u0010\f\u001a\u00020\u0001*\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¨\u0006\u000f"}, d2 = {"dnsConfig", "Lokhttp3/OkHttpClient$Builder;", "getOkHttpClient", "Lokhttp3/OkHttpClient;", "Lokhttp3/Call;", "getRetryAndFollowUpInterceptor", "Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "getStreamAllocation", "Lokhttp3/internal/connection/StreamAllocation;", "httpsConfig", "reflectHttpEventListener", "Lcom/xh/xhcore/common/http/strategy/okhttp/HttpEventListener;", "setProxyIfNotEmpty", "networkProxy", "", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class OkHttpExtensionKt {
    public static final OkHttpClient.Builder dnsConfig(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.dns(new BootstrapDNS(null, 1, 0 == true ? 1 : 0));
        if (XHAppConfigProxy.getInstance().enableExtendDNS()) {
            builder.addInterceptor(new DNSStateRetryInterceptor()).addNetworkInterceptor(new FollowUpMarkNetworkInterceptor());
        }
        return builder;
    }

    public static final OkHttpClient getOkHttpClient(Call call) throws Exception {
        Intrinsics.checkNotNullParameter(call, "<this>");
        Object field = ReflectUtil.getField(getRetryAndFollowUpInterceptor(call), "client");
        if (field != null) {
            return (OkHttpClient) field;
        }
        throw new NullPointerException("null cannot be cast to non-null type okhttp3.OkHttpClient");
    }

    public static final RetryAndFollowUpInterceptor getRetryAndFollowUpInterceptor(Call call) throws Exception {
        Intrinsics.checkNotNullParameter(call, "<this>");
        Object field = ReflectUtil.getField(call, "retryAndFollowUpInterceptor");
        if (field != null) {
            return (RetryAndFollowUpInterceptor) field;
        }
        throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.http.RetryAndFollowUpInterceptor");
    }

    public static final StreamAllocation getStreamAllocation(Call call) {
        Intrinsics.checkNotNullParameter(call, "<this>");
        return getRetryAndFollowUpInterceptor(call).streamAllocation();
    }

    public static final OkHttpClient.Builder httpsConfig(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        SSLSocketFactory sSLSocketFactory = HttpsUtil.getSSLSocketFactory(null, null);
        if (sSLSocketFactory != null) {
            builder.hostnameVerifier(XHHostnameVerifier.INSTANCE.getINSTANCE()).sslSocketFactory(sSLSocketFactory);
        }
        return builder;
    }

    public static final HttpEventListener reflectHttpEventListener(Call call) throws Exception {
        Intrinsics.checkNotNullParameter(call, "<this>");
        Object field = ReflectUtil.getField(call, "eventListener");
        return (field == null || !(field instanceof HttpEventListener)) ? (HttpEventListener) null : (HttpEventListener) field;
    }

    public static final OkHttpClient.Builder setProxyIfNotEmpty(OkHttpClient.Builder builder, String str) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        if (!TextUtils.isEmpty(str)) {
            builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, 8888)));
        }
        return builder;
    }
}
