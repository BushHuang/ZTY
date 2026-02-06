package com.journeyapps.barcodescanner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.R;
import java.util.HashMap;
import java.util.List;

public class BarcodeView extends CameraPreview {
    private BarcodeCallback callback;
    private DecodeMode decodeMode;
    private DecoderFactory decoderFactory;
    private DecoderThread decoderThread;
    private final Handler.Callback resultCallback;
    private Handler resultHandler;

    private enum DecodeMode {
        NONE,
        SINGLE,
        CONTINUOUS
    }

    public BarcodeView(Context context) {
        super(context);
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        this.resultCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == R.id.zxing_decode_succeeded) {
                    BarcodeResult barcodeResult = (BarcodeResult) message.obj;
                    if (barcodeResult != null && BarcodeView.this.callback != null && BarcodeView.this.decodeMode != DecodeMode.NONE) {
                        BarcodeView.this.callback.barcodeResult(barcodeResult);
                        if (BarcodeView.this.decodeMode == DecodeMode.SINGLE) {
                            BarcodeView.this.stopDecoding();
                        }
                    }
                    return true;
                }
                if (message.what == R.id.zxing_decode_failed) {
                    return true;
                }
                if (message.what != R.id.zxing_possible_result_points) {
                    return false;
                }
                List<ResultPoint> list = (List) message.obj;
                if (BarcodeView.this.callback != null && BarcodeView.this.decodeMode != DecodeMode.NONE) {
                    BarcodeView.this.callback.possibleResultPoints(list);
                }
                return true;
            }
        };
        initialize(context, null);
    }

    public BarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        this.resultCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == R.id.zxing_decode_succeeded) {
                    BarcodeResult barcodeResult = (BarcodeResult) message.obj;
                    if (barcodeResult != null && BarcodeView.this.callback != null && BarcodeView.this.decodeMode != DecodeMode.NONE) {
                        BarcodeView.this.callback.barcodeResult(barcodeResult);
                        if (BarcodeView.this.decodeMode == DecodeMode.SINGLE) {
                            BarcodeView.this.stopDecoding();
                        }
                    }
                    return true;
                }
                if (message.what == R.id.zxing_decode_failed) {
                    return true;
                }
                if (message.what != R.id.zxing_possible_result_points) {
                    return false;
                }
                List<ResultPoint> list = (List) message.obj;
                if (BarcodeView.this.callback != null && BarcodeView.this.decodeMode != DecodeMode.NONE) {
                    BarcodeView.this.callback.possibleResultPoints(list);
                }
                return true;
            }
        };
        initialize(context, attributeSet);
    }

    public BarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        this.resultCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == R.id.zxing_decode_succeeded) {
                    BarcodeResult barcodeResult = (BarcodeResult) message.obj;
                    if (barcodeResult != null && BarcodeView.this.callback != null && BarcodeView.this.decodeMode != DecodeMode.NONE) {
                        BarcodeView.this.callback.barcodeResult(barcodeResult);
                        if (BarcodeView.this.decodeMode == DecodeMode.SINGLE) {
                            BarcodeView.this.stopDecoding();
                        }
                    }
                    return true;
                }
                if (message.what == R.id.zxing_decode_failed) {
                    return true;
                }
                if (message.what != R.id.zxing_possible_result_points) {
                    return false;
                }
                List<ResultPoint> list = (List) message.obj;
                if (BarcodeView.this.callback != null && BarcodeView.this.decodeMode != DecodeMode.NONE) {
                    BarcodeView.this.callback.possibleResultPoints(list);
                }
                return true;
            }
        };
        initialize(context, attributeSet);
    }

    private Decoder createDecoder() {
        if (this.decoderFactory == null) {
            this.decoderFactory = createDefaultDecoderFactory();
        }
        DecoderResultPointCallback decoderResultPointCallback = new DecoderResultPointCallback();
        HashMap map = new HashMap();
        map.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, decoderResultPointCallback);
        Decoder decoderCreateDecoder = this.decoderFactory.createDecoder(map);
        decoderResultPointCallback.setDecoder(decoderCreateDecoder);
        return decoderCreateDecoder;
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        this.decoderFactory = new DefaultDecoderFactory();
        this.resultHandler = new Handler(this.resultCallback);
    }

    private void startDecoderThread() {
        stopDecoderThread();
        if (this.decodeMode == DecodeMode.NONE || !isPreviewActive()) {
            return;
        }
        DecoderThread decoderThread = new DecoderThread(getCameraInstance(), createDecoder(), this.resultHandler);
        this.decoderThread = decoderThread;
        decoderThread.setCropRect(getPreviewFramingRect());
        this.decoderThread.start();
    }

    private void stopDecoderThread() {
        DecoderThread decoderThread = this.decoderThread;
        if (decoderThread != null) {
            decoderThread.stop();
            this.decoderThread = null;
        }
    }

    protected DecoderFactory createDefaultDecoderFactory() {
        return new DefaultDecoderFactory();
    }

    public void decodeContinuous(BarcodeCallback barcodeCallback) {
        this.decodeMode = DecodeMode.CONTINUOUS;
        this.callback = barcodeCallback;
        startDecoderThread();
    }

    public void decodeSingle(BarcodeCallback barcodeCallback) {
        this.decodeMode = DecodeMode.SINGLE;
        this.callback = barcodeCallback;
        startDecoderThread();
    }

    public DecoderFactory getDecoderFactory() {
        return this.decoderFactory;
    }

    @Override
    public void pause() {
        stopDecoderThread();
        super.pause();
    }

    @Override
    protected void previewStarted() {
        super.previewStarted();
        startDecoderThread();
    }

    public void setDecoderFactory(DecoderFactory decoderFactory) {
        Util.validateMainThread();
        this.decoderFactory = decoderFactory;
        DecoderThread decoderThread = this.decoderThread;
        if (decoderThread != null) {
            decoderThread.setDecoder(createDecoder());
        }
    }

    public void stopDecoding() {
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        stopDecoderThread();
    }
}
