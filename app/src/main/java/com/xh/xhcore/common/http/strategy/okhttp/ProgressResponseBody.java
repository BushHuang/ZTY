package com.xh.xhcore.common.http.strategy.okhttp;

import android.os.SystemClock;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
    private BaseForwardingSource baseForwardingSource;
    private BufferedSource bufferedSource;
    private final ProgressResponseListener progressResponseListener;
    private final ResponseBody responseBody;

    public class BaseForwardingSource extends ForwardingSource {
        private long consumeTime;
        long startTimeMillis;
        private long totalBytesRead;

        public BaseForwardingSource(Source source) {
            super(source);
            this.totalBytesRead = 0L;
            this.consumeTime = 0L;
            this.startTimeMillis = SystemClock.uptimeMillis();
        }

        @Override
        public long read(Buffer buffer, long j) throws IOException {
            long j2 = super.read(buffer, j);
            this.totalBytesRead += j2 != -1 ? j2 : 0L;
            long jUptimeMillis = SystemClock.uptimeMillis() - this.startTimeMillis;
            this.consumeTime = jUptimeMillis;
            long j3 = jUptimeMillis > 0 ? (this.totalBytesRead * 1000) / jUptimeMillis : 0L;
            if (ProgressResponseBody.this.responseBody.contentLength() > 0) {
                ProgressResponseBody.this.progressResponseListener.downloadProgress(this.totalBytesRead, ProgressResponseBody.this.responseBody.contentLength(), j2 == -1, j3);
            }
            if (j2 == -1) {
                LogUtils.d("BaseBuryDataManager", "totalBytesRead = " + this.totalBytesRead + " consumeTime = " + this.consumeTime);
            }
            return j2;
        }

        public String toMainMessage() {
            return "current: " + this.totalBytesRead + ", total: " + ProgressResponseBody.this.responseBody.contentLength() + ", consumeTime: " + this.consumeTime;
        }
    }

    public interface ProgressResponseListener {
        void downloadProgress(long j, long j2, boolean z, long j3);
    }

    public ProgressResponseBody(ResponseBody responseBody, ProgressResponseListener progressResponseListener) {
        this.responseBody = responseBody;
        this.progressResponseListener = progressResponseListener;
    }

    private Source source(Source source) {
        BaseForwardingSource baseForwardingSource = new BaseForwardingSource(source);
        this.baseForwardingSource = baseForwardingSource;
        return baseForwardingSource;
    }

    @Override
    public long contentLength() {
        return this.responseBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return this.responseBody.contentType();
    }

    public BaseForwardingSource getBaseForwardingSource() {
        return this.baseForwardingSource;
    }

    @Override
    public BufferedSource source() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.responseBody.source()));
        }
        return this.bufferedSource;
    }
}
