package com.kwai.koom.javaoom.analysis;

import com.kwai.koom.javaoom.common.KLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.sequences.Sequence;
import kshark.HeapObject;

class ClassHierarchyFetcher {
    private static final String TAG = "ClassHierarchyFetcher";
    private static ClassHierarchyFetcher instance;
    private Map<Long, List<ClassGeneration>> classGenerations = new HashMap();
    private Set<Integer> computeGenerations;

    static class ClassGeneration {
        int generation;
        long id;

        ClassGeneration() {
        }
    }

    private static String computeGenerationsToString(Set<Integer> set) {
        Iterator<Integer> it = set.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next() + ",";
        }
        return str;
    }

    private static Map<Long, List<ClassGeneration>> getClassGenerations() {
        return getInstance().classGenerations;
    }

    private static Set<Integer> getComputeGenerations() {
        return getInstance().computeGenerations;
    }

    public static long getIdOfGeneration(long j, int i) {
        List<ClassGeneration> list = getClassGenerations().get(Long.valueOf(j));
        if (list == null) {
            return 0L;
        }
        for (ClassGeneration classGeneration : list) {
            if (classGeneration.generation == i) {
                return classGeneration.id;
            }
        }
        return 0L;
    }

    private static ClassHierarchyFetcher getInstance() {
        ClassHierarchyFetcher classHierarchyFetcher = instance;
        if (classHierarchyFetcher != null) {
            return classHierarchyFetcher;
        }
        ClassHierarchyFetcher classHierarchyFetcher2 = new ClassHierarchyFetcher();
        instance = classHierarchyFetcher2;
        return classHierarchyFetcher2;
    }

    public static void initComputeGenerations(Set<Integer> set) {
        KLog.i("ClassHierarchyFetcher", "initComputeGenerations " + computeGenerationsToString(set));
        getInstance().computeGenerations = set;
    }

    public static void process(long j, Sequence<HeapObject.HeapClass> sequence) {
        if (getClassGenerations().get(Long.valueOf(j)) != null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HeapObject.HeapClass> it = sequence.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            it.next();
            i2++;
        }
        Set<Integer> computeGenerations = getComputeGenerations();
        for (HeapObject.HeapClass heapClass : sequence) {
            i++;
            for (Integer num : computeGenerations) {
                if (i == i2 - num.intValue()) {
                    ClassGeneration classGeneration = new ClassGeneration();
                    classGeneration.id = heapClass.getObjectId();
                    classGeneration.generation = num.intValue();
                    arrayList.add(classGeneration);
                }
            }
        }
        getClassGenerations().put(Long.valueOf(j), arrayList);
    }
}
