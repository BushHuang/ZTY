package com.zaze.utils.cache;

import android.content.res.Configuration;
import android.os.SystemClock;
import com.zaze.utils.DescriptionUtil;
import com.zaze.utils.DeviceUtil;
import com.zaze.utils.ZStringUtil;
import com.zaze.utils.log.ZLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class MemoryCache implements CacheFace, MemoryListener {
    private static final long CACHE_BLOCK_LENGTH = 1048576;
    private static final Object LOCK = new Object();
    private static final HashMap<String, Cache> cacheMap = new HashMap<>();
    private static volatile MemoryCache memoryCache;
    private final long CACHE_SIZE_MAX;
    private final long PASSIVE_RELEASE;
    private boolean cacheLog = false;
    private long memoryCacheSize = 0;

    private MemoryCache() {
        double runtimeMaxMemory = DeviceUtil.getRuntimeMaxMemory();
        Double.isNaN(runtimeMaxMemory);
        long j = (long) (runtimeMaxMemory * 0.3d);
        this.CACHE_SIZE_MAX = j;
        double d = j;
        Double.isNaN(d);
        this.PASSIVE_RELEASE = (long) (d * 0.4d);
    }

    private String dispatchMemoryCache(byte[] bArr) {
        if (bArr == null) {
            return "MemoryCache cache.getBytes is null";
        }
        if (bArr.length > 1048576) {
            return "MemoryCache cacheData is larger than CACHE_BLOCK_LENGTH 1048576";
        }
        passiveRelease(bArr.length);
        return "";
    }

    private Cache get(String str) {
        synchronized (LOCK) {
            if (!cacheMap.containsKey(str)) {
                return null;
            }
            return cacheMap.get(str);
        }
    }

    public static MemoryCache getInstance() {
        if (memoryCache == null) {
            synchronized (MemoryCache.class) {
                if (memoryCache == null) {
                    memoryCache = new MemoryCache();
                }
            }
        }
        return memoryCache;
    }

    private void passiveRelease(long j) {
        if (this.memoryCacheSize + j < this.CACHE_SIZE_MAX) {
            return;
        }
        if (this.cacheLog) {
            ZLog.e("Cache[ZZ]", "即将达到CACHE_SIZE_MAX >> 强制释放不常用的内存", new Object[0]);
        }
        ArrayList<Cache> arrayListResetSize = resetSize();
        int size = arrayListResetSize.size();
        int i = 0;
        while (true) {
            int i2 = size - 1;
            if (i >= i2) {
                break;
            }
            int i3 = 0;
            while (i3 < i2 - i) {
                Cache cache = arrayListResetSize.get(i3);
                int i4 = i3 + 1;
                Cache cache2 = arrayListResetSize.get(i4);
                if (cache.getLastTimeMillis() > cache2.getLastTimeMillis()) {
                    arrayListResetSize.set(i3, cache2);
                    arrayListResetSize.set(i4, cache);
                } else if (cache.getLastTimeMillis() == cache2.getLastTimeMillis() && cache.getUsedNum() > cache2.getUsedNum()) {
                    arrayListResetSize.set(i3, cache2);
                    arrayListResetSize.set(i4, cache);
                }
                i3 = i4;
            }
            i++;
        }
        long length = 0;
        long j2 = this.PASSIVE_RELEASE;
        if (j2 <= j) {
            j2 = j;
        }
        Iterator<Cache> it = arrayListResetSize.iterator();
        while (length < j2 && it.hasNext()) {
            Cache next = it.next();
            if (next != null && next.getKey() != null && next.getBytes() != null) {
                length += next.getBytes().length;
                clearCache(next.getKey());
            }
        }
        if (this.cacheLog) {
            ZLog.d("Cache[ZZ]", "被动释放策略 >> 释放 : " + length, new Object[0]);
            ZLog.d("Cache[ZZ]", "被动释放策略 >> (after) memoryCacheSize : " + this.memoryCacheSize, new Object[0]);
        }
    }

    private void put(String str, byte[] bArr, long j, int i) {
        synchronized (LOCK) {
            Cache cache = get(str);
            long length = 0;
            long length2 = bArr != null ? bArr.length : 0L;
            if (cache == null) {
                cache = new Cache(str, bArr, j, 0, System.currentTimeMillis());
            } else {
                length2 = cache.updateCache(bArr, j);
            }
            cacheMap.put(str, cache);
            this.memoryCacheSize += length2;
            if (this.cacheLog) {
                ZLog.d("Cache[ZZ]", "数据key : %s ", str);
                Object[] objArr = new Object[1];
                if (cache.getBytes() != null) {
                    length = cache.getBytes().length;
                }
                objArr[0] = DescriptionUtil.toByteUnit(length);
                ZLog.d("Cache[ZZ]", "数据length : %s", objArr);
                ZLog.d("Cache[ZZ]", "被动释放临界点 : %s", DescriptionUtil.toByteUnit(this.PASSIVE_RELEASE));
                ZLog.d("Cache[ZZ]", "缓存空间最大容量 : %s", DescriptionUtil.toByteUnit(this.CACHE_SIZE_MAX));
                ZLog.d("Cache[ZZ]", "当前缓存空间已使用 : %s", DescriptionUtil.toByteUnit(this.memoryCacheSize));
            }
        }
    }

    private ArrayList<Cache> resetSize() {
        ArrayList<Cache> arrayList;
        synchronized (LOCK) {
            arrayList = new ArrayList<>();
            long length = 0;
            Iterator<String> it = cacheMap.keySet().iterator();
            while (it.hasNext()) {
                Cache cache = get(it.next());
                if (cache != null) {
                    length += cache.getBytes().length;
                    arrayList.add(cache);
                }
            }
            this.memoryCacheSize = length;
        }
        return arrayList;
    }

    @Override
    public void clearCache(String str) {
        synchronized (LOCK) {
            Cache cache = get(str);
            if (cache != null && cache.getBytes() != null) {
                this.memoryCacheSize -= cache.getBytes().length;
            }
            if (this.cacheLog) {
                ZLog.d("Cache[ZZ]", "clearCache : " + str, new Object[0]);
            }
            cacheMap.remove(str);
        }
    }

    @Override
    public void clearMemoryCache() {
        synchronized (LOCK) {
            if (this.cacheLog) {
                ZLog.d("Cache[ZZ]", "clearMemoryCache", new Object[0]);
            }
            cacheMap.clear();
            this.memoryCacheSize = 0L;
        }
    }

    @Override
    public byte[] getCache(String str) {
        long jUptimeMillis = this.cacheLog ? SystemClock.uptimeMillis() : 0L;
        Cache cache = get(str);
        if (cache == null) {
            if (!this.cacheLog) {
                return null;
            }
            ZLog.d("Cache[ZZ]", "not found key : " + str, new Object[0]);
            return null;
        }
        if (this.cacheLog) {
            ZLog.d("Cache[ZZ]", "hit key : " + str, new Object[0]);
        }
        if (this.cacheLog) {
            ZLog.d("Cache[ZZ]", "getCache耗时 : " + (SystemClock.uptimeMillis() - jUptimeMillis), new Object[0]);
        }
        return cache.getBytes();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override
    public void onLowMemory() {
        if (this.cacheLog) {
            ZLog.d("Cache[ZZ]", "MemoryCache onLowMemory", new Object[0]);
        }
        clearMemoryCache();
    }

    @Override
    public void onTrimMemory(int i) {
        HashMap map;
        if (this.cacheLog) {
            ZLog.d("Cache[ZZ]", "onTrimMemory : %s, 释放超时和无效的内存", Integer.valueOf(i));
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (LOCK) {
            map = new HashMap(cacheMap);
        }
        for (String str : map.keySet()) {
            Cache cache = (Cache) map.get(str);
            if (cache == null) {
                clearCache(str);
            } else {
                byte[] bytes = cache.getBytes();
                if (bytes == null || bytes.length == 0) {
                    clearCache(str);
                } else if (jCurrentTimeMillis >= cache.getLastTimeMillis() + cache.getKeepTime()) {
                    clearCache(str);
                    if (this.cacheLog) {
                        ZLog.d("Cache[ZZ]", "onTrimMemory : " + cache.toString(), new Object[0]);
                    }
                }
            }
        }
    }

    @Override
    public void saveCache(String str, byte[] bArr, long j, int i) {
        if (bArr == null) {
            return;
        }
        long jUptimeMillis = this.cacheLog ? SystemClock.uptimeMillis() : 0L;
        String strDispatchMemoryCache = dispatchMemoryCache(bArr);
        if (!ZStringUtil.isEmpty(strDispatchMemoryCache) && this.cacheLog) {
            ZLog.w("Cache[ZZ]", strDispatchMemoryCache, new Object[0]);
        }
        put(str, bArr, j, i);
        if (this.cacheLog) {
            ZLog.d("Cache[ZZ]", "saveCache耗时 : " + (SystemClock.uptimeMillis() - jUptimeMillis), new Object[0]);
        }
    }

    void setCacheLog(boolean z) {
        this.cacheLog = z;
    }
}
