package com.obs.services.model;

public class BucketDirectColdAccess extends HeaderResponse {
    private RuleStatusEnum status;

    public BucketDirectColdAccess() {
    }

    public BucketDirectColdAccess(RuleStatusEnum ruleStatusEnum) {
        this.status = ruleStatusEnum;
    }

    public RuleStatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(RuleStatusEnum ruleStatusEnum) {
        this.status = ruleStatusEnum;
    }

    @Override
    public String toString() {
        return "BucketDirectColdAccess [Status=" + this.status.getCode() + "]";
    }
}
