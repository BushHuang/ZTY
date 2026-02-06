package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer extends ReferenceTypeDeserializer<AtomicReference<Object>> {
    private static final long serialVersionUID = 1;

    public AtomicReferenceDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(javaType, valueInstantiator, typeDeserializer, jsonDeserializer);
    }

    @Override
    public Object getEmptyValue(DeserializationContext deserializationContext) {
        return new AtomicReference();
    }

    @Override
    public AtomicReference<Object> getNullValue(DeserializationContext deserializationContext) {
        return new AtomicReference<>();
    }

    @Override
    public Object getReferenced(AtomicReference<Object> atomicReference) {
        return atomicReference.get();
    }

    @Override
    public AtomicReference<Object> referenceValue(Object obj) {
        return new AtomicReference<>(obj);
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    @Override
    public AtomicReference<Object> updateReference(AtomicReference<Object> atomicReference, Object obj) {
        atomicReference.set(obj);
        return atomicReference;
    }

    @Override
    public ReferenceTypeDeserializer<AtomicReference<Object>> withResolved(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return new AtomicReferenceDeserializer(this._fullType, this._valueInstantiator, typeDeserializer, jsonDeserializer);
    }

    @Override
    public ReferenceTypeDeserializer<AtomicReference<Object>> withResolved2(TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        return withResolved(typeDeserializer, (JsonDeserializer<?>) jsonDeserializer);
    }
}
