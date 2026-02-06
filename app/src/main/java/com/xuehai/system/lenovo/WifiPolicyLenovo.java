package com.xuehai.system.lenovo;

import com.xuehai.system.common.compat.WifiPolicyDefault;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/lenovo/WifiPolicyLenovo;", "Lcom/xuehai/system/common/compat/WifiPolicyDefault;", "()V", "resetNetworkSetting", "", "setWiFiState", "enable", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiPolicyLenovo extends WifiPolicyDefault {
    @Override
    public boolean resetNetworkSetting() {
        return false;
    }

    @Override
    public boolean setWiFiState(boolean enable) {
        return false;
    }
}
