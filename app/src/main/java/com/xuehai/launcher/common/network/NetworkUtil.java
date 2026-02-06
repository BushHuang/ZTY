package com.xuehai.launcher.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.system.mdm.proxy.PolicyManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0002J\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0017\u001a\u00020\u0004H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/xuehai/launcher/common/network/NetworkUtil;", "", "()V", "DEFAULT_MAC", "", "DEFAULT_MAC_ADDRESS", "DEFAULT_MAC_ADDRESS1", "defaultMacList", "", "getDefaultMacList", "()Ljava/util/List;", "defaultMacList$delegate", "Lkotlin/Lazy;", "getConnectivityManager", "Landroid/net/ConnectivityManager;", "context", "Landroid/content/Context;", "getLocalInetAddress", "Ljava/net/InetAddress;", "getLocalMacAddressFromIp", "getMacAddress", "getNetworkType", "Lcom/xuehai/launcher/common/network/NetworkType;", "getWLANMacAddress", "getWifiManager", "Landroid/net/wifi/WifiManager;", "isValidMac", "", "mac", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NetworkUtil {
    private static final String DEFAULT_MAC = "02:00:00:00:00:02";
    private static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
    private static final String DEFAULT_MAC_ADDRESS1 = "00:00:00:00:00:00";
    public static final NetworkUtil INSTANCE = new NetworkUtil();

    private static final Lazy defaultMacList = LazyKt.lazy(new Function0<ArrayList<String>>() {
        @Override
        public final ArrayList<String> invoke() {
            return CollectionsKt.arrayListOf("02:00:00:00:00:02", "02:00:00:00:00:00", "00:00:00:00:00:00");
        }
    });

    private NetworkUtil() {
    }

    private final ConnectivityManager getConnectivityManager(Context context) {
        Object systemService = context.getSystemService("connectivity");
        if (systemService != null) {
            return (ConnectivityManager) systemService;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }

    private final List<String> getDefaultMacList() {
        return (List) defaultMacList.getValue();
    }

    private final InetAddress getLocalInetAddress() throws SocketException {
        InetAddress inetAddress;
        SocketException e;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            inetAddress = null;
            do {
                try {
                    if (!networkInterfaces.hasMoreElements()) {
                        break;
                    }
                    NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                    if (networkInterfaceNextElement == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.net.NetworkInterface");
                    }
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (true) {
                        if (!inetAddresses.hasMoreElements()) {
                            break;
                        }
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        try {
                            if (!inetAddressNextElement.isLoopbackAddress()) {
                                String hostAddress = inetAddressNextElement.getHostAddress();
                                Intrinsics.checkNotNullExpressionValue(hostAddress, "ip.hostAddress");
                                if (!StringsKt.contains$default((CharSequence) hostAddress, (CharSequence) ":", false, 2, (Object) null)) {
                                    inetAddress = inetAddressNextElement;
                                    break;
                                }
                            }
                            inetAddress = null;
                        } catch (SocketException e2) {
                            e = e2;
                            inetAddress = inetAddressNextElement;
                            e.printStackTrace();
                            return inetAddress;
                        }
                    }
                } catch (SocketException e3) {
                    e = e3;
                }
            } while (inetAddress == null);
        } catch (SocketException e4) {
            inetAddress = null;
            e = e4;
        }
        return inetAddress;
    }

    private final String getLocalMacAddressFromIp() throws SocketException {
        try {
            byte[] hardwareAddress = NetworkInterface.getByInetAddress(getLocalInetAddress()).getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    sb.append(':');
                }
                String hexString = Integer.toHexString(hardwareAddress[i] & 255);
                Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(b[i].toInt() and 0xFF)");
                if (hexString.length() == 1) {
                    hexString = '0' + hexString;
                }
                sb.append(hexString);
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "buffer.toString()");
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String upperCase = string.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            return upperCase;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final String getWLANMacAddress() throws SocketException {
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                return "02:00:00:00:00:02";
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
            MyLog.w("Error[MDM]", "getWLANMacAddress()", e);
            return "02:00:00:00:00:02";
        }
    }

    private final WifiManager getWifiManager(Context context) {
        Object systemService = context.getApplicationContext().getSystemService("wifi");
        if (systemService != null) {
            return (WifiManager) systemService;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.net.wifi.WifiManager");
    }

    @JvmStatic
    public static final boolean isValidMac(String mac) {
        String str = mac;
        return ((str == null || str.length() == 0) || INSTANCE.getDefaultMacList().contains(mac)) ? false : true;
    }

    public final String getMacAddress() throws SocketException {
        String wLANMacAddress = getWLANMacAddress();
        if (!isValidMac(wLANMacAddress)) {
            wLANMacAddress = getLocalMacAddressFromIp();
        }
        if (!isValidMac(wLANMacAddress)) {
            wLANMacAddress = PolicyManager.getDevicePolicyProxy().getDeviceMacAddress();
        }
        String str = wLANMacAddress;
        return str == null || str.length() == 0 ? "02:00:00:00:00:02" : wLANMacAddress;
    }

    public final NetworkType getNetworkType(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new NetworkType(getConnectivityManager(context).getActiveNetworkInfo());
    }
}
