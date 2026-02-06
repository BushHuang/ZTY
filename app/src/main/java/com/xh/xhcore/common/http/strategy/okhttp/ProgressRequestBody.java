package com.xh.xhcore.common.http.strategy.okhttp;

import android.os.SystemClock;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.ReflectUtil;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {
    private BaseForwardingSink baseForwardingSink;
    private boolean hasHookSink = false;
    private final ProgressRequestListener progressRequestListener;
    private final RequestBody requestBody;

    public class BaseForwardingSink extends ForwardingSink {
        private long consumeTime;
        private long current;
        private long startTimeMillis;
        private long total;

        public BaseForwardingSink(Sink sink) {
            super(sink);
            this.consumeTime = 0L;
            this.startTimeMillis = SystemClock.uptimeMillis();
        }

        public String toMainMessage() {
            return "current: " + this.current + ", total: " + this.total + ", consumeTime: " + this.consumeTime;
        }

        @Override
        public void write(Buffer buffer, long j) throws IOException {
            super.write(buffer, j);
            if (this.total == 0) {
                this.total = ProgressRequestBody.this.contentLength();
            }
            this.current += j;
            long jUptimeMillis = SystemClock.uptimeMillis() - this.startTimeMillis;
            this.consumeTime = jUptimeMillis;
            long j2 = jUptimeMillis > 0 ? (this.current * 1000) / jUptimeMillis : 0L;
            if (this.current == this.total) {
                LogUtils.d("BaseBuryDataManager", "totalBytesRead = " + this.current + " consumeTime = " + this.consumeTime);
            }
            ProgressRequestBody.this.progressRequestListener.uploadProgress(this.current, this.total, j2);
        }
    }

    public interface ProgressRequestListener {
        void uploadProgress(long j, long j2, long j3);
    }

    public ProgressRequestBody(RequestBody requestBody, ProgressRequestListener progressRequestListener) {
        this.requestBody = requestBody;
        this.progressRequestListener = progressRequestListener;
    }

    private Sink forwardingSink(Sink sink) {
        BaseForwardingSink baseForwardingSink = new BaseForwardingSink(sink);
        this.baseForwardingSink = baseForwardingSink;
        return baseForwardingSink;
    }

    private void hookCountingSinkInRealBufferdSink(BufferedSink bufferedSink) {
        try {
            ReflectUtil.setField(bufferedSink, "sink", forwardingSink((Sink) ReflectUtil.getField(bufferedSink, "sink")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long contentLength() throws IOException {
        return this.requestBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return this.requestBody.contentType();
    }

    public BaseForwardingSink getBaseForwardingSink() {
        return this.baseForwardingSink;
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if (!this.hasHookSink) {
            this.hasHookSink = true;
            hookCountingSinkInRealBufferdSink(bufferedSink);
        }
        this.requestBody.writeTo(bufferedSink);
    }
}
