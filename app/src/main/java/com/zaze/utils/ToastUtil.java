package com.zaze.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void toast(Context context, String str) {
        toast(context, str, 0);
    }

    public static void toast(Context context, String str, int i) {
        if (context != null) {
            Toast.makeText(context, str, i).show();
        }
    }
}
