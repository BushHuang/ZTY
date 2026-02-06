package com.xh.xhcore.common.http.strategy.xh.security;

import com.xh.xhcore.common.http.XHRequestCallBack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0018\u0010\t\u001a\u00020\u00042\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u0014J\u0016\u0010\f\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH$J\b\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/XHMultiDownloadCallback;", "Lcom/xh/xhcore/common/http/XHRequestCallBack$XHDownloadCallBack;", "()V", "failed", "", "code", "", "msg", "", "failedWithAllItemsFailed", "statusCodeList", "", "failedWithSuccessItem", "hasSuccessItem", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class XHMultiDownloadCallback extends XHRequestCallBack.XHDownloadCallBack {
    private final boolean hasSuccessItem() {
        List<Integer> list = this.multiDownloadStatusCodeList;
        Intrinsics.checkNotNull(list);
        for (Integer num : list) {
            Intrinsics.checkNotNullExpressionValue(num, "statusCode");
            if (isSuccess(num.intValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final void failed(int code, String msg) {
        if (code != 107002004 || !hasSuccessItem()) {
            failedWithAllItemsFailed(this.multiDownloadStatusCodeList);
            return;
        }
        List<Integer> list = this.multiDownloadStatusCodeList;
        Intrinsics.checkNotNull(list);
        Intrinsics.checkNotNullExpressionValue(list, "multiDownloadStatusCodeList!!");
        failedWithSuccessItem(list);
    }

    protected void failedWithAllItemsFailed(List<Integer> statusCodeList) {
    }

    protected abstract void failedWithSuccessItem(List<Integer> statusCodeList);
}
