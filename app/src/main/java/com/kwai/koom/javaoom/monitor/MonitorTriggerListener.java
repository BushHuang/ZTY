package com.kwai.koom.javaoom.monitor;

public interface MonitorTriggerListener {
    boolean onTrigger(MonitorType monitorType, TriggerReason triggerReason);
}
