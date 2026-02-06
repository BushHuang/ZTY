package com.obs.services.internal.security;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import com.obs.services.model.AuthTypeEnum;

public class ProviderCredentials {
    protected static final ILogger log = LoggerBuilder.getLogger((Class<?>) ProviderCredentials.class);
    protected String accessKey;
    protected AuthTypeEnum authType;
    protected String secretKey;
    private String securityToken;
    private ThreadLocal<AuthTypeEnum> threadLocalAuthType;

    public ProviderCredentials(String str, String str2) {
        this.accessKey = str;
        this.secretKey = str2;
    }

    public ProviderCredentials(String str, String str2, String str3) {
        this(str, str2);
        this.securityToken = str3;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public AuthTypeEnum getAuthType() {
        ThreadLocal<AuthTypeEnum> threadLocal = this.threadLocalAuthType;
        return threadLocal == null ? this.authType : threadLocal.get();
    }

    public String getRegion() {
        return "region";
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getSecurityToken() {
        return this.securityToken;
    }

    public void initThreadLocalAuthType() {
        if (this.threadLocalAuthType == null) {
            this.threadLocalAuthType = new ThreadLocal<AuthTypeEnum>() {
                @Override
                protected AuthTypeEnum initialValue() {
                    return ProviderCredentials.this.authType;
                }
            };
        }
    }

    public void removeThreadLocalAuthType() {
        ThreadLocal<AuthTypeEnum> threadLocal = this.threadLocalAuthType;
        if (threadLocal != null) {
            threadLocal.remove();
        }
    }

    public void setAuthType(AuthTypeEnum authTypeEnum) {
        this.authType = authTypeEnum;
    }

    public void setSecurityToken(String str) {
        this.securityToken = str;
    }

    public void setThreadLocalAuthType(AuthTypeEnum authTypeEnum) {
        ThreadLocal<AuthTypeEnum> threadLocal = this.threadLocalAuthType;
        if (threadLocal != null) {
            threadLocal.set(authTypeEnum);
        }
    }
}
