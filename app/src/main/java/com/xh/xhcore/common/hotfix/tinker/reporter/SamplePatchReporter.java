package com.xh.xhcore.common.hotfix.tinker.reporter;

import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import java.io.File;
import java.util.List;

public class SamplePatchReporter extends DefaultPatchReporter {
    private static final String TAG = "Tinker.SamplePatchReporter";

    public SamplePatchReporter(Context context) {
        super(context);
    }

    @Override
    public void onPatchDexOptFail(File file, List<File> list, Throwable th) {
        super.onPatchDexOptFail(file, list, th);
        SampleTinkerReport.onApplyDexOptFail(th);
    }

    @Override
    public void onPatchException(File file, Throwable th) {
        super.onPatchException(file, th);
        SampleTinkerReport.onApplyCrash(th);
    }

    @Override
    public void onPatchInfoCorrupted(File file, String str, String str2) {
        super.onPatchInfoCorrupted(file, str, str2);
        SampleTinkerReport.onApplyInfoCorrupted();
    }

    @Override
    public void onPatchPackageCheckFail(File file, int i) {
        super.onPatchPackageCheckFail(file, i);
        SampleTinkerReport.onApplyPackageCheckFail(i);
    }

    @Override
    public void onPatchResult(File file, boolean z, long j) {
        super.onPatchResult(file, z, j);
        SampleTinkerReport.onApplied(j, z);
    }

    @Override
    public void onPatchServiceStart(Intent intent) throws Throwable {
        super.onPatchServiceStart(intent);
        SampleTinkerReport.onApplyPatchServiceStart();
    }

    @Override
    public void onPatchTypeExtractFail(File file, File file2, String str, int i) {
        super.onPatchTypeExtractFail(file, file2, str, i);
        SampleTinkerReport.onApplyExtractFail(i);
    }

    @Override
    public void onPatchVersionCheckFail(File file, SharePatchInfo sharePatchInfo, String str) {
        super.onPatchVersionCheckFail(file, sharePatchInfo, str);
        SampleTinkerReport.onApplyVersionCheckFail();
    }
}
