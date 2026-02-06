package com.obs.services.internal.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class MyObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 4563671462132723274L;

    public MyObjectMapper() {
        configure(MapperFeature.AUTO_DETECT_FIELDS, true);
        configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
        configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(SerializationFeature.INDENT_OUTPUT, false);
    }
}
