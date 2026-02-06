package com.xh.xhcore.common.http.strategy.okhttp.callback;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public abstract class BaseSFCallback extends BaseCallback implements SFCallback {
    @Override
    public void onFailure(Call call, IOException iOException) {
    }

    @Override
    public final void onResponse(Call call, Response response) {
        LogUtils.d("response code = " + response.code());
        if (response.isSuccessful()) {
            onResponseSuccess(call, response);
        } else {
            onResponseFailure(call, response);
        }
    }
}
