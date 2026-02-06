package com.tencent.tinker.lib.patch;

import android.content.Context;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareArkHotDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipFile;

public class ArkHotDiffPatchInternal extends BasePatchInternal {
    private static final String TAG = "Tinker.ArkHotDiffPatchInternal";
    private static ArrayList<ShareArkHotDiffPatchInfo> arkPatchList = new ArrayList<>();

    private static boolean extractArkHotLibrary(Context context, String str, File file, int i) throws Throwable {
        String str2;
        Tinker tinkerWith = Tinker.with(context);
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(file);
                try {
                    Iterator<ShareArkHotDiffPatchInfo> it = arkPatchList.iterator();
                    while (it.hasNext()) {
                        ShareArkHotDiffPatchInfo next = it.next();
                        String str3 = next.path;
                        if (str3.equals("")) {
                            str2 = next.name;
                        } else {
                            str2 = str3 + "/" + next.name;
                        }
                        String str4 = next.patchMd5;
                        if (!SharePatchFileUtil.checkIfMd5Valid(str4)) {
                            tinkerWith.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                            SharePatchFileUtil.closeZip(zipFile2);
                            return false;
                        }
                        File file2 = new File(str + next.name);
                        if (!file2.exists()) {
                            file2.getParentFile().mkdirs();
                        } else if (!str4.equals(SharePatchFileUtil.getMD5(file2))) {
                            file2.delete();
                        }
                        if (!extract(zipFile2, zipFile2.getEntry(str2), file2, str4, false)) {
                            tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file2, next.name, i);
                            SharePatchFileUtil.closeZip(zipFile2);
                            return false;
                        }
                    }
                    SharePatchFileUtil.closeZip(zipFile2);
                    return true;
                } catch (IOException e) {
                    e = e;
                    throw new TinkerRuntimeException("patch " + ShareTinkerInternals.getTypeString(i) + " extract failed (" + e.getMessage() + ").", e);
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    SharePatchFileUtil.closeZip(zipFile);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean patchArkHotLibraryExtract(Context context, String str, String str2, File file) {
        String str3 = str + "/arkHot/";
        arkPatchList.clear();
        ShareArkHotDiffPatchInfo.parseDiffPatchInfo(str2, arkPatchList);
        return extractArkHotLibrary(context, str3, file, 8);
    }

    protected static boolean tryRecoverArkHotLibrary(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        String str2 = shareSecurityCheck.getMetaContentMap().get("assets/arkHot_meta.txt");
        if (str2 == null) {
            return true;
        }
        patchArkHotLibraryExtract(context, str, str2, file);
        return true;
    }
}
