package com.kwai.koom.javaoom.common;

import android.app.Application;
import com.kwai.koom.javaoom.monitor.HeapThreshold;
import com.kwai.koom.javaoom.report.DefaultRunningInfoFetcher;
import java.io.File;

public class KGlobalConfig {
    static final String HPROF_DIR = "hprof";
    static final String KOOM_DIR = "koom";
    static final String REPORT_DIR = "report";
    private static KGlobalConfig globalConfig;
    private static String hprofDir;
    private static String reportDir;
    private static String rootDir;
    private Application application;
    private KConfig kConfig;
    private RunningInfoFetcher runningInfoFetcher;
    private KSoLoader soLoader;

    private KGlobalConfig() {
    }

    public static Application getApplication() {
        return getGlobalConfig().application;
    }

    private static KGlobalConfig getGlobalConfig() {
        KGlobalConfig kGlobalConfig = globalConfig;
        if (kGlobalConfig != null) {
            return kGlobalConfig;
        }
        KGlobalConfig kGlobalConfig2 = new KGlobalConfig();
        globalConfig = kGlobalConfig2;
        return kGlobalConfig2;
    }

    public static HeapThreshold getHeapThreshold() {
        return getGlobalConfig().kConfig.getHeapThreshold();
    }

    public static String getHprofDir() {
        String str = hprofDir;
        if (str != null) {
            return str;
        }
        String str2 = getRootDir() + File.separator + "hprof";
        hprofDir = str2;
        return str2;
    }

    public static KConfig getKConfig() {
        return getGlobalConfig().kConfig;
    }

    public static String getReportDir() {
        String str = reportDir;
        if (str != null) {
            return str;
        }
        String str2 = getRootDir() + File.separator + "report";
        reportDir = str2;
        return str2;
    }

    public static String getRootDir() {
        String str = rootDir;
        if (str != null) {
            return str;
        }
        String rootDir2 = getGlobalConfig().kConfig.getRootDir();
        rootDir = rootDir2;
        return rootDir2;
    }

    public static RunningInfoFetcher getRunningInfoFetcher() {
        return getGlobalConfig().runningInfoFetcher;
    }

    public static KSoLoader getSoLoader() {
        KSoLoader kSoLoader = getGlobalConfig().soLoader;
        if (kSoLoader != null) {
            return kSoLoader;
        }
        KGlobalConfig globalConfig2 = getGlobalConfig();
        DefaultKSoLoader defaultKSoLoader = new DefaultKSoLoader();
        globalConfig2.soLoader = defaultKSoLoader;
        return defaultKSoLoader;
    }

    public static void setApplication(Application application) {
        getGlobalConfig().setApplicationInternal(application);
    }

    public static void setKConfig(KConfig kConfig) {
        getGlobalConfig().setKConfigInternal(kConfig);
    }

    public static void setRootDir(String str) {
        getGlobalConfig().kConfig.setRootDir(str);
    }

    public static void setSoLoader(KSoLoader kSoLoader) {
        getGlobalConfig().soLoader = kSoLoader;
    }

    public void setApplicationInternal(Application application) {
        this.application = application;
        this.runningInfoFetcher = new DefaultRunningInfoFetcher(application);
    }

    public void setKConfigInternal(KConfig kConfig) {
        this.kConfig = kConfig;
    }
}
