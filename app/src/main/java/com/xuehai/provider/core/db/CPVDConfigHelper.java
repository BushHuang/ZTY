package com.xuehai.provider.core.db;

import android.os.Environment;
import com.xuehai.provider.utils.CPVDUtil;
import java.io.File;
import java.util.Map;
import java.util.Properties;

class CPVDConfigHelper {
    private static final String FILE_DIR = Environment.getExternalStorageDirectory() + "/xuehai/keep";
    private String filePath;

    public CPVDConfigHelper(String str) {
        this.filePath = FILE_DIR + File.separator + str;
    }

    public boolean delete() {
        return new File(this.filePath).delete();
    }

    public CPVDConfigHelper getDefault() {
        return new CPVDConfigHelper(FILE_DIR + "/provider.ini");
    }

    public String getProperty(String str) {
        return CPVDPropertiesUtil.getProperty(this.filePath, str);
    }

    public Properties load() {
        return new File(this.filePath).exists() ? CPVDPropertiesUtil.load(this.filePath) : new Properties();
    }

    @Deprecated
    public void setProperty(String str, String str2) throws Throwable {
        store(str, str2);
    }

    public void store(String str, String str2) throws Throwable {
        if (str == null || str2 == null) {
            return;
        }
        try {
            CPVDUtil.createFile(this.filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Properties propertiesLoad = CPVDPropertiesUtil.load(this.filePath);
        propertiesLoad.put(str, str2);
        CPVDPropertiesUtil.store(this.filePath, propertiesLoad);
    }

    public void storeAll(Map<String, String> map) throws Throwable {
        if (map == null) {
            return;
        }
        try {
            CPVDUtil.createFile(this.filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Properties propertiesLoad = CPVDPropertiesUtil.load(this.filePath);
        propertiesLoad.putAll(map);
        CPVDPropertiesUtil.store(this.filePath, propertiesLoad);
    }
}
