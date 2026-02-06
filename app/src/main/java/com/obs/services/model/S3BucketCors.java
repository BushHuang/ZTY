package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class S3BucketCors extends HeaderResponse {
    private List<BucketCorsRule> rules;

    public S3BucketCors() {
    }

    public S3BucketCors(List<BucketCorsRule> list) {
        this.rules = list;
    }

    public List<BucketCorsRule> getRules() {
        if (this.rules == null) {
            this.rules = new ArrayList();
        }
        return this.rules;
    }

    public void setRules(List<BucketCorsRule> list) {
        this.rules = list;
    }

    @Override
    public String toString() {
        return "ObsBucketCors [rules=" + this.rules + "]";
    }
}
