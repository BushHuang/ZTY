package com.obs.log;

public interface ILogger {
    void accessRecord(Object obj);

    void debug(CharSequence charSequence);

    void debug(Object obj);

    void debug(Object obj, Throwable th);

    void error(CharSequence charSequence);

    void error(Object obj);

    void error(Object obj, Throwable th);

    void info(CharSequence charSequence);

    void info(Object obj);

    void info(Object obj, Throwable th);

    boolean isDebugEnabled();

    boolean isErrorEnabled();

    boolean isInfoEnabled();

    boolean isTraceEnabled();

    boolean isWarnEnabled();

    void trace(CharSequence charSequence);

    void trace(Object obj);

    void trace(Object obj, Throwable th);

    void warn(CharSequence charSequence);

    void warn(Object obj);

    void warn(Object obj, Throwable th);
}
