package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;
import java.util.Collection;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<?> _delegatee;

    public DelegatingDeserializer(JsonDeserializer<?> jsonDeserializer) {
        super(jsonDeserializer.handledType());
        this._delegatee = jsonDeserializer;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(this._delegatee, beanProperty, deserializationContext.constructType(this._delegatee.handledType()));
        return jsonDeserializerHandleSecondaryContextualization == this._delegatee ? this : newDelegatingInstance(jsonDeserializerHandleSecondaryContextualization);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return this._delegatee.deserialize(jsonParser, deserializationContext);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        return this._delegatee.deserialize(jsonParser, deserializationContext, obj);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return this._delegatee.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    @Override
    public SettableBeanProperty findBackReference(String str) {
        return this._delegatee.findBackReference(str);
    }

    @Override
    public JsonDeserializer<?> getDelegatee() {
        return this._delegatee;
    }

    @Override
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return this._delegatee.getEmptyValue(deserializationContext);
    }

    @Override
    public Collection<Object> getKnownPropertyNames() {
        return this._delegatee.getKnownPropertyNames();
    }

    @Override
    public AccessPattern getNullAccessPattern() {
        return this._delegatee.getNullAccessPattern();
    }

    @Override
    public Object getNullValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return this._delegatee.getNullValue(deserializationContext);
    }

    @Override
    public ObjectIdReader getObjectIdReader() {
        return this._delegatee.getObjectIdReader();
    }

    @Override
    public boolean isCachable() {
        return this._delegatee.isCachable();
    }

    protected abstract JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> jsonDeserializer);

    @Override
    public JsonDeserializer<?> replaceDelegatee(JsonDeserializer<?> jsonDeserializer) {
        return jsonDeserializer == this._delegatee ? this : newDelegatingInstance(jsonDeserializer);
    }

    @Override
    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        NullValueProvider nullValueProvider = this._delegatee;
        if (nullValueProvider instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) nullValueProvider).resolve(deserializationContext);
        }
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._delegatee.supportsUpdate(deserializationConfig);
    }
}
