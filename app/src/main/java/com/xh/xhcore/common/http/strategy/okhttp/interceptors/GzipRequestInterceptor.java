package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

public class GzipRequestInterceptor implements Interceptor {
    private RequestBody gzip(final RequestBody requestBody) {
        return new RequestBody() {
            @Override
            public long contentLength() {
                return -1L;
            }

            @Override
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                BufferedSink bufferedSinkBuffer = Okio.buffer(new GzipSink(bufferedSink));
                requestBody.writeTo(bufferedSinkBuffer);
                bufferedSinkBuffer.close();
            }
        };
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        return (request.body() == null || request.header("Content-Encoding") != null) ? chain.proceed(request) : chain.proceed(request.newBuilder().header("Content-Encoding", "gzip").header("Transfer-Encoding", "chunked").method(request.method(), gzip(request.body())).build());
    }
}
