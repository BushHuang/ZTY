package com.xuehai.platform.hotfix;

public class PatchInfo {
    public static final int ACTIVE = 0;
    public static final int ROLLBACK = 2;
    public static final int SUSPEND = 1;
    private String distributionId;
    private String packageName;
    private String patchId;
    private int status;
    private String strategy;
    private int version;

    public String getDistributionId() {
        return this.distributionId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPatchId() {
        return this.patchId;
    }

    public int getStatus() {
        return this.status;
    }

    public String getStrategy() {
        return this.strategy;
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isActive() {
        return this.status == 0;
    }

    public boolean isRollback() {
        return this.status == 2;
    }

    public boolean isSuspend() {
        return this.status == 1;
    }

    public void setDistributionId(String str) {
        this.distributionId = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setPatchId(String str) {
        this.patchId = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setStrategy(String str) {
        this.strategy = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String toString() {
        return "PatchInfo{packageName='" + this.packageName + "', version=" + this.version + ", distributionId='" + this.distributionId + "', patchId='" + this.patchId + "', strategy='" + this.strategy + "', status=" + this.status + '}';
    }
}
