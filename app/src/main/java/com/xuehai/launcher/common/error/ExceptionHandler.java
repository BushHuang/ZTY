package com.xuehai.launcher.common.error;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\n\u001a\u00020\tJ\u001a\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0006j\b\u0012\u0004\u0012\u00020\u0003`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xuehai/launcher/common/error/ExceptionHandler;", "", "handler", "Lcom/xuehai/launcher/common/error/ThrowableHandler;", "(Lcom/xuehai/launcher/common/error/ThrowableHandler;)V", "handlers", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "addHandler", "", "clear", "handleException", "tag", "", "tr", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExceptionHandler {
    private final ArrayList<ThrowableHandler> handlers;

    public ExceptionHandler(ThrowableHandler throwableHandler) {
        Intrinsics.checkNotNullParameter(throwableHandler, "handler");
        this.handlers = new ArrayList<>();
        addHandler(throwableHandler);
    }

    public static void handleException$default(ExceptionHandler exceptionHandler, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        exceptionHandler.handleException(str, th);
    }

    public final void addHandler(ThrowableHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        synchronized (this.handlers) {
            this.handlers.add(handler);
        }
    }

    public final void clear() {
        synchronized (this.handlers) {
            this.handlers.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void handleException(String tag, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        synchronized (this.handlers) {
            Iterator<T> it = this.handlers.iterator();
            while (it.hasNext()) {
                ((ThrowableHandler) it.next()).handleException(tag, tr);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
