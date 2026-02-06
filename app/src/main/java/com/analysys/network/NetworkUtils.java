package com.analysys.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.analysys.utils.CommonUtils;

public class NetworkUtils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return CommonUtils.checkPermission(context, "android.permission.ACCESS_NETWORK_STATE") && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    public static boolean isUploadPolicy(String str, int i) {
        return ((str.equals("WWAN") ? 2 : str.equals("WIFI") ? 4 : 255) & i) != 0;
    }

    public static String networkType(Context context, boolean z) {
        if (!CommonUtils.checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "Unknown";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (activeNetworkInfo == null) {
            return "";
        }
        int type = activeNetworkInfo.getType();
        return type == 1 ? "WIFI" : type == 0 ? z ? String.valueOf(activeNetworkInfo.getSubtype()) : "WWAN" : "";
    }
}
