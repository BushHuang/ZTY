package com.kwai.koom.javaoom;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import com.kwai.koom.javaoom.KOOMEnableChecker;
import com.kwai.koom.javaoom.KOOMProgressListener;
import com.kwai.koom.javaoom.analysis.HeapAnalysisListener;
import com.kwai.koom.javaoom.analysis.HeapAnalysisTrigger;
import com.kwai.koom.javaoom.analysis.ReanalysisChecker;
import com.kwai.koom.javaoom.common.KConfig;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KHeapFile;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.common.KSoLoader;
import com.kwai.koom.javaoom.common.KUtils;
import com.kwai.koom.javaoom.dump.HeapDumpListener;
import com.kwai.koom.javaoom.dump.HeapDumpTrigger;
import com.kwai.koom.javaoom.lifecyle.KLifeCycleObserver;
import com.kwai.koom.javaoom.monitor.TriggerReason;
import com.kwai.koom.javaoom.report.HeapReportUploader;
import com.kwai.koom.javaoom.report.HprofUploader;
import java.io.File;

class KOOMInternal implements HeapDumpListener, HeapAnalysisListener {
    private static final String TAG = "KOOM";
    private HeapAnalysisTrigger heapAnalysisTrigger;
    private HeapDumpTrigger heapDumpTrigger;
    private HeapReportUploader heapReportUploader;
    private HprofUploader hprofUploader;
    private KOOMProgressListener kProgressListener;
    private Handler koomHandler;
    private boolean started;

    private KOOMInternal() {
    }

    public KOOMInternal(Application application) {
        KUtils.startup();
        buildConfig(application);
        this.heapDumpTrigger = new HeapDumpTrigger();
        this.heapAnalysisTrigger = new HeapAnalysisTrigger();
        KLifeCycleObserver.get().addObserver(this.heapAnalysisTrigger);
        KLifeCycleObserver.get().addObserver(this.heapDumpTrigger);
    }

    private void buildConfig(Application application) {
        KGlobalConfig.setApplication(application);
        KGlobalConfig.setKConfig(KConfig.defaultConfig());
    }

    private void manualTriggerInternal() throws Throwable {
        if (!this.started) {
            startInternal();
        }
        if (this.started) {
            this.heapDumpTrigger.trigger(TriggerReason.dumpReason(TriggerReason.DumpReason.MANUAL_TRIGGER));
        }
    }

    private void manualTriggerOnCrashInternal() throws Throwable {
        if (!this.started) {
            startInternal();
        }
        if (this.started) {
            this.heapDumpTrigger.trigger(TriggerReason.dumpReason(TriggerReason.DumpReason.MANUAL_TRIGGER_ON_CRASH));
        }
    }

