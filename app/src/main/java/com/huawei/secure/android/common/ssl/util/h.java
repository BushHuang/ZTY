package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class h {

    private static final String f429a = "h";

    public static String a(String str) {
        Context contextA = c.a();
        if (contextA == null) {
            return "";
        }
        try {
            return contextA.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            g.b(f429a, "getVersion NameNotFoundException : " + e.getMessage());
            return "";
        } catch (Exception e2) {
            g.b(f429a, "getVersion: " + e2.getMessage());
            return "";
        } catch (Throwable unused) {
            g.b(f429a, "throwable");
            return "";
        }
    }

    public static int b(String str) {
        Context contextA = c.a();
        if (contextA == null) {
            return 0;
        }
        try {
            return contextA.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            g.b(f429a, "getVersion NameNotFoundException");
            return 0;
        } catch (Exception e) {
            g.b(f429a, "getVersion: " + e.getMessage());
            return 0;
        }
    }
}
