package com.obs.services.model;

public enum ExtensionObjectPermissionEnum {
    GRANT_READ("grantReadHeader"),
    GRANT_READ_ACP("grantReadAcpHeader"),
    GRANT_WRITE_ACP("grantWriteAcpHeader"),
    GRANT_FULL_CONTROL("grantFullControlHeader");

    private String code;

    ExtensionObjectPermissionEnum(String str) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
