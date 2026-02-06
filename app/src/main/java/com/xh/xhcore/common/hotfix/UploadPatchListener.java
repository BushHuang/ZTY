package com.xh.xhcore.common.hotfix;

import android.content.Context;
import com.xh.xhcore.common.hotfix.bury.HotfixBuryManager;
import com.xh.xhcore.common.hotfix.tinker.reporter.SamplePatchListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/hotfix/UploadPatchListener;", "Lcom/xh/xhcore/common/hotfix/tinker/reporter/SamplePatchListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onPatchReceived", "", "path", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UploadPatchListener extends SamplePatchListener {
    public UploadPatchListener(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public int onPatchReceived(String path) {
        int iOnPatchReceived = super.onPatchReceived(path);
        HotfixBuryManager.INSTANCE.onDownloadSuccess();
        return iOnPatchReceived;
    }
}
