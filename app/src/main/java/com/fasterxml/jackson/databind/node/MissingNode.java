package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public final class MissingNode extends ValueNode {
    private static final MissingNode instance = new MissingNode();

    protected MissingNode() {
    }

    public static MissingNode getInstance() {
        return instance;
    }

    @Override
    public String asText() {
        return "";
    }

    @Override
    public String asText(String str) {
        return str;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
    }

    @Override
    public <T extends JsonNode> T deepCopy() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.MISSING;
    }

    @Override
    public int hashCode() {
        return JsonNodeType.MISSING.ordinal();
    }

    @Override
    public boolean isMissingNode() {
        return true;
    }

    @Override
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNull();
    }

    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonGenerator.writeNull();
    }

    @Override
    public String toString() {
        return "";
    }
}
