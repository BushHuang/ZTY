package com.zaze.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.zaze.utils.SharedPrefUtil;
import java.io.File;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\bH\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\r\u001a\u00020\bH\u0007J\b\u0010\u000e\u001a\u00020\bH\u0007J\b\u0010\u000f\u001a\u00020\bH\u0007J\b\u0010\u0010\u001a\u00020\bH\u0007J\b\u0010\u0011\u001a\u00020\bH\u0007J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0017"}, d2 = {"Lcom/zaze/utils/DeviceUtil;", "", "()V", "getAndroidId", "", "context", "Landroid/content/Context;", "getDataFreeSpace", "", "getDataTotalSpace", "getDeviceId", "getDeviceMemory", "Landroid/app/ActivityManager$MemoryInfo;", "getRuntimeFreeMemory", "getRuntimeMaxMemory", "getRuntimeTotalMemory", "getSdFreeSpace", "getSdTotalSpace", "getSerial", "getSimSerialNumber", "getTelephonyManager", "Landroid/telephony/TelephonyManager;", "getUUID", "util_release"}, k = 1, mv = {1, 4, 1})
public final class DeviceUtil {
    public static final DeviceUtil INSTANCE = new DeviceUtil();

    private DeviceUtil() {
    }

    @JvmStatic
    public static final String getAndroidId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        Intrinsics.checkNotNullExpressionValue(string, "Settings.Secure.getStrin…ttings.Secure.ANDROID_ID)");
        return string;
    }

    @JvmStatic
    public static final long getDataFreeSpace() {
        File dataDirectory = Environment.getDataDirectory();
        Intrinsics.checkNotNullExpressionValue(dataDirectory, "Environment.getDataDirectory()");
        return FileUtil.getFreeSpace(dataDirectory);
    }

    @JvmStatic
    public static final long getDataTotalSpace() {
        File dataDirectory = Environment.getDataDirectory();
        Intrinsics.checkNotNullExpressionValue(dataDirectory, "Environment.getDataDirectory()");
        return FileUtil.getTotalSpace(dataDirectory);
    }

    @JvmStatic
    public static final String getDeviceId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return Build.VERSION.SDK_INT >= 26 ? getTelephonyManager(context).getImei() : getTelephonyManager(context).getDeviceId();
        } catch (SecurityException unused) {
            return null;
        }
    }

    @JvmStatic
    public static final ActivityManager.MemoryInfo getDeviceMemory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    @JvmStatic
    public static final long getRuntimeFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    @JvmStatic
    public static final long getRuntimeMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    @JvmStatic
    public static final long getRuntimeTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    @JvmStatic
    public static final long getSdFreeSpace() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkNotNullExpressionValue(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        return FileUtil.getFreeSpace(externalStorageDirectory);
    }

    @JvmStatic
    public static final long getSdTotalSpace() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkNotNullExpressionValue(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        return FileUtil.getTotalSpace(externalStorageDirectory);
    }

    @JvmStatic
    public static final String getSimSerialNumber(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return getTelephonyManager(context).getSimSerialNumber();
        } catch (SecurityException unused) {
            return null;
        }
    }

    @JvmStatic
    public static final TelephonyManager getTelephonyManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("phone");
        if (systemService != null) {
            return (TelephonyManager) systemService;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
    }

    @JvmStatic
    public static final String getUUID(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String serial = INSTANCE.getSerial();
        String str = serial;
        if (!(str == null || str.length() == 0) && !Intrinsics.areEqual(serial, "unknown")) {
            return serial;
        }
        String simSerialNumber = getSimSerialNumber(context);
        String str2 = simSerialNumber;
        if (!(str2 == null || str2.length() == 0)) {
            return simSerialNumber;
        }
        String deviceId = getDeviceId(context);
        String str3 = deviceId;
        if (!(str3 == null || str3.length() == 0)) {
            return deviceId;
        }
        String androidId = getAndroidId(context);
        String str4 = androidId;
        if (!(str4 == null || str4.length() == 0) && !Intrinsics.areEqual("9774d56d682e549c", androidId)) {
            return androidId;
        }
        String str5 = (String) SharedPrefUtil.Companion.newInstance$default(SharedPrefUtil.INSTANCE, context, null, 2, null).get("getUUID", "");
        String str6 = str5;
        if (!(str6 == null || str6.length() == 0)) {
            return str5;
        }
        String string = UUID.randomUUID().toString();
        SharedPrefUtil.Companion.newInstance$default(SharedPrefUtil.INSTANCE, context, null, 2, null).commit("getUUID", string);
        return string;
    }

    public final String getSerial() {
        try {
            return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
        } catch (SecurityException unused) {
            return null;
        }
    }
}
