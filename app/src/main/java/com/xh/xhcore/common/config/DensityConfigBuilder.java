package com.xh.xhcore.common.config;

import android.util.Pair;

public class DensityConfigBuilder {
    private DensityAdaptationMode densityAdaptationMode = DensityAdaptationMode.ADAPTATION_MODE_OFF;
    private float customDensity = 0.0f;
    private Pair<Float, Float> densityAdaptationBasicWidthHeight = new Pair<>(Float.valueOf(768.0f), Float.valueOf(1024.0f));
    private boolean isForbidFontSizeChangeBySystem = true;

    public float getCustomDensity() {
        return this.customDensity;
    }

    public Pair<Float, Float> getDensityAdaptationBasicWidthHeight() {
        return this.densityAdaptationBasicWidthHeight;
    }

    public DensityAdaptationMode getDensityAdaptationMode() {
        return this.densityAdaptationMode;
    }

    public boolean isIsForbidFontSizeChangeBySystem() {
        return this.isForbidFontSizeChangeBySystem;
    }

    public DensityConfigBuilder setCustomDensity(float f) {
        this.customDensity = f;
        return this;
    }

    public DensityConfigBuilder setDensityAdaptationBasicWidthHeight(Pair<Float, Float> pair) {
        this.densityAdaptationBasicWidthHeight = pair;
        return this;
    }

    public DensityConfigBuilder setDensityAdaptationMode(DensityAdaptationMode densityAdaptationMode) {
        this.densityAdaptationMode = densityAdaptationMode;
        return this;
    }

    public DensityConfigBuilder setIsForbidFontSizeChangeBySystem(boolean z) {
        this.isForbidFontSizeChangeBySystem = z;
        return this;
    }
}
