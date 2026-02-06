package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final Class<Enum> _enumClass;
    protected JsonDeserializer<Enum<?>> _enumDeserializer;
    protected final JavaType _enumType;
    protected final Boolean _unwrapSingle;

    public EnumSetDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        super((Class<?>) EnumSet.class);
        this._enumType = javaType;
        Class rawClass = javaType.getRawClass();
        this._enumClass = rawClass;
        if (rawClass.isEnum()) {
            this._enumDeserializer = jsonDeserializer;
            this._unwrapSingle = null;
        } else {
            throw new IllegalArgumentException("Type " + javaType + " not Java Enum type");
        }
    }

    protected EnumSetDeserializer(EnumSetDeserializer enumSetDeserializer, JsonDeserializer<?> jsonDeserializer, Boolean bool) {
        super(enumSetDeserializer);
        this._enumType = enumSetDeserializer._enumType;
        this._enumClass = enumSetDeserializer._enumClass;
        this._enumDeserializer = jsonDeserializer;
        this._unwrapSingle = bool;
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }

    protected final EnumSet<?> _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, EnumSet enumSet) throws IOException {
        while (true) {
            try {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (jsonTokenNextToken == JsonToken.END_ARRAY) {
                    return enumSet;
                }
                if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                    return (EnumSet) deserializationContext.handleUnexpectedToken(this._enumClass, jsonParser);
                }
                Enum<?> enumDeserialize = this._enumDeserializer.deserialize(jsonParser, deserializationContext);
                if (enumDeserialize != null) {
                    enumSet.add(enumDeserialize);
                }
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath(e, enumSet, enumSet.size());
            }
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        Boolean boolFindFormatFeature = findFormatFeature(deserializationContext, beanProperty, EnumSet.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonDeserializer<Enum<?>> jsonDeserializer = this._enumDeserializer;
        return withResolved(jsonDeserializer == null ? deserializationContext.findContextualValueDeserializer(this._enumType, beanProperty) : deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, this._enumType), boolFindFormatFeature);
    }

    @Override
    public EnumSet<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EnumSet enumSetConstructSet = constructSet();
        return !jsonParser.isExpectedStartArrayToken() ? handleNonArray(jsonParser, deserializationContext, enumSetConstructSet) : _deserialize(jsonParser, deserializationContext, enumSetConstructSet);
    }

    @Override
    public EnumSet<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, EnumSet<?> enumSet) throws IOException {
        return !jsonParser.isExpectedStartArrayToken() ? handleNonArray(jsonParser, deserializationContext, enumSet) : _deserialize(jsonParser, deserializationContext, enumSet);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    protected EnumSet<?> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, EnumSet enumSet) throws IOException {
        if (!(this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {
            return (EnumSet) deserializationContext.handleUnexpectedToken(EnumSet.class, jsonParser);
        }
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return (EnumSet) deserializationContext.handleUnexpectedToken(this._enumClass, jsonParser);
        }
        try {
            Enum<?> enumDeserialize = this._enumDeserializer.deserialize(jsonParser, deserializationContext);
            if (enumDeserialize != null) {
                enumSet.add(enumDeserialize);
            }
            return enumSet;
        } catch (Exception e) {
            throw JsonMappingException.wrapWithPath(e, enumSet, enumSet.size());
        }
    }

    @Override
    public boolean isCachable() {
        return this._enumType.getValueHandler() == null;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    public EnumSetDeserializer withDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return this._enumDeserializer == jsonDeserializer ? this : new EnumSetDeserializer(this, jsonDeserializer, this._unwrapSingle);
    }

    public EnumSetDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, Boolean bool) {
        return (this._unwrapSingle == bool && this._enumDeserializer == jsonDeserializer) ? this : new EnumSetDeserializer(this, jsonDeserializer, bool);
    }
}
