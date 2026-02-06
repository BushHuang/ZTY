package com.obs.services.model;

public class BucketLocationResponse extends HeaderResponse {
    private String location;

    public BucketLocationResponse(String str) {
        this.location = str;
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return "BucketLocationResponse [location=" + this.location + "]";
    }
}
