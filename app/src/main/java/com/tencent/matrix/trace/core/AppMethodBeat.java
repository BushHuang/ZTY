package com.tencent.matrix.trace.core;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.matrix.AppActiveMatrixDelegate;
import com.tencent.matrix.trace.core.LooperMonitor;
import com.tencent.matrix.trace.hacker.ActivityThreadHacker;
import com.tencent.matrix.trace.listeners.IAppMethodBeatListener;
import com.tencent.matrix.trace.util.Utils;
import com.tencent.matrix.util.MatrixHandlerThread;
import com.tencent.matrix.util.MatrixLog;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AppMethodBeat implements BeatLifecycle {
    public static final int METHOD_ID_DISPATCH = 1048574;
    private static final int METHOD_ID_MAX = 1048575;
    private static final int STATUS_DEFAULT = Integer.MAX_VALUE;
    private static final int STATUS_EXPIRED_START = -2;
    private static final int STATUS_OUT_RELEASE = -3;
    private static final int STATUS_READY = 1;
    private static final int STATUS_STARTED = 2;
    private static final int STATUS_STOPPED = -1;
    private static final String TAG = "Matrix.AppMethodBeat";
    public static boolean isDev = false;
    private static IndexRecord sIndexRecordHead;
    public static MethodEnterListener sMethodEnterListener;
    private static Runnable sUpdateDiffTimeRunnable;
    private static AppMethodBeat sInstance = new AppMethodBeat();
    private static volatile int status = Integer.MAX_VALUE;
    private static final Object statusLock = new Object();
    private static long[] sBuffer = new long[1000000];
    private static int sIndex = 0;
    private static int sLastIndex = -1;
    private static boolean assertIn = false;
    private static volatile long sCurrentDiffTime = SystemClock.uptimeMillis();
    private static volatile long sDiffTime = sCurrentDiffTime;
    private static long sMainThreadId = Looper.getMainLooper().getThread().getId();
    private static HandlerThread sTimerUpdateThread = MatrixHandlerThread.getNewHandlerThread("matrix_time_update_thread");
    private static Handler sHandler = new Handler(sTimerUpdateThread.getLooper());
    public static Set<String> sFocusActivitySet = new HashSet();
    private static final HashSet<IAppMethodBeatListener> listeners = new HashSet<>();
    private static final Object updateTimeLock = new Object();
    private static boolean isPauseUpdateTime = false;
    private static Runnable checkStartExpiredRunnable = null;
    private static LooperMonitor.LooperDispatchListener looperMonitorListener = new LooperMonitor.LooperDispatchListener() {
        @Override
        public void dispatchEnd() {
            super.dispatchEnd();
            AppMethodBeat.dispatchEnd();
        }

        @Override
        public void dispatchStart() {
            super.dispatchStart();
            AppMethodBeat.dispatchBegin();
        }

        @Override
        public boolean isValid() {
            return AppMethodBeat.status >= 1;
        }
    };

    public static final class IndexRecord {
        public int index;
        public boolean isValid;
        private IndexRecord next;
        public String source;

        public IndexRecord() {
            this.isValid = true;
            this.isValid = false;
        }

        public IndexRecord(int i) {
            this.isValid = true;
            this.index = i;
        }

        public void release() {
            this.isValid = false;
            IndexRecord indexRecord = null;
            for (IndexRecord indexRecord2 = AppMethodBeat.sIndexRecordHead; indexRecord2 != null; indexRecord2 = indexRecord2.next) {
                if (indexRecord2 == this) {
                    if (indexRecord != null) {
                        indexRecord.next = indexRecord2.next;
                    } else {
                        IndexRecord unused = AppMethodBeat.sIndexRecordHead = indexRecord2.next;
                    }
                    indexRecord2.next = null;
                    return;
                }
                indexRecord = indexRecord2;
            }
        }

        public String toString() {
            return "index:" + this.index + ",\tisValid:" + this.isValid + " source:" + this.source;
        }
    }

    public interface MethodEnterListener {
        void enter(int i, long j);
    }

    static {
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AppMethodBeat.realRelease();
            }
        }, 15000L);
        sUpdateDiffTimeRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (AppMethodBeat.isPauseUpdateTime || AppMethodBeat.status <= -1) {
                            synchronized (AppMethodBeat.updateTimeLock) {
                                AppMethodBeat.updateTimeLock.wait();
                            }
                        } else {
                            long unused = AppMethodBeat.sCurrentDiffTime = SystemClock.uptimeMillis() - AppMethodBeat.sDiffTime;
                            SystemClock.sleep(5L);
                        }
                    } catch (InterruptedException e) {
                        MatrixLog.e("Matrix.AppMethodBeat", "" + e.toString(), new Object[0]);
                        return;
                    }
                }
            }
        };
        sIndexRecordHead = null;
    }

    public static void at(Activity activity, boolean z) {
        String name = activity.getClass().getName();
        if (!z) {
            if (sFocusActivitySet.remove(name)) {
                MatrixLog.i("Matrix.AppMethodBeat", "[at] visibleScene[%s] has %s focus!", getVisibleScene(), "detach");
            }
        } else if (sFocusActivitySet.add(name)) {
            synchronized (listeners) {
                Iterator<IAppMethodBeatListener> it = listeners.iterator();
                while (it.hasNext()) {
                    it.next().onActivityFocused(name);
                }
            }
            MatrixLog.i("Matrix.AppMethodBeat", "[at] visibleScene[%s] has %s focus!", getVisibleScene(), "attach");
        }
    }

    private static void checkPileup(int i) {
        IndexRecord indexRecord = sIndexRecordHead;
        while (indexRecord != null) {
            if (indexRecord.index != i && (indexRecord.index != -1 || sLastIndex != 999999)) {
                return;
            }
            indexRecord.isValid = false;
            MatrixLog.w("Matrix.AppMethodBeat", "[checkPileup] %s", indexRecord.toString());
            indexRecord = indexRecord.next;
            sIndexRecordHead = indexRecord;
        }
    }

    private long[] copyData(IndexRecord indexRecord, IndexRecord indexRecord2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long[] jArr = new long[0];
        try {
            try {
                if (!indexRecord.isValid || !indexRecord2.isValid) {
                    MatrixLog.i("Matrix.AppMethodBeat", "[copyData] [%s:%s] length:%s cost:%sms", Integer.valueOf(Math.max(0, indexRecord.index)), Integer.valueOf(indexRecord2.index), 0, Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    return jArr;
                }
                int iMax = Math.max(0, indexRecord.index);
                int iMax2 = Math.max(0, indexRecord2.index);
                if (iMax2 > iMax) {
                    int i = (iMax2 - iMax) + 1;
                    jArr = new long[i];
                    System.arraycopy(sBuffer, iMax, jArr, 0, i);
                } else if (iMax2 < iMax) {
                    int i2 = iMax2 + 1;
                    jArr = new long[(sBuffer.length - iMax) + i2];
                    System.arraycopy(sBuffer, iMax, jArr, 0, sBuffer.length - iMax);
                    System.arraycopy(sBuffer, 0, jArr, sBuffer.length - iMax, i2);
                }
                MatrixLog.i("Matrix.AppMethodBeat", "[copyData] [%s:%s] length:%s cost:%sms", Integer.valueOf(Math.max(0, indexRecord.index)), Integer.valueOf(indexRecord2.index), Integer.valueOf(jArr.length), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return jArr;
            } catch (OutOfMemoryError e) {
                MatrixLog.e("Matrix.AppMethodBeat", e.toString(), new Object[0]);
                MatrixLog.i("Matrix.AppMethodBeat", "[copyData] [%s:%s] length:%s cost:%sms", Integer.valueOf(Math.max(0, indexRecord.index)), Integer.valueOf(indexRecord2.index), Integer.valueOf(jArr.length), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return jArr;
            }
        } catch (Throwable th) {
            MatrixLog.i("Matrix.AppMethodBeat", "[copyData] [%s:%s] length:%s cost:%sms", Integer.valueOf(Math.max(0, indexRecord.index)), Integer.valueOf(indexRecord2.index), Integer.valueOf(jArr.length), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            throw th;
        }
    }

    private static void dispatchBegin() {
        sCurrentDiffTime = SystemClock.uptimeMillis() - sDiffTime;
        isPauseUpdateTime = false;
        synchronized (updateTimeLock) {
            updateTimeLock.notify();
        }
    }

    private static void dispatchEnd() {
        isPauseUpdateTime = true;
    }

    public static long getDiffTime() {
        return sDiffTime;
    }

    public static AppMethodBeat getInstance() {
        return sInstance;
    }

    public static String getVisibleScene() {
        return AppActiveMatrixDelegate.INSTANCE.getVisibleScene();
    }

    public static void i(int i) {
        if (status > -1 && i < 1048575) {
            if (status == Integer.MAX_VALUE) {
                synchronized (statusLock) {
                    if (status == Integer.MAX_VALUE) {
                        realExecute();
                        status = 1;
                    }
                }
            }
            long id = Thread.currentThread().getId();
            MethodEnterListener methodEnterListener = sMethodEnterListener;
            if (methodEnterListener != null) {
                methodEnterListener.enter(i, id);
            }
            if (id == sMainThreadId) {
                if (assertIn) {
                    Log.e("Matrix.AppMethodBeat", "ERROR!!! AppMethodBeat.i Recursive calls!!!");
                    return;
                }
                assertIn = true;
                int i2 = sIndex;
                if (i2 < 1000000) {
                    mergeData(i, i2, true);
                } else {
                    sIndex = 0;
                    mergeData(i, 0, true);
                }
                sIndex++;
                assertIn = false;
            }
        }
    }

    public static boolean isRealTrace() {
        return status >= 1;
    }

    private static void mergeData(int i, int i2, boolean z) {
        if (i == 1048574) {
            sCurrentDiffTime = SystemClock.uptimeMillis() - sDiffTime;
        }
        sBuffer[i2] = (z ? Long.MIN_VALUE : 0L) | (i << 43) | (sCurrentDiffTime & 8796093022207L);
        checkPileup(i2);
        sLastIndex = i2;
    }

    public static void o(int i) {
        if (status > -1 && i < 1048575 && Thread.currentThread().getId() == sMainThreadId) {
            int i2 = sIndex;
            if (i2 < 1000000) {
                mergeData(i, i2, false);
            } else {
                sIndex = 0;
                mergeData(i, 0, false);
            }
            sIndex++;
        }
    }

    private static void realExecute() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException {
        MatrixLog.i("Matrix.AppMethodBeat", "[realExecute] timestamp:%s", Long.valueOf(System.currentTimeMillis()));
        sCurrentDiffTime = SystemClock.uptimeMillis() - sDiffTime;
        sHandler.removeCallbacksAndMessages(null);
        sHandler.postDelayed(sUpdateDiffTimeRunnable, 5L);
        Handler handler = sHandler;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (AppMethodBeat.statusLock) {
                    MatrixLog.i("Matrix.AppMethodBeat", "[startExpired] timestamp:%s status:%s", Long.valueOf(System.currentTimeMillis()), Integer.valueOf(AppMethodBeat.status));
                    if (AppMethodBeat.status == Integer.MAX_VALUE || AppMethodBeat.status == 1) {
                        int unused = AppMethodBeat.status = -2;
                    }
                }
            }
        };
        checkStartExpiredRunnable = runnable;
        handler.postDelayed(runnable, 15000L);
        ActivityThreadHacker.hackSysHandlerCallback();
        LooperMonitor.register(looperMonitorListener);
    }

    private static void realRelease() {
        synchronized (statusLock) {
            if (status == Integer.MAX_VALUE) {
                MatrixLog.i("Matrix.AppMethodBeat", "[realRelease] timestamp:%s", Long.valueOf(System.currentTimeMillis()));
                sHandler.removeCallbacksAndMessages(null);
                LooperMonitor.unregister(looperMonitorListener);
                sTimerUpdateThread.quit();
                sBuffer = null;
                status = -3;
            }
        }
    }

    public void addListener(IAppMethodBeatListener iAppMethodBeatListener) {
        synchronized (listeners) {
            listeners.add(iAppMethodBeatListener);
        }
    }

    public long[] copyData(IndexRecord indexRecord) {
        return copyData(indexRecord, new IndexRecord(sIndex - 1));
    }

    @Override
    public boolean isAlive() {
        return status >= 2;
    }

    public IndexRecord maskIndex(String str) {
        if (sIndexRecordHead == null) {
            IndexRecord indexRecord = new IndexRecord(sIndex - 1);
            sIndexRecordHead = indexRecord;
            indexRecord.source = str;
            return sIndexRecordHead;
        }
        IndexRecord indexRecord2 = new IndexRecord(sIndex - 1);
        indexRecord2.source = str;
        IndexRecord indexRecord3 = null;
        for (IndexRecord indexRecord4 = sIndexRecordHead; indexRecord4 != null; indexRecord4 = indexRecord4.next) {
            if (indexRecord2.index <= indexRecord4.index) {
                if (indexRecord3 == null) {
                    IndexRecord indexRecord5 = sIndexRecordHead;
                    sIndexRecordHead = indexRecord2;
                    indexRecord2.next = indexRecord5;
                } else {
                    IndexRecord indexRecord6 = indexRecord3.next;
                    if (indexRecord3.next != null) {
                        indexRecord3.next = indexRecord2;
                    }
                    indexRecord2.next = indexRecord6;
                }
                return indexRecord2;
            }
            indexRecord3 = indexRecord4;
        }
        indexRecord3.next = indexRecord2;
        return indexRecord2;
    }

    @Override
    public void onStart() {
        synchronized (statusLock) {
            if (status >= 2 || status < -2) {
                MatrixLog.w("Matrix.AppMethodBeat", "[onStart] current status:%s", Integer.valueOf(status));
            } else {
                sHandler.removeCallbacks(checkStartExpiredRunnable);
                if (sBuffer == null) {
                    throw new RuntimeException("Matrix.AppMethodBeat sBuffer == null");
                }
                MatrixLog.i("Matrix.AppMethodBeat", "[onStart] preStatus:%s", Integer.valueOf(status), Utils.getStack());
                status = 2;
            }
        }
    }

    @Override
    public void onStop() {
        synchronized (statusLock) {
            if (status == 2) {
                MatrixLog.i("Matrix.AppMethodBeat", "[onStop] %s", Utils.getStack());
                status = -1;
            } else {
                MatrixLog.w("Matrix.AppMethodBeat", "[onStop] current status:%s", Integer.valueOf(status));
            }
        }
    }

    public void printIndexRecord() {
        StringBuilder sb = new StringBuilder(" \n");
        for (IndexRecord indexRecord = sIndexRecordHead; indexRecord != null; indexRecord = indexRecord.next) {
            sb.append(indexRecord);
            sb.append("\n");
        }
        MatrixLog.i("Matrix.AppMethodBeat", "[printIndexRecord] %s", sb.toString());
    }

    public void removeListener(IAppMethodBeatListener iAppMethodBeatListener) {
        synchronized (listeners) {
            listeners.remove(iAppMethodBeatListener);
        }
    }
}
