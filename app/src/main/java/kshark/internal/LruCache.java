package kshark.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u0004\u0018\u00018\u00012\b\u0010\u0019\u001a\u0004\u0018\u00018\u0000H\u0086\u0002¢\u0006\u0002\u0010\u001aJ\u001d\u0010\u001b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00028\u0001¢\u0006\u0002\u0010\u001dJ\u0015\u0010\u001e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001a\u00028\u0000¢\u0006\u0002\u0010\u001aJ\b\u0010\u001f\u001a\u00020 H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u001e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0011\u0010\u0014\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\f¨\u0006!"}, d2 = {"Lkshark/internal/LruCache;", "K", "V", "", "maxSize", "", "(I)V", "cache", "Ljava/util/LinkedHashMap;", "<set-?>", "evictionCount", "getEvictionCount", "()I", "hitCount", "getHitCount", "getMaxSize", "missCount", "getMissCount", "putCount", "getPutCount", "size", "getSize", "evictAll", "", "get", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
public final class LruCache<K, V> {
    private final LinkedHashMap<K, V> cache;
    private int evictionCount;
    private int hitCount;
    private final int maxSize;
    private int missCount;
    private int putCount;

    public LruCache(int i) {
        this.maxSize = i;
        boolean z = true;
        if (i > 0) {
            this.cache = new LinkedHashMap<K, V>(this.maxSize, 0.75f, z) {
                @Override
                public final Set<Map.Entry<K, V>> entrySet() {
                    return getEntries();
                }

                public Set getEntries() {
                    return super.entrySet();
                }

                public Set getKeys() {
                    return super.keySet();
                }

                public int getSize() {
                    return super.size();
                }

                public Collection getValues() {
                    return super.values();
                }

                @Override
                public final Set<K> keySet() {
                    return getKeys();
                }

                @Override
                protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                    if (size() < LruCache.this.getMaxSize()) {
                        return false;
                    }
                    LruCache lruCache = LruCache.this;
                    lruCache.evictionCount = lruCache.getEvictionCount() + 1;
                    return true;
                }

                @Override
                public final int size() {
                    return getSize();
                }

                @Override
                public final Collection<V> values() {
                    return getValues();
                }
            };
            return;
        }
        throw new IllegalArgumentException(("maxSize=" + this.maxSize + " <= 0").toString());
    }

    public final void evictAll() {
        this.cache.clear();
    }

    public final V get(K key) {
        V v = this.cache.get(key);
        if (v != null) {
            this.hitCount++;
            return v;
        }
        this.missCount++;
        return null;
    }

    public final int getEvictionCount() {
        return this.evictionCount;
    }

    public final int getHitCount() {
        return this.hitCount;
    }

    public final int getMaxSize() {
        return this.maxSize;
    }

    public final int getMissCount() {
        return this.missCount;
    }

    public final int getPutCount() {
        return this.putCount;
    }

    public final int getSize() {
        return this.cache.size();
    }

    public final V put(K key, V value) {
        this.putCount++;
        return this.cache.put(key, value);
    }

    public final V remove(K key) {
        return this.cache.remove(key);
    }

    public String toString() {
        int i = this.hitCount;
        int i2 = this.missCount + i;
        int i3 = i2 != 0 ? (i * 100) / i2 : 0;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Arrays.copyOf(new Object[]{Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i3)}, 4));
        Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(format, *args)");
        return str;
    }
}
