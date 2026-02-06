package com.xh.xhcore.common.util;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/util/OnRequestPermissionsCallback;", "", "()V", "isCurrRequest", "", "code", "", "onClearCacheData", "", "onGranted", "onNoTip", "onRefused", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class OnRequestPermissionsCallback {
    public boolean isCurrRequest(int code) {
        return code == 1001;
    }

    public void onClearCacheData() {
    }

    public void onGranted() {
    }

    public boolean onNoTip() {
        return true;
    }

    public void onRefused() {
    }
}
