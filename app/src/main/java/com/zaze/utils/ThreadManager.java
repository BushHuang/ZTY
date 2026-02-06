package com.zaze.utils;

import android.os.Handler;
import android.os.Looper;
import com.zaze.utils.log.ZLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Deprecated
public class ThreadManager {
    private static volatile ThreadManager instance = null;
    private static boolean needLog = false;
    private ThreadPoolExecutor backgroundExecutor;
    private ThreadPoolExecutor diskIO;
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private ThreadPoolExecutor multiExecutor;
    private ThreadPoolExecutor singleExecutor;

    private ThreadManager() {
        initDiskIO();
        initBackgroundExecutor();
        initSingleExecutor();
        initMultiExecutor();
    }

    public static ThreadManager getInstance() {
        if (instance == null) {
            synchronized (ThreadManager.class) {
                if (instance == null) {
                    instance = new ThreadManager();
                }
            }
        }
        return instance;
    }

    private void initBackgroundExecutor() {
        this.backgroundExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "backgroundExecutor");
                thread.setPriority(1);
                thread.setDaemon(true);
                if (ThreadManager.needLog) {
                    ZLog.i("Debug[ZZ]", "run %s(%s)", thread.getName(), Long.valueOf(thread.getId()));
                }
                return thread;
            }
        });
    }

    private void initDiskIO() {
        this.diskIO = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "diskIO");
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                if (ThreadManager.needLog) {
                    ZLog.i("Debug[ZZ]", "run %s(%s)", thread.getName(), Long.valueOf(thread.getId()));
                }
                return thread;
            }
        });
    }

    private void initMultiExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "multiExecutor");
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                if (ThreadManager.needLog) {
                    ZLog.i("Debug[ZZ]", "run %s(%s)", thread.getName(), Long.valueOf(thread.getId()));
                }
                return thread;
            }
        });
        this.multiExecutor = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    private void initSingleExecutor() {
        this.singleExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "singleExecutor");
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                if (ThreadManager.needLog) {
                    ZLog.i("Debug[ZZ]", "run %s(%s)", thread.getName(), Long.valueOf(thread.getId()));
                }
                return thread;
            }
        });
    }

    public static void setNeedLog(boolean z) {
        needLog = z;
    }

    public void runInBackgroundThread(final Runnable runnable) {
        this.backgroundExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void runInDiskIO(Runnable runnable) {
        this.diskIO.execute(runnable);
    }

    public void runInMultiThread(Runnable runnable) {
        this.multiExecutor.execute(runnable);
    }

    public void runInSingleThread(Runnable runnable) {
        this.singleExecutor.execute(runnable);
    }

    public void runInUIThread(Runnable runnable) {
        this.mainThreadHandler.post(runnable);
    }

    public void runInUIThread(Runnable runnable, long j) {
        this.mainThreadHandler.postDelayed(runnable, j);
    }

    public void shutdownAll() {
        shutdownSingleThread();
        shutdownBackgroundThread();
        shutdownMultiThread();
        shutdownDiskIOThread();
    }

    public void shutdownBackgroundThread() {
        if (needLog) {
            ZLog.i("Debug[ZZ]", "shutdownBackgroundThread", new Object[0]);
        }
        this.backgroundExecutor.shutdownNow();
        initBackgroundExecutor();
    }

    public void shutdownDiskIOThread() {
        if (needLog) {
            ZLog.i("Debug[ZZ]", "shutdownBackgroundThread", new Object[0]);
        }
        this.diskIO.shutdownNow();
        initDiskIO();
    }

    public void shutdownMultiThread() {
        if (needLog) {
            ZLog.i("Debug[ZZ]", "shutdownMultiThread", new Object[0]);
        }
        this.multiExecutor.shutdownNow();
        initMultiExecutor();
    }

    public void shutdownSingleThread() {
        if (needLog) {
            ZLog.i("Debug[ZZ]", "shutdownSingleThread", new Object[0]);
        }
        this.singleExecutor.shutdownNow();
        initSingleExecutor();
    }
}
