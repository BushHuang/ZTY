package com.xuehai.system.common.policies;

import com.xuehai.system.common.entities.NetworkStats;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\b\u001a\u00020\u0006H&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H&J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H&J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H&¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/common/policies/IWifiPolicy;", "", "getApplicationNetworkStats", "", "Lcom/xuehai/system/common/entities/NetworkStats;", "isRandomMacDisabled", "", "isWIFIEditDisabled", "resetNetworkSetting", "setNetworkSpeedState", "state", "setRandomMacDisabled", "isDisabled", "setWIFIEditDisabled", "enable", "setWiFiState", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IWifiPolicy {
    List<NetworkStats> getApplicationNetworkStats();

    boolean isRandomMacDisabled();

    boolean isWIFIEditDisabled();

    boolean resetNetworkSetting();

    boolean setNetworkSpeedState(boolean state);

    boolean setRandomMacDisabled(boolean isDisabled);

    boolean setWIFIEditDisabled(boolean enable);

    boolean setWiFiState(boolean enable);
}
