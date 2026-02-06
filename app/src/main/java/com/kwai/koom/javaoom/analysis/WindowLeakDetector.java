package com.kwai.koom.javaoom.analysis;

import android.view.Window;
import com.kwai.koom.javaoom.common.KLog;
import kshark.HeapGraph;
import kshark.HeapObject;

public class WindowLeakDetector extends LeakDetector {
    static final boolean $assertionsDisabled = false;
    private static final int GENERATION = 1;
    private static final String TAG = "WindowLeakDetector";
    private static final String WINDOW_CLASS_NAME = "android.view.Window";
    private long windowClassId;
    private ClassCounter windowCounter;

    private WindowLeakDetector() {
    }

    public WindowLeakDetector(HeapGraph heapGraph) {
        this.windowClassId = heapGraph.findClassByName("android.view.Window").getObjectId();
        this.windowCounter = new ClassCounter();
    }

    @Override
    public long classId() {
        return this.windowClassId;
    }

    @Override
    public String className() {
        return "android.view.Window";
    }

    @Override
    public Class<?> clazz() {
        return Window.class;
    }

    @Override
    public int generation() {
        return 1;
    }

    @Override
    public ClassCounter instanceCount() {
        return this.windowCounter;
    }

    @Override
    public boolean isLeak(HeapObject.HeapInstance heapInstance) {
        if (this.VERBOSE_LOG) {
            KLog.i("WindowLeakDetector", "run isLeak");
        }
        this.windowCounter.instancesCount++;
        return false;
    }

    @Override
    public String leakReason() {
        return "Window";
    }
}
