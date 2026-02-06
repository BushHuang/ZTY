package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.util.RawValue;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory implements Serializable, JsonNodeCreator {
    private static final long serialVersionUID = 1;
    private final boolean _cfgBigDecimalExact;
    private static final JsonNodeFactory decimalsNormalized = new JsonNodeFactory(false);
    private static final JsonNodeFactory decimalsAsIs = new JsonNodeFactory(true);
    public static final JsonNodeFactory instance = decimalsNormalized;

    protected JsonNodeFactory() {
        this(false);
    }

    public JsonNodeFactory(boolean z) {
        this._cfgBigDecimalExact = z;
    }

    public static JsonNodeFactory withExactBigDecimals(boolean z) {
        return z ? decimalsAsIs : decimalsNormalized;
    }

    protected boolean _inIntRange(long j) {
        return ((long) ((int) j)) == j;
    }

    @Override
    public ArrayNode arrayNode() {
        return new ArrayNode(this);
    }

    @Override
    public ArrayNode arrayNode(int i) {
        return new ArrayNode(this, i);
    }

    @Override
    public BinaryNode binaryNode(byte[] bArr) {
        return BinaryNode.valueOf(bArr);
    }

    @Override
    public BinaryNode binaryNode(byte[] bArr, int i, int i2) {
        return BinaryNode.valueOf(bArr, i, i2);
    }

    @Override
    public BooleanNode booleanNode(boolean z) {
        return z ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    @Override
    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    @Override
    public NumericNode numberNode(byte b) {
        return IntNode.valueOf(b);
    }

    @Override
    public NumericNode numberNode(double d) {
        return DoubleNode.valueOf(d);
    }

    @Override
    public NumericNode numberNode(float f) {
        return FloatNode.valueOf(f);
    }

    @Override
    public NumericNode numberNode(int i) {
        return IntNode.valueOf(i);
    }

    @Override
    public NumericNode numberNode(long j) {
        return LongNode.valueOf(j);
    }

    @Override
    public NumericNode numberNode(short s) {
        return ShortNode.valueOf(s);
    }

    @Override
    public ValueNode numberNode(Byte b) {
        return b == null ? nullNode() : IntNode.valueOf(b.intValue());
    }

    @Override
    public ValueNode numberNode(Double d) {
        return d == null ? nullNode() : DoubleNode.valueOf(d.doubleValue());
    }

    @Override
    public ValueNode numberNode(Float f) {
        return f == null ? nullNode() : FloatNode.valueOf(f.floatValue());
    }

    @Override
    public ValueNode numberNode(Integer num) {
        return num == null ? nullNode() : IntNode.valueOf(num.intValue());
    }

    @Override
    public ValueNode numberNode(Long l) {
        return l == null ? nullNode() : LongNode.valueOf(l.longValue());
    }

    @Override
    public ValueNode numberNode(Short sh) {
        return sh == null ? nullNode() : ShortNode.valueOf(sh.shortValue());
    }

    @Override
    public ValueNode numberNode(BigDecimal bigDecimal) {
        return bigDecimal == null ? nullNode() : this._cfgBigDecimalExact ? DecimalNode.valueOf(bigDecimal) : bigDecimal.compareTo(BigDecimal.ZERO) == 0 ? DecimalNode.ZERO : DecimalNode.valueOf(bigDecimal.stripTrailingZeros());
    }

    @Override
    public ValueNode numberNode(BigInteger bigInteger) {
        return bigInteger == null ? nullNode() : BigIntegerNode.valueOf(bigInteger);
    }

    @Override
    public ObjectNode objectNode() {
        return new ObjectNode(this);
    }

    @Override
    public ValueNode pojoNode(Object obj) {
        return new POJONode(obj);
    }

    @Override
    public ValueNode rawValueNode(RawValue rawValue) {
        return new POJONode(rawValue);
    }

    @Override
    public TextNode textNode(String str) {
        return TextNode.valueOf(str);
    }
}
