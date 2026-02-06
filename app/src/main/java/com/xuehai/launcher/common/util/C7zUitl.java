package com.xuehai.launcher.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.sevenzip4j.SevenZipArchiveOutputStream;
import org.sevenzip4j.archive.SevenZipEntry;

public class C7zUitl {
    public static void compressFile(String str, String str2) {
        SevenZipArchiveOutputStream sevenZipArchiveOutputStream;
        Exception e;
        File file = new File(str);
        if (!file.isDirectory() || !file.exists()) {
            return;
        }
        try {
            sevenZipArchiveOutputStream = new SevenZipArchiveOutputStream(new File(str2));
            try {
                File[] fileArrListFiles = file.listFiles();
                for (File file2 : fileArrListFiles) {
                    SevenZipEntry sevenZipEntry = new SevenZipEntry();
                    setSevenZipEntryAttributes(file2, sevenZipEntry);
                    sevenZipArchiveOutputStream.putNextEntry(sevenZipEntry);
                    copy(sevenZipArchiveOutputStream, new FileInputStream(file2));
                }
                sevenZipArchiveOutputStream.finish();
                sevenZipArchiveOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                if (sevenZipArchiveOutputStream != null) {
                    try {
                        sevenZipArchiveOutputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                e.printStackTrace();
            }
        } catch (Exception e4) {
            sevenZipArchiveOutputStream = null;
            e = e4;
        }
    }

    private static void copy(OutputStream outputStream, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1048576];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    private static void setSevenZipEntryAttributes(File file, SevenZipEntry sevenZipEntry) {
        sevenZipEntry.setName(file.getName());
        sevenZipEntry.setSize(file.length());
        sevenZipEntry.setLastWriteTime(Long.valueOf(file.lastModified()));
        sevenZipEntry.setReadonly(!file.canWrite());
        sevenZipEntry.setHidden(file.isHidden());
        sevenZipEntry.setDirectory(file.isDirectory());
        sevenZipEntry.setArchive(true);
        sevenZipEntry.setSystem(false);
    }
}
