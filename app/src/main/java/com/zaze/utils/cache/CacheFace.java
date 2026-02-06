package com.zaze.utils.cache;

interface CacheFace {
    void clearCache(String str);

    void clearMemoryCache();

    byte[] getCache(String str);

    void saveCache(String str, byte[] bArr, long j, int i);
}
