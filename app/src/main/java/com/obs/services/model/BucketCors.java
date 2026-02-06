package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class BucketCors extends S3BucketCors {
    private List<BucketCorsRule> rules;

    public BucketCors() {
    }

    public BucketCors(List<BucketCorsRule> list) {
        this.rules = list;
    }

    @Override
    public List<BucketCorsRule> getRules() {
        if (this.rules == null) {
            this.rules = new ArrayList();
        }
        return this.rules;
    }

    @Override
    public void setRules(List<BucketCorsRule> list) {
        this.rules = list;
    }

    @Override
    public String toString() {
        return "ObsBucketCors [rules=" + this.rules + "]";
    }
}
