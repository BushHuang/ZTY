package com.kwai.koom.javaoom;

import android.app.Application;
import com.kwai.koom.javaoom.analysis.HeapAnalysisTrigger;
import com.kwai.koom.javaoom.common.KConfig;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.dump.HeapDumpTrigger;
import com.kwai.koom.javaoom.report.HeapReportUploader;
import com.kwai.koom.javaoom.report.HprofUploader;

public class KOOM {
    private static final String TAG = "koom";
    private static boolean inited;
    private static KOOM koom;
    private KOOMInternal internal;

    private KOOM() {
    }

    private KOOM(Application application) {
        if (!inited) {
            init(application);
        }
        this.internal = new KOOMInternal(application);
    }

    public static KOOM getInstance() {
        return koom;
    }

    public static void init(Application application) {
        KLog.init(new KLog.DefaultLogger());
        if (inited) {
            KLog.i("koom", "already init!");
            return;
        }
        inited = true;
        if (koom == null) {
            koom = new KOOM(application);
        }
        koom.start();
    }

    public String getHprofDir() {
        return this.internal.getHprofDir();
    }

    public String getReportDir() {
        return this.internal.getReportDir();
    }

    public void manualTrigger() {
        this.internal.manualTrigger();
    }

    public void manualTriggerOnCrash() {
        this.internal.manualTriggerOnCrash();
    }

    public void setHeapAnalysisTrigger(HeapAnalysisTrigger heapAnalysisTrigger) {
        this.internal.setHeapAnalysisTrigger(heapAnalysisTrigger);
    }

    public void setHeapDumpTrigger(HeapDumpTrigger heapDumpTrigger) {
        this.internal.setHeapDumpTrigger(heapDumpTrigger);
    }

    public void setHeapReportUploader(HeapReportUploader heapReportUploader) {
        this.internal.setHeapReportUploader(heapReportUploader);
    }

    public void setHprofUploader(HprofUploader hprofUploader) {
        this.internal.setHprofUploader(hprofUploader);
    }

    public void setKConfig(KConfig kConfig) {
        this.internal.setKConfig(kConfig);
    }

    public void setLogger(KLog.KLogger kLogger) {
        KLog.init(kLogger);
    }

    public void setProgressListener(KOOMProgressListener kOOMProgressListener) {
        this.internal.setProgressListener(kOOMProgressListener);
    }

    public boolean setRootDir(String str) {
        return this.internal.setRootDir(str);
    }

    public void start() {
        this.internal.start();
    }

    public void stop() {
        this.internal.stop();
    }
}
