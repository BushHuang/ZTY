package com.tencent.tinker.lib.tinker;

import android.content.Intent;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TinkerApplicationHelper {
    private static final String TAG = "Tinker.TinkerApplicationHelper";

    public static void cleanPatch(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        File patchDirectory = SharePatchFileUtil.getPatchDirectory(applicationLike.getApplication());
        if (!patchDirectory.exists()) {
            TinkerLog.w("Tinker.TinkerApplicationHelper", "try to clean patch while there're not any applied patches.", new Object[0]);
            return;
        }
        File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(patchDirectory.getAbsolutePath());
        if (!patchInfoFile.exists()) {
            TinkerLog.w("Tinker.TinkerApplicationHelper", "try to clean patch while patch info file does not exist.", new Object[0]);
            return;
        }
        File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(patchDirectory.getAbsolutePath());
        SharePatchInfo andCheckPropertyWithLock = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
        if (andCheckPropertyWithLock != null) {
            andCheckPropertyWithLock.isRemoveNewVersion = true;
            SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, andCheckPropertyWithLock, patchInfoLockFile);
        }
    }

    public static String getCurrentVersion(ApplicationLike applicationLike) throws Throwable {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent == null) {
            return null;
        }
        String stringExtra = ShareIntentUtil.getStringExtra(tinkerResultIntent, "intent_patch_old_version");
        String stringExtra2 = ShareIntentUtil.getStringExtra(tinkerResultIntent, "intent_patch_new_version");
        boolean zIsInMainProcess = ShareTinkerInternals.isInMainProcess(applicationLike.getApplication());
        if (stringExtra == null || stringExtra2 == null) {
            return null;
        }
        return zIsInMainProcess ? stringExtra2 : stringExtra;
    }

    public static HashMap<String, String> getLoadDexesAndMd5(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0) {
            return ShareIntentUtil.getIntentPatchDexPaths(tinkerResultIntent);
        }
        return null;
    }

    public static HashMap<String, String> getLoadLibraryAndMd5(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0) {
            return ShareIntentUtil.getIntentPatchLibsPaths(tinkerResultIntent);
        }
        return null;
    }

    public static HashMap<String, String> getPackageConfigs(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        if (tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0) {
            return ShareIntentUtil.getIntentPackageConfig(tinkerResultIntent);
        }
        return null;
    }

    public static File getTinkerPatchDirectory(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        return SharePatchFileUtil.getPatchDirectory(applicationLike.getApplication());
    }

    public static boolean isTinkerEnableAll(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        return ShareTinkerInternals.isTinkerEnabledAll(applicationLike.getTinkerFlags());
    }

    public static boolean isTinkerEnableForDex(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        return ShareTinkerInternals.isTinkerEnabledForDex(applicationLike.getTinkerFlags());
    }

    public static boolean isTinkerEnableForNativeLib(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        return ShareTinkerInternals.isTinkerEnabledForNativeLib(applicationLike.getTinkerFlags());
    }

    public static boolean isTinkerEnableForResource(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        return ShareTinkerInternals.isTinkerEnabledForResource(applicationLike.getTinkerFlags());
    }

    public static boolean isTinkerLoadSuccess(ApplicationLike applicationLike) {
        if (applicationLike == null || applicationLike.getApplication() == null) {
            throw new TinkerRuntimeException("tinkerApplication is null");
        }
        Intent tinkerResultIntent = applicationLike.getTinkerResultIntent();
        return tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(tinkerResultIntent) == 0;
    }

    public static void loadArmLibrary(ApplicationLike applicationLike, String str) {
        if (str == null || str.isEmpty() || applicationLike == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        }
        if (isTinkerEnableForNativeLib(applicationLike) && loadLibraryFromTinker(applicationLike, "lib/armeabi", str)) {
            return;
        }
        System.loadLibrary(str);
    }

    public static void loadArmV7aLibrary(ApplicationLike applicationLike, String str) {
        if (str == null || str.isEmpty() || applicationLike == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        }
        if (isTinkerEnableForNativeLib(applicationLike) && loadLibraryFromTinker(applicationLike, "lib/armeabi-v7a", str)) {
            return;
        }
        System.loadLibrary(str);
    }

    public static boolean loadLibraryFromTinker(ApplicationLike applicationLike, String str, String str2) throws Throwable {
        HashMap<String, String> loadLibraryAndMd5;
        File patchDirectory;
        if (!str2.startsWith("lib")) {
            str2 = "lib" + str2;
        }
        if (!str2.endsWith(".so")) {
            str2 = str2 + ".so";
        }
        String str3 = str + "/" + str2;
        if (!isTinkerEnableForNativeLib(applicationLike) || !isTinkerEnableForNativeLib(applicationLike) || (loadLibraryAndMd5 = getLoadLibraryAndMd5(applicationLike)) == null) {
            return false;
        }
        String currentVersion = getCurrentVersion(applicationLike);
        if (ShareTinkerInternals.isNullOrNil(currentVersion) || (patchDirectory = SharePatchFileUtil.getPatchDirectory(applicationLike.getApplication())) == null) {
            return false;
        }
        String str4 = new File(patchDirectory.getAbsolutePath() + "/" + SharePatchFileUtil.getPatchVersionDirectory(currentVersion)).getAbsolutePath() + "/lib";
        Iterator<Map.Entry<String, String>> it = loadLibraryAndMd5.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (key.equals(str3)) {
                String str5 = str4 + "/" + key;
                File file = new File(str5);
                if (!file.exists()) {
                    continue;
                } else {
                    if (!applicationLike.getTinkerLoadVerifyFlag() || SharePatchFileUtil.verifyFileMd5(file, loadLibraryAndMd5.get(key))) {
                        System.load(str5);
                        TinkerLog.i("Tinker.TinkerApplicationHelper", "loadLibraryFromTinker success:" + str5, new Object[0]);
                        return true;
                    }
                    TinkerLog.i("Tinker.TinkerApplicationHelper", "loadLibraryFromTinker md5mismatch fail:" + str5, new Object[0]);
                }
            }
        }
        return false;
    }
}
