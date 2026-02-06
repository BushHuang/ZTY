package com.xh.logutils;

import com.xh.xhcore.common.http.strategy.LogUtils;

public class Log {
    static int stackLevel = 4;
    private static LogLevel logLevel = LogLevel.LOG_LEVEL_VERBOSE;
    public static String XH_COMMON_TAG = "XH_COMMON";
    public static String XH_NETWORK_CLIENT_TAG = "XH_NETWORK_CLIENT";
    public static String XH_STORAGE_TAG = "XH_STORAGE";

    private Log() {
    }

    public static void addStackLevel(int i) {
        e("XH_COMMON", "addStackLevel=" + i);
        stackLevel = i + 4;
    }

    public static void d(String str, String str2) {
        LogUtils.d(str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        LogUtils.d(str, str2, th);
    }

    public static void e(String str, String str2) {
        LogUtils.e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        LogUtils.e(str, str2, th);
    }

    public static void i(String str, String str2) {
        LogUtils.i(str, str2);
    }

    public static void i(String str, String str2, Throwable th) {
        LogUtils.i(str, str2, th);
    }

    public static void setLogLevel(LogLevel logLevel2) {
        e("XH_COMMON", "logLevel=" + logLevel2);
        LogUtils.setLogLevel(logLevel2);
    }

    public static void v(String str, String str2) {
        LogUtils.v(str, str2);
    }

    public static void v(String str, String str2, Throwable th) {
        LogUtils.v(str, str2, th);
    }

    public static void w(String str, String str2) {
        LogUtils.w(str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        LogUtils.w(str, str2, th);
    }
}
