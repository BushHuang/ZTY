package com.dovar.dtoast.inner;

import android.os.Handler;
import android.os.Message;

class SafelyHandlerWrapper extends Handler {
    private Handler impl;

    public SafelyHandlerWrapper(Handler handler) {
        this.impl = handler;
    }

    @Override
    public void dispatchMessage(Message message) {
        try {
            this.impl.dispatchMessage(message);
        } catch (Exception unused) {
        }
    }

    @Override
    public void handleMessage(Message message) {
        this.impl.handleMessage(message);
    }
}
