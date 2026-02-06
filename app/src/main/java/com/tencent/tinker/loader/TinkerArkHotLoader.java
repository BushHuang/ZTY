package com.tencent.tinker.loader;

import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareArkHotDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TinkerArkHotLoader {
    private static final String ARKHOT_PATH = "arkHot";
    private static final String ARK_MEAT_FILE = "assets/arkHot_meta.txt";
    private static final String TAG = "Tinker.TinkerArkHotLoader";
    private static HashSet<ShareArkHotDiffPatchInfo> arkHotApkInfo = new HashSet<>();
    private static boolean isArkHotRuning = ShareTinkerInternals.isArkHotRuning();

    private TinkerArkHotLoader() {
    }

    public static boolean checkComplete(String str, ShareSecurityCheck shareSecurityCheck, Intent intent) {
        String str2 = shareSecurityCheck.getMetaContentMap().get("assets/arkHot_meta.txt");
        if (str2 == null) {
            return true;
        }
        arkHotApkInfo.clear();
        ArrayList arrayList = new ArrayList();
        ShareArkHotDiffPatchInfo.parseDiffPatchInfo(str2, arrayList);
        if (arrayList.isEmpty()) {
            return true;
        }
        HashMap map = new HashMap(1);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ShareArkHotDiffPatchInfo shareArkHotDiffPatchInfo = (ShareArkHotDiffPatchInfo) it.next();
            if (!ShareArkHotDiffPatchInfo.checkDiffPatchInfo(shareArkHotDiffPatchInfo)) {
                intent.putExtra("intent_patch_package_patch_check", -3);
                ShareIntentUtil.setIntentReturnCode(intent, -8);
                return false;
            }
            if (isArkHotRuning && "patch.apk".equals(shareArkHotDiffPatchInfo.name)) {
                arkHotApkInfo.add(shareArkHotDiffPatchInfo);
            }
        }
        if (isArkHotRuning && !arkHotApkInfo.isEmpty()) {
            map.put("patch.apk", "");
        }
        String str3 = str + "/arkHot/";
        File file = new File(str3);
        if (!file.exists() || !file.isDirectory()) {
            ShareIntentUtil.setIntentReturnCode(intent, -9);
            return false;
        }
        Iterator it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            File file2 = new File(str3 + ((String) it2.next()));
            if (!SharePatchFileUtil.isLegalFile(file2)) {
                try {
                    intent.putExtra("intent_patch_missing_dex_path", file2.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ShareIntentUtil.setIntentReturnCode(intent, -10);
                return false;
            }
        }
        intent.putExtra("intent_patch_dexes_path", map);
        return true;
    }

    public static boolean loadTinkerArkHot(TinkerApplication tinkerApplication, String str, Intent intent) {
        if (arkHotApkInfo.isEmpty()) {
            Log.w("Tinker.TinkerArkHotLoader", "there is no apk to load");
            return true;
        }
        PathClassLoader pathClassLoader = (PathClassLoader) TinkerArkHotLoader.class.getClassLoader();
        if (pathClassLoader == null) {
            Log.e("Tinker.TinkerArkHotLoader", "classloader is null");
            ShareIntentUtil.setIntentReturnCode(intent, -12);
            return false;
        }
        Log.i("Tinker.TinkerArkHotLoader", "classloader: " + pathClassLoader.toString());
        String str2 = str + "/arkHot/";
        ArrayList arrayList = new ArrayList();
        if (isArkHotRuning && !arkHotApkInfo.isEmpty()) {
            arrayList.add(new File(str2 + "patch.apk"));
        }
        try {
            SystemClassLoaderAdder.installApk(pathClassLoader, arrayList);
            return true;
        } catch (Throwable th) {
            Log.e("Tinker.TinkerArkHotLoader", "install dexes failed");
            intent.putExtra("intent_patch_exception", th);
            ShareIntentUtil.setIntentReturnCode(intent, -14);
            return false;
        }
    }
}
