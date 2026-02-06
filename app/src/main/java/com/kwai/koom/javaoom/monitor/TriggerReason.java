package com.kwai.koom.javaoom.monitor;

public class TriggerReason {
    private static TriggerReason reason;
    public AnalysisReason analysisReason;
    public DumpReason dumpReason;

    public enum AnalysisReason {
        RIGHT_NOW,
        REANALYSIS,
        TEST
    }

    public enum DumpReason {
        MANUAL_TRIGGER,
        MANUAL_TRIGGER_ON_CRASH,
        HEAP_OVER_THRESHOLD,
        HEAP_THRASHING_HEAVILY,
        HEAP_OOM_CRASH,
        FD_OVER_THRESHOLD,
        FD_OOM_CRASH,
        THREAD_OVER_THRESHOLD,
        THREAD_OOM_CRASH
    }

    public static TriggerReason analysisReason(AnalysisReason analysisReason) {
        getTriggerReason().analysisReason = analysisReason;
        return reason;
    }

    public static TriggerReason dumpReason(DumpReason dumpReason) {
        getTriggerReason().dumpReason = dumpReason;
        return reason;
    }

    private static TriggerReason getTriggerReason() {
        if (reason == null) {
            reason = new TriggerReason();
        }
        return reason;
    }
}
