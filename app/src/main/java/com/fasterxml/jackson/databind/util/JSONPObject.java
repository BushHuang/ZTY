package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public class JSONPObject implements JsonSerializable {
    protected final String _function;
    protected final JavaType _serializationType;
    protected final Object _value;

    public JSONPObject(String str, Object obj) {
        this(str, obj, (JavaType) null);
    }

    public JSONPObject(String str, Object obj, JavaType javaType) {
        this._function = str;
        this._value = obj;
        this._serializationType = javaType;
    }

    public String getFunction() {
        return this._function;
    }

    public JavaType getSerializationType() {
        return this._serializationType;
    }

    public Object getValue() {
        return this._value;
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeRaw(this._function);
        jsonGenerator.writeRaw('(');
        if (this._value == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else {
            boolean z = jsonGenerator.getCharacterEscapes() == null;
            if (z) {
                jsonGenerator.setCharacterEscapes(JsonpCharacterEscapes.instance());
            }
            try {
                if (this._serializationType != null) {
                    serializerProvider.findTypedValueSerializer(this._serializationType, true, (BeanProperty) null).serialize(this._value, jsonGenerator, serializerProvider);
                } else {
                    serializerProvider.findTypedValueSerializer(this._value.getClass(), true, (BeanProperty) null).serialize(this._value, jsonGenerator, serializerProvider);
                }
            } finally {
                if (z) {
                    jsonGenerator.setCharacterEscapes(null);
                }
            }
        }
        jsonGenerator.writeRaw(')');
    }

    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        serialize(jsonGenerator, serializerProvider);
    }
}
