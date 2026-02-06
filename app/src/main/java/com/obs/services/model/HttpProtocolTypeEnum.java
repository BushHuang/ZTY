package com.obs.services.model;

public enum HttpProtocolTypeEnum {
    HTTP1_1("http1.1"),
    HTTP2_0("http2.0");

    private String code;

    HttpProtocolTypeEnum(String str) {
        this.code = str;
    }

    public static HttpProtocolTypeEnum getValueFromCode(String str) {
        for (HttpProtocolTypeEnum httpProtocolTypeEnum : values()) {
            if (httpProtocolTypeEnum.code.equals(str)) {
                return httpProtocolTypeEnum;
            }
        }
        return HTTP1_1;
    }

    public String getCode() {
        return this.code;
    }
}
