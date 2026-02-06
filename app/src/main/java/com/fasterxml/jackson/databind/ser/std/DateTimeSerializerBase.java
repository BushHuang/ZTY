package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DateTimeSerializerBase<T> extends StdScalarSerializer<T> implements ContextualSerializer {
    protected final DateFormat _customFormat;
    protected final AtomicReference<DateFormat> _reusedCustomFormat;
    protected final Boolean _useTimestamp;

    protected DateTimeSerializerBase(Class<T> cls, Boolean bool, DateFormat dateFormat) {
        super(cls);
        this._useTimestamp = bool;
        this._customFormat = dateFormat;
        this._reusedCustomFormat = dateFormat == null ? null : new AtomicReference<>();
    }

    protected void _acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, boolean z) throws JsonMappingException {
        if (z) {
            visitIntFormat(jsonFormatVisitorWrapper, javaType, JsonParser.NumberType.LONG, JsonValueFormat.UTC_MILLISEC);
        } else {
            visitStringFormat(jsonFormatVisitorWrapper, javaType, JsonValueFormat.DATE_TIME);
        }
    }

    protected boolean _asTimestamp(SerializerProvider serializerProvider) {
        Boolean bool = this._useTimestamp;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (this._customFormat != null) {
            return false;
        }
        if (serializerProvider != null) {
            return serializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        throw new IllegalArgumentException("Null SerializerProvider passed for " + handledType().getName());
    }

    protected void _serializeAsString(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (this._customFormat == null) {
            serializerProvider.defaultSerializeDateValue(date, jsonGenerator);
            return;
        }
        DateFormat andSet = this._reusedCustomFormat.getAndSet(null);
        if (andSet == null) {
            andSet = (DateFormat) this._customFormat.clone();
        }
        jsonGenerator.writeString(andSet.format(date));
        this._reusedCustomFormat.compareAndSet(null, andSet);
    }

    protected abstract long _timestamp(T t);

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        _acceptJsonFormatVisitor(jsonFormatVisitorWrapper, javaType, _asTimestamp(jsonFormatVisitorWrapper.getProvider()));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, handledType());
        if (valueFindFormatOverrides == null) {
            return this;
        }
        JsonFormat.Shape shape = valueFindFormatOverrides.getShape();
        if (shape.isNumeric()) {
            return withFormat(Boolean.TRUE, null);
        }
        if (valueFindFormatOverrides.hasPattern()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(valueFindFormatOverrides.getPattern(), valueFindFormatOverrides.hasLocale() ? valueFindFormatOverrides.getLocale() : serializerProvider.getLocale());
            simpleDateFormat.setTimeZone(valueFindFormatOverrides.hasTimeZone() ? valueFindFormatOverrides.getTimeZone() : serializerProvider.getTimeZone());
            return withFormat(Boolean.FALSE, simpleDateFormat);
        }
        boolean zHasLocale = valueFindFormatOverrides.hasLocale();
        boolean zHasTimeZone = valueFindFormatOverrides.hasTimeZone();
        boolean z = shape == JsonFormat.Shape.STRING;
        if (!zHasLocale && !zHasTimeZone && !z) {
            return this;
        }
        DateFormat dateFormat = serializerProvider.getConfig().getDateFormat();
        if (dateFormat instanceof StdDateFormat) {
            StdDateFormat stdDateFormatWithTimeZone = (StdDateFormat) dateFormat;
            if (valueFindFormatOverrides.hasLocale()) {
                stdDateFormatWithTimeZone = stdDateFormatWithTimeZone.withLocale(valueFindFormatOverrides.getLocale());
            }
            if (valueFindFormatOverrides.hasTimeZone()) {
                stdDateFormatWithTimeZone = stdDateFormatWithTimeZone.withTimeZone(valueFindFormatOverrides.getTimeZone());
            }
            return withFormat(Boolean.FALSE, stdDateFormatWithTimeZone);
        }
        if (!(dateFormat instanceof SimpleDateFormat)) {
            serializerProvider.reportBadDefinition((Class<?>) handledType(), String.format("Configured `DateFormat` (%s) not a `SimpleDateFormat`; cannot configure `Locale` or `TimeZone`", dateFormat.getClass().getName()));
        }
        SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) dateFormat;
        SimpleDateFormat simpleDateFormat3 = zHasLocale ? new SimpleDateFormat(simpleDateFormat2.toPattern(), valueFindFormatOverrides.getLocale()) : (SimpleDateFormat) simpleDateFormat2.clone();
        TimeZone timeZone = valueFindFormatOverrides.getTimeZone();
        if ((timeZone == null || timeZone.equals(simpleDateFormat3.getTimeZone())) ? false : true) {
            simpleDateFormat3.setTimeZone(timeZone);
        }
        return withFormat(Boolean.FALSE, simpleDateFormat3);
    }

    @Override
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(_asTimestamp(serializerProvider) ? "number" : "string", true);
    }

    @Override
    public boolean isEmpty(SerializerProvider serializerProvider, T t) {
        return false;
    }

    @Override
    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    public abstract DateTimeSerializerBase<T> withFormat(Boolean bool, DateFormat dateFormat);
}
