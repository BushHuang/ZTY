package com.xuehai.system.common.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.xuehai.system.common.log.MdmLog;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0007J'\u0010\u001a\u001a\u0004\u0018\u00010\u00042\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u001c\"\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010!\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u0004J\u000e\u0010#\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010$\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020\u0004J\b\u0010&\u001a\u0004\u0018\u00010\u0004J+\u0010'\u001a\u00020(2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u001c\"\u00020\u0004H\u0007¢\u0006\u0002\u0010)J\u000e\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020\u0004J\u0012\u0010,\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010.\u001a\u00020\u00042\b\u0010/\u001a\u0004\u0018\u000100H\u0002J\u0012\u00101\u001a\u0004\u0018\u00010\u00042\b\u00102\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00040\bj\b\u0012\u0004\u0012\u00020\u0004`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/xuehai/system/common/util/MdmUtil;", "", "()V", "DEFAULT_MAC", "", "HEX", "INVALID_ANDROID_ID", "INVALID_IMEIs", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "TAG", "appendHex", "", "sb", "Ljava/lang/StringBuffer;", "b", "", "getCalendar", "Ljava/util/Calendar;", "date", "Ljava/util/Date;", "timeZone", "Ljava/util/TimeZone;", "getDayStart", "", "timeMillis", "getDescribe", "permissions", "", "([Ljava/lang/String;)Ljava/lang/String;", "getImei", "context", "Landroid/content/Context;", "getPkgByUid", "uid", "getUUID", "getUid", "pkg", "getWifiMacAddress", "hasPermissions", "", "(Landroid/content/Context;[Ljava/lang/String;)Z", "isAllCharsSame", "serialNumber", "isValidImei", "imei", "toHex", "buf", "", "toMD5", "source", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmUtil {
    private static final String DEFAULT_MAC = "";
    private static final String HEX = "0123456789ABCDEF";
    private static final String TAG = "MdmUtil";
    public static final MdmUtil INSTANCE = new MdmUtil();
    private static final ArrayList<String> INVALID_IMEIs = CollectionsKt.arrayListOf("358673013795895", "004999010640000", "00000000000000", "000000000000000");
    private static final String INVALID_ANDROID_ID = "9774d56d682e549c";

    private MdmUtil() {
    }

    private final void appendHex(StringBuffer sb, byte b) {
        sb.append("0123456789ABCDEF".charAt((b >> 4) & 15));
        sb.append("0123456789ABCDEF".charAt(b & 15));
    }

    public static Calendar getCalendar$default(MdmUtil mdmUtil, Date date, TimeZone timeZone, int i, Object obj) {
        if ((i & 2) != 0) {
            timeZone = TimeZone.getDefault();
            Intrinsics.checkNotNullExpressionValue(timeZone, "getDefault()");
        }
        return mdmUtil.getCalendar(date, timeZone);
    }

    @JvmStatic
    public static final long getDayStart(long timeMillis) {
        Calendar calendar$default = getCalendar$default(INSTANCE, new Date(timeMillis), null, 2, null);
        calendar$default.set(11, 0);
        calendar$default.set(12, 0);
        calendar$default.set(13, 0);
        calendar$default.set(14, 0);
        return calendar$default.getTimeInMillis();
    }

    private final String getDescribe(String... permissions) {
        String str;
        if (!(!(permissions.length == 0)) || (str = permissions[0]) == null) {
            return null;
        }
        switch (str.hashCode()) {
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    return "存储";
                }
                return null;
            case -5573545:
                if (str.equals("android.permission.READ_PHONE_STATE")) {
                    return "电话";
                }
                return null;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    return "相机";
                }
                return null;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    return "存储";
                }
                return null;
            default:
                return null;
        }
    }

    @JvmStatic
    public static final boolean hasPermissions(Context context, String... permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (Build.VERSION.SDK_INT < 23) {
            MdmLog.d("MdmUtil", "API 小于 23 的情况下，不用请求权限");
            return true;
        }
        if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context".toString());
        }
        for (String str : permissions) {
            if (ActivityCompat.checkSelfPermission(context, str) == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append("缺少权限 ");
                String describe = INSTANCE.getDescribe(str);
                if (describe != null) {
                    str = describe;
                }
                sb.append(str);
                MdmLog.w("MdmUtil", sb.toString());
                return false;
            }
        }
        return true;
    }

    private final boolean isValidImei(String imei) {
        String str = imei;
        return ((str == null || str.length() == 0) || imei.length() < 10 || INVALID_IMEIs.contains(imei) || StringsKt.toSet(str).size() == 1) ? false : true;
    }

    private final String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(buf.length * 2);
        for (byte b : buf) {
            appendHex(stringBuffer, b);
        }
        String string = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "result.toString()");
        return string;
    }

    public final Calendar getCalendar(Date date, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(timeZone);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    public final String getImei(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!hasPermissions(context, "android.permission.READ_PHONE_STATE")) {
            return (String) null;
        }
        Object systemService = context.getSystemService("phone");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        TelephonyManager telephonyManager = (TelephonyManager) systemService;
        try {
            return Build.VERSION.SDK_INT >= 26 ? telephonyManager.getImei() : telephonyManager.getDeviceId();
        } catch (SecurityException unused) {
            return (String) null;
        }
    }

    public final String getPkgByUid(Context context, String uid) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uid, "uid");
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Integer.parseInt(uid));
        String str = packagesForUid != null ? packagesForUid[0] : null;
        return str == null ? "" : str;
    }

    public final String getUUID(Context context) throws SocketException, NoSuchAlgorithmException {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        MdmFileUtils mdmFileUtils = MdmFileUtils.INSTANCE;
        String imei = getImei(context);
        if (isValidImei(imei)) {
            Intrinsics.checkNotNull(imei);
            return imei;
        }
        try {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            string = "";
        }
        String str = string;
        if (!(str == null || str.length() == 0)) {
            String str2 = INVALID_ANDROID_ID;
            Intrinsics.checkNotNullExpressionValue(string, "androidId");
            String lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (!TextUtils.equals(str2, lowerCase)) {
                return string;
            }
        }
        String wifiMacAddress = getWifiMacAddress();
        String str3 = wifiMacAddress;
        if (str3 == null || str3.length() == 0) {
            return "";
        }
        String md5 = toMD5(wifiMacAddress + Build.MODEL + Build.MANUFACTURER + Build.ID + Build.DEVICE);
        String str4 = md5;
        if (!(str4 == null || str4.length() == 0) && md5.length() == 32) {
            md5 = md5.substring(8, 24);
            Intrinsics.checkNotNullExpressionValue(md5, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        return md5 == null ? "" : md5;
    }

    public final String getUid(Context context, String pkg) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkg, "pkg");
        try {
            return String.valueOf(context.getPackageManager().getApplicationInfo(pkg, 128).uid);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final String getWifiMacAddress() throws SocketException {
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            byte[] hardwareAddress = byName.getHardwareAddress();
            Intrinsics.checkNotNullExpressionValue(hardwareAddress, "networkInterface.hardwareAddress");
            for (byte b : hardwareAddress) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%02X:", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                sb.append(str);
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "{\n                    va…tring()\n                }");
            return string;
        } catch (Exception e) {
            MdmLog.w("MdmLog", "getWifiMacAddress() error ", e);
            return "";
        }
    }

    public final boolean isAllCharsSame(String serialNumber) {
        Intrinsics.checkNotNullParameter(serialNumber, "serialNumber");
        if (serialNumber.length() == 0) {
            return true;
        }
        char cCharAt = serialNumber.charAt(0);
        int length = serialNumber.length();
        for (int i = 0; i < length; i++) {
            if (serialNumber.charAt(i) != cCharAt) {
                return false;
            }
        }
        return true;
    }

    public final String toMD5(String source) throws NoSuchAlgorithmException {
        if (source != null && !Intrinsics.areEqual("", source)) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] bytes = source.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                messageDigest.update(bytes);
                return toHex(messageDigest.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
