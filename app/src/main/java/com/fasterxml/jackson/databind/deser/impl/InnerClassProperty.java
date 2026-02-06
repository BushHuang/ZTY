package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class InnerClassProperty extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1;
    protected AnnotatedConstructor _annotated;
    protected final transient Constructor<?> _creator;

    protected InnerClassProperty(SettableBeanProperty settableBeanProperty, AnnotatedConstructor annotatedConstructor) {
        super(settableBeanProperty);
        this._annotated = annotatedConstructor;
        Constructor<?> annotated = annotatedConstructor == null ? null : annotatedConstructor.getAnnotated();
        this._creator = annotated;
        if (annotated == null) {
            throw new IllegalArgumentException("Missing constructor (broken JDK (de)serialization?)");
        }
    }

    public InnerClassProperty(SettableBeanProperty settableBeanProperty, Constructor<?> constructor) {
        super(settableBeanProperty);
        this._creator = constructor;
    }

    @Override
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IllegalAccessException, InstantiationException, IOException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        Object objDeserializeWithType;
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            objDeserializeWithType = this._valueDeserializer.getNullValue(deserializationContext);
        } else if (this._valueTypeDeserializer != null) {
            objDeserializeWithType = this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
        } else {
            try {
                objNewInstance = this._creator.newInstance(obj);
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e, String.format("Failed to instantiate class %s, problem: %s", this._creator.getDeclaringClass().getName(), e.getMessage()));
                objNewInstance = null;
            }
            this._valueDeserializer.deserialize(jsonParser, deserializationContext, objNewInstance);
            objDeserializeWithType = objNewInstance;
        }
        set(obj, objDeserializeWithType);
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    Object readResolve() {
        return new InnerClassProperty(this, this._annotated);
    }

    @Override
    protected SettableBeanProperty withDelegate(SettableBeanProperty settableBeanProperty) {
        return settableBeanProperty == this.delegate ? this : new InnerClassProperty(settableBeanProperty, this._creator);
    }

    Object writeReplace() {
        return this._annotated == null ? new InnerClassProperty(this, new AnnotatedConstructor(null, this._creator, null, null)) : this;
    }
}
