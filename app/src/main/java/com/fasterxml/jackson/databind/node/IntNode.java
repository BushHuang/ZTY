package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class IntNode extends NumericNode {
    private static final IntNode[] CANONICALS = new IntNode[12];
    static final int MAX_CANONICAL = 10;
    static final int MIN_CANONICAL = -1;
    protected final int _value;

    static {
        for (int i = 0; i < 12; i++) {
            CANONICALS[i] = new IntNode(i - 1);
        }
    }

    public IntNode(int i) {
        this._value = i;
    }

    public static IntNode valueOf(int i) {
        return (i > 10 || i < -1) ? new IntNode(i) : CANONICALS[i - (-1)];
    }

    @Override
    public boolean asBoolean(boolean z) {
        return this._value != 0;
    }

    @Override
    public String asText() {
        return NumberOutput.toString(this._value);
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
        return obj != null && (obj instanceof IntNode) && ((IntNode) obj)._value == this._value;
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
    public boolean isInt() {
        return true;
    }

    @Override
    public boolean isIntegralNumber() {
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
        return Integer.valueOf(this._value);
    }

    @Override
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(this._value);
    }

    @Override
    public short shortValue() {
        return (short) this._value;
    }
}
