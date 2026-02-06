package com.kwai.koom.javaoom.monitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonitorManager {
    private List<Monitor> monitors = new ArrayList();
    private MonitorThread monitorThread = new MonitorThread();

    public void addMonitor(Monitor monitor) {
        this.monitors.add(monitor);
    }

    public void removeMonitor(Monitor monitor) {
        this.monitors.remove(monitor);
    }

    public void setTriggerListener(MonitorTriggerListener monitorTriggerListener) {
        this.monitorThread.setMonitorTriggerListener(monitorTriggerListener);
    }

    public void start() {
        this.monitorThread.start(this.monitors);
    }

    public void startMonitor(Monitor monitor) {
        monitor.start();
    }

    public void stop() {
        Iterator<Monitor> it = this.monitors.iterator();
        while (it.hasNext()) {
            it.next().stop();
        }
        this.monitorThread.stop();
    }

    public void stopMonitor(Monitor monitor) {
        monitor.stop();
    }
}
