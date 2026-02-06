package com.kwai.koom.javaoom.monitor;

public interface Threshold {
    boolean ascending();

    int overTimes();

    int pollInterval();

    float value();

    ThresholdValueType valueType();
}
