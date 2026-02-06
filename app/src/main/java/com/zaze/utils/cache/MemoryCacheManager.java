package com.zaze.utils.cache;

import android.content.res.Configuration;
import com.zaze.utils.ZStringUtil;

public class MemoryCacheManager {
    public static final long DAY = 86400000;
    public static final long HALF_YEAR = 110376000000L;
    public static final long HOURE = 3600000;
    public static final long MINUTE = 60000;
    public static final long SECOND = 1000;
    public static final long WEEK = 604800000;
    public static final long YEAR = 220752000000L;

    public static void clearMemoryCache() {
        MemoryCache.getInstance().clearMemoryCache();
    }

    public static void deleteCache(String str) {
        MemoryCache.getInstance().clearCache(str);
    }

    public static String getCache(String str) {
        return ZStringUtil.bytesToString(getCacheBytes(str));
    }

    public static byte[] getCacheBytes(String str) {
        return MemoryCache.getInstance().getCache(str);
    }

    public static void onConfigurationChanged(Configuration configuration) {
        MemoryCache.getInstance().onConfigurationChanged(configuration);
    }

    public static void onLowMemory() {
        MemoryCache.getInstance().onLowMemory();
    }

    public static void onTrimMemory(int i) {
        MemoryCache.getInstance().onTrimMemory(i);
    }

    public static void saveCache(String str, String str2) {
        saveCache(str, str2, 3600000L);
    }

    public static void saveCache(String str, String str2, long j) {
        saveCache(str, str2, j, 100);
    }

    public static void saveCache(String str, String str2, long j, int i) {
        saveCacheBytes(str, ZStringUtil.string2Bytes(str2), j, i);
    }

    public static void saveCacheBytes(String str, byte[] bArr) {
        saveCacheBytes(str, bArr, 3600000L);
    }

    public static void saveCacheBytes(String str, byte[] bArr, long j) {
        saveCacheBytes(str, bArr, j, 100);
    }

    public static void saveCacheBytes(String str, byte[] bArr, long j, int i) {
        MemoryCache.getInstance().saveCache(str, bArr, j, i);
    }

    public static void setCacheLog(boolean z) {
        MemoryCache.getInstance().setCacheLog(z);
    }
}
