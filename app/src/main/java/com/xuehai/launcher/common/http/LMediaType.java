package com.xuehai.launcher.common.http;

public class LMediaType {
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_URLENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String CONTENT_TYPE = "Content-Type";
    private String mediaType = "application/json";

    public String getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(String str) {
        this.mediaType = str;
    }
}
