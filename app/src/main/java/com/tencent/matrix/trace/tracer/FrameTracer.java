package com.tencent.matrix.trace.tracer;

import android.os.Handler;
import android.os.SystemClock;
import com.tencent.matrix.Matrix;
import com.tencent.matrix.report.Issue;
import com.tencent.matrix.trace.TracePlugin;
import com.tencent.matrix.trace.config.TraceConfig;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.matrix.trace.core.UIThreadMonitor;
import com.tencent.matrix.trace.listeners.IDoFrameListener;
import com.tencent.matrix.trace.util.Utils;
import com.tencent.matrix.util.DeviceUtil;
import com.tencent.matrix.util.MatrixHandlerThread;
import com.tencent.matrix.util.MatrixLog;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class FrameTracer extends Tracer {
    private static final String TAG = "Matrix.FrameTracer";
    private final TraceConfig config;
    private long frozenThreshold;
    private long highThreshold;
    private boolean isFPSEnable;
    private long middleThreshold;
    private long normalThreshold;
    private long timeSliceMs;
    private final HashSet<IDoFrameListener> listeners = new HashSet<>();
    private final long frameIntervalMs = TimeUnit.MILLISECONDS.convert(UIThreadMonitor.getMonitor().getFrameIntervalNanos(), TimeUnit.NANOSECONDS) + 1;

    public enum DropStatus {
        DROPPED_FROZEN(4),
        DROPPED_HIGH(3),
        DROPPED_MIDDLE(2),
        DROPPED_NORMAL(1),
        DROPPED_BEST(0);

        public int index;

        DropStatus(int i) {
            this.index = i;
        }
    }

    private class FPSCollector extends IDoFrameListener {
        Executor executor;
        private Handler frameHandler;
        private HashMap<String, FrameCollectItem> map;

        private FPSCollector() {
            this.frameHandler = new Handler(MatrixHandlerThread.getDefaultHandlerThread().getLooper());
            this.executor = new Executor() {
                @Override
                public void execute(Runnable runnable) {
                    FPSCollector.this.frameHandler.post(runnable);
                }
            };
            this.map = new HashMap<>();
        }

        @Override
        public void doFrameAsync(String str, long j, long j2, int i, boolean z) {
            super.doFrameAsync(str, j, j2, i, z);
            if (Utils.isEmpty(str)) {
                return;
            }
            FrameCollectItem frameCollectItem = this.map.get(str);
            if (frameCollectItem == null) {
                frameCollectItem = FrameTracer.this.new FrameCollectItem(str);
                this.map.put(str, frameCollectItem);
            }
            frameCollectItem.collect(i, z);
            if (frameCollectItem.sumFrameCost >= FrameTracer.this.timeSliceMs) {
                if (frameCollectItem.isNeedReport()) {
                    frameCollectItem.report();
                }
                this.map.remove(str);
            }
        }

        @Override
        public Executor getExecutor() {
            return this.executor;
        }
    }

    private class FrameCollectItem {
        int sumDroppedFrames;
        long sumFrameCost;
        String visibleScene;
        int sumFrame = 0;
        int sumTaskFrame = 0;
        int[] dropLevel = new int[DropStatus.values().length];
        int[] dropSum = new int[DropStatus.values().length];

        FrameCollectItem(String str) {
            this.visibleScene = str;
        }

        private float getCurrentDropStatusRadio(DropStatus dropStatus) {
            float f = this.dropLevel[dropStatus.index];
            long j = 0;
            for (int i = 0; i < this.dropLevel.length; i++) {
                j += r0[i];
            }
            return f / j;
        }

        void collect(int i, boolean z) {
            this.sumFrameCost += ((i + 1) * UIThreadMonitor.getMonitor().getFrameIntervalNanos()) / 1000000;
            this.sumDroppedFrames += i;
            this.sumFrame++;
            if (!z) {
                this.sumTaskFrame++;
            }
            long j = i;
            if (j >= FrameTracer.this.frozenThreshold) {
                int[] iArr = this.dropLevel;
                int i2 = DropStatus.DROPPED_FROZEN.index;
                iArr[i2] = iArr[i2] + 1;
                int[] iArr2 = this.dropSum;
                int i3 = DropStatus.DROPPED_FROZEN.index;
                iArr2[i3] = iArr2[i3] + i;
                return;
            }
            if (j >= FrameTracer.this.highThreshold) {
                int[] iArr3 = this.dropLevel;
                int i4 = DropStatus.DROPPED_HIGH.index;
                iArr3[i4] = iArr3[i4] + 1;
                int[] iArr4 = this.dropSum;
                int i5 = DropStatus.DROPPED_HIGH.index;
                iArr4[i5] = iArr4[i5] + i;
                return;
            }
            if (j >= FrameTracer.this.middleThreshold) {
                int[] iArr5 = this.dropLevel;
                int i6 = DropStatus.DROPPED_MIDDLE.index;
                iArr5[i6] = iArr5[i6] + 1;
                int[] iArr6 = this.dropSum;
                int i7 = DropStatus.DROPPED_MIDDLE.index;
                iArr6[i7] = iArr6[i7] + i;
                return;
            }
            if (j >= FrameTracer.this.normalThreshold) {
                int[] iArr7 = this.dropLevel;
                int i8 = DropStatus.DROPPED_NORMAL.index;
                iArr7[i8] = iArr7[i8] + 1;
                int[] iArr8 = this.dropSum;
                int i9 = DropStatus.DROPPED_NORMAL.index;
                iArr8[i9] = iArr8[i9] + i;
                return;
            }
            int[] iArr9 = this.dropLevel;
            int i10 = DropStatus.DROPPED_BEST.index;
            iArr9[i10] = iArr9[i10] + 1;
            int[] iArr10 = this.dropSum;
            int i11 = DropStatus.DROPPED_BEST.index;
            int i12 = iArr10[i11];
            if (i < 0) {
                i = 0;
            }
            iArr10[i11] = i12 + i;
        }

        public boolean isNeedReport() {
            return getCurrentDropStatusRadio(DropStatus.DROPPED_NORMAL) > FrameTracer.this.config.getDroppedFramesRatioReportThresholdNormal() || getCurrentDropStatusRadio(DropStatus.DROPPED_MIDDLE) > FrameTracer.this.config.getDroppedFramesRatioReportThresholdMiddle() || getCurrentDropStatusRadio(DropStatus.DROPPED_HIGH) > FrameTracer.this.config.getDroppedFramesRatioReportThresholdHigh() || getCurrentDropStatusRadio(DropStatus.DROPPED_FROZEN) > FrameTracer.this.config.getDroppedFramesRatioReportThresholdFrozen();
        }

        void report() {
            TracePlugin tracePlugin;
            float fMin = Math.min(60.0f, (this.sumFrame * 1000.0f) / this.sumFrameCost);
            MatrixLog.i("Matrix.FrameTracer", "[report] FPS:%s %s", Float.valueOf(fMin), toString());
            try {
                try {
                    tracePlugin = (TracePlugin) Matrix.with().getPluginByClass(TracePlugin.class);
                } catch (JSONException e) {
                    MatrixLog.e("Matrix.FrameTracer", "json error", e);
                }
                if (tracePlugin == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(DropStatus.DROPPED_FROZEN.name(), this.dropLevel[DropStatus.DROPPED_FROZEN.index]);
                jSONObject.put(DropStatus.DROPPED_HIGH.name(), this.dropLevel[DropStatus.DROPPED_HIGH.index]);
                jSONObject.put(DropStatus.DROPPED_MIDDLE.name(), this.dropLevel[DropStatus.DROPPED_MIDDLE.index]);
                jSONObject.put(DropStatus.DROPPED_NORMAL.name(), this.dropLevel[DropStatus.DROPPED_NORMAL.index]);
                jSONObject.put(DropStatus.DROPPED_BEST.name(), this.dropLevel[DropStatus.DROPPED_BEST.index]);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(DropStatus.DROPPED_FROZEN.name(), this.dropSum[DropStatus.DROPPED_FROZEN.index]);
                jSONObject2.put(DropStatus.DROPPED_HIGH.name(), this.dropSum[DropStatus.DROPPED_HIGH.index]);
                jSONObject2.put(DropStatus.DROPPED_MIDDLE.name(), this.dropSum[DropStatus.DROPPED_MIDDLE.index]);
                jSONObject2.put(DropStatus.DROPPED_NORMAL.name(), this.dropSum[DropStatus.DROPPED_NORMAL.index]);
                jSONObject2.put(DropStatus.DROPPED_BEST.name(), this.dropSum[DropStatus.DROPPED_BEST.index]);
                JSONObject jSONObject3 = new JSONObject();
                DeviceUtil.getDeviceInfo(jSONObject3, tracePlugin.getApplication());
                jSONObject3.put("scene", this.visibleScene);
                jSONObject3.put("dropLevel", jSONObject);
                jSONObject3.put("dropSum", jSONObject2);
                jSONObject3.put("fps", fMin);
                jSONObject3.put("dropTaskFrameSum", this.sumTaskFrame);
                Issue issue = new Issue();
                issue.setTag("Trace_FPS");
                issue.setContent(jSONObject3);
                tracePlugin.onDetectIssue(issue);
            } finally {
                this.sumFrame = 0;
                this.sumDroppedFrames = 0;
                this.sumFrameCost = 0L;
                this.sumTaskFrame = 0;
            }
        }

        public String toString() {
            return "visibleScene=" + this.visibleScene + ", sumFrame=" + this.sumFrame + ", sumDroppedFrames=" + this.sumDroppedFrames + ", sumFrameCost=" + this.sumFrameCost + ", dropLevel=" + Arrays.toString(this.dropLevel);
        }
    }

    public FrameTracer(TraceConfig traceConfig) {
        this.config = traceConfig;
        this.timeSliceMs = traceConfig.getTimeSliceMs();
        this.isFPSEnable = traceConfig.isFPSEnable();
        this.frozenThreshold = traceConfig.getFrozenThreshold();
        this.highThreshold = traceConfig.getHighThreshold();
        this.normalThreshold = traceConfig.getNormalThreshold();
        this.middleThreshold = traceConfig.getMiddleThreshold();
        MatrixLog.i("Matrix.FrameTracer", "[init] frameIntervalMs:%s isFPSEnable:%s", Long.valueOf(this.frameIntervalMs), Boolean.valueOf(this.isFPSEnable));
        if (this.isFPSEnable) {
            addListener(new FPSCollector());
        }
    }

    private void notifyListener(final String str, final long j, final long j2, final boolean z) throws Throwable {
        long j3;
        Iterator<IDoFrameListener> it;
        IDoFrameListener iDoFrameListener;
        HashSet<IDoFrameListener> hashSet;
        long j4;
        int i;
        int iCurrentTimeMillis = System.currentTimeMillis();
        try {
            HashSet<IDoFrameListener> hashSet2 = this.listeners;
            try {
                synchronized (hashSet2) {
                    try {
                        Iterator<IDoFrameListener> it2 = this.listeners.iterator();
                        iCurrentTimeMillis = iCurrentTimeMillis;
                        while (it2.hasNext()) {
                            final IDoFrameListener next = it2.next();
                            if (this.config.isDevEnv()) {
                                next.time = SystemClock.uptimeMillis();
                            }
                            final int i2 = (int) (j / this.frameIntervalMs);
                            next.doFrameSync(str, j, j2, i2, z);
                            if (next.getExecutor() != null) {
                                it = it2;
                                iDoFrameListener = next;
                                hashSet = hashSet2;
                                j4 = iCurrentTimeMillis;
                                i = 2;
                                next.getExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        next.doFrameAsync(str, j, j2, i2, z);
                                    }
                                });
                            } else {
                                it = it2;
                                iDoFrameListener = next;
                                hashSet = hashSet2;
                                j4 = iCurrentTimeMillis;
                                i = 2;
                            }
                            if (this.config.isDevEnv()) {
                                iDoFrameListener.time = SystemClock.uptimeMillis() - iDoFrameListener.time;
                                Object[] objArr = new Object[i];
                                objArr[0] = Long.valueOf(iDoFrameListener.time);
                                objArr[1] = iDoFrameListener;
                                MatrixLog.d("Matrix.FrameTracer", "[notifyListener] cost:%sms listener:%s", objArr);
                            }
                            it2 = it;
                            hashSet2 = hashSet;
                            iCurrentTimeMillis = j4;
                        }
                        long j5 = iCurrentTimeMillis;
                        long jCurrentTimeMillis = System.currentTimeMillis() - j5;
                        if (!this.config.isDebug() || jCurrentTimeMillis <= this.frameIntervalMs) {
                            return;
                        }
                        MatrixLog.w("Matrix.FrameTracer", "[notifyListener] warm! maybe do heavy work in doFrameSync! size:%s cost:%sms", Integer.valueOf(this.listeners.size()), Long.valueOf(jCurrentTimeMillis));
                    } catch (Throwable th) {
                        th = th;
                        HashSet<IDoFrameListener> hashSet3 = hashSet2;
                        j3 = iCurrentTimeMillis;
                        iCurrentTimeMillis = 2;
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            th = th2;
                            long jCurrentTimeMillis2 = System.currentTimeMillis() - j3;
                            if (this.config.isDebug() && jCurrentTimeMillis2 > this.frameIntervalMs) {
                                Object[] objArr2 = new Object[iCurrentTimeMillis];
                                objArr2[0] = Integer.valueOf(this.listeners.size());
                                objArr2[1] = Long.valueOf(jCurrentTimeMillis2);
                                MatrixLog.w("Matrix.FrameTracer", "[notifyListener] warm! maybe do heavy work in doFrameSync! size:%s cost:%sms", objArr2);
                            }
                            throw th;
                        }
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            j3 = iCurrentTimeMillis;
            iCurrentTimeMillis = 2;
        }
    }

    public void addListener(IDoFrameListener iDoFrameListener) {
        synchronized (this.listeners) {
            this.listeners.add(iDoFrameListener);
        }
    }

    @Override
    public void doFrame(String str, long j, long j2, long j3, long j4, long j5, long j6) throws Throwable {
        if (isForeground() && AppMethodBeat.sFocusActivitySet.contains(str)) {
            notifyListener(str, j2 - j, j3, j3 >= 0);
        }
    }

    @Override
    public void onAlive() {
        super.onAlive();
        UIThreadMonitor.getMonitor().addObserver(this);
    }

    @Override
    public void onDead() {
        super.onDead();
        UIThreadMonitor.getMonitor().removeObserver(this);
    }

    public void removeListener(IDoFrameListener iDoFrameListener) {
        synchronized (this.listeners) {
            this.listeners.remove(iDoFrameListener);
        }
    }
}
