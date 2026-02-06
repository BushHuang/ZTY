package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

public class DateDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    @JacksonStdImpl
    public static class CalendarDeserializer extends DateBasedDeserializer<Calendar> {
        protected final Constructor<Calendar> _defaultCtor;

        public CalendarDeserializer() {
            super(Calendar.class);
            this._defaultCtor = null;
        }

        public CalendarDeserializer(CalendarDeserializer calendarDeserializer, DateFormat dateFormat, String str) {
            super(calendarDeserializer, dateFormat, str);
            this._defaultCtor = calendarDeserializer._defaultCtor;
        }

        public CalendarDeserializer(Class<? extends Calendar> cls) {
            super(cls);
            this._defaultCtor = ClassUtil.findConstructor(cls, false);
        }

        @Override
        public JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        @Override
        public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IllegalAccessException, InstantiationException, IOException, IllegalArgumentException, InvocationTargetException {
            Date date_parseDate = _parseDate(jsonParser, deserializationContext);
            if (date_parseDate == null) {
                return null;
            }
            Constructor<Calendar> constructor = this._defaultCtor;
            if (constructor == null) {
                return deserializationContext.constructCalendar(date_parseDate);
            }
            try {
                Calendar calendarNewInstance = constructor.newInstance(new Object[0]);
                calendarNewInstance.setTimeInMillis(date_parseDate.getTime());
                TimeZone timeZone = deserializationContext.getTimeZone();
                if (timeZone != null) {
                    calendarNewInstance.setTimeZone(timeZone);
                }
                return calendarNewInstance;
            } catch (Exception e) {
                return (Calendar) deserializationContext.handleInstantiationProblem(handledType(), date_parseDate, e);
            }
        }

        @Override
        protected DateBasedDeserializer<Calendar> withDateFormat2(DateFormat dateFormat, String str) {
            return new CalendarDeserializer(this, dateFormat, str);
        }
    }

    protected static abstract class DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements ContextualDeserializer {
        protected final DateFormat _customFormat;
        protected final String _formatString;

        protected DateBasedDeserializer(DateBasedDeserializer<T> dateBasedDeserializer, DateFormat dateFormat, String str) {
            super(dateBasedDeserializer._valueClass);
            this._customFormat = dateFormat;
            this._formatString = str;
        }

        protected DateBasedDeserializer(Class<?> cls) {
            super(cls);
            this._customFormat = null;
            this._formatString = null;
        }

        @Override
        protected Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            Date date;
            if (this._customFormat == null || !jsonParser.hasToken(JsonToken.VALUE_STRING)) {
                return super._parseDate(jsonParser, deserializationContext);
            }
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return (Date) getEmptyValue(deserializationContext);
            }
            synchronized (this._customFormat) {
                try {
                    try {
                        date = this._customFormat.parse(strTrim);
                    } catch (ParseException unused) {
                        return (Date) deserializationContext.handleWeirdStringValue(handledType(), strTrim, "expected format \"%s\"", this._formatString);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return date;
        }

        public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            DateFormat dateFormat;
            DateFormat dateFormatWithLenient;
            JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(deserializationContext, beanProperty, handledType());
            if (valueFindFormatOverrides != null) {
                TimeZone timeZone = valueFindFormatOverrides.getTimeZone();
                Boolean lenient = valueFindFormatOverrides.getLenient();
                if (valueFindFormatOverrides.hasPattern()) {
                    String pattern = valueFindFormatOverrides.getPattern();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, valueFindFormatOverrides.hasLocale() ? valueFindFormatOverrides.getLocale() : deserializationContext.getLocale());
                    if (timeZone == null) {
                        timeZone = deserializationContext.getTimeZone();
                    }
                    simpleDateFormat.setTimeZone(timeZone);
                    if (lenient != null) {
                        simpleDateFormat.setLenient(lenient.booleanValue());
                    }
                    return withDateFormat2(simpleDateFormat, pattern);
                }
                if (timeZone != null) {
                    DateFormat dateFormat2 = deserializationContext.getConfig().getDateFormat();
                    if (dateFormat2.getClass() == StdDateFormat.class) {
                        StdDateFormat stdDateFormatWithLocale = ((StdDateFormat) dateFormat2).withTimeZone(timeZone).withLocale(valueFindFormatOverrides.hasLocale() ? valueFindFormatOverrides.getLocale() : deserializationContext.getLocale());
                        dateFormatWithLenient = stdDateFormatWithLocale;
                        if (lenient != null) {
                            dateFormatWithLenient = stdDateFormatWithLocale.withLenient(lenient);
                        }
                    } else {
                        DateFormat dateFormat3 = (DateFormat) dateFormat2.clone();
                        dateFormat3.setTimeZone(timeZone);
                        dateFormatWithLenient = dateFormat3;
                        if (lenient != null) {
                            dateFormat3.setLenient(lenient.booleanValue());
                            dateFormatWithLenient = dateFormat3;
                        }
                    }
                    return withDateFormat2(dateFormatWithLenient, this._formatString);
                }
                if (lenient != null) {
                    DateFormat dateFormat4 = deserializationContext.getConfig().getDateFormat();
                    String pattern2 = this._formatString;
                    if (dateFormat4.getClass() == StdDateFormat.class) {
                        StdDateFormat stdDateFormatWithLenient = ((StdDateFormat) dateFormat4).withLenient(lenient);
                        pattern2 = stdDateFormatWithLenient.toPattern();
                        dateFormat = stdDateFormatWithLenient;
                    } else {
                        DateFormat dateFormat5 = (DateFormat) dateFormat4.clone();
                        dateFormat5.setLenient(lenient.booleanValue());
                        boolean z = dateFormat5 instanceof SimpleDateFormat;
                        dateFormat = dateFormat5;
                        if (z) {
                            ((SimpleDateFormat) dateFormat5).toPattern();
                            dateFormat = dateFormat5;
                        }
                    }
                    if (pattern2 == null) {
                        pattern2 = "[unknown]";
                    }
                    return withDateFormat2(dateFormat, pattern2);
                }
            }
            return this;
        }

        protected abstract DateBasedDeserializer<T> withDateFormat2(DateFormat dateFormat, String str);
    }

    @JacksonStdImpl
    public static class DateDeserializer extends DateBasedDeserializer<Date> {
        public static final DateDeserializer instance = new DateDeserializer();

        public DateDeserializer() {
            super(Date.class);
        }

        public DateDeserializer(DateDeserializer dateDeserializer, DateFormat dateFormat, String str) {
            super(dateDeserializer, dateFormat, str);
        }

        @Override
        public JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return _parseDate(jsonParser, deserializationContext);
        }

        @Override
        protected DateBasedDeserializer<Date> withDateFormat2(DateFormat dateFormat, String str) {
            return new DateDeserializer(this, dateFormat, str);
        }
    }

    public static class SqlDateDeserializer extends DateBasedDeserializer<java.sql.Date> {
        public SqlDateDeserializer() {
            super(java.sql.Date.class);
        }

        public SqlDateDeserializer(SqlDateDeserializer sqlDateDeserializer, DateFormat dateFormat, String str) {
            super(sqlDateDeserializer, dateFormat, str);
        }

        @Override
        public JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        @Override
        public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            Date date_parseDate = _parseDate(jsonParser, deserializationContext);
            if (date_parseDate == null) {
                return null;
            }
            return new java.sql.Date(date_parseDate.getTime());
        }

        @Override
        protected DateBasedDeserializer<java.sql.Date> withDateFormat2(DateFormat dateFormat, String str) {
            return new SqlDateDeserializer(this, dateFormat, str);
        }
    }

    public static class TimestampDeserializer extends DateBasedDeserializer<Timestamp> {
        public TimestampDeserializer() {
            super(Timestamp.class);
        }

        public TimestampDeserializer(TimestampDeserializer timestampDeserializer, DateFormat dateFormat, String str) {
            super(timestampDeserializer, dateFormat, str);
        }

        @Override
        public JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        @Override
        public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            Date date_parseDate = _parseDate(jsonParser, deserializationContext);
            if (date_parseDate == null) {
                return null;
            }
            return new Timestamp(date_parseDate.getTime());
        }

        @Override
        protected DateBasedDeserializer<Timestamp> withDateFormat2(DateFormat dateFormat, String str) {
            return new TimestampDeserializer(this, dateFormat, str);
        }
    }

    static {
        Class[] clsArr = {Calendar.class, GregorianCalendar.class, java.sql.Date.class, Date.class, Timestamp.class};
        for (int i = 0; i < 5; i++) {
            _classNames.add(clsArr[i].getName());
        }
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (!_classNames.contains(str)) {
            return null;
        }
        if (cls == Calendar.class) {
            return new CalendarDeserializer();
        }
        if (cls == Date.class) {
            return DateDeserializer.instance;
        }
        if (cls == java.sql.Date.class) {
            return new SqlDateDeserializer();
        }
        if (cls == Timestamp.class) {
            return new TimestampDeserializer();
        }
        if (cls == GregorianCalendar.class) {
            return new CalendarDeserializer(GregorianCalendar.class);
        }
        return null;
    }
}
