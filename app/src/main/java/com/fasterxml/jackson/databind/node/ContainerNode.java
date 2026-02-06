package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ContainerNode<T extends ContainerNode<T>> extends BaseJsonNode implements JsonNodeCreator {
    protected final JsonNodeFactory _nodeFactory;

    protected ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    @Override
    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    @Override
    public final ArrayNode arrayNode(int i) {
        return this._nodeFactory.arrayNode(i);
    }

    @Override
    public String asText() {
        return "";
    }

    @Override
    public abstract JsonToken asToken();

    @Override
    public final BinaryNode binaryNode(byte[] bArr) {
        return this._nodeFactory.binaryNode(bArr);
    }

    @Override
    public final BinaryNode binaryNode(byte[] bArr, int i, int i2) {
        return this._nodeFactory.binaryNode(bArr, i, i2);
    }

    @Override
    public final BooleanNode booleanNode(boolean z) {
        return this._nodeFactory.booleanNode(z);
    }

    @Override
    public abstract JsonNode get(int i);

    @Override
    public abstract JsonNode get(String str);

    @Override
    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    @Override
    public final NumericNode numberNode(byte b) {
        return this._nodeFactory.numberNode(b);
    }

    @Override
    public final NumericNode numberNode(double d) {
        return this._nodeFactory.numberNode(d);
    }

    @Override
    public final NumericNode numberNode(float f) {
        return this._nodeFactory.numberNode(f);
    }

    @Override
    public final NumericNode numberNode(int i) {
        return this._nodeFactory.numberNode(i);
    }

    @Override
    public final NumericNode numberNode(long j) {
        return this._nodeFactory.numberNode(j);
    }

    @Override
    public final NumericNode numberNode(short s) {
        return this._nodeFactory.numberNode(s);
    }

    @Override
    public final ValueNode numberNode(Byte b) {
        return this._nodeFactory.numberNode(b);
    }

    @Override
    public final ValueNode numberNode(Double d) {
        return this._nodeFactory.numberNode(d);
    }

    @Override
    public final ValueNode numberNode(Float f) {
        return this._nodeFactory.numberNode(f);
    }

    @Override
    public final ValueNode numberNode(Integer num) {
        return this._nodeFactory.numberNode(num);
    }

    @Override
    public final ValueNode numberNode(Long l) {
        return this._nodeFactory.numberNode(l);
    }

    @Override
    public final ValueNode numberNode(Short sh) {
        return this._nodeFactory.numberNode(sh);
    }

    @Override
    public final ValueNode numberNode(BigDecimal bigDecimal) {
        return this._nodeFactory.numberNode(bigDecimal);
    }

    @Override
    public final ValueNode numberNode(BigInteger bigInteger) {
        return this._nodeFactory.numberNode(bigInteger);
    }

    @Override
    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    @Override
    public final ValueNode pojoNode(Object obj) {
        return this._nodeFactory.pojoNode(obj);
    }

    @Override
    public final ValueNode rawValueNode(RawValue rawValue) {
        return this._nodeFactory.rawValueNode(rawValue);
    }

    public abstract T removeAll();

    @Override
    public abstract int size();

    @Override
    public final TextNode textNode(String str) {
        return this._nodeFactory.textNode(str);
    }
}
