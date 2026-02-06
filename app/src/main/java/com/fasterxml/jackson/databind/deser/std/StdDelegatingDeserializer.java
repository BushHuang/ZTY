package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;

public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final Converter<Object, T> _converter;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JavaType _delegateType;

    protected StdDelegatingDeserializer(StdDelegatingDeserializer<T> stdDelegatingDeserializer) {
        super(stdDelegatingDeserializer);
        this._converter = stdDelegatingDeserializer._converter;
        this._delegateType = stdDelegatingDeserializer._delegateType;
        this._delegateDeserializer = stdDelegatingDeserializer._delegateDeserializer;
    }

    public StdDelegatingDeserializer(Converter<?, T> converter) {
        super((Class<?>) Object.class);
        this._converter = converter;
        this._delegateType = null;
        this._delegateDeserializer = null;
    }

    public StdDelegatingDeserializer(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        this._converter = converter;
        this._delegateType = javaType;
        this._delegateDeserializer = jsonDeserializer;
    }

    protected Object _handleIncompatibleUpdateValue(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        throw new UnsupportedOperationException(String.format("Cannot update object of type %s (using deserializer for type %s)" + obj.getClass().getName(), this._delegateType));
    }

    protected T convertValue(Object obj) {
        return this._converter.convert(obj);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, this._delegateType);
            return jsonDeserializerHandleSecondaryContextualization != this._delegateDeserializer ? withDelegate(this._converter, this._delegateType, jsonDeserializerHandleSecondaryContextualization) : this;
        }
        JavaType inputType = this._converter.getInputType(deserializationContext.getTypeFactory());
        return withDelegate(this._converter, inputType, deserializationContext.findContextualValueDeserializer(inputType, beanProperty));
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object objDeserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (objDeserialize == null) {
            return null;
        }
        return convertValue(objDeserialize);
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        return this._delegateType.getRawClass().isAssignableFrom(obj.getClass()) ? (T) this._delegateDeserializer.deserialize(jsonParser, deserializationContext, obj) : (T) _handleIncompatibleUpdateValue(jsonParser, deserializationContext, obj);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        Object objDeserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (objDeserialize == null) {
            return null;
        }
        return convertValue(objDeserialize);
    }

    @Override
    public JsonDeserializer<?> getDelegatee() {
        return this._delegateDeserializer;
    }

    @Override
    public Class<?> handledType() {
        return this._delegateDeserializer.handledType();
    }

    @Override
    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        NullValueProvider nullValueProvider = this._delegateDeserializer;
        if (nullValueProvider == null || !(nullValueProvider instanceof ResolvableDeserializer)) {
            return;
        }
        ((ResolvableDeserializer) nullValueProvider).resolve(deserializationContext);
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._delegateDeserializer.supportsUpdate(deserializationConfig);
    }

    protected StdDelegatingDeserializer<T> withDelegate(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "withDelegate");
        return new StdDelegatingDeserializer<>(converter, javaType, jsonDeserializer);
    }
}
