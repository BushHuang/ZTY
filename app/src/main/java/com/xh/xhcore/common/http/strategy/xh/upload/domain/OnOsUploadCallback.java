package com.xh.xhcore.common.http.strategy.xh.upload.domain;

import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsResponse;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.exception.OsException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\"\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/OnOsUploadCallback;", "", "onError", "", "e", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/exception/OsException;", "onProgress", "current", "", "total", "speed", "", "onSuccess", "response", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsResponse;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface OnOsUploadCallback {
    void onError(OsException e);

    void onProgress(double current, double total, String speed);

    void onSuccess(OsResponse response);
}
