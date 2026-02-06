package com.tencent.tinker.lib.service;

import android.os.Process;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.File;

public class DefaultTinkerResultService extends AbstractResultService {
    private static final String TAG = "Tinker.DefaultTinkerResultService";

    public boolean checkIfNeedKill(PatchResult patchResult) {
        TinkerLoadResult tinkerLoadResultIfPresent;
        Tinker tinkerWith = Tinker.with(getApplicationContext());
        if (!tinkerWith.isTinkerLoaded() || (tinkerLoadResultIfPresent = tinkerWith.getTinkerLoadResultIfPresent()) == null) {
            return true;
        }
        return patchResult.patchVersion == null || !patchResult.patchVersion.equals(tinkerLoadResultIfPresent.currentVersion);
    }

    public void deleteRawPatchFile(File file) {
        if (SharePatchFileUtil.isLegalFile(file)) {
            TinkerLog.w("Tinker.DefaultTinkerResultService", "deleteRawPatchFile rawFile path: %s", file.getPath());
            String name = file.getName();
            if (!name.startsWith("patch-") || !name.endsWith(".apk")) {
                SharePatchFileUtil.safeDeleteFile(file);
                return;
            }
            File parentFile = file.getParentFile();
            if (!parentFile.getName().startsWith("patch-")) {
                SharePatchFileUtil.safeDeleteFile(file);
            } else {
                if (parentFile.getParentFile().getName().equals("tinker")) {
                    return;
                }
                SharePatchFileUtil.safeDeleteFile(file);
            }
        }
    }

    @Override
    public void onPatchResult(PatchResult patchResult) {
        if (patchResult == null) {
            TinkerLog.e("Tinker.DefaultTinkerResultService", "DefaultTinkerResultService received null result!!!!", new Object[0]);
            return;
        }
        TinkerLog.i("Tinker.DefaultTinkerResultService", "DefaultTinkerResultService received a result:%s ", patchResult.toString());
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());
        if (patchResult.isSuccess) {
            deleteRawPatchFile(new File(patchResult.rawPatchFilePath));
            if (checkIfNeedKill(patchResult)) {
                Process.killProcess(Process.myPid());
            } else {
                TinkerLog.i("Tinker.DefaultTinkerResultService", "I have already install the newly patch version!", new Object[0]);
            }
        }
    }
}
