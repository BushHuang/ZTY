package com.xuehai.system.lenovo.csdk.tb_x6c6f;

import android.app.csdk.CSDKManager;
import android.content.Context;
import com.xuehai.system.common.compat.DateTimePolicyDefault;
import com.xuehai.system.lenovo.csdk.LenovoCSDK;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb_x6c6f/DateTimePolicyLenovoX6C6F;", "Lcom/xuehai/system/common/compat/DateTimePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "csdkManager", "Landroid/app/csdk/CSDKManager;", "disallowSetSysTimeZone", "", "disallow", "getAutomaticTime", "setAutomaticTime", "enable", "setDateTimeChangeEnabled", "setSystemTime", "date", "Ljava/util/Date;", "setTimeZone", "timeZoneId", "", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DateTimePolicyLenovoX6C6F extends DateTimePolicyDefault {
    private final CSDKManager csdkManager;

    public DateTimePolicyLenovoX6C6F(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.csdkManager = LenovoCSDK.INSTANCE.getCSDKManager(context);
    }

    @Override
    public boolean disallowSetSysTimeZone(boolean disallow) {
        this.csdkManager.disallowSetSysTimeZoneV3(disallow);
        return true;
    }

    @Override
    public boolean getAutomaticTime() {
        return true;
    }

    @Override
    public boolean setAutomaticTime(boolean enable) {
        this.csdkManager.disallowSetSysDateV3(enable);
        this.csdkManager.disallowSetSysTimeV3(enable);
        if (!enable) {
            return true;
        }
        this.csdkManager.setAutoState(0);
        return true;
    }

    @Override
    public boolean setDateTimeChangeEnabled(boolean enable) {
        this.csdkManager.disallowSetSysDateV3(!enable);
        this.csdkManager.disallowSetSysTimeV3(!enable);
        return true;
    }

    @Override
    public boolean setSystemTime(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.csdkManager.setSysTime(date.getTime());
        return true;
    }

    @Override
    public boolean setTimeZone(String timeZoneId) {
        Intrinsics.checkNotNullParameter(timeZoneId, "timeZoneId");
        this.csdkManager.setTimeZoneV3(timeZoneId);
        return true;
    }
}
