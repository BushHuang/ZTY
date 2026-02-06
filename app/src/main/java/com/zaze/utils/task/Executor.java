package com.zaze.utils.task;

public interface Executor<T> {
    void onComplete();

    void onError();

    void onExecute(T t) throws Exception;

    void onStart();
}
