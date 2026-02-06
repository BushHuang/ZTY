package com.xh.xhcore.common.http.strategy.okhttp;

import android.text.TextUtils;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.connect.time.ConnectTimeConsumingManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class HttpEventListener extends EventListener {
    public static final DownloadUploadFactory DOWNLOAD_UPLOAD_FACTORY;
    public static final EventListener.Factory FACTORY;
    private final long callId;
    private final long callStartNanos;
    private long connectionAcquiredTime;
    private long connectionDuration;
    private long dnsDuration;
    private long dnsStartTime;
    private boolean enableNetworkBury;
    private final Map<String, HttpEventItem> httpEventMap;
    private final HttpUrl httpUrl;

    private static class DownloadUploadFactory extends RequestFactory {
        private DownloadUploadFactory() {
            super();
        }

        @Override
        protected EventListener create(long j, HttpUrl httpUrl, long j2) {
            return new HttpEventListener(j, httpUrl, j2, false);
        }
    }

    private static class HttpEventItem {
        String first;
        String last;
        int times;

        public HttpEventItem(String str, String str2, int i) {
            this.first = str;
            this.last = str2;
            this.times = i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{f='" + this.first + '\'');
            if (!TextUtils.isEmpty(this.last)) {
                sb.append(", l='");
                sb.append(this.last);
                sb.append('\'');
            }
            if (this.times > 1) {
                sb.append(", t=");
                sb.append(this.times);
            }
            sb.append('}');
            return sb.toString();
        }
    }

    private static class RequestFactory implements EventListener.Factory {
        protected final AtomicLong nextCallId;

        private RequestFactory() {
            this.nextCallId = new AtomicLong(1L);
        }

        protected EventListener create(long j, HttpUrl httpUrl, long j2) {
            return new HttpEventListener(j, httpUrl, j2);
        }

        @Override
        public final EventListener create(Call call) {
            return create(this.nextCallId.getAndIncrement(), call.request().url(), System.nanoTime());
        }
    }

    static {
        FACTORY = new RequestFactory();
        DOWNLOAD_UPLOAD_FACTORY = new DownloadUploadFactory();
    }

    public HttpEventListener(long j, HttpUrl httpUrl, long j2) {
        this.httpEventMap = Collections.synchronizedMap(new LinkedHashMap());
        this.connectionDuration = -1L;
        this.dnsDuration = -1L;
        this.enableNetworkBury = true;
        this.callId = j;
        this.httpUrl = httpUrl;
        this.callStartNanos = j2;
    }

    public HttpEventListener(long j, HttpUrl httpUrl, long j2, boolean z) {
        this(j, httpUrl, j2);
        this.enableNetworkBury = z;
    }

    private void recordEventLog(String str) {
        HttpEventItem httpEventItem;
        long jNanoTime = System.nanoTime() - this.callStartNanos;
        Locale locale = Locale.CHINA;
        double d = jNanoTime;
        Double.isNaN(d);
        String str2 = String.format(locale, "%.3f", Double.valueOf(d / 1.0E9d));
        HttpEventItem httpEventItem2 = this.httpEventMap.get(str);
        if (httpEventItem2 == null) {
            httpEventItem = new HttpEventItem(str2, "", 1);
        } else {
            String str3 = httpEventItem2.first;
            int i = httpEventItem2.times + 1;
            httpEventItem2.times = i;
            httpEventItem = new HttpEventItem(str3, str2, i);
        }
        this.httpEventMap.put(str, httpEventItem);
        if (str.equalsIgnoreCase("callEnd") || str.equalsIgnoreCase("callFailed")) {
            LogUtils.i(getHttpEventMessage());
        }
    }

    @Override
    public void callEnd(Call call) {
        super.callEnd(call);
        recordEventLog("callEnd");
    }

    @Override
    public void callFailed(Call call, IOException iOException) {
        super.callFailed(call, iOException);
        recordEventLog("callFailed");
    }

    @Override
    public void callStart(Call call) {
        super.callStart(call);
        recordEventLog("callStart");
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        recordEventLog("connectEnd");
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException iOException) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, iOException);
        recordEventLog("connectFailed");
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        recordEventLog("connectStart");
    }

    @Override
    public void connectionAcquired(Call call, Connection connection) {
        this.connectionAcquiredTime = System.nanoTime();
        recordEventLog("connectionAcquired");
    }

    @Override
    public void connectionReleased(Call call, Connection connection) {
        this.connectionDuration = (System.nanoTime() - this.connectionAcquiredTime) / 1000000;
        if (this.enableNetworkBury) {
            ConnectTimeConsumingManager.INSTANCE.getInstance().addItem(Long.valueOf(this.connectionDuration));
        }
        recordEventLog("connectionReleased");
    }

    @Override
    public void dnsEnd(Call call, String str, List<InetAddress> list) {
        super.dnsEnd(call, str, list);
        recordEventLog("dnsEnd");
        this.dnsDuration = (System.nanoTime() - this.dnsStartTime) / 1000000;
    }

    @Override
    public void dnsStart(Call call, String str) {
        super.dnsStart(call, str);
        recordEventLog("dnsStart");
        this.dnsStartTime = System.nanoTime();
    }

    public long getConnectionDuration() {
        return this.connectionDuration;
    }

    public long getDnsDuration() {
        return this.dnsDuration;
    }

    public String getHttpEventMessage() {
        String string;
        synchronized (this.httpEventMap) {
            string = this.httpEventMap.toString();
        }
        return "url: " + this.httpUrl.toString() + "; callId: " + this.callId + "; \n" + string + "; this: " + toString();
    }

    @Override
    public void requestBodyEnd(Call call, long j) {
        super.requestBodyEnd(call, j);
        recordEventLog("requestBodyEnd");
    }

    @Override
    public void requestBodyStart(Call call) {
        super.requestBodyStart(call);
        recordEventLog("requestBodyStart");
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        super.requestHeadersEnd(call, request);
        recordEventLog("requestHeadersEnd");
    }

    @Override
    public void requestHeadersStart(Call call) {
        super.requestHeadersStart(call);
        recordEventLog("requestHeadersStart");
    }

    @Override
    public void responseBodyEnd(Call call, long j) {
        super.responseBodyEnd(call, j);
        recordEventLog("responseBodyEnd");
    }

    @Override
    public void responseBodyStart(Call call) {
        super.responseBodyStart(call);
        recordEventLog("responseBodyStart");
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        super.responseHeadersEnd(call, response);
        recordEventLog("responseHeadersEnd");
    }

    @Override
    public void responseHeadersStart(Call call) {
        super.responseHeadersStart(call);
        recordEventLog("responseHeadersStart");
    }

    @Override
    public void secureConnectEnd(Call call, Handshake handshake) {
        super.secureConnectEnd(call, handshake);
        recordEventLog("secureConnectEnd");
    }

    @Override
    public void secureConnectStart(Call call) {
        super.secureConnectStart(call);
        recordEventLog("secureConnectStart");
    }
}
