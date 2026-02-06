package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;

public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1;

    public StackTraceElementDeserializer() {
        super((Class<?>) StackTraceElement.class);
    }

    @Deprecated
    protected StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i, String str4, String str5) {
        return constructValue(deserializationContext, str, str2, str3, i, str4, str5, null);
    }

    protected StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i, String str4, String str5, String str6) {
        return new StackTraceElement(str, str2, str3, i);
    }

    @Override
    public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT) {
            if (currentToken != JsonToken.START_ARRAY || !deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return (StackTraceElement) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
            jsonParser.nextToken();
            StackTraceElement stackTraceElementDeserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(jsonParser, deserializationContext);
            }
            return stackTraceElementDeserialize;
        }
        String text = null;
        String text2 = null;
        String text3 = null;
        String text4 = "";
        String text5 = text4;
        String text6 = text5;
        int intValue = -1;
        while (true) {
            JsonToken jsonTokenNextValue = jsonParser.nextValue();
            if (jsonTokenNextValue == JsonToken.END_OBJECT) {
                return constructValue(deserializationContext, text4, text5, text6, intValue, text, text2, text3);
            }
            String currentName = jsonParser.getCurrentName();
            if ("className".equals(currentName)) {
                text4 = jsonParser.getText();
            } else if ("classLoaderName".equals(currentName)) {
                text3 = jsonParser.getText();
            } else if ("fileName".equals(currentName)) {
                text6 = jsonParser.getText();
            } else if ("lineNumber".equals(currentName)) {
                intValue = jsonTokenNextValue.isNumeric() ? jsonParser.getIntValue() : _parseIntPrimitive(jsonParser, deserializationContext);
            } else if ("methodName".equals(currentName)) {
                text5 = jsonParser.getText();
            } else if (!"nativeMethod".equals(currentName)) {
                if ("moduleName".equals(currentName)) {
                    text = jsonParser.getText();
                } else if ("moduleVersion".equals(currentName)) {
                    text2 = jsonParser.getText();
                } else if (!"declaringClass".equals(currentName) && !"format".equals(currentName)) {
                    handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                }
            }
            jsonParser.skipChildren();
        }
    }
}
