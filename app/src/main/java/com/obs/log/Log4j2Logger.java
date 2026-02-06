package com.obs.log;

import com.obs.log.LoggerBuilder;
import com.obs.services.internal.utils.AccessLoggerUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Log4j2Logger implements ILogger {
    private final Object logger;
    private volatile int isInfoE = -1;
    private volatile int isDebugE = -1;
    private volatile int isErrorE = -1;
    private volatile int isWarnE = -1;
    private volatile int isTraceE = -1;

    private static class LoggerMethodHolder {
        private static Method debug;
        private static Method error;
        private static Method info;
        private static Method isDebug;
        private static Method isError;
        private static Method isInfo;
        private static Method isTrace;
        private static Method isWarn;
        private static Method trace;
        private static Method warn;

        static {
            try {
                if (LoggerBuilder.GetLoggerHolder.loggerClass != null) {
                    info = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("info", Object.class, Throwable.class);
                    warn = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("warn", Object.class, Throwable.class);
                    error = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("error", Object.class, Throwable.class);
                    debug = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("debug", Object.class, Throwable.class);
                    trace = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("trace", Object.class, Throwable.class);
                    isInfo = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("isInfoEnabled", new Class[0]);
                    isDebug = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("isDebugEnabled", new Class[0]);
                    isError = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("isErrorEnabled", new Class[0]);
                    isWarn = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("isWarnEnabled", new Class[0]);
                    isTrace = LoggerBuilder.GetLoggerHolder.loggerClass.getMethod("isTraceEnabled", new Class[0]);
                }
            } catch (Exception unused) {
            }
        }

        private LoggerMethodHolder() {
        }
    }

    Log4j2Logger(Object obj) {
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
                this.isDebugE = (this.logger == null || LoggerMethodHolder.isDebug == null || !((Boolean) LoggerMethodHolder.isDebug.invoke(this.logger, new Object[0])).booleanValue()) ? 0 : 1;
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
                this.isErrorE = (this.logger == null || LoggerMethodHolder.isError == null || !((Boolean) LoggerMethodHolder.isError.invoke(this.logger, new Object[0])).booleanValue()) ? 0 : 1;
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
                this.isInfoE = (this.logger == null || LoggerMethodHolder.isInfo == null || !((Boolean) LoggerMethodHolder.isInfo.invoke(this.logger, new Object[0])).booleanValue()) ? 0 : 1;
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
                this.isTraceE = (this.logger == null || LoggerMethodHolder.isTrace == null || !((Boolean) LoggerMethodHolder.isTrace.invoke(this.logger, new Object[0])).booleanValue()) ? 0 : 1;
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
                this.isWarnE = (this.logger == null || LoggerMethodHolder.isWarn == null || !((Boolean) LoggerMethodHolder.isWarn.invoke(this.logger, new Object[0])).booleanValue()) ? 0 : 1;
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
