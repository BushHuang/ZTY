package com.xh.xhcore.common.hotfix;

import android.content.Context;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.hotfix.bury.HotfixBuryManager;
import com.xh.xhcore.common.hotfix.tinker.reporter.SampleLoadReporter;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001a\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u001a\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u001a\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\nH\u0016J&\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0018\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\"\u0010\u0019\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¨\u0006\u001e"}, d2 = {"Lcom/xh/xhcore/common/hotfix/UploadLoadReporter;", "Lcom/xh/xhcore/common/hotfix/tinker/reporter/SampleLoadReporter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onLoadException", "", "throwable", "", "errorCode", "", "onLoadFileMd5Mismatch", "file", "Ljava/io/File;", "fileType", "onLoadInterpret", "type", "onLoadPackageCheckFail", "patchFile", "onLoadPatchInfoCorrupted", "oldVersion", "", "newVersion", "patchInfoFile", "onLoadPatchListenerReceiveFail", "onLoadResult", "patchDirectory", "loadCode", "cost", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UploadLoadReporter extends SampleLoadReporter {
    public UploadLoadReporter(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public void onLoadException(Throwable throwable, int errorCode) throws Throwable {
        super.onLoadException(throwable, errorCode);
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onLoadExceptionTinker\n");
        sb.append("errorCode=" + errorCode + '\n');
        sb.append(Intrinsics.stringPlus("stack=", LogUtils.INSTANCE.getStackTraceString(throwable)));
        logManager.uploadLog(sb.toString());
    }

    @Override
    public void onLoadFileMd5Mismatch(File file, int fileType) {
        super.onLoadFileMd5Mismatch(file, fileType);
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onLoadFileMd5MismatchTinker\n");
        sb.append("stack=" + LogUtils.INSTANCE.getStackTraceString(new Throwable()) + '\n');
        StringBuilder sb2 = new StringBuilder();
        sb2.append("params={file: ");
        sb2.append((Object) (file == null ? null : file.getAbsolutePath()));
        sb2.append(", fileType: ");
        sb2.append((Object) ShareTinkerInternals.getTypeString(fileType));
        sb.append(sb2.toString());
        logManager.uploadLog(sb.toString());
    }

    @Override
    public void onLoadInterpret(int type, Throwable throwable) {
        super.onLoadInterpret(type, throwable);
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onLoadInterpretTinker\n");
        sb.append("type=" + type + '\n');
        sb.append(Intrinsics.stringPlus("stack=", LogUtils.INSTANCE.getStackTraceString(throwable)));
        logManager.uploadLog(sb.toString());
    }

    @Override
    public void onLoadPackageCheckFail(File patchFile, int errorCode) {
        super.onLoadPackageCheckFail(patchFile, errorCode);
        if (errorCode != 0) {
            LogManager logManager = LogManager.getInstance();
            StringBuilder sb = new StringBuilder("errorTag=onLoadPackageCheckFailTinker\n");
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
    public void onLoadPatchInfoCorrupted(String oldVersion, String newVersion, File patchInfoFile) {
        super.onLoadPatchInfoCorrupted(oldVersion, newVersion, patchInfoFile);
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onLoadPatchInfoCorruptedTinker\n");
        sb.append("stack=" + LogUtils.INSTANCE.getStackTraceString(new Throwable()) + '\n');
        StringBuilder sb2 = new StringBuilder();
        sb2.append("params={patchInfoFile: ");
        sb2.append((Object) (patchInfoFile == null ? null : patchInfoFile.getAbsolutePath()));
        sb2.append(", oldVersion: ");
        sb2.append((Object) oldVersion);
        sb2.append(", newVersion: ");
        sb2.append((Object) newVersion);
        sb2.append('}');
        sb.append(sb2.toString());
        logManager.uploadLog(sb.toString());
    }

    @Override
    public void onLoadPatchListenerReceiveFail(File patchFile, int errorCode) {
        super.onLoadPatchListenerReceiveFail(patchFile, errorCode);
        if (errorCode == 0 || errorCode == -3 || errorCode == -4 || errorCode == -6) {
            return;
        }
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onLoadPatchListenerReceiveFailTinker\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("params={patchFile: ");
        sb2.append((Object) (patchFile == null ? null : patchFile.getAbsolutePath()));
        sb2.append(", errorCode: ");
        sb2.append(errorCode);
        sb2.append('}');
        sb.append(sb2.toString());
        logManager.uploadLog(sb.toString());
    }

    @Override
    public void onLoadResult(File patchDirectory, int loadCode, long cost) {
        super.onLoadResult(patchDirectory, loadCode, cost);
        if (loadCode == 0) {
            HotfixBuryManager.INSTANCE.onApplySuccess();
            return;
        }
        if (loadCode == -3 || loadCode == -2 || loadCode == -1) {
            return;
        }
        LogManager logManager = LogManager.getInstance();
        StringBuilder sb = new StringBuilder("errorTag=onLoadResultTinker\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("params={patchDirectory: ");
        sb2.append((Object) (patchDirectory == null ? null : patchDirectory.getAbsolutePath()));
        sb2.append(", loadCode: ");
        sb2.append(loadCode);
        sb2.append(", cost: ");
        sb2.append(cost);
        sb2.append('}');
        sb.append(sb2.toString());
        logManager.uploadLog(sb.toString());
    }
}
