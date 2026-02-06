package com.xuehai.launcher.receiver;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.log.MyLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0003¨\u0006\u000e"}, d2 = {"Lcom/xuehai/launcher/receiver/WifiCompat;", "", "()V", "hasNetworkPermission", "", "context", "Landroid/content/Context;", "listener", "", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "listenerByBroadcast", "Landroid/app/Application;", "listenerByConn", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiCompat {
    public static final WifiCompat INSTANCE = new WifiCompat();

    private WifiCompat() {
    }

    public static void listener$default(WifiCompat wifiCompat, ConnectivityManager.NetworkCallback networkCallback, int i, Object obj) {
        if ((i & 1) != 0) {
            networkCallback = null;
        }
        wifiCompat.listener(networkCallback);
    }

    private final void listenerByBroadcast(Application context) {
        MyLog.i("[MDM]", "Listener wifi by Broadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new WifiReceiver(), intentFilter);
    }

    private final void listenerByConn(ConnectivityManager.NetworkCallback networkCallback) {
        MyLog.i("[MDM]", "Listener wifi by NetworkCallback");
        App companion = App.INSTANCE.getInstance();
        ConnectivityManager connectivityManager = (ConnectivityManager) companion.getSystemService("connectivity");
        if (connectivityManager == null) {
            MyLog.w("[MDM]", "connectivityManager is null !!");
        } else if (hasNetworkPermission(companion)) {
            connectivityManager.requestNetwork(new NetworkRequest.Builder().build(), networkCallback);
        } else {
            MyLog.w("Debug[MDM]", "checkSelfPermission denied!!");
        }
    }

    public final boolean hasNetworkPermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ContextCompat.checkSelfPermission(context, "android.permission.CHANGE_NETWORK_STATE") == 0;
    }

    public final void listener(ConnectivityManager.NetworkCallback networkCallback) {
        if (Build.VERSION.SDK_INT < 21 || networkCallback == null) {
            listenerByBroadcast(App.INSTANCE.getInstance());
        } else {
            listenerByConn(networkCallback);
        }
    }
}
