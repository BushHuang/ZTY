package com.zaze.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.text.TextUtils;
import com.zaze.utils.log.ZLog;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NetUtil {
    private static final String MARK = "\"";
    public static final String MOBILE = "mobile";
    public static final String WIFI = "wifi";
    private static ConnectivityManager connectivityManager;
    private static WifiManager mWifiManager;

    private static void appendDesc(StringBuilder sb, String str) {
        if (sb.length() <= 0) {
            sb.append(str);
        } else {
            sb.append("/");
            sb.append(str);
        }
    }

    public static void connect(Context context, String str, String str2, String str3) {
        if (!isWifiEnabled(context)) {
            ZLog.e("Debug[ZZ]", "当前wifi不可用", new Object[0]);
            return;
        }
        while (mWifiManager.getWifiState() == 2) {
            SystemClock.sleep(100L);
        }
        createWifiInfo(str, str2, str3);
    }

    private static WifiConfiguration createWifiInfo(String str, String str2, String str3) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        if (str3.toUpperCase().contains("WPA")) {
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
        } else if (str3.toUpperCase().contains("WEP")) {
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        } else {
            wifiConfiguration.wepKeys[0] = "";
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        return wifiConfiguration;
    }

    public static String formatWifiDesc(ScanResult scanResult) {
        String upperCase = scanResult.capabilities.toUpperCase();
        StringBuilder sb = new StringBuilder();
        boolean zContains = upperCase.contains("WPA-PSK");
        boolean zContains2 = upperCase.contains("WPA2-PSK");
        boolean zContains3 = upperCase.contains("ESS");
        if (zContains) {
            appendDesc(sb, "WPA");
        }
        if (zContains2) {
            appendDesc(sb, "WPA2");
        }
        if (zContains3) {
            appendDesc(sb, "ESS");
        }
        return sb.toString();
    }

    public static WifiInfo getConnectionInfo(Context context) {
        return getWifiManager(context).getConnectionInfo();
    }

    public static ConnectivityManager getConnectivityManager(Context context) {
        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }
        return connectivityManager;
    }

    public static List<String> getDNS(Context context) {
        DhcpInfo dhcpInfo = getDhcpInfo(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(intToInetAddress(dhcpInfo.dns1).getHostAddress());
        arrayList.add(intToInetAddress(dhcpInfo.dns2).getHostAddress());
        return arrayList;
    }

    public static DhcpInfo getDhcpInfo(Context context) {
        return getWifiManager(context).getDhcpInfo();
    }

    public static String getGateway(Context context) {
        return intToInetAddress(getDhcpInfo(context).gateway).getHostAddress();
    }

    public static String getIpAddress(Context context) {
        return intToInetAddress(getDhcpInfo(context).ipAddress).getHostAddress();
    }

    public static String getNetwork(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            ZLog.e("Debug[ZZ]", "无网络连接", new Object[0]);
            return "";
        }
        String str = null;
        int type = activeNetworkInfo.getType();
        if (type == 0) {
            ZLog.i("Debug[ZZ]", "当前使用数据流量", new Object[0]);
            str = "mobile";
        } else if (type == 1) {
            ZLog.i("Debug[ZZ]", "当前连接wifi", new Object[0]);
            str = "wifi";
        }
        return activeNetworkInfo.isAvailable() ? str : "";
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        return getConnectivityManager(context).getActiveNetworkInfo();
    }

    public static String getWLANMacAddress() throws SocketException {
        StringBuilder sb = new StringBuilder();
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                return "02:00:00:00:00:02";
            }
            byte[] hardwareAddress = byName.getHardwareAddress();
            if (hardwareAddress != null) {
                for (byte b : hardwareAddress) {
                    sb.append(String.format("%02X:", Byte.valueOf(b)));
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
    }

    public static NetworkInfo getWifiInfo(Context context) {
        return getConnectivityManager(context).getNetworkInfo(1);
    }

    public static WifiManager getWifiManager(Context context) {
        if (mWifiManager == null) {
            mWifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        }
        return mWifiManager;
    }

    public static InetAddress intToInetAddress(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    public static boolean isAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static boolean isSSIDEquals(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (TextUtils.equals(str, str2)) {
                return true;
            }
            if (str.startsWith("\"") && str.endsWith("\"")) {
                return TextUtils.equals(str, "\"" + str2 + "\"");
            }
            if (str2.startsWith("\"") && str2.endsWith("\"")) {
                return TextUtils.equals("\"" + str + "\"", str2);
            }
        }
        return false;
    }

    public static boolean isWifiEnabled(Context context) {
        return getWifiManager(context).isWifiEnabled();
    }
}
