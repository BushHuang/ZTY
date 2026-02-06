package com.xh.xhcore.common.http.strategy.xh.download;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface XHDownloadType {
    public static final int CACHE_ONLY = 1;
    public static final int DOWNLOAD_ONLY = 0;
}
