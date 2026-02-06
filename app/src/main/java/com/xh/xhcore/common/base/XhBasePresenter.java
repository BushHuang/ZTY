package com.xh.xhcore.common.base;

public abstract class XhBasePresenter<T> {
    public T mView;

    public void attach(T t) {
        this.mView = t;
    }

    public void detach() {
        this.mView = null;
    }
}
