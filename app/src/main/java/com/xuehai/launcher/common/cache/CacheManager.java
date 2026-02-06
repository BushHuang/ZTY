package com.xuehai.launcher.common.cache;

import com.zaze.utils.cache.MemoryCacheManager;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\b\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u001c\u0010\u000f\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u0007J\u001c\u0010\u0011\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/common/cache/CacheManager;", "", "()V", "clearCache", "", "deleteCache", "key", "", "getCache", "getCacheBytes", "", "onLowMemory", "onTrimMemory", "level", "", "saveCache", "valueStr", "saveCacheBytes", "values", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CacheManager {
    public static final CacheManager INSTANCE = new CacheManager();

    private CacheManager() {
    }

    @JvmStatic
    public static final synchronized void clearCache() {
        MemoryCacheManager.clearMemoryCache();
        ApplicationManager.INSTANCE.clearAllCache();
    }

    @JvmStatic
    public static final void deleteCache(String key) {
        MemoryCacheManager.deleteCache(key);
    }

    @JvmStatic
    public static final String getCache(String key) {
        String cache = MemoryCacheManager.getCache(key);
        Intrinsics.checkNotNullExpressionValue(cache, "getCache(key)");
        return cache;
    }

    @JvmStatic
    public static final byte[] getCacheBytes(String key) {
        return MemoryCacheManager.getCacheBytes(key);
    }

    @JvmStatic
    public static final synchronized void onLowMemory() {
        MemoryCacheManager.onLowMemory();
        ApplicationManager.INSTANCE.clearAllCache();
    }

    @JvmStatic
    public static final synchronized void onTrimMemory(int level) {
        MemoryCacheManager.onTrimMemory(level);
        ApplicationManager.INSTANCE.clearAllCache();
    }

    @JvmStatic
    public static final void saveCache(String key, String valueStr) {
        MemoryCacheManager.saveCache(key, valueStr);
    }

    @JvmStatic
    public static final void saveCacheBytes(String key, byte[] values) {
        MemoryCacheManager.saveCacheBytes(key, values);
    }
}
