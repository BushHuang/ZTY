package com.xuehai.system.common.compat;

import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.policies.IDateTimePolicy;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/common/compat/DateTimePolicyDefault;", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "()V", "disallowSetSysTimeZone", "", "disallow", "getAutomaticTime", "setAutomaticTime", "enable", "setDateTimeChangeEnabled", "setSystemTime", "date", "Ljava/util/Date;", "setTimeZone", "timeZoneId", "", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DateTimePolicyDefault implements IDateTimePolicy {
    @Override
    public boolean disallowSetSysTimeZone(boolean disallow) {
        throw new UnSupportException();
    }

    @Override
    public boolean getAutomaticTime() {
        throw new UnSupportException();
    }

    @Override
    public boolean setAutomaticTime(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setDateTimeChangeEnabled(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setSystemTime(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        throw new UnSupportException();
    }

    @Override
    public boolean setTimeZone(String timeZoneId) {
        Intrinsics.checkNotNullParameter(timeZoneId, "timeZoneId");
        throw new UnSupportException();
    }
}
