package com.obs.services.model;

public enum ProtocolEnum {
    HTTP("http"),
    HTTPS("https");

    private String code;

    ProtocolEnum(String str) {
        this.code = str;
    }

    public static ProtocolEnum getValueFromCode(String str) {
        for (ProtocolEnum protocolEnum : values()) {
            if (protocolEnum.code.equals(str)) {
                return protocolEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
