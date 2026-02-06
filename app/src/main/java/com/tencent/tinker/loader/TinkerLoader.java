package com.tencent.tinker.loader;

import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.hotplug.ComponentHotplug;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class TinkerLoader extends AbstractTinkerLoader {
    private static final String TAG = "Tinker.TinkerLoader";
    private SharePatchInfo patchInfo;

    private boolean checkSafeModeCount(TinkerApplication tinkerApplication) throws Throwable {
        int safeModeCount = ShareTinkerInternals.getSafeModeCount(tinkerApplication);
        if (safeModeCount >= 2) {
            ShareTinkerInternals.setSafeModeCount(tinkerApplication, 0);
            return false;
        }
        tinkerApplication.setUseSafeMode(true);
        ShareTinkerInternals.setSafeModeCount(tinkerApplication, safeModeCount + 1);
        return true;
    }

    private void tryLoadPatchFilesInternal(TinkerApplication tinkerApplication, Intent intent) throws Throwable {
        String str;
        ShareSecurityCheck shareSecurityCheck;
        boolean z;
        int i;
        boolean z2;
        String str2;
        int tinkerFlags = tinkerApplication.getTinkerFlags();
        if (!ShareTinkerInternals.isTinkerEnabled(tinkerFlags)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles: tinker is disable, just return");
            ShareIntentUtil.setIntentReturnCode(intent, -1);
            return;
        }
        if (ShareTinkerInternals.isInPatchProcess(tinkerApplication)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles: we don't load patch with :patch process itself, just return");
            ShareIntentUtil.setIntentReturnCode(intent, -1);
            return;
        }
        File patchDirectory = SharePatchFileUtil.getPatchDirectory(tinkerApplication);
        if (patchDirectory == null) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:getPatchDirectory == null");
            ShareIntentUtil.setIntentReturnCode(intent, -2);
            return;
        }
        String absolutePath = patchDirectory.getAbsolutePath();
        if (!patchDirectory.exists()) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:patch dir not exist:" + absolutePath);
            ShareIntentUtil.setIntentReturnCode(intent, -2);
            return;
        }
        File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(absolutePath);
        if (!patchInfoFile.exists()) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:patch info not exist:" + patchInfoFile.getAbsolutePath());
            ShareIntentUtil.setIntentReturnCode(intent, -3);
            return;
        }
        File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(absolutePath);
        SharePatchInfo andCheckPropertyWithLock = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
        this.patchInfo = andCheckPropertyWithLock;
        if (andCheckPropertyWithLock == null) {
            ShareIntentUtil.setIntentReturnCode(intent, -4);
            return;
        }
        boolean z3 = andCheckPropertyWithLock.isProtectedApp;
        intent.putExtra("intent_is_protected_app", z3);
        String str3 = this.patchInfo.oldVersion;
        String str4 = this.patchInfo.newVersion;
        String str5 = this.patchInfo.oatDir;
        if (str3 == null || str4 == null || str5 == null) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchInfoCorrupted");
            ShareIntentUtil.setIntentReturnCode(intent, -4);
            return;
        }
        boolean zIsInMainProcess = ShareTinkerInternals.isInMainProcess(tinkerApplication);
        boolean z4 = this.patchInfo.isRemoveNewVersion;
        if (zIsInMainProcess) {
            String patchVersionDirectory = SharePatchFileUtil.getPatchVersionDirectory(str4);
            if (z4) {
                Log.w("Tinker.TinkerLoader", "found clean patch mark and we are in main process, delete patch file now.");
                if (patchVersionDirectory != null) {
                    boolean zEquals = str3.equals(str4);
                    if (zEquals) {
                        str3 = "";
                    }
                    this.patchInfo.oldVersion = str3;
                    this.patchInfo.newVersion = str3;
                    String str6 = str3;
                    this.patchInfo.isRemoveNewVersion = false;
                    SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, this.patchInfo, patchInfoLockFile);
                    SharePatchFileUtil.deleteDir(absolutePath + "/" + patchVersionDirectory);
                    if (zEquals) {
                        ShareTinkerInternals.killProcessExceptMain(tinkerApplication);
                        ShareIntentUtil.setIntentReturnCode(intent, -2);
                        return;
                    } else {
                        str3 = str6;
                        str4 = str3;
                    }
                }
            }
            if (this.patchInfo.isRemoveInterpretOATDir) {
                Log.i("Tinker.TinkerLoader", "tryLoadPatchFiles: isRemoveInterpretOATDir is true, try to delete interpret optimize files");
                str2 = str4;
                this.patchInfo.isRemoveInterpretOATDir = false;
                SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, this.patchInfo, patchInfoLockFile);
                ShareTinkerInternals.killProcessExceptMain(tinkerApplication);
                SharePatchFileUtil.deleteDir((absolutePath + "/" + patchVersionDirectory) + "/interpet");
            } else {
                str2 = str4;
            }
            str = str2;
        } else {
            str = str4;
        }
        intent.putExtra("intent_patch_old_version", str3);
        intent.putExtra("intent_patch_new_version", str);
        boolean z5 = !str3.equals(str);
        boolean zEquals2 = str5.equals("changing");
        String currentOatMode = ShareTinkerInternals.getCurrentOatMode(tinkerApplication, str5);
        intent.putExtra("intent_patch_oat_dir", currentOatMode);
        if (z5 && zIsInMainProcess) {
            str3 = str;
        }
        if (ShareTinkerInternals.isNullOrNil(str3)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:version is blank, wait main process to restart");
            ShareIntentUtil.setIntentReturnCode(intent, -5);
            return;
        }
        String patchVersionDirectory2 = SharePatchFileUtil.getPatchVersionDirectory(str3);
        if (patchVersionDirectory2 == null) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:patchName is null");
            ShareIntentUtil.setIntentReturnCode(intent, -6);
            return;
        }
        String str7 = absolutePath + "/" + patchVersionDirectory2;
        File file = new File(str7);
        if (!file.exists()) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchVersionDirectoryNotFound");
            ShareIntentUtil.setIntentReturnCode(intent, -6);
            return;
        }
        String patchVersionFile = SharePatchFileUtil.getPatchVersionFile(str3);
        File file2 = patchVersionFile != null ? new File(file.getAbsolutePath(), patchVersionFile) : null;
        if (!SharePatchFileUtil.isLegalFile(file2)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchVersionFileNotFound");
            ShareIntentUtil.setIntentReturnCode(intent, -7);
            return;
        }
        ShareSecurityCheck shareSecurityCheck2 = new ShareSecurityCheck(tinkerApplication);
        int iCheckTinkerPackage = ShareTinkerInternals.checkTinkerPackage(tinkerApplication, tinkerFlags, file2, shareSecurityCheck2);
        if (iCheckTinkerPackage != 0) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:checkTinkerPackage");
            intent.putExtra("intent_patch_package_patch_check", iCheckTinkerPackage);
            ShareIntentUtil.setIntentReturnCode(intent, -8);
            return;
        }
        intent.putExtra("intent_patch_package_config", shareSecurityCheck2.getPackagePropertiesIfPresent());
        boolean zIsTinkerEnabledForDex = ShareTinkerInternals.isTinkerEnabledForDex(tinkerFlags);
        boolean zIsArkHotRuning = ShareTinkerInternals.isArkHotRuning();
        if (!zIsArkHotRuning && zIsTinkerEnabledForDex && !TinkerDexLoader.checkComplete(str7, shareSecurityCheck2, currentOatMode, intent)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:dex check fail");
            return;
        }
        boolean zIsTinkerEnabledForArkHot = ShareTinkerInternals.isTinkerEnabledForArkHot(tinkerFlags);
        if (zIsArkHotRuning && zIsTinkerEnabledForArkHot && !TinkerArkHotLoader.checkComplete(str7, shareSecurityCheck2, intent)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:dex check fail");
            return;
        }
        if (ShareTinkerInternals.isTinkerEnabledForNativeLib(tinkerFlags) && !TinkerSoLoader.checkComplete(str7, shareSecurityCheck2, intent)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:native lib check fail");
            return;
        }
        boolean zIsTinkerEnabledForResource = ShareTinkerInternals.isTinkerEnabledForResource(tinkerFlags);
        Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:isEnabledForResource:" + zIsTinkerEnabledForResource);
        if (zIsTinkerEnabledForResource && !TinkerResourceLoader.checkComplete(tinkerApplication, str7, shareSecurityCheck2, intent)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:resource check fail");
            return;
        }
        boolean z6 = ShareTinkerInternals.isVmArt() && ShareTinkerInternals.isSystemOTA(this.patchInfo.fingerPrint) && Build.VERSION.SDK_INT >= 21 && !ShareTinkerInternals.isAfterAndroidO();
        intent.putExtra("intent_patch_system_ota", z6);
        if (zIsInMainProcess) {
            if (z5) {
                this.patchInfo.oldVersion = str3;
            }
            if (zEquals2) {
                this.patchInfo.oatDir = currentOatMode;
                this.patchInfo.isRemoveInterpretOATDir = true;
            }
        }
        if (!checkSafeModeCount(tinkerApplication)) {
            intent.putExtra("intent_patch_exception", new TinkerRuntimeException("checkSafeModeCount fail"));
            ShareIntentUtil.setIntentReturnCode(intent, -25);
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:checkSafeModeCount fail");
            return;
        }
        String str8 = "tryLoadPatchFiles:onReWritePatchInfoCorrupted";
        if (zIsArkHotRuning || !zIsTinkerEnabledForDex) {
            shareSecurityCheck = shareSecurityCheck2;
            z = zIsTinkerEnabledForResource;
            i = -19;
        } else {
            i = -19;
            boolean z7 = z6;
            z = zIsTinkerEnabledForResource;
            shareSecurityCheck = shareSecurityCheck2;
            boolean zLoadTinkerJars = TinkerDexLoader.loadTinkerJars(tinkerApplication, str7, currentOatMode, intent, z7, z3);
            if (z7) {
                this.patchInfo.fingerPrint = Build.FINGERPRINT;
                this.patchInfo.oatDir = zLoadTinkerJars ? "interpet" : "odex";
                if (!SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, this.patchInfo, patchInfoLockFile)) {
                    ShareIntentUtil.setIntentReturnCode(intent, -19);
                    Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                    return;
                } else {
                    str8 = "tryLoadPatchFiles:onReWritePatchInfoCorrupted";
                    intent.putExtra("intent_patch_oat_dir", this.patchInfo.oatDir);
                    z2 = false;
                }
            } else {
                str8 = "tryLoadPatchFiles:onReWritePatchInfoCorrupted";
                z2 = zEquals2;
            }
            if (!zLoadTinkerJars) {
                Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchLoadDexesFail");
                return;
            }
            zEquals2 = z2;
        }
        if (zIsArkHotRuning && zIsTinkerEnabledForArkHot && !TinkerArkHotLoader.loadTinkerArkHot(tinkerApplication, str7, intent)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchLoadArkApkFail");
            return;
        }
        if (z && !TinkerResourceLoader.loadTinkerResources(tinkerApplication, str7, intent)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchLoadResourcesFail");
            return;
        }
        if ((zIsTinkerEnabledForDex || zIsTinkerEnabledForArkHot) && z) {
            ComponentHotplug.install(tinkerApplication, shareSecurityCheck);
        }
        if (!AppInfoChangedBlocker.tryStart(tinkerApplication)) {
            Log.w("Tinker.TinkerLoader", "tryLoadPatchFiles:AppInfoChangedBlocker install fail.");
            ShareIntentUtil.setIntentReturnCode(intent, -28);
            return;
        }
        if (zIsInMainProcess && (z5 || zEquals2)) {
            if (!SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, this.patchInfo, patchInfoLockFile)) {
                ShareIntentUtil.setIntentReturnCode(intent, i);
                Log.w("Tinker.TinkerLoader", str8);
                return;
            }
            ShareTinkerInternals.killProcessExceptMain(tinkerApplication);
        }
        ShareIntentUtil.setIntentReturnCode(intent, 0);
        Log.i("Tinker.TinkerLoader", "tryLoadPatchFiles: load end, ok!");
    }

    @Override
    public Intent tryLoad(TinkerApplication tinkerApplication) throws Throwable {
        Log.d("Tinker.TinkerLoader", "tryLoad test test");
        Intent intent = new Intent();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        tryLoadPatchFilesInternal(tinkerApplication, intent);
        ShareIntentUtil.setIntentPatchCostTime(intent, SystemClock.elapsedRealtime() - jElapsedRealtime);
        return intent;
    }
}
