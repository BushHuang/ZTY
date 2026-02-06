package com.obs.services.internal.consensus;

import com.obs.services.model.AuthTypeEnum;
import java.util.Date;

public class CacheData {
    private static final long VALID_PERIOD = ((((int) (Math.random() * 5.0d)) + 15) * 60) * 1000;
    private AuthTypeEnum apiVersion;
    private long expirationTime = new Date().getTime() + VALID_PERIOD;

    public CacheData(AuthTypeEnum authTypeEnum) {
        this.apiVersion = authTypeEnum;
    }

    public AuthTypeEnum getApiVersion() {
        return this.apiVersion;
    }

    public long getExpirationTime() {
        return this.expirationTime;
    }
}
