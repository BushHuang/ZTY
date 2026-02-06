package com.tencent.tinker.ziputils.ziputil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class TinkerZipUtil {
    private static final int BUFFER_SIZE = 16384;

    public static void extractLargeModifyFile(TinkerZipEntry tinkerZipEntry, File file, long j, TinkerZipOutputStream tinkerZipOutputStream) throws Throwable {
        BufferedInputStream bufferedInputStream;
        TinkerZipEntry tinkerZipEntry2 = new TinkerZipEntry(tinkerZipEntry);
        tinkerZipEntry2.setMethod(0);
        tinkerZipEntry2.setSize(file.length());
        tinkerZipEntry2.setCompressedSize(file.length());
        tinkerZipEntry2.setCrc(j);
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            tinkerZipOutputStream.putNextEntry(new TinkerZipEntry(tinkerZipEntry2));
            byte[] bArr = new byte[16384];
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i == -1) {
                    tinkerZipOutputStream.closeEntry();
                    bufferedInputStream.close();
                    return;
                }
                tinkerZipOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            throw th;
        }
    }

    public static void extractTinkerEntry(TinkerZipFile tinkerZipFile, TinkerZipEntry tinkerZipEntry, TinkerZipOutputStream tinkerZipOutputStream) throws Throwable {
        InputStream inputStream;
        try {
            inputStream = tinkerZipFile.getInputStream(tinkerZipEntry);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            tinkerZipOutputStream.putNextEntry(new TinkerZipEntry(tinkerZipEntry));
            byte[] bArr = new byte[16384];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    tinkerZipOutputStream.write(bArr, 0, i);
                }
            }
            tinkerZipOutputStream.closeEntry();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static boolean validateZipEntryName(File file, String str) {
        if (str != null && !str.isEmpty()) {
            try {
                String canonicalPath = file.getCanonicalPath();
                return file.toPath().resolve(str).toFile().getCanonicalPath().startsWith(canonicalPath + File.separator);
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
