package com.kwai.koom.javaoom.analysis;

import android.app.Application;
import android.app.IntentService;
import android.content.Intent;
import android.os.Process;
import android.os.ResultReceiver;
import com.kwai.koom.javaoom.analysis.IPCReceiver;
import com.kwai.koom.javaoom.common.KHeapFile;
import com.kwai.koom.javaoom.common.KLog;

public class HeapAnalyzeService extends IntentService {
    static final boolean $assertionsDisabled = false;
    private static final String TAG = "HeapAnalyzeService";
    private KHeapAnalyzer heapAnalyzer;
    private ResultReceiver ipcReceiver;

    public HeapAnalyzeService() {
        super("HeapAnalyzeService");
    }

    public HeapAnalyzeService(String str) {
        super(str);
    }

    private void beforeAnalyze(Intent intent) {
        this.ipcReceiver = (ResultReceiver) intent.getParcelableExtra("receiver");
        KHeapFile kHeapFile = (KHeapFile) intent.getParcelableExtra("heap_file");
        KHeapFile.buildInstance(kHeapFile);
        this.heapAnalyzer = new KHeapAnalyzer(kHeapFile);
    }

    private static IPCReceiver buildAnalysisReceiver(final HeapAnalysisListener heapAnalysisListener) {
        return new IPCReceiver(new IPCReceiver.ReceiverCallback() {
            @Override
            public void onError() {
                KLog.i("HeapAnalyzeService", "IPC call back, heap analysis failed");
                heapAnalysisListener.onHeapAnalyzeFailed();
            }

            @Override
            public void onSuccess() {
                KLog.i("HeapAnalyzeService", "IPC call back, heap analysis success");
                heapAnalysisListener.onHeapAnalyzed();
            }
        });
    }

    private boolean doAnalyze() {
        return this.heapAnalyzer.analyze();
    }

    public static void runAnalysis(Application application, HeapAnalysisListener heapAnalysisListener) {
        KLog.i("HeapAnalyzeService", "runAnalysis startService");
        Intent intent = new Intent(application, (Class<?>) HeapAnalyzeService.class);
        intent.putExtra("receiver", buildAnalysisReceiver(heapAnalysisListener));
        intent.putExtra("heap_file", KHeapFile.getKHeapFile());
        application.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean zDoAnalyze;
        KLog.i("HeapAnalyzeService", "start analyze pid:" + Process.myPid());
        try {
            beforeAnalyze(intent);
            zDoAnalyze = doAnalyze();
        } catch (Throwable th) {
            th.printStackTrace();
            zDoAnalyze = false;
        }
        ResultReceiver resultReceiver = this.ipcReceiver;
        if (resultReceiver != null) {
            resultReceiver.send(zDoAnalyze ? 1001 : 1002, null);
        }
    }
}
