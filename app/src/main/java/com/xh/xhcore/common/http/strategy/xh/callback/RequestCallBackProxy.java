package com.xh.xhcore.common.http.strategy.xh.callback;

import com.xh.xhcore.common.http.HttpReq;

public class RequestCallBackProxy implements HttpReq.IRequestCallBack {
    private HttpReq.IRequestCallBack callback;

    public RequestCallBackProxy(HttpReq.IRequestCallBack iRequestCallBack) {
        this.callback = iRequestCallBack;
    }

    @Override
    public void onEvent(int i, int i2, String str, String str2) {
        HttpReq.IRequestCallBack iRequestCallBack = this.callback;
        if (iRequestCallBack != null) {
            iRequestCallBack.onEvent(i, i2, str, str2);
        }
    }

    @Override
    public void onProgress(int i, double d, double d2, String str) {
        HttpReq.IRequestCallBack iRequestCallBack = this.callback;
        if (iRequestCallBack != null) {
            iRequestCallBack.onProgress(i, d, d2, str);
        }
    }

    @Override
    public void onRecvDate(int i, byte[] bArr, int i2, String str) {
        HttpReq.IRequestCallBack iRequestCallBack = this.callback;
        if (iRequestCallBack != null) {
            iRequestCallBack.onRecvDate(i, bArr, i2, str);
        }
    }
}
