package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class ListBucketsResult extends HeaderResponse {
    private List<ObsBucket> buckets;
    private Owner owner;

    public ListBucketsResult(List<ObsBucket> list, Owner owner) {
        this.buckets = list;
        this.owner = owner;
    }

    public List<ObsBucket> getBuckets() {
        if (this.buckets == null) {
            this.buckets = new ArrayList();
        }
        return this.buckets;
    }

    public Owner getOwner() {
        return this.owner;
    }

    @Override
    public String toString() {
        return "ListBucketsResult [buckets=" + this.buckets + ", owner=" + this.owner + "]";
    }
}
