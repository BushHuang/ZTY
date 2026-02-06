package com.xuehai.launcher.common.widget;

import android.content.Context;
import com.xh.view.ToastUtil;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.plugins.ThreadPlugins;

public class CustomToast {
    public static void postShowToast(final int i) {
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() {
                CustomToast.showToast(i);
            }
        }, 0L);
    }

    public static void postShowToast(final String str) {
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() {
                CustomToast.showToast(str);
            }
        }, 0L);
    }

    public static void showToast(int i) {
        showToast(BaseApplication.getInstance().getString(i));
    }

    public static void showToast(String str) {
        Context mTopActivity = BaseApplication.getInstance().getMTopActivity();
        if (mTopActivity == null) {
            mTopActivity = BaseApplication.getInstance();
        }
        ToastUtil.showToast(mTopActivity, str, 1);
    }
}