    private void startInKOOMThread() {
        this.koomHandler.postDelayed(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.startInternal();
            }
        }, 10000L);
    }

    private void startInternal() throws Throwable {
        if (this.started) {
            KLog.i("KOOM", "already started!");
            return;
        }
        this.started = true;
        this.heapDumpTrigger.setHeapDumpListener(this);
        this.heapAnalysisTrigger.setHeapAnalysisListener(this);
        if (KOOMEnableChecker.doCheck() != KOOMEnableChecker.Result.NORMAL) {
            KLog.e("KOOM", "koom start failed, check result: " + KOOMEnableChecker.doCheck());
            return;
        }
        if (new ReanalysisChecker().detectReanalysisFile() == null) {
            this.heapDumpTrigger.startTrack();
        } else {
            KLog.i("KOOM", "detected reanalysis file");
            this.heapAnalysisTrigger.trigger(TriggerReason.analysisReason(TriggerReason.AnalysisReason.REANALYSIS));
        }
    }

    private void uploadFiles(KHeapFile kHeapFile) {
        uploadHprof(kHeapFile.hprof);
        uploadHeapReport(kHeapFile.report);
    }

    private void uploadHeapReport(KHeapFile.Report report) {
        HeapReportUploader heapReportUploader = this.heapReportUploader;
        if (heapReportUploader != null) {
            heapReportUploader.upload(report.file());
        }
        HeapReportUploader heapReportUploader2 = this.heapReportUploader;
        if (heapReportUploader2 == null || !heapReportUploader2.deleteWhenUploaded()) {
            return;
        }
        KLog.i("KOOM", "report delete");
        report.delete();
    }

    private void uploadHprof(KHeapFile.Hprof hprof) {
        HprofUploader hprofUploader = this.hprofUploader;
        if (hprofUploader != null) {
            hprofUploader.upload(hprof.file());
        }
        HprofUploader hprofUploader2 = this.hprofUploader;
        if (hprofUploader2 == null || hprofUploader2.deleteWhenUploaded()) {
            KLog.i("KOOM", "delete " + hprof.path);
            hprof.delete();
        }
    }

    public void changeProgress(KOOMProgressListener.Progress progress) {
        KOOMProgressListener kOOMProgressListener = this.kProgressListener;
        if (kOOMProgressListener != null) {
            kOOMProgressListener.onProgress(progress);
        }
    }

    public String getHprofDir() {
        return KGlobalConfig.getHprofDir();
    }

    public String getReportDir() {
        return KGlobalConfig.getReportDir();
    }

    public void manualTrigger() {
        this.koomHandler.post(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.manualTriggerInternal();
            }
        });
    }

    public void manualTriggerOnCrash() {
        this.koomHandler.post(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.manualTriggerOnCrashInternal();
            }
        });
    }

    @Override
    public void onHeapAnalysisTrigger() {
        KLog.i("KOOM", "onHeapAnalysisTrigger");
        changeProgress(KOOMProgressListener.Progress.HEAP_ANALYSIS_START);
    }

    @Override
    public void onHeapAnalyzeFailed() {
        changeProgress(KOOMProgressListener.Progress.HEAP_ANALYSIS_FAILED);
    }

    @Override
    public void onHeapAnalyzed() {
        KLog.i("KOOM", "onHeapAnalyzed");
        changeProgress(KOOMProgressListener.Progress.HEAP_ANALYSIS_DONE);
        uploadFiles(KHeapFile.getKHeapFile());
    }

    @Override
    public void onHeapDumpFailed() {
        changeProgress(KOOMProgressListener.Progress.HEAP_DUMP_FAILED);
    }

    @Override
    public void onHeapDumpTrigger(TriggerReason.DumpReason dumpReason) {
        KLog.i("KOOM", "onHeapDumpTrigger");
        changeProgress(KOOMProgressListener.Progress.HEAP_DUMP_START);
    }

    @Override
    public void onHeapDumped(TriggerReason.DumpReason dumpReason) throws Throwable {
        KLog.i("KOOM", "onHeapDumped");
        changeProgress(KOOMProgressListener.Progress.HEAP_DUMPED);
        if (dumpReason != TriggerReason.DumpReason.MANUAL_TRIGGER_ON_CRASH) {
            this.heapAnalysisTrigger.startTrack();
        } else {
            KLog.i("KOOM", "reanalysis next launch when trigger on crash");
        }
    }

    public void setHeapAnalysisTrigger(HeapAnalysisTrigger heapAnalysisTrigger) {
        this.heapAnalysisTrigger = heapAnalysisTrigger;
    }

    public void setHeapDumpTrigger(HeapDumpTrigger heapDumpTrigger) {
        this.heapDumpTrigger = heapDumpTrigger;
    }

    public void setHeapReportUploader(HeapReportUploader heapReportUploader) {
        this.heapReportUploader = heapReportUploader;
    }

    public void setHprofUploader(HprofUploader hprofUploader) {
        this.hprofUploader = hprofUploader;
    }

    public void setKConfig(KConfig kConfig) {
        KGlobalConfig.setKConfig(kConfig);
    }

    public void setProgressListener(KOOMProgressListener kOOMProgressListener) {
        this.kProgressListener = kOOMProgressListener;
    }

    public boolean setRootDir(String str) {
        if (!new File(str).exists()) {
            return false;
        }
        KGlobalConfig.setRootDir(str);
        return true;
    }

    public void setSoLoader(KSoLoader kSoLoader) {
        KGlobalConfig.setSoLoader(kSoLoader);
    }

    public void start() {
        HandlerThread handlerThread = new HandlerThread("koom");
        handlerThread.start();
        this.koomHandler = new Handler(handlerThread.getLooper());
        startInKOOMThread();
    }

    public void stop() {
        HeapDumpTrigger heapDumpTrigger = this.heapDumpTrigger;
        if (heapDumpTrigger != null) {
            heapDumpTrigger.stopTrack();
        }
        HeapAnalysisTrigger heapAnalysisTrigger = this.heapAnalysisTrigger;
        if (heapAnalysisTrigger != null) {
            heapAnalysisTrigger.stopTrack();
        }
    }
}
