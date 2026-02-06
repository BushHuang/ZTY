package com.tencent.matrix.trace.core;

import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer;
import com.tencent.matrix.trace.config.TraceConfig;
import com.tencent.matrix.trace.core.LooperMonitor;
import com.tencent.matrix.trace.listeners.LooperObserver;
import com.tencent.matrix.trace.util.Utils;
import com.tencent.matrix.util.MatrixLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class UIThreadMonitor implements BeatLifecycle, Runnable {
    private static final String ADD_CALLBACK = "addCallbackLocked";
    public static final int CALLBACK_ANIMATION = 1;
    public static final int CALLBACK_INPUT = 0;
    private static final int CALLBACK_LAST = 2;
    public static final int CALLBACK_TRAVERSAL = 2;
    private static final int DO_QUEUE_BEGIN = 1;
    private static final int DO_QUEUE_DEFAULT = 0;
    private static final int DO_QUEUE_END = 2;
    public static final int DO_QUEUE_END_ERROR = -100;
    private static final String TAG = "Matrix.UIThreadMonitor";
    private static final UIThreadMonitor sInstance = new UIThreadMonitor();
    private Method addAnimationQueue;
    private Method addInputQueue;
    private Method addTraversalQueue;
    private Object callbackQueueLock;
    private Object[] callbackQueues;
    private Choreographer choreographer;
    private TraceConfig config;
    private volatile boolean isAlive = false;
    private long[] dispatchTimeMs = new long[4];
    private final HashSet<LooperObserver> observers = new HashSet<>();
    private volatile long token = 0;
    private boolean isBelongFrame = false;
    private long frameIntervalNanos = 16666666;
    private int[] queueStatus = new int[3];
    private boolean[] callbackExist = new boolean[3];
    private long[] queueCost = new long[3];
    private boolean isInit = false;

    private synchronized void addFrameCallback(int i, Runnable runnable, boolean z) {
        if (this.callbackExist[i]) {
            MatrixLog.w("Matrix.UIThreadMonitor", "[addFrameCallback] this type %s callback has exist! isAddHeader:%s", Integer.valueOf(i), Boolean.valueOf(z));
            return;
        }
        if (!this.isAlive && i == 0) {
            MatrixLog.w("Matrix.UIThreadMonitor", "[addFrameCallback] UIThreadMonitor is not alive!", new Object[0]);
            return;
        }
        try {
            synchronized (this.callbackQueueLock) {
                Method method = i != 0 ? i != 1 ? i != 2 ? null : this.addTraversalQueue : this.addAnimationQueue : this.addInputQueue;
                if (method != null) {
                    Object obj = this.callbackQueues[i];
                    Object[] objArr = new Object[3];
                    objArr[0] = Long.valueOf(!z ? SystemClock.uptimeMillis() : -1L);
                    objArr[1] = runnable;
                    objArr[2] = null;
                    method.invoke(obj, objArr);
                    this.callbackExist[i] = true;
                }
            }
        } catch (Exception e) {
            MatrixLog.e("Matrix.UIThreadMonitor", e.toString(), new Object[0]);
        }
    }

    private void dispatchBegin() {
        long[] jArr = this.dispatchTimeMs;
        long jUptimeMillis = SystemClock.uptimeMillis();
        jArr[0] = jUptimeMillis;
        this.token = jUptimeMillis;
        this.dispatchTimeMs[2] = SystemClock.currentThreadTimeMillis();
        AppMethodBeat.i(1048574);
        synchronized (this.observers) {
            Iterator<LooperObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                LooperObserver next = it.next();
                if (!next.isDispatchBegin()) {
                    next.dispatchBegin(this.dispatchTimeMs[0], this.dispatchTimeMs[2], this.token);
                }
            }
        }
    }

    private void dispatchEnd() {
        if (this.isBelongFrame) {
            doFrameEnd(this.token);
        }
        long j = this.token;
        long jUptimeMillis = SystemClock.uptimeMillis();
        synchronized (this.observers) {
            Iterator<LooperObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                LooperObserver next = it.next();
                if (next.isDispatchBegin()) {
                    next.doFrame(AppMethodBeat.getVisibleScene(), this.token, SystemClock.uptimeMillis(), this.isBelongFrame ? jUptimeMillis - j : 0L, this.queueCost[0], this.queueCost[1], this.queueCost[2]);
                }
            }
        }
        this.dispatchTimeMs[3] = SystemClock.currentThreadTimeMillis();
        this.dispatchTimeMs[1] = SystemClock.uptimeMillis();
        AppMethodBeat.o(1048574);
        synchronized (this.observers) {
            Iterator<LooperObserver> it2 = this.observers.iterator();
            while (it2.hasNext()) {
                LooperObserver next2 = it2.next();
                if (next2.isDispatchBegin()) {
                    next2.dispatchEnd(this.dispatchTimeMs[0], this.dispatchTimeMs[2], this.dispatchTimeMs[1], this.dispatchTimeMs[3], this.token, this.isBelongFrame);
                }
            }
        }
    }

    private void doFrameBegin(long j) {
        this.isBelongFrame = true;
    }

    private void doFrameEnd(long j) {
        doQueueEnd(2);
        for (int i : this.queueStatus) {
            if (i != 2) {
                this.queueCost[i] = -100;
                if (this.config.isDevEnv) {
                    throw new RuntimeException(String.format("UIThreadMonitor happens type[%s] != DO_QUEUE_END", Integer.valueOf(i)));
                }
            }
        }
        this.queueStatus = new int[3];
        addFrameCallback(0, this, true);
        this.isBelongFrame = false;
    }

    private void doQueueBegin(int i) {
        this.queueStatus[i] = 1;
        this.queueCost[i] = System.nanoTime();
    }

    private void doQueueEnd(int i) {
        this.queueStatus[i] = 2;
        this.queueCost[i] = System.nanoTime() - this.queueCost[i];
        synchronized (this) {
            this.callbackExist[i] = false;
        }
    }

    public static UIThreadMonitor getMonitor() {
        return sInstance;
    }

    private Method reflectChoreographerMethod(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Exception e) {
            MatrixLog.e("Matrix.UIThreadMonitor", e.toString(), new Object[0]);
            return null;
        }
    }

    private <T> T reflectObject(Object obj, String str) throws NoSuchFieldException {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return (T) declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            MatrixLog.e("Matrix.UIThreadMonitor", e.toString(), new Object[0]);
            return null;
        }
    }

    public void addObserver(LooperObserver looperObserver) {
        if (!this.isAlive) {
            onStart();
        }
        synchronized (this.observers) {
            this.observers.add(looperObserver);
        }
    }

    public long getFrameIntervalNanos() {
        return this.frameIntervalNanos;
    }

    public long getQueueCost(int i, long j) {
        if (j != this.token) {
            return -1L;
        }
        if (this.queueStatus[i] == 2) {
            return this.queueCost[i];
        }
        return 0L;
    }

    public void init(TraceConfig traceConfig) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new AssertionError("must be init in main thread!");
        }
        this.config = traceConfig;
        Choreographer choreographer = Choreographer.getInstance();
        this.choreographer = choreographer;
        this.callbackQueueLock = reflectObject(choreographer, "mLock");
        Object[] objArr = (Object[]) reflectObject(this.choreographer, "mCallbackQueues");
        this.callbackQueues = objArr;
        this.addInputQueue = reflectChoreographerMethod(objArr[0], "addCallbackLocked", Long.TYPE, Object.class, Object.class);
        this.addAnimationQueue = reflectChoreographerMethod(this.callbackQueues[1], "addCallbackLocked", Long.TYPE, Object.class, Object.class);
        this.addTraversalQueue = reflectChoreographerMethod(this.callbackQueues[2], "addCallbackLocked", Long.TYPE, Object.class, Object.class);
        this.frameIntervalNanos = ((Long) reflectObject(this.choreographer, "mFrameIntervalNanos")).longValue();
        LooperMonitor.register(new LooperMonitor.LooperDispatchListener() {
            @Override
            public void dispatchEnd() {
                super.dispatchEnd();
                UIThreadMonitor.this.dispatchEnd();
            }

            @Override
            public void dispatchStart() {
                super.dispatchStart();
                UIThreadMonitor.this.dispatchBegin();
            }

            @Override
            public boolean isValid() {
                return UIThreadMonitor.this.isAlive;
            }
        });
        this.isInit = true;
        Object[] objArr2 = new Object[6];
        objArr2[0] = Boolean.valueOf(this.callbackQueueLock == null);
        objArr2[1] = Boolean.valueOf(this.callbackQueues == null);
        objArr2[2] = Boolean.valueOf(this.addInputQueue == null);
        objArr2[3] = Boolean.valueOf(this.addTraversalQueue == null);
        objArr2[4] = Boolean.valueOf(this.addAnimationQueue == null);
        objArr2[5] = Long.valueOf(this.frameIntervalNanos);
        MatrixLog.i("Matrix.UIThreadMonitor", "[UIThreadMonitor] %s %s %s %s %s frameIntervalNanos:%s", objArr2);
        if (traceConfig.isDevEnv()) {
            addObserver(new LooperObserver() {
                @Override
                public void doFrame(String str, long j, long j2, long j3, long j4, long j5, long j6) {
                    MatrixLog.i("Matrix.UIThreadMonitor", "activityName[%s] frame cost:%sms [%s|%s|%s]ns", str, Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5), Long.valueOf(j6));
                }
            });
        }
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    public boolean isInit() {
        return this.isInit;
    }

    @Override
    public synchronized void onStart() {
        if (!this.isInit) {
            throw new RuntimeException("never init!");
        }
        if (!this.isAlive) {
            this.isAlive = true;
            synchronized (this) {
                MatrixLog.i("Matrix.UIThreadMonitor", "[onStart] callbackExist:%s %s", Arrays.toString(this.callbackExist), Utils.getStack());
                this.callbackExist = new boolean[3];
                this.queueStatus = new int[3];
                this.queueCost = new long[3];
                addFrameCallback(0, this, true);
            }
        }
    }

    @Override
    public synchronized void onStop() {
        if (!this.isInit) {
            throw new RuntimeException("UIThreadMonitor is never init!");
        }
        if (this.isAlive) {
            this.isAlive = false;
            MatrixLog.i("Matrix.UIThreadMonitor", "[onStop] callbackExist:%s %s", Arrays.toString(this.callbackExist), Utils.getStack());
        }
    }

    public void removeObserver(LooperObserver looperObserver) {
        synchronized (this.observers) {
            this.observers.remove(looperObserver);
            if (this.observers.isEmpty()) {
                onStop();
            }
        }
    }

    @Override
    public void run() {
        long jNanoTime = System.nanoTime();
        try {
            doFrameBegin(this.token);
            doQueueBegin(0);
            addFrameCallback(1, new Runnable() {
                @Override
                public void run() {
                    UIThreadMonitor.this.doQueueEnd(0);
                    UIThreadMonitor.this.doQueueBegin(1);
                }
            }, true);
            addFrameCallback(2, new Runnable() {
                @Override
                public void run() {
                    UIThreadMonitor.this.doQueueEnd(1);
                    UIThreadMonitor.this.doQueueBegin(2);
                }
            }, true);
            if (this.config.isDevEnv()) {
                MatrixLog.d("Matrix.UIThreadMonitor", "[UIThreadMonitor#run] inner cost:%sns", Long.valueOf(System.nanoTime() - jNanoTime));
            }
        } catch (Throwable th) {
            if (this.config.isDevEnv()) {
                MatrixLog.d("Matrix.UIThreadMonitor", "[UIThreadMonitor#run] inner cost:%sns", Long.valueOf(System.nanoTime() - jNanoTime));
            }
            throw th;
        }
    }
}
