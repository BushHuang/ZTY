package com.kwai.koom.javaoom.analysis;

public interface HeapAnalysisListener {
    void onHeapAnalysisTrigger();

    void onHeapAnalyzeFailed();

    void onHeapAnalyzed();
}
