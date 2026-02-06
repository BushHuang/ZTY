package com.obs.services.internal.utils;

public interface IAuthentication {
    String getAuthorization();

    String getCanonicalRequest();

    String getStringToSign();
}
