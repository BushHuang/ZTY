package com.xh.xhcore.common.util;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xh.xhcore.common.base.XhBaseApplication;

public class XHScreenUtil {
    private XHScreenUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) XhBaseApplication.mContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) XhBaseApplication.mContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
