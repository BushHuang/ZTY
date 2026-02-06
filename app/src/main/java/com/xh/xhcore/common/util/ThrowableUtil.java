package com.xh.xhcore.common.util;

import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/util/ThrowableUtil;", "", "()V", "mergeCauseThrowable", "", "causedThrowable", "", "causingThrowable", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThrowableUtil {
    public static final ThrowableUtil INSTANCE = new ThrowableUtil();

    private ThrowableUtil() {
    }

    @JvmStatic
    public static final String mergeCauseThrowable(Throwable causedThrowable, Throwable causingThrowable) {
        Intrinsics.checkNotNullParameter(causedThrowable, "causedThrowable");
        Intrinsics.checkNotNullParameter(causingThrowable, "causingThrowable");
        String str = LogUtils.INSTANCE.getStackTraceString(causedThrowable) + LogUtils.INSTANCE.getStackTraceString(causingThrowable);
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder(LogUtils.g…              .toString()");
        return str;
    }
}
