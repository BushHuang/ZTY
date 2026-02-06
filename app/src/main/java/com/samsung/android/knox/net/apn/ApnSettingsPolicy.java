package com.samsung.android.knox.net.apn;

import java.util.List;

public class ApnSettingsPolicy {
    private android.app.enterprise.ApnSettingsPolicy mApnSettingsPolicy;

    public ApnSettingsPolicy(android.app.enterprise.ApnSettingsPolicy apnSettingsPolicy) {
        this.mApnSettingsPolicy = apnSettingsPolicy;
    }

    public long createApnSettings(ApnSettings apnSettings) {
        try {
            return this.mApnSettingsPolicy.createApnSettings(ApnSettings.convertToOld(apnSettings));
        } catch (NoSuchFieldError e) {
            throw new NoSuchFieldError(e.getMessage());
        }
    }

    public boolean deleteApn(long j) {
        return this.mApnSettingsPolicy.deleteApn(j);
    }

    public List<ApnSettings> getApnList() {
        return ApnSettings.convertToNewList(this.mApnSettingsPolicy.getApnList());
    }

    public ApnSettings getApnSettings(long j) {
        return ApnSettings.convertToNew(this.mApnSettingsPolicy.getApnSettings(j));
    }

    public ApnSettings getPreferredApnSettings() {
        return ApnSettings.convertToNew(this.mApnSettingsPolicy.getPreferredApnSettings());
    }

    public boolean setPreferredApn(long j) {
        return this.mApnSettingsPolicy.setPreferredApn(j);
    }

    public boolean updateApnSettings(ApnSettings apnSettings) {
        try {
            return this.mApnSettingsPolicy.updateApnSettings(ApnSettings.convertToOld(apnSettings));
        } catch (NoSuchFieldError e) {
            throw new NoSuchFieldError(e.getMessage());
        }
    }
}
