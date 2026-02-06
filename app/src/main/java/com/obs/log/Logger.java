package com.obs.log;

import com.obs.log.LoggerBuilder;

public class Logger implements ILogger {
    private final ILogger delegate;

    Logger(Object obj) {
        Class<?> cls = LoggerBuilder.GetLoggerHolder.loggerClass;
        if (obj instanceof java.util.logging.Logger) {
            this.delegate = new BasicLogger((java.util.logging.Logger) obj);
        } else if (cls == null || !cls.getName().equals("org.apache.log4j.Logger")) {
            this.delegate = new Log4j2Logger(obj);
        } else {
            this.delegate = new Log4jLogger(obj);
        }
    }

    @Override
    public void accessRecord(Object obj) {
        this.delegate.accessRecord(obj);
    }

    @Override
    public void debug(CharSequence charSequence) {
        this.delegate.debug(charSequence);
    }

    @Override
    public void debug(Object obj) {
        this.delegate.debug(obj);
    }

    @Override
    public void debug(Object obj, Throwable th) {
        this.delegate.debug(obj, th);
    }

    @Override
    public void error(CharSequence charSequence) {
        this.delegate.error(charSequence);
    }

    @Override
    public void error(Object obj) {
        this.delegate.error(obj);
    }

    @Override
    public void error(Object obj, Throwable th) {
        this.delegate.error(obj, th);
    }

    @Override
    public void info(CharSequence charSequence) {
        this.delegate.info(charSequence);
    }

    @Override
    public void info(Object obj) {
        this.delegate.info(obj);
    }

    @Override
    public void info(Object obj, Throwable th) {
        this.delegate.info(obj, th);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.delegate.isDebugEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return this.delegate.isErrorEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return this.delegate.isInfoEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return this.delegate.isTraceEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return this.delegate.isWarnEnabled();
    }

    @Override
    public void trace(CharSequence charSequence) {
        this.delegate.trace(charSequence);
    }

    @Override
    public void trace(Object obj) {
        this.delegate.trace(obj);
    }

    @Override
    public void trace(Object obj, Throwable th) {
        this.delegate.trace(obj, th);
    }

    @Override
    public void warn(CharSequence charSequence) {
        this.delegate.warn(charSequence);
    }

    @Override
    public void warn(Object obj) {
        this.delegate.warn(obj);
    }

    @Override
    public void warn(Object obj, Throwable th) {
        this.delegate.warn(obj, th);
    }
}
