package com.kwai.koom.javaoom.monitor;

public interface Monitor {
    TriggerReason getTriggerReason();

    boolean isTrigger();

    MonitorType monitorType();

    int pollInterval();

    void setThreshold(Threshold threshold);

    void start();

    void stop();
}
