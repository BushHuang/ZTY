package com.dovar.dtoast;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DUtil {
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static int OS_MIUI = -1;
    protected static boolean enableLog = false;

    private static String getSystemProperty(String str, String str2) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            return str2;
        }
    }

    private static boolean is1707A01() {
        return is360() && "1707-A01".equals(Build.MODEL.toUpperCase());
    }

    private static boolean is360() {
        String upperCase = Build.MANUFACTURER.toUpperCase();
        return "360".equals(upperCase) || "QIKU".equals(upperCase);
    }

    private static boolean isMIUI() throws IOException {
        int i = OS_MIUI;
        boolean z = true;
        ?? r3 = 0;
        if (i != -1) {
            return i == 1;
        }
        if (Build.VERSION.SDK_INT > 25) {
            try {
                if (TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.code", "")) && TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name", ""))) {
                    if (TextUtils.isEmpty(getSystemProperty("ro.miui.internal.storage", ""))) {
                        z = false;
                    }
                }
                r3 = z;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                if (properties.getProperty("ro.miui.ui.version.code", null) == null && properties.getProperty("ro.miui.ui.version.name", null) == null && properties.getProperty("ro.miui.internal.storage", null) == null) {
                }
                r3 = z;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        OS_MIUI = r3;
        return r3;
    }

    public static boolean isWhiteList() {
        return isMIUI() || is1707A01();
    }

    public static void log(String str) {
        if (!enableLog || TextUtils.isEmpty(str)) {
            return;
        }
        Log.d("DToast", str);
    }
}
