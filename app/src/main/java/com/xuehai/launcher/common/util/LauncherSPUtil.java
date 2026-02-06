package com.xuehai.launcher.common.util;

import com.xuehai.launcher.common.base.BaseApplication;
import com.zaze.utils.SharedPrefUtil;

public class LauncherSPUtil {
    public static void commit(String str, Object obj) {
        if (obj != null) {
            try {
                getSpUtil().commit(str, obj);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static <T> T get(String str, T t) {
        try {
            return (T) getSpUtil().get(str, t);
        } catch (Throwable unused) {
            return t;
        }
    }

    public static SharedPrefUtil getSpUtil() {
        return SharedPrefUtil.INSTANCE.newInstance(BaseApplication.getInstance().getDeviceProtectedStorageContext());
    }

    public static long getUpdateTimeByKey(String str) {
        return ((Long) get(str + "_update_time", 0L)).longValue();
    }

    public static void put(String str, Object obj) {
        if (obj != null) {
            try {
                getSpUtil().apply(str, obj);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void setUpdateTimeByKey(String str) {
        put(str + "_update_time", Long.valueOf(System.currentTimeMillis()));
    }
}
