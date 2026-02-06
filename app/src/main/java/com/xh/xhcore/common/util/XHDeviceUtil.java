package com.xh.xhcore.common.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class XHDeviceUtil {
    private static final String DEFAULT_MAC = "";
    private static final String HEX = "0123456789ABCDEF";
    private static final String INVALID_ANDROID_ID = "9774d56d682e549c";
    private static final List<String> INVALID_IMEIs;
    private static final String TAG = "XHDeviceUtil";

    static {
        ArrayList arrayList = new ArrayList();
        INVALID_IMEIs = arrayList;
        arrayList.add("358673013795895");
        INVALID_IMEIs.add("004999010640000");
        INVALID_IMEIs.add("00000000000000");
        INVALID_IMEIs.add("000000000000000");
    }

    private XHDeviceUtil() {
    }

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
    }

    public static String getMacFromHardware() throws SocketException {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "00:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "00:00:00:00:00:00";
        }
    }

    public static String getUUID(Context context) {
        String deviceId;
        if (!XHAppConfigProxy.getInstance().isObtainDeviceInfo()) {
            return null;
        }
        String string = "";
        if (XhPermissionUtil.hasPermissions(context, "android.permission.READ_PHONE_STATE")) {
            try {
                deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (Exception e) {
                e.printStackTrace();
                deviceId = "";
            }
            if (isValidIMEI(deviceId)) {
                return deviceId;
            }
        }
        try {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(string) && !"9774d56d682e549c".equalsIgnoreCase(string)) {
            return string;
        }
        String wifiMacAddress = getWifiMacAddress();
        if (TextUtils.isEmpty(wifiMacAddress)) {
            return null;
        }
        String md5 = toMD5(wifiMacAddress + Build.MODEL + Build.MANUFACTURER + Build.ID + Build.DEVICE);
        return (md5 == null || md5.length() != 32) ? md5 : md5.substring(8, 24);
    }

    public static String getWifiMacAddress() throws SocketException {
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : byName.getHardwareAddress()) {
                sb.append(String.format("%02X:", Byte.valueOf(b)));
            }
            if (!TextUtils.isEmpty(sb)) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Deprecated
    public static boolean hasPermissions(Context context, String... strArr) {
        return XhPermissionUtil.hasPermissions(context, strArr);
    }

    public static boolean isValidIMEI(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("unknown_value") || str.equalsIgnoreCase("unknown") || str.length() < 10 || INVALID_IMEIs.contains(str)) {
            return false;
        }
        return StringUtils.INSTANCE.isNotSameChar(str);
    }

    private static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            appendHex(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    public static String toMD5(String str) throws NoSuchAlgorithmException {
        if (str != null && !"".equals(str)) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str.getBytes());
                return toHex(messageDigest.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
