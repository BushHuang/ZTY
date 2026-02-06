package com.obs.services;

import com.obs.services.internal.utils.AccessLoggerUtils;
import com.obs.services.internal.utils.ServiceUtils;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogConfigurator {
    private static volatile boolean accessLogEnabled;
    private static volatile boolean logEnabled;
    private static String logFileDir;
    private static int logFileRolloverCount;
    private static int logFileSize;
    private static Level logLevel;
    public static final Level OFF = Level.parse("OFF");
    public static final Level TRACE = Level.parse("FINEST");
    public static final Level DEBUG = Level.parse("FINE");
    public static final Level INFO = Level.parse("INFO");
    public static final Level WARN = Level.parse("WARNING");
    public static final Level ERROR = Level.parse("SEVERE");
    private static final Logger logger = Logger.getLogger("com.obs");
    private static final Logger accessLogger = Logger.getLogger("com.obs.log.AccessLogger");

    static {
        disableLog();
        disableAccessLog();
        logFileSize = 31457280;
        logFileRolloverCount = 50;
        logEnabled = false;
        accessLogEnabled = false;
    }

    protected static synchronized void disableAccessLog() {
        logOff(accessLogger);
    }

    protected static synchronized void disableLog() {
        logOff(logger);
    }

    public static synchronized void enableAccessLog() {
        if (accessLogEnabled) {
            logOff(accessLogger);
        }
        logOn(accessLogger, "/OBS-SDK-access.log");
    }

    public static synchronized void enableLog() {
        if (logEnabled) {
            logOff(logger);
        }
        logOn(logger, "/OBS-SDK.log");
    }

    private static String getDefaultLogFileDir() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        try {
            Class<?> cls = Class.forName("android.os.Environment");
            Method method = cls.getMethod("getExternalStorageDirectory", new Class[0]);
            if (method != null) {
                return method.invoke(cls, new Object[0]).toString() + "/logs";
            }
        } catch (Exception unused) {
        }
        return System.getProperty("user.dir") + "/logs";
    }

    private static void logOff(Logger logger2) throws SecurityException {
        logger2.setLevel(OFF);
        Handler[] handlers = logger2.getHandlers();
        if (handlers != null) {
            for (Handler handler : handlers) {
                logger2.removeHandler(handler);
            }
        }
        if (logger2 == accessLogger) {
            accessLogEnabled = false;
        } else if (logger2 == logger) {
            logEnabled = false;
        }
    }

    private static void logOn(final Logger logger2, String str) throws IllegalAccessException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        logger2.setUseParentHandlers(false);
        Level level = logLevel;
        if (level == null) {
            level = WARN;
        }
        logger2.setLevel(level);
        if (logFileDir == null) {
            logFileDir = getDefaultLogFileDir();
        }
        try {
            File file = new File(logFileDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileHandler fileHandler = new FileHandler(logFileDir + str, logFileSize, logFileRolloverCount, true);
            fileHandler.setEncoding("UTF-8");
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord logRecord) {
                    String name = logRecord.getLevel().getName();
                    if ("SEVERE".equals(name)) {
                        name = "ERROR";
                    } else if ("FINE".equals(name)) {
                        name = "DEBUG";
                    } else if ("FINEST".equals(name)) {
                        name = "TRACE";
                    }
                    if (logger2 == LogConfigurator.accessLogger) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(Thread.currentThread().getName());
                        sb.append("\n");
                        sb.append(logRecord.getMessage());
                        sb.append(logRecord.getThrown() != null ? logRecord.getThrown() : "");
                        sb.append(System.getProperty("line.separator"));
                        return sb.toString();
                    }
                    Date date = new Date(logRecord.getMillis());
                    SimpleDateFormat format = AccessLoggerUtils.getFormat();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(format.format(date));
                    sb2.append("|");
                    sb2.append(Thread.currentThread().getName());
                    sb2.append("|");
                    sb2.append(name);
                    sb2.append(" |");
                    sb2.append(logRecord.getMessage());
                    sb2.append(logRecord.getThrown() != null ? logRecord.getThrown() : "");
                    sb2.append(System.getProperty("line.separator"));
                    return sb2.toString();
                }
            });
            logger2.addHandler(fileHandler);
            if (logger2 == accessLogger) {
                accessLogEnabled = true;
            } else if (logger2 == logger) {
                logEnabled = true;
            }
        } catch (Exception e) {
            try {
                Class<?> cls = Class.forName("android.util.Log");
                try {
                    cls.getMethod("i", String.class, String.class, Throwable.class).invoke(null, "OBS Android SDK", "Enable SDK log failed", e);
                } catch (Exception unused) {
                    cls.getMethod("i", String.class, String.class).invoke(null, "OBS Android SDK", "Enable SDK log failed" + e.getMessage());
                }
            } catch (Exception unused2) {
            }
            logOff(logger2);
        }
    }

    public static synchronized void setLogFileDir(String str) {
        if (ServiceUtils.isValid(str)) {
            logFileDir = str;
        }
    }

    public static synchronized void setLogFileRolloverCount(int i) {
        if (i > 0) {
            logFileRolloverCount = i;
        }
    }

    public static synchronized void setLogFileSize(int i) {
        if (i >= 0) {
            logFileSize = i;
        }
    }

    public static synchronized void setLogLevel(Level level) {
        if (level != null) {
            logLevel = level;
        }
    }
}
