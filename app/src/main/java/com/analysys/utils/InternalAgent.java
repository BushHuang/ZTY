package com.analysys.utils;

import android.content.Context;
import android.os.Build;
import com.analysys.ab;
import com.analysys.ai;
import com.analysys.network.NetworkUtils;
import com.analysys.process.AgentProcess;
import com.analysys.v;
import java.util.Locale;
import java.util.TimeZone;
import javax.net.ssl.SSLSocketFactory;

public class InternalAgent {
    public static final String DEV_SDK_VERSION_NAME = "4.5.2";
    public static final String PUSH_EVENT_CLICK_MSG = "$push_click";
    public static final String PUSH_EVENT_PROCESS_SUCCESS = "$push_process_success";
    public static final String PUSH_EVENT_RECEIVER_MSG = "$push_receiver_success";
    public static String uri;

    public static boolean checkClass(String str, String str2) {
        return CommonUtils.checkClass(str, str2);
    }

    public static void checkEventName(Object obj) {
        ai.a(obj);
    }

    public static void checkKey(Object obj) {
        ai.b(obj);
    }

    public static boolean checkPermission(Context context, String str) {
        return CommonUtils.checkPermission(context, str);
    }

    public static String checkUrl(String str) {
        return CommonUtils.checkUrl(str);
    }

    public static void checkValue(Object obj) {
        ai.c(obj);
    }

    public static SSLSocketFactory createSSL(Context context) {
        return CommonUtils.getSSLSocketFactory(context);
    }

    public static void d(Object... objArr) {
        ANSLog.d(objArr);
    }

    public static void e(Object... objArr) {
        ANSLog.e(objArr);
    }

    public static String getAppId(Context context) {
        return CommonUtils.getAppKey(context);
    }

    public static Object getBrand(Context context) {
        return Build.BRAND;
    }

    public static String getCarrierName(Context context) {
        return CommonUtils.getCarrierName(context);
    }

    public static String getChannel(Context context) {
        return CommonUtils.getChannel(context);
    }

    public static Object getCurrentTime(Context context) {
        return Long.valueOf(System.currentTimeMillis());
    }

    public static Object getDebugMode(Context context) {
        return CommonUtils.getDebugMode(context);
    }

    public static String getDeviceId(Context context) {
        if (AgentProcess.getInstance().getConfig().isAutoTrackDeviceId()) {
            return ab.g();
        }
        return null;
    }

    public static Object getDeviceLanguage(Context context) {
        return Locale.getDefault().getLanguage();
    }

    public static String getDeviceModel(Context context) {
        return Build.MODEL;
    }

    public static Float getFloat(Context context, String str, float f) {
        return Float.valueOf(SharedUtil.getFloat(context, str, f));
    }

    public static String getIMEI(Context context) {
        return CommonUtils.isAutoCollect(context, "ANALYSYS_AUTO_COLLECTION_IMEI") ? CommonUtils.getIMEI(context) : "";
    }

    public static String getLaunchSource(Context context) {
        return CommonUtils.getLaunchSource();
    }

    public static String getLibVersion(Context context) {
        return "4.5.2";
    }

    public static boolean getLogin(Context context) {
        return ab.d();
    }

    public static Object getMac(Context context) {
        return CommonUtils.isAutoCollect(context, "ANALYSYS_AUTO_COLLECTION_MAC") ? CommonUtils.getMac(context) : "";
    }

    public static String getManufacturer(Context context) {
        return Build.MANUFACTURER;
    }

    public static String getNetwork(Context context) {
        return NetworkUtils.networkType(context, true);
    }

    public static String getOSVersion(Context context) {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static Object getScreenHeight(Context context) {
        return CommonUtils.getScreenHeight(context);
    }

    public static Object getScreenWidth(Context context) {
        return CommonUtils.getScreenWidth(context);
    }

    public static String getSessionId(Context context) {
        return v.a(context).a();
    }

    public static String getSourceDetail(Context context) {
        return uri;
    }

    public static String getString(Context context, String str, String str2) {
        return SharedUtil.getString(context, str, str2);
    }

    public static String getTimeZone(Context context) {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static String getUserId(Context context) {
        return CommonUtils.getUserId(context);
    }

    public static String getVersionName(Context context) {
        return CommonUtils.getVersionName(context);
    }

    public static void i(Object... objArr) {
        ANSLog.i(objArr);
    }

    public static Object isCalibrated(Context context) {
        return Boolean.valueOf(Constants.isCalibration);
    }

    public static boolean isEmpty(Object obj) {
        return CommonUtils.isEmpty(obj);
    }

    public static Object isFirstDay(Context context) {
        return CommonUtils.isFirstDay(context);
    }

    public static Object isFirstTime(Context context) {
        return Boolean.valueOf(CommonUtils.isFirstStart(context));
    }

    public static boolean isNetworkAvailable(Context context) {
        return NetworkUtils.isNetworkAvailable(context);
    }

    public static String networkType(Context context) {
        return NetworkUtils.networkType(context, true);
    }

    public static void setFloat(Context context, String str, float f) {
        SharedUtil.setFloat(context, str, f);
    }

    public static void setString(Context context, String str, String str2) {
        SharedUtil.setString(context, str, str2);
    }

    public static void v(Object... objArr) {
        ANSLog.v(objArr);
    }

    public static void w(Object... objArr) {
        ANSLog.w(objArr);
    }
}
