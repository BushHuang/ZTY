package com.obs.services.model;

import com.obs.services.exception.ObsException;

public interface TaskCallback<K, V> {
    void onException(ObsException obsException, V v);

    void onSuccess(K k);
}
