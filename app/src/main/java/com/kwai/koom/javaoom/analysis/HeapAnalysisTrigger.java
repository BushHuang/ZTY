package com.kwai.koom.javaoom.analysis;

import android.app.Application;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.common.KTrigger;
import com.kwai.koom.javaoom.common.KTriggerStrategy;
import com.kwai.koom.javaoom.monitor.TriggerReason;
import com.kwai.koom.javaoom.report.HeapAnalyzeReporter;

public class HeapAnalysisTrigger implements KTrigger {
    private static final String TAG = "HeapAnalysisTrigger";
    private HeapAnalysisListener heapAnalysisListener;
    private volatile boolean isForeground;
    private TriggerReason reTriggerReason;
    private KTriggerStrategy strategy;
    private boolean triggered;

    public void doAnalysis(Application application) {
        HeapAnalyzeService.runAnalysis(application, this.heapAnalysisListener);
    }

    @Override
    public void onBackground() {
        KLog.i("HeapAnalysisTrigger", "onBackground");
        this.isForeground = false;
    }

    @Override
    public void onForeground() throws Throwable {
        KLog.i("HeapAnalysisTrigger", "onForeground");
        this.isForeground = true;
        TriggerReason triggerReason = this.reTriggerReason;
        if (triggerReason != null) {
            this.reTriggerReason = null;
            trigger(triggerReason);
        }
    }

    public void setHeapAnalysisListener(HeapAnalysisListener heapAnalysisListener) {
        this.heapAnalysisListener = heapAnalysisListener;
    }

    public void setStrategy(KTriggerStrategy kTriggerStrategy) {
        this.strategy = kTriggerStrategy;
    }

    @Override
    public void startTrack() throws Throwable {
        if (strategy() == KTriggerStrategy.RIGHT_NOW) {
            trigger(TriggerReason.analysisReason(TriggerReason.AnalysisReason.RIGHT_NOW));
        }
    }

    @Override
    public void stopTrack() {
    }

    @Override
    public KTriggerStrategy strategy() {
        KTriggerStrategy kTriggerStrategy = this.strategy;
        return kTriggerStrategy != null ? kTriggerStrategy : KTriggerStrategy.RIGHT_NOW;
    }

    @Override
    public void trigger(TriggerReason triggerReason) throws Throwable {
        if (!this.isForeground) {
            KLog.i("HeapAnalysisTrigger", "reTrigger when foreground");
            this.reTriggerReason = triggerReason;
            return;
        }
        KLog.i("HeapAnalysisTrigger", "trigger reason:" + triggerReason.analysisReason);
        if (this.triggered) {
            KLog.i("HeapAnalysisTrigger", "Only once trigger!");
            return;
        }
        this.triggered = true;
        HeapAnalyzeReporter.addAnalysisReason(triggerReason.analysisReason);
        if (triggerReason.analysisReason == TriggerReason.AnalysisReason.REANALYSIS) {
            HeapAnalyzeReporter.recordReanalysis();
        }
        HeapAnalysisListener heapAnalysisListener = this.heapAnalysisListener;
        if (heapAnalysisListener != null) {
            heapAnalysisListener.onHeapAnalysisTrigger();
        }
        try {
            doAnalysis(KGlobalConfig.getApplication());
        } catch (Exception e) {
            KLog.e("HeapAnalysisTrigger", "doAnalysis failed");
            e.printStackTrace();
            HeapAnalysisListener heapAnalysisListener2 = this.heapAnalysisListener;
            if (heapAnalysisListener2 != null) {
                heapAnalysisListener2.onHeapAnalyzeFailed();
            }
        }
    }
}
