package com.zaze.utils.date;

import android.text.TextUtils;
import com.zaze.utils.ZStringUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0007J\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0002J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0012\u0010\u001d\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u001a\u0010\u001f\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010 \u001a\u00020\u000eH\u0002J\u0018\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u000eH\u0002J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0012\u0010\"\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0010H\u0007J\u0012\u0010(\u001a\u00020)2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0010\u0010(\u001a\u00020)2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0018\u0010(\u001a\u00020)2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0007J\u0010\u0010*\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0010\u0010,\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J$\u0010-\u001a\u0004\u0018\u00010\u00122\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0004H\u0007J\"\u0010/\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/zaze/utils/date/DateUtil;", "", "()V", "DAY", "", "HALF_YEAR", "HOUR", "MINUTE", "SECOND", "WEEK", "YEAR", "calculateMonth", "timeMillis", "offset", "", "dateToString", "", "date", "Ljava/util/Date;", "pattern", "timeZone", "Ljava/util/TimeZone;", "getCalendar", "Ljava/util/Calendar;", "getDateFormat", "Ljava/text/SimpleDateFormat;", "getDay", "getDayEnd", "getDayStart", "getHour", "getHourAndMin", "getInteger", "dateType", "getMinAndSec", "getMinute", "getMonth", "Lcom/zaze/utils/date/Month;", "getSecond", "getTimeMillisByHM", "dateStr", "getWeek", "Lcom/zaze/utils/date/Week;", "getWeekEnd", "getWeekStart", "getYear", "stringToDate", "timeMillisToDate", "timeMillisToString", "util_release"}, k = 1, mv = {1, 4, 1})
public final class DateUtil {
    public static final long DAY = 86400000;
    public static final long HALF_YEAR = 15768000000L;
    public static final long HOUR = 3600000;
    public static final DateUtil INSTANCE = new DateUtil();
    public static final long MINUTE = 60000;
    public static final long SECOND = 1000;
    public static final long WEEK = 604800000;
    public static final long YEAR = 31536000000L;

    private DateUtil() {
    }

    @JvmStatic
    public static final long calculateMonth(long timeMillis, int offset) {
        Calendar calendar$default = getCalendar$default(INSTANCE, new Date(timeMillis), null, 2, null);
        calendar$default.set(2, calendar$default.get(2) + offset);
        return calendar$default.getTimeInMillis();
    }

    @JvmStatic
    public static final String dateToString(Date date, String pattern, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        String str = INSTANCE.getDateFormat(pattern, timeZone).format(date);
        Intrinsics.checkNotNullExpressionValue(str, "getDateFormat(pattern, timeZone).format(date)");
        return str;
    }

    public static String dateToString$default(Date date, String str, TimeZone timeZone, int i, Object obj) {
        if ((i & 4) != 0) {
            timeZone = TimeZone.getDefault();
            Intrinsics.checkNotNullExpressionValue(timeZone, "TimeZone.getDefault()");
        }
        return dateToString(date, str, timeZone);
    }

    private final Calendar getCalendar(Date date, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        calendar.setTime(date);
        calendar.setTimeZone(timeZone);
        return calendar;
    }

    static Calendar getCalendar$default(DateUtil dateUtil, Date date, TimeZone timeZone, int i, Object obj) {
        if ((i & 2) != 0) {
            timeZone = TimeZone.getDefault();
            Intrinsics.checkNotNullExpressionValue(timeZone, "TimeZone.getDefault()");
        }
        return dateUtil.getCalendar(date, timeZone);
    }

    private final SimpleDateFormat getDateFormat(String pattern, TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat;
    }

    static SimpleDateFormat getDateFormat$default(DateUtil dateUtil, String str, TimeZone timeZone, int i, Object obj) {
        if ((i & 2) != 0) {
            timeZone = TimeZone.getDefault();
            Intrinsics.checkNotNullExpressionValue(timeZone, "TimeZone.getDefault()");
        }
        return dateUtil.getDateFormat(str, timeZone);
    }

    @JvmStatic
    public static final int getDay(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return INSTANCE.getInteger(date, 5);
    }

    @JvmStatic
    public static final long getDayEnd(long timeMillis) {
        Calendar calendar$default = getCalendar$default(INSTANCE, new Date(timeMillis), null, 2, null);
        calendar$default.set(11, 23);
        calendar$default.set(12, 59);
        calendar$default.set(13, 59);
        calendar$default.set(14, 59);
        return calendar$default.getTimeInMillis();
    }

    @JvmStatic
    public static final long getDayStart(long timeMillis) {
        Calendar calendar$default = getCalendar$default(INSTANCE, new Date(timeMillis), null, 2, null);
        calendar$default.set(11, 0);
        calendar$default.set(12, 0);
        calendar$default.set(13, 0);
        calendar$default.set(14, 0);
        return calendar$default.getTimeInMillis();
    }

