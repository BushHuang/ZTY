package com.obs.services.model;

public class ObjectRepleaceMetadata {
    private String cacheControl;
    private String contentDisposition;
    private String contentEncoding;
    private String contentLanguage;
    private String contentType;
    private String expires;

    public String getCacheControl() {
        return this.cacheControl;
    }

    public String getContentDisposition() {
        return this.contentDisposition;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public String getContentLanguage() {
        return this.contentLanguage;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getExpires() {
        return this.expires;
    }

    public void setCacheControl(String str) {
        this.cacheControl = str;
    }

    public void setContentDisposition(String str) {
        this.contentDisposition = str;
    }

    public void setContentEncoding(String str) {
        this.contentEncoding = str;
    }

    public void setContentLanguage(String str) {
        this.contentLanguage = str;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setExpires(String str) {
        this.expires = str;
    }

    public String toString() {
        return "ObjectRepleaceMetadata [contentType=" + this.contentType + ", contentLanguage=" + this.contentLanguage + ", expires=" + this.expires + ", cacheControl=" + this.cacheControl + ", contentDisposition=" + this.contentDisposition + ", contentEncoding=" + this.contentEncoding + "]";
    }
}
