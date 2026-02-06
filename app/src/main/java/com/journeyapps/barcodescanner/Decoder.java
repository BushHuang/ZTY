package com.journeyapps.barcodescanner;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.List;

public class Decoder implements ResultPointCallback {
    private List<ResultPoint> possibleResultPoints = new ArrayList();
    private Reader reader;

    public Decoder(Reader reader) {
        this.reader = reader;
    }

    protected Result decode(BinaryBitmap binaryBitmap) {
        Result resultDecodeWithState;
        this.possibleResultPoints.clear();
        try {
            resultDecodeWithState = this.reader instanceof MultiFormatReader ? ((MultiFormatReader) this.reader).decodeWithState(binaryBitmap) : this.reader.decode(binaryBitmap);
        } catch (Exception unused) {
            resultDecodeWithState = null;
        } catch (Throwable th) {
            this.reader.reset();
            throw th;
        }
        this.reader.reset();
        return resultDecodeWithState;
    }

    public Result decode(LuminanceSource luminanceSource) {
        return decode(toBitmap(luminanceSource));
    }

    @Override
    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        this.possibleResultPoints.add(resultPoint);
    }

    public List<ResultPoint> getPossibleResultPoints() {
        return new ArrayList(this.possibleResultPoints);
    }

    protected Reader getReader() {
        return this.reader;
    }

    protected BinaryBitmap toBitmap(LuminanceSource luminanceSource) {
        return new BinaryBitmap(new HybridBinarizer(luminanceSource));
    }
}
