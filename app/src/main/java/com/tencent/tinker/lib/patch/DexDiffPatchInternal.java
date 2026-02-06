package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.SystemClock;
import com.tencent.tinker.commons.dexpatcher.DexPatchApplier;
import com.tencent.tinker.commons.util.DigestUtil;
import com.tencent.tinker.commons.util.IOHelper;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerDexOptimizer;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareDexDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareElfFile;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.tinker.ziputils.ziputil.AlignedZipOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DexDiffPatchInternal extends BasePatchInternal {
    protected static final int MAX_WAIT_COUNT = 120;
    protected static final String TAG = "Tinker.DexDiffPatchInternal";
    protected static final int WAIT_ASYN_OAT_TIME = 10000;
    private static ArrayList<File> optFiles = new ArrayList<>();
    private static ArrayList<ShareDexDiffPatchInfo> patchList = new ArrayList<>();
    private static HashMap<ShareDexDiffPatchInfo, File> classNDexInfo = new HashMap<>();
    private static boolean isVmArt = ShareTinkerInternals.isVmArt();

    private static boolean checkAllDexOptFile(ArrayList<File> arrayList, int i) {
        Iterator<File> it = arrayList.iterator();
        while (it.hasNext()) {
            File next = it.next();
            if (!SharePatchFileUtil.isLegalFile(next) && !SharePatchFileUtil.shouldAcceptEvenIfIllegal(next)) {
                TinkerLog.e("Tinker.DexDiffPatchInternal", "parallel dex optimizer file %s is not exist, just wait %d times", next.getName(), Integer.valueOf(i));
                return false;
            }
        }
        return true;
    }

    private static boolean checkClassNDexFiles(String str) {
        boolean z = false;
        if (!patchList.isEmpty() && isVmArt) {
            Iterator<ShareDexDiffPatchInfo> it = patchList.iterator();
            ShareDexDiffPatchInfo shareDexDiffPatchInfo = null;
            File file = null;
            while (it.hasNext()) {
                ShareDexDiffPatchInfo next = it.next();
                File file2 = new File(str + next.realName);
                if (ShareConstants.CLASS_N_PATTERN.matcher(file2.getName()).matches()) {
                    classNDexInfo.put(next, file2);
                }
                if (next.rawName.startsWith("test.dex")) {
                    shareDexDiffPatchInfo = next;
                    file = file2;
                }
            }
            if (shareDexDiffPatchInfo != null) {
                HashMap<ShareDexDiffPatchInfo, File> map = classNDexInfo;
                map.put(ShareTinkerInternals.changeTestDexToClassN(shareDexDiffPatchInfo, map.size() + 1), file);
            }
            File file3 = new File(str, "tinker_classN.apk");
            if (file3.exists()) {
                Iterator<ShareDexDiffPatchInfo> it2 = classNDexInfo.keySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    ShareDexDiffPatchInfo next2 = it2.next();
                    if (!SharePatchFileUtil.verifyDexFileMd5(file3, next2.rawName, next2.destMd5InArt)) {
                        TinkerLog.e("Tinker.DexDiffPatchInternal", "verify dex file md5 error, entry name; %s, file len: %d", next2.rawName, Long.valueOf(file3.length()));
                        break;
                    }
                }
                if (!z) {
                    SharePatchFileUtil.safeDeleteFile(file3);
                }
            }
            if (z) {
                Iterator<File> it3 = classNDexInfo.values().iterator();
                while (it3.hasNext()) {
                    SharePatchFileUtil.safeDeleteFile(it3.next());
                }
            }
        }
        return z;
    }

    private static boolean dexOptimizeDexFiles(Context context, List<File> list, String str, File file) {
        Tinker tinkerWith = Tinker.with(context);
        optFiles.clear();
        if (list != null) {
            File file2 = new File(str);
            if (!file2.exists() && !file2.mkdirs()) {
                TinkerLog.w("Tinker.DexDiffPatchInternal", "patch recover, make optimizeDexDirectoryFile fail", new Object[0]);
                return false;
            }
            Iterator<File> it = list.iterator();
            while (it.hasNext()) {
                optFiles.add(new File(SharePatchFileUtil.optimizedPathFor(it.next(), file2)));
            }
            TinkerLog.i("Tinker.DexDiffPatchInternal", "patch recover, try to optimize dex file count:%d, optimizeDexDirectory:%s", Integer.valueOf(list.size()), str);
            final Vector vector = new Vector();
            final Throwable[] thArr = new Throwable[1];
            TinkerDexOptimizer.optimizeAll(context, list, file2, new TinkerDexOptimizer.ResultCallback() {
                long startTime;

                @Override
                public void onFailed(File file3, File file4, Throwable th) {
                    TinkerLog.i("Tinker.DexDiffPatchInternal", "fail to parallel optimize dex %s use time %d", file3.getPath(), Long.valueOf(System.currentTimeMillis() - this.startTime));
                    vector.add(file3);
                    thArr[0] = th;
                }

                @Override
                public void onStart(File file3, File file4) {
                    this.startTime = System.currentTimeMillis();
                    TinkerLog.i("Tinker.DexDiffPatchInternal", "start to parallel optimize dex %s, size: %d", file3.getPath(), Long.valueOf(file3.length()));
                }

                @Override
                public void onSuccess(File file3, File file4, File file5) {
                    TinkerLog.i("Tinker.DexDiffPatchInternal", "success to parallel optimize dex %s, opt file:%s, opt file size: %d, use time %d", file3.getPath(), file5.getPath(), Long.valueOf(file5.length()), Long.valueOf(System.currentTimeMillis() - this.startTime));
                }
            });
            if (!vector.isEmpty()) {
                tinkerWith.getPatchReporter().onPatchDexOptFail(file, vector, thArr[0]);
                return false;
            }
        }
        return true;
    }

    private static boolean extractDexDiffInternals(Context context, String str, String str2, File file, int i) {
        ZipFile zipFile;
        String str3;
        String str4;
        String str5 = str;
        patchList.clear();
        ShareDexDiffPatchInfo.parseDexDiffPatchInfo(str2, patchList);
        int i2 = 1;
        char c = 0;
        if (patchList.isEmpty()) {
            TinkerLog.w("Tinker.DexDiffPatchInternal", "extract patch list is empty! type:%s:", ShareTinkerInternals.getTypeString(i));
            return true;
        }
        File file2 = new File(str5);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        Tinker tinkerWith = Tinker.with(context);
        ZipFile zipFile2 = null;
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                TinkerLog.w("Tinker.DexDiffPatchInternal", "applicationInfo == null!!!!", new Object[0]);
                SharePatchFileUtil.closeZip(null);
                SharePatchFileUtil.closeZip(null);
                return false;
            }
            ZipFile zipFile3 = new ZipFile(applicationInfo.sourceDir);
            try {
                zipFile = new ZipFile(file);
            } catch (Throwable th) {
                th = th;
                zipFile = null;
            }
            try {
                if (checkClassNDexFiles(str)) {
                    TinkerLog.w("Tinker.DexDiffPatchInternal", "class n dex file %s is already exist, and md5 match, just continue", "tinker_classN.apk");
                    SharePatchFileUtil.closeZip(zipFile3);
                    SharePatchFileUtil.closeZip(zipFile);
                    return true;
                }
                Iterator<ShareDexDiffPatchInfo> it = patchList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        ShareDexDiffPatchInfo next = it.next();
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (next.path.equals("")) {
                            str3 = next.rawName;
                        } else {
                            str3 = next.path + "/" + next.rawName;
                        }
                        String str6 = next.dexDiffMd5;
                        String str7 = next.oldDexCrC;
                        if (isVmArt || !next.destMd5InDvm.equals("0")) {
                            String str8 = isVmArt ? next.destMd5InArt : next.destMd5InDvm;
                            if (!SharePatchFileUtil.checkIfMd5Valid(str8)) {
                                TinkerLog.w("Tinker.DexDiffPatchInternal", "meta file md5 invalid, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), next.rawName, str8);
                                tinkerWith.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                                break;
                            }
                            Iterator<ShareDexDiffPatchInfo> it2 = it;
                            File file3 = new File(str5 + next.realName);
                            if (!file3.exists()) {
                                str4 = "meta file md5 invalid, type:%s, name: %s, md5: %s";
                                file3.getParentFile().mkdirs();
                            } else if (SharePatchFileUtil.verifyDexFileMd5(file3, str8)) {
                                TinkerLog.w("Tinker.DexDiffPatchInternal", "dex file %s is already exist, and md5 match, just continue", file3.getPath());
                                str5 = str;
                                it = it2;
                                i2 = 1;
                                c = 0;
                            } else {
                                str4 = "meta file md5 invalid, type:%s, name: %s, md5: %s";
                                TinkerLog.w("Tinker.DexDiffPatchInternal", "have a mismatch corrupted dex " + file3.getPath(), new Object[0]);
                                file3.delete();
                            }
                            ZipEntry entry = zipFile.getEntry(str3);
                            ZipEntry entry2 = zipFile3.getEntry(str3);
                            String str9 = str8;
                            if (str7.equals("0")) {
                                if (entry == null) {
                                    TinkerLog.w("Tinker.DexDiffPatchInternal", "patch entry is null. path:" + str3, new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                    break;
                                }
                                if (!extractDexFile(zipFile, entry, file3, next)) {
                                    TinkerLog.w("Tinker.DexDiffPatchInternal", "Failed to extract raw patch file " + file3.getPath(), new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                    break;
                                }
                                str5 = str;
                                it = it2;
                                i2 = 1;
                                c = 0;
                            } else if (str6.equals("0")) {
                                if (isVmArt) {
                                    if (entry2 != null) {
                                        String strValueOf = String.valueOf(entry2.getCrc());
                                        if (!strValueOf.equals(str7)) {
                                            TinkerLog.e("Tinker.DexDiffPatchInternal", "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str7, strValueOf);
                                            tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                            break;
                                        }
                                        extractDexFile(zipFile3, entry2, file3, next);
                                        if (!SharePatchFileUtil.verifyDexFileMd5(file3, str9)) {
                                            TinkerLog.w("Tinker.DexDiffPatchInternal", "Failed to recover dex file when verify patched dex: " + file3.getPath(), new Object[0]);
                                            tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                            SharePatchFileUtil.safeDeleteFile(file3);
                                            break;
                                        }
                                    } else {
                                        TinkerLog.w("Tinker.DexDiffPatchInternal", "apk entry is null. path:" + str3, new Object[0]);
                                        tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                        break;
                                    }
                                }
                                str5 = str;
                                it = it2;
                                i2 = 1;
                                c = 0;
                            } else {
                                if (entry == null) {
                                    TinkerLog.w("Tinker.DexDiffPatchInternal", "patch entry is null. path:" + str3, new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                    break;
                                }
                                if (!SharePatchFileUtil.checkIfMd5Valid(str6)) {
                                    TinkerLog.w("Tinker.DexDiffPatchInternal", str4, ShareTinkerInternals.getTypeString(i), next.rawName, str6);
                                    tinkerWith.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                                    break;
                                }
                                if (entry2 == null) {
                                    TinkerLog.w("Tinker.DexDiffPatchInternal", "apk entry is null. path:" + str3, new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                    break;
                                }
                                String strValueOf2 = String.valueOf(entry2.getCrc());
                                if (!strValueOf2.equals(str7)) {
                                    TinkerLog.e("Tinker.DexDiffPatchInternal", "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str7, strValueOf2);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                    break;
                                }
                                patchDexFile(zipFile3, zipFile, entry2, entry, next, file3);
                                if (!SharePatchFileUtil.verifyDexFileMd5(file3, str9)) {
                                    TinkerLog.w("Tinker.DexDiffPatchInternal", "Failed to recover dex file when verify patched dex: " + file3.getPath(), new Object[0]);
                                    tinkerWith.getPatchReporter().onPatchTypeExtractFail(file, file3, next.rawName, i);
                                    SharePatchFileUtil.safeDeleteFile(file3);
                                    break;
                                }
                                TinkerLog.w("Tinker.DexDiffPatchInternal", "success recover dex file: %s, size: %d, use time: %d", file3.getPath(), Long.valueOf(file3.length()), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                                str5 = str;
                                it = it2;
                                i2 = 1;
                                c = 0;
                            }
                        } else {
                            Object[] objArr = new Object[i2];
                            objArr[c] = str3;
                            TinkerLog.w("Tinker.DexDiffPatchInternal", "patch dex %s is only for art, just continue", objArr);
                        }
                    } else if (mergeClassNDexFiles(context, file, str5)) {
                        SharePatchFileUtil.closeZip(zipFile3);
                        SharePatchFileUtil.closeZip(zipFile);
                        return true;
                    }
                }
                SharePatchFileUtil.closeZip(zipFile3);
                SharePatchFileUtil.closeZip(zipFile);
                return false;
            } catch (Throwable th2) {
                th = th2;
                zipFile2 = zipFile3;
                try {
                    throw new TinkerRuntimeException("patch " + ShareTinkerInternals.getTypeString(i) + " extract failed (" + th.getMessage() + ").", th);
                } catch (Throwable th3) {
                    SharePatchFileUtil.closeZip(zipFile2);
                    SharePatchFileUtil.closeZip(zipFile);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            zipFile = null;
        }
    }

    private static boolean extractDexFile(ZipFile zipFile, ZipEntry zipEntry, File file, ShareDexDiffPatchInfo shareDexDiffPatchInfo) throws IOException {
        String str = isVmArt ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
        return (SharePatchFileUtil.isRawDexFile(shareDexDiffPatchInfo.rawName) && shareDexDiffPatchInfo.isJarMode) ? extractDexToJar(zipFile, zipEntry, file, str) : extract(zipFile, zipEntry, file, str, true);
    }

    private static boolean extractDexToJar(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws Throwable {
        ZipOutputStream zipOutputStream;
        BufferedInputStream bufferedInputStream;
        int i = 0;
        boolean zVerifyDexFileMd5 = false;
        while (i < 2 && !zVerifyDexFileMd5) {
            i++;
            TinkerLog.i("Tinker.DexDiffPatchInternal", "try Extracting " + file.getPath(), new Object[0]);
            BufferedInputStream bufferedInputStream2 = null;
            try {
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                try {
                    bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                zipOutputStream = null;
            }
            try {
                byte[] bArr = new byte[16384];
                zipOutputStream.putNextEntry(new ZipEntry("classes.dex"));
                for (int i2 = bufferedInputStream.read(bArr); i2 != -1; i2 = bufferedInputStream.read(bArr)) {
                    zipOutputStream.write(bArr, 0, i2);
                }
                zipOutputStream.closeEntry();
                IOHelper.closeQuietly(bufferedInputStream);
                IOHelper.closeQuietly(zipOutputStream);
                zVerifyDexFileMd5 = SharePatchFileUtil.verifyDexFileMd5(file, str);
                TinkerLog.i("Tinker.DexDiffPatchInternal", "isExtractionSuccessful: %b", Boolean.valueOf(zVerifyDexFileMd5));
                if (!zVerifyDexFileMd5 && (!file.delete() || file.exists())) {
                    TinkerLog.e("Tinker.DexDiffPatchInternal", "Failed to delete corrupted dex " + file.getPath(), new Object[0]);
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream2 = bufferedInputStream;
                IOHelper.closeQuietly(bufferedInputStream2);
                IOHelper.closeQuietly(zipOutputStream);
                throw th;
            }
        }
        return zVerifyDexFileMd5;
    }

    private static ZipEntry makeStoredZipEntry(ZipEntry zipEntry, String str) {
        ZipEntry zipEntry2 = new ZipEntry(str);
        zipEntry2.setMethod(0);
        zipEntry2.setCompressedSize(zipEntry.getSize());
        zipEntry2.setSize(zipEntry.getSize());
        zipEntry2.setCrc(zipEntry.getCrc());
        return zipEntry2;
    }

    private static boolean mergeClassNDexFiles(Context context, File file, String str) {
        boolean z;
        ZipFile zipFile;
        ZipEntry zipEntryMakeStoredZipEntry;
        ?? inputStream;
        if (patchList.isEmpty() || !isVmArt) {
            return true;
        }
        File file2 = new File(str, "tinker_classN.apk");
        if (classNDexInfo.isEmpty()) {
            TinkerLog.w("Tinker.DexDiffPatchInternal", "classNDexInfo size: %d, no need to merge classN dex files", Integer.valueOf(classNDexInfo.size()));
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        AlignedZipOutputStream alignedZipOutputStream = null;
        try {
            AlignedZipOutputStream alignedZipOutputStream2 = new AlignedZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
            try {
                for (ShareDexDiffPatchInfo shareDexDiffPatchInfo : classNDexInfo.keySet()) {
                    File file3 = classNDexInfo.get(shareDexDiffPatchInfo);
                    if (shareDexDiffPatchInfo.isJarMode) {
                        try {
                            zipFile = new ZipFile(file3);
                            try {
                                ZipEntry entry = zipFile.getEntry("classes.dex");
                                zipEntryMakeStoredZipEntry = makeStoredZipEntry(entry, shareDexDiffPatchInfo.rawName);
                                inputStream = zipFile.getInputStream(entry);
                            } catch (Throwable th) {
                                th = th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            zipFile = null;
                        }
                        try {
                            try {
                                alignedZipOutputStream2.putNextEntry(zipEntryMakeStoredZipEntry);
                                IOHelper.copyStream(inputStream, alignedZipOutputStream2);
                                IOHelper.closeQuietly(inputStream);
                                IOHelper.closeQuietly(zipFile);
                            } finally {
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            alignedZipOutputStream = inputStream;
                            IOHelper.closeQuietly(alignedZipOutputStream);
                            IOHelper.closeQuietly(zipFile);
                            throw th;
                        }
                    } else {
                        ZipEntry zipEntry = new ZipEntry(shareDexDiffPatchInfo.rawName);
                        zipEntry.setMethod(0);
                        zipEntry.setCompressedSize(file3.length());
                        zipEntry.setSize(file3.length());
                        zipEntry.setCrc(DigestUtil.getCRC32(file3));
                        try {
                            ?? bufferedInputStream = new BufferedInputStream(new FileInputStream(file3));
                            try {
                                try {
                                    alignedZipOutputStream2.putNextEntry(zipEntry);
                                    IOHelper.copyStream(bufferedInputStream, alignedZipOutputStream2);
                                    IOHelper.closeQuietly(bufferedInputStream);
                                } finally {
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                alignedZipOutputStream = bufferedInputStream;
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    }
                }
                IOHelper.closeQuietly(alignedZipOutputStream2);
                z = true;
            } catch (Throwable th6) {
                th = th6;
                alignedZipOutputStream = alignedZipOutputStream2;
                try {
                    TinkerLog.printErrStackTrace("Tinker.DexDiffPatchInternal", th, "merge classN file", new Object[0]);
                    IOHelper.closeQuietly(alignedZipOutputStream);
                    z = false;
                    if (z) {
                    }
                    if (z) {
                    }
                    TinkerLog.i("Tinker.DexDiffPatchInternal", "merge classN dex file %s, result: %b, size: %d, use: %dms", file2.getPath(), Boolean.valueOf(z), Long.valueOf(file2.length()), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    return z;
                } finally {
                    IOHelper.closeQuietly(alignedZipOutputStream);
                }
            }
        } catch (Throwable th7) {
            th = th7;
        }
        if (z) {
            Iterator<ShareDexDiffPatchInfo> it = classNDexInfo.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ShareDexDiffPatchInfo next = it.next();
                if (!SharePatchFileUtil.verifyDexFileMd5(file2, next.rawName, next.destMd5InArt)) {
                    TinkerLog.e("Tinker.DexDiffPatchInternal", "verify dex file md5 error, entry name; %s, file len: %d", next.rawName, Long.valueOf(file2.length()));
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            TinkerLog.e("Tinker.DexDiffPatchInternal", "merge classN dex error, try delete temp file", new Object[0]);
            SharePatchFileUtil.safeDeleteFile(file2);
            Tinker.with(context).getPatchReporter().onPatchTypeExtractFail(file, file2, file2.getName(), 7);
        } else {
            Iterator<File> it2 = classNDexInfo.values().iterator();
            while (it2.hasNext()) {
                SharePatchFileUtil.safeDeleteFile(it2.next());
            }
        }
        TinkerLog.i("Tinker.DexDiffPatchInternal", "merge classN dex file %s, result: %b, size: %d, use: %dms", file2.getPath(), Boolean.valueOf(z), Long.valueOf(file2.length()), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return z;
    }

    private static boolean patchDexExtractViaDexDiff(Context context, String str, String str2, File file) {
        String str3 = str + "/dex/";
        if (!extractDexDiffInternals(context, str3, str2, file, 3)) {
            TinkerLog.w("Tinker.DexDiffPatchInternal", "patch recover, extractDiffInternals fail", new Object[0]);
            return false;
        }
        File[] fileArrListFiles = new File(str3).listFiles();
        ArrayList arrayList = new ArrayList();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                String name = file2.getName();
                if (file2.isFile() && (name.endsWith(".dex") || name.endsWith(".jar") || name.endsWith(".apk"))) {
                    arrayList.add(file2);
                }
            }
        }
        TinkerLog.i("Tinker.DexDiffPatchInternal", "legal files to do dexopt: " + arrayList, new Object[0]);
        return dexOptimizeDexFiles(context, arrayList, str + "/odex/", file);
    }

    private static void patchDexFile(ZipFile zipFile, ZipFile zipFile2, ZipEntry zipEntry, ZipEntry zipEntry2, ShareDexDiffPatchInfo shareDexDiffPatchInfo, File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        BufferedInputStream bufferedInputStream3;
        ZipOutputStream zipOutputStream;
        ZipEntry nextEntry;
        BufferedInputStream bufferedInputStream4 = null;
        try {
            bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            if (zipEntry2 != null) {
                try {
                    bufferedInputStream3 = new BufferedInputStream(zipFile2.getInputStream(zipEntry2));
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = null;
                    bufferedInputStream4 = bufferedInputStream2;
                    IOHelper.closeQuietly(bufferedInputStream4);
                    IOHelper.closeQuietly(bufferedInputStream);
                    throw th;
                }
            } else {
                bufferedInputStream3 = null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
        try {
            boolean zIsRawDexFile = SharePatchFileUtil.isRawDexFile(shareDexDiffPatchInfo.rawName);
            if (!zIsRawDexFile || shareDexDiffPatchInfo.isJarMode) {
                try {
                    zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    zipOutputStream.putNextEntry(new ZipEntry("classes.dex"));
                    if (zIsRawDexFile) {
                        new DexPatchApplier(bufferedInputStream2, bufferedInputStream3).executeAndSaveTo(zipOutputStream);
                    } else {
                        try {
                            ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream2);
                            do {
                                try {
                                    nextEntry = zipInputStream.getNextEntry();
                                    if (nextEntry == null) {
                                        break;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    bufferedInputStream4 = zipInputStream;
                                    IOHelper.closeQuietly(bufferedInputStream4);
                                    throw th;
                                }
                            } while (!"classes.dex".equals(nextEntry.getName()));
                            if (nextEntry == null) {
                                throw new TinkerRuntimeException("can't recognize zip dex format file:" + file.getAbsolutePath());
                            }
                            new DexPatchApplier(zipInputStream, bufferedInputStream3).executeAndSaveTo(zipOutputStream);
                            IOHelper.closeQuietly(zipInputStream);
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    }
                    zipOutputStream.closeEntry();
                    IOHelper.closeQuietly(zipOutputStream);
                } catch (Throwable th6) {
                    th = th6;
                    bufferedInputStream4 = zipOutputStream;
                    IOHelper.closeQuietly(bufferedInputStream4);
                    throw th;
                }
            } else {
                new DexPatchApplier(bufferedInputStream2, bufferedInputStream3).executeAndSaveTo(file);
            }
            IOHelper.closeQuietly(bufferedInputStream2);
            IOHelper.closeQuietly(bufferedInputStream3);
        } catch (Throwable th7) {
            bufferedInputStream4 = bufferedInputStream2;
            bufferedInputStream = bufferedInputStream3;
            th = th7;
            IOHelper.closeQuietly(bufferedInputStream4);
            IOHelper.closeQuietly(bufferedInputStream);
            throw th;
        }
    }

    protected static boolean tryRecoverDexFiles(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        if (!tinker.isEnabledForDex()) {
            TinkerLog.w("Tinker.DexDiffPatchInternal", "patch recover, dex is not enabled", new Object[0]);
            return true;
        }
        String str2 = shareSecurityCheck.getMetaContentMap().get("assets/dex_meta.txt");
        if (str2 == null) {
            TinkerLog.w("Tinker.DexDiffPatchInternal", "patch recover, dex is not contained", new Object[0]);
            return true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zPatchDexExtractViaDexDiff = patchDexExtractViaDexDiff(context, str, str2, file);
        TinkerLog.i("Tinker.DexDiffPatchInternal", "recover dex result:%b, cost:%d", Boolean.valueOf(zPatchDexExtractViaDexDiff), Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
        return zPatchDexExtractViaDexDiff;
    }

    protected static boolean waitAndCheckDexOptFile(File file, Tinker tinker) throws InterruptedException {
        if (optFiles.isEmpty()) {
            return true;
        }
        int size = patchList.size() * 30;
        if (size > 120) {
            size = 120;
        }
        TinkerLog.i("Tinker.DexDiffPatchInternal", "raw dex count: %d, dex opt dex count: %d, final wait times: %d", Integer.valueOf(patchList.size()), Integer.valueOf(optFiles.size()), Integer.valueOf(size));
        int i = 0;
        while (i < size) {
            i++;
            if (!checkAllDexOptFile(optFiles, i)) {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    TinkerLog.e("Tinker.DexDiffPatchInternal", "thread sleep InterruptedException e:" + e, new Object[0]);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<File> it = optFiles.iterator();
        while (it.hasNext()) {
            File next = it.next();
            TinkerLog.i("Tinker.DexDiffPatchInternal", "check dex optimizer file exist: %s, size %d", next.getPath(), Long.valueOf(next.length()));
            if (!SharePatchFileUtil.isLegalFile(next) && !SharePatchFileUtil.shouldAcceptEvenIfIllegal(next)) {
                TinkerLog.e("Tinker.DexDiffPatchInternal", "final parallel dex optimizer file %s is not exist, return false", next.getName());
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            tinker.getPatchReporter().onPatchDexOptFail(file, arrayList, new TinkerRuntimeException("checkDexOptExist failed"));
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Iterator<File> it2 = optFiles.iterator();
            Throwable th = null;
            while (it2.hasNext()) {
                File next2 = it2.next();
                if (!SharePatchFileUtil.shouldAcceptEvenIfIllegal(next2)) {
                    TinkerLog.i("Tinker.DexDiffPatchInternal", "check dex optimizer file format: %s, size %d", next2.getName(), Long.valueOf(next2.length()));
                    try {
                        if (ShareElfFile.getFileTypeByMagic(next2) == 1) {
                            try {
                                IOHelper.closeQuietly(new ShareElfFile(next2));
                            } finally {
                                th = th;
                                try {
                                } finally {
                                }
                            }
                        } else {
                            continue;
                        }
                    } catch (IOException unused) {
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                tinker.getPatchReporter().onPatchDexOptFail(file, arrayList, th == null ? new TinkerRuntimeException("checkDexOptFormat failed") : new TinkerRuntimeException("checkDexOptFormat failed", th));
                return false;
            }
        }
        return true;
    }
}
