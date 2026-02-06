package com.kwai.koom.javaoom.report;

import android.os.Build;
import android.os.Debug;
import android.util.Pair;
import com.google.gson.Gson;
import com.kwai.koom.javaoom.analysis.LeakDetector;
import com.kwai.koom.javaoom.common.KConstants;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KHeapFile;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.common.KUtils;
import com.kwai.koom.javaoom.monitor.TriggerReason;
import com.kwai.koom.javaoom.report.HeapReport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kshark.ApplicationLeak;
import kshark.Leak;
import kshark.LeakTrace;
import kshark.LeakTraceObject;
import kshark.LeakTraceReference;
import kshark.LibraryLeak;

public class HeapAnalyzeReporter {
    private static final String TAG = "HeapAnalyzeReporter";
    private static HeapAnalyzeReporter instance;
    private HeapReport heapReport;
    private Gson gson = new Gson();
    private File reportFile = KHeapFile.getKHeapFile().report.file();

    public HeapAnalyzeReporter() throws Throwable {
        HeapReport heapReportLoadFile = loadFile();
        this.heapReport = heapReportLoadFile;
        if (heapReportLoadFile == null) {
            this.heapReport = new HeapReport();
        }
    }

    public static void addAnalysisReason(TriggerReason.AnalysisReason analysisReason) throws Throwable {
        getInstance().addAnalysisReasonInternal(analysisReason);
    }

    private void addAnalysisReasonInternal(TriggerReason.AnalysisReason analysisReason) throws Throwable {
        getRunningInfo().analysisReason = analysisReason.name();
        flushFile();
    }

    public static void addClassInfo(List<LeakDetector> list) {
        getInstance().addClassInfoInternal(list);
    }

    private void addClassInfoInternal(List<LeakDetector> list) throws Throwable {
        KLog.i("HeapAnalyzeReporter", "addClassInfoInternal");
        this.heapReport.classInfos = new ArrayList();
        for (LeakDetector leakDetector : list) {
            HeapReport.ClassInfo classInfo = new HeapReport.ClassInfo();
            classInfo.className = leakDetector.className();
            classInfo.instanceCount = Integer.valueOf(leakDetector.instanceCount().instancesCount);
            classInfo.leakInstanceCount = Integer.valueOf(leakDetector.instanceCount().leakInstancesCount);
            this.heapReport.classInfos.add(classInfo);
            KLog.i("HeapAnalyzeReporter", "class:" + classInfo.className + " all instances:" + classInfo.instanceCount + ", leaked instances:" + classInfo.leakInstanceCount);
        }
        flushFile();
    }

    public static void addDeviceRunningInfo() throws Throwable {
        getInstance().addRunningInfoInternal();
    }

    public static void addDumpReason(TriggerReason.DumpReason dumpReason) throws Throwable {
        getInstance().addDumpReasonInternal(dumpReason);
    }

    private void addDumpReasonInternal(TriggerReason.DumpReason dumpReason) throws Throwable {
        getRunningInfo().dumpReason = dumpReason.name();
        flushFile();
    }

    public static void addGCPath(Pair<List<ApplicationLeak>, List<LibraryLeak>> pair, Map<Long, String> map) throws Throwable {
        getInstance().addGCPathInternal(pair, map);
    }

    private void addGCPathInternal(Pair<List<ApplicationLeak>, List<LibraryLeak>> pair, Map<Long, String> map) throws Throwable {
        if (this.heapReport.gcPaths == null) {
            this.heapReport.gcPaths = new ArrayList();
        }
        addLeaks((List) pair.first, map);
        addLeaks((List) pair.second, map);
        flushFile();
    }

