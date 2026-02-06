package com.xh.xhcore.common.config;

import android.text.TextUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.data.XmData;
import com.xh.xhcore.common.http.BaseMicroServer;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.XHEnvironment;
import com.xh.xhcore.common.util.MMKVUtil;
import com.xh.xhcore.common.util.XhPermissionUtil;
import com.xh.xhcore.common.util.XmFileUtil;
import com.xh.xhcore.common.util.XmJsonUtil;
import com.xuehai.provider.core.dto.CPVDUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class XmConfig {
    private static final String NET_CONFIG_PATH = "/ServerNetworkConfig.ini";
    private static final String V1_CONFIG_PATH = "/XMConfigV1_1.ini";
    private static final String V2_CONFIG_PATH = "/XMConfigV2.ini";
    private static final String V3_CONFIG_PATH = "/XMConfigV3.ini";
    private static final boolean saveMicroServerByMMKV = XHAppConfigProxy.getInstance().saveMicroServerByMMKV();
    private final String filePath;

    public static class XMPropertiesUtil {
        private static final Object LOCK = new Object();

        public static void clear(String str) {
            Properties propertiesLoad = load(str);
            propertiesLoad.clear();
            store(str, propertiesLoad);
        }

        public static String getProperty(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return null;
            }
            return load(str).getProperty(str2);
        }

        public static Properties load(String str) {
            Properties properties;
            FileInputStream fileInputStream;
            if (!XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")) {
                LogUtils.w("缺少权限，此处用于存储微服务地址相关信息");
                return new Properties();
            }
            synchronized (LOCK) {
                properties = new Properties();
                XmFileUtil.createFileNotExists(str);
                if (!TextUtils.isEmpty(str)) {
                    FileInputStream fileInputStream2 = null;
                    try {
                        try {
                            fileInputStream = new FileInputStream(str);
                            try {
                                properties.load(fileInputStream);
                            } catch (Exception e) {
                                e = e;
                                fileInputStream2 = fileInputStream;
                                e.printStackTrace();
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e2) {
                                        e = e2;
                                        e.printStackTrace();
                                        return properties;
                                    }
                                }
                                return properties;
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream2 = fileInputStream;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e4) {
                            e = e4;
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            e = e5;
                            e.printStackTrace();
                            return properties;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
            return properties;
        }

        public static void setProperty(String str, String str2, String str3) {
            if (str2 == null || str3 == null) {
                return;
            }
            Properties propertiesLoad = load(str);
            propertiesLoad.setProperty(str2, str3);
            store(str, propertiesLoad);
        }

        public static void store(String str, Properties properties) {
            FileOutputStream fileOutputStream;
            if (!XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")) {
                LogUtils.w("缺少权限，此处用于存储微服务地址相关信息");
                return;
            }
            synchronized (LOCK) {
                FileOutputStream fileOutputStream2 = null;
                try {
                    try {
                        fileOutputStream = new FileOutputStream(str, false);
                        try {
                            properties.store(fileOutputStream, "");
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream2 = fileOutputStream;
                            e.printStackTrace();
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e2) {
                                    e = e2;
                                    e.printStackTrace();
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream2 = fileOutputStream;
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                    }
                    try {
                        fileOutputStream.close();
                    } catch (IOException e5) {
                        e = e5;
                        e.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    private XmConfig(String str) {
        this.filePath = str;
    }

    private static void addMap(HashMap<String, BaseMicroServer.ServerUrlUnify> map, String str, String str2) {
        BaseMicroServer.ServerUrlUnify serverUrlUnify = (BaseMicroServer.ServerUrlUnify) XmJsonUtil.parseJson(str2, BaseMicroServer.ServerUrlUnify.class);
        if (serverUrlUnify != null) {
            map.put(str, serverUrlUnify);
        }
    }

    public static HashMap<String, BaseMicroServer.ServerUrlUnify> getAllMicroServer() {
        HashMap<String, BaseMicroServer.ServerUrlUnify> map = new HashMap<>();
        Properties propertiesLoad = XMPropertiesUtil.load(getDir(false) + "/XMConfigV1_1.ini");
        for (String str : propertiesLoad.stringPropertyNames()) {
            addMap(map, str, propertiesLoad.getProperty(str));
        }
        Properties propertiesLoad2 = XMPropertiesUtil.load(getDir(false) + "/XMConfigV2.ini");
        for (String str2 : propertiesLoad2.stringPropertyNames()) {
            addMap(map, str2, propertiesLoad2.getProperty(str2));
        }
        Properties propertiesLoad3 = XMPropertiesUtil.load(getDir(false) + "/XMConfigV3.ini");
        for (String str3 : propertiesLoad3.stringPropertyNames()) {
            addMap(map, str3, propertiesLoad3.getProperty(str3));
        }
        for (Map.Entry<String, String> entry : MMKVUtil.load(getDir(true) + "/XMConfigV1_1.ini").entrySet()) {
            addMap(map, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry2 : MMKVUtil.load(getDir(true) + "/XMConfigV2.ini").entrySet()) {
            addMap(map, entry2.getKey(), entry2.getValue());
        }
        for (Map.Entry<String, String> entry3 : MMKVUtil.load(getDir(true) + "/XMConfigV3.ini").entrySet()) {
            addMap(map, entry3.getKey(), entry3.getValue());
        }
        return map;
    }

    @Deprecated
    public static XmConfig getBoxConfig() {
        return new XmConfig(getDir() + "/BoxConfig.ini");
    }

    @Deprecated
    public static XmConfig getControlConfig() {
        return new XmConfig(getDir() + "/ControlConfig.ini");
    }

    public static String getDir() {
        return getDir(saveMicroServerByMMKV);
    }

    private static String getDir(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(getXueHaiSchoolIdDir());
        sb.append(z ? "/mmkv" : "");
        return sb.toString();
    }

    public static String getDynamicServerConfigDir() {
        return getXueHaiSchoolIdDir() + "/ServerNetworkConfig.ini";
    }

    public static String getEnvironmentString() {
        StringBuilder sb = new StringBuilder();
        CPVDUser user = XmData.getUser();
        if (user != null) {
            sb.append(user.getEnvironment());
            sb.append(File.separator);
        }
        LogUtils.d("environment = " + sb.toString());
        return sb.toString();
    }

    public static String getHttpProxyDirV2() {
        return getXueHaiSchoolIdDir() + "/httpProxy";
    }

    private static String getSchoolId() {
        CPVDUser user = XmData.getUser();
        return user != null ? String.valueOf(user.getSchoolId()) : "noUserSchoolId";
    }

    private static String getXueHaiConfigDir() {
        return XHEnvironment.getExternalStorageDirectory().getAbsolutePath() + "/xuehai/config";
    }

    private static String getXueHaiSchoolIdDir() {
        return getXueHaiConfigDir() + "/" + getEnvironmentString() + getSchoolId();
    }

    public static XmConfig newInstance() {
        return new XmConfig(getDir() + "/XMConfig.ini");
    }

    public static XmConfig newInstanceV1_1() {
        return new XmConfig(getDir() + "/XMConfigV1_1.ini");
    }

    public static XmConfig newInstanceV2() {
        return new XmConfig(getDir() + "/XMConfigV2.ini");
    }

    public static XmConfig newInstanceV3() {
        return new XmConfig(getDir() + "/XMConfigV3.ini");
    }

    public void clear() {
        if (saveMicroServerByMMKV) {
            MMKVUtil.clear(this.filePath);
        } else {
            XMPropertiesUtil.clear(this.filePath);
        }
    }

    public String getProperty(String str) {
        return !saveMicroServerByMMKV ? XMPropertiesUtil.getProperty(this.filePath, str) : MMKVUtil.getString(this.filePath, str, "");
    }

    public void setProperty(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        if (saveMicroServerByMMKV) {
            MMKVUtil.put(this.filePath, str, str2);
            return;
        }
        Properties propertiesLoad = XMPropertiesUtil.load(this.filePath);
        propertiesLoad.setProperty(str, str2);
        XMPropertiesUtil.store(this.filePath, propertiesLoad);
    }

    @Deprecated
    public <K, V> void store(K k, V v) {
        if (k == null || v == null) {
            return;
        }
        if (saveMicroServerByMMKV) {
            MMKVUtil.put(this.filePath, k.toString(), v);
            return;
        }
        Properties propertiesLoad = XMPropertiesUtil.load(this.filePath);
        propertiesLoad.put(k, v);
        XMPropertiesUtil.store(this.filePath, propertiesLoad);
    }

    public void storeAll(Map<String, String> map) {
        if (map == null) {
            return;
        }
        if (!saveMicroServerByMMKV) {
            Properties propertiesLoad = XMPropertiesUtil.load(this.filePath);
            propertiesLoad.putAll(map);
            XMPropertiesUtil.store(this.filePath, propertiesLoad);
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                MMKVUtil.put(this.filePath, entry.getKey(), entry.getValue());
            }
        }
    }
}
