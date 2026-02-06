package com.kwai.koom.javaoom.analysis;

import android.app.Activity;
import com.kwai.koom.javaoom.common.KLog;
import kshark.HeapField;
import kshark.HeapGraph;
import kshark.HeapObject;

public class ActivityLeakDetector extends LeakDetector {
    static final boolean $assertionsDisabled = false;
    private static final String ACTIVITY_CLASS_NAME = "android.app.Activity";
    private static final String DESTROYED_FIELD_NAME = "mDestroyed";
    private static final String FINISHED_FIELD_NAME = "mFinished";
    private static final String TAG = "ActivityLeakDetector";
    private long activityClassId;
    private ClassCounter activityCounter;

    private ActivityLeakDetector() {
    }

    public ActivityLeakDetector(HeapGraph heapGraph) {
        this.activityClassId = heapGraph.findClassByName("android.app.Activity").getObjectId();
        this.activityCounter = new ClassCounter();
    }

    @Override
    public long classId() {
        return this.activityClassId;
    }

    @Override
    public String className() {
        return "android.app.Activity";
    }

    @Override
    public Class<?> clazz() {
        return Activity.class;
    }

    @Override
    public ClassCounter instanceCount() {
        return this.activityCounter;
    }

    @Override
    public boolean isLeak(HeapObject.HeapInstance heapInstance) {
        if (this.VERBOSE_LOG) {
            KLog.i("ActivityLeakDetector", "run isLeak");
        }
        this.activityCounter.instancesCount++;
        HeapField heapField = heapInstance.get("android.app.Activity", "mDestroyed");
        HeapField heapField2 = heapInstance.get("android.app.Activity", "mFinished");
        if (heapField.getValue().getAsBoolean() == null || heapField2.getValue().getAsBoolean() == null) {
            KLog.e("ActivityLeakDetector", "ABNORMAL destroyField or finishedField is null");
            return false;
        }
        Boolean asBoolean = heapField.getValue().getAsBoolean();
        Boolean asBoolean2 = heapField2.getValue().getAsBoolean();
        boolean z = asBoolean.booleanValue() || asBoolean2.booleanValue();
        if (z) {
            if (this.VERBOSE_LOG) {
                KLog.e("ActivityLeakDetector", "activity leak : " + heapInstance.getInstanceClassName() + ", destroyed = " + asBoolean + ", finished = " + asBoolean2);
            }
            this.activityCounter.leakInstancesCount++;
        }
        return z;
    }

    @Override
    public String leakReason() {
        return "Activity Leak";
    }
}
