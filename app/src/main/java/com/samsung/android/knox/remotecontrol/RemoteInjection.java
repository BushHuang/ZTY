package com.samsung.android.knox.remotecontrol;

import android.view.KeyEvent;
import android.view.MotionEvent;

public class RemoteInjection {
    private android.app.enterprise.remotecontrol.RemoteInjection mRemoteInjection;

    public RemoteInjection(android.app.enterprise.remotecontrol.RemoteInjection remoteInjection) {
        this.mRemoteInjection = remoteInjection;
    }

    public boolean injectKeyEvent(KeyEvent keyEvent, boolean z) {
        return this.mRemoteInjection.injectKeyEvent(keyEvent, z);
    }

    public boolean injectPointerEvent(MotionEvent motionEvent, boolean z) {
        return this.mRemoteInjection.injectPointerEvent(motionEvent, z);
    }

    public boolean injectTrackballEvent(MotionEvent motionEvent, boolean z) {
        return this.mRemoteInjection.injectTrackballEvent(motionEvent, z);
    }
}
