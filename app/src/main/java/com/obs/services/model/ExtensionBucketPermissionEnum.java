package com.obs.services.model;

public enum ExtensionBucketPermissionEnum {
    GRANT_READ("grantReadHeader"),
    GRANT_WRITE("grantWriteHeader"),
    GRANT_READ_ACP("grantReadAcpHeader"),
    GRANT_WRITE_ACP("grantWriteAcpHeader"),
    GRANT_FULL_CONTROL("grantFullControlHeader"),
    GRANT_READ_DELIVERED("grantReadDeliveredHeader"),
    GRANT_FULL_CONTROL_DELIVERED("grantFullControlDeliveredHeader");

    private String code;

    ExtensionBucketPermissionEnum(String str) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
