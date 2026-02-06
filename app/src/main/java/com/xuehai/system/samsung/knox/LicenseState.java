package com.xuehai.system.samsung.knox;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/samsung/knox/LicenseState;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getELMLicenseState", "", "getKLMLicenseState", "setELMLicenseState", "", "isSuccess", "setKLMLicenseState", "Companion", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LicenseState {
    public static final String SAMSUNG_ELM_KEY_STATUS = "samsung_elm_key_status";
    public static final String SAMSUNG_KLM_KEY_STATUS = "samsung_klm_key_status";
    private final SharedPreferences sharedPreferences;

    public LicenseState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.sharedPreferences = context.getApplicationContext().getSharedPreferences("share_data", 0);
    }

    public final boolean getELMLicenseState() {
        return this.sharedPreferences.getBoolean("samsung_elm_key_status", false);
    }

    public final boolean getKLMLicenseState() {
        return this.sharedPreferences.getBoolean("samsung_klm_key_status", false);
    }

    public final void setELMLicenseState(boolean isSuccess) {
        this.sharedPreferences.edit().putBoolean("samsung_elm_key_status", isSuccess).apply();
    }

    public final void setKLMLicenseState(boolean isSuccess) {
        this.sharedPreferences.edit().putBoolean("samsung_klm_key_status", isSuccess).apply();
    }
}
