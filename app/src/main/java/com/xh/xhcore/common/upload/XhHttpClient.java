package com.xh.xhcore.common.upload;

import com.xh.xhcore.common.http.XHRequestCallBack;

public class XhHttpClient {
    final int connectTimeout;
    final XhHeaders mHeaders;
    private IRequest mIRequest;
    final int mSdkType;
    final String url;

    public static final class Builder {
        int connectTimeout;
        XhHeaders mHeaders;
        int mSdkType = 0;
        String url;

        public XhHttpClient build() {
            return new XhHttpClient(this);
        }

        public Builder setConnectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        public Builder setHeaders(XhHeaders xhHeaders) {
            this.mHeaders = xhHeaders;
            return this;
        }

        public void setSdkType(int i) {
            this.mSdkType = i;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    public XhHttpClient() {
        this(new Builder());
    }

    public XhHttpClient(Builder builder) {
        this.connectTimeout = builder.connectTimeout;
        this.url = builder.url;
        this.mHeaders = builder.mHeaders;
        this.mSdkType = builder.mSdkType;
    }

    public XHTask execute() {
        return null;
    }

    public XHTask execute(XHRequestCallBack.HttpCallBack httpCallBack) {
        if (this.mSdkType == 0) {
            this.mIRequest = new XueHaiRequest(this);
        }
        return this.mIRequest.execute(httpCallBack);
    }
}
