package com.xuehai.launcher.common.network;

import android.net.NetworkInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010\u0011\u001a\u00020\u0006J\u0006\u0010\u0012\u001a\u00020\u0006J\u0006\u0010\u0013\u001a\u00020\u0006R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcom/xuehai/launcher/common/network/NetworkType;", "", "networkInfo", "Landroid/net/NetworkInfo;", "(Landroid/net/NetworkInfo;)V", "isAvailable", "", "()Z", "setAvailable", "(Z)V", "isConnected", "setConnected", "netType", "", "getNetType", "()I", "isEnable", "isMobileAvailable", "isNull", "isWlanAvailable", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NetworkType {
    public static final int MOBILE = 20;
    public static final int NULL = 0;
    public static final int WIFI = 10;
    private boolean isAvailable;
    private boolean isConnected;
    private final int netType;

    public NetworkType() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public NetworkType(NetworkInfo networkInfo) {
        int i;
        if (networkInfo != null) {
            int type = networkInfo.getType();
            if (type == 0) {
                i = 20;
            } else if (type == 1) {
                i = 10;
            } else if (type != 2 && type != 3 && type != 4 && type != 5) {
                i = 0;
            }
        }
        this.netType = i;
        this.isAvailable = networkInfo != null ? networkInfo.isAvailable() : false;
        this.isConnected = networkInfo != null ? networkInfo.isConnected() : false;
    }

    public NetworkType(NetworkInfo networkInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : networkInfo);
    }

    public final int getNetType() {
        return this.netType;
    }

    public final boolean getIsAvailable() {
        return this.isAvailable;
    }

    public final boolean getIsConnected() {
        return this.isConnected;
    }

    public final boolean isEnable() {
        return this.isAvailable;
    }

    public final boolean isMobileAvailable() {
        return this.netType == 20 && isEnable();
    }

    public final boolean isNull() {
        return this.netType == 0;
    }

    public final boolean isWlanAvailable() {
        return this.netType == 10 && isEnable();
    }

    public final void setAvailable(boolean z) {
        this.isAvailable = z;
    }

    public final void setConnected(boolean z) {
        this.isConnected = z;
    }
}
