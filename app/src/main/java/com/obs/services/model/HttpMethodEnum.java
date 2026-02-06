package com.obs.services.model;

public enum HttpMethodEnum {
    GET("Get"),
    PUT("Put"),
    POST("Post"),
    DELETE("Delete"),
    HEAD("Head"),
    OPTIONS("Options");

    private String operationType;

    HttpMethodEnum(String str) {
        if (str == null) {
            throw new IllegalArgumentException("operation type code is null");
        }
        this.operationType = str;
    }

    public static HttpMethodEnum getValueFromStringCode(String str) {
        if (str == null) {
            throw new IllegalArgumentException("operation type is null");
        }
        for (HttpMethodEnum httpMethodEnum : values()) {
            if (httpMethodEnum.getOperationType().equals(str.toUpperCase())) {
                return httpMethodEnum;
            }
        }
        throw new IllegalArgumentException("operation type is illegal");
    }

    public String getOperationType() {
        return this.operationType.toUpperCase();
    }
}
