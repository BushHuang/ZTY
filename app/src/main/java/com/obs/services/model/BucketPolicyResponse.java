package com.obs.services.model;

public class BucketPolicyResponse extends HeaderResponse {
    private String policy;

    public BucketPolicyResponse(String str) {
        this.policy = str;
    }

    public String getPolicy() {
        return this.policy;
    }

    @Override
    public String toString() {
        return "BucketPolicyResponse [policy=" + this.policy + "]";
    }
}
