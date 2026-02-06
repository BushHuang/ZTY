package com.xh.xhcore.common.matrix;

import com.tencent.mrs.plugin.IDynamicConfig;
import com.xh.xhcore.common.matrix.config.DynamicConfigImpl;

public class APMConfig {
    private boolean isDebugAPM = false;
    private boolean enable = false;
    private String splashActivities = null;
    private IDynamicConfig apmDynamicConfig = new DynamicConfigImpl();

    public IDynamicConfig getApmDynamicConfig() {
        return this.apmDynamicConfig;
    }

    String getSplashActivities() {
        return this.splashActivities;
    }

    boolean isDebugAPM() {
        return this.isDebugAPM;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public APMConfig setApmDynamicConfig(IDynamicConfig iDynamicConfig) {
        this.apmDynamicConfig = iDynamicConfig;
        return this;
    }

    public APMConfig setDebugAPM(boolean z) {
        this.isDebugAPM = z;
        return this;
    }

    public APMConfig setEnable(boolean z) {
        this.enable = z;
        return this;
    }

    public APMConfig setSplashActivities(String str) {
        this.splashActivities = str;
        return this;
    }
}
