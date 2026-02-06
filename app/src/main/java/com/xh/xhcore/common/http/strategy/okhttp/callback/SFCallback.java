package com.xh.xhcore.common.http.strategy.okhttp.callback;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public interface SFCallback extends Callback {
    @Override
    void onFailure(Call call, IOException iOException);

    void onResponseFailure(Call call, Response response);

    void onResponseSuccess(Call call, Response response);
}
