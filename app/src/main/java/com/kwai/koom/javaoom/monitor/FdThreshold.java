package com.kwai.koom.javaoom.monitor;

public class FdThreshold implements Threshold {
    private static final int DEFAULT_FD_COUNT = 800;
    private static final int DEFAULT_OVER_TIMES = 3;
    private static final int DEFAULT_POLL_INTERVAL = 15000;

    @Override
    public boolean ascending() {
        return true;
    }

    @Override
    public int overTimes() {
        return 3;
    }

    @Override
    public int pollInterval() {
        return 15000;
    }

    @Override
    public float value() {
        return 800.0f;
    }

    @Override
    public ThresholdValueType valueType() {
        return ThresholdValueType.COUNT;
    }
}
