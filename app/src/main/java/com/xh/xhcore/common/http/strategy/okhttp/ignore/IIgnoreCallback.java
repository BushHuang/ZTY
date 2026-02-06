package com.xh.xhcore.common.http.strategy.okhttp.ignore;

import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/ignore/IIgnoreCallback;", "", "onIgnore", "", "reason", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IIgnoreCallback {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onIgnore(IIgnoreCallback iIgnoreCallback, String str) {
            Intrinsics.checkNotNullParameter(iIgnoreCallback, "this");
            Intrinsics.checkNotNullParameter(str, "reason");
            LogUtils.INSTANCE.d(Intrinsics.stringPlus("[onIgnore] reason = ", str));
        }
    }

    void onIgnore(String reason);
}
