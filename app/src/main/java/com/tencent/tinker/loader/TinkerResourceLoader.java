package com.tencent.tinker.loader;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareResPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import java.io.File;

public class TinkerResourceLoader {
    protected static final String RESOURCE_FILE = "resources.apk";
    protected static final String RESOURCE_META_FILE = "assets/res_meta.txt";
    protected static final String RESOURCE_PATH = "res";
    private static final String TAG = "Tinker.ResourceLoader";
    private static ShareResPatchInfo resPatchInfo = new ShareResPatchInfo();

    private TinkerResourceLoader() {
    }

    public static boolean checkComplete(Context context, String str, ShareSecurityCheck shareSecurityCheck, Intent intent) {
        String str2 = shareSecurityCheck.getMetaContentMap().get("assets/res_meta.txt");
        if (str2 == null) {
            return true;
        }
        ShareResPatchInfo.parseResPatchInfoFirstLine(str2, resPatchInfo);
        if (resPatchInfo.resArscMd5 == null) {
            return true;
        }
        if (!ShareResPatchInfo.checkResPatchInfo(resPatchInfo)) {
            intent.putExtra("intent_patch_package_patch_check", -8);
            ShareIntentUtil.setIntentReturnCode(intent, -8);
            return false;
        }
        String str3 = str + "/res/";
        File file = new File(str3);
        if (!file.exists() || !file.isDirectory()) {
            ShareIntentUtil.setIntentReturnCode(intent, -21);
            return false;
        }
        if (!SharePatchFileUtil.isLegalFile(new File(str3 + "resources.apk"))) {
            ShareIntentUtil.setIntentReturnCode(intent, -22);
            return false;
        }
        try {
            TinkerResourcePatcher.isResourceCanPatch(context);
            return true;
        } catch (Throwable th) {
            Log.e("Tinker.ResourceLoader", "resource hook check failed.", th);
            intent.putExtra("intent_patch_exception", th);
            ShareIntentUtil.setIntentReturnCode(intent, -23);
            return false;
        }
    }

    public static boolean loadTinkerResources(TinkerApplication tinkerApplication, String str, Intent intent) {
        ShareResPatchInfo shareResPatchInfo = resPatchInfo;
        if (shareResPatchInfo == null || shareResPatchInfo.resArscMd5 == null) {
            return true;
        }
        String str2 = str + "/res/resources.apk";
        File file = new File(str2);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (tinkerApplication.isTinkerLoadVerifyFlag()) {
            if (!SharePatchFileUtil.checkResourceArscMd5(file, resPatchInfo.resArscMd5)) {
                Log.e("Tinker.ResourceLoader", "Failed to load resource file, path: " + file.getPath() + ", expect md5: " + resPatchInfo.resArscMd5);
                ShareIntentUtil.setIntentReturnCode(intent, -24);
                return false;
            }
            Log.i("Tinker.ResourceLoader", "verify resource file:" + file.getPath() + " md5, use time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
        }
        try {
            TinkerResourcePatcher.monkeyPatchExistingResources(tinkerApplication, str2);
            Log.i("Tinker.ResourceLoader", "monkeyPatchExistingResources resource file:" + str2 + ", use time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
            return true;
        } catch (Throwable th) {
            Log.e("Tinker.ResourceLoader", "install resources failed");
            try {
                SystemClassLoaderAdder.uninstallPatchDex(tinkerApplication.getClassLoader());
            } catch (Throwable unused) {
                Log.e("Tinker.ResourceLoader", "uninstallPatchDex failed", th);
            }
            intent.putExtra("intent_patch_exception", th);
            ShareIntentUtil.setIntentReturnCode(intent, -23);
            return false;
        }
    }
}
