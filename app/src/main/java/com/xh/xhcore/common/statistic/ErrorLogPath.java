package com.xh.xhcore.common.statistic;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/statistic/ErrorLogPath;", "", "path", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getPath", "()Ljava/lang/String;", "appUpload", "networkError", "crash", "apm", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum ErrorLogPath {
    appUpload(ConstStatistics.getAPP_ERROR_LOG_PATH()),
    networkError(ConstStatistics.getNETWORK_ERROR_LOG_PATH()),
    crash(ConstStatistics.getCRASH_LOG_PATH()),
    apm(ConstStatistics.getAPM_LOG_PATH());

    private final String path;

    ErrorLogPath(String str) {
        this.path = str;
    }

    public final String getPath() {
        return this.path;
    }
}
