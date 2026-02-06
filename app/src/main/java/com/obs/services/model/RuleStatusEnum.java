package com.obs.services.model;

public enum RuleStatusEnum {
    ENABLED("Enabled"),
    DISABLED("Disabled");

    private String code;

    RuleStatusEnum(String str) {
        this.code = str;
    }

    public static RuleStatusEnum getValueFromCode(String str) {
        for (RuleStatusEnum ruleStatusEnum : values()) {
            if (ruleStatusEnum.code.equals(str)) {
                return ruleStatusEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
