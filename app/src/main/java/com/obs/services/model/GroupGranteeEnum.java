package com.obs.services.model;

public enum GroupGranteeEnum {
    ALL_USERS,
    AUTHENTICATED_USERS,
    LOG_DELIVERY;

    public static GroupGranteeEnum getValueFromCode(String str) {
        if ("Everyone".equals(str) || "http://acs.amazonaws.com/groups/global/AllUsers".equals(str)) {
            return ALL_USERS;
        }
        if ("http://acs.amazonaws.com/groups/global/AuthenticatedUsers".equals(str)) {
            return AUTHENTICATED_USERS;
        }
        if ("http://acs.amazonaws.com/groups/s3/LogDelivery".equals(str)) {
            return LOG_DELIVERY;
        }
        return null;
    }

    public String getCode() {
        return name();
    }
}
