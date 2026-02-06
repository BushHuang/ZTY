package com.xh.xhcore.common.config;

public class BaseXHActivityConfig implements IXHActivityConfig {
    @Override
    public boolean enableAutoAdaptation() {
        return false;
    }

    @Override
    @Deprecated
    public DensityAdaptationMode getDensityAdaptationMode() {
        return DensityAdaptationMode.ADAPTATION_MODE_OFF;
    }
}
