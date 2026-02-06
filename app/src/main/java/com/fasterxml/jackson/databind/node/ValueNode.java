package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.List;

public abstract class ValueNode extends BaseJsonNode {
    protected ValueNode() {
    }

    @Override
    protected JsonNode _at(JsonPointer jsonPointer) {
        return MissingNode.getInstance();
    }

    @Override
    public abstract JsonToken asToken();

    @Override
    public <T extends JsonNode> T deepCopy() {
        return this;
    }

    @Override
    public final ObjectNode findParent(String str) {
        return null;
    }

    @Override
    public final List<JsonNode> findParents(String str, List<JsonNode> list) {
        return list;
    }

    @Override
    public final JsonNode findValue(String str) {
        return null;
    }

    @Override
    public final List<JsonNode> findValues(String str, List<JsonNode> list) {
        return list;
    }

    @Override
    public final List<String> findValuesAsText(String str, List<String> list) {
        return list;
    }

    @Override
    public final JsonNode get(int i) {
        return null;
    }

    @Override
    public final JsonNode get(String str) {
        return null;
    }

    @Override
    public final boolean has(int i) {
        return false;
    }

    @Override
    public final boolean has(String str) {
        return false;
    }

    @Override
    public final boolean hasNonNull(int i) {
        return false;
    }

    @Override
    public final boolean hasNonNull(String str) {
        return false;
    }

    @Override
    public final JsonNode path(int i) {
        return MissingNode.getInstance();
    }

    @Override
    public final JsonNode path(String str) {
        return MissingNode.getInstance();
    }

    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(this, asToken()));
        serialize(jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
    }

    @Override
    public String toString() {
        return asText();
    }
}
