package com.xh.xhcore.common.hotfix.tinker.reporter;

import android.app.ActivityManager;
import android.content.Context;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.xh.xhcore.common.hotfix.tinker.util.Utils;
import java.io.File;
import java.util.Properties;

public class SamplePatchListener extends DefaultPatchListener {
    protected static final long NEW_PATCH_RESTRICTION_SPACE_SIZE_MIN = 62914560;
    private static final String TAG = "Tinker.SamplePatchListener";
    private final int maxMemory;

    public SamplePatchListener(Context context) {
        super(context);
        this.maxMemory = ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
        TinkerLog.i("Tinker.SamplePatchListener", "application maxMemory:" + this.maxMemory, new Object[0]);
    }

    @Override
    public int patchCheck(String str, String str2) throws Throwable {
        File file = new File(str);
        TinkerLog.i("Tinker.SamplePatchListener", "receive a patch file: %s, file size:%d", str, Long.valueOf(SharePatchFileUtil.getFileOrDirectorySize(file)));
        int iPatchCheck = super.patchCheck(str, str2);
        if (iPatchCheck == 0) {
            iPatchCheck = Utils.checkForPatchRecover(62914560L, this.maxMemory);
        }
        if (iPatchCheck == 0 && this.context.getSharedPreferences("tinker_share_config", 4).getInt(str2, 0) >= 3) {
            iPatchCheck = -23;
        }
        if (iPatchCheck == 0) {
            Properties propertiesFastGetPatchPackageMeta = ShareTinkerInternals.fastGetPatchPackageMeta(file);
            if (propertiesFastGetPatchPackageMeta == null) {
                iPatchCheck = -24;
            } else {
                TinkerLog.i("Tinker.SamplePatchListener", "get platform:" + propertiesFastGetPatchPackageMeta.getProperty("platform"), new Object[0]);
            }
        }
        SampleTinkerReport.onTryApply(iPatchCheck == 0);
        return iPatchCheck;
    }
}
