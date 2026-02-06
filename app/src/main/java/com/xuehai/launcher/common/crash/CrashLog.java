package com.xuehai.launcher.common.crash;

import android.util.Log;

public class CrashLog {
    private static final String TAG = "CrashHandler";

    public static void e(String str) {
        Log.e("CrashHandler", str);
    }

    public static void i(String str) {
        Log.i("CrashHandler", str);
    }
}
