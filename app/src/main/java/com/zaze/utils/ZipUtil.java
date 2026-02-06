package com.zaze.utils;

import com.zaze.utils.log.ZLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static void compressFile(String str, String str2) throws Throwable {
        ZLog.d("Compress[ZZ]", "zipFile : %s >> %s", str, str2);
        FileUtil.createDirNotExists(new File(str2).getParentFile());
        FileUtil.reCreateFile(str2);
        ZipOutputStream zipOutputStream = null;
        try {
            try {
                try {
                    ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(str2));
                    try {
                        File file = new File(str);
                        compressFiles(zipOutputStream2, file, file.getName() + File.separator);
                        zipOutputStream2.flush();
                        zipOutputStream2.closeEntry();
                        zipOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        zipOutputStream = zipOutputStream2;
                        e.printStackTrace();
                        if (zipOutputStream != null) {
                            zipOutputStream.closeEntry();
                            zipOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        zipOutputStream = zipOutputStream2;
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.closeEntry();
                                zipOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    private static void compressFiles(ZipOutputStream zipOutputStream, File file, String str) throws Exception {
        ZLog.v("Compress[ZZ]", "压缩文件: " + str + file.getName(), new Object[0]);
        if (zipOutputStream == null) {
            return;
        }
        if (!file.isFile()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                return;
            }
            for (File file2 : fileArrListFiles) {
                if (file2 != null) {
                    if (file2.isDirectory()) {
                        compressFiles(zipOutputStream, file2, str + file2.getName() + File.separator);
                    } else {
                        compressFiles(zipOutputStream, file2, str);
                    }
                }
            }
            return;
        }
        ZipEntry zipEntry = new ZipEntry(str + file.getName());
        FileInputStream fileInputStream = new FileInputStream(file);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bArr = new byte[4096];
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                zipOutputStream.write(bArr, 0, i);
            }
        }
    }

    public static List<File> getFileList(String str, boolean z, boolean z2) throws IOException {
        ZLog.d("Compress[ZZ]", "getFileList : %s ", str);
        ArrayList arrayList = null;
        try {
            ArrayList arrayList2 = new ArrayList();
            try {
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        zipInputStream.close();
                        return arrayList2;
                    }
                    String name = nextEntry.getName();
                    if (nextEntry.isDirectory()) {
                        File file = new File(name.substring(0, name.length() - 1));
                        if (z) {
                            arrayList2.add(file);
                        }
                    } else {
                        File file2 = new File(name);
                        if (z2) {
                            arrayList2.add(file2);
                        }
                    }
                }
            } catch (IOException e) {
                e = e;
                arrayList = arrayList2;
                e.printStackTrace();
                return arrayList;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static void unCompressToFolder(String str, String str2) throws IOException {
        ZLog.d("Compress[ZZ]", "unZipToFolder : %s >> %s ", str, str2);
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    return;
                }
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    FileUtil.createDirNotExists(new File(str2 + File.separator + name.substring(0, name.length() - 1)));
                } else {
                    String str3 = str2 + File.separator + name;
                    File file = new File(str3);
                    FileUtil.createFileNotExists(str3);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
