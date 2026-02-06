package com.obs.services.internal.utils;

import com.obs.services.internal.IHeaders;
import com.obs.services.internal.V2Headers;

public class V2Authentication extends AbstractAuthentication {
    private static V2Authentication instance = new V2Authentication();

    private V2Authentication() {
    }

    public static AbstractAuthentication getInstance() {
        return instance;
    }

    @Override
    protected String getAuthPrefix() {
        return "AWS";
    }

    @Override
    protected IHeaders getIHeaders() {
        return V2Headers.getInstance();
    }
}
