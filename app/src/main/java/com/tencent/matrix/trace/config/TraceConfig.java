package com.tencent.matrix.trace.config;

import com.tencent.matrix.trace.listeners.IDefaultConfig;
import com.tencent.mrs.plugin.IDynamicConfig;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TraceConfig implements IDefaultConfig {
    private static final String TAG = "Matrix.TraceConfig";
    public boolean defaultAnrEnable;
    public boolean defaultFpsEnable;
    public boolean defaultMethodTraceEnable;
    public boolean defaultStartupEnable;
    public IDynamicConfig dynamicConfig;
    public boolean isDebug;
    public boolean isDevEnv;
    public String splashActivities;
    public Set<String> splashActivitiesSet;

    public static class Builder {
        private TraceConfig config = new TraceConfig();

        public TraceConfig build() {
            return this.config;
        }

        public Builder dynamicConfig(IDynamicConfig iDynamicConfig) {
            this.config.dynamicConfig = iDynamicConfig;
            return this;
        }

        public Builder enableAnrTrace(boolean z) {
            this.config.defaultAnrEnable = z;
            return this;
        }

        public Builder enableEvilMethodTrace(boolean z) {
            this.config.defaultMethodTraceEnable = z;
            return this;
        }

        public Builder enableFPS(boolean z) {
            this.config.defaultFpsEnable = z;
            return this;
        }

        public Builder enableStartup(boolean z) {
            this.config.defaultStartupEnable = z;
            return this;
        }

        public Builder isDebug(boolean z) {
            this.config.isDebug = z;
            return this;
        }

        public Builder isDevEnv(boolean z) {
            this.config.isDevEnv = z;
            return this;
        }

        public Builder splashActivities(String str) {
            this.config.splashActivities = str;
            return this;
        }
    }

    private TraceConfig() {
    }

    private float get(IDynamicConfig.ExptEnum exptEnum, float f) {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        return iDynamicConfig == null ? f : iDynamicConfig.get(exptEnum.name(), f);
    }

    public int get(IDynamicConfig.ExptEnum exptEnum, int i) {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        return iDynamicConfig == null ? i : iDynamicConfig.get(exptEnum.name(), i);
    }

    public int getColdStartupThresholdMs() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 10000;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_trace_app_start_up_threshold.name(), 10000);
    }

    public float getDroppedFramesRatioReportThresholdFrozen() {
        return get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_frames_threshold_frozen, 0.0f);
    }

    public float getDroppedFramesRatioReportThresholdHigh() {
        return get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_frames_threshold_high, 0.001f);
    }

    public float getDroppedFramesRatioReportThresholdMiddle() {
        return get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_frames_threshold_middle, 0.01f);
    }

    public float getDroppedFramesRatioReportThresholdNormal() {
        return get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_frames_threshold_normal, 0.1f);
    }

    public int getEvilThresholdMs() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 700;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_trace_evil_method_threshold.name(), 700);
    }

    public int getFrozenThreshold() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 42;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_frozen.name(), 42);
    }

    public int getHighThreshold() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 24;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_high.name(), 24);
    }

    public int getMiddleThreshold() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 9;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_middle.name(), 9);
    }

    public int getNormalThreshold() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 3;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_normal.name(), 3);
    }

    public Set<String> getSplashActivities() {
        if (this.splashActivitiesSet == null) {
            HashSet hashSet = new HashSet();
            this.splashActivitiesSet = hashSet;
            IDynamicConfig iDynamicConfig = this.dynamicConfig;
            if (iDynamicConfig == null) {
                String str = this.splashActivities;
                if (str == null) {
                    return hashSet;
                }
                hashSet.addAll(Arrays.asList(str.split(";")));
            } else {
                String str2 = iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_trace_care_scene_set.name(), this.splashActivities);
                this.splashActivities = str2;
                if (str2 == null) {
                    return this.splashActivitiesSet;
                }
                this.splashActivitiesSet.addAll(Arrays.asList(str2.split(";")));
            }
        }
        return this.splashActivitiesSet;
    }

    public int getTimeSliceMs() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 10000;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_trace_fps_time_slice.name(), 10000);
    }

    public int getWarmStartupThresholdMs() {
        IDynamicConfig iDynamicConfig = this.dynamicConfig;
        if (iDynamicConfig == null) {
            return 4000;
        }
        return iDynamicConfig.get(IDynamicConfig.ExptEnum.clicfg_matrix_trace_warm_app_start_up_threshold.name(), 4000);
    }

    @Override
    public boolean isAnrTraceEnable() {
        return this.defaultAnrEnable;
    }

    @Override
    public boolean isDebug() {
        return this.isDebug;
    }

    @Override
    public boolean isDevEnv() {
        return this.isDevEnv;
    }

    @Override
    public boolean isEvilMethodTraceEnable() {
        return this.defaultMethodTraceEnable;
    }

    @Override
    public boolean isFPSEnable() {
        return this.defaultFpsEnable;
    }

    public boolean isStartupEnable() {
        return this.defaultStartupEnable;
    }

    public String toString() {
        return " \n# TraceConfig\n* isDebug:\t" + this.isDebug + "\n* isDevEnv:\t" + this.isDevEnv + "\n* defaultFpsEnable:\t" + this.defaultFpsEnable + "\n* defaultMethodTraceEnable:\t" + this.defaultMethodTraceEnable + "\n* defaultStartupEnable:\t" + this.defaultStartupEnable + "\n* defaultAnrEnable:\t" + this.defaultAnrEnable + "\n* splashActivities:\t" + this.splashActivities + "\n";
    }
}
