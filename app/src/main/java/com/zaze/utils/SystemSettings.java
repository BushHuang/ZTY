package com.zaze.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.Settings;
import com.zaze.utils.log.ZLog;

public class SystemSettings {
    public static long getBootTime() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static void setScreenBrightness(Context context, int i) {
        Uri uriFor = Settings.System.getUriFor("screen_brightness");
        ContentResolver contentResolver = context.getContentResolver();
        Settings.System.putInt(contentResolver, "screen_brightness", i);
        contentResolver.notifyChange(uriFor, null);
    }

    public static void setScreenCloseTime(Context context, int i) {
        ZLog.i("System[ZZ]", "设置屏幕关闭时间 : %s秒", Integer.valueOf(i));
        Settings.System.putInt(context.getContentResolver(), "screen_off_timeout", i);
    }
}
