package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import java.io.IOException;

public class ConstantValueInstantiator extends ValueInstantiator {
    protected final Object _value;

    public ConstantValueInstantiator(Object obj) {
        this._value = obj;
    }

    @Override
    public boolean canCreateUsingDefault() {
        return true;
    }

    @Override
    public boolean canInstantiate() {
        return true;
    }

    @Override
    public Object createUsingDefault(DeserializationContext deserializationContext) throws IOException {
        return this._value;
    }

    @Override
    public Class<?> getValueClass() {
        return this._value.getClass();
    }
}
