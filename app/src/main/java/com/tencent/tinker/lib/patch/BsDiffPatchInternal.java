package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;
import com.tencent.tinker.bsdiff.BSPatch;
import com.tencent.tinker.commons.util.IOHelper;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareBsDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BsDiffPatchInternal extends BasePatchInternal {
    private static final String TAG = "Tinker.BsDiffPatchInternal";

    private static boolean extractBsDiffInternals(Context context, String str, String str2, File file, int i) {
        ZipFile zipFile;
        ZipFile zipFile2;
        ZipFile zipFile3;
        String str3;
        InputStream inputStream;
        InputStream inputStream2;
        InputStream inputStream3;
        String str4 = str;
        ArrayList arrayList = new ArrayList();
        ShareBsDiffPatchInfo.parseDiffPatchInfo(str2, arrayList);
        if (arrayList.isEmpty()) {
            TinkerLog.w("Tinker.BsDiffPatchInternal", "extract patch list is empty! type:%s:", ShareTinkerInternals.getTypeString(i));
            return true;
        }
        File file2 = new File(str4);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        Tinker tinkerWith = Tinker.with(context);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            TinkerLog.w("Tinker.BsDiffPatchInternal", "applicationInfo == null!!!!", new Object[0]);
            return false;
        }
        try {
            zipFile3 = new ZipFile(applicationInfo.sourceDir);
            try {
                zipFile = new ZipFile(file);
            } catch (Throwable th) {
                th = th;
                zipFile2 = zipFile3;
                zipFile = null;
            }
        } catch (Throwable th2) {
            th = th2;
            zipFile = null;
            zipFile2 = null;
        }
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ShareBsDiffPatchInfo shareBsDiffPatchInfo = (ShareBsDiffPatchInfo) it.next();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (shareBsDiffPatchInfo.path.equals("")) {
                    str3 = shareBsDiffPatchInfo.name;
                } else {
                    str3 = shareBsDiffPatchInfo.path + "/" + shareBsDiffPatchInfo.name;
                }
                String str5 = shareBsDiffPatchInfo.md5;
                if (SharePatchFileUtil.checkIfMd5Valid(str5)) {
                    StringBuilder sb = new StringBuilder();
                    Iterator it2 = it;
                    sb.append(shareBsDiffPatchInfo.path);
                    sb.append("/");
                    sb.append(shareBsDiffPatchInfo.name);
                    File file3 = new File(str4 + sb.toString());
                    if (!file3.exists()) {
                        file3.getParentFile().mkdirs();
                    } else if (str5.equals(SharePatchFileUtil.getMD5(file3))) {
                        TinkerLog.w("Tinker.BsDiffPatchInternal", "bsdiff file %s is already exist, and md5 match, just continue", file3.getPath());
                        it = it2;
                    } else {
                        TinkerLog.w("Tinker.BsDiffPatchInternal", "have a mismatch corrupted dex " + file3.getPath(), new Object[0]);
                        file3.delete();
                    }
                    String str6 = shareBsDiffPatchInfo.patchMd5;
                    ZipEntry entry = zipFile.getEntry(str3);
                    if (entry == null) {
                        TinkerLog.w("Tinker.BsDiffPatchInternal", "patch entry is null. path:" + str3, new Object[0]);
                        tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                    } else if (str6.equals("0")) {
                        if (extract(zipFile, entry, file3, str5, false)) {
                            str4 = str;
                            it = it2;
                        } else {
                            TinkerLog.w("Tinker.BsDiffPatchInternal", "Failed to extract file " + file3.getPath(), new Object[0]);
                            tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                        }
                    } else if (SharePatchFileUtil.checkIfMd5Valid(str6)) {
                        ZipEntry entry2 = zipFile3.getEntry(str3);
                        if (entry2 == null) {
                            TinkerLog.w("Tinker.BsDiffPatchInternal", "apk entry is null. path:" + str3, new Object[0]);
                            tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                        } else {
                            String str7 = shareBsDiffPatchInfo.rawCrc;
                            String strValueOf = String.valueOf(entry2.getCrc());
                            if (strValueOf.equals(str7)) {
                                try {
                                    inputStream3 = zipFile3.getInputStream(entry2);
                                    try {
                                        inputStream = zipFile.getInputStream(entry);
                                    } catch (Throwable th3) {
                                        th = th3;
                                        inputStream2 = inputStream3;
                                        inputStream = null;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    inputStream = null;
                                    inputStream2 = null;
                                }
                                try {
                                    BSPatch.patchFast(inputStream3, inputStream, file3);
                                    IOHelper.closeQuietly(inputStream3);
                                    IOHelper.closeQuietly(inputStream);
                                    if (SharePatchFileUtil.verifyFileMd5(file3, str5)) {
                                        TinkerLog.w("Tinker.BsDiffPatchInternal", "success recover bsdiff file: %s, use time: %d", file3.getPath(), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                                        str4 = str;
                                        it = it2;
                                    } else {
                                        TinkerLog.w("Tinker.BsDiffPatchInternal", "Failed to recover diff file " + file3.getPath(), new Object[0]);
                                        tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                                        SharePatchFileUtil.safeDeleteFile(file3);
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    inputStream2 = inputStream3;
                                    IOHelper.closeQuietly(inputStream2);
                                    IOHelper.closeQuietly(inputStream);
                                    throw th;
                                }
                            } else {
                                TinkerLog.e("Tinker.BsDiffPatchInternal", "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str7, strValueOf);
                                tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, shareBsDiffPatchInfo.name, i);
                            }
                        }
                    } else {
                        TinkerLog.w("Tinker.BsDiffPatchInternal", "meta file md5 mismatch, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareBsDiffPatchInfo.name, str6);
                        tinkerWith.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                    }
                } else {
                    TinkerLog.w("Tinker.BsDiffPatchInternal", "meta file md5 mismatch, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareBsDiffPatchInfo.name, shareBsDiffPatchInfo.md5);
                    tinkerWith.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                }
                SharePatchFileUtil.closeZip(zipFile3);
                SharePatchFileUtil.closeZip(zipFile);
                return false;
            }
            SharePatchFileUtil.closeZip(zipFile3);
            SharePatchFileUtil.closeZip(zipFile);
            return true;
        } catch (Throwable th6) {
            th = th6;
            zipFile2 = zipFile3;
            try {
                throw new TinkerRuntimeException("patch " + ShareTinkerInternals.getTypeString(i) + " extract failed (" + th.getMessage() + ").", th);
            } catch (Throwable th7) {
                SharePatchFileUtil.closeZip(zipFile2);
                SharePatchFileUtil.closeZip(zipFile);
                throw th7;
            }
        }
    }

    private static boolean patchLibraryExtractViaBsDiff(Context context, String str, String str2, File file) {
        return extractBsDiffInternals(context, str + "/lib/", str2, file, 5);
    }

    protected static boolean tryRecoverLibraryFiles(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        if (!tinker.isEnabledForNativeLib()) {
            TinkerLog.w("Tinker.BsDiffPatchInternal", "patch recover, library is not enabled", new Object[0]);
            return true;
        }
        String str2 = shareSecurityCheck.getMetaContentMap().get("assets/so_meta.txt");
        if (str2 == null) {
            TinkerLog.w("Tinker.BsDiffPatchInternal", "patch recover, library is not contained", new Object[0]);
            return true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zPatchLibraryExtractViaBsDiff = patchLibraryExtractViaBsDiff(context, str, str2, file);
        TinkerLog.i("Tinker.BsDiffPatchInternal", "recover lib result:%b, cost:%d", Boolean.valueOf(zPatchLibraryExtractViaBsDiff), Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
        return zPatchLibraryExtractViaBsDiff;
    }
}
