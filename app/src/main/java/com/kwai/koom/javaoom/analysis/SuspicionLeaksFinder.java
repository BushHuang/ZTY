package com.kwai.koom.javaoom.analysis;

import android.util.Pair;
import com.kwai.koom.javaoom.common.KConstants;
import com.kwai.koom.javaoom.common.KHeapFile;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.report.HeapAnalyzeReporter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kshark.AndroidReferenceMatchers;
import kshark.ApplicationLeak;
import kshark.GcRoot;
import kshark.HeapAnalyzer;
import kshark.HeapGraph;
import kshark.HeapObject;
import kshark.Hprof;
import kshark.HprofHeapGraph;
import kshark.LibraryLeak;
import kshark.OnAnalysisProgressListener;

class SuspicionLeaksFinder {
    private static final int SAME_CLASS_LEAK_OBJECT_GC_PATH_THRESHOLD = 45;
    private static final String TAG = "LeaksFinder";
    private HeapGraph heapGraph;
    private KHeapFile.Hprof hprofFile;
    public Map<Long, String> leakReasonTable;
    private Set<Long> leakingObjects = new HashSet();
    private List<LeakDetector> leakDetectors = new ArrayList();
    private Set<Integer> computeGenerations = new HashSet();

    public SuspicionLeaksFinder(KHeapFile.Hprof hprof) {
        this.hprofFile = hprof;
    }

    private void addDetector(LeakDetector leakDetector) {
        this.leakDetectors.add(leakDetector);
        this.computeGenerations.add(Integer.valueOf(leakDetector.generation()));
    }

    private boolean buildIndex() {
        KLog.i("LeaksFinder", "build index file:" + this.hprofFile.path);
        if (this.hprofFile.file() != null && this.hprofFile.file().exists()) {
            this.heapGraph = HprofHeapGraph.INSTANCE.indexHprof(Hprof.INSTANCE.open(this.hprofFile.file()), null, SetsKt.setOf((Object[]) new KClass[]{Reflection.getOrCreateKotlinClass(GcRoot.JniGlobal.class), Reflection.getOrCreateKotlinClass(GcRoot.JniLocal.class), Reflection.getOrCreateKotlinClass(GcRoot.NativeStack.class), Reflection.getOrCreateKotlinClass(GcRoot.StickyClass.class), Reflection.getOrCreateKotlinClass(GcRoot.ThreadBlock.class), Reflection.getOrCreateKotlinClass(GcRoot.ThreadObject.class), Reflection.getOrCreateKotlinClass(GcRoot.JniMonitor.class)}));
            return true;
        }
        KLog.e("LeaksFinder", "hprof file is not exists : " + this.hprofFile.path + "!!");
        return false;
    }

    private void findObjectArrayLeaks() {
        for (HeapObject.HeapObjectArray heapObjectArray : this.heapGraph.getObjectArrays()) {
            int arrayLength = heapObjectArray.getArrayLength();
            if (arrayLength >= 262144) {
                KLog.i("LeaksFinder", "object arrayName:" + heapObjectArray.getArrayClassName() + " objectId:" + heapObjectArray.getObjectId());
                this.leakingObjects.add(Long.valueOf(heapObjectArray.getObjectId()));
                this.leakReasonTable.put(Long.valueOf(heapObjectArray.getObjectId()), "object array size over threshold:" + arrayLength);
            }
        }
    }

    private void findPrimitiveArrayLeaks() {
        for (HeapObject.HeapPrimitiveArray heapPrimitiveArray : this.heapGraph.getPrimitiveArrays()) {
            int arrayLength = heapPrimitiveArray.getArrayLength();
            if (arrayLength >= 262144) {
                KLog.e("LeaksFinder", "primitive arrayName:" + heapPrimitiveArray.getArrayClassName() + " typeName:" + heapPrimitiveArray.getPrimitiveType().toString() + " objectId:" + (heapPrimitiveArray.getObjectId() & 4294967295L) + " arraySize:" + arrayLength);
                this.leakingObjects.add(Long.valueOf(heapPrimitiveArray.getObjectId()));
                this.leakReasonTable.put(Long.valueOf(heapPrimitiveArray.getObjectId()), "primitive array size over threshold:" + arrayLength + "," + (arrayLength / KConstants.Bytes.KB) + "KB");
            }
        }
    }

    private void initLeakDetectors() {
        addDetector(new ActivityLeakDetector(this.heapGraph));
        addDetector(new FragmentLeakDetector(this.heapGraph));
        addDetector(new BitmapLeakDetector(this.heapGraph));
        addDetector(new NativeAllocationRegistryLeakDetector(this.heapGraph));
        addDetector(new WindowLeakDetector(this.heapGraph));
        ClassHierarchyFetcher.initComputeGenerations(this.computeGenerations);
        this.leakReasonTable = new HashMap();
    }

    public Pair<List<ApplicationLeak>, List<LibraryLeak>> find() {
        if (!buildIndex()) {
            return null;
        }
        initLeakDetectors();
        findLeaks();
        return findPath();
    }

    public void findLeaks() {
        KLog.i("LeaksFinder", "start find leaks");
        for (HeapObject.HeapInstance heapInstance : this.heapGraph.getInstances()) {
            if (!heapInstance.getIsPrimitiveWrapper()) {
                ClassHierarchyFetcher.process(heapInstance.getInstanceClassId(), heapInstance.getInstanceClass().getClassHierarchy());
                for (LeakDetector leakDetector : this.leakDetectors) {
                    if (leakDetector.isSubClass(heapInstance.getInstanceClassId()) && leakDetector.isLeak(heapInstance) && leakDetector.instanceCount().leakInstancesCount <= 45) {
                        this.leakingObjects.add(Long.valueOf(heapInstance.getObjectId()));
                        this.leakReasonTable.put(Long.valueOf(heapInstance.getObjectId()), leakDetector.leakReason());
                    }
                }
            }
        }
        HeapAnalyzeReporter.addClassInfo(this.leakDetectors);
        findPrimitiveArrayLeaks();
        findObjectArrayLeaks();
    }

    public Pair<List<ApplicationLeak>, List<LibraryLeak>> findPath() {
        KLog.i("LeaksFinder", "findPath object size:" + this.leakingObjects.size());
        kotlin.Pair<List<ApplicationLeak>, List<LibraryLeak>> pairFindLeaks = new HeapAnalyzer(new OnAnalysisProgressListener() {
            @Override
            public final void onAnalysisProgress(OnAnalysisProgressListener.Step step) {
                KLog.i("LeaksFinder", "step:" + step.name());
            }
        }).findLeaks(new HeapAnalyzer.FindLeakInput(this.heapGraph, AndroidReferenceMatchers.INSTANCE.getAppDefaults(), false, Collections.emptyList()), this.leakingObjects, true);
        return new Pair<>(pairFindLeaks.getFirst(), pairFindLeaks.getSecond());
    }

    public Map<Long, String> getLeakReasonTable() {
        return this.leakReasonTable;
    }
}
