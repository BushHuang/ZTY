package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS;
    protected static final Calendar CALENDAR;
    protected static final DateFormat DATE_FORMAT_ISO8601;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    protected static final Locale DEFAULT_LOCALE;
    protected static final TimeZone DEFAULT_TIMEZONE;
    protected static final Pattern PATTERN_ISO8601;
    protected static final Pattern PATTERN_PLAIN = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d");
    protected static final String PATTERN_PLAIN_STR = "\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d";
    public static final StdDateFormat instance;
    private transient Calendar _calendar;
    private transient DateFormat _formatRFC1123;
    protected Boolean _lenient;
    protected final Locale _locale;
    protected transient TimeZone _timezone;
    private boolean _tzSerializedWithColon;

    static {
        try {
            PATTERN_ISO8601 = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d(?:[:]\\d\\d)?(\\.\\d+)?(Z|[+-]\\d\\d(?:[:]?\\d\\d)?)?");
            ALL_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
            DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
            DEFAULT_LOCALE = Locale.US;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", DEFAULT_LOCALE);
            DATE_FORMAT_RFC1123 = simpleDateFormat;
            simpleDateFormat.setTimeZone(DEFAULT_TIMEZONE);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", DEFAULT_LOCALE);
            DATE_FORMAT_ISO8601 = simpleDateFormat2;
            simpleDateFormat2.setTimeZone(DEFAULT_TIMEZONE);
            instance = new StdDateFormat();
            CALENDAR = new GregorianCalendar(DEFAULT_TIMEZONE, DEFAULT_LOCALE);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public StdDateFormat() {
        this._tzSerializedWithColon = false;
        this._locale = DEFAULT_LOCALE;
    }

    @Deprecated
    public StdDateFormat(TimeZone timeZone, Locale locale) {
        this._tzSerializedWithColon = false;
        this._timezone = timeZone;
        this._locale = locale;
    }

    protected StdDateFormat(TimeZone timeZone, Locale locale, Boolean bool) {
        this(timeZone, locale, bool, false);
    }

    protected StdDateFormat(TimeZone timeZone, Locale locale, Boolean bool, boolean z) {
        this._tzSerializedWithColon = false;
        this._timezone = timeZone;
        this._locale = locale;
        this._lenient = bool;
        this._tzSerializedWithColon = z;
    }

    private static final DateFormat _cloneFormat(DateFormat dateFormat, String str, TimeZone timeZone, Locale locale, Boolean bool) {
        DateFormat simpleDateFormat;
        if (locale.equals(DEFAULT_LOCALE)) {
            simpleDateFormat = (DateFormat) dateFormat.clone();
            if (timeZone != null) {
                simpleDateFormat.setTimeZone(timeZone);
            }
        } else {
            simpleDateFormat = new SimpleDateFormat(str, locale);
            if (timeZone == null) {
                timeZone = DEFAULT_TIMEZONE;
            }
            simpleDateFormat.setTimeZone(timeZone);
        }
        if (bool != null) {
            simpleDateFormat.setLenient(bool.booleanValue());
        }
        return simpleDateFormat;
    }

    protected static <T> boolean _equals(T t, T t2) {
        if (t == t2) {
            return true;
        }
        return t != null && t.equals(t2);
    }

    private static int _parse2D(String str, int i) {
        return ((str.charAt(i) - '0') * 10) + (str.charAt(i + 1) - '0');
    }

    private static int _parse4D(String str, int i) {
        return ((str.charAt(i) - '0') * 1000) + ((str.charAt(i + 1) - '0') * 100) + ((str.charAt(i + 2) - '0') * 10) + (str.charAt(i + 3) - '0');
    }

    private Date _parseDateFromLong(String str, ParsePosition parsePosition) throws ParseException {
        try {
            return new Date(NumberInput.parseLong(str));
        } catch (NumberFormatException unused) {
            throw new ParseException(String.format("Timestamp value %s out of 64-bit value range", str), parsePosition.getErrorIndex());
        }
    }

    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }

    @Deprecated
    public static DateFormat getISO8601Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timeZone, locale, null);
    }

    @Deprecated
    public static DateFormat getRFC1123Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", timeZone, locale, null);
    }

    private static void pad2(StringBuffer stringBuffer, int i) {
        int i2 = i / 10;
        if (i2 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i2 + 48));
            i -= i2 * 10;
        }
        stringBuffer.append((char) (i + 48));
    }

    private static void pad3(StringBuffer stringBuffer, int i) {
        int i2 = i / 100;
        if (i2 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i2 + 48));
            i -= i2 * 100;
        }
        pad2(stringBuffer, i);
    }

    private static void pad4(StringBuffer stringBuffer, int i) {
        int i2 = i / 100;
        if (i2 == 0) {
            stringBuffer.append('0');
            stringBuffer.append('0');
        } else {
            if (i2 > 99) {
                stringBuffer.append(i2);
            } else {
                pad2(stringBuffer, i2);
            }
            i -= i2 * 100;
        }
        pad2(stringBuffer, i);
    }

    protected void _clearFormats() {
        this._formatRFC1123 = null;
    }

    protected void _format(TimeZone timeZone, Locale locale, Date date, StringBuffer stringBuffer) {
        Calendar calendar_getCalendar = _getCalendar(timeZone);
        calendar_getCalendar.setTime(date);
        int i = calendar_getCalendar.get(1);
        if (calendar_getCalendar.get(0) == 0) {
            _formatBCEYear(stringBuffer, i);
        } else {
            if (i > 9999) {
                stringBuffer.append('+');
            }
            pad4(stringBuffer, i);
        }
        stringBuffer.append('-');
        pad2(stringBuffer, calendar_getCalendar.get(2) + 1);
        stringBuffer.append('-');
        pad2(stringBuffer, calendar_getCalendar.get(5));
        stringBuffer.append('T');
        pad2(stringBuffer, calendar_getCalendar.get(11));
        stringBuffer.append(':');
        pad2(stringBuffer, calendar_getCalendar.get(12));
        stringBuffer.append(':');
        pad2(stringBuffer, calendar_getCalendar.get(13));
        stringBuffer.append('.');
        pad3(stringBuffer, calendar_getCalendar.get(14));
        int offset = timeZone.getOffset(calendar_getCalendar.getTimeInMillis());
        if (offset == 0) {
            if (this._tzSerializedWithColon) {
                stringBuffer.append("+00:00");
                return;
            } else {
                stringBuffer.append("+0000");
                return;
            }
        }
        int i2 = offset / 60000;
        int iAbs = Math.abs(i2 / 60);
        int iAbs2 = Math.abs(i2 % 60);
        stringBuffer.append(offset < 0 ? '-' : '+');
        pad2(stringBuffer, iAbs);
        if (this._tzSerializedWithColon) {
            stringBuffer.append(':');
        }
        pad2(stringBuffer, iAbs2);
    }

    protected void _formatBCEYear(StringBuffer stringBuffer, int i) {
        if (i == 1) {
            stringBuffer.append("+0000");
        } else {
            stringBuffer.append('-');
            pad4(stringBuffer, i - 1);
        }
    }

    protected Calendar _getCalendar(TimeZone timeZone) {
        Calendar calendar = this._calendar;
        if (calendar == null) {
            calendar = (Calendar) CALENDAR.clone();
            this._calendar = calendar;
        }
        if (!calendar.getTimeZone().equals(timeZone)) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setLenient(isLenient());
        return calendar;
    }

    protected Date _parseAsISO8601(String str, ParsePosition parsePosition) throws ParseException, IllegalArgumentException {
        char c;
        String str2;
        int length = str.length();
        TimeZone timeZone = DEFAULT_TIMEZONE;
        if (this._timezone != null && 'Z' != str.charAt(length - 1)) {
            timeZone = this._timezone;
        }
        Calendar calendar_getCalendar = _getCalendar(timeZone);
        calendar_getCalendar.clear();
        int iCharAt = 0;
        if (length > 10) {
            Matcher matcher = PATTERN_ISO8601.matcher(str);
            if (matcher.matches()) {
                int iStart = matcher.start(2);
                int iEnd = matcher.end(2);
                int i = iEnd - iStart;
                if (i > 1) {
                    int i_parse2D = _parse2D(str, iStart + 1) * 3600;
                    if (i >= 5) {
                        i_parse2D += _parse2D(str, iEnd - 2) * 60;
                    }
                    calendar_getCalendar.set(15, str.charAt(iStart) == '-' ? i_parse2D * (-1000) : i_parse2D * 1000);
                    calendar_getCalendar.set(16, 0);
                }
                calendar_getCalendar.set(_parse4D(str, 0), _parse2D(str, 5) - 1, _parse2D(str, 8), _parse2D(str, 11), _parse2D(str, 14), (length <= 16 || str.charAt(16) != ':') ? 0 : _parse2D(str, 17));
                int iStart2 = matcher.start(1) + 1;
                int iEnd2 = matcher.end(1);
                if (iStart2 >= iEnd2) {
                    calendar_getCalendar.set(14, 0);
                } else {
                    int i2 = iEnd2 - iStart2;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 != 2) {
                                if (i2 != 3 && i2 > 9) {
                                    throw new ParseException(String.format("Cannot parse date \"%s\": invalid fractional seconds '%s'; can use at most 9 digits", str, matcher.group(1).substring(1)), iStart2);
                                }
                                iCharAt = 0 + (str.charAt(iStart2 + 2) - '0');
                            }
                            iCharAt += (str.charAt(iStart2 + 1) - '0') * 10;
                        }
                        iCharAt += (str.charAt(iStart2) - '0') * 100;
                    }
                    calendar_getCalendar.set(14, iCharAt);
                }
                return calendar_getCalendar.getTime();
            }
            c = 1;
            str2 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        } else {
            if (PATTERN_PLAIN.matcher(str).matches()) {
                calendar_getCalendar.set(_parse4D(str, 0), _parse2D(str, 5) - 1, _parse2D(str, 8), 0, 0, 0);
                calendar_getCalendar.set(14, 0);
                return calendar_getCalendar.getTime();
            }
            str2 = "yyyy-MM-dd";
            c = 1;
        }
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[c] = str2;
        objArr[2] = this._lenient;
        throw new ParseException(String.format("Cannot parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)", objArr), 0);
    }

    protected Date _parseDate(String str, ParsePosition parsePosition) throws ParseException {
        if (looksLikeISO8601(str)) {
            return parseAsISO8601(str, parsePosition);
        }
        int length = str.length();
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            char cCharAt = str.charAt(length);
            if (cCharAt < '0' || cCharAt > '9') {
                if (length > 0 || cCharAt != '-') {
                    break;
                }
            }
        }
        return (length >= 0 || !(str.charAt(0) == '-' || NumberInput.inLongRange(str, false))) ? parseAsRFC1123(str, parsePosition) : _parseDateFromLong(str, parsePosition);
    }

    @Override
    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale, this._lenient, this._tzSerializedWithColon);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        TimeZone timeZone = this._timezone;
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        _format(timeZone, this._locale, date, stringBuffer);
        return stringBuffer;
    }

    @Override
    public TimeZone getTimeZone() {
        return this._timezone;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    public boolean isColonIncludedInTimeZone() {
        return this._tzSerializedWithColon;
    }

    @Override
    public boolean isLenient() {
        Boolean bool = this._lenient;
        return bool == null || bool.booleanValue();
    }

    protected boolean looksLikeISO8601(String str) {
        return str.length() >= 7 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == '-' && Character.isDigit(str.charAt(5));
    }

    @Override
    public Date parse(String str) throws ParseException {
        String strTrim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date date_parseDate = _parseDate(strTrim, parsePosition);
        if (date_parseDate != null) {
            return date_parseDate;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('\"');
            }
            sb.append(str2);
        }
        sb.append('\"');
        throw new ParseException(String.format("Cannot parse date \"%s\": not compatible with any of standard forms (%s)", strTrim, sb.toString()), parsePosition.getErrorIndex());
    }

    @Override
    public Date parse(String str, ParsePosition parsePosition) {
        try {
            return _parseDate(str, parsePosition);
        } catch (ParseException unused) {
            return null;
        }
    }

    protected Date parseAsISO8601(String str, ParsePosition parsePosition) throws ParseException {
        try {
            return _parseAsISO8601(str, parsePosition);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format("Cannot parse date \"%s\", problem: %s", str, e.getMessage()), parsePosition.getErrorIndex());
        }
    }

    protected Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", this._timezone, this._locale, this._lenient);
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }

    @Override
    public void setLenient(boolean z) {
        Boolean boolValueOf = Boolean.valueOf(z);
        if (_equals(boolValueOf, this._lenient)) {
            return;
        }
        this._lenient = boolValueOf;
        _clearFormats();
    }

    @Override
    public void setTimeZone(TimeZone timeZone) {
        if (timeZone.equals(this._timezone)) {
            return;
        }
        _clearFormats();
        this._timezone = timeZone;
    }

    public String toPattern() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("[one of: '");
        sb.append("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        sb.append("', '");
        sb.append("EEE, dd MMM yyyy HH:mm:ss zzz");
        sb.append("' (");
        sb.append(Boolean.FALSE.equals(this._lenient) ? "strict" : "lenient");
        sb.append(")]");
        return sb.toString();
    }

    public String toString() {
        return String.format("DateFormat %s: (timezone: %s, locale: %s, lenient: %s)", getClass().getName(), this._timezone, this._locale, this._lenient);
    }

    public StdDateFormat withColonInTimeZone(boolean z) {
        return this._tzSerializedWithColon == z ? this : new StdDateFormat(this._timezone, this._locale, this._lenient, z);
    }

    public StdDateFormat withLenient(Boolean bool) {
        return _equals(bool, this._lenient) ? this : new StdDateFormat(this._timezone, this._locale, bool, this._tzSerializedWithColon);
    }

    public StdDateFormat withLocale(Locale locale) {
        return locale.equals(this._locale) ? this : new StdDateFormat(this._timezone, locale, this._lenient, this._tzSerializedWithColon);
    }

    public StdDateFormat withTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        TimeZone timeZone2 = this._timezone;
        return (timeZone == timeZone2 || timeZone.equals(timeZone2)) ? this : new StdDateFormat(timeZone, this._locale, this._lenient, this._tzSerializedWithColon);
    }
}
