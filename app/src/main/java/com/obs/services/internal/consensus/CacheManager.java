package com.obs.services.internal.consensus;

import com.obs.services.model.AuthTypeEnum;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
    private ConcurrentHashMap<String, CacheData> apiVersionCache = new ConcurrentHashMap<>();

    private static class SingletonHolder {
        private static final CacheManager INSTANCE = new CacheManager();

        private SingletonHolder() {
        }
    }

    public static CacheManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addApiVersion(String str, AuthTypeEnum authTypeEnum) {
        this.apiVersionCache.put(str, new CacheData(authTypeEnum));
    }

    public void clear() {
        ConcurrentHashMap<String, CacheData> concurrentHashMap = this.apiVersionCache;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public AuthTypeEnum getApiVersionInCache(String str) {
        CacheData cacheData = this.apiVersionCache.get(str);
        if (isValid(cacheData)) {
            return cacheData.getApiVersion();
        }
        return null;
    }

    public boolean isValid(CacheData cacheData) {
        return cacheData != null && cacheData.getExpirationTime() >= new Date().getTime();
    }
}
