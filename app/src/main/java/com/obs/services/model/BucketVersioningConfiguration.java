package com.obs.services.model;

public class BucketVersioningConfiguration extends HeaderResponse {

    @Deprecated
    public static final String ENABLED = "Enabled";

    @Deprecated
    public static final String SUSPENDED = "Suspended";
    private VersioningStatusEnum status;

    public BucketVersioningConfiguration() {
    }

    public BucketVersioningConfiguration(VersioningStatusEnum versioningStatusEnum) {
        this.status = versioningStatusEnum;
    }

    @Deprecated
    public BucketVersioningConfiguration(String str) {
        this.status = VersioningStatusEnum.getValueFromCode(str);
    }

    @Deprecated
    public String getStatus() {
        VersioningStatusEnum versioningStatusEnum = this.status;
        if (versioningStatusEnum != null) {
            return versioningStatusEnum.getCode();
        }
        return null;
    }

    public VersioningStatusEnum getVersioningStatus() {
        return this.status;
    }

    @Deprecated
    public void setStatus(String str) {
        this.status = VersioningStatusEnum.getValueFromCode(str);
    }

    public void setVersioningStatus(VersioningStatusEnum versioningStatusEnum) {
        this.status = versioningStatusEnum;
    }

    @Override
    public String toString() {
        return "BucketVersioningConfiguration [status=" + this.status + "]";
    }
}
