package com.xuehai.system.common.appusage.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"ACTIVITY_PAUSED", "", "ACTIVITY_RESUMED", "CONTINUE_PREVIOUS_DAY", "END_OF_DAY", "SCREEN_INTERACTIVE", "SCREEN_NON_INTERACTIVE", "_IntervalState_shouldDebugPackage", "", "packageName", "", "mdm_std_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class IntervalStateKt {
    public static final int ACTIVITY_PAUSED = 2;
    public static final int ACTIVITY_RESUMED = 1;
    public static final int CONTINUE_PREVIOUS_DAY = 4;
    public static final int END_OF_DAY = 3;
    public static final int SCREEN_INTERACTIVE = 15;
    public static final int SCREEN_NON_INTERACTIVE = 16;

    public static final boolean _IntervalState_shouldDebugPackage(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        return false;
    }
}
