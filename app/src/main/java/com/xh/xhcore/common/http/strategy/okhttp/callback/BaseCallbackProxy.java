package com.xh.xhcore.common.http.strategy.okhttp.callback;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaseCallbackProxy implements Callback {
    private Callback callback;

    public BaseCallbackProxy(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onFailure(Call call, IOException iOException) {
        Callback callback = this.callback;
        if (callback != null) {
            callback.onFailure(call, iOException);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Callback callback = this.callback;
        if (callback != null) {
            callback.onResponse(call, response);
        }
    }
}
