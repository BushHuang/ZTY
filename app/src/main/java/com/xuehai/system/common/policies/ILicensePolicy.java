package com.xuehai.system.common.policies;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\bH&J\b\u0010\n\u001a\u00020\bH&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&¨\u0006\r"}, d2 = {"Lcom/xuehai/system/common/policies/ILicensePolicy;", "", "activateFreeLicense", "", "activatePayLicense", "payLicenseKey", "", "isFreeLicenseActivated", "", "isPayLicenseActivated", "resetLocalActivateState", "unActivateFreeLicense", "unActivatePayLicense", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ILicensePolicy {
    void activateFreeLicense();

    void activatePayLicense(String payLicenseKey);

    boolean isFreeLicenseActivated();

    boolean isPayLicenseActivated();

    boolean resetLocalActivateState();

    void unActivateFreeLicense();

    void unActivatePayLicense();
}
