package com.kwai.koom.javaoom.monitor;

import com.kwai.koom.javaoom.common.KConstants;

public class HeapThrashingThreshold implements Threshold {
    private static final int DEFAULT_OVER_TIMES = 3;
    private static final int DEFAULT_POLL_INTERVAL = 5000;
    private static final int DEFAULT_THRASH_SIZE = 100;

    @Override
    public boolean ascending() {
        return false;
    }

    @Override
    public int overTimes() {
        return 3;
    }

    @Override
    public int pollInterval() {
        return 5000;
    }

    @Override
    public float value() {
        return KConstants.Bytes.MB * 100;
    }

    @Override
    public ThresholdValueType valueType() {
        return ThresholdValueType.BYTES;
    }
}
