package com.xh.xhcore.common.util;

import android.util.Log;
import com.xh.xhcore.common.statistic.XHEnvironment;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\n\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0007¨\u0006\u0011"}, d2 = {"Lcom/xh/xhcore/common/util/XhFileUtils;", "", "()V", "createDirs", "", "path", "", "createFile", "deleteDirs", "deleteFile", "getXhDir", "log", "", "msg", "readFile", "writeFile", "text", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XhFileUtils {
    public static final XhFileUtils INSTANCE = new XhFileUtils();

    private XhFileUtils() {
    }

    @JvmStatic
    public static final boolean createDirs(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            File file = new File(path);
            if (!file.exists() && !file.mkdirs()) {
                INSTANCE.log(Intrinsics.stringPlus("文件创建失败path=", path));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean createFile(String path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean deleteDirs(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                FilesKt.deleteRecursively(file);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean deleteFile(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final String getXhDir() {
        try {
            String str = XHEnvironment.getExternalStorageDirectory() + "/xuehai";
            INSTANCE.log(Intrinsics.stringPlus("path=", str));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return (String) null;
        }
    }

    @JvmStatic
    public static final String readFile(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            File file = new File(path);
            return file.exists() ? FilesKt.readText$default(file, null, 1, null) : (String) null;
        } catch (Exception e) {
            e.printStackTrace();
            return (String) null;
        }
    }

    @JvmStatic
    public static final boolean writeFile(String path, String text) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(text, "text");
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FilesKt.writeText$default(file, text, null, 2, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void log(String msg) {
        Log.e("XhFileUtils", msg);
    }
}
