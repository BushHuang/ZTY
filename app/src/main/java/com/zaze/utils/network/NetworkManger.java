package com.zaze.utils.network;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.zaze.utils.log.ZLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u001a\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u000eJ\u001a\u0010\u0013\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/zaze/utils/network/NetworkManger;", "", "()V", "<set-?>", "Lcom/zaze/utils/network/NetworkType;", "currentNetwork", "getCurrentNetwork", "()Lcom/zaze/utils/network/NetworkType;", "getNetworkType", "connectivityManager", "Landroid/net/ConnectivityManager;", "network", "Landroid/net/Network;", "networkChanged", "", "networkType", "onAvailable", "onLost", "onUnavailable", "refreshNetwork", "util_release"}, k = 1, mv = {1, 4, 1})
public final class NetworkManger {
    public static final NetworkManger INSTANCE = new NetworkManger();
    private static NetworkType currentNetwork = new NetworkType(null, 1, null);

    private NetworkManger() {
    }

    public static NetworkType getNetworkType$default(NetworkManger networkManger, ConnectivityManager connectivityManager, Network network, int i, Object obj) {
        if ((i & 2) != 0) {
            network = (Network) null;
        }
        return networkManger.getNetworkType(connectivityManager, network);
    }

    private final void networkChanged(NetworkType networkType) {
        currentNetwork = networkType;
        ZLog.i("Network[ZZ]", "networkChanged: " + networkType, new Object[0]);
    }

    public static void onAvailable$default(NetworkManger networkManger, ConnectivityManager connectivityManager, Network network, int i, Object obj) {
        if ((i & 2) != 0) {
            network = (Network) null;
        }
        networkManger.onAvailable(connectivityManager, network);
    }

    public static NetworkType refreshNetwork$default(NetworkManger networkManger, ConnectivityManager connectivityManager, Network network, int i, Object obj) {
        if ((i & 2) != 0) {
            network = (Network) null;
        }
        return networkManger.refreshNetwork(connectivityManager, network);
    }

    public final NetworkType getCurrentNetwork() {
        return currentNetwork;
    }

    public final NetworkType getNetworkType(ConnectivityManager connectivityManager, Network network) {
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivityManager");
        NetworkInfo networkInfo = (network != null && Build.VERSION.SDK_INT >= 21) ? connectivityManager.getNetworkInfo(network) : connectivityManager.getActiveNetworkInfo();
        return new NetworkType(networkInfo);
    }

    public final void onAvailable(ConnectivityManager connectivityManager, Network network) {
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivityManager");
        refreshNetwork(connectivityManager, network);
    }

    public final void onLost() {
        networkChanged(new NetworkType(null, 1, null));
    }

    public final void onUnavailable() {
        currentNetwork.setAvailable(false);
        networkChanged(currentNetwork);
    }

    public final NetworkType refreshNetwork(ConnectivityManager connectivityManager, Network network) {
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivityManager");
        NetworkType networkType = getNetworkType(connectivityManager, network);
        INSTANCE.networkChanged(networkType);
        return networkType;
    }
}
