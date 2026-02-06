package com.zaze.utils.log;

import android.util.Log;
import com.zaze.utils.StackTraceHelper;
import com.zaze.utils.ZStringUtil;

public class ZLog {
    private static boolean D = true;
    private static boolean E = true;
    private static boolean I = true;
    private static boolean V = true;
    private static boolean W = true;
    private static ZLogFace logFace = null;
    private static boolean needStack = false;

    static {
        registerLogCaller(ZLog.class.getName());
        registerLogCaller(Log.class.getName());
    }

    public static void closeAllLog() {
        E = false;
        W = false;
        I = false;
        D = false;
        V = false;
    }

    public static void d(String str, String str2, Object... objArr) {
        if (D) {
            ZLogFace zLogFace = logFace;
            if (zLogFace != null) {
                zLogFace.d(str, getStackTrace(str2, objArr));
            } else {
                Log.d(str, getStackTrace(str2, objArr));
            }
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (E) {
            ZLogFace zLogFace = logFace;
            if (zLogFace != null) {
                zLogFace.e(str, getStackTrace(str2, objArr));
            } else {
                Log.e(str, getStackTrace(str2, objArr));
            }
        }
    }

    private static String getStackTrace(String str, Object... objArr) {
        if (!needStack) {
            return ZStringUtil.format(str, objArr);
        }
        return ZStringUtil.format("[" + getTag(StackTraceHelper.callerStackTraceElement()) + "] : " + str, objArr);
    }

    private static String getTag(StackTraceElement stackTraceElement) {
        return ZStringUtil.format("(%s:%d)[%s]", stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()), stackTraceElement.getMethodName());
    }

    public static void i(String str, String str2, Object... objArr) {
        if (I) {
            ZLogFace zLogFace = logFace;
            if (zLogFace != null) {
                zLogFace.i(str, getStackTrace(str2, objArr));
            } else {
                Log.i(str, getStackTrace(str2, objArr));
            }
        }
    }

    public static void openAllLog() {
        E = true;
        W = true;
        I = true;
        D = true;
        V = true;
    }

    public static void registerLogCaller(String str) {
        StackTraceHelper.registerStackCaller(str);
    }

    public static void setLogFace(ZLogFace zLogFace) {
        logFace = zLogFace;
    }

    public static void setLogLevel(int i) {
        E = false;
        W = false;
        I = false;
        D = false;
        V = false;
        if (i >= 1) {
            E = true;
        }
        if (i >= 2) {
            W = true;
        }
        if (i >= 3) {
            I = true;
        }
        if (i >= 4) {
            D = true;
        }
        if (i >= 5) {
            V = true;
        }
    }

    public static void setNeedStack(boolean z) {
        needStack = z;
    }

    public static void v(String str, String str2, Object... objArr) {
        if (V) {
            ZLogFace zLogFace = logFace;
            if (zLogFace != null) {
                zLogFace.v(str, getStackTrace(str2, objArr));
            } else {
                Log.v(str, getStackTrace(str2, objArr));
            }
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (W) {
            ZLogFace zLogFace = logFace;
            if (zLogFace != null) {
                zLogFace.w(str, getStackTrace(str2, objArr));
            } else {
                Log.w(str, getStackTrace(str2, objArr));
            }
        }
    }
}
