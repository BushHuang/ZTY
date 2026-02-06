package com.xuehai.provider.core.db;

import android.content.Context;
import com.xuehai.provider.core.CPVDManager;
import com.xuehai.provider.core.dto.CPVDCache;

public class CPVDResourceData {
    public static int getApiUpdateStatus(Context context, String str) {
        try {
            return Integer.parseInt(CPVDCacheData.getCacheValue(context, getResourceKey(str)));
        } catch (Exception unused) {
            return -1;
        }
    }

    private static String getResourceKey(String str) {
        return "cpvd_resource_" + str;
    }

    public static boolean isApiNeedUpdate(String str) {
        return 1 == getApiUpdateStatus(CPVDManager.getContext(), str);
    }

    public static void setApiDefault(String str) throws RuntimeException {
        updateApi(str, 0);
    }

    public static void setApiNeedUpdate(String str) throws RuntimeException {
        updateApi(str, 1);
    }

    public static void updateApi(String str, int i) throws RuntimeException {
        Context context = CPVDManager.getContext();
        if (getApiUpdateStatus(context, str) == i) {
            return;
        }
        updateResource(context, str, i);
    }

    private static void updateResource(Context context, String str, int i) {
        CPVDCacheData.saveCache(context, new CPVDCache(getResourceKey(str), String.valueOf(i)));
    }
}
