package com.xuehai.platform.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.xuehai.platform.PlatformManager;
import com.xuehai.provider.core.CPVDProcess;
import com.xuehai.provider.core.db.CPVDCacheData;

public class FileManager {
    private static final String ACTION_STORAGE_CLEAR = "com.xuehai.action.storage.clear";
    public static final long MIN_STORAGE_SPACE = 1073741824;

    public static boolean checkSdcardIsFull(long j) {
        if (j < 1073741824) {
            j = 1073741824;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return getBlockSize(statFs) * getAvailableBlocks(statFs) < j;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean checkSdcardIsFull(Context context) {
        return checkSdcardIsFull(context, 1073741824L);
    }

    public static boolean checkSdcardIsFull(Context context, long j) throws NumberFormatException {
        if (j < 1073741824) {
            j = 1073741824;
        }
        try {
            j = Long.parseLong(CPVDCacheData.getCacheValue(context, getMinStorageSpaceKey()));
        } catch (NumberFormatException unused) {
        }
        return checkSdcardIsFull(j);
    }

    private static long getAvailableBlocks(StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getAvailableBlocksLong() : statFs.getAvailableBlocks();
    }

    private static long getBlockSize(StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() : statFs.getBlockSize();
    }

    public static String getMinStorageSpaceKey() {
        return "MIN_STORAGE_SPACE";
    }

    public static boolean isLauncherSupport(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("com.xuehai.action.storage.clear");
        return CPVDProcess.isIntentEffective(activity.getPackageManager(), intent);
    }

    public static boolean showCleanDataView(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("com.xuehai.action.storage.clear");
        return PlatformManager.showActivity(activity, intent);
    }
}
