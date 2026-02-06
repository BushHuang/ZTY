package com.samsung.android.knox.sdp.core;

import com.samsung.android.knox.sdp.SdpErrno;

public class SdpException extends Exception {
    private int errorCode;

    public SdpException(int i) {
        super(SdpErrno.toString(i));
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
