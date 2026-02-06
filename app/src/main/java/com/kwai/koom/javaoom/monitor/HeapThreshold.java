package com.kwai.koom.javaoom.monitor;

public class HeapThreshold implements Threshold {
    private float heapRatioInPercent;
    private int overTimes;
    private int pollInterval;

    public HeapThreshold(float f, int i, int i2) {
        this.heapRatioInPercent = f;
        this.overTimes = i;
        this.pollInterval = i2;
    }

    @Override
    public boolean ascending() {
        return true;
    }

    @Override
    public int overTimes() {
        return this.overTimes;
    }

    @Override
    public int pollInterval() {
        return this.pollInterval;
    }

    @Override
    public float value() {
        return this.heapRatioInPercent;
    }

    @Override
    public final ThresholdValueType valueType() {
        return ThresholdValueType.PERCENT;
    }
}
