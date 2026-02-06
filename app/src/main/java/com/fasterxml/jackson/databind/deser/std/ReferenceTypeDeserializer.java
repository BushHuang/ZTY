package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;

public abstract class ReferenceTypeDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer {
    private static final long serialVersionUID = 2;
    protected final JavaType _fullType;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    public ReferenceTypeDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        this._valueInstantiator = valueInstantiator;
        this._fullType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
    }

    @Deprecated
    public ReferenceTypeDeserializer(JavaType javaType, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        this(javaType, null, typeDeserializer, jsonDeserializer);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        JsonDeserializer<?> jsonDeserializerFindContextualValueDeserializer = jsonDeserializer == null ? deserializationContext.findContextualValueDeserializer(this._fullType.getReferencedType(), beanProperty) : deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, this._fullType.getReferencedType());
        TypeDeserializer typeDeserializerForProperty = this._valueTypeDeserializer;
        if (typeDeserializerForProperty != null) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        return (jsonDeserializerFindContextualValueDeserializer == this._valueDeserializer && typeDeserializerForProperty == this._valueTypeDeserializer) ? this : withResolved(typeDeserializerForProperty, jsonDeserializerFindContextualValueDeserializer);
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ValueInstantiator valueInstantiator = this._valueInstantiator;
        if (valueInstantiator != null) {
            return (T) deserialize(jsonParser, deserializationContext, valueInstantiator.createUsingDefault(deserializationContext));
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        return (T) referenceValue(typeDeserializer == null ? this._valueDeserializer.deserialize(jsonParser, deserializationContext) : this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer));
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) throws IOException {
        Object objDeserialize;
        if (this._valueDeserializer.supportsUpdate(deserializationContext.getConfig()).equals(Boolean.FALSE) || this._valueTypeDeserializer != null) {
            TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
            objDeserialize = typeDeserializer == null ? this._valueDeserializer.deserialize(jsonParser, deserializationContext) : this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        } else {
            Object referenced = getReferenced(t);
            if (referenced == null) {
                TypeDeserializer typeDeserializer2 = this._valueTypeDeserializer;
                return referenceValue(typeDeserializer2 == null ? this._valueDeserializer.deserialize(jsonParser, deserializationContext) : this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer2));
            }
            objDeserialize = this._valueDeserializer.deserialize(jsonParser, deserializationContext, referenced);
        }
        return updateReference(t, objDeserialize);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return getNullValue(deserializationContext);
        }
        TypeDeserializer typeDeserializer2 = this._valueTypeDeserializer;
        return typeDeserializer2 == null ? deserialize(jsonParser, deserializationContext) : referenceValue(typeDeserializer2.deserializeTypedFromAny(jsonParser, deserializationContext));
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override
    public Object getEmptyValue(DeserializationContext deserializationContext) {
        return getNullValue(deserializationContext);
    }

    @Override
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override
    public abstract T getNullValue(DeserializationContext deserializationContext);

    public abstract Object getReferenced(T t);

    @Override
    public JavaType getValueType() {
        return this._fullType;
    }

    public abstract T referenceValue(Object obj);

    @Override
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer == null) {
            return null;
        }
        return jsonDeserializer.supportsUpdate(deserializationConfig);
    }

    public abstract T updateReference(T t, Object obj);

    protected abstract ReferenceTypeDeserializer<T> withResolved(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);
}
