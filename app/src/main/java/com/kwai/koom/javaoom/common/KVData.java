package com.kwai.koom.javaoom.common;

import android.app.Application;
import android.content.SharedPreferences;
import com.kwai.koom.javaoom.common.KConstants;

public class KVData {
    private static boolean inited;
    private static SharedPreferences spLaunchTime;
    private static SharedPreferences spTriggers;

    public static void addTriggerTime(String str) {
        if (!inited) {
            init();
        }
        spTriggers.edit().putInt(str, getTriggerTimes(str) + 1).apply();
    }

    public static long firstLaunchTime(String str) {
        if (!inited) {
            init();
        }
        long j = spLaunchTime.getLong(str, 0L);
        if (j != 0) {
            return j;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        spLaunchTime.edit().putLong(str, jCurrentTimeMillis).apply();
        return jCurrentTimeMillis;
    }

    public static int getTriggerTimes(String str) {
        if (!inited) {
            init();
        }
        return spTriggers.getInt(str, 0);
    }

    public static void init() {
        Application application = KGlobalConfig.getApplication();
        spTriggers = application.getSharedPreferences(KConstants.SP.TRIGGER_TIMES_NAME, 0);
        spLaunchTime = application.getSharedPreferences(KConstants.SP.FIRST_LAUNCH_TIME_NAME, 0);
        inited = true;
    }
}
