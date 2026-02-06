package com.xuehai.system.samsung.knox.v3.custom.p620;

import android.content.Context;
import com.xuehai.system.samsung.knox.v3.LicensePolicySamsungV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p620/LicensePolicySamsungP620;", "Lcom/xuehai/system/samsung/knox/v3/LicensePolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isPayLicenseActivated", "", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LicensePolicySamsungP620 extends LicensePolicySamsungV3 {
    public LicensePolicySamsungP620(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public boolean isPayLicenseActivated() {
        return true;
    }
}
