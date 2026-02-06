package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import androidx.core.app.ActivityCompat;
import com.samsung.android.knox.net.wifi.WifiAdminProfile;
import com.samsung.android.knox.net.wifi.WifiPolicy;
import com.xuehai.system.common.compat.WifiPolicyDefault;
import com.xuehai.system.common.entities.NetworkStats;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/WifiPolicySamsungV3;", "Lcom/xuehai/system/common/compat/WifiPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "getPolicy", "()Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "getApplicationNetworkStats", "", "Lcom/xuehai/system/common/entities/NetworkStats;", "resetNetworkSetting", "", "setWiFiState", "enable", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class WifiPolicySamsungV3 extends WifiPolicyDefault {
    private final Context context;
    private final SamsungKnoxV3 policy;

    public WifiPolicySamsungV3(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(this.context);
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

    public final Context getContext() {
        return this.context;
    }

    public final SamsungKnoxV3 getPolicy() {
        return this.policy;
    }

    @Override
    public boolean resetNetworkSetting() {
        WifiManager wifiManager;
        List<WifiConfiguration> configuredNetworks;
        WifiPolicy wifiPolicy = this.policy.wifiPolicy();
        boolean zRemoveNetworkConfiguration = false;
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0 && (wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi")) != null && (configuredNetworks = wifiManager.getConfiguredNetworks()) != null) {
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
        this.policy.settingsManager().setWifiState(enable, null, null, null);
        return true;
    }
}
