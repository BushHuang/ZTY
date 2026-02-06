package com.zaze.utils.config;

import com.zaze.utils.FileUtil;
import java.io.File;
import java.util.Map;
import java.util.Properties;

public class ConfigHelper {
    private static final long MAX_SIZE = 10485760;
    private String filePath;
    private int saveMode;

    private ConfigHelper(String str, int i) {
        this.filePath = str;
        this.saveMode = i;
    }

    public static ConfigHelper newInstance(File file) {
        return newInstance(file.getAbsolutePath());
    }

    public static ConfigHelper newInstance(String str) {
        return newInstance(str, 0);
    }

    public static ConfigHelper newInstance(String str, int i) {
        return new ConfigHelper(str, i);
    }

    public String getProperty(String str) {
        return load().getProperty(str);
    }

    public Properties load() throws Throwable {
        boolean z;
        if (!FileUtil.exists(this.filePath) || new File(this.filePath).length() > 10485760) {
            FileUtil.reCreateFile(this.filePath);
            z = true;
        } else {
            z = false;
        }
        Properties propertiesLoad = PropertiesUtil.load(this.filePath);
        if (z) {
            propertiesLoad.setProperty("create_time", String.valueOf(System.currentTimeMillis()));
            PropertiesUtil.store(this.filePath, propertiesLoad);
        }
        return propertiesLoad;
    }

    public void remove(String str) throws Throwable {
        if (str == null) {
            return;
        }
        Properties propertiesLoad = load();
        propertiesLoad.remove(str);
        PropertiesUtil.store(this.filePath, propertiesLoad);
    }

    public void setProperty(String str, String str2) throws Throwable {
        if (str == null || str2 == null) {
            return;
        }
        Properties propertiesLoad = load();
        propertiesLoad.setProperty(str, str2);
        PropertiesUtil.store(this.filePath, propertiesLoad);
    }

    public void setProperty(Map<String, String> map) throws Throwable {
        if (map == null || map.isEmpty()) {
            return;
        }
        Properties propertiesLoad = load();
        propertiesLoad.putAll(map);
        PropertiesUtil.store(this.filePath, propertiesLoad);
    }
}
