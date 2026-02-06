package com.xh.xhcore.common.util;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0007J\b\u0010\f\u001a\u00020\u0004H\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u0004H\u0007J\b\u0010\u0012\u001a\u00020\u0004H\u0007J\b\u0010\u0013\u001a\u00020\u0004H\u0007J\n\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xh/xhcore/common/util/WifiUtil;", "", "()V", "WIFI_BSSID", "", "WIFI_FREQUENCY", "WIFI_INFO", "WIFI_SSID", "wifiManagerInstance", "Landroid/net/wifi/WifiManager;", "getComputedConnectWifiFrequency", "", "getConnectedWifiBssid", "getConnectedWifiFrequency", "", "getConnectedWifiInfo", "Landroid/net/wifi/WifiInfo;", "getConnectedWifiMacAddress", "getConnectedWifiSsid", "getWifiInfo", "getWifiManager", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiUtil {
    public static WifiManager wifiManagerInstance;
    public static final WifiUtil INSTANCE = new WifiUtil();
    public static final String WIFI_INFO = "Wifi-Info";
    public static final String WIFI_BSSID = "Wifi-Bssid";
    public static final String WIFI_SSID = "Wifi-Ssid";
    public static final String WIFI_FREQUENCY = "Wifi-Frequency";

    private WifiUtil() {
    }

    @JvmStatic
    public static final float getComputedConnectWifiFrequency() {
        int connectedWifiFrequency = getConnectedWifiFrequency();
        if (connectedWifiFrequency >= 3000) {
            return 5.8f;
        }
        return connectedWifiFrequency > 0 ? 2.4f : 0.0f;
    }

    @JvmStatic
    public static final String getConnectedWifiBssid() {
        WifiInfo connectedWifiInfo;
        String bssid;
        return (!XHAppConfigProxy.getInstance().isObtainDeviceInfo() || (connectedWifiInfo = getConnectedWifiInfo()) == null || (bssid = connectedWifiInfo.getBSSID()) == null) ? "" : bssid;
    }

    @JvmStatic
    public static final int getConnectedWifiFrequency() {
        WifiInfo connectedWifiInfo;
        if (Build.VERSION.SDK_INT < 21 || !XHAppConfigProxy.getInstance().isObtainDeviceInfo() || (connectedWifiInfo = getConnectedWifiInfo()) == null) {
            return 0;
        }
        return connectedWifiInfo.getFrequency();
    }

    @JvmStatic
    public static final WifiInfo getConnectedWifiInfo() {
        WifiManager wifiManager;
        if (XHAppConfigProxy.getInstance().isObtainDeviceInfo() && (wifiManager = getWifiManager()) != null) {
            return wifiManager.getConnectionInfo();
        }
        return null;
    }

    @JvmStatic
    public static final String getConnectedWifiMacAddress() {
        WifiInfo connectedWifiInfo;
        String macAddress;
        return (!XHAppConfigProxy.getInstance().isObtainDeviceInfo() || (connectedWifiInfo = getConnectedWifiInfo()) == null || (macAddress = connectedWifiInfo.getMacAddress()) == null) ? "" : macAddress;
    }

    @JvmStatic
    public static final String getConnectedWifiSsid() {
        WifiInfo connectedWifiInfo;
        String ssid;
        return (!XHAppConfigProxy.getInstance().isObtainDeviceInfo() || (connectedWifiInfo = getConnectedWifiInfo()) == null || (ssid = connectedWifiInfo.getSSID()) == null) ? "" : ssid;
    }

    @JvmStatic
    public static final String getWifiInfo() {
        if (!XHAppConfigProxy.getInstance().isObtainDeviceInfo()) {
            return "";
        }
        return getConnectedWifiSsid() + '(' + getConnectedWifiMacAddress() + ';' + getConnectedWifiBssid() + ')';
    }

    @JvmStatic
    public static final WifiManager getWifiManager() {
        if (wifiManagerInstance == null) {
            synchronized (WifiUtil.class) {
                if (wifiManagerInstance == null) {
                    wifiManagerInstance = (WifiManager) XhBaseApplication.mContext.getApplicationContext().getSystemService("wifi");
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return wifiManagerInstance;
    }
}
