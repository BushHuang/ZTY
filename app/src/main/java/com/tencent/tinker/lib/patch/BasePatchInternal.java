package com.tencent.tinker.lib.patch;

import com.tencent.tinker.commons.util.IOHelper;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BasePatchInternal {
    protected static final String ARKHOT_META_FILE = "assets/arkHot_meta.txt";
    protected static final String DEX_META_FILE = "assets/dex_meta.txt";
    protected static final String DEX_OPTIMIZE_PATH = "odex";
    protected static final String DEX_PATH = "dex";
    protected static final int MAX_EXTRACT_ATTEMPTS = 2;
    protected static final String RES_META_FILE = "assets/res_meta.txt";
    protected static final String SO_META_FILE = "assets/so_meta.txt";
    protected static final String SO_PATH = "lib";
    protected static final String TAG = "Tinker.BasePatchInternal";
    protected static final int TYPE_ARKHOT_SO = 8;
    protected static final int TYPE_CLASS_N_DEX = 7;
    protected static final int TYPE_DEX = 3;
    protected static final int TYPE_LIBRARY = 5;
    protected static final int TYPE_RESOURCE = 6;

    public static boolean extract(ZipFile zipFile, ZipEntry zipEntry, File file, String str, boolean z) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        int i = 0;
        boolean z2 = false;
        while (i < 2 && !z2) {
            i++;
            TinkerLog.i("Tinker.BasePatchInternal", "try Extracting " + file.getPath(), new Object[0]);
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
            }
            try {
                byte[] bArr = new byte[16384];
                while (true) {
                    int i2 = bufferedInputStream.read(bArr);
                    if (i2 <= 0) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, i2);
                }
                IOHelper.closeQuietly(bufferedOutputStream);
                IOHelper.closeQuietly(bufferedInputStream);
                boolean zVerifyDexFileMd5 = str != null ? z ? SharePatchFileUtil.verifyDexFileMd5(file, str) : SharePatchFileUtil.verifyFileMd5(file, str) : true;
                TinkerLog.i("Tinker.BasePatchInternal", "isExtractionSuccessful: %b", Boolean.valueOf(zVerifyDexFileMd5));
                if (!zVerifyDexFileMd5 && (!file.delete() || file.exists())) {
                    TinkerLog.e("Tinker.BasePatchInternal", "Failed to delete corrupted dex " + file.getPath(), new Object[0]);
                }
                z2 = zVerifyDexFileMd5;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream2 = bufferedOutputStream;
                IOHelper.closeQuietly(bufferedOutputStream2);
                IOHelper.closeQuietly(bufferedInputStream);
                throw th;
            }
        }
        return z2;
    }

    public static int getMetaCorruptedCode(int i) {
        if (i == 3) {
            return -3;
        }
        if (i == 5) {
            return -4;
        }
        return i == 6 ? -8 : 0;
    }
}
