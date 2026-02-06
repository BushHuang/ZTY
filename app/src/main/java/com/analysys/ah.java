package com.analysys;

import android.text.TextUtils;
import com.analysys.utils.ANSLog;
import com.analysys.utils.CommonUtils;
import org.json.JSONArray;

public class ah {
    public static void a() {
        ANSLog.w("Send message failed, please send message in the main process!");
    }

    public static void a(long j, long j2, long j3) {
        ANSLog.d("收到服务器的时间：" + CommonUtils.timeConversion(j));
        ANSLog.d("本地时间：" + CommonUtils.timeConversion(j2));
        ANSLog.d("时间相差：" + (j3 / 1000) + " 秒，数据将会进行时间校准。");
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ANSLog.w(str + ": set failed.");
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ANSLog.w(str + ": set failed." + str2);
    }

    public static void a(String str, JSONArray jSONArray) {
        ANSLog.d("Send message to server:" + str + "\n data:  " + jSONArray);
    }

    public static void a(String str, boolean z) {
        if (z) {
            ANSLog.d(str + ": set success.");
            return;
        }
        ANSLog.w(str + ": set failed.");
    }

    public static void a(boolean z) {
        if (z) {
            ANSLog.d("Encrypt success.");
        } else {
            ANSLog.w("Encrypt field.");
        }
    }

    public static void a(boolean z, String str) {
        if (z) {
            ANSLog.d("init: set success. Current channel: " + str);
            return;
        }
        ANSLog.w("init: set failed. Current channel: " + str);
    }

    public static void b() {
        ANSLog.w("Please make sure that the appKey in manifest matched with init API!");
    }

    public static void b(String str) {
        ANSLog.w(str);
    }

    public static void b(boolean z) {
        if (z) {
            ANSLog.d("Init Android Analysys Java sdk success, version: 4.5.2");
        } else {
            ANSLog.w("Please init Analysys Android SDK .");
        }
    }

    public static void b(boolean z, String str) {
        if (z) {
            ANSLog.d("init: set success. Current key: " + str);
            return;
        }
        ANSLog.w("init: set failed. Current key: " + str);
    }

    public static void c() {
        ANSLog.w("No network, Please check the network.");
    }

    public static void c(String str) {
        ANSLog.d("return code：" + str);
    }

    public static void c(boolean z) {
        if (z) {
            ANSLog.d("Data uploaded successfully.");
        } else {
            ANSLog.w("Data uploaded failed.");
        }
    }
}
