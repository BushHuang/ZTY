package com.b2b.rom;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

final class a implements ServiceConnection {

    private ISamsungDevice f161a;

    private a(ISamsungDevice iSamsungDevice) {
        this.f161a = iSamsungDevice;
    }

    a(ISamsungDevice iSamsungDevice, byte b) {
        this(iSamsungDevice);
    }

    @Override
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f161a.c = com.a.a.b.a(iBinder);
        Log.e(this.f161a.f160a, "KnoxServiceConnection:onServiceConnected");
    }

    @Override
    public final void onServiceDisconnected(ComponentName componentName) {
        Log.e(this.f161a.f160a, "KnoxServiceConnection:onServiceDisconnected");
    }
}
