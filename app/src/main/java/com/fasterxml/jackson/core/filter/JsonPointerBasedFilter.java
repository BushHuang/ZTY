package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilter extends TokenFilter {
    protected final JsonPointer _pathToMatch;

    public JsonPointerBasedFilter(JsonPointer jsonPointer) {
        this._pathToMatch = jsonPointer;
    }

    public JsonPointerBasedFilter(String str) {
        this(JsonPointer.compile(str));
    }

    @Override
    protected boolean _includeScalar() {
        return this._pathToMatch.matches();
    }

    @Override
    public TokenFilter filterStartArray() {
        return this;
    }

    @Override
    public TokenFilter filterStartObject() {
        return this;
    }

    @Override
    public TokenFilter includeElement(int i) {
        JsonPointer jsonPointerMatchElement = this._pathToMatch.matchElement(i);
        if (jsonPointerMatchElement == null) {
            return null;
        }
        return jsonPointerMatchElement.matches() ? TokenFilter.INCLUDE_ALL : new JsonPointerBasedFilter(jsonPointerMatchElement);
    }

    @Override
    public TokenFilter includeProperty(String str) {
        JsonPointer jsonPointerMatchProperty = this._pathToMatch.matchProperty(str);
        if (jsonPointerMatchProperty == null) {
            return null;
        }
        return jsonPointerMatchProperty.matches() ? TokenFilter.INCLUDE_ALL : new JsonPointerBasedFilter(jsonPointerMatchProperty);
    }

    @Override
    public String toString() {
        return "[JsonPointerFilter at: " + this._pathToMatch + "]";
    }
}
