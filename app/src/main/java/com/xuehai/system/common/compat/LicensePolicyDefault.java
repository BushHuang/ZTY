package com.xuehai.system.common.compat;

import com.xuehai.system.common.policies.ILicensePolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/common/compat/LicensePolicyDefault;", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "()V", "activateFreeLicense", "", "activatePayLicense", "payLicenseKey", "", "isFreeLicenseActivated", "", "isPayLicenseActivated", "resetLocalActivateState", "unActivateFreeLicense", "unActivatePayLicense", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LicensePolicyDefault implements ILicensePolicy {
    @Override
    public void activateFreeLicense() {
    }

    @Override
    public void activatePayLicense(String payLicenseKey) {
        Intrinsics.checkNotNullParameter(payLicenseKey, "payLicenseKey");
    }

    @Override
    public boolean isFreeLicenseActivated() {
        return true;
    }

    @Override
    public boolean isPayLicenseActivated() {
        return true;
    }

    @Override
    public boolean resetLocalActivateState() {
        return false;
    }

    @Override
    public void unActivateFreeLicense() {
    }

    @Override
    public void unActivatePayLicense() {
    }
}
