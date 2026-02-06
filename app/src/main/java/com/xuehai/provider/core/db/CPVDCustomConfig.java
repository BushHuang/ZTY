package com.xuehai.provider.core.db;

import android.content.Context;
import com.xuehai.provider.core.CPVDManager;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.log.ContentProviderLog;
import com.xuehai.provider.utils.JsonUtil;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0007J+\u0010\t\u001a\u0004\u0018\u0001H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\n\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0007¢\u0006\u0002\u0010\u000eJ\u001a\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0007J3\u0010\t\u001a\u0004\u0018\u0001H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0007¢\u0006\u0002\u0010\u000fJ,\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u00122\u0006\u0010\b\u001a\u00020\u0004H\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0003J\u0018\u0010\u0015\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/provider/core/db/CPVDCustomConfig;", "", "()V", "CUSTOM_CONFIG_KEY", "", "TAG", "clear", "", "configKey", "getConfig", "key", "T", "clazz", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "getConfigs", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getContext", "Landroid/content/Context;", "saveConfig", "configs", "library_release"}, k = 1, mv = {1, 1, 15})
public final class CPVDCustomConfig {
    public static final String CUSTOM_CONFIG_KEY = "custom_config_key";
    public static final CPVDCustomConfig INSTANCE = new CPVDCustomConfig();
    private static final String TAG = "CPVDCustomConfig";

    private CPVDCustomConfig() {
    }

    @JvmStatic
    public static final <T> T getConfig(String key, Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return (T) getConfig("custom_config_key", key, clazz);
    }

    @JvmStatic
    public static final <T> T getConfig(String configKey, String key, Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(configKey, "configKey");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return (T) JsonUtil.parseJson(getConfig(configKey, key), clazz);
    }

    @JvmStatic
    public static final String getConfig(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getConfigs("custom_config_key").get(key);
    }

    @JvmStatic
    public static final String getConfig(String configKey, String key) {
        Intrinsics.checkParameterIsNotNull(configKey, "configKey");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getConfigs(configKey).get(key);
    }

    @JvmStatic
    public static final HashMap<String, String> getConfigs(String configKey) throws JSONException {
        Intrinsics.checkParameterIsNotNull(configKey, "configKey");
        String cacheValue = CPVDCacheData.getCacheValue(getContext(), configKey);
        HashMap<String, String> map = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(cacheValue);
            Iterator<String> itKeys = jSONObject.keys();
            Intrinsics.checkExpressionValueIsNotNull(itKeys, "json.keys()");
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object obj = jSONObject.get(next);
                String string = obj != null ? obj.toString() : null;
                if (next != null && string != null) {
                    map.put(next, string);
                }
            }
        } catch (Exception unused) {
            ContentProviderLog.e("CPVDCustomConfig", "errorMessage (" + cacheValue + ')');
        }
        return map;
    }

    @JvmStatic
    private static final Context getContext() throws RuntimeException {
        Context context = CPVDManager.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "CPVDManager.getContext()");
        return context;
    }

    public final void clear() {
        clear("custom_config_key");
    }

    public final void clear(String configKey) {
        Intrinsics.checkParameterIsNotNull(configKey, "configKey");
        CPVDCacheData.deleteCache(getContext(), configKey);
    }

    public final void saveConfig(String configs) {
        saveConfig("custom_config_key", configs);
    }

    public final void saveConfig(String configKey, String configs) {
        Intrinsics.checkParameterIsNotNull(configKey, "configKey");
        CPVDCacheData.saveCache(getContext(), new CPVDCache(configKey, configs));
    }
}
