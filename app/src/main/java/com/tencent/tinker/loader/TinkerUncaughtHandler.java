package com.tencent.tinker.loader;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;

public class TinkerUncaughtHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "Tinker.UncaughtHandler";
    private final Context context;
    private final File crashFile;
    private final Thread.UncaughtExceptionHandler ueh = Thread.getDefaultUncaughtExceptionHandler();

    public TinkerUncaughtHandler(Context context) {
        this.context = context;
        this.crashFile = SharePatchFileUtil.getPatchLastCrashFile(context);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) throws Throwable {
        PrintWriter printWriter;
        Throwable th2;
        IOException e;
        Log.e("Tinker.UncaughtHandler", "TinkerUncaughtHandler catch exception:" + Log.getStackTraceString(th));
        this.ueh.uncaughtException(thread, th);
        if (this.crashFile == null || !(Thread.getDefaultUncaughtExceptionHandler() instanceof TinkerUncaughtHandler)) {
            return;
        }
        File parentFile = this.crashFile.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            Log.e("Tinker.UncaughtHandler", "print crash file error: create directory fail!");
            return;
        }
        try {
            printWriter = new PrintWriter(new FileWriter(this.crashFile, false));
        } catch (IOException e2) {
            printWriter = null;
            e = e2;
        } catch (Throwable th3) {
            printWriter = null;
            th2 = th3;
            SharePatchFileUtil.closeQuietly(printWriter);
            throw th2;
        }
        try {
            try {
                printWriter.println("process:" + ShareTinkerInternals.getProcessName(this.context));
                printWriter.println(ShareTinkerInternals.getExceptionCauseString(th));
            } catch (Throwable th4) {
                th2 = th4;
                SharePatchFileUtil.closeQuietly(printWriter);
                throw th2;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e("Tinker.UncaughtHandler", "print crash file error:" + Log.getStackTraceString(e));
            SharePatchFileUtil.closeQuietly(printWriter);
            Process.killProcess(Process.myPid());
        }
        SharePatchFileUtil.closeQuietly(printWriter);
        Process.killProcess(Process.myPid());
    }
}
