package com.xuehai.launcher.common.ext;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003¨\u0006\u0006"}, d2 = {"printError", "", "error", "", "printInfo", "message", "common_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class LogExtKt {
    public static final int printError(String str) {
        Intrinsics.checkNotNullParameter(str, "error");
        return Log.e("Debug[MDM]", str);
    }

    public static final int printInfo(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        return Log.i("Debug[MDM]", str);
    }
}
