package com.xh.logutils;

import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FireLooper implements Runnable {
    private static final Object EXIT = new Object();
    private static final ThreadLocal<FireLooper> RUNNINGS = new ThreadLocal<>();
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public static void install() {
        handler.removeMessages(0, EXIT);
        handler.post(new FireLooper());
    }

    public static boolean isSafe() {
        return RUNNINGS.get() != null;
    }

    public static void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler2) {
        uncaughtExceptionHandler = uncaughtExceptionHandler2;
    }

    public static void uninstall() {
        uninstallDelay(0L);
    }

    public static void uninstallDelay(long j) {
        handler.removeMessages(0, EXIT);
        Handler handler2 = handler;
        handler2.sendMessageDelayed(handler2.obtainMessage(0, EXIT), j);
    }

    @Override
    public void run() throws NoSuchFieldException, NoSuchMethodException, SecurityException {
        if (RUNNINGS.get() != null) {
            return;
        }
        try {
            Method declaredMethod = MessageQueue.class.getDeclaredMethod("next", new Class[0]);
            declaredMethod.setAccessible(true);
            Field declaredField = Message.class.getDeclaredField("target");
            declaredField.setAccessible(true);
            RUNNINGS.set(this);
            MessageQueue messageQueueMyQueue = Looper.myQueue();
            Binder.clearCallingIdentity();
            while (true) {
                try {
                    Message message = (Message) declaredMethod.invoke(messageQueueMyQueue, new Object[0]);
                    if (message == null || message.obj == EXIT) {
                        break;
                    }
                    ((Handler) declaredField.get(message)).dispatchMessage(message);
                    Binder.clearCallingIdentity();
                    if (Build.VERSION.SDK_INT < 21) {
                        message.recycle();
                    }
                } catch (InvocationTargetException e) {
                    e = e;
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = uncaughtExceptionHandler;
                    Throwable cause = e.getCause();
                    if (cause != null) {
                        e = cause;
                    }
                    if (uncaughtExceptionHandler2 != null) {
                        uncaughtExceptionHandler2.uncaughtException(Thread.currentThread(), e);
                    }
                    new Handler().post(this);
                } catch (Exception e2) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler3 = uncaughtExceptionHandler;
                    if (uncaughtExceptionHandler3 != null) {
                        uncaughtExceptionHandler3.uncaughtException(Thread.currentThread(), e2);
                    }
                    new Handler().post(this);
                }
            }
            RUNNINGS.set(null);
        } catch (Exception unused) {
        }
    }
}
