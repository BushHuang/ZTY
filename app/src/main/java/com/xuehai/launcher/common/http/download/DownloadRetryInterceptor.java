package com.xuehai.launcher.common.http.download;

import com.xuehai.launcher.common.http.DownloadCallback;
import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.interceptor.Interceptor;
import com.xuehai.launcher.common.log.MyLog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007H\u0016¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/common/http/download/DownloadRetryInterceptor;", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "Lcom/xuehai/launcher/common/http/LRequest;", "", "()V", "intercept", "chain", "Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadRetryInterceptor implements Interceptor<LRequest, Unit> {

    public static final Companion INSTANCE = new Companion(null);
    private static final String RETRY_URL = "http://filesoss.yunzuoye.net/XHFileServer/file/stream?url=";

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/http/download/DownloadRetryInterceptor$Companion;", "", "()V", "RETRY_URL", "", "getRetryDownloadUrl", "url", "needRetryDownload", "", "downloadUrl", "errorCode", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String getRetryDownloadUrl(String url) {
            return "http://filesoss.yunzuoye.net/XHFileServer/file/stream?url=" + url;
        }

        private final boolean needRetryDownload(String downloadUrl, int errorCode) {
            return (errorCode <= 0 || errorCode == 107001301 || StringsKt.contains$default((CharSequence) downloadUrl, (CharSequence) "http://filesoss.yunzuoye.net/XHFileServer/file/stream?url=", false, 2, (Object) null)) ? false : true;
        }
    }

    @Override
    public Unit intercept(Interceptor.Chain<LRequest, Unit> chain) {
        intercept2(chain);
        return Unit.INSTANCE;
    }

    public void intercept2(final Interceptor.Chain<LRequest, Unit> chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        final LRequest lRequestInput = chain.input();
        final String url = lRequestInput.getUrl();
        final DownloadCallback downloadCallback = DownloadExtKt.getDownloadCallback(lRequestInput);
        LRequest.Builder builderNewBuilder = lRequestInput.newBuilder();
        DownloadExtKt.setDownloadCallback(builderNewBuilder, new DownloadCallback() {
            @Override
            public void onFailure(int errorCode, String errorMessage) {
                if (!DownloadRetryInterceptor.INSTANCE.needRetryDownload(url, errorCode)) {
                    DownloadCallback downloadCallback2 = downloadCallback;
                    if (downloadCallback2 != null) {
                        downloadCallback2.onFailure(errorCode, errorMessage);
                        return;
                    }
                    return;
                }
                String retryDownloadUrl = DownloadRetryInterceptor.INSTANCE.getRetryDownloadUrl(url);
                MyLog.w("Download[MDM]", "下载失败(" + errorMessage + "),尝试调用接口方式下载 retryUrl=" + retryDownloadUrl);
                chain.process(LRequest.Builder.url$default(lRequestInput.newBuilder(), retryDownloadUrl, null, 2, null).build());
                super.onFailure(errorCode, errorMessage);
            }

            @Override
            public void onProgress(double fileTotalSize, double fileDownSize, double speed) {
                DownloadCallback downloadCallback2 = downloadCallback;
                if (downloadCallback2 != null) {
                    downloadCallback2.onProgress(fileTotalSize, fileDownSize, speed);
                }
            }

            @Override
            public void onStart(double total) {
                DownloadCallback downloadCallback2 = downloadCallback;
                if (downloadCallback2 != null) {
                    downloadCallback2.onStart(total);
                }
            }

            @Override
            public void onSuccess(String message, String savePath) {
                Intrinsics.checkNotNullParameter(savePath, "savePath");
                DownloadCallback downloadCallback2 = downloadCallback;
                if (downloadCallback2 != null) {
                    downloadCallback2.onSuccess(message, savePath);
                }
            }
        });
        chain.process(builderNewBuilder.build());
    }
}
