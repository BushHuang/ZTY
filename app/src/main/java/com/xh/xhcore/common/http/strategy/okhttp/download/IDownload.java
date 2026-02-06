package com.xh.xhcore.common.http.strategy.okhttp.download;

import com.xh.xhcore.common.http.strategy.IRequest;

public interface IDownload<T> extends IRequest<T> {
    T setSavePath(String str);
}
