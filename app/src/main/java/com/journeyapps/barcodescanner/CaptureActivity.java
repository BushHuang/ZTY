package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.google.zxing.client.android.R;

public class CaptureActivity extends Activity {
    private DecoratedBarcodeView barcodeScannerView;
    private CaptureManager capture;

    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.zxing_capture);
        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.barcodeScannerView = initializeContent();
        CaptureManager captureManager = new CaptureManager(this, this.barcodeScannerView);
        this.capture = captureManager;
        captureManager.initializeFromIntent(getIntent(), bundle);
        this.capture.decode();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.capture.onDestroy();
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.barcodeScannerView.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.capture.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.capture.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.capture.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.capture.onSaveInstanceState(bundle);
    }
}