    private <T extends Leak> void addLeaks(List<T> list, Map<Long, String> map) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("add ");
        sb.append(list.get(0) instanceof ApplicationLeak ? "ApplicationLeak " : "LibraryLeak ");
        sb.append(list.size());
        sb.append(" leaks");
        KLog.i("HeapAnalyzeReporter", sb.toString());
        for (T t : list) {
            HeapReport.GCPath gCPath = new HeapReport.GCPath();
            this.heapReport.gcPaths.add(gCPath);
            gCPath.signature = t.getSignature();
            gCPath.instanceCount = Integer.valueOf(t.getLeakTraces().size());
            LeakTrace leakTrace = t.getLeakTraces().get(0);
            String description = leakTrace.getGcRootType().getDescription();
            gCPath.gcRoot = description;
            LeakTraceObject leakingObject = leakTrace.getLeakingObject();
            String className = leakingObject.getClassName();
            String typeName = leakingObject.getTypeName();
            KLog.i("HeapAnalyzeReporter", "GC Root:" + description + ", leakObjClazz:" + className + ", leakObjType:" + typeName + ", leaking reason:" + leakingObject.getLeakingStatusReason() + ", leaking id:" + (leakingObject.getObjectId() & 4294967295L));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(map.get(Long.valueOf(leakingObject.getObjectId())));
            sb2.append(t instanceof ApplicationLeak ? "" : " " + leakingObject.getLeakingStatusReason());
            gCPath.leakReason = sb2.toString();
            gCPath.path = new ArrayList();
            HeapReport.GCPath.PathItem pathItem = new HeapReport.GCPath.PathItem();
            pathItem.reference = className;
            pathItem.referenceType = typeName;
            for (LeakTraceReference leakTraceReference : leakTrace.getReferencePath()) {
                String referenceName = leakTraceReference.getReferenceName();
                String className2 = leakTraceReference.getOriginObject().getClassName();
                String referenceDisplayName = leakTraceReference.getReferenceDisplayName();
                String referenceGenericName = leakTraceReference.getReferenceGenericName();
                String string = leakTraceReference.getReferenceType().toString();
                String declaredClassName = leakTraceReference.getDeclaredClassName();
                KLog.i("HeapAnalyzeReporter", "clazz:" + className2 + ", referenceName:" + referenceName + ", referenceDisplayName:" + referenceDisplayName + ", referenceGenericName:" + referenceGenericName + ", referenceType:" + string + ", declaredClassName:" + declaredClassName);
                HeapReport.GCPath.PathItem pathItem2 = new HeapReport.GCPath.PathItem();
                if (!referenceDisplayName.startsWith("[")) {
                    className2 = className2 + "." + referenceDisplayName;
                }
                pathItem2.reference = className2;
                pathItem2.referenceType = string;
                pathItem2.declaredClass = declaredClassName;
                gCPath.path.add(pathItem2);
            }
            gCPath.path.add(pathItem);
        }
    }

    private void addRunningInfoInternal() throws Throwable {
        KLog.i("HeapAnalyzeReporter", "addRunningInfoInternal");
        HeapReport.RunningInfo runningInfo = getRunningInfo();
        runningInfo.buildModel = Build.MODEL;
        runningInfo.manufacture = Build.MANUFACTURER;
        runningInfo.sdkInt = Integer.valueOf(Build.VERSION.SDK_INT);
        runningInfo.usageSeconds = KGlobalConfig.getRunningInfoFetcher().usageSeconds();
        runningInfo.currentPage = KGlobalConfig.getRunningInfoFetcher().currentPage();
        runningInfo.appVersion = KGlobalConfig.getRunningInfoFetcher().appVersion();
        runningInfo.nowTime = KUtils.getTimeStamp();
        runningInfo.jvmMax = Integer.valueOf((int) (Runtime.getRuntime().maxMemory() / KConstants.Bytes.MB));
        runningInfo.jvmUsed = Integer.valueOf((int) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / KConstants.Bytes.MB));
        runningInfo.pss = Integer.valueOf((int) (Debug.getPss() / KConstants.Bytes.KB));
        KUtils.ProcessStatus processMemoryUsage = KUtils.getProcessMemoryUsage();
        runningInfo.vss = Integer.valueOf((int) (processMemoryUsage.vssKbSize / KConstants.Bytes.KB));
        runningInfo.rss = Integer.valueOf((int) (processMemoryUsage.rssKbSize / KConstants.Bytes.KB));
        runningInfo.threadCount = Integer.valueOf(processMemoryUsage.threadsCount);
        runningInfo.koomVersion = Integer.valueOf(KConstants.KOOMVersion.CODE);
        this.heapReport.runningInfo = runningInfo;
        flushFile();
    }

    public static void done() throws Throwable {
        getInstance().doneInternal();
    }

    private void doneInternal() throws Throwable {
        this.heapReport.analysisDone = true;
        flushFile();
    }

    private void flushFile() throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        IOException e;
        String json;
        try {
            try {
                json = this.gson.toJson(this.heapReport);
                fileOutputStream = new FileOutputStream(this.reportFile);
            } catch (IOException e2) {
                fileOutputStream = null;
                e = e2;
            } catch (Throwable th2) {
                fileOutputStream = null;
                th = th2;
                KUtils.closeQuietly(fileOutputStream);
                throw th;
            }
            try {
                KLog.i("HeapAnalyzeReporter", "flushFile " + this.reportFile.getPath() + " str:" + json);
                fileOutputStream.write(json.getBytes());
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                KUtils.closeQuietly(fileOutputStream);
            }
            KUtils.closeQuietly(fileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            KUtils.closeQuietly(fileOutputStream);
            throw th;
        }
    }

    private static HeapAnalyzeReporter getInstance() {
        HeapAnalyzeReporter heapAnalyzeReporter = instance;
        if (heapAnalyzeReporter != null) {
            return heapAnalyzeReporter;
        }
        HeapAnalyzeReporter heapAnalyzeReporter2 = new HeapAnalyzeReporter();
        instance = heapAnalyzeReporter2;
        return heapAnalyzeReporter2;
    }

    private HeapReport.RunningInfo getRunningInfo() {
        if (this.heapReport.runningInfo != null) {
            return this.heapReport.runningInfo;
        }
        HeapReport heapReport = this.heapReport;
        HeapReport.RunningInfo runningInfo = new HeapReport.RunningInfo();
        heapReport.runningInfo = runningInfo;
        return runningInfo;
    }

    private HeapReport loadFile() throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(this.reportFile);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String str = new String(bArr);
                if (KConstants.Debug.VERBOSE_LOG) {
                    KLog.i("HeapAnalyzeReporter", "loadFile " + this.reportFile.getPath() + " str:" + str);
                }
                HeapReport heapReport = (HeapReport) this.gson.fromJson(str, HeapReport.class);
                KUtils.closeQuietly(fileInputStream);
                return heapReport;
            } catch (IOException unused) {
                fileInputStream2 = fileInputStream;
                KUtils.closeQuietly(fileInputStream2);
                return new HeapReport();
            } catch (Throwable th2) {
                th = th2;
                KUtils.closeQuietly(fileInputStream);
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
        }
    }

    private void reAnalysisInternal() throws Throwable {
        KLog.i("HeapAnalyzeReporter", "reAnalysisInternal");
        HeapReport heapReport = this.heapReport;
        heapReport.reAnalysisTimes = Integer.valueOf(heapReport.reAnalysisTimes != null ? 1 + this.heapReport.reAnalysisTimes.intValue() : 1);
        flushFile();
    }

    public static void recordReanalysis() throws Throwable {
        getInstance().reAnalysisInternal();
    }
}
