package com.xuehai.launcher.common.http;

import com.xuehai.launcher.common.constants.error.ErrorCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J \u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001a\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\bH\u0016¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/common/http/DownloadCallback;", "", "()V", "onFailure", "", "errorCode", "", "errorMessage", "", "onProgress", "fileTotalSize", "", "fileDownSize", "speed", "onStart", "total", "onSuccess", "message", "savePath", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class DownloadCallback {
    public static void onFailure$default(DownloadCallback downloadCallback, int i, String str, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onFailure");
        }
        if ((i2 & 1) != 0) {
            i = ErrorCode.ERROR_DEFAULT.getCode();
        }
        if ((i2 & 2) != 0) {
            str = ErrorCode.ERROR_DEFAULT.getMsg();
        }
        downloadCallback.onFailure(i, str);
    }

    public void onFailure(int errorCode, String errorMessage) {
    }

    public void onProgress(double fileTotalSize, double fileDownSize, double speed) {
    }

    public void onStart(double total) {
    }

    public void onSuccess(String message, String savePath) {
        Intrinsics.checkNotNullParameter(savePath, "savePath");
    }
}
