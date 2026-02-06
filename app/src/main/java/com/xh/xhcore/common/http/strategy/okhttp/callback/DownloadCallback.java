package com.xh.xhcore.common.http.strategy.okhttp.callback;

import com.xh.xhcore.common.http.strategy.okhttp.download.BaseDownloadOkHttp;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public class DownloadCallback extends BaseCallback {
    private String localPath;

    public DownloadCallback(String str) {
        this.localPath = str;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        super.onResponse(call, response);
        if (isResponseSuccessful()) {
            saveDownloadFile(response, this.localPath);
        }
    }

    protected File saveDownloadFile(Response response, String str) throws IOException {
        return BaseDownloadOkHttp.saveDownloadFile(response, str);
    }
}
