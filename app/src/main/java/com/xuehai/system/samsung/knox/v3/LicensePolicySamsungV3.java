package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.samsung.android.knox.license.KnoxEnterpriseLicenseManager;
import com.xuehai.system.common.compat.LicensePolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/LicensePolicySamsungV3;", "Lcom/xuehai/system/common/compat/LicensePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "activateFreeLicense", "", "activatePayLicense", "payLicenseKey", "", "isFreeLicenseActivated", "", "isPayLicenseActivated", "unActivateFreeLicense", "unActivatePayLicense", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LicensePolicySamsungV3 extends LicensePolicyDefault {
    private final Context context;
    private final SamsungKnoxV3 policy;

    public LicensePolicySamsungV3(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(this.context);
    }

    @Override
    public void activateFreeLicense() {
        KnoxEnterpriseLicenseManager.getInstance(this.context).activateLicense("KLM09-GAQR3-O9AY0-C3390-I7FTD-H16V3");
    }

    @Override
    public void activatePayLicense(String payLicenseKey) {
        Intrinsics.checkNotNullParameter(payLicenseKey, "payLicenseKey");
        KnoxEnterpriseLicenseManager.getInstance(this.context).activateLicense(payLicenseKey);
    }

    @Override
    public boolean isFreeLicenseActivated() {
        return this.policy.hasPermission("com.samsung.android.knox.permission.KNOX_FIREWALL") && this.policy.hasPermission("com.samsung.android.knox.permission.KNOX_APP_MGMT");
    }

    @Override
    public boolean isPayLicenseActivated() {
        return this.policy.hasPermission("com.samsung.android.knox.permission.KNOX_CUSTOM_SETTING");
    }

    @Override
    public void unActivateFreeLicense() {
        new LicenseV3State(this.context).setLicenseState(false);
    }

    @Override
    public void unActivatePayLicense() {
        new LicenseV3State(this.context).setLicenseState(false);
    }
}
