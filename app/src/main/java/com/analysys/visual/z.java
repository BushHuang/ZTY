package com.analysys.visual;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.JsonWriter;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.InternalAgent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class z implements ad {

    static String f156a = "visual_uuid";
    private static Map<String, String> b;

    private Map<String, String> a(Context context) {
        if (b == null) {
            HashMap map = new HashMap();
            map.put("$android_lib_version", "4.5.2");
            map.put("$android_os", "Android");
            map.put("$android_os_version", Build.VERSION.RELEASE == null ? "UNKNOWN" : Build.VERSION.RELEASE);
            map.put("$android_manufacturer", Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER);
            map.put("$android_brand", Build.BRAND == null ? "UNKNOWN" : Build.BRAND);
            map.put("$android_model", Build.MODEL != null ? Build.MODEL : "UNKNOWN");
            map.put("$device_id", b(context));
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                map.put("$android_app_version", packageInfo.versionName);
                map.put("$android_app_version_code", Integer.toString(packageInfo.versionCode));
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
            b = Collections.unmodifiableMap(map);
        }
        return b;
    }

    private String b(Context context) {
        String string = InternalAgent.getString(context, f156a, null);
        if (!InternalAgent.isEmpty(string)) {
            return string;
        }
        String imei = CommonUtils.getIMEI(context);
        if (!InternalAgent.isEmpty(imei)) {
            InternalAgent.setString(context, f156a, imei);
            return imei;
        }
        String androidID = CommonUtils.getAndroidID(context);
        if (!InternalAgent.isEmpty(androidID)) {
            InternalAgent.setString(context, f156a, androidID);
            return androidID;
        }
        String strC = c(context);
        if (!InternalAgent.isEmpty(strC)) {
            InternalAgent.setString(context, f156a, strC);
            return strC;
        }
        String strD = d(context);
        if (!InternalAgent.isEmpty(strD)) {
            InternalAgent.setString(context, f156a, strD);
        }
        return strD;
    }

    private String c(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        if (!InternalAgent.checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.os.Build");
            return (String) cls.getMethod("getSerial", new Class[0]).invoke(cls.newInstance(), new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String d(Context context) {
        String appId = InternalAgent.getAppId(context);
        String strValueOf = String.valueOf(System.currentTimeMillis());
        try {
            return new String(Base64.encode(MessageDigest.getInstance("MD5").digest(new String(appId + strValueOf).getBytes()), 0, 10, 0));
        } catch (Throwable unused) {
            return String.valueOf(System.currentTimeMillis());
        }
    }

    @Override
    public void a(Object obj, OutputStream outputStream) throws IOException {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Map<String, String> mapA = a(AnalysysUtil.getContext());
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(outputStream));
        try {
            jsonWriter.beginObject();
            jsonWriter.name("type").value("device_info_response");
            jsonWriter.name("payload").beginObject();
            jsonWriter.name("device_type").value("Android");
            jsonWriter.name("device_name").value(Build.BRAND + "/" + Build.MODEL);
            jsonWriter.name("scaled_density").value((double) displayMetrics.scaledDensity);
            jsonWriter.name("width").value((long) displayMetrics.widthPixels);
            jsonWriter.name("height").value((long) displayMetrics.heightPixels);
            for (Map.Entry<String, String> entry : mapA.entrySet()) {
                jsonWriter.name(entry.getKey()).value(entry.getValue());
            }
            jsonWriter.endObject();
            jsonWriter.endObject();
            ANSLog.i("analysys.visual", "Send ws command: device_info_response");
            try {
                jsonWriter.close();
            } catch (IOException e) {
                ANSLog.e("analysys.visual", "close websocket writer fail", e);
            }
        } catch (Throwable th) {
            try {
                ANSLog.e("analysys.visual", "send device_info to server fail", th);
                try {
                    jsonWriter.close();
                } catch (IOException e2) {
                    ANSLog.e("analysys.visual", "close websocket writer fail", e2);
                }
            } catch (Throwable th2) {
                try {
                    jsonWriter.close();
                } catch (IOException e3) {
                    ANSLog.e("analysys.visual", "close websocket writer fail", e3);
                }
                throw th2;
            }
        }
    }
}
