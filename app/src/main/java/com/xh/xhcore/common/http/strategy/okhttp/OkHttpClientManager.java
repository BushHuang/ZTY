package com.xh.xhcore.common.http.strategy.okhttp;

import com.google.gson.Gson;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.extension.OkHttpExtensionKt;
import com.xh.xhcore.common.http.strategy.HttpConfigKt;
import com.xh.xhcore.common.http.strategy.okhttp.BaseHttpLoggingInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.CollectIPInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.OkHttpExceptionCollectInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.ProxyExceptionInterceptors;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RedirectInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestHeadHandlerInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestSignInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestSignV2Interceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.ResponseSignCheckInterceptor;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.ResponseSignInterceptor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/OkHttpClientManager;", "", "()V", "<set-?>", "Lokhttp3/OkHttpClient;", "defaultRequestClient", "getDefaultRequestClient", "()Lokhttp3/OkHttpClient;", "defaultUploadDownloadClient", "getDefaultUploadDownloadClient", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "logBodyInterceptor", "Lcom/xh/xhcore/common/http/strategy/okhttp/BaseHttpLoggingInterceptor;", "logHeaderInterceptor", "getCommonClientBuilder", "Lokhttp3/OkHttpClient$Builder;", "networkProxy", "", "initOkHttpClient", "", "setLogEnable", "logEnable", "", "Companion", "RequestBodyWrapperFactory", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OkHttpClientManager {

    public static final Companion INSTANCE = new Companion(null);
    private static OkHttpClientManager mInstance;
    private OkHttpClient defaultRequestClient;
    private OkHttpClient defaultUploadDownloadClient;
    private final Gson gson;
    private final BaseHttpLoggingInterceptor logBodyInterceptor;
    private final BaseHttpLoggingInterceptor logHeaderInterceptor;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/OkHttpClientManager$Companion;", "", "()V", "instance", "Lcom/xh/xhcore/common/http/strategy/okhttp/OkHttpClientManager;", "getInstance$annotations", "getInstance", "()Lcom/xh/xhcore/common/http/strategy/okhttp/OkHttpClientManager;", "mInstance", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final OkHttpClientManager getInstance() {
            if (OkHttpClientManager.mInstance == null) {
                synchronized (OkHttpClientManager.class) {
                    if (OkHttpClientManager.mInstance == null) {
                        Companion companion = OkHttpClientManager.INSTANCE;
                        OkHttpClientManager.mInstance = new OkHttpClientManager(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            OkHttpClientManager okHttpClientManager = OkHttpClientManager.mInstance;
            Intrinsics.checkNotNull(okHttpClientManager);
            return okHttpClientManager;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/OkHttpClientManager$RequestBodyWrapperFactory;", "", "createRequestBodyWrapper", "Lokhttp3/RequestBody;", "requestBody", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface RequestBodyWrapperFactory {
        RequestBody createRequestBodyWrapper(RequestBody requestBody);
    }

    private OkHttpClientManager() {
        this.gson = new Gson();
        this.logBodyInterceptor = new BaseHttpLoggingInterceptor();
        this.logHeaderInterceptor = new BaseHttpLoggingInterceptor();
        initOkHttpClient();
    }

    public OkHttpClientManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final OkHttpClient.Builder getCommonClientBuilder(String networkProxy) {
        OkHttpClient.Builder builderAddNetworkInterceptor = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).addInterceptor(new RedirectInterceptor()).addNetworkInterceptor(new OkHttpExceptionCollectInterceptor()).addNetworkInterceptor(new ProxyExceptionInterceptors());
        if (XHAppConfigProxy.getInstance().isObtainDeviceInfo()) {
            builderAddNetworkInterceptor.addNetworkInterceptor(new CollectIPInterceptor());
        }
        Intrinsics.checkNotNullExpressionValue(builderAddNetworkInterceptor, "builder");
        OkHttpClient.Builder builderProtocols = OkHttpExtensionKt.httpsConfig(OkHttpExtensionKt.dnsConfig(OkHttpExtensionKt.setProxyIfNotEmpty(builderAddNetworkInterceptor, networkProxy))).protocols(Util.immutableList(Protocol.HTTP_1_1));
        Intrinsics.checkNotNullExpressionValue(builderProtocols, "builder.setProxyIfNotEmp…eList(Protocol.HTTP_1_1))");
        return builderProtocols;
    }

    public static final OkHttpClientManager getInstance() {
        return INSTANCE.getInstance();
    }

    private final void initOkHttpClient() {
        setLogEnable(true);
        RequestSignInterceptor requestSignV2Interceptor = XHAppConfigProxy.getInstance().useV2Sign() ? new RequestSignV2Interceptor() : new RequestSignInterceptor();
        String httpProxy = HttpConfigKt.getHttpProxy();
        this.defaultRequestClient = getCommonClientBuilder(httpProxy).readTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).addInterceptor(new RequestHeadHandlerInterceptor()).addInterceptor(new ResponseSignInterceptor()).addInterceptor(this.logBodyInterceptor).addInterceptor(new ResponseSignCheckInterceptor()).addNetworkInterceptor(requestSignV2Interceptor).eventListenerFactory(HttpEventListener.FACTORY).build();
        this.defaultUploadDownloadClient = getCommonClientBuilder(httpProxy).readTimeout(300L, TimeUnit.SECONDS).writeTimeout(300L, TimeUnit.SECONDS).addInterceptor(this.logHeaderInterceptor).eventListenerFactory(HttpEventListener.DOWNLOAD_UPLOAD_FACTORY).build();
    }

    public final OkHttpClient getDefaultRequestClient() {
        return this.defaultRequestClient;
    }

    public final OkHttpClient getDefaultUploadDownloadClient() {
        return this.defaultUploadDownloadClient;
    }

    public final Gson getGson() {
        return this.gson;
    }

    public final void setLogEnable(boolean logEnable) {
        this.logBodyInterceptor.setLevel(logEnable ? BaseHttpLoggingInterceptor.Level.BODY : BaseHttpLoggingInterceptor.Level.NONE);
        this.logHeaderInterceptor.setLevel(logEnable ? BaseHttpLoggingInterceptor.Level.HEADERS : BaseHttpLoggingInterceptor.Level.NONE);
    }
}
