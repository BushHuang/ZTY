package com.obs.services.model;

public enum RestoreTierEnum {
    EXPEDITED("Expedited"),
    STANDARD("Standard"),
    BULK("Bulk");

    private String code;

    RestoreTierEnum(String str) {
        this.code = str;
    }

    public static RestoreTierEnum getValueFromCode(String str) {
        for (RestoreTierEnum restoreTierEnum : values()) {
            if (restoreTierEnum.code.equals(str)) {
                return restoreTierEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
