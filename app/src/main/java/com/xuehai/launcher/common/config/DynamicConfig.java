package com.xuehai.launcher.common.config;

import com.xuehai.launcher.common.constants.FilePath;
import com.zaze.utils.FileUtil;
import com.zaze.utils.config.ConfigHelper;

public class DynamicConfig {
    private static final String FILE_PATH = FilePath.getKeepDir() + "/launcher_dynamic.ini";
    private static ConfigHelper configHelper;

    public static void clearAll() {
        FileUtil.deleteFile(FILE_PATH);
    }

    private static ConfigHelper getConfig() {
        if (configHelper == null) {
            configHelper = ConfigHelper.newInstance(FILE_PATH);
        }
        return configHelper;
    }

    public static String getDownloadConfigUrl() {
        return getProperty("download_config_url");
    }

    public static String getProperty(String str) {
        return getConfig().getProperty(str);
    }

    public static boolean needUseInnerApk() {
        return "true".equals(getProperty("need_use_inner_apk"));
    }

    public static void setProperty(String str, String str2) throws Throwable {
        getConfig().setProperty(str, str2);
    }

    public static void updateUserInnerApkState(boolean z) throws Throwable {
        setProperty("need_use_inner_apk", Boolean.toString(z));
    }
}
