package com.xuehai.launcher.common.http.download;

import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.xh.download.XHDownloadOkHttpProxy;
import com.xuehai.launcher.common.http.DownloadCallback;
import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.interceptor.Interceptor;
import com.xuehai.launcher.common.log.MyLog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/http/download/OkHttpDownload;", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "Lcom/xuehai/launcher/common/http/LRequest;", "", "()V", "intercept", "chain", "Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OkHttpDownload implements Interceptor<LRequest, Unit> {
    @Override
    public Unit intercept(Interceptor.Chain<LRequest, Unit> chain) {
        intercept2(chain);
        return Unit.INSTANCE;
    }

    public void intercept2(Interceptor.Chain<LRequest, Unit> chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        LRequest lRequestInput = chain.input();
        final DownloadCallback downloadCallback = DownloadExtKt.getDownloadCallback(lRequestInput);
        final String savePath = DownloadExtKt.getSavePath(lRequestInput);
        if (savePath == null) {
            savePath = "";
        }
        final String url = lRequestInput.getUrl();
        MyLog.i("Download[MDM]", "download start: " + url + ')');
        DownloadTaskManager downloadTaskManager = DownloadTaskManager.INSTANCE;
        T tRequest = ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) XHDownloadOkHttpProxy.createOkHttp().setUrl(url)).setDownloadAgainWhenFail(false).setExchangeToMainThread(false)).setSavePath(savePath).setCallback(new XHRequestCallBack.XHDownloadCallBack() {
            private boolean isFirst = true;

            @Override
            public void failed(int p0, String p1) {
                MyLog.w("Download[MDM]", "download failed: " + url + ')');
                DownloadTaskManager.INSTANCE.removeDownloadTask(url);
                DownloadCallback downloadCallback2 = downloadCallback;
                if (downloadCallback2 != null) {
                    downloadCallback2.onFailure(p0, p1);
                }
            }

            public final boolean getIsFirst() {
                return this.isFirst;
            }

            @Override
            public void onProgress(int id, double total, double now, String jsonParam) {
                if (this.isFirst) {
                    this.isFirst = false;
                    DownloadCallback downloadCallback2 = downloadCallback;
                    if (downloadCallback2 != null) {
                        downloadCallback2.onStart(total);
                    }
                }
                DownloadCallback downloadCallback3 = downloadCallback;
                if (downloadCallback3 != null) {
                    downloadCallback3.onProgress(total, now, 0.0d);
                }
            }

            public final void setFirst(boolean z) {
                this.isFirst = z;
            }

            @Override
            public void success(String p0) {
                MyLog.i("Download[MDM]", "download success: " + url + ')');
                DownloadTaskManager.INSTANCE.removeDownloadTask(url);
                DownloadCallback downloadCallback2 = downloadCallback;
                if (downloadCallback2 != null) {
                    downloadCallback2.onSuccess(p0, savePath);
                }
            }
        })).request();
        Intrinsics.checkNotNullExpressionValue(tRequest, "val callback = request.d…               .request()");
        downloadTaskManager.addDownloadTask(url, (XHDownloadOkHttpProxy) tRequest);
    }
}
