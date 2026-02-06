package com.kwai.koom.javaoom.analysis;

import android.util.Pair;
import com.kwai.koom.javaoom.common.KHeapFile;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.report.HeapAnalyzeReporter;
import java.util.List;
import kshark.ApplicationLeak;
import kshark.LibraryLeak;

class KHeapAnalyzer {
    private static final String TAG = "HeapAnalyzer";
    private SuspicionLeaksFinder leaksFinder;

    public KHeapAnalyzer(KHeapFile kHeapFile) {
        this.leaksFinder = new SuspicionLeaksFinder(kHeapFile.hprof);
    }

    public boolean analyze() throws Throwable {
        KLog.i("HeapAnalyzer", "analyze");
        Pair<List<ApplicationLeak>, List<LibraryLeak>> pairFind = this.leaksFinder.find();
        if (pairFind == null) {
            return false;
        }
        HeapAnalyzeReporter.addGCPath(pairFind, this.leaksFinder.leakReasonTable);
        HeapAnalyzeReporter.done();
        return true;
    }
}
