package com.xh;

public class nativeUtils {
    static {
        System.loadLibrary("xh_common");
    }

    public static native void JNIZipFiles(String str, String str2);
}
