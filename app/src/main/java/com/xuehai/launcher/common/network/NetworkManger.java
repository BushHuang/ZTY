package com.xuehai.launcher.common.network;

import androidx.lifecycle.MutableLiveData;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u0006\u0010\u0010\u001a\u00020\u000eJ\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u000eJ\u0006\u0010\u0013\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/common/network/NetworkManger;", "", "()V", "currentNetwork", "Lcom/xuehai/launcher/common/network/NetworkType;", "networkData", "Landroidx/lifecycle/MutableLiveData;", "getNetworkData", "()Landroidx/lifecycle/MutableLiveData;", "setNetworkData", "(Landroidx/lifecycle/MutableLiveData;)V", "isEnable", "", "networkChanged", "", "networkType", "onAvailable", "onLost", "onUnavailable", "refreshNetwork", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NetworkManger {
    public static final NetworkManger INSTANCE = new NetworkManger();
    private static MutableLiveData<NetworkType> networkData = new MutableLiveData<>();
    private static NetworkType currentNetwork = new NetworkType(null, 1, null);

    private NetworkManger() {
    }

    private final void networkChanged(NetworkType networkType) {
        currentNetwork = networkType;
        LiveDataExtKt.set(networkData, networkType);
    }

    public final MutableLiveData<NetworkType> getNetworkData() {
        return networkData;
    }

    public final boolean isEnable() {
        return currentNetwork.isEnable();
    }

    public final void onAvailable() {
        refreshNetwork();
    }

    public final void onLost() {
        networkChanged(new NetworkType(null, 1, null));
    }

    public final void onUnavailable() {
        currentNetwork.setAvailable(false);
        networkChanged(currentNetwork);
    }

    public final NetworkType refreshNetwork() {
        NetworkType networkType = NetworkUtil.INSTANCE.getNetworkType(BaseApplication.INSTANCE.getInstance());
        INSTANCE.networkChanged(networkType);
        return networkType;
    }

    public final void setNetworkData(MutableLiveData<NetworkType> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        networkData = mutableLiveData;
    }
}
