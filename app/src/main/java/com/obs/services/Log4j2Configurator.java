package com.obs.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Log4j2Configurator {
    private static volatile boolean isWatchStart = false;
    private static volatile boolean log4j2Enabled = false;

    private static class LogWatcher extends Thread {
        private String configPath;
        private Object ctx;
        private long watchInterval;

        LogWatcher(String str, Object obj, long j) {
            this.configPath = str;
            this.ctx = obj;
            this.watchInterval = j;
        }

        @Override
        public void run() throws IllegalAccessException, InterruptedException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
            try {
                Class<?> cls = Class.forName("org.apache.logging.log4j.core.config.Configuration");
                Method method = this.ctx.getClass().getMethod("stop", new Class[0]);
                Method method2 = this.ctx.getClass().getMethod("start", cls);
                Class<?> cls2 = Class.forName("org.apache.logging.log4j.core.config.xml.XmlConfiguration");
                Class<?> cls3 = Class.forName("org.apache.logging.log4j.core.config.ConfigurationSource");
                Constructor<?> constructor = cls3.getConstructor(InputStream.class);
                Constructor<?> constructor2 = cls2.getConstructor(this.ctx.getClass(), cls3);
                while (true) {
                    Thread.sleep(this.watchInterval);
                    method.invoke(this.ctx, new Object[0]);
                    method2.invoke(this.ctx, constructor2.newInstance(this.ctx, constructor.newInstance(new FileInputStream(this.configPath))));
                }
            } catch (Exception unused) {
            }
        }
    }

    private static Object getLogContext(String str) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        try {
            Class<?> cls = Class.forName("org.apache.logging.log4j.core.config.ConfigurationSource");
            return Class.forName("org.apache.logging.log4j.core.config.Configurator").getMethod("initialize", ClassLoader.class, cls).invoke(null, null, cls.getConstructor(InputStream.class).newInstance(new FileInputStream(str)));
        } catch (Exception unused) {
            return null;
        }
    }

    public static synchronized void setLogConfig(String str) {
        setLogConfig(str, false);
    }

    public static synchronized void setLogConfig(String str, boolean z) {
        setLogConfig(str, z, 60000L);
    }

    public static synchronized void setLogConfig(String str, boolean z, long j) {
        if (log4j2Enabled) {
            return;
        }
        Object logContext = getLogContext(str);
        if (z && logContext != null && !isWatchStart) {
            try {
                isWatchStart = true;
                if (j <= 0) {
                    j = 60000;
                }
                LogWatcher logWatcher = new LogWatcher(str, logContext, j);
                logWatcher.setDaemon(true);
                logWatcher.start();
            } catch (Exception unused) {
            }
        }
        log4j2Enabled = true;
    }
}
