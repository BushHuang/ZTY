package com.kwai.koom.javaoom.common;

import com.kwai.koom.javaoom.lifecyle.LifecycleObserver;
import com.kwai.koom.javaoom.monitor.TriggerReason;

public interface KTrigger extends LifecycleObserver {
    void startTrack();

    void stopTrack();

    KTriggerStrategy strategy();

    void trigger(TriggerReason triggerReason);
}
