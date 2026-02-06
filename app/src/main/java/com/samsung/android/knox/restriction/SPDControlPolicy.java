package com.samsung.android.knox.restriction;

import com.samsung.android.knox.SupportLibUtils;

public class SPDControlPolicy {
    private com.sec.enterprise.spd.SPDControlPolicy mSpdControlPolicy;

    public SPDControlPolicy(com.sec.enterprise.spd.SPDControlPolicy sPDControlPolicy) {
        this.mSpdControlPolicy = sPDControlPolicy;
    }

    public int getAutoSecurityPolicyUpdateMode() {
        try {
            return this.mSpdControlPolicy.getAutoSecurityPolicyUpdateMode();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SPDControlPolicy.class, "getAutoSecurityPolicyUpdateMode", null, 19));
        }
    }

    public boolean setAutoSecurityPolicyUpdateMode(int i) {
        try {
            return this.mSpdControlPolicy.setAutoSecurityPolicyUpdateMode(i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SPDControlPolicy.class, "setAutoSecurityPolicyUpdateMode", new Class[]{Integer.TYPE}, 19));
        }
    }
}
