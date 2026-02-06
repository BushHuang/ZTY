package com.xuehai.system.huawei_c5;

import android.content.ComponentName;
import com.huawei.android.app.admin.DeviceControlManager;
import com.huawei.android.app.admin.DeviceSettingsManager;
import com.xuehai.system.common.compat.DateTimePolicyDefault;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xuehai/system/huawei_c5/DateTimePolicyHWHEM;", "Lcom/xuehai/system/common/compat/DateTimePolicyDefault;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/ComponentName;)V", "getAutomaticTime", "", "setAutomaticTime", "enable", "setSystemTime", "date", "Ljava/util/Date;", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DateTimePolicyHWHEM extends DateTimePolicyDefault {
    private final ComponentName componentName;

    public DateTimePolicyHWHEM(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.componentName = componentName;
    }

    @Override
    public boolean getAutomaticTime() {
        return new DeviceSettingsManager().isTimeAndDateSetDisabled(this.componentName);
    }

    @Override
    public boolean setAutomaticTime(boolean enable) {
        return new DeviceSettingsManager().setTimeAndDateSetDisabled(this.componentName, enable);
    }

    @Override
    public boolean setSystemTime(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        new DeviceControlManager().setSysTime(this.componentName, date.getTime());
        return true;
    }
}
