package com.xuehai.provider.core.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.xuehai.provider.constants.AuthorityUris;
import com.xuehai.provider.core.CPVDProcess;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.log.ContentProviderLog;
import java.util.HashMap;
import java.util.Locale;

public class CPVDCacheData {
    public static final String ID = "_id";
    public static final String KEY = "_key";
    private static final HashMap<String, CPVDCache> MEMORY_CACHE = new HashMap<>();
    private static final String TAG = "CacheData";
    public static final String VALUE = "_value";

    public static void clearMemory() {
        synchronized (MEMORY_CACHE) {
            MEMORY_CACHE.clear();
        }
    }

    public static void clearMemory(String str) {
        synchronized (MEMORY_CACHE) {
            MEMORY_CACHE.remove(str);
        }
    }

    public static CPVDCache dealCursorPub(Cursor cursor) {
        CPVDCache cPVDCache = new CPVDCache();
        cPVDCache.setId(DaoUtil.getLong(cursor, "_id"));
        cPVDCache.setKey(DaoUtil.getString(cursor, "_key"));
        cPVDCache.setValue(DaoUtil.getString(cursor, "_value"));
        return cPVDCache;
    }

    public static void deleteCache(Context context, String str) {
        ContentProviderLog.i("CacheData", "deleteCache key : " + str);
        saveCache(context, new CPVDCache(str, null));
    }

    public static CPVDCache getCache(Context context, String str) {
        CPVDCache cPVDCacheDealCursorPub;
        Cursor cursorQuery;
        synchronized (MEMORY_CACHE) {
            cPVDCacheDealCursorPub = MEMORY_CACHE.get(str);
            if (cPVDCacheDealCursorPub == null) {
                try {
                    cursorQuery = context.getContentResolver().query(getUri(), null, String.format("%s=?", "_key"), new String[]{str}, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (cursorQuery != null) {
                    if (cursorQuery.moveToNext()) {
                        cPVDCacheDealCursorPub = dealCursorPub(cursorQuery);
                        ContentProviderLog.i("CacheData", "query from db " + str + " : " + cPVDCacheDealCursorPub);
                        MEMORY_CACHE.put(str, cPVDCacheDealCursorPub);
                    }
                    cursorQuery.close();
                }
            }
        }
        return cPVDCacheDealCursorPub;
    }

    public static String getCacheValue(Context context, String str) {
        CPVDCache cache = getCache(context, str);
        if (cache != null) {
            return cache.getValue();
        }
        return null;
    }

    private static Uri getUri() {
        return AuthorityUris.getAuthorityUris().getCacheUri();
    }

    public static boolean saveCache(Context context, CPVDCache cPVDCache) {
        synchronized (MEMORY_CACHE) {
            try {
                if (cPVDCache == null) {
                    return false;
                }
                try {
                    String key = cPVDCache.getKey();
                    String value = cPVDCache.getValue();
                    if (cPVDCache.equals(MEMORY_CACHE.get(key))) {
                        return true;
                    }
                    ContentProviderLog.i("CacheData", String.format(Locale.getDefault(), "saveCache %s : %s", key, value));
                    context.getContentResolver().delete(getUri(), String.format("%s=?", "_key"), new String[]{key});
                    if (TextUtils.isEmpty(value)) {
                        MEMORY_CACHE.remove(key);
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("_key", key);
                        contentValues.put("_value", value);
                        context.getContentResolver().insert(getUri(), contentValues);
                        MEMORY_CACHE.put(key, cPVDCache);
                    }
                    sendBroadcastByKey(context, cPVDCache.getKey());
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void sendBroadcastByKey(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra("key_cache_key", str);
        intent.setAction("com.xuehai.cpvd.cache_update");
        CPVDProcess.sendBroadcast(context, intent);
    }
}
