package com.oef.services.model;

import com.obs.services.model.SpecialParamEnum;

public enum RequestParamEnum {
    EXTENSION_POLICY("v1/extension_policy"),
    ASYNC_FETCH_JOBS("v1/async-fetch/jobs"),
    DIS_POLICIES("v1/dis_policies");

    private String stringCode;

    RequestParamEnum(String str) {
        if (str == null) {
            throw new IllegalArgumentException("stringCode is null");
        }
        this.stringCode = str;
    }

    public static SpecialParamEnum getValueFromStringCode(String str) {
        if (str == null) {
            throw new IllegalArgumentException("string code is null");
        }
        for (SpecialParamEnum specialParamEnum : SpecialParamEnum.values()) {
            if (specialParamEnum.getStringCode().equals(str.toLowerCase())) {
                return specialParamEnum;
            }
        }
        throw new IllegalArgumentException("string code is illegal");
    }

    public String getOriginalStringCode() {
        return this.stringCode;
    }

    public String getStringCode() {
        return this.stringCode.toLowerCase();
    }
}
