package com.zaze.utils;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH&J\u001a\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\u0017\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u0004\u0018\u00018\u00002\b\u0010\u0011\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/zaze/utils/ZCallback;", "D", "", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "onCompleted", "", "onError", "errorCode", "", "errorMsg", "onNext", "result", "(Ljava/lang/Object;)V", "preNext", "(Ljava/lang/Object;)Ljava/lang/Object;", "util_release"}, k = 1, mv = {1, 4, 1})
public abstract class ZCallback<D> {
    private String message;

    public String getMessage() {
        return this.message;
    }

    public abstract void onCompleted();

    public void onError(int errorCode, String errorMsg) {
    }

    public abstract void onNext(D result);

    public D preNext(D result) {
        return result;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
