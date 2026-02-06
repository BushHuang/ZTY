package com.xuehai.launcher.common.plugins;

import android.os.Handler;
import android.os.HandlerThread;
import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.task.Task;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0007J\b\u0010\u0013\u001a\u00020\u0011H\u0007J\u001a\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007J\u001a\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0007J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0004H\u0007J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\b\u0010\u001c\u001a\u00020\u0011H\u0003J\u0012\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\u00020\u000b8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0002R\u0018\u0010\r\u001a\u00020\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002¨\u0006\u001f"}, d2 = {"Lcom/xuehai/launcher/common/plugins/TaskPlugins;", "", "()V", "looperRunnable", "Ljava/lang/Runnable;", "looperTasks", "Ljava/util/HashSet;", "Ljava/lang/ref/WeakReference;", "Lcom/xuehai/launcher/common/plugins/LooperTask;", "Lkotlin/collections/HashSet;", "taskHandler", "Landroid/os/Handler;", "getTaskHandler$annotations", "taskThread", "Landroid/os/HandlerThread;", "getTaskThread$annotations", "addLooperTask", "", "runnable", "clearAllTask", "postTask", "delay", "", "what", "", "removeLooperTask", "removeTask", "command", "runLooper", "startLooper", "startAt", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TaskPlugins {
    private static final Runnable looperRunnable;
    private static volatile Handler taskHandler;
    private static volatile HandlerThread taskThread;
    public static final TaskPlugins INSTANCE = new TaskPlugins();
    private static final HashSet<WeakReference<LooperTask>> looperTasks = new HashSet<>();

    static {
        HandlerThread handlerThread = new HandlerThread("launcher_task_thread");
        handlerThread.start();
        taskThread = handlerThread;
        taskHandler = new Handler(taskThread.getLooper());
        looperRunnable = new Runnable() {
            @Override
            public final void run() {
                TaskPlugins.runLooper();
            }
        };
    }

    private TaskPlugins() {
    }

    @JvmStatic
    public static final void addLooperTask(LooperTask runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        if (Thread.currentThread().getId() == taskThread.getId()) {
            MyLog.e("[MDM]", "不可在taskThread中添加LooperTasks!!");
            return;
        }
        synchronized (looperTasks) {
            boolean z = false;
            Iterator<T> it = looperTasks.iterator();
            while (it.hasNext()) {
                LooperTask looperTask = (LooperTask) ((WeakReference) it.next()).get();
                if (looperTask != null && Intrinsics.areEqual(looperTask, runnable)) {
                    z = true;
                }
            }
            if (z) {
                runnable.next();
                MyLog.w("[MDM]", "LooperTask(" + runnable.getTaskName() + ") 已存在, 重新计算下一次执行时间。");
            } else {
                MyLog.i("[MDM]", "添加LooperTask(" + runnable.getTaskName() + ')');
                runnable.init();
                looperTasks.add(new WeakReference<>(runnable));
            }
            startLooper$default(INSTANCE, 0L, 1, null);
            Unit unit = Unit.INSTANCE;
        }
    }

    @JvmStatic
    public static final void clearAllTask() {
        Task.clear();
    }

    @JvmStatic
    private static void getTaskHandler$annotations() {
    }

    @JvmStatic
    private static void getTaskThread$annotations() {
    }

    @JvmStatic
    public static final void postTask(int what, long delay) {
        removeTask(what);
        taskHandler.sendEmptyMessageDelayed(what, delay);
    }

    @JvmStatic
    public static final void postTask(Runnable runnable, long delay) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        removeTask(runnable);
        taskHandler.postDelayed(runnable, delay);
    }

    public static void postTask$default(int i, long j, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j = 0;
        }
        postTask(i, j);
    }

    public static void postTask$default(Runnable runnable, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        postTask(runnable, j);
    }

    @JvmStatic
    public static final void removeLooperTask(LooperTask runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        synchronized (looperTasks) {
            WeakReference weakReference = null;
            Iterator<T> it = looperTasks.iterator();
            while (it.hasNext()) {
                WeakReference weakReference2 = (WeakReference) it.next();
                LooperTask looperTask = (LooperTask) weakReference2.get();
                if (looperTask != null && Intrinsics.areEqual(runnable, looperTask)) {
                    weakReference = weakReference2;
                }
            }
            TypeIntrinsics.asMutableCollection(looperTasks).remove(weakReference);
        }
    }

    @JvmStatic
    public static final void removeTask(int what) {
        taskHandler.removeMessages(what);
    }

    @JvmStatic
    public static final void removeTask(Runnable command) {
        Intrinsics.checkNotNullParameter(command, "command");
        taskHandler.removeCallbacks(command);
    }

    @JvmStatic
    private static final void runLooper() {
        synchronized (looperTasks) {
            HashSet hashSet = new HashSet();
            Iterator<T> it = looperTasks.iterator();
            long whenDo = 0;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                LooperTask looperTask = (LooperTask) weakReference.get();
                if (looperTask != null) {
                    if (looperTask.reachTime()) {
                        if (looperTask.filter()) {
                            looperTask.run();
                            looperTask.markExecuted();
                        }
                        looperTask.next();
                    }
                    if (whenDo == 0 || whenDo > looperTask.getWhenDo()) {
                        whenDo = looperTask.getWhenDo();
                    }
                } else {
                    hashSet.add(weakReference);
                }
            }
            if (!hashSet.isEmpty()) {
                looperTasks.removeAll(hashSet);
            }
            INSTANCE.startLooper(whenDo);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void startLooper(long startAt) {
        postTask(looperRunnable, RangesKt.coerceAtLeast(startAt - System.currentTimeMillis(), 500L));
    }

    static void startLooper$default(TaskPlugins taskPlugins, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        taskPlugins.startLooper(j);
    }
}
