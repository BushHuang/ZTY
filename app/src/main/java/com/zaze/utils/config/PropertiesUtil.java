package com.zaze.utils.config;

import android.text.TextUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static final String CREATE_TIME_KEY = "create_time";
    private static final String VERSION_KEY = "version_key";
    private static final String VERSION_VALUE = "1";

    public static String getProperty(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return load(str).getProperty(str2);
    }

    public static Properties load(String str) throws Throwable {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        if (!TextUtils.isEmpty(str)) {
            FileInputStream fileInputStream2 = null;
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                } catch (FileNotFoundException unused) {
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    properties.load(fileInputStream);
                    fileInputStream.close();
                } catch (FileNotFoundException unused2) {
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return properties;
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return properties;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return properties;
    }

    public static void store(String str, Properties properties) throws Throwable {
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2 = null;
        fileOutputStream = null;
        fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream3 = new FileOutputStream(str, false);
                    try {
                        ?? r0 = "1";
                        properties.put("version_key", "1");
                        properties.store(fileOutputStream3, "");
                        fileOutputStream3.close();
                        fileOutputStream = r0;
                    } catch (FileNotFoundException unused) {
                        fileOutputStream2 = fileOutputStream3;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                            fileOutputStream = fileOutputStream2;
                        }
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream3;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            fileOutputStream = fileOutputStream;
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream3;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException unused2) {
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
