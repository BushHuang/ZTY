package com.xuehai.system.samsung.knox.v3.custom.p200;

import android.content.Context;
import com.xuehai.system.samsung.knox.v3.LicensePolicySamsungV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p200/LicensePolicySamsungP200;", "Lcom/xuehai/system/samsung/knox/v3/LicensePolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activatePayLicense", "", "payLicenseKey", "", "isPayLicenseActivated", "", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LicensePolicySamsungP200 extends LicensePolicySamsungV3 {
    public LicensePolicySamsungP200(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public void activatePayLicense(String payLicenseKey) {
        Intrinsics.checkNotNullParameter(payLicenseKey, "payLicenseKey");
    }

    @Override
    public boolean isPayLicenseActivated() {
        return true;
    }
}
