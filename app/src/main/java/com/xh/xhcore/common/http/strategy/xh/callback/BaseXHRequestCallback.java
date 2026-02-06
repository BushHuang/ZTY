package com.xh.xhcore.common.http.strategy.xh.callback;

import com.xh.xhcore.common.http.HttpReq;
import com.xh.xhcore.common.http.strategy.LogUtils;

public class BaseXHRequestCallback implements HttpReq.IRequestCallBack {
    public static final String TAG = "BaseXHRequestCallback";
    private int code;
    private String description;
    private int id;
    private String responseJsonParam;

    protected boolean isLogResponseJsonParam() {
        return true;
    }

    @Override
    public void onEvent(int i, int i2, String str, String str2) {
        this.id = i;
        this.code = i2;
        this.description = str;
        this.responseJsonParam = str2;
        LogUtils.d("id = [" + i + "], code = [" + i2 + "], description = [" + str + "], jsonParam = [" + str2 + "]");
    }

    @Override
    public void onProgress(int i, double d, double d2, String str) {
        LogUtils.d("id = [" + i + "] total = [" + d + "] now = [" + d2 + "] jsonParam = [" + str + "]");
    }

    @Override
    public void onRecvDate(int i, byte[] bArr, int i2, String str) {
        LogUtils.d("id = [" + i + "] datalen = [" + i2 + "] jsonParam = [" + str + "]");
    }
}
