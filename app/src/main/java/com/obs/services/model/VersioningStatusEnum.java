package com.obs.services.model;

public enum VersioningStatusEnum {
    SUSPENDED("Suspended"),
    ENABLED("Enabled");

    private String code;

    VersioningStatusEnum(String str) {
        this.code = str;
    }

    public static VersioningStatusEnum getValueFromCode(String str) {
        for (VersioningStatusEnum versioningStatusEnum : values()) {
            if (versioningStatusEnum.code.equals(str)) {
                return versioningStatusEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
