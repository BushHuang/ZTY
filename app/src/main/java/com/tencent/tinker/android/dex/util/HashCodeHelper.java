package com.tencent.tinker.android.dex.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class HashCodeHelper {
    private HashCodeHelper() {
        throw new UnsupportedOperationException();
    }

    public static int hash(Object... objArr) {
        int iHashCode;
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        int iHash = 0;
        for (Object obj : objArr) {
            if (obj != null) {
                if (obj instanceof Number) {
                    iHashCode = obj.hashCode();
                } else if (obj instanceof boolean[]) {
                    iHashCode = Arrays.hashCode((boolean[]) obj);
                } else if (obj instanceof byte[]) {
                    iHashCode = Arrays.hashCode((byte[]) obj);
                } else if (obj instanceof char[]) {
                    iHashCode = Arrays.hashCode((char[]) obj);
                } else if (obj instanceof short[]) {
                    iHashCode = Arrays.hashCode((short[]) obj);
                } else if (obj instanceof int[]) {
                    iHashCode = Arrays.hashCode((int[]) obj);
                } else if (obj instanceof long[]) {
                    iHashCode = Arrays.hashCode((long[]) obj);
                } else if (obj instanceof float[]) {
                    iHashCode = Arrays.hashCode((float[]) obj);
                } else if (obj instanceof double[]) {
                    iHashCode = Arrays.hashCode((double[]) obj);
                } else if (obj instanceof Object[]) {
                    iHashCode = Arrays.hashCode((Object[]) obj);
                } else if (obj.getClass().isArray()) {
                    for (int i = 0; i < Array.getLength(obj); i++) {
                        iHash += hash(Array.get(obj, i));
                    }
                } else {
                    iHashCode = obj.hashCode();
                }
                iHash += iHashCode;
            }
        }
        return iHash;
    }
}
