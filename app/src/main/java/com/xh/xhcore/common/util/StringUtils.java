package com.xh.xhcore.common.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/util/StringUtils;", "", "()V", "isNotSameChar", "", "text", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StringUtils {
    public static final StringUtils INSTANCE = new StringUtils();

    private StringUtils() {
    }

    public final boolean isNotSameChar(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return StringsKt.toSet(text).size() != 1;
    }
}
