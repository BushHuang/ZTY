package com.xuehai.launcher.common.constants;

import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.XHFileUtil;
import com.zaze.utils.FileUtil;

public class FilePath {
    private static final String TAG = "FilePath";

    public static String getBakPath() {
        return mkdirs(getBasePath() + "/bak");
    }

    public static String getBasePath() {
        return mkdirs(FileUtil.getSDCardRoot() + "/xuehai");
    }

    public static String getErrorLogDir() {
        return mkdirs(getLogDir() + "/error");
    }

    public static String getKeepDir() {
        return mkdirs(getBasePath() + "/keep");
    }

    public static String getLogDir() {
        return mkdirs(getBasePath() + "/log/mdm");
    }

    private static String mkdirs(String str) {
        if (XHFileUtil.isFile(str)) {
            LogUtils.i("FilePath 该目录变成了文件，需要先删除该目录同名文件：" + str);
            LogUtils.i("FilePath 删除该目录同名文件结果：" + XHFileUtil.deleteFile(str));
        }
        XHFileUtil.createOrExistsDir(str);
        return str;
    }
}
