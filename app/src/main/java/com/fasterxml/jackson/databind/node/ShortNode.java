package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ShortNode extends NumericNode {
    protected final short _value;

    public ShortNode(short s) {
        this._value = s;
    }

    public static ShortNode valueOf(short s) {
        return new ShortNode(s);
    }

    @Override
    public boolean asBoolean(boolean z) {
        return this._value != 0;
    }

    @Override
    public String asText() {
        return NumberOutput.toString((int) this._value);
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override
    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    @Override
    public boolean canConvertToInt() {
        return true;
    }

    @Override
    public boolean canConvertToLong() {
        return true;
    }

    @Override
    public BigDecimal decimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    @Override
    public double doubleValue() {
        return this._value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof ShortNode) && ((ShortNode) obj)._value == this._value;
    }

    @Override
    public float floatValue() {
        return this._value;
    }

    @Override
    public int hashCode() {
        return this._value;
    }

    @Override
    public int intValue() {
        return this._value;
    }

    @Override
    public boolean isIntegralNumber() {
        return true;
    }

    @Override
    public boolean isShort() {
        return true;
    }

    @Override
    public long longValue() {
        return this._value;
    }

    @Override
    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.INT;
    }

    @Override
    public Number numberValue() {
        return Short.valueOf(this._value);
    }

    @Override
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(this._value);
    }

    @Override
    public short shortValue() {
        return this._value;
    }
}
