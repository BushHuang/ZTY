package com.analysys.visual;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.analysys.network.NetworkUtils;
import com.analysys.utils.ANSLog;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.InternalAgent;

public class VisualAgent {
    private static String getParams(Context context) {
        try {
            return "?appkey=" + InternalAgent.getAppId(context) + "&version=" + InternalAgent.getVersionName(context) + "&os=Android";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void loadConfigFromServer(final String str) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.a().b(str);
            }
        }).start();
    }

    public static void setVisitorConfigURL(Context context, String str) {
        try {
            if (InternalAgent.isEmpty(context) || InternalAgent.isEmpty(str)) {
                throw new IllegalArgumentException();
            }
            if (!str.startsWith("http://") && !str.startsWith("https://")) {
                throw new IllegalArgumentException();
            }
            String strCheckUrl = InternalAgent.checkUrl(str);
            if (InternalAgent.isEmpty(strCheckUrl)) {
                return;
            }
            b.a().c();
            if (CommonUtils.isMainProcess(context)) {
                String str2 = strCheckUrl + "/configure" + getParams(context);
                ANSLog.i("analysys.visual", "Set visual config url success: " + str2);
                if (NetworkUtils.isNetworkAvailable(context)) {
                    loadConfigFromServer(str2);
                } else {
                    ANSLog.i("analysys.visual", "wait network available");
                    waitNetAvailable(context, str2);
                }
            }
        } catch (Throwable th) {
            Object[] objArr = new Object[2];
            objArr[0] = "analysys.visual";
            StringBuilder sb = new StringBuilder();
            sb.append("Set visual config url fail: ");
            sb.append(context == null);
            sb.append(", ");
            sb.append(str);
            objArr[1] = sb.toString();
            ANSLog.i(objArr);
            ExceptionUtil.exceptionPrint(th);
        }
    }

    public static void setVisitorDebugURL(Context context, String str) {
        try {
            if (CommonUtils.isMainProcess(context)) {
                if (InternalAgent.isEmpty(context) || InternalAgent.isEmpty(str)) {
                    throw new IllegalArgumentException();
                }
                if (!str.startsWith("ws://") && !str.startsWith("wss://")) {
                    throw new IllegalArgumentException();
                }
                String strCheckUrl = InternalAgent.checkUrl(str);
                if (InternalAgent.isEmpty(strCheckUrl)) {
                    return;
                }
                String str2 = strCheckUrl + getParams(context);
                ANSLog.i("analysys.visual", "Set visual debug url success: " + str2);
                a.a().a(str2);
            }
        } catch (Throwable th) {
            Object[] objArr = new Object[2];
            objArr[0] = "analysys.visual";
            StringBuilder sb = new StringBuilder();
            sb.append("Set visual debug url fail: ");
            sb.append(context == null);
            sb.append(", ");
            sb.append(str);
            objArr[1] = sb.toString();
            ANSLog.i(objArr);
            ExceptionUtil.exceptionPrint(th);
        }
    }

    public static void setVisualBaseURL(Context context, String str) {
    }

    private static void waitNetAvailable(final Context context, final String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                if (NetworkUtils.isNetworkAvailable(context)) {
                    context.unregisterReceiver(this);
                    ANSLog.i("analysys.visual", "network available, url: " + str);
                    VisualAgent.loadConfigFromServer(str);
                }
            }
        }, intentFilter);
    }
}
