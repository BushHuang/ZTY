package com.xh.xhcore.common.util;

import com.xh.xhcore.common.base.XhBaseApplication;
import java.io.File;

public class XHCleanUtil {
    private XHCleanUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean cleanCustomCache(File file) {
        return XHFileUtil.deleteFilesInDir(file);
    }

    public static boolean cleanCustomCache(String str) {
        return XHFileUtil.deleteFilesInDir(str);
    }

    public static boolean cleanExternalCache() {
        return XHSDCardUtil.isSDCardEnable() && XHFileUtil.deleteFilesInDir(XhBaseApplication.mContext.getExternalCacheDir());
    }

    public static boolean cleanInternalCache() {
        return XHFileUtil.deleteFilesInDir(XhBaseApplication.mContext.getCacheDir());
    }

    public static boolean cleanInternalDbByName(String str) {
        return XhBaseApplication.mContext.deleteDatabase(str);
    }

    public static boolean cleanInternalDbs() {
        return XHFileUtil.deleteFilesInDir(XhBaseApplication.mContext.getFilesDir().getParent() + File.separator + "databases");
    }

    public static boolean cleanInternalFiles() {
        return XHFileUtil.deleteFilesInDir(XhBaseApplication.mContext.getFilesDir());
    }

    public static boolean cleanInternalSP() {
        return XHFileUtil.deleteFilesInDir(XhBaseApplication.mContext.getFilesDir().getParent() + File.separator + "shared_prefs");
    }
}
