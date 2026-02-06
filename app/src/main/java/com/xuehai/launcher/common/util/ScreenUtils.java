package com.xuehai.launcher.common.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0004¨\u0006\u0010"}, d2 = {"Lcom/xuehai/launcher/common/util/ScreenUtils;", "", "()V", "addLayoutFullScreen", "", "window", "Landroid/view/Window;", "isAllFull", "", "getLayoutFullScreenFlag", "getScreenWidth", "context", "Landroid/content/Context;", "isInLeft", "mContext", "xPos", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ScreenUtils {
    public static final ScreenUtils INSTANCE = new ScreenUtils();

    private ScreenUtils() {
    }

    public static int addLayoutFullScreen$default(ScreenUtils screenUtils, Window window, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return screenUtils.addLayoutFullScreen(window, z);
    }

    public static int getLayoutFullScreenFlag$default(ScreenUtils screenUtils, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return screenUtils.getLayoutFullScreenFlag(z);
    }

    public final int addLayoutFullScreen(Window window, boolean isAllFull) {
        Intrinsics.checkNotNullParameter(window, "window");
        int layoutFullScreenFlag = getLayoutFullScreenFlag(isAllFull) | window.getDecorView().getSystemUiVisibility();
        window.getDecorView().setSystemUiVisibility(layoutFullScreenFlag);
        return layoutFullScreenFlag;
    }

    public final int getLayoutFullScreenFlag(boolean isAllFull) {
        return isAllFull ? 5894 : 5890;
    }

    public final int getScreenWidth(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public final boolean isInLeft(Context mContext, int xPos) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        return xPos < getScreenWidth(mContext) / 2;
    }
}
