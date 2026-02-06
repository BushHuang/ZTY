package com.obs.services.internal.utils;

class ObjectMapperUtil {

    private static class ObjectMapperUtilInstance {
        private static final MyObjectMapper mapper = new MyObjectMapper();

        private ObjectMapperUtilInstance() {
        }
    }

    private ObjectMapperUtil() {
    }

    public static MyObjectMapper getInstance() {
        return ObjectMapperUtilInstance.mapper;
    }
}
