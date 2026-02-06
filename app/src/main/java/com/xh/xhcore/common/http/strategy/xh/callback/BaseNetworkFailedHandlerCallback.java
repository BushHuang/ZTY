package com.xh.xhcore.common.http.strategy.xh.callback;

import android.text.TextUtils;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.callback.uploader.BaseNetworkErrorUploader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J(\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0010\u001a\u00020\u0011R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/callback/BaseNetworkFailedHandlerCallback;", "Lcom/xh/xhcore/common/http/strategy/xh/callback/BaseProxyContainerCallback;", "()V", "haveFailedBefore", "", "getHaveFailedBefore", "()Z", "setHaveFailedBefore", "(Z)V", "failedInner", "", "code", "", "msg", "", "failedUploadInner", "baseNetworkErrorUploader", "Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/BaseNetworkErrorUploader;", "description", "throwable", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseNetworkFailedHandlerCallback extends BaseProxyContainerCallback {
    private boolean haveFailedBefore;

    public final void failedInner(int code, String msg) {
        LogUtils.Companion companion = LogUtils.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("xhNetworkEnd failed code = ");
        sb.append(code);
        sb.append(" msg = ");
        sb.append(msg == null ? "null" : msg);
        LogUtils.Companion.d$default(companion, "xhNetworkExecuteStateTag", sb.toString(), null, 4, null);
        if (this.haveFailedBefore) {
            return;
        }
        this.haveFailedBefore = true;
        failed(code, msg);
    }

    public final void failedUploadInner(int code, BaseNetworkErrorUploader baseNetworkErrorUploader) {
        Intrinsics.checkNotNullParameter(baseNetworkErrorUploader, "baseNetworkErrorUploader");
        failedUploadInner(code, "", new Throwable(), baseNetworkErrorUploader);
    }

    public final void failedUploadInner(int code, String description, Throwable throwable, BaseNetworkErrorUploader baseNetworkErrorUploader) {
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(baseNetworkErrorUploader, "baseNetworkErrorUploader");
        baseNetworkErrorUploader.setCode(code);
        if (!TextUtils.isEmpty(description)) {
            baseNetworkErrorUploader.setDescription(description);
        }
        baseNetworkErrorUploader.setLastFail(true);
        baseNetworkErrorUploader.uploadNetworkError(throwable);
        failedInner(code, baseNetworkErrorUploader.getTheDescription(code));
    }

    public final boolean getHaveFailedBefore() {
        return this.haveFailedBefore;
    }

    public final void setHaveFailedBefore(boolean z) {
        this.haveFailedBefore = z;
    }
}
