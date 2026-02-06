package com.xh.xhcore.common.upload;

import java.util.concurrent.ConcurrentMap;

public class XhHeaders {
    final String authorization;
    final String contentType;
    final ConcurrentMap<String, Object> customPropertyMap;
    final String referer;
    final String userAgent;
    final String userId;

    public static class Builder {
        String authorization;
        String contentType;
        ConcurrentMap<String, Object> customPropertyMap;
        String referer;
        String userAgent;
        String userId;

        public XhHeaders build() {
            return new XhHeaders(this);
        }

        public Builder setAuthorization(String str) {
            this.authorization = str;
            return this;
        }

        public Builder setContentType(String str) {
            this.contentType = str;
            return this;
        }

        public Builder setCustomPropertyMap(ConcurrentMap<String, Object> concurrentMap) {
            this.customPropertyMap = concurrentMap;
            return this;
        }

        public Builder setReferer(String str) {
            this.referer = str;
            return this;
        }

        public Builder setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        public Builder setUserId(String str) {
            this.userId = str;
            return this;
        }
    }

    public XhHeaders() {
        this(new Builder());
    }

    public XhHeaders(Builder builder) {
        this.contentType = builder.contentType;
        this.userAgent = builder.userAgent;
        this.authorization = builder.authorization;
        this.userId = builder.userId;
        this.referer = builder.referer;
        this.customPropertyMap = builder.customPropertyMap;
    }
}
