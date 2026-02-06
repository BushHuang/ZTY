package com.samsung.android.knox.datetime;

import android.content.Context;
import com.samsung.android.knox.SupportLibUtils;
import java.util.Date;

public class DateTimePolicy {
    public static final String ACTION_EVENT_NTP_SERVER_UNREACHABLE = "com.samsung.android.knox.intent.action.EVENT_NTP_SERVER_UNREACHABLE";
    private Context mContext;
    private android.app.enterprise.DateTimePolicy mDateTimePolicy;

    public DateTimePolicy(Context context, android.app.enterprise.DateTimePolicy dateTimePolicy) {
        this.mContext = context;
        this.mDateTimePolicy = dateTimePolicy;
    }

    public boolean getAutomaticTime() {
        return this.mDateTimePolicy.getAutomaticTime();
    }

    public String getDateFormat() {
        return this.mDateTimePolicy.getDateFormat();
    }

    public Date getDateTime() {
        return this.mDateTimePolicy.getDateTime();
    }

    public boolean getDaylightSavingTime() {
        return this.mDateTimePolicy.getDaylightSavingTime();
    }

    public NtpInfo getNtpInfo() {
        try {
            return NtpInfo.convertToNew(this.mDateTimePolicy.getNtpInfo());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DateTimePolicy.class, "getNtpInfo", null, 17));
        }
    }

    public String getTimeFormat() {
        return this.mDateTimePolicy.getTimeFormat();
    }

    public String getTimeZone() {
        return this.mDateTimePolicy.getTimeZone();
    }

    public boolean isDateTimeChangeEnabled() {
        return this.mDateTimePolicy.isDateTimeChangeEnabled();
    }

    public boolean setAutomaticTime(boolean z) {
        return this.mDateTimePolicy.setAutomaticTime(z);
    }

    public boolean setDateTime(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.mDateTimePolicy.setDateTime(i, i2, i3, i4, i5, i6);
    }

    public boolean setDateTimeChangeEnabled(boolean z) {
        return this.mDateTimePolicy.setDateTimeChangeEnabled(z);
    }

    public boolean setNtpInfo(NtpInfo ntpInfo) {
        try {
            return this.mDateTimePolicy.setNtpInfo(NtpInfo.convertToOld(this.mContext, ntpInfo));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public boolean setTimeFormat(String str) {
        return this.mDateTimePolicy.setTimeFormat(str);
    }

    public boolean setTimeZone(String str) {
        return this.mDateTimePolicy.setTimeZone(str);
    }
}
