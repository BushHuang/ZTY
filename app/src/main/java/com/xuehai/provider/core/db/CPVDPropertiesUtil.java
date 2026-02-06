package com.xuehai.provider.core.db;

import android.text.TextUtils;
import com.xuehai.provider.log.ContentProviderLog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class CPVDPropertiesUtil {
    private static final String VERSION_KEY = "version_key";
    private static final String VERSION_VALUE = "1";

    CPVDPropertiesUtil() {
    }

    public static String getProperty(String str, String str2) throws Throwable {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Properties propertiesLoad = load(str);
            if ("1".equals(propertiesLoad.getProperty("version_key"))) {
                return propertiesLoad.getProperty(str2);
            }
            ContentProviderLog.e("", "版本不匹配, 配置文件中不返回数据");
        }
        return null;
    }

    public static Properties load(String str) throws Throwable {
        Properties properties = new Properties();
        if (!TextUtils.isEmpty(str)) {
            FileInputStream fileInputStream = null;
            try {
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(str);
                    try {
                        properties.load(fileInputStream2);
                        fileInputStream2.close();
                    } catch (FileNotFoundException unused) {
                        fileInputStream = fileInputStream2;
                        ContentProviderLog.e("", str + " 配置文件不存在");
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return properties;
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return properties;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException unused2) {
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return properties;
    }

    public static void store(String str, Properties properties) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(str, false);
                    try {
                        properties.put("version_key", "1");
                        properties.store(fileOutputStream2, "");
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
