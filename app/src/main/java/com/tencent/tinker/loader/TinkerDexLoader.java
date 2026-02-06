package com.tencent.tinker.loader;

import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.TinkerDexOptimizer;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareDexDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TinkerDexLoader {
    private static final String DEFAULT_DEX_OPTIMIZE_PATH = "odex";
    private static final String DEX_MEAT_FILE = "assets/dex_meta.txt";
    private static final String DEX_PATH = "dex";
    private static final String INTERPRET_DEX_OPTIMIZE_PATH = "interpet";
    private static final String TAG = "Tinker.TinkerDexLoader";
    private static final ArrayList<ShareDexDiffPatchInfo> LOAD_DEX_LIST = new ArrayList<>();
    private static HashSet<ShareDexDiffPatchInfo> classNDexInfo = new HashSet<>();
    private static boolean isVmArt = ShareTinkerInternals.isVmArt();

    private TinkerDexLoader() {
    }

    public static boolean checkComplete(String str, ShareSecurityCheck shareSecurityCheck, String str2, Intent intent) {
        String str3 = shareSecurityCheck.getMetaContentMap().get("assets/dex_meta.txt");
        if (str3 == null) {
            return true;
        }
        LOAD_DEX_LIST.clear();
        classNDexInfo.clear();
        ArrayList arrayList = new ArrayList();
        ShareDexDiffPatchInfo.parseDexDiffPatchInfo(str3, arrayList);
        if (arrayList.isEmpty()) {
            return true;
        }
        HashMap map = new HashMap();
        ShareDexDiffPatchInfo shareDexDiffPatchInfo = null;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ShareDexDiffPatchInfo shareDexDiffPatchInfo2 = (ShareDexDiffPatchInfo) it.next();
            if (!isJustArtSupportDex(shareDexDiffPatchInfo2)) {
                if (!ShareDexDiffPatchInfo.checkDexDiffPatchInfo(shareDexDiffPatchInfo2)) {
                    intent.putExtra("intent_patch_package_patch_check", -3);
                    ShareIntentUtil.setIntentReturnCode(intent, -8);
                    return false;
                }
                if (isVmArt && shareDexDiffPatchInfo2.rawName.startsWith("test.dex")) {
                    shareDexDiffPatchInfo = shareDexDiffPatchInfo2;
                } else if (isVmArt && ShareConstants.CLASS_N_PATTERN.matcher(shareDexDiffPatchInfo2.realName).matches()) {
                    classNDexInfo.add(shareDexDiffPatchInfo2);
                } else {
                    map.put(shareDexDiffPatchInfo2.realName, getInfoMd5(shareDexDiffPatchInfo2));
                    LOAD_DEX_LIST.add(shareDexDiffPatchInfo2);
                }
            }
        }
        if (isVmArt && (shareDexDiffPatchInfo != null || !classNDexInfo.isEmpty())) {
            if (shareDexDiffPatchInfo != null) {
                HashSet<ShareDexDiffPatchInfo> hashSet = classNDexInfo;
                hashSet.add(ShareTinkerInternals.changeTestDexToClassN(shareDexDiffPatchInfo, hashSet.size() + 1));
            }
            map.put("tinker_classN.apk", "");
        }
        String str4 = str + "/dex/";
        File file = new File(str4);
        if (!file.exists() || !file.isDirectory()) {
            ShareIntentUtil.setIntentReturnCode(intent, -9);
            return false;
        }
        File file2 = new File(str + "/" + str2 + "/");
        Iterator it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            File file3 = new File(str4 + ((String) it2.next()));
            if (!SharePatchFileUtil.isLegalFile(file3)) {
                intent.putExtra("intent_patch_missing_dex_path", file3.getAbsolutePath());
                ShareIntentUtil.setIntentReturnCode(intent, -10);
                return false;
            }
            File file4 = new File(SharePatchFileUtil.optimizedPathFor(file3, file2));
            if (!SharePatchFileUtil.isLegalFile(file4) && !SharePatchFileUtil.shouldAcceptEvenIfIllegal(file4)) {
                intent.putExtra("intent_patch_missing_dex_path", file4.getAbsolutePath());
                ShareIntentUtil.setIntentReturnCode(intent, -11);
                return false;
            }
        }
        intent.putExtra("intent_patch_dexes_path", map);
        return true;
    }

    private static void deleteOutOfDateOATFile(String str) {
        SharePatchFileUtil.deleteDir(str + "/odex/");
        if (ShareTinkerInternals.isAfterAndroidO()) {
            SharePatchFileUtil.deleteDir(str + "/dex/oat/");
        }
    }

    private static String getInfoMd5(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        return isVmArt ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
    }

    private static boolean isJustArtSupportDex(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        return !isVmArt && shareDexDiffPatchInfo.destMd5InDvm.equals("0");
    }

    public static boolean loadTinkerJars(TinkerApplication tinkerApplication, String str, String str2, Intent intent, boolean z, boolean z2) {
        TinkerApplication tinkerApplication2;
        boolean z3;
        if (LOAD_DEX_LIST.isEmpty() && classNDexInfo.isEmpty()) {
            Log.w("Tinker.TinkerDexLoader", "there is no dex to load");
            return true;
        }
        ClassLoader classLoader = TinkerDexLoader.class.getClassLoader();
        if (classLoader == null) {
            Log.e("Tinker.TinkerDexLoader", "classloader is null");
            ShareIntentUtil.setIntentReturnCode(intent, -12);
            return false;
        }
        Log.i("Tinker.TinkerDexLoader", "classloader: " + classLoader.toString());
        String str3 = str + "/dex/";
        ArrayList arrayList = new ArrayList();
        Iterator<ShareDexDiffPatchInfo> it = LOAD_DEX_LIST.iterator();
        while (it.hasNext()) {
            ShareDexDiffPatchInfo next = it.next();
            if (!isJustArtSupportDex(next)) {
                File file = new File(str3 + next.realName);
                if (tinkerApplication.isTinkerLoadVerifyFlag()) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (!SharePatchFileUtil.verifyDexFileMd5(file, getInfoMd5(next))) {
                        ShareIntentUtil.setIntentReturnCode(intent, -13);
                        intent.putExtra("intent_patch_mismatch_dex_path", file.getAbsolutePath());
                        return false;
                    }
                    Log.i("Tinker.TinkerDexLoader", "verify dex file:" + file.getPath() + " md5, use time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                }
                arrayList.add(file);
            }
        }
        if (isVmArt && !classNDexInfo.isEmpty()) {
            File file2 = new File(str3 + "tinker_classN.apk");
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (tinkerApplication.isTinkerLoadVerifyFlag()) {
                Iterator<ShareDexDiffPatchInfo> it2 = classNDexInfo.iterator();
                while (it2.hasNext()) {
                    ShareDexDiffPatchInfo next2 = it2.next();
                    if (!SharePatchFileUtil.verifyDexFileMd5(file2, next2.rawName, next2.destMd5InArt)) {
                        ShareIntentUtil.setIntentReturnCode(intent, -13);
                        intent.putExtra("intent_patch_mismatch_dex_path", file2.getAbsolutePath());
                        return false;
                    }
                }
            }
            Log.i("Tinker.TinkerDexLoader", "verify dex file:" + file2.getPath() + " md5, use time: " + (System.currentTimeMillis() - jCurrentTimeMillis2));
            arrayList.add(file2);
        }
        File file3 = new File(str + "/" + str2);
        if (z) {
            final boolean[] zArr = {true};
            final Throwable[] thArr = new Throwable[1];
            try {
                String currentInstructionSet = ShareTinkerInternals.getCurrentInstructionSet();
                deleteOutOfDateOATFile(str);
                Log.w("Tinker.TinkerDexLoader", "systemOTA, try parallel oat dexes, targetISA:" + currentInstructionSet);
                File file4 = new File(str + "/interpet");
                TinkerDexOptimizer.optimizeAll(tinkerApplication, arrayList, file4, true, currentInstructionSet, new TinkerDexOptimizer.ResultCallback() {
                    long start;

                    @Override
                    public void onFailed(File file5, File file6, Throwable th) {
                        zArr[0] = false;
                        thArr[0] = th;
                        Log.i("Tinker.TinkerDexLoader", "fail to optimize dex " + file5.getPath() + ", use time " + (System.currentTimeMillis() - this.start));
                    }

                    @Override
                    public void onStart(File file5, File file6) {
                        this.start = System.currentTimeMillis();
                        Log.i("Tinker.TinkerDexLoader", "start to optimize dex:" + file5.getPath());
                    }

                    @Override
                    public void onSuccess(File file5, File file6, File file7) {
                        Log.i("Tinker.TinkerDexLoader", "success to optimize dex " + file5.getPath() + ", use time " + (System.currentTimeMillis() - this.start));
                    }
                });
                if (!zArr[0]) {
                    Log.e("Tinker.TinkerDexLoader", "parallel oat dexes failed");
                    intent.putExtra("intent_patch_interpret_exception", thArr[0]);
                    ShareIntentUtil.setIntentReturnCode(intent, -16);
                    return false;
                }
                tinkerApplication2 = tinkerApplication;
                z3 = z2;
                file3 = file4;
            } catch (Throwable th) {
                Log.i("Tinker.TinkerDexLoader", "getCurrentInstructionSet fail:" + th);
                deleteOutOfDateOATFile(str);
                intent.putExtra("intent_patch_interpret_exception", th);
                ShareIntentUtil.setIntentReturnCode(intent, -15);
                return false;
            }
        } else {
            tinkerApplication2 = tinkerApplication;
            z3 = z2;
        }
        try {
            SystemClassLoaderAdder.installDexes(tinkerApplication2, classLoader, file3, arrayList, z3);
            return true;
        } catch (Throwable th2) {
            Log.e("Tinker.TinkerDexLoader", "install dexes failed");
            intent.putExtra("intent_patch_exception", th2);
            ShareIntentUtil.setIntentReturnCode(intent, -14);
            return false;
        }
    }
}
