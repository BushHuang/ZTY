package com.obs.services.model;

public enum AvailableZoneEnum {
    MULTI_AZ("3az");

    private String code;

    AvailableZoneEnum(String str) {
        this.code = str;
    }

    public static AvailableZoneEnum getValueFromCode(String str) {
        for (AvailableZoneEnum availableZoneEnum : values()) {
            if (availableZoneEnum.code.equals(str)) {
                return availableZoneEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
