package com.xh.xhcore.common.util;

import android.app.Activity;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xuehai.platform.AppLockManager;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/util/AppLockUtil;", "", "()V", "isNeedUnlock", "", "whenActivityOnResume", "", "activity", "Landroid/app/Activity;", "whenAppOnTop", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppLockUtil {
    public static final AppLockUtil INSTANCE = new AppLockUtil();
    private static boolean isNeedUnlock;

    private AppLockUtil() {
    }

    @JvmStatic
    public static final void whenActivityOnResume(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (isNeedUnlock) {
            AppLockManager.showAppUnLockView(activity);
        }
    }

    @JvmStatic
    public static final void whenAppOnTop() {
        isNeedUnlock = AppLockManager.isAppNeedUnlock(XhBaseApplication.getXhBaseApplication());
    }
}
