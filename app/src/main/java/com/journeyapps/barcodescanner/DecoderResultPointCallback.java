package com.journeyapps.barcodescanner;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public class DecoderResultPointCallback implements ResultPointCallback {
    private Decoder decoder;

    public DecoderResultPointCallback() {
    }

    public DecoderResultPointCallback(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        Decoder decoder = this.decoder;
        if (decoder != null) {
            decoder.foundPossibleResultPoint(resultPoint);
        }
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }
}
