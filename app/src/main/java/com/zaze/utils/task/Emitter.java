package com.zaze.utils.task;

public interface Emitter<T> {
    void onComplete();

    void onError(Throwable th);

    void onExecute(T t) throws Exception;
}
