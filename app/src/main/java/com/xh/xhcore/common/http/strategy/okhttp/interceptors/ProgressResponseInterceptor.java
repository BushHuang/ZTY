package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.strategy.okhttp.ProgressResponseBody;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ProgressResponseInterceptor implements Interceptor {
    ProgressResponseBody.ProgressResponseListener mProgressResponseListener;

    public ProgressResponseInterceptor(ProgressResponseBody.ProgressResponseListener progressResponseListener) {
        this.mProgressResponseListener = progressResponseListener;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response responseProceed = chain.proceed(chain.request());
        return responseProceed.newBuilder().body(new ProgressResponseBody(responseProceed.body(), this.mProgressResponseListener)).build();
    }
}
