package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class BooleanNode extends ValueNode {
    private final boolean _value;
    public static final BooleanNode TRUE = new BooleanNode(true);
    public static final BooleanNode FALSE = new BooleanNode(false);

    protected BooleanNode(boolean z) {
        this._value = z;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    @Override
    public boolean asBoolean() {
        return this._value;
    }

    @Override
    public boolean asBoolean(boolean z) {
        return this._value;
    }

    @Override
    public double asDouble(double d) {
        return this._value ? 1.0d : 0.0d;
    }

    @Override
    public int asInt(int i) {
        return this._value ? 1 : 0;
    }

    @Override
    public long asLong(long j) {
        return this._value ? 1L : 0L;
    }

    @Override
    public String asText() {
        return this._value ? "true" : "false";
    }

    @Override
    public JsonToken asToken() {
        return this._value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    @Override
    public boolean booleanValue() {
        return this._value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof BooleanNode) && this._value == ((BooleanNode) obj)._value;
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.BOOLEAN;
    }

    @Override
    public int hashCode() {
        return this._value ? 3 : 1;
    }

    @Override
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeBoolean(this._value);
    }
}
