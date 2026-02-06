package com.tencent.tinker.entry;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

public class DefaultApplicationLike extends ApplicationLike {
    private static final String TAG = "Tinker.DefaultAppLike";

    public DefaultApplicationLike(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
    }

    @Override
    public void onBaseContextAttached(Context context) {
        Log.d("Tinker.DefaultAppLike", "onBaseContextAttached:");
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        Log.d("Tinker.DefaultAppLike", "onConfigurationChanged:" + configuration.toString());
    }

    @Override
    public void onCreate() {
        Log.d("Tinker.DefaultAppLike", "onCreate");
    }

    @Override
    public void onLowMemory() {
        Log.d("Tinker.DefaultAppLike", "onLowMemory");
    }

    @Override
    public void onTerminate() {
        Log.d("Tinker.DefaultAppLike", "onTerminate");
    }

    @Override
    public void onTrimMemory(int i) {
        Log.d("Tinker.DefaultAppLike", "onTrimMemory level:" + i);
    }
}
