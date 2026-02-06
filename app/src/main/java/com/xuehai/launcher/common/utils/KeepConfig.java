package com.xuehai.launcher.common.utils;

import com.zaze.utils.config.ConfigHelper;

public class KeepConfig {
    public static final String FILE_PATH = FilePath.getKeepDir() + "/keep.ini";
    private final ConfigHelper configHelper;

    private static final class InstanceHolder {
        static final KeepConfig INSTANCE = new KeepConfig();

        private InstanceHolder() {
        }
    }

    private KeepConfig() {
        this.configHelper = ConfigHelper.newInstance(FILE_PATH);
    }

    public static KeepConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public String getDeviceId() {
        return getInstance().getProperty("deviceId");
    }

    public String getProperty(String str) {
        return this.configHelper.getProperty(str);
    }

    public void saveDeviceId(String str) throws Throwable {
        getInstance().setProperty("deviceId", str);
    }

    public void setProperty(String str, String str2) throws Throwable {
        this.configHelper.setProperty(str, str2);
    }
}
