package com.obs.services.model;

public class ListBucketsRequest {
    private boolean queryLocation = true;

    public boolean isQueryLocation() {
        return this.queryLocation;
    }

    public void setQueryLocation(boolean z) {
        this.queryLocation = z;
    }

    public String toString() {
        return "ListBucketsRequest [queryLocation=" + this.queryLocation + "]";
    }
}
