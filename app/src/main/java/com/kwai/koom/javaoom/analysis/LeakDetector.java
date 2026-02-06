package com.kwai.koom.javaoom.analysis;

import com.kwai.koom.javaoom.common.KUtils;
import kshark.HeapObject;

public abstract class LeakDetector {
    boolean VERBOSE_LOG = true;
    int storedGeneration = 0;

    public abstract long classId();

    public abstract String className();

    public abstract Class<?> clazz();

    public int generation() {
        int i = this.storedGeneration;
        if (i != 0) {
            return i;
        }
        int iComputeGenerations = KUtils.computeGenerations(clazz());
        this.storedGeneration = iComputeGenerations;
        return iComputeGenerations;
    }

    public abstract ClassCounter instanceCount();

    abstract boolean isLeak(HeapObject.HeapInstance heapInstance);

    boolean isSubClass(long j) {
        return ClassHierarchyFetcher.getIdOfGeneration(j, generation()) == classId();
    }

    public abstract String leakReason();
}
