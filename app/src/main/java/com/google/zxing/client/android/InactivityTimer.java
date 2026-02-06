package com.google.zxing.client.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

public final class InactivityTimer {
    private static final long INACTIVITY_DELAY_MS = 300000;
    private static final String TAG = InactivityTimer.class.getSimpleName();
    private Runnable callback;
    private final Context context;
    private boolean onBattery;
    private boolean registered = false;
    private final BroadcastReceiver powerStatusReceiver = new PowerStatusReceiver();
    private Handler handler = new Handler();

    private final class PowerStatusReceiver extends BroadcastReceiver {
        private PowerStatusReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                final boolean z = intent.getIntExtra("plugged", -1) <= 0;
                InactivityTimer.this.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        InactivityTimer.this.onBattery(z);
                    }
                });
            }
        }
    }

    public InactivityTimer(Context context, Runnable runnable) {
        this.context = context;
        this.callback = runnable;
    }

    private void cancelCallback() {
        this.handler.removeCallbacksAndMessages(null);
    }

    private void onBattery(boolean z) {
        this.onBattery = z;
        if (this.registered) {
            activity();
        }
    }

    private void registerReceiver() {
        if (this.registered) {
            return;
        }
        this.context.registerReceiver(this.powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.registered = true;
    }

    private void unregisterReceiver() {
        if (this.registered) {
            this.context.unregisterReceiver(this.powerStatusReceiver);
            this.registered = false;
        }
    }

    public void activity() {
        cancelCallback();
        if (this.onBattery) {
            this.handler.postDelayed(this.callback, 300000L);
        }
    }

    public void cancel() {
        cancelCallback();
        unregisterReceiver();
    }

    public void start() {
        registerReceiver();
        activity();
    }
}
