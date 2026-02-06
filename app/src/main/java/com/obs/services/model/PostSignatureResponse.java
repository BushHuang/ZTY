package com.obs.services.model;

public class PostSignatureResponse {
    protected String expiration;
    protected String originPolicy;
    protected String policy;
    protected String signature;
    protected String token;

    public PostSignatureResponse() {
    }

    public PostSignatureResponse(String str, String str2, String str3, String str4, String str5) {
        this.policy = str;
        this.originPolicy = str2;
        this.signature = str3;
        this.expiration = str4;
        this.token = str5 + ":" + str3 + ":" + str;
    }

    public String getExpiration() {
        return this.expiration;
    }

    public String getOriginPolicy() {
        return this.originPolicy;
    }

    public String getPolicy() {
        return this.policy;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getToken() {
        return this.token;
    }

    public String toString() {
        return "PostSignatureResponse [policy=" + this.policy + ", originPolicy=" + this.originPolicy + ", signature=" + this.signature + ", expiration=" + this.expiration + ", token=" + this.token + "]";
    }
}
