package com.oef.services.model;

import com.obs.services.model.HeaderResponse;

public class GetDisPolicyResult extends HeaderResponse {
    private DisPolicy policy;

    public GetDisPolicyResult() {
    }

    public GetDisPolicyResult(DisPolicy disPolicy) {
        setPolicy(disPolicy);
    }

    public DisPolicy getPolicy() {
        return this.policy;
    }

    public void setPolicy(DisPolicy disPolicy) {
        this.policy = disPolicy;
    }
}
