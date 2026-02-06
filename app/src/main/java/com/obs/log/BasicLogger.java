package com.obs.log;

import com.obs.services.LogConfigurator;
import com.obs.services.internal.utils.AccessLoggerUtils;

public class BasicLogger implements ILogger {
    private final java.util.logging.Logger logger;

    BasicLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void accessRecord(Object obj) {
        if (obj != null) {
            this.logger.log(LogConfigurator.INFO, obj.toString());
        }
    }

    @Override
    public void debug(CharSequence charSequence) {
        if (charSequence != null) {
            this.logger.log(LogConfigurator.DEBUG, charSequence.toString());
            AccessLoggerUtils.appendLog(charSequence, "debug");
        }
    }

    @Override
    public void debug(Object obj) {
        if (obj != null) {
            this.logger.log(LogConfigurator.DEBUG, obj.toString());
            AccessLoggerUtils.appendLog(obj, "debug");
        }
    }

    @Override
    public void debug(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.log(LogConfigurator.DEBUG, obj.toString(), th);
            AccessLoggerUtils.appendLog(obj, "debug");
        }
    }

    @Override
    public void error(CharSequence charSequence) {
        if (charSequence != null) {
            this.logger.severe(charSequence.toString());
            AccessLoggerUtils.appendLog(charSequence, "error");
        }
    }

    @Override
    public void error(Object obj) {
        if (obj != null) {
            this.logger.severe(obj.toString());
            AccessLoggerUtils.appendLog(obj, "error");
        }
    }

    @Override
    public void error(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.log(LogConfigurator.ERROR, obj.toString(), th);
            AccessLoggerUtils.appendLog(obj, "error");
        }
    }

    @Override
    public void info(CharSequence charSequence) {
        if (charSequence != null) {
            this.logger.info(charSequence.toString());
            AccessLoggerUtils.appendLog(charSequence, "info");
        }
    }

    @Override
    public void info(Object obj) {
        if (obj != null) {
            this.logger.info(obj.toString());
            AccessLoggerUtils.appendLog(obj, "info");
        }
    }

    @Override
    public void info(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.log(LogConfigurator.INFO, obj.toString(), th);
            AccessLoggerUtils.appendLog(obj, "info");
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isLoggable(LogConfigurator.DEBUG);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.logger.isLoggable(LogConfigurator.ERROR);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.logger.isLoggable(LogConfigurator.INFO);
    }

    @Override
    public boolean isTraceEnabled() {
        return this.logger.isLoggable(LogConfigurator.TRACE);
    }

    @Override
    public boolean isWarnEnabled() {
        return this.logger.isLoggable(LogConfigurator.WARN);
    }

    @Override
    public void trace(CharSequence charSequence) {
        if (charSequence != null) {
            this.logger.log(LogConfigurator.TRACE, charSequence.toString());
            AccessLoggerUtils.appendLog(charSequence, "trace");
        }
    }

    @Override
    public void trace(Object obj) {
        if (obj != null) {
            this.logger.log(LogConfigurator.TRACE, obj.toString());
            AccessLoggerUtils.appendLog(obj, "trace");
        }
    }

    @Override
    public void trace(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.log(LogConfigurator.TRACE, obj.toString(), th);
            AccessLoggerUtils.appendLog(obj, "trace");
        }
    }

    @Override
    public void warn(CharSequence charSequence) {
        if (charSequence != null) {
            this.logger.warning(charSequence.toString());
            AccessLoggerUtils.appendLog(charSequence, "warn");
        }
    }

    @Override
    public void warn(Object obj) {
        if (obj != null) {
            this.logger.warning(obj.toString());
            AccessLoggerUtils.appendLog(obj, "warn");
        }
    }

    @Override
    public void warn(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.log(LogConfigurator.WARN, obj.toString(), th);
            AccessLoggerUtils.appendLog(obj, "warn");
        }
    }
}
