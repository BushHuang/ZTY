package com.kwai.koom.javaoom.dump;

import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KHeapFile;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.common.KTrigger;
import com.kwai.koom.javaoom.common.KTriggerStrategy;
import com.kwai.koom.javaoom.common.KVData;
import com.kwai.koom.javaoom.monitor.HeapMonitor;
import com.kwai.koom.javaoom.monitor.MonitorManager;
import com.kwai.koom.javaoom.monitor.MonitorTriggerListener;
import com.kwai.koom.javaoom.monitor.MonitorType;
import com.kwai.koom.javaoom.monitor.TriggerReason;
import com.kwai.koom.javaoom.report.HeapAnalyzeReporter;

public class HeapDumpTrigger implements KTrigger {
    private static final String TAG = "HeapDumpTrigger";
    private HeapDumpListener heapDumpListener;
    private HeapDumper heapDumper;
    private MonitorManager monitorManager;
    private boolean triggered;

    public HeapDumpTrigger() {
        MonitorManager monitorManager = new MonitorManager();
        this.monitorManager = monitorManager;
        monitorManager.addMonitor(new HeapMonitor());
        this.heapDumper = new ForkAndStripHeapDumper();
    }

    public void doHeapDump(TriggerReason.DumpReason dumpReason) throws Throwable {
        KLog.i("HeapDumpTrigger", "doHeapDump");
        KHeapFile.getKHeapFile().buildFiles();
        HeapAnalyzeReporter.addDumpReason(dumpReason);
        HeapAnalyzeReporter.addDeviceRunningInfo();
        if (this.heapDumper.dump(KHeapFile.getKHeapFile().hprof.path)) {
            this.heapDumpListener.onHeapDumped(dumpReason);
            return;
        }
        KLog.e("HeapDumpTrigger", "heap dump failed!");
        this.heapDumpListener.onHeapDumpFailed();
        KHeapFile.delete();
    }

    public boolean lambda$startTrack$0$HeapDumpTrigger(MonitorType monitorType, TriggerReason triggerReason) {
        trigger(triggerReason);
        return true;
    }

    @Override
    public void onBackground() {
    }

    @Override
    public void onForeground() {
    }

    public void setHeapDumpListener(HeapDumpListener heapDumpListener) {
        this.heapDumpListener = heapDumpListener;
    }

    public void setHeapDumper(HeapDumper heapDumper) {
        this.heapDumper = heapDumper;
    }

    @Override
    public void startTrack() {
        this.monitorManager.start();
        this.monitorManager.setTriggerListener(new MonitorTriggerListener() {
            @Override
            public final boolean onTrigger(MonitorType monitorType, TriggerReason triggerReason) {
                return this.f$0.lambda$startTrack$0$HeapDumpTrigger(monitorType, triggerReason);
            }
        });
    }

    @Override
    public void stopTrack() {
        this.monitorManager.stop();
    }

    @Override
    public KTriggerStrategy strategy() {
        return KTriggerStrategy.RIGHT_NOW;
    }

    @Override
    public void trigger(TriggerReason triggerReason) {
        if (this.triggered) {
            KLog.e("HeapDumpTrigger", "Only once trigger!");
            return;
        }
        this.triggered = true;
        this.monitorManager.stop();
        KLog.i("HeapDumpTrigger", "trigger reason:" + triggerReason.dumpReason);
        HeapDumpListener heapDumpListener = this.heapDumpListener;
        if (heapDumpListener != null) {
            heapDumpListener.onHeapDumpTrigger(triggerReason.dumpReason);
        }
        try {
            doHeapDump(triggerReason.dumpReason);
        } catch (Exception e) {
            KLog.e("HeapDumpTrigger", "doHeapDump failed");
            e.printStackTrace();
            HeapDumpListener heapDumpListener2 = this.heapDumpListener;
            if (heapDumpListener2 != null) {
                heapDumpListener2.onHeapDumpFailed();
            }
        }
        KVData.addTriggerTime(KGlobalConfig.getRunningInfoFetcher().appVersion());
    }
}
