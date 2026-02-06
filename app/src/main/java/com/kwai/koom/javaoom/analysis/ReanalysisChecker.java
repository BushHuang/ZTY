package com.kwai.koom.javaoom.analysis;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.kwai.koom.javaoom.common.KConstants;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KHeapFile;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.common.KUtils;
import com.kwai.koom.javaoom.report.HeapReport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReanalysisChecker {
    private static final String TAG = "ReanalysisChecker";

    private boolean analysisNotDone(HeapReport heapReport) {
        return heapReport.analysisDone == null || !heapReport.analysisDone.booleanValue();
    }

    private KHeapFile buildKHeapFile(File file) {
        File fileFindHprof = findHprof(getReportFilePrefix(file));
        if (fileFindHprof != null) {
            return KHeapFile.buildInstance(fileFindHprof, file);
        }
        KLog.e("ReanalysisChecker", "Reanalyze hprof file not found!");
        file.delete();
        return null;
    }

    private File findHprof(String str) {
        File[] fileArrListFiles = new File(KGlobalConfig.getHprofDir()).listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        for (File file : fileArrListFiles) {
            if (TextUtils.equals(str, file.getName().substring(0, r6.length() - 6))) {
                return file;
            }
        }
        return null;
    }

    private String getReportFilePrefix(File file) {
        return file.getName().substring(0, r3.length() - 5);
    }

    private HeapReport loadFile(File file) throws Throwable {
        FileInputStream fileInputStream;
        Gson gson = new Gson();
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            String str = new String(bArr);
            if (KConstants.Debug.VERBOSE_LOG) {
                KLog.i("ReanalysisChecker", "loadFile " + file.getPath() + " str:" + str);
            }
            HeapReport heapReport = (HeapReport) gson.fromJson(str, HeapReport.class);
            if (heapReport == null) {
                heapReport = new HeapReport();
            }
            KUtils.closeQuietly(fileInputStream);
            return heapReport;
        } catch (IOException unused2) {
            fileInputStream2 = fileInputStream;
            KUtils.closeQuietly(fileInputStream2);
            return new HeapReport();
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            KUtils.closeQuietly(fileInputStream2);
            throw th;
        }
    }

    private boolean overReanalysisMaxTimes(HeapReport heapReport) {
        return heapReport.reAnalysisTimes != null && heapReport.reAnalysisTimes.intValue() >= KConstants.ReAnalysis.MAX_TIMES;
    }

    public KHeapFile detectReanalysisFile() throws Throwable {
        File[] fileArrListFiles = new File(KGlobalConfig.getReportDir()).listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        for (File file : fileArrListFiles) {
            HeapReport heapReportLoadFile = loadFile(file);
            if (analysisNotDone(heapReportLoadFile)) {
                if (!overReanalysisMaxTimes(heapReportLoadFile)) {
                    KLog.i("ReanalysisChecker", "find reanalyze report");
                    return buildKHeapFile(file);
                }
                KLog.e("ReanalysisChecker", "Reanalyze " + file.getName() + " too many times");
                File fileFindHprof = findHprof(getReportFilePrefix(file));
                if (fileFindHprof != null) {
                    fileFindHprof.delete();
                }
                file.delete();
            }
        }
        return null;
    }
}
