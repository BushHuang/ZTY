package com.xuehai.launcher.common.crash;

import android.content.Context;
import android.os.Process;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u001c\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/common/crash/MyUncaughtExceptionHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "()V", "crashReportHandler", "Lcom/xuehai/launcher/common/crash/CrashReportHandler;", "defaultUncaughtExceptionHandler", "handleException", "", "thread", "Ljava/lang/Thread;", "throwable", "", "init", "context", "Landroid/content/Context;", "uncaughtException", "t", "e", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Object LOCK = new Object();
    private CrashReportHandler crashReportHandler;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private final void handleException(Thread thread, Throwable throwable) {
        Unit unit;
        CrashLog.i("Java Crash Happen cause by " + thread.getName() + '(' + thread.getId() + ')');
        CrashReportHandler crashReportHandler = this.crashReportHandler;
        if (crashReportHandler != null) {
            try {
                CrashLog.i("crashReport handle start!");
                crashReportHandler.uncaughtException(thread, throwable);
                CrashLog.i("crashReport handle start!");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultUncaughtExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            CrashLog.i("system default handle start!");
            uncaughtExceptionHandler.uncaughtException(thread, throwable);
            CrashLog.i("system default handle end!");
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit != null) {
            return;
        }
        CrashLog.i("kill process start!");
        Process.killProcess(Process.myPid());
        System.exit(1);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.defaultUncaughtExceptionHandler = defaultUncaughtExceptionHandler;
        if (!(defaultUncaughtExceptionHandler instanceof MyUncaughtExceptionHandler)) {
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
        this.crashReportHandler = new CrashReportHandler(context);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (t == null || e == null) {
            return;
        }
        synchronized (LOCK) {
            handleException(t, e);
            Unit unit = Unit.INSTANCE;
        }
    }
}
