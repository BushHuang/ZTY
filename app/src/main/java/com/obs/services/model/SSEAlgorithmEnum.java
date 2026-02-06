package com.obs.services.model;

public enum SSEAlgorithmEnum {
    KMS("kms"),
    AES256("AES256");

    private String code;

    SSEAlgorithmEnum(String str) {
        this.code = str;
    }

    public static SSEAlgorithmEnum getValueFromCode(String str) {
        for (SSEAlgorithmEnum sSEAlgorithmEnum : values()) {
            if (sSEAlgorithmEnum.code.equals(str)) {
                return sSEAlgorithmEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
