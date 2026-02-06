package com.kwai.koom.javaoom;

public interface KOOMProgressListener {

    public enum Progress {
        HEAP_DUMP_START,
        HEAP_DUMPED,
        HEAP_DUMP_FAILED,
        HEAP_ANALYSIS_START,
        HEAP_ANALYSIS_DONE,
        HEAP_ANALYSIS_FAILED
    }

    void onProgress(Progress progress);
}
