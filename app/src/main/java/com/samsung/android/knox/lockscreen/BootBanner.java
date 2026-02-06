package com.samsung.android.knox.lockscreen;

import android.app.enterprise.SecurityPolicy;
import com.samsung.android.knox.SupportLibUtils;

public class BootBanner {
    private SecurityPolicy mSecurityPolicy;

    public BootBanner(SecurityPolicy securityPolicy) {
        this.mSecurityPolicy = securityPolicy;
    }

    public boolean enableRebootBanner(boolean z) {
        return this.mSecurityPolicy.enableRebootBanner(z);
    }

    public boolean enableRebootBanner(boolean z, String str) {
        try {
            return this.mSecurityPolicy.enableRebootBanner(z, str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(BootBanner.class, "enableRebootBanner", new Class[]{Boolean.TYPE, String.class}, 13));
        }
    }

    public boolean isRebootBannerEnabled() {
        return this.mSecurityPolicy.isRebootBannerEnabled();
    }
}
