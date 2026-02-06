package com.xuehai.system.samsung.knox;

import android.content.Context;
import com.samsung.android.knox.license.EnterpriseLicenseManager;
import com.samsung.android.knox.license.KnoxEnterpriseLicenseManager;
import com.xuehai.system.common.compat.LicensePolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xuehai/system/samsung/knox/LicensePolicySamsung;", "Lcom/xuehai/system/common/compat/LicensePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activateFreeLicense", "", "activatePayLicense", "payLicenseKey", "", "isFreeLicenseActivated", "", "isPayLicenseActivated", "unActivateFreeLicense", "unActivatePayLicense", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LicensePolicySamsung extends LicensePolicyDefault {
    private final Context context;

    public LicensePolicySamsung(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public void activateFreeLicense() {
        if (!new LicenseState(this.context).getKLMLicenseState()) {
            KnoxEnterpriseLicenseManager.getInstance(this.context).activateLicense("KLM09-F7TPK-RD12M-VULHR-IZNMH-F2OAC");
        } else {
            if (new LicenseState(this.context).getELMLicenseState()) {
                return;
            }
            EnterpriseLicenseManager.getInstance(this.context).activateLicense("33E67254A91B7C777011B5BA51A750E4AD277E9AEF51039030266268AAEEC60C350B0E85443EED0525AA847AE149A3BD7491180E4EEBB5378AAF654FB5D42286");
        }
    }

    @Override
    public void activatePayLicense(String payLicenseKey) {
        Intrinsics.checkNotNullParameter(payLicenseKey, "payLicenseKey");
    }

    @Override
    public boolean isFreeLicenseActivated() {
        return new LicenseState(this.context).getELMLicenseState() && new LicenseState(this.context).getKLMLicenseState();
    }

    @Override
    public boolean isPayLicenseActivated() {
        return true;
    }

    @Override
    public void unActivateFreeLicense() {
        new LicenseState(this.context).setELMLicenseState(false);
        new LicenseState(this.context).setKLMLicenseState(false);
    }

    @Override
    public void unActivatePayLicense() {
    }
}
