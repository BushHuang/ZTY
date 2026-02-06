package com.huawei.hms.common.api;

import com.huawei.hms.common.Feature;

@Deprecated
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature feature;

    public UnsupportedApiCallException(Feature feature) {
        this.feature = feature;
    }

    @Override
    public final String getMessage() {
        return this.feature + " is unsupported";
    }
}
