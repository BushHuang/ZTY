package com.xuehai.system.common.compat;

import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.entities.NetworkStats;
import com.xuehai.system.common.policies.IWifiPolicy;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0016¨\u0006\u0011"}, d2 = {"Lcom/xuehai/system/common/compat/WifiPolicyDefault;", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "()V", "getApplicationNetworkStats", "", "Lcom/xuehai/system/common/entities/NetworkStats;", "isRandomMacDisabled", "", "isWIFIEditDisabled", "resetNetworkSetting", "setNetworkSpeedState", "state", "setRandomMacDisabled", "isDisabled", "setWIFIEditDisabled", "enable", "setWiFiState", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class WifiPolicyDefault implements IWifiPolicy {
    @Override
    public List<NetworkStats> getApplicationNetworkStats() {
        return new ArrayList();
    }

    @Override
    public boolean isRandomMacDisabled() {
        throw new UnSupportException();
    }

    @Override
    public boolean isWIFIEditDisabled() {
        throw new UnSupportException();
    }

    @Override
    public boolean resetNetworkSetting() {
        throw new UnSupportException();
    }

    @Override
    public boolean setNetworkSpeedState(boolean state) {
        throw new UnSupportException();
    }

    @Override
    public boolean setRandomMacDisabled(boolean isDisabled) {
        throw new UnSupportException();
    }

    @Override
    public boolean setWIFIEditDisabled(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setWiFiState(boolean enable) {
        throw new UnSupportException();
    }
}
