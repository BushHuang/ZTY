package com.xh.xhcore.common.hotfix.tinker.reporter;

import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import java.io.File;

public class SampleLoadReporter extends DefaultLoadReporter {
    private static final String TAG = "Tinker.SampleLoadReporter";

    public SampleLoadReporter(Context context) {
        super(context);
    }

    public boolean lambda$onLoadResult$0$SampleLoadReporter() {
        if (!UpgradePatchRetry.getInstance(this.context).onPatchRetryLoad()) {
            return false;
        }
        SampleTinkerReport.onReportRetryPatch();
        return false;
    }

    @Override
    public void onLoadException(Throwable th, int i) throws Throwable {
        super.onLoadException(th, i);
        SampleTinkerReport.onLoadException(th, i);
    }

    @Override
    public void onLoadFileMd5Mismatch(File file, int i) {
        super.onLoadFileMd5Mismatch(file, i);
        SampleTinkerReport.onLoadFileMisMatch(i);
    }

    @Override
    public void onLoadFileNotFound(File file, int i, boolean z) {
        super.onLoadFileNotFound(file, i, z);
        SampleTinkerReport.onLoadFileNotFound(i);
    }

    @Override
    public void onLoadInterpret(int i, Throwable th) {
        super.onLoadInterpret(i, th);
        SampleTinkerReport.onLoadInterpretReport(i, th);
    }

    @Override
    public void onLoadPackageCheckFail(File file, int i) {
        super.onLoadPackageCheckFail(file, i);
        SampleTinkerReport.onLoadPackageCheckFail(i);
    }

    @Override
    public void onLoadPatchInfoCorrupted(String str, String str2, File file) {
        super.onLoadPatchInfoCorrupted(str, str2, file);
        SampleTinkerReport.onLoadInfoCorrupted();
    }

    @Override
    public void onLoadPatchListenerReceiveFail(File file, int i) {
        super.onLoadPatchListenerReceiveFail(file, i);
        SampleTinkerReport.onTryApplyFail(i);
    }

    @Override
    public void onLoadPatchVersionChanged(String str, String str2, File file, String str3) throws Throwable {
        super.onLoadPatchVersionChanged(str, str2, file, str3);
    }

    @Override
    public void onLoadResult(File file, int i, long j) {
        super.onLoadResult(file, i, j);
        if (i == 0) {
            SampleTinkerReport.onLoaded(j);
        }
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public final boolean queueIdle() {
                return this.f$0.lambda$onLoadResult$0$SampleLoadReporter();
            }
        });
    }
}
