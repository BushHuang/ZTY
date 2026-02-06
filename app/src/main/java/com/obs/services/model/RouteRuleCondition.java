package com.obs.services.model;

public class RouteRuleCondition {
    private String httpErrorCodeReturnedEquals;
    private String keyPrefixEquals;

    public String getHttpErrorCodeReturnedEquals() {
        return this.httpErrorCodeReturnedEquals;
    }

    public String getKeyPrefixEquals() {
        return this.keyPrefixEquals;
    }

    public void setHttpErrorCodeReturnedEquals(String str) {
        this.httpErrorCodeReturnedEquals = str;
    }

    public void setKeyPrefixEquals(String str) {
        this.keyPrefixEquals = str;
    }

    public String toString() {
        return "RouteRuleCondition [keyPrefixEquals=" + this.keyPrefixEquals + ", httpErrorCodeReturnedEquals=" + this.httpErrorCodeReturnedEquals + "]";
    }
}
