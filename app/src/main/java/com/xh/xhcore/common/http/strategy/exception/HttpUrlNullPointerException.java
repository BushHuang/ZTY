package com.xh.xhcore.common.http.strategy.exception;

import java.io.IOException;

public class HttpUrlNullPointerException extends IOException {
    public HttpUrlNullPointerException(String str) {
        super(str);
    }

    public HttpUrlNullPointerException(String str, Throwable th) {
        super(str, th);
    }
}
