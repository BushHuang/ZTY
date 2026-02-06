package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class NumericNode extends ValueNode {
    protected NumericNode() {
    }

    @Override
    public final double asDouble() {
        return doubleValue();
    }

    @Override
    public final double asDouble(double d) {
        return doubleValue();
    }

    @Override
    public final int asInt() {
        return intValue();
    }

    @Override
    public final int asInt(int i) {
        return intValue();
    }

    @Override
    public final long asLong() {
        return longValue();
    }

    @Override
    public final long asLong(long j) {
        return longValue();
    }

    @Override
    public abstract String asText();

    @Override
    public abstract BigInteger bigIntegerValue();

    @Override
    public abstract boolean canConvertToInt();

    @Override
    public abstract boolean canConvertToLong();

    @Override
    public abstract BigDecimal decimalValue();

    @Override
    public abstract double doubleValue();

    @Override
    public final JsonNodeType getNodeType() {
        return JsonNodeType.NUMBER;
    }

    @Override
    public abstract int intValue();

    public boolean isNaN() {
        return false;
    }

    @Override
    public abstract long longValue();

    @Override
    public abstract JsonParser.NumberType numberType();

    @Override
    public abstract Number numberValue();
}
