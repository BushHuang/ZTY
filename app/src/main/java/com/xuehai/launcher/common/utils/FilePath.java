package com.xuehai.launcher.common.utils;

import com.xuehai.system.common.log.MdmLog;
import com.zaze.utils.FileUtil;
import java.io.File;

public class FilePath {
    private static final String TAG = "FilePath";

    public static String getBasePath() {
        return mkdirs(FileUtil.getSDCardRoot() + "/xuehai");
    }

    public static String getKeepDir() {
        return mkdirs(getBasePath() + "/keep");
    }

    private static String mkdirs(String str) {
        if (FileUtil.isFile(str)) {
            MdmLog.i("FilePath", " 该目录变成了文件，需要先删除该目录同名文件：" + str);
            FileUtil.deleteFile(str);
        }
        FileUtil.createDirNotExists(new File(str));
        return str;
    }
}
