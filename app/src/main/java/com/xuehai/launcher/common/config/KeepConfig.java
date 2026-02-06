package com.xuehai.launcher.common.config;

import com.xuehai.launcher.common.constants.FilePath;
import com.zaze.utils.config.ConfigHelper;

public class KeepConfig {
    private static final String FILE_PATH = FilePath.getKeepDir() + "/keep.ini";
    private static ConfigHelper configHelper;

    private static ConfigHelper getConfig() {
        if (configHelper == null) {
            configHelper = ConfigHelper.newInstance(FILE_PATH);
        }
        return configHelper;
    }

    public static String getProperty(String str) {
        return getConfig().getProperty(str);
    }

    public static String getPurpose() {
        return getProperty("purpose");
    }

    public static String getServiceVersionCode() {
        return getProperty("serviceVersionCode");
    }

    public static void setProperty(String str, String str2) throws Throwable {
        getConfig().setProperty(str, str2);
    }

    public static void updatePurpose(int i) {
        setProperty("purpose", String.valueOf(i));
    }

    public static void updateServiceVersionCode(String str) {
        setProperty("serviceVersionCode", str);
    }
}
