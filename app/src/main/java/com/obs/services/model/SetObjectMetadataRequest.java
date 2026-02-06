package com.obs.services.model;

import java.util.HashMap;
import java.util.Map;

public class SetObjectMetadataRequest {
    private String bucketName;
    private Map<String, String> metadata;
    private String objectKey;
    private boolean removeUnset;
    private ObjectRepleaceMetadata replaceMetadata = new ObjectRepleaceMetadata();
    private StorageClassEnum storageClass;
    private String versionId;
    private String webSiteRedirectLocation;

    public SetObjectMetadataRequest() {
    }

    public SetObjectMetadataRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public SetObjectMetadataRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.versionId = str3;
    }

    public void addAllUserMetadata(Map<String, String> map) {
        if (map != null) {
            getMetadata().putAll(map);
        }
    }

    public void addUserMetadata(String str, String str2) {
        getMetadata().put(str, str2);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getCacheControl() {
        return this.replaceMetadata.getCacheControl();
    }

    public String getContentDisposition() {
        return this.replaceMetadata.getContentDisposition();
    }

    public String getContentEncoding() {
        return this.replaceMetadata.getContentEncoding();
    }

    public String getContentLanguage() {
        return this.replaceMetadata.getContentLanguage();
    }

    public String getContentType() {
        return this.replaceMetadata.getContentType();
    }

    public String getExpires() {
        return this.replaceMetadata.getExpires();
    }

    public Map<String, String> getMetadata() {
        if (this.metadata == null) {
            this.metadata = new HashMap();
        }
        return this.metadata;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public StorageClassEnum getObjectStorageClass() {
        return this.storageClass;
    }

    public Object getUserMetadata(String str) {
        return getMetadata().get(str);
    }

    public String getVersionId() {
        return this.versionId;
    }

    public String getWebSiteRedirectLocation() {
        return this.webSiteRedirectLocation;
    }

    public boolean isRemoveUnset() {
        return this.removeUnset;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCacheControl(String str) {
        this.replaceMetadata.setCacheControl(str);
    }

    public void setContentDisposition(String str) {
        this.replaceMetadata.setContentDisposition(str);
    }

    public void setContentEncoding(String str) {
        this.replaceMetadata.setContentEncoding(str);
    }

    public void setContentLanguage(String str) {
        this.replaceMetadata.setContentLanguage(str);
    }

    public void setContentType(String str) {
        this.replaceMetadata.setContentType(str);
    }

    public void setExpires(String str) {
        this.replaceMetadata.setExpires(str);
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setObjectStorageClass(StorageClassEnum storageClassEnum) {
        this.storageClass = storageClassEnum;
    }

    public void setRemoveUnset(boolean z) {
        this.removeUnset = z;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public void setWebSiteRedirectLocation(String str) {
        this.webSiteRedirectLocation = str;
    }

    public String toString() {
        return "SetObjectMetadataRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", versionId=" + this.versionId + ", storageClass=" + this.storageClass + ", webSiteRedirectLocation=" + this.webSiteRedirectLocation + ", removeUnset=" + this.removeUnset + ", metadata=" + this.metadata + ", replaceMetadata=" + this.replaceMetadata + "]";
    }
}
