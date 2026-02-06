package com.kwai.koom.javaoom.common;

import com.kwai.koom.javaoom.common.KConstants;
import com.kwai.koom.javaoom.monitor.HeapThreshold;
import java.io.File;

public class KConfig {
    private HeapThreshold heapThreshold;
    private String processName;
    private String rootDir;

    public static class KConfigBuilder {
        private String processName;
        private float heapRatio = KConstants.HeapThreshold.getDefaultPercentRation();
        private int heapOverTimes = KConstants.HeapThreshold.OVER_TIMES;
        private int heapPollInterval = KConstants.HeapThreshold.POLL_INTERVAL;
        private String rootDir = KGlobalConfig.getApplication().getCacheDir().getAbsolutePath() + File.separator + "koom";

        public KConfigBuilder() {
            File file = new File(this.rootDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.processName = KGlobalConfig.getApplication().getPackageName();
        }

        public KConfig build() {
            return new KConfig(new HeapThreshold(this.heapRatio, this.heapOverTimes, this.heapPollInterval), this.rootDir, this.processName);
        }

        public KConfigBuilder heapOverTimes(int i) {
            this.heapOverTimes = i;
            return this;
        }

        public KConfigBuilder heapRatio(float f) {
            this.heapRatio = f;
            return this;
        }

        public KConfigBuilder processName(String str) {
            this.processName = str;
            return this;
        }

        public KConfigBuilder rootDir(String str) {
            this.rootDir = str;
            return this;
        }
    }

    public KConfig(HeapThreshold heapThreshold, String str, String str2) {
        this.rootDir = str;
        this.processName = str2;
        this.heapThreshold = heapThreshold;
    }

    public static KConfig defaultConfig() {
        return new KConfigBuilder().build();
    }

    public HeapThreshold getHeapThreshold() {
        return this.heapThreshold;
    }

    public String getProcessName() {
        return this.processName;
    }

    public String getRootDir() {
        return this.rootDir;
    }

    public void setRootDir(String str) {
        this.rootDir = str;
    }
}
