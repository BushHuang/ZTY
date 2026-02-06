package com.xh.xhcore.common.http.strategy.xh.upload;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import kotlin.Metadata;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\"\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J \u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH&¨\u0006\u0014"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/UploadOssCallback;", "", "onFailed", "", "code", "", "clientException", "Lcom/alibaba/sdk/android/oss/ClientException;", "serviceException", "Lcom/alibaba/sdk/android/oss/ServiceException;", "onProgress", "current", "", "total", "speed", "", "onSuccess", "reqId", "path", "fileUrl", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface UploadOssCallback {
    void onFailed(int code, ClientException clientException, ServiceException serviceException);

    void onProgress(double current, double total, String speed);

    void onSuccess(int reqId, String path, String fileUrl);
}
