package com.obs.services.internal.utils;

public class DefaultAuthentication implements IAuthentication {
    private String authorization;
    private String canonicalRequest;
    private String stringToSign;

    public DefaultAuthentication(String str, String str2, String str3) {
        this.canonicalRequest = str;
        this.stringToSign = str2;
        this.authorization = str3;
    }

    @Override
    public String getAuthorization() {
        return this.authorization;
    }

    @Override
    public String getCanonicalRequest() {
        return this.canonicalRequest;
    }

    @Override
    public String getStringToSign() {
        return this.stringToSign;
    }
}
