package com.xh.view;

import android.content.Context;

@Deprecated
public class TipToast {
    public static void showToast(Context context, String str, int i) {
        ToastUtil.showToast(context, str, i);
    }

    public static boolean showToast(Context context, int i) {
        return ToastUtil.showToast(context, i);
    }
}
