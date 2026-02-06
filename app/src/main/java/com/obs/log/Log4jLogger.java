package com.obs.log;

import com.obs.log.LoggerBuilder;
import com.obs.services.internal.utils.AccessLoggerUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Log4jLogger implements ILogger {
    private final Object logger;
    private volatile int isInfoE = -1;
    private volatile int isDebugE = -1;
    private volatile int isErrorE = -1;
    private volatile int isWarnE = -1;
    private volatile int isTraceE = -1;

    private static class LoggerMethodHolder {
        private static Method debug;
        private static Object debugLevel;
        private static Method error;
        private static Object errorLevel;
        private static Method info;
        private static Object infoLevel;
        private static Method isEnabledFor;
        private static Class<?> level;
        private static Class<?> priority;
        private static Method trace;
        private static Object traceLevel;
        private static Method warn;
        private static Object warnLevel;

        static {
            try {
                if (LoggerBuilder.GetLoggerHolder.loggerClass != null) {
                    info = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("info", Object.class, Throwable.class);
                    warn = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("warn", Object.class, Throwable.class);
                    error = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("error", Object.class, Throwable.class);
                    debug = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("debug", Object.class, Throwable.class);
                    trace = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("trace", Object.class, Throwable.class);
                    priority = Class.forName("org.apache.log4j.Priority");
                    isEnabledFor = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("isEnabledFor", priority);
                    Class<?> cls = Class.forName("org.apache.log4j.Level");
                    level = cls;
                    infoLevel = cls.getField("INFO").get(level);
                    debugLevel = level.getField("DEBUG").get(level);
                    errorLevel = level.getField("ERROR").get(level);
                    warnLevel = level.getField("WARN").get(level);
                    traceLevel = level.getField("TRACE").get(level);
                }
            } catch (Exception unused) {
            }
        }

        private LoggerMethodHolder() {
        }
    }

    Log4jLogger(Object obj) {
        this.logger = obj;
    }

    @Override
    public void accessRecord(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.info == null) {
            return;
        }
        try {
            LoggerMethodHolder.info.invoke(this.logger, obj, null);
        } catch (Exception unused) {
        }
    }

    @Override
    public void debug(CharSequence charSequence) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.debug == null) {
            return;
        }
        try {
            LoggerMethodHolder.debug.invoke(this.logger, charSequence, null);
            AccessLoggerUtils.appendLog(charSequence, "debug");
        } catch (Exception unused) {
        }
    }

    @Override
    public void debug(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.debug == null) {
            return;
        }
        try {
            LoggerMethodHolder.debug.invoke(this.logger, obj, null);
            AccessLoggerUtils.appendLog(obj, "debug");
        } catch (Exception unused) {
        }
    }

    @Override
    public void debug(Object obj, Throwable th) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.debug == null) {
            return;
        }
        try {
            LoggerMethodHolder.debug.invoke(this.logger, obj, th);
            AccessLoggerUtils.appendLog(obj, "debug");
        } catch (Exception unused) {
        }
    }

    @Override
    public void error(CharSequence charSequence) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.error == null) {
            return;
        }
        try {
            LoggerMethodHolder.error.invoke(this.logger, charSequence, null);
            AccessLoggerUtils.appendLog(charSequence, "error");
        } catch (Exception unused) {
        }
    }

    @Override
    public void error(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.error == null) {
            return;
        }
        try {
            LoggerMethodHolder.error.invoke(this.logger, obj, null);
            AccessLoggerUtils.appendLog(obj, "error");
        } catch (Exception unused) {
        }
    }

    @Override
    public void error(Object obj, Throwable th) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.error == null) {
            return;
        }
        try {
            LoggerMethodHolder.error.invoke(this.logger, obj, th);
            AccessLoggerUtils.appendLog(obj, "error");
        } catch (Exception unused) {
        }
    }

    @Override
    public void info(CharSequence charSequence) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.info == null) {
            return;
        }
        try {
            LoggerMethodHolder.info.invoke(this.logger, charSequence, null);
            AccessLoggerUtils.appendLog(charSequence, "info");
        } catch (Exception unused) {
        }
    }

    @Override
    public void info(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.info == null) {
            return;
        }
        try {
            LoggerMethodHolder.info.invoke(this.logger, obj, null);
            AccessLoggerUtils.appendLog(obj, "info");
        } catch (Exception unused) {
        }
    }

    @Override
    public void info(Object obj, Throwable th) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.info == null) {
            return;
        }
        try {
            LoggerMethodHolder.info.invoke(this.logger, obj, th);
            AccessLoggerUtils.appendLog(obj, "info");
        } catch (Exception unused) {
        }
    }

    @Override
    public boolean isDebugEnabled() {
        if (this.isDebugE == -1) {
            try {
                this.isDebugE = (this.logger == null || LoggerMethodHolder.debugLevel == null || !((Boolean) LoggerMethodHolder.isEnabledFor.invoke(this.logger, LoggerMethodHolder.debugLevel)).booleanValue()) ? 0 : 1;
            } catch (Exception unused) {
                this.isDebugE = 0;
            }
        }
        return this.isDebugE == 1;
    }

    @Override
    public boolean isErrorEnabled() {
        if (this.isErrorE == -1) {
            try {
                this.isErrorE = (this.logger == null || LoggerMethodHolder.errorLevel == null || !((Boolean) LoggerMethodHolder.isEnabledFor.invoke(this.logger, LoggerMethodHolder.errorLevel)).booleanValue()) ? 0 : 1;
            } catch (Exception unused) {
                this.isErrorE = 0;
            }
        }
        return this.isErrorE == 1;
    }

    @Override
    public boolean isInfoEnabled() {
        if (this.isInfoE == -1) {
            try {
                this.isInfoE = (this.logger == null || LoggerMethodHolder.infoLevel == null || !((Boolean) LoggerMethodHolder.isEnabledFor.invoke(this.logger, LoggerMethodHolder.infoLevel)).booleanValue()) ? 0 : 1;
            } catch (Exception unused) {
                this.isInfoE = 0;
            }
        }
        return this.isInfoE == 1;
    }

    @Override
    public boolean isTraceEnabled() {
        if (this.isTraceE == -1) {
            try {
                this.isTraceE = (this.logger == null || LoggerMethodHolder.traceLevel == null || !((Boolean) LoggerMethodHolder.isEnabledFor.invoke(this.logger, LoggerMethodHolder.traceLevel)).booleanValue()) ? 0 : 1;
            } catch (Exception unused) {
                this.isTraceE = 0;
            }
        }
        return this.isTraceE == 1;
    }

    @Override
    public boolean isWarnEnabled() {
        if (this.isWarnE == -1) {
            try {
                this.isWarnE = (this.logger == null || LoggerMethodHolder.warnLevel == null || !((Boolean) LoggerMethodHolder.isEnabledFor.invoke(this.logger, LoggerMethodHolder.warnLevel)).booleanValue()) ? 0 : 1;
            } catch (Exception unused) {
                this.isWarnE = 0;
            }
        }
        return this.isWarnE == 1;
    }

    @Override
    public void trace(CharSequence charSequence) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.trace == null) {
            return;
        }
        try {
            LoggerMethodHolder.trace.invoke(this.logger, charSequence, null);
            AccessLoggerUtils.appendLog(charSequence, "trace");
        } catch (Exception unused) {
        }
    }

    @Override
    public void trace(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.trace == null) {
            return;
        }
        try {
            LoggerMethodHolder.trace.invoke(this.logger, obj, null);
            AccessLoggerUtils.appendLog(obj, "trace");
        } catch (Exception unused) {
        }
    }

    @Override
    public void trace(Object obj, Throwable th) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.trace == null) {
            return;
        }
        try {
            LoggerMethodHolder.trace.invoke(this.logger, obj, th);
            AccessLoggerUtils.appendLog(obj, "trace");
        } catch (Exception unused) {
        }
    }

    @Override
    public void warn(CharSequence charSequence) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.warn == null) {
            return;
        }
        try {
            LoggerMethodHolder.warn.invoke(this.logger, charSequence, null);
            AccessLoggerUtils.appendLog(charSequence, "warn");
        } catch (Exception unused) {
        }
    }

    @Override
    public void warn(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.warn == null) {
            return;
        }
        try {
            LoggerMethodHolder.warn.invoke(this.logger, obj, null);
            AccessLoggerUtils.appendLog(obj, "warn");
        } catch (Exception unused) {
        }
    }

    @Override
    public void warn(Object obj, Throwable th) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.logger == null || LoggerMethodHolder.warn == null) {
            return;
        }
        try {
            LoggerMethodHolder.warn.invoke(this.logger, obj, th);
            AccessLoggerUtils.appendLog(obj, "warn");
        } catch (Exception unused) {
        }
    }
}
