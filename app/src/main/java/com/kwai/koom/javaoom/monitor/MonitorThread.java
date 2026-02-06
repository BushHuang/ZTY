package com.kwai.koom.javaoom.monitor;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.kwai.koom.javaoom.common.KConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonitorThread {
    private static final String TAG = "MonitorThread";
    private Handler handler;
    private MonitorTriggerListener monitorTriggerListener;
    private volatile boolean stop = false;
    private HandlerThread thread;

    class MonitorRunnable implements Runnable {
        private Monitor monitor;

        public MonitorRunnable(Monitor monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            if (MonitorThread.this.stop) {
                return;
            }
            if (KConstants.Debug.VERBOSE_LOG) {
                Log.i("MonitorThread", this.monitor.monitorType() + " monitor run");
            }
            if (this.monitor.isTrigger()) {
                Log.i("MonitorThread", this.monitor.monitorType() + " monitor " + this.monitor.monitorType() + " trigger");
                MonitorThread monitorThread = MonitorThread.this;
                monitorThread.stop = monitorThread.monitorTriggerListener.onTrigger(this.monitor.monitorType(), this.monitor.getTriggerReason());
            }
            if (MonitorThread.this.stop) {
                return;
            }
            MonitorThread.this.handler.postDelayed(this, this.monitor.pollInterval());
        }
    }

    public MonitorThread() {
        HandlerThread handlerThread = new HandlerThread("MonitorThread");
        this.thread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.thread.getLooper());
    }

    public void setMonitorTriggerListener(MonitorTriggerListener monitorTriggerListener) {
        this.monitorTriggerListener = monitorTriggerListener;
    }

    public void start(List<Monitor> list) {
        this.stop = false;
        Log.i("MonitorThread", "start");
        ArrayList arrayList = new ArrayList();
        for (Monitor monitor : list) {
            monitor.start();
            arrayList.add(new MonitorRunnable(monitor));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.handler.post((Runnable) it.next());
        }
    }

    public void stop() {
        this.stop = true;
    }
}
