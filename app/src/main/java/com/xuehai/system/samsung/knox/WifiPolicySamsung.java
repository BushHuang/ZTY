package com.xuehai.system.samsung.knox;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.samsung.android.knox.net.wifi.WifiAdminProfile;
import com.samsung.android.knox.net.wifi.WifiPolicy;
import com.xuehai.system.common.compat.WifiPolicyDefault;
import com.xuehai.system.common.entities.NetworkStats;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/samsung/knox/WifiPolicySamsung;", "Lcom/xuehai/system/common/compat/WifiPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/SamsungPolicy;", "getApplicationNetworkStats", "", "Lcom/xuehai/system/common/entities/NetworkStats;", "resetNetworkSetting", "", "setWiFiState", "enable", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiPolicySamsung extends WifiPolicyDefault {
    private final Context context;
    private final SamsungPolicy policy;

    public WifiPolicySamsung(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.policy = new SamsungPolicy(context);
    }

    @Override
    public List<NetworkStats> getApplicationNetworkStats() {
        ArrayList arrayList = new ArrayList();
        List<com.samsung.android.knox.application.NetworkStats> applicationNetworkStats = this.policy.applicationPolicy().getApplicationNetworkStats();
        if (applicationNetworkStats == null) {
            return arrayList;
        }
        for (com.samsung.android.knox.application.NetworkStats networkStats : applicationNetworkStats) {
            NetworkStats networkStats2 = new NetworkStats();
            networkStats2.uid = networkStats.uid;
            networkStats2.wifiRxBytes = networkStats.wifiRxBytes;
            networkStats2.wifiTxBytes = networkStats.wifiTxBytes;
            networkStats2.mobileTxBytes = networkStats.mobileTxBytes;
            networkStats2.mobileRxBytes = networkStats.mobileRxBytes;
            arrayList.add(networkStats2);
        }
        return arrayList;
    }

    @Override
    public boolean resetNetworkSetting() {
        List<WifiConfiguration> configuredNetworks;
        WifiPolicy wifiPolicy = this.policy.wifiPolicy();
        WifiManager wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi");
        boolean zRemoveNetworkConfiguration = true;
        if (wifiManager != null && (configuredNetworks = wifiManager.getConfiguredNetworks()) != null) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                WifiAdminProfile wifiAdminProfile = new WifiAdminProfile();
                wifiAdminProfile.ssid = wifiConfiguration.SSID;
                wifiAdminProfile.security = "NONE";
                wifiPolicy.setWifiProfile(wifiAdminProfile);
                zRemoveNetworkConfiguration = wifiPolicy.removeNetworkConfiguration(wifiConfiguration.SSID);
            }
        }
        return zRemoveNetworkConfiguration;
    }

    @Override
    public boolean setWiFiState(boolean enable) {
        return this.policy.restrictionPolicy().setWifiTethering(enable);
    }
}
