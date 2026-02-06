package com.obs.services.model.fs;

public enum FSStatusEnum {
    ENABLED("Enabled"),
    DISABLED("Disabled");

    private String code;

    FSStatusEnum(String str) {
        this.code = str;
    }

    public static FSStatusEnum getValueFromCode(String str) {
        for (FSStatusEnum fSStatusEnum : values()) {
            if (fSStatusEnum.code.equals(str)) {
                return fSStatusEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
