package com.obs.services.internal.security;

public class ProviderCredentialThreadContext {
    private ThreadLocal<ProviderCredentials> context;

    private static class ProviderCredentialThreadContextHolder {
        private static ProviderCredentialThreadContext instance = new ProviderCredentialThreadContext();

        private ProviderCredentialThreadContextHolder() {
        }
    }

    private ProviderCredentialThreadContext() {
        this.context = new ThreadLocal<>();
    }

    public static ProviderCredentialThreadContext getInstance() {
        return ProviderCredentialThreadContextHolder.instance;
    }

    public void clearProviderCredentials() {
        this.context.remove();
    }

    public ProviderCredentials getProviderCredentials() {
        return this.context.get();
    }

    public void setProviderCredentials(ProviderCredentials providerCredentials) {
        this.context.set(providerCredentials);
    }
}
