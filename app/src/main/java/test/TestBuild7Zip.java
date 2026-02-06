package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.sevenzip4j.SevenZipArchiveOutputStream;
import org.sevenzip4j.archive.SevenZipEntry;

public class TestBuild7Zip {
    private static void copy(OutputStream outputStream, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static void main(String[] strArr) {
        SevenZipArchiveOutputStream sevenZipArchiveOutputStream;
        Exception e;
        try {
            sevenZipArchiveOutputStream = new SevenZipArchiveOutputStream(new File("test/test.7z"));
            try {
                File file = new File("test/7zC.txt");
                SevenZipEntry sevenZipEntry = new SevenZipEntry();
                setSevenZipEntryAttributes(file, sevenZipEntry);
                sevenZipArchiveOutputStream.putNextEntry(sevenZipEntry);
                copy(sevenZipArchiveOutputStream, new FileInputStream(file));
                File file2 = new File("test/7zFormat.txt");
                SevenZipEntry sevenZipEntry2 = new SevenZipEntry();
                setSevenZipEntryAttributes(file2, sevenZipEntry2);
                sevenZipArchiveOutputStream.putNextEntry(sevenZipEntry2);
                copy(sevenZipArchiveOutputStream, new FileInputStream(file2));
                File file3 = new File("test/history.txt");
                SevenZipEntry sevenZipEntry3 = new SevenZipEntry();
                setSevenZipEntryAttributes(file3, sevenZipEntry3);
                sevenZipArchiveOutputStream.putNextEntry(sevenZipEntry3);
                copy(sevenZipArchiveOutputStream, new FileInputStream(file3));
                File file4 = new File("test/test.txt");
                SevenZipEntry sevenZipEntry4 = new SevenZipEntry();
                setSevenZipEntryAttributes(file4, sevenZipEntry4);
                sevenZipArchiveOutputStream.putNextEntry(sevenZipEntry4);
                copy(sevenZipArchiveOutputStream, new FileInputStream(file4));
                File file5 = new File("test/verzeichnis1/7zCxx.txt");
                SevenZipEntry sevenZipEntry5 = new SevenZipEntry();
                setSevenZipEntryAttributes(file5, sevenZipEntry5);
                sevenZipEntry5.setName("verzeichnis1/" + file5.getName());
                sevenZipArchiveOutputStream.putNextEntry(sevenZipEntry5);
                copy(sevenZipArchiveOutputStream, new FileInputStream(file5));
                File file6 = new File("test/verzeichnis1");
                SevenZipEntry sevenZipEntry6 = new SevenZipEntry();
                setSevenZipEntryAttributes(file6, sevenZipEntry6);
                sevenZipEntry6.setSize(0L);
                sevenZipArchiveOutputStream.putNextEntry(sevenZipEntry6);
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
