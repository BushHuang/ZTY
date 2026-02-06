package com.xh.xhcore.common.http.archi;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.upload.bean.UploadFileEntity;
import com.xh.xhcore.common.util.ZipUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtil {

    public interface UnZipListener {
        String onUnZipEntry(ZipEntry zipEntry) throws IOException;

        void onUnZipSuccess() throws IOException;
    }

    public static boolean containsHtml(String str) {
        return !TextUtils.isEmpty(str) && str.toLowerCase().contains("<html");
    }

    public static <T> T requireNonNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static JSONObject toJSONObject(String str) {
        if (containsHtml(str)) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static void unzip(String str, String str2) throws IOException {
        unzip(str, str2, null);
    }

    public static void unzip(String str, String str2, UnZipListener unZipListener) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
        ArrayList<File> arrayList = new ArrayList();
        while (true) {
            try {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    byte[] bArr = new byte[4096];
                    File file = new File(str2 + (unZipListener == null ? nextEntry.getName() : unZipListener.onUnZipEntry(nextEntry)));
                    Log.i("Unzip: entryFilePath = ", file.getAbsolutePath());
                    File file2 = new File(file.getParent());
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 4096);
                    while (true) {
                        int i = zipInputStream.read(bArr, 0, 4096);
                        if (i != -1) {
                            bufferedOutputStream.write(bArr, 0, i);
                        }
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    arrayList.add(file);
                } catch (IOException e) {
                    for (File file3 : arrayList) {
                        if (file3 != null && file3.exists()) {
                            file3.delete();
                        }
                    }
                    throw e;
                }
            } finally {
                zipInputStream.close();
                arrayList.clear();
            }
        }
        if (unZipListener != null) {
            unZipListener.onUnZipSuccess();
        }
    }

    public static File zip(File[] fileArr, String str, String str2) throws Throwable {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str + File.separator + str2);
        if (file2.exists()) {
            file2.delete();
        }
        try {
            ZipUtils.zipFiles(Arrays.asList(fileArr), file2, (String) null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file2;
    }

    private static void zipFilesInner(File[] fileArr, File file) throws IOException {
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(file), new CRC32()));
            for (File file2 : fileArr) {
                zipOneFile(zipOutputStream, file2);
            }
            zipOutputStream.flush();
            zipOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void zipOneFile(ZipOutputStream zipOutputStream, File file) throws IOException {
        LogUtils.d("文件名: " + file.getName());
        zipOutputStream.putNextEntry(new ZipEntry(SystemClock.uptimeMillis() + file.getName()));
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[1024];
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i == -1) {
                zipOutputStream.flush();
                fileInputStream.close();
                return;
            }
            zipOutputStream.write(bArr, 0, i);
        }
    }

    public static File zipUploadFiles(List<UploadFileEntity> list, String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str + File.separator + str2);
        if (file2.exists()) {
            file2.delete();
        }
        try {
            ZipUtils.zipUploadFiles(list, file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file2;
    }
}
