package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.CameraPreview;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CaptureManager {
    private static final long DELAY_BEEP = 150;
    private static final String SAVED_ORIENTATION_LOCK = "SAVED_ORIENTATION_LOCK";
    private static final String TAG = CaptureManager.class.getSimpleName();
    private static int cameraPermissionReqCode = 250;
    private Activity activity;
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private Handler handler;
    private InactivityTimer inactivityTimer;
    private int orientationLock = -1;
    private boolean returnBarcodeImagePath = false;
    private boolean destroyed = false;
    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(final BarcodeResult barcodeResult) {
            CaptureManager.this.barcodeView.pause();
            CaptureManager.this.beepManager.playBeepSoundAndVibrate();
            CaptureManager.this.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CaptureManager.this.returnResult(barcodeResult);
                }
            }, 150L);
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> list) {
        }
    };
    private final CameraPreview.StateListener stateListener = new CameraPreview.StateListener() {
        @Override
        public void cameraError(Exception exc) {
            CaptureManager.this.displayFrameworkBugMessageAndExit();
        }

        @Override
        public void previewSized() {
        }

        @Override
        public void previewStarted() {
        }

        @Override
        public void previewStopped() {
        }
    };
    private boolean askedPermission = false;

    public CaptureManager(Activity activity, DecoratedBarcodeView decoratedBarcodeView) {
        this.activity = activity;
        this.barcodeView = decoratedBarcodeView;
        decoratedBarcodeView.getBarcodeView().addStateListener(this.stateListener);
        this.handler = new Handler();
        this.inactivityTimer = new InactivityTimer(activity, new Runnable() {
            @Override
            public void run() {
                Log.d(CaptureManager.TAG, "Finishing due to inactivity");
                CaptureManager.this.finish();
            }
        });
        this.beepManager = new BeepManager(activity);
    }

    private void finish() {
        this.activity.finish();
    }

    private String getBarcodeImagePath(BarcodeResult barcodeResult) throws IOException {
        if (this.returnBarcodeImagePath) {
            Bitmap bitmap = barcodeResult.getBitmap();
            try {
                File fileCreateTempFile = File.createTempFile("barcodeimage", ".jpg", this.activity.getCacheDir());
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                return fileCreateTempFile.getAbsolutePath();
            } catch (IOException e) {
                Log.w(TAG, "Unable to create temporary file and store bitmap! " + e);
            }
        }
        return null;
    }

    public static int getCameraPermissionReqCode() {
        return cameraPermissionReqCode;
    }

    private void openCameraWithPermission() {
        if (ContextCompat.checkSelfPermission(this.activity, "android.permission.CAMERA") == 0) {
            this.barcodeView.resume();
        } else {
            if (this.askedPermission) {
                return;
            }
            ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.CAMERA"}, cameraPermissionReqCode);
            this.askedPermission = true;
        }
    }

    public static Intent resultIntent(BarcodeResult barcodeResult, String str) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.addFlags(524288);
        intent.putExtra("SCAN_RESULT", barcodeResult.toString());
        intent.putExtra("SCAN_RESULT_FORMAT", barcodeResult.getBarcodeFormat().toString());
        byte[] rawBytes = barcodeResult.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent.putExtra("SCAN_RESULT_BYTES", rawBytes);
        }
        Map<ResultMetadataType, Object> resultMetadata = barcodeResult.getResultMetadata();
        if (resultMetadata != null) {
            if (resultMetadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra("SCAN_RESULT_UPC_EAN_EXTENSION", resultMetadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Number number = (Number) resultMetadata.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent.putExtra("SCAN_RESULT_ORIENTATION", number.intValue());
            }
            String str2 = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str2 != null) {
                intent.putExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL", str2);
            }
            Iterable iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                int i = 0;
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    intent.putExtra("SCAN_RESULT_BYTE_SEGMENTS_" + i, (byte[]) it.next());
                    i++;
                }
            }
        }
        if (str != null) {
            intent.putExtra("SCAN_RESULT_IMAGE_PATH", str);
        }
        return intent;
    }

    public static void setCameraPermissionReqCode(int i) {
        cameraPermissionReqCode = i;
    }

    public void decode() {
        this.barcodeView.decodeSingle(this.callback);
    }

    protected void displayFrameworkBugMessageAndExit() {
        if (this.activity.isFinishing() || this.destroyed) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle(this.activity.getString(R.string.zxing_app_name));
        builder.setMessage(this.activity.getString(R.string.zxing_msg_camera_framework_bug));
        builder.setPositiveButton(R.string.zxing_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CaptureManager.this.finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                CaptureManager.this.finish();
            }
        });
        builder.show();
    }

    public void initializeFromIntent(Intent intent, Bundle bundle) {
        this.activity.getWindow().addFlags(128);
        if (bundle != null) {
            this.orientationLock = bundle.getInt("SAVED_ORIENTATION_LOCK", -1);
        }
        if (intent != null) {
            if (this.orientationLock == -1 && intent.getBooleanExtra("SCAN_ORIENTATION_LOCKED", true)) {
                lockOrientation();
            }
            if ("com.google.zxing.client.android.SCAN".equals(intent.getAction())) {
                this.barcodeView.initializeFromIntent(intent);
            }
            if (!intent.getBooleanExtra("BEEP_ENABLED", true)) {
                this.beepManager.setBeepEnabled(false);
                this.beepManager.updatePrefs();
            }
            if (intent.hasExtra("TIMEOUT")) {
                this.handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CaptureManager.this.returnResultTimeout();
                    }
                }, intent.getLongExtra("TIMEOUT", 0L));
            }
            if (intent.getBooleanExtra("BARCODE_IMAGE_ENABLED", false)) {
                this.returnBarcodeImagePath = true;
            }
        }
    }

    protected void lockOrientation() {
        if (this.orientationLock == -1) {
            int rotation = this.activity.getWindowManager().getDefaultDisplay().getRotation();
            int i = this.activity.getResources().getConfiguration().orientation;
            int i2 = 0;
            if (i == 2) {
                if (rotation != 0 && rotation != 1) {
                    i2 = 8;
                }
            } else if (i == 1) {
                i2 = (rotation == 0 || rotation == 3) ? 1 : 9;
            }
            this.orientationLock = i2;
        }
        this.activity.setRequestedOrientation(this.orientationLock);
    }

    public void onDestroy() {
        this.destroyed = true;
        this.inactivityTimer.cancel();
    }

    public void onPause() {
        this.barcodeView.pause();
        this.inactivityTimer.cancel();
        this.beepManager.close();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == cameraPermissionReqCode) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                displayFrameworkBugMessageAndExit();
            } else {
                this.barcodeView.resume();
            }
        }
    }

    public void onResume() {
        if (Build.VERSION.SDK_INT >= 23) {
            openCameraWithPermission();
        } else {
            this.barcodeView.resume();
        }
        this.beepManager.updatePrefs();
        this.inactivityTimer.start();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("SAVED_ORIENTATION_LOCK", this.orientationLock);
    }

    protected void returnResult(BarcodeResult barcodeResult) {
        this.activity.setResult(-1, resultIntent(barcodeResult, getBarcodeImagePath(barcodeResult)));
        finish();
    }

    protected void returnResultTimeout() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("TIMEOUT", true);
        this.activity.setResult(0, intent);
        finish();
    }
}
