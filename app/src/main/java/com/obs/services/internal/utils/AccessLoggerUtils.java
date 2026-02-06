package com.obs.services.internal.utils;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessLoggerUtils {
    private static final int INDEX = 5;
    private static final ILogger accessLog = LoggerBuilder.getLogger("com.obs.log.AccessLogger");
    private static final ThreadLocal<StringBuilder> threadLocalLog = new ThreadLocal<>();
    private static final ThreadLocal<SoftReference<SimpleDateFormat>> simpleDateFormateHolder = new ThreadLocal<>();
    public static volatile boolean ACCESSLOG_ENABLED = true;

    public static void appendLog(Object obj, String str) {
        if (ACCESSLOG_ENABLED) {
            Boolean boolValueOf = false;
            if ("info".equalsIgnoreCase(str)) {
                boolValueOf = Boolean.valueOf(accessLog.isInfoEnabled());
            } else if ("debug".equalsIgnoreCase(str)) {
                boolValueOf = Boolean.valueOf(accessLog.isDebugEnabled());
            } else if ("warn".equalsIgnoreCase(str)) {
                boolValueOf = Boolean.valueOf(accessLog.isWarnEnabled());
            } else if ("error".equalsIgnoreCase(str)) {
                boolValueOf = Boolean.valueOf(accessLog.isErrorEnabled());
            } else if ("trace".equalsIgnoreCase(str)) {
                boolValueOf = Boolean.valueOf(accessLog.isTraceEnabled());
            }
            if (boolValueOf.booleanValue()) {
                getLog().append(getFormat().format(new Date()) + "|" + getLogPrefix() + obj.toString() + "\n");
            }
        }
    }

    public static SimpleDateFormat getFormat() {
        SimpleDateFormat simpleDateFormat;
        SoftReference<SimpleDateFormat> softReference = simpleDateFormateHolder.get();
        if (softReference != null && (simpleDateFormat = softReference.get()) != null) {
            return simpleDateFormat;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        simpleDateFormateHolder.set(new SoftReference<>(simpleDateFormat2));
        return simpleDateFormat2;
    }

    private static StringBuilder getLog() {
        StringBuilder sb = threadLocalLog.get();
        if (sb != null) {
            return sb;
        }
        StringBuilder sb2 = new StringBuilder();
        threadLocalLog.set(sb2);
        return sb2;
    }

    private static String getLogPrefix() {
        if (!ACCESSLOG_ENABLED) {
            return "";
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace.length > 5 ? stackTrace[5] : stackTrace[stackTrace.length - 1];
        return stackTraceElement.getClassName() + "|" + stackTraceElement.getMethodName() + "|" + stackTraceElement.getLineNumber() + "|";
    }

    public static void printLog() {
        if (ACCESSLOG_ENABLED) {
            String string = getLog().toString();
            if (ServiceUtils.isValid(string)) {
                accessLog.accessRecord(string);
            }
            threadLocalLog.remove();
        }
    }
}
