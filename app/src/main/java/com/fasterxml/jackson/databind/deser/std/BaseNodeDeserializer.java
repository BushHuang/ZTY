package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;

abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> {
    protected final Boolean _supportsUpdates;

    public BaseNodeDeserializer(Class<T> cls, Boolean bool) {
        super((Class<?>) cls);
        this._supportsUpdates = bool;
    }

    protected final JsonNode _fromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        Object embeddedObject = jsonParser.getEmbeddedObject();
        return embeddedObject == null ? jsonNodeFactory.nullNode() : embeddedObject.getClass() == byte[].class ? jsonNodeFactory.binaryNode((byte[]) embeddedObject) : embeddedObject instanceof RawValue ? jsonNodeFactory.rawValueNode((RawValue) embeddedObject) : embeddedObject instanceof JsonNode ? (JsonNode) embeddedObject : jsonNodeFactory.pojoNode(embeddedObject);
    }

    protected final JsonNode _fromFloat(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        return numberType == JsonParser.NumberType.BIG_DECIMAL ? jsonNodeFactory.numberNode(jsonParser.getDecimalValue()) : deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.isNaN() ? jsonNodeFactory.numberNode(jsonParser.getDoubleValue()) : jsonNodeFactory.numberNode(jsonParser.getDecimalValue()) : numberType == JsonParser.NumberType.FLOAT ? jsonNodeFactory.numberNode(jsonParser.getFloatValue()) : jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        JsonParser.NumberType numberType = (F_MASK_INT_COERCIONS & deserializationFeatures) != 0 ? DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures) ? JsonParser.NumberType.BIG_INTEGER : DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures) ? JsonParser.NumberType.LONG : jsonParser.getNumberType() : jsonParser.getNumberType();
        return numberType == JsonParser.NumberType.INT ? jsonNodeFactory.numberNode(jsonParser.getIntValue()) : numberType == JsonParser.NumberType.LONG ? jsonNodeFactory.numberNode(jsonParser.getLongValue()) : jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected void _handleDuplicateField(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws JsonProcessingException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            deserializationContext.reportInputMismatch(JsonNode.class, "Duplicate field '%s' for ObjectNode: not allowed when FAIL_ON_READING_DUP_TREE_KEY enabled", str);
        }
    }

    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId == 2) {
            return jsonNodeFactory.objectNode();
        }
        switch (currentTokenId) {
            case 5:
                return deserializeObjectAtName(jsonParser, deserializationContext, jsonNodeFactory);
            case 6:
                return jsonNodeFactory.textNode(jsonParser.getText());
            case 7:
                return _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            case 8:
                return _fromFloat(jsonParser, deserializationContext, jsonNodeFactory);
            case 9:
                return jsonNodeFactory.booleanNode(true);
            case 10:
                return jsonNodeFactory.booleanNode(false);
            case 11:
                return jsonNodeFactory.nullNode();
            case 12:
                return _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
            default:
                return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
        }
    }

    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            switch (jsonParser.nextToken().id()) {
                case 1:
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 2:
                case 5:
                case 8:
                default:
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 3:
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 4:
                    return arrayNode;
                case 6:
                    arrayNode.add(jsonNodeFactory.textNode(jsonParser.getText()));
                    break;
                case 7:
                    arrayNode.add(_fromInt(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 9:
                    arrayNode.add(jsonNodeFactory.booleanNode(true));
                    break;
                case 10:
                    arrayNode.add(jsonNodeFactory.booleanNode(false));
                    break;
                case 11:
                    arrayNode.add(jsonNodeFactory.nullNode());
                    break;
                case 12:
                    arrayNode.add(_fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
            }
        }
    }

    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode jsonNodeDeserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String strNextFieldName = jsonParser.nextFieldName();
        while (strNextFieldName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken == null) {
                jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
            }
            int iId = jsonTokenNextToken.id();
            if (iId == 1) {
                jsonNodeDeserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 3) {
                jsonNodeDeserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 6) {
                jsonNodeDeserializeObject = jsonNodeFactory.textNode(jsonParser.getText());
            } else if (iId != 7) {
                switch (iId) {
                    case 9:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(true);
                        break;
                    case 10:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(false);
                        break;
                    case 11:
                        jsonNodeDeserializeObject = jsonNodeFactory.nullNode();
                        break;
                    case 12:
                        jsonNodeDeserializeObject = _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        jsonNodeDeserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            } else {
                jsonNodeDeserializeObject = _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            }
            JsonNode jsonNode = jsonNodeDeserializeObject;
            JsonNode jsonNodeReplace = objectNode.replace(strNextFieldName, jsonNode);
            if (jsonNodeReplace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, strNextFieldName, objectNode, jsonNodeReplace, jsonNode);
            }
            strNextFieldName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    protected final ObjectNode deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode jsonNodeDeserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String currentName = jsonParser.getCurrentName();
        while (currentName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken == null) {
                jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
            }
            int iId = jsonTokenNextToken.id();
            if (iId == 1) {
                jsonNodeDeserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 3) {
                jsonNodeDeserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 6) {
                jsonNodeDeserializeObject = jsonNodeFactory.textNode(jsonParser.getText());
            } else if (iId != 7) {
                switch (iId) {
                    case 9:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(true);
                        break;
                    case 10:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(false);
                        break;
                    case 11:
                        jsonNodeDeserializeObject = jsonNodeFactory.nullNode();
                        break;
                    case 12:
                        jsonNodeDeserializeObject = _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        jsonNodeDeserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            } else {
                jsonNodeDeserializeObject = _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            }
            JsonNode jsonNode = jsonNodeDeserializeObject;
            JsonNode jsonNodeReplace = objectNode.replace(currentName, jsonNode);
            if (jsonNodeReplace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, currentName, objectNode, jsonNodeReplace, jsonNode);
            }
            currentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._supportsUpdates;
    }

    protected final JsonNode updateArray(JsonParser jsonParser, DeserializationContext deserializationContext, ArrayNode arrayNode) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        while (true) {
            switch (jsonParser.nextToken().id()) {
                case 1:
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 2:
                case 5:
                case 8:
                default:
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 3:
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 4:
                    return arrayNode;
                case 6:
                    arrayNode.add(nodeFactory.textNode(jsonParser.getText()));
                    break;
                case 7:
                    arrayNode.add(_fromInt(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 9:
                    arrayNode.add(nodeFactory.booleanNode(true));
                    break;
                case 10:
                    arrayNode.add(nodeFactory.booleanNode(false));
                    break;
                case 11:
                    arrayNode.add(nodeFactory.nullNode());
                    break;
                case 12:
                    arrayNode.add(_fromEmbedded(jsonParser, deserializationContext, nodeFactory));
                    break;
            }
        }
    }

    protected final JsonNode updateObject(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectNode objectNode) throws IOException {
        String currentName;
        JsonNode jsonNodeDeserializeObject;
        if (jsonParser.isExpectedStartObjectToken()) {
            currentName = jsonParser.nextFieldName();
        } else {
            if (!jsonParser.hasToken(JsonToken.FIELD_NAME)) {
                return deserialize(jsonParser, deserializationContext);
            }
            currentName = jsonParser.getCurrentName();
        }
        while (currentName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            JsonNode jsonNode = objectNode.get(currentName);
            if (jsonNode == null) {
                if (jsonTokenNextToken == null) {
                    jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
                }
                JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
                int iId = jsonTokenNextToken.id();
                if (iId == 1) {
                    jsonNodeDeserializeObject = deserializeObject(jsonParser, deserializationContext, nodeFactory);
                } else if (iId == 3) {
                    jsonNodeDeserializeObject = deserializeArray(jsonParser, deserializationContext, nodeFactory);
                } else if (iId == 6) {
                    jsonNodeDeserializeObject = nodeFactory.textNode(jsonParser.getText());
                } else if (iId != 7) {
                    switch (iId) {
                        case 9:
                            jsonNodeDeserializeObject = nodeFactory.booleanNode(true);
                            break;
                        case 10:
                            jsonNodeDeserializeObject = nodeFactory.booleanNode(false);
                            break;
                        case 11:
                            jsonNodeDeserializeObject = nodeFactory.nullNode();
                            break;
                        case 12:
                            jsonNodeDeserializeObject = _fromEmbedded(jsonParser, deserializationContext, nodeFactory);
                            break;
                        default:
                            jsonNodeDeserializeObject = deserializeAny(jsonParser, deserializationContext, nodeFactory);
                            break;
                    }
                } else {
                    jsonNodeDeserializeObject = _fromInt(jsonParser, deserializationContext, nodeFactory);
                }
                JsonNode jsonNode2 = jsonNodeDeserializeObject;
                if (jsonNode != null) {
                    _handleDuplicateField(jsonParser, deserializationContext, nodeFactory, currentName, objectNode, jsonNode, jsonNode2);
                }
                objectNode.set(currentName, jsonNode2);
            } else if (jsonNode instanceof ObjectNode) {
                JsonNode jsonNodeUpdateObject = updateObject(jsonParser, deserializationContext, (ObjectNode) jsonNode);
                if (jsonNodeUpdateObject != jsonNode) {
                    objectNode.set(currentName, jsonNodeUpdateObject);
                }
            } else if (jsonNode instanceof ArrayNode) {
                JsonNode jsonNodeUpdateArray = updateArray(jsonParser, deserializationContext, (ArrayNode) jsonNode);
                if (jsonNodeUpdateArray != jsonNode) {
                    objectNode.set(currentName, jsonNodeUpdateArray);
                }
            }
            currentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }
}
