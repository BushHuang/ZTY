package com.xh.xhcore.common.upload;

import com.xh.xhcore.common.http.XHRequestCallBack;

public class XueHaiRequest implements IRequest {
    XhHttpClient mClient;

    public XueHaiRequest(XhHttpClient xhHttpClient) {
        this.mClient = xhHttpClient;
    }

    @Override
    public XHTask execute() {
        return null;
    }

    @Override
    public XHTask execute(XHRequestCallBack.HttpCallBack httpCallBack) {
        return null;
    }
}
