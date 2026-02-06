package com.xuehai.launcher.common.crash;

import android.content.Context;
import android.os.Process;
import com.xuehai.launcher.common.config.ClientConfig;
import com.zaze.utils.StackTraceHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\b\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\b\"\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u0011\u0010\u001f\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010!\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\b¨\u0006)"}, d2 = {"Lcom/xuehai/launcher/common/crash/CrashDetail;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appName", "", "getAppName", "()Ljava/lang/String;", "packageName", "getPackageName", "pid", "", "getPid", "()I", "stackTrace", "getStackTrace", "setStackTrace", "(Ljava/lang/String;)V", "threadId", "", "getThreadId", "()J", "setThreadId", "(J)V", "threadName", "getThreadName", "setThreadName", "timeMillis", "getTimeMillis", "setTimeMillis", "versionCode", "getVersionCode", "versionName", "getVersionName", "uncaughtException", "", "thread", "Ljava/lang/Thread;", "throwable", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CrashDetail {
    private final String appName;
    private final String packageName;
    private final int pid;
    private String stackTrace;
    private long threadId;
    private String threadName;
    private long timeMillis;
    private final long versionCode;
    private final String versionName;

    public CrashDetail(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.pid = Process.myPid();
        String packageName = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        this.packageName = packageName;
        this.versionCode = ClientConfig.INSTANCE.getAppVersionCode();
        this.versionName = ClientConfig.INSTANCE.getAppVersion();
        this.appName = "智通云";
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final int getPid() {
        return this.pid;
    }

    public final String getStackTrace() {
        return this.stackTrace;
    }

    public final long getThreadId() {
        return this.threadId;
    }

    public final String getThreadName() {
        return this.threadName;
    }

    public final long getTimeMillis() {
        return this.timeMillis;
    }

    public final long getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final void setStackTrace(String str) {
        this.stackTrace = str;
    }

    public final void setThreadId(long j) {
        this.threadId = j;
    }

    public final void setThreadName(String str) {
        this.threadName = str;
    }

    public final void setTimeMillis(long j) {
        this.timeMillis = j;
    }

    public final void uncaughtException(Thread thread, Throwable throwable) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        this.threadName = thread.getName();
        this.threadId = thread.getId();
        Throwable cause = throwable.getCause();
        if (cause != null) {
            throwable = cause;
        }
        this.timeMillis = System.currentTimeMillis();
        this.stackTrace = StackTraceHelper.INSTANCE.getErrorMsg(throwable);
    }
}
