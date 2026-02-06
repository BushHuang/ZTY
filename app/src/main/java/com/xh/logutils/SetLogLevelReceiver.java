package com.xh.logutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xh.xhcore.common.util.XHLog;

public class SetLogLevelReceiver extends BroadcastReceiver {
    static boolean isLoadLibraryOk = true;

    static {
        try {
            System.loadLibrary("xh_common");
        } catch (Error unused) {
            isLoadLibraryOk = false;
        }
    }

    static native void JNISetLogLevel(int i);

    @Override
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("packageName");
        String stringExtra2 = intent.getStringExtra("logLevel");
        Log.d(Log.XH_COMMON_TAG, "receive set log");
        String packageName = context.getApplicationContext().getPackageName();
        if (stringExtra.equals("com.xh.logcatcher") && stringExtra.equals(packageName)) {
            if (stringExtra2.equals("LOG_LEVEL_VERBOSE")) {
                XHLog.setLogLevel(LogLevel.LOG_LEVEL_VERBOSE);
            } else {
                XHLog.setLogLevel(LogLevel.LOG_LEVEL_ERROR);
            }
        }
    }
}
