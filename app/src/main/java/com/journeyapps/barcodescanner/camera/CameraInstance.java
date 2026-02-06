package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.Util;

public class CameraInstance {
    private static final String TAG = CameraInstance.class.getSimpleName();
    private CameraManager cameraManager;
    private CameraThread cameraThread;
    private DisplayConfiguration displayConfiguration;
    private Handler readyHandler;
    private CameraSurface surface;
    private boolean open = false;
    private CameraSettings cameraSettings = new CameraSettings();
    private Runnable opener = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Opening camera");
                CameraInstance.this.cameraManager.open();
            } catch (Exception e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to open camera", e);
            }
        }
    };
    private Runnable configure = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Configuring camera");
                CameraInstance.this.cameraManager.configure();
                if (CameraInstance.this.readyHandler != null) {
                    CameraInstance.this.readyHandler.obtainMessage(R.id.zxing_prewiew_size_ready, CameraInstance.this.getPreviewSize()).sendToTarget();
                }
            } catch (Exception e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to configure camera", e);
            }
        }
    };
    private Runnable previewStarter = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Starting preview");
                CameraInstance.this.cameraManager.setPreviewDisplay(CameraInstance.this.surface);
                CameraInstance.this.cameraManager.startPreview();
            } catch (Exception e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to start preview", e);
            }
        }
    };
    private Runnable closer = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Closing camera");
                CameraInstance.this.cameraManager.stopPreview();
                CameraInstance.this.cameraManager.close();
            } catch (Exception e) {
                Log.e(CameraInstance.TAG, "Failed to close camera", e);
            }
            CameraInstance.this.cameraThread.decrementInstances();
        }
    };

    public CameraInstance(Context context) {
        Util.validateMainThread();
        this.cameraThread = CameraThread.getInstance();
        CameraManager cameraManager = new CameraManager(context);
        this.cameraManager = cameraManager;
        cameraManager.setCameraSettings(this.cameraSettings);
    }

    public CameraInstance(CameraManager cameraManager) {
        Util.validateMainThread();
        this.cameraManager = cameraManager;
    }

    private Size getPreviewSize() {
        return this.cameraManager.getPreviewSize();
    }

    private void notifyError(Exception exc) {
        Handler handler = this.readyHandler;
        if (handler != null) {
            handler.obtainMessage(R.id.zxing_camera_error, exc).sendToTarget();
        }
    }

    private void validateOpen() {
        if (!this.open) {
            throw new IllegalStateException("CameraInstance is not open");
        }
    }

    public void close() {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(this.closer);
        }
        this.open = false;
    }

    public void configureCamera() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.configure);
    }

    protected CameraManager getCameraManager() {
        return this.cameraManager;
    }

    public int getCameraRotation() {
        return this.cameraManager.getCameraRotation();
    }

    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }

    protected CameraThread getCameraThread() {
        return this.cameraThread;
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return this.displayConfiguration;
    }

    protected CameraSurface getSurface() {
        return this.surface;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void open() {
        Util.validateMainThread();
        this.open = true;
        this.cameraThread.incrementAndEnqueue(this.opener);
    }

    public void requestPreview(final PreviewCallback previewCallback) {
        validateOpen();
        this.cameraThread.enqueue(new Runnable() {
            @Override
            public void run() {
                CameraInstance.this.cameraManager.requestPreviewFrame(previewCallback);
            }
        });
    }

    public void setCameraSettings(CameraSettings cameraSettings) {
        if (this.open) {
            return;
        }
        this.cameraSettings = cameraSettings;
        this.cameraManager.setCameraSettings(cameraSettings);
    }

    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration) {
        this.displayConfiguration = displayConfiguration;
        this.cameraManager.setDisplayConfiguration(displayConfiguration);
    }

    public void setReadyHandler(Handler handler) {
        this.readyHandler = handler;
    }

    public void setSurface(CameraSurface cameraSurface) {
        this.surface = cameraSurface;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        setSurface(new CameraSurface(surfaceHolder));
    }

    public void setTorch(final boolean z) {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(new Runnable() {
                @Override
                public void run() {
                    CameraInstance.this.cameraManager.setTorch(z);
                }
            });
        }
    }

    public void startPreview() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.previewStarter);
    }
}
