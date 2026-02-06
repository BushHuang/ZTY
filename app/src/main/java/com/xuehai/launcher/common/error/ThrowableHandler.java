package com.xuehai.launcher.common.error;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/error/ThrowableHandler;", "", "handleException", "", "tag", "", "tr", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ThrowableHandler {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void handleException$default(ThrowableHandler throwableHandler, String str, Throwable th, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleException");
            }
            if ((i & 2) != 0) {
                th = null;
            }
            throwableHandler.handleException(str, th);
        }
    }

    void handleException(String tag, Throwable tr);
}
