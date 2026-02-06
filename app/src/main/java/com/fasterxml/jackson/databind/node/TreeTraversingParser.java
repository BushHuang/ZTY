package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.node.NodeCursor;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TreeTraversingParser extends ParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    static class AnonymousClass1 {
        static final int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = iArr;
            try {
                iArr[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public TreeTraversingParser(JsonNode jsonNode) {
        this(jsonNode, null);
    }

    public TreeTraversingParser(JsonNode jsonNode, ObjectCodec objectCodec) {
        super(0);
        this._objectCodec = objectCodec;
        if (jsonNode.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new NodeCursor.ArrayCursor(jsonNode, null);
        } else if (!jsonNode.isObject()) {
            this._nodeCursor = new NodeCursor.RootCursor(jsonNode, null);
        } else {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new NodeCursor.ObjectCursor(jsonNode, null);
        }
    }

    @Override
    protected void _handleEOF() throws JsonParseException {
        _throwInternal();
    }

    @Override
    public void close() throws IOException {
        if (this._closed) {
            return;
        }
        this._closed = true;
        this._nodeCursor = null;
        this._currToken = null;
    }

    protected JsonNode currentNode() {
        NodeCursor nodeCursor;
        if (this._closed || (nodeCursor = this._nodeCursor) == null) {
            return null;
        }
        return nodeCursor.currentNode();
    }

    protected JsonNode currentNumericNode() throws JsonParseException {
        JsonNode jsonNodeCurrentNode = currentNode();
        if (jsonNodeCurrentNode != null && jsonNodeCurrentNode.isNumber()) {
            return jsonNodeCurrentNode;
        }
        throw _constructError("Current token (" + (jsonNodeCurrentNode == null ? null : jsonNodeCurrentNode.asToken()) + ") not numeric, cannot use numeric value accessors");
    }

    @Override
    public BigInteger getBigIntegerValue() throws IOException {
        return currentNumericNode().bigIntegerValue();
    }

    @Override
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        JsonNode jsonNodeCurrentNode = currentNode();
        if (jsonNodeCurrentNode != null) {
            return jsonNodeCurrentNode instanceof TextNode ? ((TextNode) jsonNodeCurrentNode).getBinaryValue(base64Variant) : jsonNodeCurrentNode.binaryValue();
        }
        return null;
    }

    @Override
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override
    public JsonLocation getCurrentLocation() {
        return JsonLocation.NA;
    }

    @Override
    public String getCurrentName() {
        NodeCursor nodeCursor = this._nodeCursor;
        if (nodeCursor == null) {
            return null;
        }
        return nodeCursor.getCurrentName();
    }

    @Override
    public BigDecimal getDecimalValue() throws IOException {
        return currentNumericNode().decimalValue();
    }

    @Override
    public double getDoubleValue() throws IOException {
        return currentNumericNode().doubleValue();
    }

    @Override
    public Object getEmbeddedObject() {
        JsonNode jsonNodeCurrentNode;
        if (this._closed || (jsonNodeCurrentNode = currentNode()) == null) {
            return null;
        }
        if (jsonNodeCurrentNode.isPojo()) {
            return ((POJONode) jsonNodeCurrentNode).getPojo();
        }
        if (jsonNodeCurrentNode.isBinary()) {
            return ((BinaryNode) jsonNodeCurrentNode).binaryValue();
        }
        return null;
    }

    @Override
    public float getFloatValue() throws IOException {
        return (float) currentNumericNode().doubleValue();
    }

    @Override
    public int getIntValue() throws IOException {
        return currentNumericNode().intValue();
    }

    @Override
    public long getLongValue() throws IOException {
        return currentNumericNode().longValue();
    }

    @Override
    public JsonParser.NumberType getNumberType() throws IOException {
        JsonNode jsonNodeCurrentNumericNode = currentNumericNode();
        if (jsonNodeCurrentNumericNode == null) {
            return null;
        }
        return jsonNodeCurrentNumericNode.numberType();
    }

    @Override
    public Number getNumberValue() throws IOException {
        return currentNumericNode().numberValue();
    }

    @Override
    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    @Override
    public String getText() {
        JsonNode jsonNodeCurrentNode;
        if (this._closed) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 1) {
            return this._nodeCursor.getCurrentName();
        }
        if (i == 2) {
            return currentNode().textValue();
        }
        if (i == 3 || i == 4) {
            return String.valueOf(currentNode().numberValue());
        }
        if (i == 5 && (jsonNodeCurrentNode = currentNode()) != null && jsonNodeCurrentNode.isBinary()) {
            return jsonNodeCurrentNode.asText();
        }
        if (this._currToken == null) {
            return null;
        }
        return this._currToken.asString();
    }

    @Override
    public char[] getTextCharacters() throws IOException {
        return getText().toCharArray();
    }

    @Override
    public int getTextLength() throws IOException {
        return getText().length();
    }

    @Override
    public int getTextOffset() throws IOException {
        return 0;
    }

    @Override
    public JsonLocation getTokenLocation() {
        return JsonLocation.NA;
    }

    @Override
    public boolean hasTextCharacters() {
        return false;
    }

    @Override
    public boolean isClosed() {
        return this._closed;
    }

    @Override
    public boolean isNaN() {
        if (this._closed) {
            return false;
        }
        JsonNode jsonNodeCurrentNode = currentNode();
        if (jsonNodeCurrentNode instanceof NumericNode) {
            return ((NumericNode) jsonNodeCurrentNode).isNaN();
        }
        return false;
    }

    @Override
    public JsonToken nextToken() throws IOException {
        JsonToken jsonToken = this._nextToken;
        if (jsonToken != null) {
            this._currToken = jsonToken;
            this._nextToken = null;
            return this._currToken;
        }
        if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            NodeCursor nodeCursorIterateChildren = this._nodeCursor.iterateChildren();
            this._nodeCursor = nodeCursorIterateChildren;
            this._currToken = nodeCursorIterateChildren.nextToken();
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        }
        NodeCursor nodeCursor = this._nodeCursor;
        if (nodeCursor == null) {
            this._closed = true;
            return null;
        }
        this._currToken = nodeCursor.nextToken();
        if (this._currToken == null) {
            this._currToken = this._nodeCursor.endToken();
            this._nodeCursor = this._nodeCursor.getParent();
            return this._currToken;
        }
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = true;
        }
        return this._currToken;
    }

    @Override
    public void overrideCurrentName(String str) {
        NodeCursor nodeCursor = this._nodeCursor;
        if (nodeCursor != null) {
            nodeCursor.overrideCurrentName(str);
        }
    }

    @Override
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        byte[] binaryValue = getBinaryValue(base64Variant);
        if (binaryValue == null) {
            return 0;
        }
        outputStream.write(binaryValue, 0, binaryValue.length);
        return binaryValue.length;
    }

    @Override
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override
    public JsonParser skipChildren() throws IOException {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }
}
