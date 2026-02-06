package com.xuehai.system.mdm.proxy;

import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.IDateTimePolicy;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xuehai/system/mdm/proxy/DateTimePolicyProxy;", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "policy", "(Lcom/xuehai/system/common/policies/IDateTimePolicy;)V", "disallowSetSysTimeZone", "", "disallow", "getAutomaticTime", "setAutomaticTime", "enable", "setDateTimeChangeEnabled", "setSystemTime", "date", "Ljava/util/Date;", "setTimeZone", "timeZoneId", "", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DateTimePolicyProxy implements IDateTimePolicy {
    public static final String TAG = "MDM:DateTimePolicy";
    private final IDateTimePolicy policy;

    public DateTimePolicyProxy(IDateTimePolicy iDateTimePolicy) {
        Intrinsics.checkNotNullParameter(iDateTimePolicy, "policy");
        this.policy = iDateTimePolicy;
    }

    @Override
    public boolean disallowSetSysTimeZone(boolean disallow) {
        String str = disallow ? "禁止设置系统时区" : "允许设置系统时区";
        try {
            boolean zDisallowSetSysTimeZone = this.policy.disallowSetSysTimeZone(disallow);
            MdmLog.log("MDM:DateTimePolicy", str, Boolean.valueOf(zDisallowSetSysTimeZone));
            return zDisallowSetSysTimeZone;
        } catch (Throwable th) {
            MdmLog.e("MDM:DateTimePolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean getAutomaticTime() {
        try {
            boolean automaticTime = this.policy.getAutomaticTime();
            MdmLog.log("MDM:DateTimePolicy", "获取自动同步时间状态", Boolean.valueOf(automaticTime));
            return automaticTime;
        } catch (Throwable th) {
            MdmLog.e("MDM:DateTimePolicy", "获取自动同步时间状态 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setAutomaticTime(boolean enable) {
        String str = enable ? "开启自动同步时间" : "关闭自动同步时间";
        try {
            boolean automaticTime = this.policy.setAutomaticTime(enable);
            MdmLog.log("MDM:DateTimePolicy", str, Boolean.valueOf(automaticTime));
            return automaticTime;
        } catch (Throwable th) {
            MdmLog.e("MDM:DateTimePolicy", "设置时区 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setDateTimeChangeEnabled(boolean enable) {
        String str = enable ? "允许修改时间日期" : "禁止修改时间日期";
        try {
            boolean dateTimeChangeEnabled = this.policy.setDateTimeChangeEnabled(enable);
            MdmLog.log("MDM:DateTimePolicy", str, Boolean.valueOf(dateTimeChangeEnabled));
            return dateTimeChangeEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:DateTimePolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setSystemTime(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return false;
    }

    @Override
    public boolean setTimeZone(String timeZoneId) {
        Intrinsics.checkNotNullParameter(timeZoneId, "timeZoneId");
        try {
            boolean automaticTime = this.policy.getAutomaticTime();
            if (automaticTime) {
                this.policy.setAutomaticTime(false);
            }
            boolean timeZone = this.policy.setTimeZone(timeZoneId);
            if (automaticTime) {
                this.policy.setAutomaticTime(true);
            }
            MdmLog.log("MDM:DateTimePolicy", "设置时区: " + timeZoneId, Boolean.valueOf(timeZone));
            return timeZone;
        } catch (Throwable th) {
            MdmLog.e("MDM:DateTimePolicy", "设置时区 发生异常！" + timeZoneId, th);
            return false;
        }
    }
}
