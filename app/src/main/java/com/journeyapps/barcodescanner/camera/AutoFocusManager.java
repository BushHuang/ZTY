package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;

public final class AutoFocusManager {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF;
    private static final String TAG = AutoFocusManager.class.getSimpleName();
    private final Camera camera;
    private boolean focusing;
    private boolean stopped;
    private final boolean useAutoFocus;
    private int MESSAGE_FOCUS = 1;
    private final Handler.Callback focusHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what != AutoFocusManager.this.MESSAGE_FOCUS) {
                return false;
            }
            AutoFocusManager.this.focus();
            return true;
        }
    };
    private final Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean z, Camera camera) {
            AutoFocusManager.this.handler.post(new Runnable() {
                @Override
                public void run() {
                    AutoFocusManager.this.focusing = false;
                    AutoFocusManager.this.autoFocusAgainLater();
                }
            });
        }
    };
    private Handler handler = new Handler(this.focusHandlerCallback);

    static {
        ArrayList arrayList = new ArrayList(2);
        FOCUS_MODES_CALLING_AF = arrayList;
        arrayList.add("auto");
        FOCUS_MODES_CALLING_AF.add("macro");
    }

    public AutoFocusManager(Camera camera, CameraSettings cameraSettings) {
        this.camera = camera;
        String focusMode = camera.getParameters().getFocusMode();
        this.useAutoFocus = cameraSettings.isAutoFocusEnabled() && FOCUS_MODES_CALLING_AF.contains(focusMode);
        Log.i(TAG, "Current focus mode '" + focusMode + "'; use auto focus? " + this.useAutoFocus);
        start();
    }

    private synchronized void autoFocusAgainLater() {
        if (!this.stopped && !this.handler.hasMessages(this.MESSAGE_FOCUS)) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(this.MESSAGE_FOCUS), 2000L);
        }
    }

    private void cancelOutstandingTask() {
        this.handler.removeMessages(this.MESSAGE_FOCUS);
    }

    private void focus() {
        if (!this.useAutoFocus || this.stopped || this.focusing) {
            return;
        }
        try {
            this.camera.autoFocus(this.autoFocusCallback);
            this.focusing = true;
        } catch (RuntimeException e) {
            Log.w(TAG, "Unexpected exception while focusing", e);
            autoFocusAgainLater();
        }
    }

    public void start() {
        this.stopped = false;
        focus();
    }

    public void stop() {
        this.stopped = true;
        this.focusing = false;
        cancelOutstandingTask();
        if (this.useAutoFocus) {
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
    }
}
