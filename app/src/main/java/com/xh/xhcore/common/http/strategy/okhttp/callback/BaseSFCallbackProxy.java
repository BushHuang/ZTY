package com.xh.xhcore.common.http.strategy.okhttp.callback;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public class BaseSFCallbackProxy extends BaseSFCallback {
    SFCallback realCallBack;

    public BaseSFCallbackProxy(SFCallback sFCallback) {
        this.realCallBack = sFCallback;
    }

    @Override
    public void onFailure(Call call, IOException iOException) {
        SFCallback sFCallback = this.realCallBack;
        if (sFCallback != null) {
            sFCallback.onFailure(call, iOException);
        }
    }

    @Override
    public void onResponseFailure(Call call, Response response) {
        SFCallback sFCallback = this.realCallBack;
        if (sFCallback != null) {
            sFCallback.onResponseFailure(call, response);
        }
    }

    @Override
    public void onResponseSuccess(Call call, Response response) {
        SFCallback sFCallback = this.realCallBack;
        if (sFCallback != null) {
            sFCallback.onResponseSuccess(call, response);
        }
    }
}
