package com.kwai.koom.javaoom.analysis;

import com.kwai.koom.javaoom.common.KLog;
import kshark.HeapGraph;
import kshark.HeapObject;

public class NativeAllocationRegistryLeakDetector extends LeakDetector {
    private static final int GENERATION = 1;
    private static final String NATIVE_ALLOCATION_CLASS_NAME = "libcore.util.NativeAllocationRegistry";
    private static final String NATIVE_ALLOCATION_CLEANER_THUNK_CLASS_NAME = "libcore.util.NativeAllocationRegistry$CleanerThunk";
    private static final String TAG = "NativeAllocation";
    private long nativeAllocationClassId;
    private ClassCounter nativeAllocationCounter;
    private long nativeAllocationThunkClassId;
    private boolean supported;

    private NativeAllocationRegistryLeakDetector() {
    }

    public NativeAllocationRegistryLeakDetector(HeapGraph heapGraph) {
        if (this.VERBOSE_LOG) {
            KLog.i("NativeAllocation", "run isLeak");
        }
        HeapObject.HeapClass heapClassFindClassByName = heapGraph.findClassByName("libcore.util.NativeAllocationRegistry");
        HeapObject.HeapClass heapClassFindClassByName2 = heapGraph.findClassByName("libcore.util.NativeAllocationRegistry$CleanerThunk");
        if (heapClassFindClassByName != null) {
            this.nativeAllocationClassId = heapClassFindClassByName.getObjectId();
        } else {
            this.supported = false;
        }
        if (heapClassFindClassByName2 != null) {
            this.nativeAllocationThunkClassId = heapClassFindClassByName2.getObjectId();
        } else {
            this.supported = false;
        }
        this.nativeAllocationCounter = new ClassCounter();
        this.supported = true;
    }

    @Override
    public long classId() {
        return this.nativeAllocationClassId;
    }

    @Override
    public String className() {
        return "libcore.util.NativeAllocationRegistry";
    }

    @Override
    public Class<?> clazz() {
        return null;
    }

    @Override
    public int generation() {
        return 1;
    }

    @Override
    public ClassCounter instanceCount() {
        return this.nativeAllocationCounter;
    }

    @Override
    public boolean isLeak(HeapObject.HeapInstance heapInstance) {
        if (!this.supported) {
            return false;
        }
        this.nativeAllocationCounter.instancesCount++;
        return false;
    }

    @Override
    public boolean isSubClass(long j) {
        if (!this.supported) {
            return false;
        }
        long idOfGeneration = ClassHierarchyFetcher.getIdOfGeneration(j, generation());
        return idOfGeneration == this.nativeAllocationClassId || idOfGeneration == this.nativeAllocationThunkClassId;
    }

    @Override
    public String leakReason() {
        return "NativeAllocation";
    }
}