    @JvmStatic
    public static final int getHour(Date date) {
        return INSTANCE.getInteger(date, 11);
    }

    @JvmStatic
    public static final String getHourAndMin(long timeMillis) {
        return timeMillisToString$default(timeMillis, "HH:mm", null, 4, null);
    }

    private final int getInteger(long timeMillis, int dateType) {
        if (timeMillis <= 0) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        calendar.setTime(new Date(timeMillis));
        return calendar.get(dateType);
    }

    private final int getInteger(Date date, int dateType) {
        if (date != null) {
            return getCalendar$default(this, date, null, 2, null).get(dateType);
        }
        return 0;
    }

    @JvmStatic
    public static final String getMinAndSec(long timeMillis) {
        String str = ZStringUtil.format("%02d' %02d'%s ", Integer.valueOf(getMinute(timeMillis)), Integer.valueOf(getSecond(timeMillis)), "'");
        Intrinsics.checkNotNullExpressionValue(str, "ZStringUtil.format(\n    …imeMillis), \"'\"\n        )");
        return str;
    }

    @JvmStatic
    public static final int getMinute(long timeMillis) {
        return INSTANCE.getInteger(timeMillis, 12);
    }

    @JvmStatic
    public static final int getMinute(Date date) {
        return INSTANCE.getInteger(date, 12);
    }

    @JvmStatic
    public static final Month getMonth(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        switch (INSTANCE.getInteger(date, 2)) {
        }
        return Month.JANUARY;
    }

    @JvmStatic
    public static final int getSecond(long timeMillis) {
        return INSTANCE.getInteger(timeMillis, 13);
    }

    @JvmStatic
    public static final int getSecond(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return INSTANCE.getInteger(date, 13);
    }

    @JvmStatic
    public static final long getTimeMillisByHM(long timeMillis) {
        Date dateTimeMillisToDate = timeMillisToDate(timeMillis);
        return ((getHour(dateTimeMillisToDate) * 3600) + (getMinute(dateTimeMillisToDate) * 60)) * 1000;
    }

    @JvmStatic
    public static final long getTimeMillisByHM(String dateStr) {
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Date dateStringToDate$default = stringToDate$default(dateStr, "HH:mm", null, 4, null);
        return ((getHour(dateStringToDate$default) * 3600) + (getMinute(dateStringToDate$default) * 60)) * 1000;
    }

    @JvmStatic
    public static final Week getWeek(long timeMillis) {
        return getWeek(new Date(timeMillis));
    }

    @JvmStatic
    public static final Week getWeek(String date, String pattern) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return getWeek(stringToDate$default(date, pattern, null, 4, null));
    }

    @JvmStatic
    public static final Week getWeek(Date date) {
        switch (INSTANCE.getInteger(date, 7)) {
            case 1:
                return Week.SUNDAY;
            case 2:
                return Week.MONDAY;
            case 3:
                return Week.TUESDAY;
            case 4:
                return Week.WEDNESDAY;
            case 5:
                return Week.THURSDAY;
            case 6:
                return Week.FRIDAY;
            case 7:
                return Week.SATURDAY;
            default:
                return Week.MONDAY;
        }
    }

    @JvmStatic
    public static final long getWeekEnd(long timeMillis) {
        return getDayEnd(timeMillis) + ((7 - getWeek(timeMillis).getNumber()) * 86400000);
    }

    @JvmStatic
    public static final long getWeekStart(long timeMillis) {
        return getDayStart(timeMillis - ((getWeek(timeMillis).getNumber() - 1) * 86400000));
    }

    @JvmStatic
    public static final int getYear(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return INSTANCE.getInteger(date, 1);
    }

    @JvmStatic
    public static final Date stringToDate(String dateStr, String pattern, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            return INSTANCE.getDateFormat(pattern, timeZone).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date stringToDate$default(String str, String str2, TimeZone timeZone, int i, Object obj) {
        if ((i & 4) != 0) {
            timeZone = TimeZone.getDefault();
            Intrinsics.checkNotNullExpressionValue(timeZone, "TimeZone.getDefault()");
        }
        return stringToDate(str, str2, timeZone);
    }

    @JvmStatic
    public static final Date timeMillisToDate(long timeMillis) {
        return new Date(timeMillis);
    }

    @JvmStatic
    public static final String timeMillisToString(long timeMillis, String pattern, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return dateToString(new Date(timeMillis), pattern, timeZone);
    }

    public static String timeMillisToString$default(long j, String str, TimeZone timeZone, int i, Object obj) {
        if ((i & 4) != 0) {
            timeZone = TimeZone.getDefault();
            Intrinsics.checkNotNullExpressionValue(timeZone, "TimeZone.getDefault()");
        }
        return timeMillisToString(j, str, timeZone);
    }
}
