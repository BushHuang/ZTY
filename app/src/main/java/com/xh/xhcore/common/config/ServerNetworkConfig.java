package com.xh.xhcore.common.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0016\u001a\u00020\u0014J\u0006\u0010\u0017\u001a\u00020\u0014J\u0006\u0010\u0018\u001a\u00020\u0014J\u0006\u0010\u0019\u001a\u00020\u0014J\u0006\u0010\u001a\u001a\u00020\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/xh/xhcore/common/config/ServerNetworkConfig;", "", "connectionKeepAliveMillis", "", "socketConnectTimeoutMillis", "socketWriteTimeoutMillis", "socketReadTimeoutMillis", "socketConnectTimeoutMillisUploadDownload", "socketWriteTimeoutMillisUploadDownload", "socketReadTimeoutMillisUploadDownload", "(JJJJJJJ)V", "getConnectionKeepAliveMillis", "()J", "getSocketConnectTimeoutMillis", "getSocketConnectTimeoutMillisUploadDownload", "getSocketReadTimeoutMillis", "getSocketReadTimeoutMillisUploadDownload", "getSocketWriteTimeoutMillis", "getSocketWriteTimeoutMillisUploadDownload", "isConnectionKeepAliveMillisValid", "", "isSocketConnectionTimeoutUploadDownloadValid", "isSocketConnectionTimeoutValid", "isSocketReadTimeoutUploadDownloadValid", "isSocketReadTimeoutValid", "isSocketWriteTimeoutUploadDownloadValid", "isSocketWriteTimeoutValid", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ServerNetworkConfig {
    private final long connectionKeepAliveMillis;
    private final long socketConnectTimeoutMillis;
    private final long socketConnectTimeoutMillisUploadDownload;
    private final long socketReadTimeoutMillis;
    private final long socketReadTimeoutMillisUploadDownload;
    private final long socketWriteTimeoutMillis;
    private final long socketWriteTimeoutMillisUploadDownload;

    public ServerNetworkConfig() {
        this(0L, 0L, 0L, 0L, 0L, 0L, 0L, 127, null);
    }

    public ServerNetworkConfig(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.connectionKeepAliveMillis = j;
        this.socketConnectTimeoutMillis = j2;
        this.socketWriteTimeoutMillis = j3;
        this.socketReadTimeoutMillis = j4;
        this.socketConnectTimeoutMillisUploadDownload = j5;
        this.socketWriteTimeoutMillisUploadDownload = j6;
        this.socketReadTimeoutMillisUploadDownload = j7;
    }

    public ServerNetworkConfig(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? -1L : j, (i & 2) != 0 ? -1L : j2, (i & 4) != 0 ? -1L : j3, (i & 8) != 0 ? -1L : j4, (i & 16) != 0 ? -1L : j5, (i & 32) != 0 ? -1L : j6, (i & 64) == 0 ? j7 : -1L);
    }

    public final long getConnectionKeepAliveMillis() {
        return this.connectionKeepAliveMillis;
    }

    public final long getSocketConnectTimeoutMillis() {
        return this.socketConnectTimeoutMillis;
    }

    public final long getSocketConnectTimeoutMillisUploadDownload() {
        return this.socketConnectTimeoutMillisUploadDownload;
    }

    public final long getSocketReadTimeoutMillis() {
        return this.socketReadTimeoutMillis;
    }

    public final long getSocketReadTimeoutMillisUploadDownload() {
        return this.socketReadTimeoutMillisUploadDownload;
    }

    public final long getSocketWriteTimeoutMillis() {
        return this.socketWriteTimeoutMillis;
    }

    public final long getSocketWriteTimeoutMillisUploadDownload() {
        return this.socketWriteTimeoutMillisUploadDownload;
    }

    public final boolean isConnectionKeepAliveMillisValid() {
        return this.connectionKeepAliveMillis >= 0;
    }

    public final boolean isSocketConnectionTimeoutUploadDownloadValid() {
        return this.socketConnectTimeoutMillisUploadDownload > 0;
    }

    public final boolean isSocketConnectionTimeoutValid() {
        return this.socketConnectTimeoutMillis > 0;
    }

    public final boolean isSocketReadTimeoutUploadDownloadValid() {
        return this.socketReadTimeoutMillisUploadDownload > 0;
    }

    public final boolean isSocketReadTimeoutValid() {
        return this.socketReadTimeoutMillis > 0;
    }

    public final boolean isSocketWriteTimeoutUploadDownloadValid() {
        return this.socketWriteTimeoutMillisUploadDownload > 0;
    }

    public final boolean isSocketWriteTimeoutValid() {
        return this.socketWriteTimeoutMillis > 0;
    }
}
