package com.obs.services.model;

public enum CacheOptionEnum {
    PERFORMANCE("cache-with-performance");

    private String code;

    CacheOptionEnum(String str) {
        this.code = str;
    }

    public static CacheOptionEnum getValueFromCode(String str) {
        for (CacheOptionEnum cacheOptionEnum : values()) {
            if (cacheOptionEnum.code.equals(str)) {
                return cacheOptionEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
