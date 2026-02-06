package com.xuehai.system.huawei_c5;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/huawei_c5/HuaweiHemLicenseState;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getLicenseState", "", "setLicenseState", "", "isSuccess", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HuaweiHemLicenseState {
    public static final String LICENSE_STATE_HEM = "license_state_hem";
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public HuaweiHemLicenseState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.sharedPreferences = context.getApplicationContext().getSharedPreferences("share_data", 0);
    }

    public final boolean getLicenseState() {
        return this.sharedPreferences.getBoolean("license_state_hem", false);
    }

    public final void setLicenseState(boolean isSuccess) {
        this.sharedPreferences.edit().putBoolean("license_state_hem", isSuccess).apply();
    }
}
