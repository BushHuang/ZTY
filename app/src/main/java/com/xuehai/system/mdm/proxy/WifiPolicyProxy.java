package com.xuehai.system.mdm.proxy;

import com.xuehai.system.common.entities.NetworkStats;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.IWifiPolicy;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xuehai/system/mdm/proxy/WifiPolicyProxy;", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "policy", "(Lcom/xuehai/system/common/policies/IWifiPolicy;)V", "getApplicationNetworkStats", "", "Lcom/xuehai/system/common/entities/NetworkStats;", "isRandomMacDisabled", "", "isWIFIEditDisabled", "resetNetworkSetting", "setNetworkSpeedState", "state", "setRandomMacDisabled", "isDisabled", "setWIFIEditDisabled", "enable", "setWiFiState", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiPolicyProxy implements IWifiPolicy {
    public static final String TAG = "MDM:WifiPolicy";
    private final IWifiPolicy policy;

    public WifiPolicyProxy(IWifiPolicy iWifiPolicy) {
        Intrinsics.checkNotNullParameter(iWifiPolicy, "policy");
        this.policy = iWifiPolicy;
    }

    @Override
    public List<NetworkStats> getApplicationNetworkStats() {
        try {
            List<NetworkStats> applicationNetworkStats = this.policy.getApplicationNetworkStats();
            MdmLog.log$default("MDM:WifiPolicy", "获取流量统计数据", null, 4, null);
            return applicationNetworkStats;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", "获取流量统计数据 发生异常", th);
            return new ArrayList();
        }
    }

    @Override
    public boolean isRandomMacDisabled() {
        try {
            boolean zIsRandomMacDisabled = this.policy.isRandomMacDisabled();
            MdmLog.log("MDM:WifiPolicy", "查询 WLAN 随机mac地址 禁用状态", Boolean.valueOf(zIsRandomMacDisabled));
            return zIsRandomMacDisabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", "查询 WLAN 随机mac地址 禁用状态 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean isWIFIEditDisabled() {
        try {
            boolean zIsWIFIEditDisabled = this.policy.isWIFIEditDisabled();
            MdmLog.log("MDM:WifiPolicy", "查询编辑 WLAN 设置项禁用状态", Boolean.valueOf(zIsWIFIEditDisabled));
            return zIsWIFIEditDisabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", "查询编辑 WLAN 设置项禁用状态 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean resetNetworkSetting() {
        try {
            boolean zResetNetworkSetting = this.policy.resetNetworkSetting();
            MdmLog.log("MDM:WifiPolicy", "重置网络配置", Boolean.valueOf(zResetNetworkSetting));
            return zResetNetworkSetting;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", "重置网络配置 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean setNetworkSpeedState(boolean state) {
        try {
            boolean networkSpeedState = this.policy.setNetworkSpeedState(state);
            MdmLog.log("MDM:WifiPolicy", "设置网络速度显示状态: " + state, Boolean.valueOf(networkSpeedState));
            return networkSpeedState;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", "设置网络速度显示状态: " + state + " 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean setRandomMacDisabled(boolean isDisabled) {
        String str = isDisabled ? "禁用 WLAN 随机mac地址" : "启用 WLAN 随机mac地址";
        try {
            boolean randomMacDisabled = this.policy.setRandomMacDisabled(isDisabled);
            MdmLog.log("MDM:WifiPolicy", str, Boolean.valueOf(randomMacDisabled));
            return randomMacDisabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", str + " 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean setWIFIEditDisabled(boolean enable) {
        String str = enable ? "启用WLAN高级选项菜单" : "禁用WLAN高级选项菜单";
        try {
            boolean wIFIEditDisabled = this.policy.setWIFIEditDisabled(enable);
            MdmLog.log("MDM:WifiPolicy", str, Boolean.valueOf(wIFIEditDisabled));
            return wIFIEditDisabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", str + " 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean setWiFiState(boolean enable) {
        String str = enable ? "打开网络" : "关闭网络";
        try {
            boolean wiFiState = this.policy.setWiFiState(enable);
            MdmLog.log("MDM:WifiPolicy", str, Boolean.valueOf(wiFiState));
            return wiFiState;
        } catch (Throwable th) {
            MdmLog.e("MDM:WifiPolicy", str + " 发生异常", th);
            return false;
        }
    }
}
