package com.samsung.android.knox.restriction;

public class RoamingPolicy {
    private android.app.enterprise.RoamingPolicy mRoamingPolicy;

    public RoamingPolicy(android.app.enterprise.RoamingPolicy roamingPolicy) {
        this.mRoamingPolicy = roamingPolicy;
    }

    public boolean isRoamingDataEnabled() {
        return this.mRoamingPolicy.isRoamingDataEnabled();
    }

    public boolean isRoamingPushEnabled() {
        return this.mRoamingPolicy.isRoamingPushEnabled();
    }

    public boolean isRoamingSyncEnabled() {
        return this.mRoamingPolicy.isRoamingSyncEnabled();
    }

    public boolean isRoamingVoiceCallsEnabled() {
        return this.mRoamingPolicy.isRoamingVoiceCallsEnabled();
    }

    public void setRoamingData(boolean z) {
        this.mRoamingPolicy.setRoamingData(z);
    }

    public void setRoamingPush(boolean z) {
        this.mRoamingPolicy.setRoamingPush(z);
    }

    public void setRoamingSync(boolean z) {
        this.mRoamingPolicy.setRoamingSync(z);
    }

    public void setRoamingVoiceCalls(boolean z) {
        this.mRoamingPolicy.setRoamingVoiceCalls(z);
    }
}
