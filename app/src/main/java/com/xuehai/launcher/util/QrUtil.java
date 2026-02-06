package com.xuehai.launcher.util;

import android.content.Intent;
import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrUtil {
    public static Bitmap createBitmap(String str, int i, int i2) {
        try {
            return new BarcodeEncoder().createBitmap(new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i2));
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static String parseActivityResult(int i, int i2, Intent intent) {
        IntentResult activityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (activityResult == null || -1 != i2) {
            return null;
        }
        return activityResult.getContents();
    }
}
