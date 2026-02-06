package com.obs.log;

import java.lang.reflect.Method;

public class LoggerBuilder {

    static class GetLoggerHolder {
        static Method getLoggerClass;
        static Class<?> logManagerClass;
        static Class<?> loggerClass;

        static {
            try {
                try {
                    try {
                        logManagerClass = Class.forName("org.apache.logging.log4j.LogManager");
                        loggerClass = Class.forName("org.apache.logging.log4j.Logger");
                        getLoggerClass = logManagerClass.getMethod("getLogger", String.class);
                    } catch (Exception unused) {
                        Class<?> cls = Class.forName("org.apache.log4j.Logger");
                        loggerClass = cls;
                        getLoggerClass = cls.getMethod("getLogger", String.class);
                    }
                } catch (Exception unused2) {
                    Class<?> cls2 = Class.forName("java.util.logging.Logger");
                    loggerClass = cls2;
                    getLoggerClass = cls2.getMethod("getLogger", String.class);
                }
            } catch (Exception unused3) {
            }
        }

        GetLoggerHolder() {
        }
    }

    public static ILogger getLogger(Class<?> cls) {
        return getLogger(cls.getName());
    }

    public static ILogger getLogger(String str) {
        if (GetLoggerHolder.getLoggerClass != null) {
            try {
                return new Logger(GetLoggerHolder.getLoggerClass.invoke(null, str));
            } catch (Exception unused) {
            }
        }
        return new Logger(null);
    }
}
