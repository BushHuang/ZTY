package com.xh.xhcore.common.util;

import android.os.SystemClock;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class DateUtil {
    private static final String ALTERNATIVE_ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String RFC822_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
    private static volatile long amendTimeSkewed;

    static int getDefaultRawOffset() {
        return TimeZone.getDefault().getRawOffset();
    }

    @Deprecated
    public static Long getNetworkTimeMillis() {
        return getServerTimeMillis();
    }

    private static DateFormat getRfc822DateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat;
    }

    public static Long getServerTimeMillis() {
        if (amendTimeSkewed > 0) {
            return Long.valueOf(SystemClock.elapsedRealtime() + amendTimeSkewed);
        }
        return null;
    }

    public static long getTimeMillis() {
        Long serverTimeMillis = getServerTimeMillis();
        return serverTimeMillis != null ? serverTimeMillis.longValue() : System.currentTimeMillis();
    }

    private static Date parseRfc822Date(String str) throws ParseException {
        return getRfc822DateFormat().parse(str);
    }

    public static synchronized void setCurrentServerTime(String str) {
        if (amendTimeSkewed > 0) {
            return;
        }
        try {
            amendTimeSkewed = parseRfc822Date(str).getTime() - SystemClock.elapsedRealtime();
        } catch (ParseException unused) {
            LogUtils.e("fail to parse serverTime responseDateString = " + str);
        }
    }
}
