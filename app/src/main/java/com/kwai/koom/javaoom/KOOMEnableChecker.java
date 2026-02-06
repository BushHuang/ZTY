package com.kwai.koom.javaoom;

import android.os.Build;
import android.text.TextUtils;
import com.kwai.koom.javaoom.common.KConstants;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.common.KUtils;
import com.kwai.koom.javaoom.common.KVData;
import java.io.IOException;

public class KOOMEnableChecker {
    private static KOOMEnableChecker runningChecker;
    private Result result;

    public enum Result {
        NORMAL,
        EXPIRED_DATE,
        EXPIRED_TIMES,
        SPACE_NOT_ENOUGH,
        PROCESS_NOT_ENABLED,
        OS_VERSION_NO_COMPATIBILITY
    }

    public static Result doCheck() {
        KOOMEnableChecker kOOMEnableChecker = get();
        runningChecker = kOOMEnableChecker;
        Result result = kOOMEnableChecker.result;
        if (result != null) {
            return result;
        }
        if (!kOOMEnableChecker.isVersionPermit()) {
            KOOMEnableChecker kOOMEnableChecker2 = runningChecker;
            Result result2 = Result.OS_VERSION_NO_COMPATIBILITY;
            kOOMEnableChecker2.result = result2;
            return result2;
        }
        if (!runningChecker.isSpaceEnough()) {
            KOOMEnableChecker kOOMEnableChecker3 = runningChecker;
            Result result3 = Result.SPACE_NOT_ENOUGH;
            kOOMEnableChecker3.result = result3;
            return result3;
        }
        if (runningChecker.isDateExpired()) {
            KOOMEnableChecker kOOMEnableChecker4 = runningChecker;
            Result result4 = Result.EXPIRED_DATE;
            kOOMEnableChecker4.result = result4;
            return result4;
        }
        if (runningChecker.isMaxTimesOverflow()) {
            KOOMEnableChecker kOOMEnableChecker5 = runningChecker;
            Result result5 = Result.EXPIRED_TIMES;
            kOOMEnableChecker5.result = result5;
            return result5;
        }
        if (runningChecker.isProcessPermitted()) {
            return Result.NORMAL;
        }
        KOOMEnableChecker kOOMEnableChecker6 = runningChecker;
        Result result6 = Result.PROCESS_NOT_ENABLED;
        kOOMEnableChecker6.result = result6;
        return result6;
    }

    public static KOOMEnableChecker get() {
        KOOMEnableChecker kOOMEnableChecker = runningChecker;
        if (kOOMEnableChecker == null) {
            kOOMEnableChecker = new KOOMEnableChecker();
        }
        runningChecker = kOOMEnableChecker;
        return kOOMEnableChecker;
    }

    public boolean isDateExpired() {
        String strAppVersion = KGlobalConfig.getRunningInfoFetcher().appVersion();
        long jFirstLaunchTime = KVData.firstLaunchTime(strAppVersion);
        KLog.i("koom", "version:" + strAppVersion + " first launch time:" + jFirstLaunchTime);
        return System.currentTimeMillis() - jFirstLaunchTime > ((long) KConstants.EnableCheck.MAX_TIME_WINDOW_IN_DAYS) * KConstants.Time.DAY_IN_MILLS;
    }

    public boolean isMaxTimesOverflow() {
        String strAppVersion = KGlobalConfig.getRunningInfoFetcher().appVersion();
        int triggerTimes = KVData.getTriggerTimes(strAppVersion);
        KLog.i("koom", "version:" + strAppVersion + " triggered times:" + triggerTimes);
        return triggerTimes > KConstants.EnableCheck.TRIGGER_MAX_TIMES;
    }

    public boolean isProcessPermitted() throws IOException {
        String processName = KGlobalConfig.getKConfig().getProcessName();
        String processName2 = KUtils.getProcessName();
        KLog.i("koom", "enabledProcess:" + processName + ", runningProcess:" + processName2);
        return TextUtils.equals(processName, processName2);
    }

    public boolean isSpaceEnough() {
        float spaceInGB = KUtils.getSpaceInGB(KGlobalConfig.getRootDir());
        if (KConstants.Debug.VERBOSE_LOG) {
            KLog.i("koom", "Disk space:" + spaceInGB + "Gb");
        }
        return spaceInGB > KConstants.Disk.ENOUGH_SPACE_IN_GB;
    }

    public boolean isVersionPermit() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 29;
    }
}
