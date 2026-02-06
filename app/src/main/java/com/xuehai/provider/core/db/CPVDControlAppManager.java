package com.xuehai.provider.core.db;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.core.dto.CPVDControlApp;
import com.xuehai.provider.utils.JsonUtil;
import java.util.List;

public class CPVDControlAppManager {
    private static final String PACKAGE_NAME_CAMERA = "com.xh.camera";
    private static final String PACKAGE_NAME_PHOTO = "com.xh.photo";

    public static List<CPVDControlApp> get(Context context) {
        return JsonUtil.parseJsonToList(CPVDCacheData.getCacheValue(context, getKey()), new TypeToken<List<CPVDControlApp>>() {
        }.getType());
    }

    public static String getKey() {
        return "control_apps";
    }

    public static boolean isAppShow(Context context, String str, boolean z) {
        List<CPVDControlApp> list = get(context);
        for (int i = 0; list != null && i < list.size(); i++) {
            CPVDControlApp cPVDControlApp = list.get(i);
            if (str.equals(cPVDControlApp.getPackageName())) {
                return cPVDControlApp.isShow();
            }
        }
        return z;
    }

    public static boolean isCameraShow(Context context, boolean z) {
        return isAppShow(context, "com.xh.camera", z);
    }

    public static boolean isPhotoShow(Context context, boolean z) {
        return isAppShow(context, "com.xh.photo", z);
    }

    public static void update(Context context, List<CPVDControlApp> list) {
        CPVDCacheData.saveCache(context, new CPVDCache(getKey(), JsonUtil.objToJson(list)));
    }
}
