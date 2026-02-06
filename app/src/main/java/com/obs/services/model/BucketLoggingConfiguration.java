package com.obs.services.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketLoggingConfiguration extends HeaderResponse {
    private String agency;
    private String logfilePrefix;
    private String targetBucketName;
    private final List<GrantAndPermission> targetGrantsList = new ArrayList();

    public BucketLoggingConfiguration() {
    }

    public BucketLoggingConfiguration(String str, String str2) {
        this.targetBucketName = str;
        this.logfilePrefix = str2;
    }

    public void addTargetGrant(GrantAndPermission grantAndPermission) {
        this.targetGrantsList.add(grantAndPermission);
    }

    public String getAgency() {
        return this.agency;
    }

    public String getLogfilePrefix() {
        return this.logfilePrefix;
    }

    public String getTargetBucketName() {
        return this.targetBucketName;
    }

    public GrantAndPermission[] getTargetGrants() {
        List<GrantAndPermission> list = this.targetGrantsList;
        return (GrantAndPermission[]) list.toArray(new GrantAndPermission[list.size()]);
    }

    public boolean isLoggingEnabled() {
        return (this.targetBucketName == null && this.logfilePrefix == null && this.targetGrantsList.size() <= 0) ? false : true;
    }

    public void setAgency(String str) {
        this.agency = str;
    }

    public void setLogfilePrefix(String str) {
        this.logfilePrefix = str;
    }

    public void setTargetBucketName(String str) {
        this.targetBucketName = str;
    }

    public void setTargetGrants(GrantAndPermission[] grantAndPermissionArr) {
        this.targetGrantsList.clear();
        this.targetGrantsList.addAll(Arrays.asList(grantAndPermissionArr));
    }

    @Override
    public String toString() {
        return "BucketLoggingConfiguration [targetBucketName=" + this.targetBucketName + ", logfilePrefix=" + this.logfilePrefix + ", agency=" + this.agency + ", targetGrantsList=" + this.targetGrantsList + "]";
    }
}
