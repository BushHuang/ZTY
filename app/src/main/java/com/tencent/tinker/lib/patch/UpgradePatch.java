package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.os.Build;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UpgradePatch extends AbstractPatch {
    private static final String TAG = "Tinker.UpgradePatch";

    @Override
    public boolean tryPatch(Context context, String str, PatchResult patchResult) throws Throwable {
        File file;
        File file2;
        String str2;
        SharePatchInfo sharePatchInfo;
        Tinker tinkerWith = Tinker.with(context);
        File file3 = new File(str);
        if (!tinkerWith.isTinkerEnabled() || !ShareTinkerInternals.isTinkerEnableWithSharedPreferences(context)) {
            TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:patch is disabled, just return", new Object[0]);
            return false;
        }
        if (!SharePatchFileUtil.isLegalFile(file3)) {
            TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:patch file is not found, just return", new Object[0]);
            return false;
        }
        ShareSecurityCheck shareSecurityCheck = new ShareSecurityCheck(context);
        int iCheckTinkerPackage = ShareTinkerInternals.checkTinkerPackage(context, tinkerWith.getTinkerFlags(), file3, shareSecurityCheck);
        if (iCheckTinkerPackage != 0) {
            TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:onPatchPackageCheckFail", new Object[0]);
            tinkerWith.getPatchReporter().onPatchPackageCheckFail(file3, iCheckTinkerPackage);
            return false;
        }
        String md5 = SharePatchFileUtil.getMD5(file3);
        if (md5 == null) {
            TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:patch md5 is null, just return", new Object[0]);
            return false;
        }
        patchResult.patchVersion = md5;
        TinkerLog.i("Tinker.UpgradePatch", "UpgradePatch tryPatch:patchMd5:%s", md5);
        String absolutePath = tinkerWith.getPatchDirectory().getAbsolutePath();
        File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(absolutePath);
        File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(absolutePath);
        HashMap<String, String> packagePropertiesIfPresent = shareSecurityCheck.getPackagePropertiesIfPresent();
        if (packagePropertiesIfPresent == null) {
            TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch packageProperties is null, do we process a valid patch apk ?", new Object[0]);
            return false;
        }
        String str3 = packagePropertiesIfPresent.get("is_protected_app");
        boolean z = (str3 == null || str3.isEmpty() || "0".equals(str3)) ? false : true;
        SharePatchInfo andCheckPropertyWithLock = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
        if (andCheckPropertyWithLock == null) {
            file = patchInfoFile;
            file2 = patchInfoLockFile;
            str2 = absolutePath;
            sharePatchInfo = new SharePatchInfo("", md5, z, false, Build.FINGERPRINT, "odex", false);
        } else {
            if (andCheckPropertyWithLock.oldVersion == null || andCheckPropertyWithLock.newVersion == null || andCheckPropertyWithLock.oatDir == null) {
                TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:onPatchInfoCorrupted", new Object[0]);
                tinkerWith.getPatchReporter().onPatchInfoCorrupted(file3, andCheckPropertyWithLock.oldVersion, andCheckPropertyWithLock.newVersion);
                return false;
            }
            if (!SharePatchFileUtil.checkIfMd5Valid(md5)) {
                TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:onPatchVersionCheckFail md5 %s is valid", md5);
                tinkerWith.getPatchReporter().onPatchVersionCheckFail(file3, andCheckPropertyWithLock, md5);
                return false;
            }
            boolean zEquals = andCheckPropertyWithLock.oatDir.equals("interpet");
            if (!zEquals && !ShareTinkerInternals.isNullOrNil(andCheckPropertyWithLock.newVersion) && andCheckPropertyWithLock.newVersion.equals(md5) && !andCheckPropertyWithLock.isRemoveNewVersion) {
                TinkerLog.e("Tinker.UpgradePatch", "patch already applied, md5: %s", md5);
                UpgradePatchRetry.getInstance(context).onPatchResetMaxCheck(md5);
                return true;
            }
            file = patchInfoFile;
            file2 = patchInfoLockFile;
            str2 = absolutePath;
            sharePatchInfo = new SharePatchInfo(andCheckPropertyWithLock.oldVersion, md5, z, false, Build.FINGERPRINT, zEquals ? "changing" : andCheckPropertyWithLock.oatDir, false);
        }
        String str4 = str2 + "/" + SharePatchFileUtil.getPatchVersionDirectory(md5);
        TinkerLog.i("Tinker.UpgradePatch", "UpgradePatch tryPatch:patchVersionDirectory:%s", str4);
        File file4 = new File(str4 + "/" + SharePatchFileUtil.getPatchVersionFile(md5));
        try {
            if (!md5.equals(SharePatchFileUtil.getMD5(file4))) {
                SharePatchFileUtil.copyFileUsingStream(file3, file4);
                TinkerLog.w("Tinker.UpgradePatch", "UpgradePatch copy patch file, src file: %s size: %d, dest file: %s size:%d", file3.getAbsolutePath(), Long.valueOf(file3.length()), file4.getAbsolutePath(), Long.valueOf(file4.length()));
            }
            if (!DexDiffPatchInternal.tryRecoverDexFiles(tinkerWith, shareSecurityCheck, context, str4, file4)) {
                TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, try patch dex failed", new Object[0]);
                return false;
            }
            if (!ArkHotDiffPatchInternal.tryRecoverArkHotLibrary(tinkerWith, shareSecurityCheck, context, str4, file4)) {
                return false;
            }
            if (!BsDiffPatchInternal.tryRecoverLibraryFiles(tinkerWith, shareSecurityCheck, context, str4, file4)) {
                TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, try patch library failed", new Object[0]);
                return false;
            }
            if (!ResDiffPatchInternal.tryRecoverResourceFiles(tinkerWith, shareSecurityCheck, context, str4, file4)) {
                TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, try patch resource failed", new Object[0]);
                return false;
            }
            if (!DexDiffPatchInternal.waitAndCheckDexOptFile(file3, tinkerWith)) {
                TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, check dex opt file failed", new Object[0]);
                return false;
            }
            if (SharePatchInfo.rewritePatchInfoFileWithLock(file, sharePatchInfo, file2)) {
                UpgradePatchRetry.getInstance(context).onPatchResetMaxCheck(md5);
                TinkerLog.w("Tinker.UpgradePatch", "UpgradePatch tryPatch: done, it is ok", new Object[0]);
                return true;
            }
            TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, rewrite patch info failed", new Object[0]);
            tinkerWith.getPatchReporter().onPatchInfoCorrupted(file3, sharePatchInfo.oldVersion, sharePatchInfo.newVersion);
            return false;
        } catch (IOException unused) {
            TinkerLog.e("Tinker.UpgradePatch", "UpgradePatch tryPatch:copy patch file fail from %s to %s", file3.getPath(), file4.getPath());
            tinkerWith.getPatchReporter().onPatchTypeExtractFail(file3, file4, file3.getName(), 1);
            return false;
        }
    }
}
