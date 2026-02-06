package com.kwai.koom.javaoom.analysis;

import android.app.Fragment;
import com.kwai.koom.javaoom.common.KLog;
import kshark.HeapField;
import kshark.HeapGraph;
import kshark.HeapObject;

public class FragmentLeakDetector extends LeakDetector {
    static final boolean $assertionsDisabled = false;
    private static final String ANDROIDX_FRAGMENT_CLASS_NAME = "androidx.fragment.app.Fragment";
    private static final String FRAGMENT_MANAGER_FIELD_NAME = "mFragmentManager";
    private static final String FRAGMENT_MCALLED_FIELD_NAME = "mCalled";
    private static final int GENERATION = 1;
    private static final String NATIVE_FRAGMENT_CLASS_NAME = "android.app.Fragment";
    private static final String SUPPORT_FRAGMENT_CLASS_NAME = "androidx.fragment.app.Fragment";
    private static final String TAG = "FragmentLeakDetector";
    private long fragmentClassId;
    private String fragmentClassName;
    private ClassCounter fragmentCounter;

    public FragmentLeakDetector(HeapGraph heapGraph) {
        HeapObject.HeapClass heapClassFindClassByName = heapGraph.findClassByName("androidx.fragment.app.Fragment");
        this.fragmentClassName = "androidx.fragment.app.Fragment";
        if (heapClassFindClassByName == null) {
            HeapObject.HeapClass heapClassFindClassByName2 = heapGraph.findClassByName("android.app.Fragment");
            this.fragmentClassName = "android.app.Fragment";
            heapClassFindClassByName = heapClassFindClassByName2;
        }
        if (heapClassFindClassByName == null) {
            heapClassFindClassByName = heapGraph.findClassByName("androidx.fragment.app.Fragment");
            this.fragmentClassName = "androidx.fragment.app.Fragment";
        }
        this.fragmentClassId = heapClassFindClassByName.getObjectId();
        this.fragmentCounter = new ClassCounter();
    }

    @Override
    public long classId() {
        return this.fragmentClassId;
    }

    @Override
    public String className() {
        return this.fragmentClassName;
    }

    @Override
    public Class<?> clazz() {
        return Fragment.class;
    }

    @Override
    public int generation() {
        return 1;
    }

    @Override
    public ClassCounter instanceCount() {
        return this.fragmentCounter;
    }

    @Override
    public boolean isLeak(HeapObject.HeapInstance heapInstance) {
        if (this.VERBOSE_LOG) {
            KLog.i("FragmentLeakDetector", "run isLeak");
        }
        this.fragmentCounter.instancesCount++;
        HeapField heapField = heapInstance.get(this.fragmentClassName, "mFragmentManager");
        boolean zBooleanValue = false;
        if (heapField != null && heapField.getValue().getAsObject() == null) {
            HeapField heapField2 = heapInstance.get(this.fragmentClassName, "mCalled");
            if (heapField2 == null || heapField2.getValue().getAsBoolean() == null) {
                KLog.e("FragmentLeakDetector", "ABNORMAL mCalledField is null");
                return false;
            }
            zBooleanValue = heapField2.getValue().getAsBoolean().booleanValue();
            if (zBooleanValue) {
                if (this.VERBOSE_LOG) {
                    KLog.e("FragmentLeakDetector", "fragment leak : " + heapInstance.getInstanceClassName());
                }
                this.fragmentCounter.leakInstancesCount++;
            }
        }
        return zBooleanValue;
    }

    @Override
    public String leakReason() {
        return "Fragment Leak";
    }
}
