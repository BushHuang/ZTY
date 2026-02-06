package com.obs.services.model;

public enum SpecialParamEnum {
    LOCATION("location"),
    STORAGEINFO("storageinfo"),
    QUOTA("quota"),
    ACL("acl"),
    LOGGING("logging"),
    POLICY("policy"),
    LIFECYCLE("lifecycle"),
    WEBSITE("website"),
    VERSIONING("versioning"),
    STORAGEPOLICY("storagePolicy"),
    STORAGECLASS("storageClass"),
    CORS("cors"),
    UPLOADS("uploads"),
    VERSIONS("versions"),
    DELETE("delete"),
    RESTORE("restore"),
    TAGGING("tagging"),
    NOTIFICATION("notification"),
    REPLICATION("replication"),
    APPEND("append"),
    RENAME("rename"),
    TRUNCATE("truncate"),
    MODIFY("modify"),
    FILEINTERFACE("fileinterface"),
    METADATA("metadata"),
    ENCRYPTION("encryption"),
    DIRECTCOLDACCESS("directcoldaccess");

    private String stringCode;

    SpecialParamEnum(String str) {
        if (str == null) {
            throw new IllegalArgumentException("stringCode is null");
        }
        this.stringCode = str;
    }

    public static SpecialParamEnum getValueFromStringCode(String str) {
        if (str == null) {
            throw new IllegalArgumentException("string code is null");
        }
        for (SpecialParamEnum specialParamEnum : values()) {
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
