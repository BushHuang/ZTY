package com.huawei.secure.android.common.ssl.util;

import android.content.Context;

public class c {

    private static Context f424a;

    public static Context a() {
        return f424a;
    }

    public static void a(Context context) {
        if (context == null || f424a != null) {
            return;
        }
        f424a = context.getApplicationContext();
    }
}
