package com.xh.xhcore.common.http.strategy.okhttp.callback;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class BaseCallback implements Callback {
    private Call call;
    private IOException ioException;
    private Request request;
    private Response response;

    private void initRequestAfterInitCall() {
        Call call = this.call;
        if (call != null) {
            this.request = call.request();
        }
    }

    protected void cancelCall() {
        Call call = this.call;
        if (call != null) {
            call.cancel();
        }
    }

    public Call getCall() {
        return this.call;
    }

    public IOException getIoException() {
        return this.ioException;
    }

    public Request getRequest() {
        return this.request;
    }

    public String getRequestUrl() {
        Request request = this.request;
        return request != null ? request.url().toString() : "";
    }

    public Response getResponse() {
        return this.response;
    }

    public Integer getResponseStatusCode() {
        Response response = this.response;
        if (response != null) {
            return Integer.valueOf(response.code());
        }
        return null;
    }

    public boolean isCanceled() {
        Call call = this.call;
        if (call != null) {
            return call.isCanceled();
        }
        return false;
    }

    public boolean isConnected() {
        return this.response != null;
    }

    @Deprecated
    public boolean isResponseOk() {
        Response response = this.response;
        return response != null && response.code() == 200;
    }

    @Deprecated
    public boolean isResponseOk(int i) {
        return i == 200;
    }

    public boolean isResponseSuccessful() {
        Response response = this.response;
        if (response != null) {
            return response.isSuccessful();
        }
        return false;
    }

    public boolean isResponseSuccessful(int i) {
        return i >= 200 && i < 300;
    }

    @Override
    public void onFailure(Call call, IOException iOException) {
        this.call = call;
        initRequestAfterInitCall();
        if (this.request != null) {
            LogUtils.e("IOException request url = " + this.request.url() + " method = " + this.request.method());
        }
        LogUtils.i("[onFailure] e ", iOException != null ? iOException.getClass().getSimpleName() : "null");
        this.ioException = iOException;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        this.call = call;
        initRequestAfterInitCall();
        this.response = response;
    }
}
