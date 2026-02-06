package com.obs.services.model;

public class V4PostSignatureResponse extends PostSignatureResponse {
    private String algorithm;
    private String credential;
    private String date;

    public V4PostSignatureResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.policy = str;
        this.originPolicy = str2;
        this.algorithm = str3;
        this.credential = str4;
        this.date = str5;
        this.signature = str6;
        this.expiration = str7;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getCredential() {
        return this.credential;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "V4PostSignatureResponse [algorithm=" + this.algorithm + ", credential=" + this.credential + ", date=" + this.date + ", expiration=" + this.expiration + ", policy=" + this.policy + ", originPolicy=" + this.originPolicy + ", signature=" + this.signature + "]";
    }
}
