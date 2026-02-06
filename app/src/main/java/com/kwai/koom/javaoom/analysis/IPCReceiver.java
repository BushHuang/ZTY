package com.kwai.koom.javaoom.analysis;

import android.os.Bundle;
import android.os.ResultReceiver;

class IPCReceiver extends ResultReceiver {
    public static final int RESULT_CODE_FAIL = 1002;
    public static final int RESULT_CODE_OK = 1001;
    private ReceiverCallback receiverCallBack;

    public interface ReceiverCallback {
        void onError();

        void onSuccess();
    }

    public IPCReceiver(ReceiverCallback receiverCallback) {
        super(null);
        this.receiverCallBack = receiverCallback;
    }

    @Override
    protected void onReceiveResult(int i, Bundle bundle) {
        super.onReceiveResult(i, bundle);
        ReceiverCallback receiverCallback = this.receiverCallBack;
        if (receiverCallback != null) {
            if (i == 1001) {
                receiverCallback.onSuccess();
            } else {
                receiverCallback.onError();
            }
        }
    }
}
