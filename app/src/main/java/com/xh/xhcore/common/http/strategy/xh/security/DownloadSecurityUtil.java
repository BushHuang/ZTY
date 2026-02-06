package com.xh.xhcore.common.http.strategy.xh.security;

import android.text.TextUtils;
import com.xh.xhcore.common.http.XHErrorCodeUtil;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.archi.MD5Util;
import com.xh.xhcore.common.http.archi.ThreadUtil;
import com.xh.xhcore.common.http.strategy.BaseRequest;
import com.xh.xhcore.common.http.strategy.okhttp.download.DownloadOkHttp;
import com.xh.xhcore.common.http.strategy.xh.callback.BaseXHHttpCallback;
import com.xh.xhcore.common.http.strategy.xh.download.XHDownloadOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.security.DownloadSecurityUtil;
import com.xh.xhcore.common.oss.OssConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\n\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002J,\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0013H\u0007J\"\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0007J\u001e\u0010\u0018\u001a\u00020\u00102\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/DownloadSecurityUtil;", "", "()V", "EXCHANGE_TEMP_URL_SERVER", "", "getEXCHANGE_TEMP_URL_SERVER", "()Ljava/lang/String;", "computeDownloadSecuritySign", "kotlin.jvm.PlatformType", "conditions", "createIfNull", "Lcom/xh/xhcore/common/http/strategy/xh/security/DownloadSecurityConfig;", "downloadSecurityConfig", "Lcom/xh/xhcore/common/http/strategy/xh/security/SingleDownloadSecurityConfig;", "singleDownloadSecurityConfig", "getSecureTempUrl", "", "url", "callback", "Lcom/xh/xhcore/common/http/XHRequestCallBack$HttpCallBack;", "secureExchangeTempUrl", "", "xHDownloadOkHttpProxy", "Lcom/xh/xhcore/common/http/strategy/xh/download/XHDownloadOkHttpProxy;", "secureNoExchangeTempUrl", "baseRequest", "Lcom/xh/xhcore/common/http/strategy/BaseRequest;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadSecurityUtil {
    public static final DownloadSecurityUtil INSTANCE = new DownloadSecurityUtil();
    private static final String EXCHANGE_TEMP_URL_SERVER = Intrinsics.stringPlus(OssConfig.ALI_AK_SK_HOST, "/XHFileServer/file/getTempUrl");

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0016J\u0018\u0010\t\u001a\u00020\u00052\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016¨\u0006\u000b"}, d2 = {"com/xh/xhcore/common/http/strategy/xh/security/DownloadSecurityUtil$getSecureTempUrl$3", "Lcom/xh/xhcore/common/http/strategy/xh/callback/BaseXHHttpCallback;", "", "", "failed", "", "code", "", "msg", "success", "result", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass3 extends BaseXHHttpCallback<List<? extends String>> {
        final XHRequestCallBack.HttpCallBack<String> $callback;

        AnonymousClass3(XHRequestCallBack.HttpCallBack<String> httpCallBack) {
            this.$callback = httpCallBack;
        }

        private static final void m26failed$lambda2(XHRequestCallBack.HttpCallBack httpCallBack, int i, String str) {
            Intrinsics.checkNotNullParameter(str, "$message");
            if (httpCallBack == null) {
                return;
            }
            httpCallBack.failed(i, str);
        }

        private static final void m28success$lambda0(XHRequestCallBack.HttpCallBack httpCallBack, List list) {
            if (httpCallBack == null) {
                return;
            }
            httpCallBack.success(list.get(0));
        }

        private static final void m29success$lambda1(XHRequestCallBack.HttpCallBack httpCallBack) {
            if (httpCallBack == null) {
                return;
            }
            httpCallBack.failed(107002003, XHErrorCodeUtil.getErrorMsgInfo(107002003));
        }

        @Override
        public void failed(final int code, String msg) {
            final String strStringPlus = Intrinsics.stringPlus(msg, ", getTempUrl failed");
            super.failed(code, strStringPlus);
            final XHRequestCallBack.HttpCallBack<String> httpCallBack = this.$callback;
            ThreadUtil.toMainThreadChecked(true, httpCallBack, new Runnable() {
                @Override
                public final void run() {
                    DownloadSecurityUtil.AnonymousClass3.m26failed$lambda2(httpCallBack, code, strStringPlus);
                }
            });
        }

        @Override
        public void success(final List<String> result) {
            super.success((AnonymousClass3) result);
            List<String> list = result;
            if ((list == null || list.isEmpty()) || TextUtils.isEmpty(result.get(0))) {
                final XHRequestCallBack.HttpCallBack<String> httpCallBack = this.$callback;
                ThreadUtil.toMainThreadChecked(true, httpCallBack, new Runnable() {
                    @Override
                    public final void run() {
                        DownloadSecurityUtil.AnonymousClass3.m29success$lambda1(httpCallBack);
                    }
                });
            } else {
                final XHRequestCallBack.HttpCallBack<String> httpCallBack2 = this.$callback;
                ThreadUtil.toMainThreadChecked(true, httpCallBack2, new Runnable() {
                    @Override
                    public final void run() {
                        DownloadSecurityUtil.AnonymousClass3.m28success$lambda0(httpCallBack2, result);
                    }
                });
            }
        }
    }

    private DownloadSecurityUtil() {
    }

    private final String computeDownloadSecuritySign(String conditions) {
        return MD5Util.encode(StringsKt.reversed((CharSequence) conditions).toString());
    }

    private final DownloadSecurityConfig createIfNull(DownloadSecurityConfig downloadSecurityConfig) {
        return downloadSecurityConfig == null ? new DownloadSecurityConfig() : downloadSecurityConfig;
    }

    private final SingleDownloadSecurityConfig createIfNull(SingleDownloadSecurityConfig singleDownloadSecurityConfig) {
        return singleDownloadSecurityConfig == null ? new SingleDownloadSecurityConfig(0, null, 3, null) : singleDownloadSecurityConfig;
    }

    @JvmStatic
    public static final void getSecureTempUrl(final String url, SingleDownloadSecurityConfig singleDownloadSecurityConfig, final XHRequestCallBack.HttpCallBack<String> callback) {
        Intrinsics.checkNotNullParameter(url, "url");
        SingleDownloadSecurityConfig singleDownloadSecurityConfigCreateIfNull = INSTANCE.createIfNull(singleDownloadSecurityConfig);
        if (singleDownloadSecurityConfigCreateIfNull.isRemoteTypeBox()) {
            ThreadUtil.toMainThreadChecked(true, callback, new Runnable() {
                @Override
                public final void run() {
                    DownloadSecurityUtil.m23getSecureTempUrl$lambda0(callback, url);
                }
            });
            return;
        }
        if (!ConstSecurity.INSTANCE.isPrivate(url)) {
            ThreadUtil.toMainThreadChecked(true, callback, new Runnable() {
                @Override
                public final void run() {
                    DownloadSecurityUtil.m24getSecureTempUrl$lambda1(callback, url);
                }
            });
            return;
        }
        String conditions = singleDownloadSecurityConfigCreateIfNull.getConditions();
        XHRequestOkHttpProxy xHRequestOkHttpProxy = (XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setMethod("POST")).setUrl(EXCHANGE_TEMP_URL_SERVER)).setIsAsync(true)).setExchangeToMainThread(true);
        Intrinsics.checkNotNullExpressionValue(conditions, "conditions");
        String strComputeDownloadSecuritySign = INSTANCE.computeDownloadSecuritySign(conditions);
        Intrinsics.checkNotNullExpressionValue(strComputeDownloadSecuritySign, "computeDownloadSecuritySign(conditions)");
        ((XHRequestOkHttpProxy) xHRequestOkHttpProxy.setRequestBody((Object) CollectionsKt.listOf(new ExchangeTempUrlRequestBodyItem(url, conditions, strComputeDownloadSecuritySign, 0))).setCallback(new AnonymousClass3(callback))).request();
    }

    public static void getSecureTempUrl$default(String str, SingleDownloadSecurityConfig singleDownloadSecurityConfig, XHRequestCallBack.HttpCallBack httpCallBack, int i, Object obj) {
        if ((i & 2) != 0) {
            singleDownloadSecurityConfig = null;
        }
        getSecureTempUrl(str, singleDownloadSecurityConfig, httpCallBack);
    }

    private static final void m23getSecureTempUrl$lambda0(XHRequestCallBack.HttpCallBack httpCallBack, String str) {
        Intrinsics.checkNotNullParameter(str, "$url");
        if (httpCallBack == null) {
            return;
        }
        httpCallBack.success(str);
    }

    private static final void m24getSecureTempUrl$lambda1(XHRequestCallBack.HttpCallBack httpCallBack, String str) {
        Intrinsics.checkNotNullParameter(str, "$url");
        if (httpCallBack == null) {
            return;
        }
        httpCallBack.success(str);
    }

    @JvmStatic
    public static final boolean secureExchangeTempUrl(final XHDownloadOkHttpProxy xHDownloadOkHttpProxy, SingleDownloadSecurityConfig singleDownloadSecurityConfig, String url) {
        Intrinsics.checkNotNullParameter(xHDownloadOkHttpProxy, "xHDownloadOkHttpProxy");
        Intrinsics.checkNotNullParameter(url, "url");
        SingleDownloadSecurityConfig singleDownloadSecurityConfigCreateIfNull = INSTANCE.createIfNull(singleDownloadSecurityConfig);
        if (singleDownloadSecurityConfigCreateIfNull.isRemoteTypeBox()) {
            ?? baseRequest = xHDownloadOkHttpProxy.getBaseRequest();
            Intrinsics.checkNotNullExpressionValue(baseRequest, "xHDownloadOkHttpProxy.baseRequest");
            secureNoExchangeTempUrl(baseRequest, singleDownloadSecurityConfig);
            return false;
        }
        if (!ConstSecurity.INSTANCE.isPrivate(url)) {
            return false;
        }
        String conditions = singleDownloadSecurityConfigCreateIfNull.getConditions();
        XHRequestOkHttpProxy xHRequestOkHttpProxy = (XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setMethod("POST")).setUrl(EXCHANGE_TEMP_URL_SERVER)).setIsAsync(((DownloadOkHttp) xHDownloadOkHttpProxy.getBaseRequest()).isAsync())).setExchangeToMainThread(false);
        Intrinsics.checkNotNullExpressionValue(conditions, "conditions");
        String strComputeDownloadSecuritySign = INSTANCE.computeDownloadSecuritySign(conditions);
        Intrinsics.checkNotNullExpressionValue(strComputeDownloadSecuritySign, "computeDownloadSecuritySign(conditions)");
        ((XHRequestOkHttpProxy) xHRequestOkHttpProxy.setRequestBody((Object) CollectionsKt.listOf(new ExchangeTempUrlRequestBodyItem(url, conditions, strComputeDownloadSecuritySign, 0))).setCallback(new BaseXHHttpCallback<List<? extends String>>() {
            @Override
            public void failed(int code, String msg) {
                String strStringPlus = Intrinsics.stringPlus(msg, ", getTempUrl failed");
                super.failed(code, strStringPlus);
                xHDownloadOkHttpProxy.failedWithThreadChecked(code, strStringPlus);
            }

            @Override
            public void success(List<String> result) {
                super.success((AnonymousClass1) result);
                List<String> list = result;
                if ((list == null || list.isEmpty()) || TextUtils.isEmpty(result.get(0))) {
                    xHDownloadOkHttpProxy.failedWithThreadChecked(107002003, XHErrorCodeUtil.getErrorMsgInfo(107002003));
                } else {
                    ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) xHDownloadOkHttpProxy.setUrl(result.get(0))).setIsTempUrl(true).setNeedAddDefaultHeaders(false)).request();
                }
            }
        })).request();
        return true;
    }

    @JvmStatic
    public static final void secureNoExchangeTempUrl(BaseRequest<?> baseRequest, DownloadSecurityConfig downloadSecurityConfig) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        String conditions = INSTANCE.createIfNull(downloadSecurityConfig).getConditions();
        if (TextUtils.isEmpty(conditions)) {
            return;
        }
        BaseRequest baseRequestAddHeader = baseRequest.addHeader(ConstSecurity.SECURITY_CONDITIONS_KEY, (Object) conditions);
        String str = ConstSecurity.SECURITY_CONDITION_SIGN_KEY;
        DownloadSecurityUtil downloadSecurityUtil = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(conditions, "conditions");
        baseRequestAddHeader.addHeader(str, (Object) downloadSecurityUtil.computeDownloadSecuritySign(conditions));
    }

    public final String getEXCHANGE_TEMP_URL_SERVER() {
        return EXCHANGE_TEMP_URL_SERVER;
    }
}
