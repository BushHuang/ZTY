package com.obs.services.model;

public enum StorageClassEnum {
    STANDARD,
    WARM,
    COLD;

    public static StorageClassEnum getValueFromCode(String str) {
        if ("STANDARD".equals(str)) {
            return STANDARD;
        }
        if ("WARM".equals(str) || "STANDARD_IA".equals(str)) {
            return WARM;
        }
        if ("COLD".equals(str) || "GLACIER".equals(str)) {
            return COLD;
        }
        return null;
    }

    public String getCode() {
        return name();
    }
}
