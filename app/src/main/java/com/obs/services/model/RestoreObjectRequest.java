package com.obs.services.model;

public class RestoreObjectRequest {

    @Deprecated
    public static final String BULK = "Bulk";

    @Deprecated
    public static final String EXPEDITED = "Expedited";

    @Deprecated
    public static final String STANDARD = "Standard";
    private String bucketName;
    private int days;
    private String objectKey;
    private RestoreTierEnum tier;
    private String versionId;

    public static class RestoreObjectStatus extends HeaderResponse {
        public static final RestoreObjectStatus AVALIABLE = new RestoreObjectStatus(200);
        public static final RestoreObjectStatus INPROGRESS = new RestoreObjectStatus(202);
        private int code;

        private RestoreObjectStatus(int i) {
            this.code = i;
        }

        public static RestoreObjectStatus valueOf(int i) {
            return i == 200 ? AVALIABLE : i == 202 ? INPROGRESS : new RestoreObjectStatus(i);
        }

        public int getCode() {
            return this.code;
        }
    }

    public RestoreObjectRequest() {
    }

    public RestoreObjectRequest(String str, String str2, int i) {
        this.bucketName = str;
        this.objectKey = str2;
        this.days = i;
    }

    public RestoreObjectRequest(String str, String str2, String str3, int i) {
        this(str, str2, i);
        this.versionId = str3;
    }

    public RestoreObjectRequest(String str, String str2, String str3, int i, RestoreTierEnum restoreTierEnum) {
        this(str, str2, str3, i);
        this.tier = restoreTierEnum;
    }

    @Deprecated
    public RestoreObjectRequest(String str, String str2, String str3, int i, String str4) {
        this(str, str2, str3, i);
        this.tier = RestoreTierEnum.getValueFromCode(str4);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public int getDays() {
        return this.days;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public RestoreTierEnum getRestoreTier() {
        return this.tier;
    }

    @Deprecated
    public String getTier() {
        RestoreTierEnum restoreTierEnum = this.tier;
        if (restoreTierEnum != null) {
            return restoreTierEnum.getCode();
        }
        return null;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setDays(int i) {
        this.days = i;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setRestoreTier(RestoreTierEnum restoreTierEnum) {
        this.tier = restoreTierEnum;
    }

    @Deprecated
    public void setTier(String str) {
        this.tier = RestoreTierEnum.getValueFromCode(str);
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        return "RestoreObjectRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", versionId=" + this.versionId + ", days=" + this.days + ", tier=" + this.tier + "]";
    }
}
