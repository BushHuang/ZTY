package com.xh.xhcore.common.http.strategy.xh.download;

import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.BaseRequest;
import com.xh.xhcore.common.http.strategy.okhttp.download.DownloadOkHttp;
import com.xh.xhcore.common.http.strategy.okhttp.download.IDownload;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.download.XHDownloadProxy;
import com.xh.xhcore.common.upload.XHTask;

public abstract class XHDownloadProxy<T extends XHDownloadProxy, R extends BaseRequest> extends XHBaseRequestProxy<T, R, XHRequestCallBack.XHDownloadCallBack> implements IDownload<T> {
    protected boolean downloadAgainWhenFail;
    protected boolean hasFailedBefore;

    XHDownloadProxy(R r) {
        super(r);
        this.downloadAgainWhenFail = true;
        this.hasFailedBefore = false;
    }

    public static XHDownloadOkHttpProxy createOkHttp() {
        return new XHDownloadOkHttpProxy(new DownloadOkHttp());
    }

    public static XHDownloadOkHttpProxy createOkHttp(DownloadOkHttp downloadOkHttp) {
        return new XHDownloadOkHttpProxy(downloadOkHttp);
    }

    protected abstract String getSavePath();

    public boolean isHasFailedBefore() {
        return this.hasFailedBefore;
    }

    public T setDownloadAgainWhenFail(boolean z) {
        this.downloadAgainWhenFail = z;
        return this;
    }

    public abstract int stop(XHTask xHTask);
}
