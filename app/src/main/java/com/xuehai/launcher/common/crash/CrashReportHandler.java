package com.xuehai.launcher.common.crash;

import android.content.Context;
import android.os.Environment;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xuehai/launcher/common/crash/CrashReportHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "crashDetail", "Lcom/xuehai/launcher/common/crash/CrashDetail;", "uncaughtException", "", "t", "Ljava/lang/Thread;", "e", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CrashReportHandler implements Thread.UncaughtExceptionHandler {
    private final CrashDetail crashDetail;

    public CrashReportHandler(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.crashDetail = new CrashDetail(context);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Intrinsics.checkNotNullParameter(t, "t");
        Intrinsics.checkNotNullParameter(e, "e");
        this.crashDetail.uncaughtException(t, e);
        StringBuilder sb = new StringBuilder();
        sb.append("------------ CrashDetail ----------\n");
        sb.append("PID: " + this.crashDetail.getPid() + '\n');
        sb.append("APP: " + this.crashDetail.getAppName() + '(' + this.crashDetail.getPackageName() + ")\n");
        sb.append("VERSION: " + this.crashDetail.getVersionName() + '(' + this.crashDetail.getVersionCode() + ")\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("DATE: ");
        sb2.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
        sb2.append('\n');
        sb.append(sb2.toString());
        sb.append("STACKTRACE: " + this.crashDetail.getStackTrace());
        CrashLog.e(sb.toString());
        CrashLevelManager.INSTANCE.levelUp();
        if (Intrinsics.areEqual(Environment.getExternalStorageState(), "mounted")) {
            return;
        }
        CrashLog.e("CrashDetail MEDIA_MOUNTED : false");
    }
}
