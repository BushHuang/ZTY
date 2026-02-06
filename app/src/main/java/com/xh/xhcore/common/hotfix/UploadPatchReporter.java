package com.xh.xhcore.common.hotfix;

import android.content.Context;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.hotfix.bury.HotfixBuryManager;
import com.xh.xhcore.common.hotfix.tinker.reporter.SamplePatchReporter;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\u000e\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0012\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J.\u0010\u001a\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001d\u001a\u00020\u0014H\u0016J&\u0010\u001e\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u0010H\u0016¨\u0006\""}, d2 = {"Lcom/xh/xhcore/common/hotfix/UploadPatchReporter;", "Lcom/xh/xhcore/common/hotfix/tinker/reporter/SamplePatchReporter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onPatchDexOptFail", "", "patchFile", "Ljava/io/File;", "dexFiles", "", "throwable", "", "onPatchException", "onPatchInfoCorrupted", "oldVersion", "", "newVersion", "onPatchPackageCheckFail", "errorCode", "", "onPatchResult", "success", "", "cost", "", "onPatchTypeExtractFail", "extractTo", "filename", "fileType", "onPatchVersionCheckFail", "oldPatchInfo", "Lcom/tencent/tinker/loader/shareutil/SharePatchInfo;", "patchFileVersion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UploadPatchReporter extends SamplePatchReporter {
    public UploadPatchReporter(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public void onPatchDexOptFail(File patchFile, List<File> dexFiles, Throwable throwable) {
        super.onPatchDexOptFail(patchFile, dexFiles, throwable);
        LogManager.getInstance().uploadLog("errorTag=onPatchDexOptFailTinker\n" + Intrinsics.stringPlus("stack=", LogUtils.INSTANCE.getStackTraceString(throwable)));
    }

    @Override
    public void onPatchException(File patchFile, Throwable throwable) {
        super.onPatchException(patchFile, throwable);
        LogManager.getInstance().uploadLog("errorTag=onPatchExceptionTinker\n" + Intrinsics.stringPlus("stack=", LogUtils.INSTANCE.getStackTraceString(throwable)));
    }

    @Override
    public void onPatchInfoCorrupted(File patchFile, String oldVersion, String newVersion) {
        super.onPatchInfoCorrupted(patchFile, oldVersion, newVersion);
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onPatchInfoCorruptedTinker\n");
        sb.append("stack=" + LogUtils.INSTANCE.getStackTraceString(new Throwable()) + '\n');
        StringBuilder sb2 = new StringBuilder();
        sb2.append("params={patchFile: ");
        sb2.append((Object) (patchFile == null ? null : patchFile.getAbsolutePath()));
        sb2.append(", oldVersion: ");
        sb2.append((Object) oldVersion);
        sb2.append(", newVersion: ");
        sb2.append((Object) newVersion);
        sb2.append('}');
        sb.append(sb2.toString());
        logManager.uploadLog(sb.toString());
    }

    @Override
    public void onPatchPackageCheckFail(File patchFile, int errorCode) {
        super.onPatchPackageCheckFail(patchFile, errorCode);
        if (errorCode != 0) {
            LogManager logManager = LogManager.getInstance();
            StringBuilder sb = new StringBuilder("errorTag=onPatchPackageCheckFailTinker\n");
            sb.append("stack=" + LogUtils.INSTANCE.getStackTraceString(new Throwable()) + '\n');
            StringBuilder sb2 = new StringBuilder();
            sb2.append("params={patchFile: ");
            sb2.append((Object) (patchFile == null ? null : patchFile.getAbsolutePath()));
            sb2.append(", errorCode: ");
            sb2.append(errorCode);
            sb2.append('}');
            sb.append(sb2.toString());
            logManager.uploadLog(sb.toString());
        }
    }

    @Override
    public void onPatchResult(File patchFile, boolean success, long cost) {
        super.onPatchResult(patchFile, success, cost);
        if (success) {
            HotfixBuryManager.INSTANCE.onPatchSuccess();
        }
    }

    @Override
    public void onPatchTypeExtractFail(File patchFile, File extractTo, String filename, int fileType) {
        super.onPatchTypeExtractFail(patchFile, extractTo, filename, fileType);
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onPatchTypeExtractFailTinker\n");
        sb.append("stack=" + LogUtils.INSTANCE.getStackTraceString(new Throwable()) + '\n');
        StringBuilder sb2 = new StringBuilder();
        sb2.append("params={patchFile: ");
        sb2.append((Object) (patchFile == null ? null : patchFile.getAbsolutePath()));
        sb2.append(", extractTo: ");
        sb2.append((Object) (extractTo != null ? extractTo.getAbsolutePath() : null));
        sb2.append(", filename: ");
        sb2.append((Object) filename);
        sb2.append(" , fileType: ");
        sb2.append((Object) ShareTinkerInternals.getTypeString(fileType));
        sb2.append('}');
        sb.append(sb2.toString());
        logManager.uploadLog(sb.toString());
    }

    @Override
    public void onPatchVersionCheckFail(File patchFile, SharePatchInfo oldPatchInfo, String patchFileVersion) {
        super.onPatchVersionCheckFail(patchFile, oldPatchInfo, patchFileVersion);
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onPatchVersionCheckFailTinker\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("params={patchFile: ");
        sb2.append((Object) (patchFile == null ? null : patchFile.getAbsolutePath()));
        sb2.append(", oldPatchInfo: ");
        sb2.append(UploadPatchReporterKt.toString(oldPatchInfo));
        sb2.append(", patchFileVersion: ");
        sb2.append((Object) patchFileVersion);
        sb2.append('}');
        sb.append(sb2.toString());
        logManager.uploadLog(sb.toString());
    }
}
