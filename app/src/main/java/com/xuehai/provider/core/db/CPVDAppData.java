package com.xuehai.provider.core.db;

import android.content.Context;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.utils.JsonUtil;
import java.util.Map;

public class CPVDAppData {
    public static boolean clearAllAppLockedStatus(long j) {
        return getAppLockedConfig(j).delete();
    }

    public static CPVDApp getAppInfo(Context context, long j, String str) {
        return (CPVDApp) JsonUtil.parseJson(CPVDCacheData.getCacheValue(context, getKey(j, str)), CPVDApp.class);
    }

    private static CPVDConfigHelper getAppLockedConfig(long j) {
        return new CPVDConfigHelper("app_lock_" + j + ".ini");
    }

    public static boolean getAppLockedStatus(long j, String str) {
        return Boolean.valueOf(getAppLockedConfig(j).getProperty(str)).booleanValue();
    }

    public static boolean getGlobalAppLockedStatus(long j) {
        return Boolean.valueOf(getAppLockedConfig(j).getProperty("is_need_input_lock_password")).booleanValue();
    }

    private static String getKey(long j, String str) {
        return "app_" + str + "_" + j;
    }

    public static void updateAppInfo(Context context, long j, CPVDApp cPVDApp) {
        CPVDCacheData.saveCache(context, new CPVDCache(getKey(j, cPVDApp.getPkgName()), JsonUtil.objToJson(cPVDApp)));
    }

    public static void updateAppLockStatus(long j, String str, boolean z) throws Throwable {
        getAppLockedConfig(j).store(str, String.valueOf(z));
    }

    public static void updateAppsLockStatus(long j, Map<String, String> map) throws Throwable {
        getAppLockedConfig(j).storeAll(map);
    }

    public static void updateGlobalAppLockedStatus(long j, boolean z) throws Throwable {
        getAppLockedConfig(j).store("is_need_input_lock_password", String.valueOf(z));
    }
}
