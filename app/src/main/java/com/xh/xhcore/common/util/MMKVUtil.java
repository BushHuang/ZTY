package com.xh.xhcore.common.util;

import com.tencent.mmkv.MMKV;
import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xh/xhcore/common/util/MMKVUtil;", "", "()V", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MMKVUtil {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J-\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u0002H\bH\u0007¢\u0006\u0002\u0010\u000bJ \u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0007J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0010H\u0007J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0012H\u0007J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0014H\u0007J \u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0007J\b\u0010\u0016\u001a\u00020\u0004H\u0007J\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00182\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0001H\u0007J\u0018\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u001e"}, d2 = {"Lcom/xh/xhcore/common/util/MMKVUtil$Companion;", "", "()V", "clear", "", "filePath", "", "get", "T", "key", "defaultObject", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getBoolean", "", "defaultValue", "getFloat", "", "getInt", "", "getLong", "", "getString", "initMMKV", "load", "", "put", "value", "remove", "returnMMKV", "Lcom/tencent/mmkv/MMKV;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final MMKV returnMMKV(String filePath) {
            if (XHStringUtil.isSpace(filePath)) {
                throw new NullPointerException("Filepath must not be empty!");
            }
            String fileName = XHFileUtil.getFileName(filePath);
            String fileParentPath = XHFileUtil.getFileParentPath(filePath);
            if (Intrinsics.areEqual(fileName, filePath) || Intrinsics.areEqual(fileParentPath, filePath)) {
                throw new IllegalArgumentException("It's must be file!");
            }
            try {
                return MMKV.mmkvWithID(fileName, 2, "", fileParentPath);
            } catch (Exception e) {
                LogUtils.INSTANCE.e((String) null, e);
                return (MMKV) null;
            }
        }

        @JvmStatic
        public final void clear(String filePath) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            if (mmkvReturnMMKV == null) {
                return;
            }
            mmkvReturnMMKV.clear();
        }

        @JvmStatic
        public final <T> T get(String filePath, String key, T defaultObject) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            if (mmkvReturnMMKV == null) {
                return defaultObject;
            }
            if (defaultObject instanceof String) {
                if (defaultObject != 0) {
                    return (T) mmkvReturnMMKV.getString(key, (String) defaultObject);
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            if (defaultObject instanceof Integer) {
                if (defaultObject != 0) {
                    return (T) Integer.valueOf(mmkvReturnMMKV.getInt(key, ((Integer) defaultObject).intValue()));
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            if (defaultObject instanceof Boolean) {
                if (defaultObject != 0) {
                    return (T) Boolean.valueOf(mmkvReturnMMKV.getBoolean(key, ((Boolean) defaultObject).booleanValue()));
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            if (defaultObject instanceof Float) {
                if (defaultObject != 0) {
                    return (T) Float.valueOf(mmkvReturnMMKV.getFloat(key, ((Float) defaultObject).floatValue()));
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
            }
            if (!(defaultObject instanceof Long)) {
                return null;
            }
            if (defaultObject != 0) {
                return (T) Long.valueOf(mmkvReturnMMKV.getLong(key, ((Long) defaultObject).longValue()));
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
        }

        @JvmStatic
        public final boolean getBoolean(String filePath, String key, boolean defaultValue) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            return mmkvReturnMMKV == null ? defaultValue : mmkvReturnMMKV.getBoolean(key, defaultValue);
        }

        @JvmStatic
        public final float getFloat(String filePath, String key, float defaultValue) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            return mmkvReturnMMKV == null ? defaultValue : mmkvReturnMMKV.getFloat(key, defaultValue);
        }

        @JvmStatic
        public final int getInt(String filePath, String key, int defaultValue) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            return mmkvReturnMMKV == null ? defaultValue : mmkvReturnMMKV.getInt(key, defaultValue);
        }

        @JvmStatic
        public final long getLong(String filePath, String key, long defaultValue) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            return mmkvReturnMMKV == null ? defaultValue : mmkvReturnMMKV.getLong(key, defaultValue);
        }

        @JvmStatic
        public final String getString(String filePath, String key, String defaultValue) {
            String string;
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            return (mmkvReturnMMKV == null || (string = mmkvReturnMMKV.getString(key, defaultValue)) == null) ? defaultValue : string;
        }

        @JvmStatic
        public final void initMMKV() {
            MMKV.initialize(XmConfig.getDir());
        }

        @JvmStatic
        public final Map<String, String> load(String filePath) {
            String[] strArrAllKeys;
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            HashMap map = new HashMap();
            if (mmkvReturnMMKV != null && (strArrAllKeys = mmkvReturnMMKV.allKeys()) != null) {
                int i = 0;
                int length = strArrAllKeys.length;
                while (i < length) {
                    String str = strArrAllKeys[i];
                    i++;
                    Intrinsics.checkNotNullExpressionValue(str, "it");
                    String str2 = "";
                    String string = mmkvReturnMMKV.getString(str, "");
                    if (string != null) {
                        str2 = string;
                    }
                    map.put(str, str2);
                }
            }
            return map;
        }

        @JvmStatic
        public final void put(String filePath, String key, Object value) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            if (mmkvReturnMMKV == null) {
                return;
            }
            if (value instanceof String) {
                mmkvReturnMMKV.putString(key, (String) value);
            } else if (value instanceof Integer) {
                mmkvReturnMMKV.putInt(key, ((Number) value).intValue());
            } else if (value instanceof Float) {
                mmkvReturnMMKV.putFloat(key, ((Number) value).floatValue());
            } else if (value instanceof Boolean) {
                mmkvReturnMMKV.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Long) {
                mmkvReturnMMKV.putLong(key, ((Number) value).longValue());
            } else {
                mmkvReturnMMKV.putString(key, value.toString());
            }
            mmkvReturnMMKV.apply();
        }

        @JvmStatic
        public final void remove(String filePath, String key) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(key, "key");
            MMKV mmkvReturnMMKV = returnMMKV(filePath);
            if (mmkvReturnMMKV == null) {
                return;
            }
            mmkvReturnMMKV.remove(key);
        }
    }

    @JvmStatic
    public static final void clear(String str) {
        INSTANCE.clear(str);
    }

    @JvmStatic
    public static final <T> T get(String str, String str2, T t) {
        return (T) INSTANCE.get(str, str2, t);
    }

    @JvmStatic
    public static final boolean getBoolean(String str, String str2, boolean z) {
        return INSTANCE.getBoolean(str, str2, z);
    }

    @JvmStatic
    public static final float getFloat(String str, String str2, float f) {
        return INSTANCE.getFloat(str, str2, f);
    }

    @JvmStatic
    public static final int getInt(String str, String str2, int i) {
        return INSTANCE.getInt(str, str2, i);
    }

    @JvmStatic
    public static final long getLong(String str, String str2, long j) {
        return INSTANCE.getLong(str, str2, j);
    }

    @JvmStatic
    public static final String getString(String str, String str2, String str3) {
        return INSTANCE.getString(str, str2, str3);
    }

    @JvmStatic
    public static final void initMMKV() {
        INSTANCE.initMMKV();
    }

    @JvmStatic
    public static final Map<String, String> load(String str) {
        return INSTANCE.load(str);
    }

    @JvmStatic
    public static final void put(String str, String str2, Object obj) {
        INSTANCE.put(str, str2, obj);
    }

    @JvmStatic
    public static final void remove(String str, String str2) {
        INSTANCE.remove(str, str2);
    }
}
