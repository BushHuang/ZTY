package com.xuehai.provider.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

@Deprecated
public class UpdateReceiver extends BroadcastReceiver {

    @Deprecated
    public interface UpdateCallback {
        void onUpdate(Context context);
    }

    public static void setCallback(UpdateCallback updateCallback) {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    }
}
