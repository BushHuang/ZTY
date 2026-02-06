package com.xuehai.system.mdm.proxy;

import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.ILicensePolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/mdm/proxy/LicensePolicyProxy;", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "policy", "(Lcom/xuehai/system/common/policies/ILicensePolicy;)V", "activateFreeLicense", "", "activatePayLicense", "payLicenseKey", "", "isFreeLicenseActivated", "", "isPayLicenseActivated", "resetLocalActivateState", "unActivateFreeLicense", "unActivatePayLicense", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LicensePolicyProxy implements ILicensePolicy {
    public static final String TAG = "MDM:LicensePolicy";
    private final ILicensePolicy policy;

    public LicensePolicyProxy(ILicensePolicy iLicensePolicy) {
        Intrinsics.checkNotNullParameter(iLicensePolicy, "policy");
        this.policy = iLicensePolicy;
    }

    @Override
    public void activateFreeLicense() {
        try {
            this.policy.activateFreeLicense();
            MdmLog.log$default("MDM:LicensePolicy", "请求激活FreeLicense", null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:LicensePolicy", "请求激活FreeLicense 发生异常！", th);
        }
    }

    @Override
    public void activatePayLicense(String payLicenseKey) {
        Intrinsics.checkNotNullParameter(payLicenseKey, "payLicenseKey");
        try {
            this.policy.activatePayLicense(payLicenseKey);
            MdmLog.log$default("MDM:LicensePolicy", "请求激活PayLicense", null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:LicensePolicy", "请求激活PayLicense 发生异常！", th);
        }
    }

    @Override
    public boolean isFreeLicenseActivated() {
        try {
            boolean zIsFreeLicenseActivated = this.policy.isFreeLicenseActivated();
            MdmLog.log("MDM:LicensePolicy", "获取FreeLicense激活状态", Boolean.valueOf(zIsFreeLicenseActivated));
            return zIsFreeLicenseActivated;
        } catch (Throwable th) {
            MdmLog.e("MDM:LicensePolicy", "获取FreeLicense激活状态 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean isPayLicenseActivated() {
        try {
            boolean zIsPayLicenseActivated = this.policy.isPayLicenseActivated();
            MdmLog.log("MDM:LicensePolicy", "获取PayLicense激活状态", Boolean.valueOf(zIsPayLicenseActivated));
            return zIsPayLicenseActivated;
        } catch (Throwable th) {
            MdmLog.e("MDM:LicensePolicy", "获取PayLicense激活状态 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean resetLocalActivateState() {
        try {
            boolean zResetLocalActivateState = this.policy.resetLocalActivateState();
            MdmLog.log("MDM:LicensePolicy", "重置本地保存的激活状态", Boolean.valueOf(zResetLocalActivateState));
            return zResetLocalActivateState;
        } catch (Throwable th) {
            MdmLog.e("MDM:LicensePolicy", "重置本地保存的激活状态 发生异常！", th);
            return false;
        }
    }

    @Override
    public void unActivateFreeLicense() {
        try {
            this.policy.unActivateFreeLicense();
            MdmLog.log$default("MDM:LicensePolicy", "清除FreeLicense激活状态", null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:LicensePolicy", "清除FreeLicense激活状态 发生异常！", th);
        }
    }

    @Override
    public void unActivatePayLicense() {
        try {
            this.policy.unActivatePayLicense();
            MdmLog.log$default("MDM:LicensePolicy", "清除PayLicense激活状态", null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:LicensePolicy", "清除PayLicense激活状态 发生异常！", th);
        }
    }
}
