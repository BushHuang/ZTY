package com.analysys.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class AThreadPool {
    private static final int MAXSIZE = 100;
    private static LinkedList<Runnable> middleLinked = new LinkedList<>();
    private static final byte[] middleByte = new byte[1];
    private static LinkedList<Runnable> loWLinked = new LinkedList<>();
    private static final byte[] lowByte = new byte[1];
    private static ExecutorService highService = Executors.newSingleThreadExecutor();
    private static ExecutorService middleService = Executors.newSingleThreadExecutor();
    private static ExecutorService lowService = Executors.newSingleThreadExecutor();
    private static volatile boolean initSuccess = false;

    public static void asyncHighPriorityExecutor(Runnable runnable) {
        highService.execute(runnable);
    }

    public static void asyncLowPriorityExecutor(Runnable runnable) {
        if (!initSuccess) {
            if (loWLinked.size() < 100) {
                loWLinked.add(runnable);
                return;
            }
            return;
        }
        try {
            if (loWLinked.size() > 0) {
                synchronized (lowByte) {
                    if (loWLinked.size() > 0) {
                        Iterator<Runnable> it = loWLinked.iterator();
                        while (it.hasNext()) {
                            lowService.execute(it.next());
                        }
                        loWLinked.clear();
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        lowService.execute(runnable);
    }

    public static void asyncMiddlePriorityExecutor(Runnable runnable) {
        if (!initSuccess) {
            if (middleLinked.size() < 100) {
                middleLinked.add(runnable);
                return;
            }
            return;
        }
        try {
            if (middleLinked.size() > 0) {
                synchronized (middleByte) {
                    if (middleLinked.size() > 0) {
                        Iterator<Runnable> it = middleLinked.iterator();
                        while (it.hasNext()) {
                            middleService.execute(it.next());
                        }
                        middleLinked.clear();
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        middleService.execute(runnable);
    }

    public static void initHighPriorityExecutor(Callable callable) {
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(callable) {
            @Override
            protected void done() {
                try {
                    get().booleanValue();
                    boolean unused = AThreadPool.initSuccess = true;
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        };
        if (initSuccess) {
            return;
        }
        highService.execute(futureTask);
    }

    public static Object syncHighPriorityExecutor(Callable callable) {
        FutureTask futureTask = new FutureTask(callable);
        highService.execute(futureTask);
        Object obj = null;
        while (!futureTask.isDone() && !futureTask.isCancelled()) {
            try {
                obj = futureTask.get();
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return obj;
    }
}
