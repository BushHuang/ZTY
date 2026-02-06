package com.xuehai.system.huawei_c5;

import android.content.ComponentName;
import android.content.Context;
import com.huawei.android.app.admin.DeviceControlManager;
import com.huawei.android.app.admin.DeviceSettingsManager;
import com.xuehai.system.common.compat.LocationPolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xuehai/system/huawei_c5/LocationPolicyHWHEM;", "Lcom/xuehai/system/common/compat/LocationPolicyDefault;", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "allowLocationService", "", "enable", "isLocationServiceEnable", "startGPS", "start", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocationPolicyHWHEM extends LocationPolicyDefault {
    private final ComponentName componentName;
    private final Context context;

    public LocationPolicyHWHEM(Context context, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.context = context;
        this.componentName = componentName;
    }

    @Override
    public boolean allowLocationService(boolean enable) {
        return new DeviceSettingsManager().setLocationServiceDisabled(this.componentName, !enable);
    }

    @Override
    public boolean isLocationServiceEnable() {
        return !new DeviceSettingsManager().isLocationServiceDisabled(this.componentName);
    }

    @Override
    public boolean startGPS(boolean start) {
        if (start && !isLocationServiceEnable()) {
            return false;
        }
        new DeviceControlManager().turnOnGPS(this.componentName, start);
        return true;
    }
}
