package com.xh.xhcore.common.http.strategy.xh.callback;

import com.xh.xhcore.common.http.archi.IFailedCallback;
import com.xh.xhcore.common.http.strategy.okhttp.ignore.IIgnoreCallback;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003R(\u0010\u0004\u001a\u0010\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/callback/BaseProxyContainerCallback;", "Lcom/xh/xhcore/common/http/archi/IFailedCallback;", "Lcom/xh/xhcore/common/http/strategy/okhttp/ignore/IIgnoreCallback;", "()V", "xhBaseRequestProxy", "Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;", "getXhBaseRequestProxy", "()Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;", "setXhBaseRequestProxy", "(Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;)V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseProxyContainerCallback implements IFailedCallback, IIgnoreCallback {
    private XHBaseRequestProxy<?, ?, ?> xhBaseRequestProxy;

    public final XHBaseRequestProxy<?, ?, ?> getXhBaseRequestProxy() {
        return this.xhBaseRequestProxy;
    }

    @Override
    public void onIgnore(String str) {
        IIgnoreCallback.DefaultImpls.onIgnore(this, str);
    }

    public final void setXhBaseRequestProxy(XHBaseRequestProxy<?, ?, ?> xHBaseRequestProxy) {
        this.xhBaseRequestProxy = xHBaseRequestProxy;
    }
}
