package com.kwai.koom.javaoom;

import android.app.Application;
import com.kwai.koom.javaoom.common.KConfig;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.report.HeapReportUploader;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.File;

public class KOOMInitializer {
    private static final String KOOMTag = "KOOMTag";
    private String KOOMRootDirectory = "";
    private boolean isDebug = false;
    private HeapReportUploader heapReportUploader = null;

    private static class AppLogger extends KLog.DefaultLogger {
        private AppLogger() {
        }

        @Override
        public void d(String str, String str2) {
            LogUtils.d("KOOMTag_" + str, str2);
        }

        @Override
        public void e(String str, String str2) {
            LogUtils.e("KOOMTag_" + str, str2);
        }

        @Override
        public void i(String str, String str2) {
            LogUtils.i("KOOMTag_" + str, str2);
        }
    }

    private KOOMInitializer() {
    }

    public static KOOMInitializer create() {
        return new KOOMInitializer();
    }

    private void customConfig() {
        KConfig.KConfigBuilder kConfigBuilder = new KConfig.KConfigBuilder();
        if (this.isDebug) {
            kConfigBuilder = kConfigBuilder.heapRatio(70.0f).heapOverTimes(1);
        }
        KOOM.getInstance().setKConfig(kConfigBuilder.rootDir(this.KOOMRootDirectory).build());
    }

    private void customLogger() {
        LogUtils.registerLogClass(AppLogger.class.getName());
        LogUtils.registerLogClass(KLog.class.getName());
        KOOM.getInstance().setLogger(new AppLogger());
    }

    private void listenReportGenerateStatus() {
        KOOM.getInstance().setHeapReportUploader(this.heapReportUploader);
    }

    public void init(Application application) {
        KOOM.init(application);
        customLogger();
        customConfig();
        listenReportGenerateStatus();
    }

    public KOOMInitializer setDebug(boolean z) {
        this.isDebug = z;
        return this;
    }

    public KOOMInitializer setHeapReportUploader(HeapReportUploader heapReportUploader) {
        this.heapReportUploader = heapReportUploader;
        return this;
    }

    public KOOMInitializer setKOOMRootDirectory(String str) {
        this.KOOMRootDirectory = str;
        new File(str).mkdirs();
        return this;
    }
}
