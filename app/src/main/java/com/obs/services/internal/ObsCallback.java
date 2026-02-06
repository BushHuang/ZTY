package com.obs.services.internal;

import java.lang.Exception;

interface ObsCallback<T, K extends Exception> {
    void onFailure(K k);

    void onSuccess(T t);
}
