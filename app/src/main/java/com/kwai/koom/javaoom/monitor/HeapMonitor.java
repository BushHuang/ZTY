package com.kwai.koom.javaoom.monitor;

import com.kwai.koom.javaoom.common.KConstants;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.monitor.TriggerReason;

public class HeapMonitor implements Monitor {
    private static final String TAG = "HeapMonitor";
    private HeapThreshold heapThreshold;
    private HeapStatus lastHeapStatus;
    private int currentTimes = 0;
    private volatile boolean started = false;

    static class HeapStatus {
        boolean isOverThreshold;
        long max;
        long used;

        HeapStatus() {
        }
    }

    private HeapStatus currentHeapStatus() {
        HeapStatus heapStatus = new HeapStatus();
        heapStatus.max = Runtime.getRuntime().maxMemory();
        heapStatus.used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        heapStatus.isOverThreshold = (((float) heapStatus.used) * 100.0f) / ((float) heapStatus.max) > this.heapThreshold.value();
        return heapStatus;
    }

    @Override
    public TriggerReason getTriggerReason() {
        return TriggerReason.dumpReason(TriggerReason.DumpReason.HEAP_OVER_THRESHOLD);
    }

    @Override
    public boolean isTrigger() {
        if (!this.started) {
            return false;
        }
        HeapStatus heapStatusCurrentHeapStatus = currentHeapStatus();
        if (heapStatusCurrentHeapStatus.isOverThreshold) {
            KLog.i("HeapMonitor", "heap status used:" + (heapStatusCurrentHeapStatus.used / KConstants.Bytes.MB) + ", max:" + (heapStatusCurrentHeapStatus.max / KConstants.Bytes.MB) + ", last over times:" + this.currentTimes);
            if (!this.heapThreshold.ascending() || this.lastHeapStatus == null || heapStatusCurrentHeapStatus.used >= this.lastHeapStatus.used) {
                this.currentTimes++;
            } else {
                KLog.i("HeapMonitor", "heap status used is not ascending, and over times reset to 0");
                this.currentTimes = 0;
            }
        } else {
            this.currentTimes = 0;
        }
        this.lastHeapStatus = heapStatusCurrentHeapStatus;
        return this.currentTimes >= this.heapThreshold.overTimes();
    }

    @Override
    public MonitorType monitorType() {
        return MonitorType.HEAP;
    }

    @Override
    public int pollInterval() {
        return this.heapThreshold.pollInterval();
    }

    @Override
    public void setThreshold(Threshold threshold) {
        if (!(threshold instanceof HeapThreshold)) {
            throw new RuntimeException("Must be HeapThreshold!");
        }
        this.heapThreshold = (HeapThreshold) threshold;
    }

    @Override
    public void start() {
        this.started = true;
        if (this.heapThreshold == null) {
            this.heapThreshold = KGlobalConfig.getHeapThreshold();
        }
        KLog.i("HeapMonitor", "start HeapMonitor, HeapThreshold ratio:" + this.heapThreshold.value() + ", max over times: " + this.heapThreshold.overTimes());
    }

    @Override
    public void stop() {
        KLog.i("HeapMonitor", "stop");
        this.started = false;
    }
}
