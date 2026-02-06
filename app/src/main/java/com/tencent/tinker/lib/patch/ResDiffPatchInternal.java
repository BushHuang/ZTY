package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;
import com.tencent.tinker.bsdiff.BSPatch;
import com.tencent.tinker.commons.util.IOHelper;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareResPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.tinker.ziputils.ziputil.TinkerZipEntry;
import com.tencent.tinker.ziputils.ziputil.TinkerZipFile;
import com.tencent.tinker.ziputils.ziputil.TinkerZipOutputStream;
import com.tencent.tinker.ziputils.ziputil.TinkerZipUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResDiffPatchInternal extends BasePatchInternal {
    protected static final String TAG = "Tinker.ResDiffPatchInternal";

    private static boolean checkAndExtractResourceLargeFile(Context context, String str, File file, File file2, File file3, ShareResPatchInfo shareResPatchInfo, int i) {
        ZipFile zipFile;
        ZipFile zipFile2;
        InputStream inputStream;
        InputStream inputStream2;
        InputStream inputStream3;
        File file4 = file2;
        ShareResPatchInfo shareResPatchInfo2 = shareResPatchInfo;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Tinker tinkerWith = Tinker.with(context);
        try {
            ZipFile zipFile3 = new ZipFile(str);
            try {
                ZipEntry entry = zipFile3.getEntry("resources.arsc");
                File file5 = new File(file, "resources.arsc");
                ?? r13 = 0;
                if (entry == null) {
                    try {
                        TinkerLog.w("Tinker.ResDiffPatchInternal", "resources apk entry is null. path:resources.arsc", new Object[0]);
                        tinkerWith.getPatchReporter().onPatchTypeExtractFail(file3, file5, "resources.arsc", i);
                        SharePatchFileUtil.closeZip(zipFile3);
                        SharePatchFileUtil.closeZip(null);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        zipFile = null;
                    }
                } else {
                    String strValueOf = String.valueOf(entry.getCrc());
                    if (!strValueOf.equals(shareResPatchInfo2.arscBaseCrc)) {
                        TinkerLog.e("Tinker.ResDiffPatchInternal", "resources.arsc's crc is not equal, expect crc: %s, got crc: %s", shareResPatchInfo2.arscBaseCrc, strValueOf);
                        tinkerWith.getPatchReporter().onPatchTypeExtractFail(file3, file5, "resources.arsc", i);
                        SharePatchFileUtil.closeZip(zipFile3);
                        SharePatchFileUtil.closeZip(null);
                        return false;
                    }
                    if (shareResPatchInfo2.largeModRes.isEmpty() && shareResPatchInfo2.storeRes.isEmpty()) {
                        TinkerLog.i("Tinker.ResDiffPatchInternal", "no large modify or store resources, just return", new Object[0]);
                        SharePatchFileUtil.closeZip(zipFile3);
                        SharePatchFileUtil.closeZip(null);
                        return true;
                    }
                    zipFile = new ZipFile(file3);
                    try {
                        for (String str2 : shareResPatchInfo2.storeRes.keySet()) {
                            long jCurrentTimeMillis2 = System.currentTimeMillis();
                            File file6 = new File(file4, str2);
                            SharePatchFileUtil.ensureFileDirectory(file6);
                            ZipEntry entry2 = zipFile.getEntry(str2);
                            if (entry2 == null) {
                                TinkerLog.w("Tinker.ResDiffPatchInternal", "store patch entry is null. path:" + str2, new Object[0]);
                                tinkerWith.getPatchReporter().onPatchTypeExtractFail(file3, file6, str2, i);
                                SharePatchFileUtil.closeZip(zipFile3);
                                SharePatchFileUtil.closeZip(zipFile);
                                return false;
                            }
                            extract(zipFile, entry2, file6, null, false);
                            if (entry2.getSize() != file6.length()) {
                                TinkerLog.w("Tinker.ResDiffPatchInternal", "resource meta file size mismatch, type:%s, name: %s, patch size: %d, file size; %d", ShareTinkerInternals.getTypeString(i), str2, Long.valueOf(entry2.getSize()), Long.valueOf(file6.length()));
                                tinkerWith.getPatchReporter().onPatchPackageCheckFail(file3, BasePatchInternal.getMetaCorruptedCode(i));
                                SharePatchFileUtil.closeZip(zipFile3);
                                SharePatchFileUtil.closeZip(zipFile);
                                return false;
                            }
                            shareResPatchInfo2.storeRes.put(str2, file6);
                            TinkerLog.w("Tinker.ResDiffPatchInternal", "success recover store file:%s, file size:%d, use time:%d", file6.getPath(), Long.valueOf(file6.length()), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis2));
                        }
                        Iterator<String> it = shareResPatchInfo2.largeModRes.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            long jCurrentTimeMillis3 = System.currentTimeMillis();
                            ShareResPatchInfo.LargeModeInfo largeModeInfo = shareResPatchInfo2.largeModMap.get(next);
                            if (largeModeInfo == null) {
                                Object[] objArr = new Object[2];
                                objArr[r13] = ShareTinkerInternals.getTypeString(i);
                                objArr[1] = next;
                                TinkerLog.w("Tinker.ResDiffPatchInternal", "resource not found largeModeInfo, type:%s, name: %s", objArr);
                                tinkerWith.getPatchReporter().onPatchPackageCheckFail(file3, BasePatchInternal.getMetaCorruptedCode(i));
                                SharePatchFileUtil.closeZip(zipFile3);
                                SharePatchFileUtil.closeZip(zipFile);
                                return r13;
                            }
                            largeModeInfo.file = new File(file4, next);
                            SharePatchFileUtil.ensureFileDirectory(largeModeInfo.file);
                            if (SharePatchFileUtil.checkIfMd5Valid(largeModeInfo.md5)) {
                                ZipEntry entry3 = zipFile.getEntry(next);
                                if (entry3 == null) {
                                    TinkerLog.w("Tinker.ResDiffPatchInternal", "large mod patch entry is null. path:" + next, new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file3, largeModeInfo.file, next, i);
                                } else {
                                    ZipEntry entry4 = zipFile3.getEntry(next);
                                    if (entry4 == null) {
                                        TinkerLog.w("Tinker.ResDiffPatchInternal", "resources apk entry is null. path:" + next, new Object[0]);
                                        tinkerWith.getPatchReporter().onPatchTypeExtractFail(file3, largeModeInfo.file, next, i);
                                    } else {
                                        try {
                                            inputStream3 = zipFile3.getInputStream(entry4);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            inputStream = null;
                                        }
                                        try {
                                            inputStream2 = zipFile.getInputStream(entry3);
                                            try {
                                                BSPatch.patchFast(inputStream3, inputStream2, largeModeInfo.file);
                                                IOHelper.closeQuietly(inputStream3);
                                                IOHelper.closeQuietly(inputStream2);
                                                if (SharePatchFileUtil.verifyFileMd5(largeModeInfo.file, largeModeInfo.md5)) {
                                                    TinkerLog.w("Tinker.ResDiffPatchInternal", "success recover large modify file:%s, file size:%d, use time:%d", largeModeInfo.file.getPath(), Long.valueOf(largeModeInfo.file.length()), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis3));
                                                    file4 = file2;
                                                    shareResPatchInfo2 = shareResPatchInfo;
                                                    r13 = 0;
                                                } else {
                                                    TinkerLog.w("Tinker.ResDiffPatchInternal", "Failed to recover large modify file:%s", largeModeInfo.file.getPath());
                                                    SharePatchFileUtil.safeDeleteFile(largeModeInfo.file);
                                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file3, largeModeInfo.file, next, i);
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                inputStream = inputStream3;
                                                IOHelper.closeQuietly(inputStream);
                                                IOHelper.closeQuietly(inputStream2);
                                                throw th;
                                            }
                                        } catch (Throwable th4) {
                                            th = th4;
                                            inputStream = inputStream3;
                                            inputStream2 = null;
                                            IOHelper.closeQuietly(inputStream);
                                            IOHelper.closeQuietly(inputStream2);
                                            throw th;
                                        }
                                    }
                                }
                            } else {
                                TinkerLog.w("Tinker.ResDiffPatchInternal", "resource meta file md5 mismatch, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), next, largeModeInfo.md5);
                                tinkerWith.getPatchReporter().onPatchPackageCheckFail(file3, BasePatchInternal.getMetaCorruptedCode(i));
                            }
                            SharePatchFileUtil.closeZip(zipFile3);
                            SharePatchFileUtil.closeZip(zipFile);
                            return false;
                        }
                        TinkerLog.w("Tinker.ResDiffPatchInternal", "success recover all large modify and store resources use time:%d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                        SharePatchFileUtil.closeZip(zipFile3);
                        SharePatchFileUtil.closeZip(zipFile);
                        return true;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
                zipFile2 = zipFile3;
            } catch (Throwable th6) {
                th = th6;
                zipFile2 = zipFile3;
                zipFile = null;
            }
        } catch (Throwable th7) {
            th = th7;
            zipFile = null;
            zipFile2 = null;
        }
        try {
            throw new TinkerRuntimeException("patch " + ShareTinkerInternals.getTypeString(i) + " extract failed (" + th.getMessage() + ").", th);
        } catch (Throwable th8) {
            SharePatchFileUtil.closeZip(zipFile2);
            SharePatchFileUtil.closeZip(zipFile);
            throw th8;
        }
    }

    private static boolean extractResourceDiffInternals(Context context, String str, String str2, File file, int i) throws NumberFormatException {
        TinkerZipFile tinkerZipFile;
        TinkerZipFile tinkerZipFile2;
        File file2 = file;
        int i2 = i;
        ShareResPatchInfo shareResPatchInfo = new ShareResPatchInfo();
        ShareResPatchInfo.parseAllResPatchInfo(str2, shareResPatchInfo);
        TinkerLog.i("Tinker.ResDiffPatchInternal", "res dir: %s, meta: %s", str, shareResPatchInfo.toString());
        Tinker tinkerWith = Tinker.with(context);
        if (!SharePatchFileUtil.checkIfMd5Valid(shareResPatchInfo.resArscMd5)) {
            TinkerLog.w("Tinker.ResDiffPatchInternal", "resource meta file md5 mismatch, type:%s, md5: %s", ShareTinkerInternals.getTypeString(i), shareResPatchInfo.resArscMd5);
            tinkerWith.getPatchReporter().onPatchPackageCheckFail(file2, BasePatchInternal.getMetaCorruptedCode(i));
            return false;
        }
        File file3 = new File(str);
        File file4 = new File(file3, "res_temp");
        File file5 = new File(file3, "resources.apk");
        if (!file5.exists()) {
            file5.getParentFile().mkdirs();
        } else {
            if (SharePatchFileUtil.checkResourceArscMd5(file5, shareResPatchInfo.resArscMd5)) {
                TinkerLog.w("Tinker.ResDiffPatchInternal", "resource file %s is already exist, and md5 match, just return true", file5.getPath());
                return true;
            }
            TinkerLog.w("Tinker.ResDiffPatchInternal", "have a mismatch corrupted resource " + file5.getPath(), new Object[0]);
            file5.delete();
        }
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                TinkerLog.w("Tinker.ResDiffPatchInternal", "applicationInfo == null!!!!", new Object[0]);
                return false;
            }
            String str3 = applicationInfo.sourceDir;
            if (!checkAndExtractResourceLargeFile(context, str3, file3, file4, file, shareResPatchInfo, i)) {
                return false;
            }
            TinkerZipOutputStream tinkerZipOutputStream = null;
            try {
                TinkerZipOutputStream tinkerZipOutputStream2 = new TinkerZipOutputStream(new BufferedOutputStream(new FileOutputStream(file5)));
                try {
                    tinkerZipFile = new TinkerZipFile(str3);
                    try {
                        tinkerZipFile2 = new TinkerZipFile(file2);
                        try {
                            Enumeration<? extends TinkerZipEntry> enumerationEntries = tinkerZipFile.entries();
                            int i3 = 0;
                            while (enumerationEntries.hasMoreElements()) {
                                TinkerZipEntry tinkerZipEntryNextElement = enumerationEntries.nextElement();
                                if (tinkerZipEntryNextElement == null) {
                                    throw new TinkerRuntimeException("zipEntry is null when get from oldApk");
                                }
                                String name = tinkerZipEntryNextElement.getName();
                                if (!name.contains("../") && ShareResPatchInfo.checkFileInPattern(shareResPatchInfo.patterns, name) && !shareResPatchInfo.deleteRes.contains(name) && !shareResPatchInfo.modRes.contains(name) && !shareResPatchInfo.largeModRes.contains(name) && !name.equals("AndroidManifest.xml")) {
                                    TinkerZipUtil.extractTinkerEntry(tinkerZipFile, tinkerZipEntryNextElement, tinkerZipOutputStream2);
                                    i3++;
                                }
                            }
                            TinkerZipEntry entry = tinkerZipFile.getEntry("AndroidManifest.xml");
                            if (entry == null) {
                                TinkerLog.w("Tinker.ResDiffPatchInternal", "manifest patch entry is null. path:AndroidManifest.xml", new Object[0]);
                                tinkerWith.getPatchReporter().onPatchTypeExtractFail(file2, file5, "AndroidManifest.xml", i2);
                                IOHelper.closeQuietly(tinkerZipOutputStream2);
                                IOHelper.closeQuietly(tinkerZipFile);
                                IOHelper.closeQuietly(tinkerZipFile2);
                                SharePatchFileUtil.deleteDir(file4);
                                return false;
                            }
                            TinkerZipUtil.extractTinkerEntry(tinkerZipFile, entry, tinkerZipOutputStream2);
                            int i4 = i3 + 1;
                            Iterator<String> it = shareResPatchInfo.largeModRes.iterator();
                            while (it.hasNext()) {
                                try {
                                    String next = it.next();
                                    TinkerZipEntry entry2 = tinkerZipFile.getEntry(next);
                                    if (entry2 == null) {
                                        TinkerLog.w("Tinker.ResDiffPatchInternal", "large patch entry is null. path:" + next, new Object[0]);
                                        tinkerWith.getPatchReporter().onPatchTypeExtractFail(file2, file5, next, i2);
                                        IOHelper.closeQuietly(tinkerZipOutputStream2);
                                        IOHelper.closeQuietly(tinkerZipFile);
                                        IOHelper.closeQuietly(tinkerZipFile2);
                                        SharePatchFileUtil.deleteDir(file4);
                                        return false;
                                    }
                                    ShareResPatchInfo.LargeModeInfo largeModeInfo = shareResPatchInfo.largeModMap.get(next);
                                    TinkerZipUtil.extractLargeModifyFile(entry2, largeModeInfo.file, largeModeInfo.crc, tinkerZipOutputStream2);
                                    i4++;
                                    file2 = file;
                                    i2 = i;
                                } catch (Throwable th) {
                                    th = th;
                                    tinkerZipOutputStream = tinkerZipOutputStream2;
                                    IOHelper.closeQuietly(tinkerZipOutputStream);
                                    IOHelper.closeQuietly(tinkerZipFile);
                                    IOHelper.closeQuietly(tinkerZipFile2);
                                    SharePatchFileUtil.deleteDir(file4);
                                    throw th;
                                }
                            }
                            Iterator<String> it2 = shareResPatchInfo.addRes.iterator();
                            while (it2.hasNext()) {
                                String next2 = it2.next();
                                TinkerZipEntry entry3 = tinkerZipFile2.getEntry(next2);
                                if (entry3 == null) {
                                    TinkerLog.w("Tinker.ResDiffPatchInternal", "add patch entry is null. path:" + next2, new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file5, next2, i);
                                    IOHelper.closeQuietly(tinkerZipOutputStream2);
                                    IOHelper.closeQuietly(tinkerZipFile);
                                    IOHelper.closeQuietly(tinkerZipFile2);
                                    SharePatchFileUtil.deleteDir(file4);
                                    return false;
                                }
                                if (shareResPatchInfo.storeRes.containsKey(next2)) {
                                    TinkerZipUtil.extractLargeModifyFile(entry3, shareResPatchInfo.storeRes.get(next2), entry3.getCrc(), tinkerZipOutputStream2);
                                } else {
                                    TinkerZipUtil.extractTinkerEntry(tinkerZipFile2, entry3, tinkerZipOutputStream2);
                                }
                                i4++;
                            }
                            Iterator<String> it3 = shareResPatchInfo.modRes.iterator();
                            while (it3.hasNext()) {
                                String next3 = it3.next();
                                TinkerZipEntry entry4 = tinkerZipFile2.getEntry(next3);
                                if (entry4 == null) {
                                    TinkerLog.w("Tinker.ResDiffPatchInternal", "mod patch entry is null. path:" + next3, new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file5, next3, i);
                                    IOHelper.closeQuietly(tinkerZipOutputStream2);
                                    IOHelper.closeQuietly(tinkerZipFile);
                                    IOHelper.closeQuietly(tinkerZipFile2);
                                    SharePatchFileUtil.deleteDir(file4);
                                    return false;
                                }
                                if (shareResPatchInfo.storeRes.containsKey(next3)) {
                                    TinkerZipUtil.extractLargeModifyFile(entry4, shareResPatchInfo.storeRes.get(next3), entry4.getCrc(), tinkerZipOutputStream2);
                                } else {
                                    TinkerZipUtil.extractTinkerEntry(tinkerZipFile2, entry4, tinkerZipOutputStream2);
                                }
                                i4++;
                            }
                            tinkerZipOutputStream2.setComment(tinkerZipFile.getComment());
                            IOHelper.closeQuietly(tinkerZipOutputStream2);
                            IOHelper.closeQuietly(tinkerZipFile);
                            IOHelper.closeQuietly(tinkerZipFile2);
                            SharePatchFileUtil.deleteDir(file4);
                            if (SharePatchFileUtil.checkResourceArscMd5(file5, shareResPatchInfo.resArscMd5)) {
                                TinkerLog.i("Tinker.ResDiffPatchInternal", "final new resource file:%s, entry count:%d, size:%d", file5.getAbsolutePath(), Integer.valueOf(i4), Long.valueOf(file5.length()));
                                return true;
                            }
                            TinkerLog.i("Tinker.ResDiffPatchInternal", "check final new resource file fail path:%s, entry count:%d, size:%d", file5.getAbsolutePath(), Integer.valueOf(i4), Long.valueOf(file5.length()));
                            SharePatchFileUtil.safeDeleteFile(file5);
                            tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file5, "resources.apk", i);
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        tinkerZipFile2 = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    tinkerZipFile = null;
                    tinkerZipFile2 = null;
                }
            } catch (Throwable th5) {
                th = th5;
                tinkerZipFile = null;
                tinkerZipFile2 = null;
            }
        } catch (Throwable th6) {
            throw new TinkerRuntimeException("patch " + ShareTinkerInternals.getTypeString(i) + " extract failed (" + th6.getMessage() + ").", th6);
        }
    }

    private static boolean patchResourceExtractViaResourceDiff(Context context, String str, String str2, File file) {
        if (extractResourceDiffInternals(context, str + "/res/", str2, file, 6)) {
            return true;
        }
        TinkerLog.w("Tinker.ResDiffPatchInternal", "patch recover, extractDiffInternals fail", new Object[0]);
        return false;
    }

    protected static boolean tryRecoverResourceFiles(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        if (!tinker.isEnabledForResource()) {
            TinkerLog.w("Tinker.ResDiffPatchInternal", "patch recover, resource is not enabled", new Object[0]);
            return true;
        }
        String str2 = shareSecurityCheck.getMetaContentMap().get("assets/res_meta.txt");
        if (str2 == null || str2.length() == 0) {
            TinkerLog.w("Tinker.ResDiffPatchInternal", "patch recover, resource is not contained", new Object[0]);
            return true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zPatchResourceExtractViaResourceDiff = patchResourceExtractViaResourceDiff(context, str, str2, file);
        TinkerLog.i("Tinker.ResDiffPatchInternal", "recover resource result:%b, cost:%d", Boolean.valueOf(zPatchResourceExtractViaResourceDiff), Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
        return zPatchResourceExtractViaResourceDiff;
    }
}
