package com.b2b.rom;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

final class b implements ServiceConnection {

    private ISamsungDevice f162a;

    private b(ISamsungDevice iSamsungDevice) {
        this.f162a = iSamsungDevice;
    }

    b(ISamsungDevice iSamsungDevice, byte b) {
        this(iSamsungDevice);
    }

    @Override
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f162a.d = com.b.a.b.a(iBinder);
        Log.e(this.f162a.f160a, "SoftUpdateServiceConnection:onServiceConnected");
    }

    @Override
    public final void onServiceDisconnected(ComponentName componentName) {
        Log.e(this.f162a.f160a, "SoftUpdateServiceConnection:onServiceDisconnected");
    }
}
