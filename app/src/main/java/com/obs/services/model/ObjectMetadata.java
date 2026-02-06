package com.obs.services.model;

import java.util.Date;
import java.util.Map;

public class ObjectMetadata extends HeaderResponse {
    private boolean appendable;
    private String contentEncoding;
    private Long contentLength;
    private String contentMd5;
    private String contentType;
    private String etag;
    private Date lastModified;
    private long nextPosition = -1;
    private StorageClassEnum storageClass;
    private String webSiteRedirectLocation;

    private boolean isMatching(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.toLowerCase().equals(str2.toLowerCase());
    }

    public void addUserMetadata(String str, String str2) {
        getMetadata().put(str, str2);
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public Long getContentLength() {
        return this.contentLength;
    }

    public String getContentMd5() {
        return this.contentMd5;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getEtag() {
        return this.etag;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public Map<String, Object> getMetadata() {
        return getResponseHeaders();
    }

    public long getNextPosition() {
        return this.nextPosition;
    }

    public StorageClassEnum getObjectStorageClass() {
        return this.storageClass;
    }

    @Deprecated
    public String getStorageClass() {
        StorageClassEnum storageClassEnum = this.storageClass;
        if (storageClassEnum != null) {
            return storageClassEnum.getCode();
        }
        return null;
    }

    public Object getUserMetadata(String str) {
        return getMetadata().get(str);
    }

    public Object getValue(String str) {
        for (Map.Entry<String, Object> entry : getMetadata().entrySet()) {
            if (isMatching(entry.getKey(), str)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String getWebSiteRedirectLocation() {
        return this.webSiteRedirectLocation;
    }

    public boolean isAppendable() {
        return this.appendable;
    }

    public void setAppendable(boolean z) {
        this.appendable = z;
    }

    public void setContentEncoding(String str) {
        this.contentEncoding = str;
    }

    public void setContentLength(Long l) {
        this.contentLength = l;
    }

    public void setContentMd5(String str) {
        this.contentMd5 = str;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public void setLastModified(Date date) {
        this.lastModified = date;
    }

    public void setMetadata(Map<String, Object> map) {
        this.responseHeaders = map;
    }

    public void setNextPosition(long j) {
        this.nextPosition = j;
    }

    public void setObjectStorageClass(StorageClassEnum storageClassEnum) {
        this.storageClass = storageClassEnum;
    }

    @Deprecated
    public void setStorageClass(String str) {
        this.storageClass = StorageClassEnum.getValueFromCode(str);
    }

    public void setWebSiteRedirectLocation(String str) {
        this.webSiteRedirectLocation = str;
    }

    @Override
    public String toString() {
        return "ObjectMetadata [metadata=" + getMetadata() + ", lastModified=" + this.lastModified + ", contentLength=" + this.contentLength + ", contentType=" + this.contentType + ", contentEncoding=" + this.contentEncoding + ", etag=" + this.etag + ", contentMd5=" + this.contentMd5 + ", storageClass=" + this.storageClass + ", webSiteRedirectLocation=" + this.webSiteRedirectLocation + ", nextPosition=" + this.nextPosition + ", appendable=" + this.appendable + "]";
    }
}
