package com.xuehai.launcher.receiver;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.network.NetworkManger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016¨\u0006\u0012"}, d2 = {"Lcom/xuehai/launcher/receiver/MyNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "()V", "onAvailable", "", "network", "Landroid/net/Network;", "onCapabilitiesChanged", "networkCapabilities", "Landroid/net/NetworkCapabilities;", "onLinkPropertiesChanged", "linkProperties", "Landroid/net/LinkProperties;", "onLosing", "maxMsToLive", "", "onLost", "onUnavailable", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyNetworkCallback extends ConnectivityManager.NetworkCallback {
    @Override
    public void onAvailable(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        super.onAvailable(network);
        MyLog.i("Network[MDM]", "onAvailable : " + network);
        NetworkManger.INSTANCE.onAvailable();
    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        Intrinsics.checkNotNullParameter(network, "network");
        Intrinsics.checkNotNullParameter(networkCapabilities, "networkCapabilities");
        super.onCapabilitiesChanged(network, networkCapabilities);
        MyLog.d("Network[MDM]", "onCapabilitiesChanged(" + network + "): " + networkCapabilities);
    }

    @Override
    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        Intrinsics.checkNotNullParameter(network, "network");
        Intrinsics.checkNotNullParameter(linkProperties, "linkProperties");
        super.onLinkPropertiesChanged(network, linkProperties);
        MyLog.d("Network[MDM]", "onLinkPropertiesChanged(" + network + "): " + linkProperties + ' ');
    }

    @Override
    public void onLosing(Network network, int maxMsToLive) {
        Intrinsics.checkNotNullParameter(network, "network");
        super.onLosing(network, maxMsToLive);
        MyLog.d("Network[MDM]", "onLosing : " + network + "   maxMsToLive : " + maxMsToLive);
    }

    @Override
    public void onLost(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        super.onLost(network);
        MyLog.w("Network[MDM]", "onLost : " + network);
        NetworkManger.INSTANCE.onLost();
    }

    @Override
    public void onUnavailable() {
        super.onUnavailable();
        MyLog.w("Network[MDM]", "onUnavailable ");
        NetworkManger.INSTANCE.onUnavailable();
    }
}
