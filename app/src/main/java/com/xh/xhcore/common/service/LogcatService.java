package com.xh.xhcore.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.xh.xhcore.common.base.XhBaseApplication;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogcatService extends Service {
    public static final long LOG_MAX_SIZE = 4194304;
    private static ExecutorService looperExecutor;

    public static boolean isRunning() {
        return LogcatUtil.isRunning();
    }

    static Thread lambda$onCreate$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "LogcatService");
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }

    public static void resetRunningState() {
        LogcatUtil.stopCatchLog();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (looperExecutor == null) {
            looperExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
                @Override
                public final Thread newThread(Runnable runnable) {
                    return LogcatService.lambda$onCreate$0(runnable);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resetRunningState();
        ExecutorService executorService = looperExecutor;
        if (executorService != null) {
            executorService.shutdownNow();
            looperExecutor = null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            final long jMax = intent.hasExtra("maxSize") ? Math.max(4194304L, intent.getLongExtra("maxSize", 4194304L)) : 4194304L;
            if (!LogcatUtil.isRunning()) {
                looperExecutor.execute(new Runnable() {
                    @Override
                    public final void run() {
                        LogcatUtil.startCatchLog(XhBaseApplication.mContext, jMax);
                    }
                });
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
