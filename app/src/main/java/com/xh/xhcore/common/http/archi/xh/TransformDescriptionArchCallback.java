package com.xh.xhcore.common.http.archi.xh;

import com.xh.xhcore.common.http.HttpReq;
import com.xh.xhcore.common.http.XHErrorCodeUtil;

public class TransformDescriptionArchCallback implements HttpReq.IRequestCallBack {
    HttpReq.IRequestCallBack callback;

    public TransformDescriptionArchCallback(HttpReq.IRequestCallBack iRequestCallBack) {
        this.callback = iRequestCallBack;
    }

    @Override
    public void onEvent(int i, int i2, String str, String str2) {
        HttpReq.IRequestCallBack iRequestCallBack = this.callback;
        if (iRequestCallBack != null) {
            iRequestCallBack.onEvent(i, i2, XHErrorCodeUtil.getErrorMsgInfo(i2, str), str2);
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
