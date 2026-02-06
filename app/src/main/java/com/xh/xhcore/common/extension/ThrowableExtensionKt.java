package com.xh.xhcore.common.extension;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"getSpecifiedCause", "T", "", "tClass", "Ljava/lang/Class;", "(Ljava/lang/Throwable;Ljava/lang/Class;)Ljava/lang/Throwable;", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ThrowableExtensionKt {
    public static final <T extends Throwable> T getSpecifiedCause(Throwable th, Class<T> cls) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        Intrinsics.checkNotNullParameter(cls, "tClass");
        for (T t = (T) th.getCause(); t != null; t = (T) t.getCause()) {
            if (t.getClass().equals(cls)) {
                return t;
            }
        }
        return null;
    }
}
