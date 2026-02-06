package com.xh.shbmath;

import android.util.Log;

public class ShbMath {
    static {
        System.loadLibrary("shbmath");
    }

    public static int CreateGifFromEq(String str, String str2) {
        Log.v("SHB_MATH", "expression=" + str + " gifFilePath=" + str2);
        return JNICreateGifFromEq(str, str2);
    }

    private static native int JNICreateGifFromEq(String str, String str2);
}
