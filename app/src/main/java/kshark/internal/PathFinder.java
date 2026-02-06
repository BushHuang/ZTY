package kshark.internal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;
import kshark.GcRoot;
import kshark.HeapField;
import kshark.HeapGraph;
import kshark.HeapObject;
import kshark.HeapValue;
import kshark.IgnoredReferenceMatcher;
import kshark.LeakTraceReference;
import kshark.LibraryLeakReferenceMatcher;
import kshark.OnAnalysisProgressListener;
import kshark.PrimitiveType;
import kshark.ReferenceMatcher;
import kshark.ReferencePattern;
import kshark.SharkLog;
import kshark.internal.ReferencePathNode;
import kshark.internal.hppc.LongLongScatterMap;
import kshark.internal.hppc.LongScatterSet;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002?@B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001c\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u001c2\u0006\u0010\u001d\u001a\u00020\nJ\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"0\u0007H\u0002J\u0014\u0010%\u001a\u00020\n*\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J(\u0010)\u001a\u00020**\u00020&2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020\u00102\b\b\u0002\u0010,\u001a\u00020\u0010H\u0002J\f\u0010-\u001a\u00020**\u00020&H\u0002J\f\u0010\u0019\u001a\u00020\u001a*\u00020&H\u0002J\f\u0010.\u001a\u00020(*\u00020&H\u0002J\u001c\u0010/\u001a\u00020**\u00020&2\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\nH\u0002J\u0014\u00102\u001a\u00020**\u00020&2\u0006\u00100\u001a\u00020\u0013H\u0002J$\u00103\u001a\u00020**\u00020&2\u0006\u00104\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\nH\u0002J\u001c\u00105\u001a\u00020**\u00020&2\u0006\u00106\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0013H\u0002J\u001c\u00107\u001a\u00020**\u00020&2\u0006\u00108\u001a\u0002092\u0006\u00104\u001a\u00020(H\u0002J\u001c\u0010:\u001a\u00020**\u00020&2\u0006\u0010;\u001a\u00020 2\u0006\u00104\u001a\u00020(H\u0002J\u001c\u0010<\u001a\u00020**\u00020&2\u0006\u0010=\u001a\u00020>2\u0006\u00104\u001a\u00020(H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0016\u001a\u001a\u0012\u0004\u0012\u00020\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lkshark/internal/PathFinder;", "", "graph", "Lkshark/HeapGraph;", "listener", "Lkshark/OnAnalysisProgressListener;", "referenceMatchers", "", "Lkshark/ReferenceMatcher;", "enableSameInstanceThreshold", "", "(Lkshark/HeapGraph;Lkshark/OnAnalysisProgressListener;Ljava/util/List;Z)V", "SAME_INSTANCE_THRESHOLD", "", "fieldNameByClassName", "", "", "instanceCountMap", "", "", "", "jniGlobalReferenceMatchers", "staticFieldNameByClassName", "threadNameReferenceMatchers", "determineSizeOfObjectInstances", "findPathsFromGcRoots", "Lkshark/internal/PathFinder$PathFindingResults;", "leakingObjectIds", "", "computeRetainedHeapSize", "isOverThresholdInstance", "graphObject", "Lkshark/HeapObject$HeapInstance;", "sortedGcRoots", "Lkotlin/Pair;", "Lkshark/HeapObject;", "Lkshark/GcRoot;", "checkSeen", "Lkshark/internal/PathFinder$State;", "node", "Lkshark/internal/ReferencePathNode;", "enqueue", "", "heapClassName", "fieldName", "enqueueGcRoots", "poll", "undominate", "objectId", "neverEnqueued", "undominateWithSkips", "updateDominator", "parent", "updateDominatorWithSkips", "parentObjectId", "visitClassRecord", "heapClass", "Lkshark/HeapObject$HeapClass;", "visitInstance", "instance", "visitObjectArray", "objectArray", "Lkshark/HeapObject$HeapObjectArray;", "PathFindingResults", "State", "shark"}, k = 1, mv = {1, 1, 15})
public final class PathFinder {
    private final int SAME_INSTANCE_THRESHOLD;
    private final boolean enableSameInstanceThreshold;
    private final Map<String, Map<String, ReferenceMatcher>> fieldNameByClassName;
    private final HeapGraph graph;
    private Map<Long, Short> instanceCountMap;
    private final Map<String, ReferenceMatcher> jniGlobalReferenceMatchers;
    private final OnAnalysisProgressListener listener;
    private final Map<String, Map<String, ReferenceMatcher>> staticFieldNameByClassName;
    private final Map<String, ReferenceMatcher> threadNameReferenceMatchers;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lkshark/internal/PathFinder$PathFindingResults;", "", "pathsToLeakingObjects", "", "Lkshark/internal/ReferencePathNode;", "dominatedObjectIds", "Lkshark/internal/hppc/LongLongScatterMap;", "(Ljava/util/List;Lkshark/internal/hppc/LongLongScatterMap;)V", "getDominatedObjectIds", "()Lkshark/internal/hppc/LongLongScatterMap;", "getPathsToLeakingObjects", "()Ljava/util/List;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class PathFindingResults {
        private final LongLongScatterMap dominatedObjectIds;
        private final List<ReferencePathNode> pathsToLeakingObjects;

        public PathFindingResults(List<? extends ReferencePathNode> list, LongLongScatterMap longLongScatterMap) {
            Intrinsics.checkParameterIsNotNull(list, "pathsToLeakingObjects");
            Intrinsics.checkParameterIsNotNull(longLongScatterMap, "dominatedObjectIds");
            this.pathsToLeakingObjects = list;
            this.dominatedObjectIds = longLongScatterMap;
        }

        public final LongLongScatterMap getDominatedObjectIds() {
            return this.dominatedObjectIds;
        }

        public final List<ReferencePathNode> getPathsToLeakingObjects() {
            return this.pathsToLeakingObjects;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR!\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u001cj\b\u0012\u0004\u0012\u00020\u0004`\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR!\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u001cj\b\u0012\u0004\u0012\u00020\u0004`\u001d¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"Lkshark/internal/PathFinder$State;", "", "leakingObjectIds", "", "", "sizeOfObjectInstances", "", "computeRetainedHeapSize", "", "(Ljava/util/Set;IZ)V", "getComputeRetainedHeapSize", "()Z", "dominatedObjectIds", "Lkshark/internal/hppc/LongLongScatterMap;", "getDominatedObjectIds", "()Lkshark/internal/hppc/LongLongScatterMap;", "getLeakingObjectIds", "()Ljava/util/Set;", "queuesNotEmpty", "getQueuesNotEmpty", "getSizeOfObjectInstances", "()I", "toVisitLastQueue", "Ljava/util/Deque;", "Lkshark/internal/ReferencePathNode;", "getToVisitLastQueue", "()Ljava/util/Deque;", "toVisitLastSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getToVisitLastSet", "()Ljava/util/HashSet;", "toVisitQueue", "getToVisitQueue", "toVisitSet", "getToVisitSet", "visitedSet", "Lkshark/internal/hppc/LongScatterSet;", "getVisitedSet", "()Lkshark/internal/hppc/LongScatterSet;", "shark"}, k = 1, mv = {1, 1, 15})
    private static final class State {
        private final boolean computeRetainedHeapSize;
        private final LongLongScatterMap dominatedObjectIds;
        private final Set<Long> leakingObjectIds;
        private final int sizeOfObjectInstances;
        private final Deque<ReferencePathNode> toVisitLastQueue;
        private final HashSet<Long> toVisitLastSet;
        private final Deque<ReferencePathNode> toVisitQueue;
        private final HashSet<Long> toVisitSet;
        private final LongScatterSet visitedSet;

        public State(Set<Long> set, int i, boolean z) {
            Intrinsics.checkParameterIsNotNull(set, "leakingObjectIds");
            this.leakingObjectIds = set;
            this.sizeOfObjectInstances = i;
            this.computeRetainedHeapSize = z;
            this.toVisitQueue = new ArrayDeque();
            this.toVisitLastQueue = new ArrayDeque();
            this.toVisitSet = new HashSet<>();
            this.toVisitLastSet = new HashSet<>();
            this.visitedSet = new LongScatterSet();
            this.dominatedObjectIds = new LongLongScatterMap();
        }

        public final boolean getComputeRetainedHeapSize() {
            return this.computeRetainedHeapSize;
        }

        public final LongLongScatterMap getDominatedObjectIds() {
            return this.dominatedObjectIds;
        }

        public final Set<Long> getLeakingObjectIds() {
            return this.leakingObjectIds;
        }

        public final boolean getQueuesNotEmpty() {
            return (this.toVisitQueue.isEmpty() ^ true) || (this.toVisitLastQueue.isEmpty() ^ true);
        }

        public final int getSizeOfObjectInstances() {
            return this.sizeOfObjectInstances;
        }

        public final Deque<ReferencePathNode> getToVisitLastQueue() {
            return this.toVisitLastQueue;
        }

        public final HashSet<Long> getToVisitLastSet() {
            return this.toVisitLastSet;
        }

        public final Deque<ReferencePathNode> getToVisitQueue() {
            return this.toVisitQueue;
        }

        public final HashSet<Long> getToVisitSet() {
            return this.toVisitSet;
        }

        public final LongScatterSet getVisitedSet() {
            return this.visitedSet;
        }
    }

    public PathFinder(HeapGraph heapGraph, OnAnalysisProgressListener onAnalysisProgressListener, List<? extends ReferenceMatcher> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(heapGraph, "graph");
        Intrinsics.checkParameterIsNotNull(onAnalysisProgressListener, "listener");
        Intrinsics.checkParameterIsNotNull(list, "referenceMatchers");
        this.graph = heapGraph;
        this.listener = onAnalysisProgressListener;
        this.enableSameInstanceThreshold = z;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        ArrayList<ReferenceMatcher> arrayList = new ArrayList();
        for (Object obj : list) {
            ReferenceMatcher referenceMatcher = (ReferenceMatcher) obj;
            if ((referenceMatcher instanceof IgnoredReferenceMatcher) || ((referenceMatcher instanceof LibraryLeakReferenceMatcher) && ((LibraryLeakReferenceMatcher) referenceMatcher).getPatternApplies().invoke(this.graph).booleanValue())) {
                arrayList.add(obj);
            }
        }
        for (ReferenceMatcher referenceMatcher2 : arrayList) {
            ReferencePattern pattern = referenceMatcher2.getPattern();
            if (pattern instanceof ReferencePattern.JavaLocalPattern) {
                linkedHashMap3.put(((ReferencePattern.JavaLocalPattern) pattern).getThreadName(), referenceMatcher2);
            } else if (pattern instanceof ReferencePattern.StaticFieldPattern) {
                ReferencePattern.StaticFieldPattern staticFieldPattern = (ReferencePattern.StaticFieldPattern) pattern;
                LinkedHashMap linkedHashMap5 = (Map) linkedHashMap2.get(staticFieldPattern.getClassName());
                if (linkedHashMap5 == null) {
                    linkedHashMap5 = new LinkedHashMap();
                    linkedHashMap2.put(staticFieldPattern.getClassName(), linkedHashMap5);
                }
                linkedHashMap5.put(staticFieldPattern.getFieldName(), referenceMatcher2);
            } else if (pattern instanceof ReferencePattern.InstanceFieldPattern) {
                ReferencePattern.InstanceFieldPattern instanceFieldPattern = (ReferencePattern.InstanceFieldPattern) pattern;
                LinkedHashMap linkedHashMap6 = (Map) linkedHashMap.get(instanceFieldPattern.getClassName());
                if (linkedHashMap6 == null) {
                    linkedHashMap6 = new LinkedHashMap();
                    linkedHashMap.put(instanceFieldPattern.getClassName(), linkedHashMap6);
                }
                linkedHashMap6.put(instanceFieldPattern.getFieldName(), referenceMatcher2);
            } else if (pattern instanceof ReferencePattern.NativeGlobalVariablePattern) {
                linkedHashMap4.put(((ReferencePattern.NativeGlobalVariablePattern) pattern).getClassName(), referenceMatcher2);
            }
        }
        this.fieldNameByClassName = linkedHashMap;
        this.staticFieldNameByClassName = linkedHashMap2;
        this.threadNameReferenceMatchers = linkedHashMap3;
        this.jniGlobalReferenceMatchers = linkedHashMap4;
        this.SAME_INSTANCE_THRESHOLD = 1024;
        this.instanceCountMap = new LinkedHashMap();
    }

    private final boolean checkSeen(State state, ReferencePathNode referencePathNode) {
        return !state.getVisitedSet().add(referencePathNode.getObjectId());
    }

    private final int determineSizeOfObjectInstances(HeapGraph graph) {
        HeapObject.HeapClass heapClassFindClassByName = graph.findClassByName("java.lang.Object");
        if (heapClassFindClassByName == null) {
            return 0;
        }
        int fieldsByteSize = heapClassFindClassByName.readFieldsByteSize();
        int identifierByteSize = graph.getIdentifierByteSize() + PrimitiveType.INT.getByteSize();
        if (fieldsByteSize == identifierByteSize) {
            return identifierByteSize;
        }
        return 0;
    }

    private final void enqueue(State state, ReferencePathNode referencePathNode, String str, String str2) throws IllegalArgumentException {
        boolean z;
        if (referencePathNode.getObjectId() == 0 || state.getVisitedSet().contains(referencePathNode.getObjectId()) || state.getToVisitSet().contains(Long.valueOf(referencePathNode.getObjectId()))) {
            return;
        }
        boolean z2 = false;
        if ((referencePathNode instanceof ReferencePathNode.LibraryLeakNode) || ((referencePathNode instanceof ReferencePathNode.RootNode) && (((ReferencePathNode.RootNode) referencePathNode).getGcRoot() instanceof GcRoot.ThreadObject))) {
            z = true;
        } else {
            if (referencePathNode instanceof ReferencePathNode.ChildNode.NormalNode) {
                ReferencePathNode.ChildNode.NormalNode normalNode = (ReferencePathNode.ChildNode.NormalNode) referencePathNode;
                if (!(normalNode.getParent() instanceof ReferencePathNode.RootNode) || !(((ReferencePathNode.RootNode) normalNode.getParent()).getGcRoot() instanceof GcRoot.JavaFrame)) {
                }
            }
            z = false;
        }
        if (state.getToVisitLastSet().contains(Long.valueOf(referencePathNode.getObjectId()))) {
            if (z) {
                return;
            }
            state.getToVisitQueue().add(referencePathNode);
            state.getToVisitSet().add(Long.valueOf(referencePathNode.getObjectId()));
            for (ReferencePathNode referencePathNode2 : state.getToVisitLastQueue()) {
                if (referencePathNode2.getObjectId() == referencePathNode.getObjectId()) {
                    state.getToVisitLastQueue().remove(referencePathNode2);
                    state.getToVisitLastSet().remove(Long.valueOf(referencePathNode.getObjectId()));
                    return;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        if (!state.getLeakingObjectIds().contains(Long.valueOf(referencePathNode.getObjectId()))) {
            HeapObject heapObjectFindObjectById = this.graph.findObjectById(referencePathNode.getObjectId());
            if (!(heapObjectFindObjectById instanceof HeapObject.HeapClass)) {
                if (heapObjectFindObjectById instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObjectFindObjectById;
                    if (heapInstance.getIsPrimitiveWrapper() || heapInstance.getInstanceClass().getInstanceByteSize() <= state.getSizeOfObjectInstances() || isOverThresholdInstance(heapInstance)) {
                        z2 = true;
                    }
                } else {
                    if (heapObjectFindObjectById instanceof HeapObject.HeapObjectArray) {
                        if (((HeapObject.HeapObjectArray) heapObjectFindObjectById).getIsPrimitiveWrapperArray()) {
                        }
                    } else if (!(heapObjectFindObjectById instanceof HeapObject.HeapPrimitiveArray)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    z2 = true;
                }
            }
            if (z2) {
                return;
            }
        }
        if (z) {
            state.getToVisitLastQueue().add(referencePathNode);
            state.getToVisitLastSet().add(Long.valueOf(referencePathNode.getObjectId()));
        } else {
            state.getToVisitQueue().add(referencePathNode);
            state.getToVisitSet().add(Long.valueOf(referencePathNode.getObjectId()));
        }
    }

    static void enqueue$default(PathFinder pathFinder, State state, ReferencePathNode referencePathNode, String str, String str2, int i, Object obj) throws IllegalArgumentException {
        if ((i & 2) != 0) {
            str = "";
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        pathFinder.enqueue(state, referencePathNode, str, str2);
    }

    private final void enqueueGcRoots(final State state) throws IllegalArgumentException {
        ReferenceMatcher referenceMatcher;
        SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
        if (logger != null) {
            logger.d("start enqueueGcRoots");
        }
        SharkLog.Logger logger2 = SharkLog.INSTANCE.getLogger();
        if (logger2 != null) {
            logger2.d("start sortedGcRoots");
        }
        List<Pair<HeapObject, GcRoot>> listSortedGcRoots = sortedGcRoots();
        SharkLog.Logger logger3 = SharkLog.INSTANCE.getLogger();
        if (logger3 != null) {
            logger3.d("end sortedGcRoots");
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        Iterator<T> it = listSortedGcRoots.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            HeapObject heapObject = (HeapObject) pair.component1();
            GcRoot gcRoot = (GcRoot) pair.component2();
            if (state.getComputeRetainedHeapSize()) {
                undominateWithSkips(state, gcRoot.getId());
            }
            if (gcRoot instanceof GcRoot.ThreadObject) {
                Integer numValueOf = Integer.valueOf(((GcRoot.ThreadObject) gcRoot).getThreadSerialNumber());
                HeapObject.HeapInstance asInstance = heapObject.getAsInstance();
                if (asInstance == null) {
                    Intrinsics.throwNpe();
                }
                linkedHashMap2.put(numValueOf, TuplesKt.to(asInstance, gcRoot));
                enqueue$default(this, state, new ReferencePathNode.RootNode.NormalRootNode(gcRoot.getId(), gcRoot), null, null, 6, null);
            } else if (gcRoot instanceof GcRoot.JavaFrame) {
                Pair pair2 = (Pair) linkedHashMap2.get(Integer.valueOf(((GcRoot.JavaFrame) gcRoot).getThreadSerialNumber()));
                if (pair2 == null) {
                    enqueue$default(this, state, new ReferencePathNode.RootNode.NormalRootNode(gcRoot.getId(), gcRoot), null, null, 6, null);
                } else {
                    final HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) pair2.component1();
                    GcRoot.ThreadObject threadObject = (GcRoot.ThreadObject) pair2.component2();
                    String strInvoke = (String) linkedHashMap.get(heapInstance);
                    if (strInvoke == null) {
                        strInvoke = new Function0<String>() {
                            {
                                super(0);
                            }

                            @Override
                            public final String invoke() {
                                String asJavaString;
                                HeapValue value;
                                HeapField heapField = heapInstance.get(Reflection.getOrCreateKotlinClass(Thread.class), "name");
                                if (heapField == null || (value = heapField.getValue()) == null || (asJavaString = value.readAsJavaString()) == null) {
                                    asJavaString = "";
                                }
                                linkedHashMap.put(heapInstance, asJavaString);
                                return asJavaString;
                            }
                        }.invoke();
                    }
                    ReferenceMatcher referenceMatcher2 = this.threadNameReferenceMatchers.get(strInvoke);
                    if (!(referenceMatcher2 instanceof IgnoredReferenceMatcher)) {
                        ReferencePathNode.RootNode.NormalRootNode normalRootNode = new ReferencePathNode.RootNode.NormalRootNode(threadObject.getId(), gcRoot);
                        LeakTraceReference.ReferenceType referenceType = LeakTraceReference.ReferenceType.LOCAL;
                        enqueue$default(this, state, referenceMatcher2 instanceof LibraryLeakReferenceMatcher ? new ReferencePathNode.ChildNode.LibraryLeakChildNode(gcRoot.getId(), normalRootNode, referenceType, "", (LibraryLeakReferenceMatcher) referenceMatcher2, "") : new ReferencePathNode.ChildNode.NormalNode(gcRoot.getId(), normalRootNode, referenceType, "", ""), null, null, 6, null);
                    }
                }
            } else if (gcRoot instanceof GcRoot.JniGlobal) {
                if (heapObject instanceof HeapObject.HeapClass) {
                    referenceMatcher = this.jniGlobalReferenceMatchers.get(((HeapObject.HeapClass) heapObject).getName());
                } else if (heapObject instanceof HeapObject.HeapInstance) {
                    referenceMatcher = this.jniGlobalReferenceMatchers.get(((HeapObject.HeapInstance) heapObject).getInstanceClassName());
                } else if (heapObject instanceof HeapObject.HeapObjectArray) {
                    referenceMatcher = this.jniGlobalReferenceMatchers.get(((HeapObject.HeapObjectArray) heapObject).getArrayClassName());
                } else {
                    if (!(heapObject instanceof HeapObject.HeapPrimitiveArray)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    referenceMatcher = this.jniGlobalReferenceMatchers.get(((HeapObject.HeapPrimitiveArray) heapObject).getArrayClassName());
                }
                if (!(referenceMatcher instanceof IgnoredReferenceMatcher)) {
                    if (referenceMatcher instanceof LibraryLeakReferenceMatcher) {
                        enqueue$default(this, state, new ReferencePathNode.RootNode.LibraryLeakRootNode(gcRoot.getId(), gcRoot, (LibraryLeakReferenceMatcher) referenceMatcher), null, null, 6, null);
                    } else {
                        enqueue$default(this, state, new ReferencePathNode.RootNode.NormalRootNode(gcRoot.getId(), gcRoot), null, null, 6, null);
                    }
                }
            } else {
                enqueue$default(this, state, new ReferencePathNode.RootNode.NormalRootNode(gcRoot.getId(), gcRoot), null, null, 6, null);
            }
        }
        SharkLog.Logger logger4 = SharkLog.INSTANCE.getLogger();
        if (logger4 != null) {
            logger4.d("end enqueueGcRoots");
        }
    }

    private final PathFindingResults findPathsFromGcRoots(State state) throws IllegalArgumentException {
        SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
        if (logger != null) {
            logger.d("start findPathsFromGcRoots");
        }
        enqueueGcRoots(state);
        ArrayList arrayList = new ArrayList();
        while (state.getQueuesNotEmpty()) {
            ReferencePathNode referencePathNodePoll = poll(state);
            if (checkSeen(state, referencePathNodePoll)) {
                throw new IllegalStateException("Node " + referencePathNodePoll + " objectId=" + referencePathNodePoll.getObjectId() + " should not be enqueued when already visited or enqueued");
            }
            if (state.getLeakingObjectIds().contains(Long.valueOf(referencePathNodePoll.getObjectId()))) {
                arrayList.add(referencePathNodePoll);
                if (arrayList.size() == state.getLeakingObjectIds().size()) {
                    if (!state.getComputeRetainedHeapSize()) {
                        break;
                    }
                    this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.FINDING_DOMINATORS);
                }
            }
            HeapObject heapObjectFindObjectById = this.graph.findObjectById(referencePathNodePoll.getObjectId());
            if (heapObjectFindObjectById instanceof HeapObject.HeapClass) {
                visitClassRecord(state, (HeapObject.HeapClass) heapObjectFindObjectById, referencePathNodePoll);
            } else if (heapObjectFindObjectById instanceof HeapObject.HeapInstance) {
                visitInstance(state, (HeapObject.HeapInstance) heapObjectFindObjectById, referencePathNodePoll);
            } else if (heapObjectFindObjectById instanceof HeapObject.HeapObjectArray) {
                visitObjectArray(state, (HeapObject.HeapObjectArray) heapObjectFindObjectById, referencePathNodePoll);
            }
        }
        SharkLog.Logger logger2 = SharkLog.INSTANCE.getLogger();
        if (logger2 != null) {
            logger2.d("end findPathsFromGcRoots");
        }
        return new PathFindingResults(arrayList, state.getDominatedObjectIds());
    }

    private final boolean isOverThresholdInstance(HeapObject.HeapInstance graphObject) {
        if (!this.enableSameInstanceThreshold || kotlin.text.StringsKt.startsWith$default(graphObject.getInstanceClassName(), "java.util", false, 2, (Object) null) || kotlin.text.StringsKt.startsWith$default(graphObject.getInstanceClassName(), "android.util", false, 2, (Object) null) || kotlin.text.StringsKt.startsWith$default(graphObject.getInstanceClassName(), "java.lang.String", false, 2, (Object) null)) {
            return false;
        }
        Short sh = this.instanceCountMap.get(Long.valueOf(graphObject.getInstanceClassId()));
        if (sh == null) {
            sh = (short) 0;
        }
        if (sh.shortValue() < this.SAME_INSTANCE_THRESHOLD) {
            this.instanceCountMap.put(Long.valueOf(graphObject.getInstanceClassId()), Short.valueOf((short) (sh.shortValue() + 1)));
        }
        return sh.shortValue() >= this.SAME_INSTANCE_THRESHOLD;
    }

    private final ReferencePathNode poll(State state) {
        if (state.getToVisitQueue().isEmpty()) {
            ReferencePathNode referencePathNodePoll = state.getToVisitLastQueue().poll();
            state.getToVisitLastSet().remove(Long.valueOf(referencePathNodePoll.getObjectId()));
            Intrinsics.checkExpressionValueIsNotNull(referencePathNodePoll, "removedNode");
            return referencePathNodePoll;
        }
        ReferencePathNode referencePathNodePoll2 = state.getToVisitQueue().poll();
        state.getToVisitSet().remove(Long.valueOf(referencePathNodePoll2.getObjectId()));
        Intrinsics.checkExpressionValueIsNotNull(referencePathNodePoll2, "removedNode");
        return referencePathNodePoll2;
    }

    private final List<Pair<HeapObject, GcRoot>> sortedGcRoots() {
        final PathFinder$sortedGcRoots$rootClassName$1 pathFinder$sortedGcRoots$rootClassName$1 = new Function1<HeapObject, String>() {
            @Override
            public final String invoke(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "graphObject");
                if (heapObject instanceof HeapObject.HeapClass) {
                    return ((HeapObject.HeapClass) heapObject).getName();
                }
                if (heapObject instanceof HeapObject.HeapInstance) {
                    return ((HeapObject.HeapInstance) heapObject).getInstanceClassName();
                }
                if (heapObject instanceof HeapObject.HeapObjectArray) {
                    return ((HeapObject.HeapObjectArray) heapObject).getArrayClassName();
                }
                if (heapObject instanceof HeapObject.HeapPrimitiveArray) {
                    return ((HeapObject.HeapPrimitiveArray) heapObject).getArrayClassName();
                }
                throw new NoWhenBranchMatchedException();
            }
        };
        List<GcRoot> gcRoots = this.graph.getGcRoots();
        ArrayList arrayList = new ArrayList();
        for (Object obj : gcRoots) {
            if (this.graph.objectExists(((GcRoot) obj).getId())) {
                arrayList.add(obj);
            }
        }
        ArrayList<GcRoot> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (GcRoot gcRoot : arrayList2) {
            arrayList3.add(TuplesKt.to(this.graph.findObjectById(gcRoot.getId()), gcRoot));
        }
        return CollectionsKt.sortedWith(arrayList3, new Comparator<Pair<? extends HeapObject, ? extends GcRoot>>() {
            @Override
            public final int compare(Pair<? extends HeapObject, ? extends GcRoot> pair, Pair<? extends HeapObject, ? extends GcRoot> pair2) {
                HeapObject heapObjectComponent1 = pair.component1();
                GcRoot gcRootComponent2 = pair.component2();
                HeapObject heapObjectComponent12 = pair2.component1();
                String name = pair2.component2().getClass().getName();
                String name2 = gcRootComponent2.getClass().getName();
                Intrinsics.checkExpressionValueIsNotNull(name2, "root1::class.java.name");
                int iCompareTo = name.compareTo(name2);
                return iCompareTo != 0 ? iCompareTo : ((String) pathFinder$sortedGcRoots$rootClassName$1.invoke(heapObjectComponent1)).compareTo((String) pathFinder$sortedGcRoots$rootClassName$1.invoke(heapObjectComponent12));
            }
        });
    }

    private final void undominate(State state, long j, boolean z) {
        state.getDominatedObjectIds().remove(j);
        if (z) {
            state.getVisitedSet().add(j);
        }
    }

    private final void undominateWithSkips(State state, long j) throws IllegalArgumentException {
        HeapValue value;
        HeapObject heapObjectFindObjectById = this.graph.findObjectById(j);
        if (heapObjectFindObjectById instanceof HeapObject.HeapClass) {
            undominate(state, j, false);
            return;
        }
        if (heapObjectFindObjectById instanceof HeapObject.HeapInstance) {
            HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObjectFindObjectById;
            if (!Intrinsics.areEqual(heapInstance.getInstanceClassName(), "java.lang.String")) {
                undominate(state, j, false);
                return;
            }
            undominate(state, j, true);
            HeapField heapField = heapInstance.get("java.lang.String", "value");
            Long asObjectId = (heapField == null || (value = heapField.getValue()) == null) ? null : value.getAsObjectId();
            if (asObjectId != null) {
                undominate(state, asObjectId.longValue(), true);
                return;
            }
            return;
        }
        if (!(heapObjectFindObjectById instanceof HeapObject.HeapObjectArray)) {
            undominate(state, j, false);
            return;
        }
        HeapObject.HeapObjectArray heapObjectArray = (HeapObject.HeapObjectArray) heapObjectFindObjectById;
        if (!heapObjectArray.getIsPrimitiveWrapperArray()) {
            undominate(state, j, false);
            return;
        }
        undominate(state, j, true);
        for (long j2 : heapObjectArray.readRecord().getElementIds()) {
            undominate(state, j2, true);
        }
    }

    private final void updateDominator(State state, long j, long j2, boolean z) {
        int slot = state.getDominatedObjectIds().getSlot(j2);
        if (slot == -1 && (state.getVisitedSet().contains(j2) || state.getToVisitSet().contains(Long.valueOf(j2)) || state.getToVisitLastSet().contains(Long.valueOf(j2)))) {
            return;
        }
        int slot2 = state.getDominatedObjectIds().getSlot(j);
        boolean zContains = state.getLeakingObjectIds().contains(Long.valueOf(j));
        if (!zContains && slot2 == -1) {
            if (z) {
                state.getVisitedSet().add(j2);
            }
            if (slot != -1) {
                state.getDominatedObjectIds().remove(j2);
                return;
            }
            return;
        }
        if (!zContains) {
            j = state.getDominatedObjectIds().getSlotValue(slot2);
        }
        if (slot == -1) {
            state.getDominatedObjectIds().set(j2, j);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z2 = false;
        boolean z3 = false;
        while (!z3) {
            arrayList.add(Long.valueOf(j));
            int slot3 = state.getDominatedObjectIds().getSlot(j);
            if (slot3 == -1) {
                z3 = true;
            } else {
                j = state.getDominatedObjectIds().getSlotValue(slot3);
            }
        }
        long slotValue = state.getDominatedObjectIds().getSlotValue(slot);
        while (!z2) {
            arrayList2.add(Long.valueOf(slotValue));
            int slot4 = state.getDominatedObjectIds().getSlot(slotValue);
            if (slot4 == -1) {
                z2 = true;
            } else {
                slotValue = state.getDominatedObjectIds().getSlotValue(slot4);
            }
        }
        Long lValueOf = (Long) null;
        Iterator it = arrayList.iterator();
        loop2: while (true) {
            if (!it.hasNext()) {
                break;
            }
            long jLongValue = ((Number) it.next()).longValue();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                long jLongValue2 = ((Number) it2.next()).longValue();
                if (jLongValue2 == jLongValue) {
                    lValueOf = Long.valueOf(jLongValue2);
                    break loop2;
                }
            }
        }
        if (lValueOf != null) {
            state.getDominatedObjectIds().set(j2, lValueOf.longValue());
            return;
        }
        state.getDominatedObjectIds().remove(j2);
        if (z) {
            state.getVisitedSet().add(j2);
        }
    }

    private final void updateDominatorWithSkips(State state, long j, long j2) throws IllegalArgumentException {
        HeapValue value;
        HeapObject heapObjectFindObjectById = this.graph.findObjectById(j2);
        if (heapObjectFindObjectById instanceof HeapObject.HeapClass) {
            undominate(state, j2, false);
            return;
        }
        if (heapObjectFindObjectById instanceof HeapObject.HeapInstance) {
            HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObjectFindObjectById;
            if (!Intrinsics.areEqual(heapInstance.getInstanceClassName(), "java.lang.String")) {
                updateDominator(state, j, j2, false);
                return;
            }
            updateDominator(state, j, j2, true);
            HeapField heapField = heapInstance.get("java.lang.String", "value");
            Long asObjectId = (heapField == null || (value = heapField.getValue()) == null) ? null : value.getAsObjectId();
            if (asObjectId != null) {
                updateDominator(state, j, asObjectId.longValue(), true);
                return;
            }
            return;
        }
        if (!(heapObjectFindObjectById instanceof HeapObject.HeapObjectArray)) {
            updateDominator(state, j, j2, false);
            return;
        }
        HeapObject.HeapObjectArray heapObjectArray = (HeapObject.HeapObjectArray) heapObjectFindObjectById;
        if (!heapObjectArray.getIsPrimitiveWrapperArray()) {
            updateDominator(state, j, j2, false);
            return;
        }
        updateDominator(state, j, j2, true);
        for (long j3 : heapObjectArray.readRecord().getElementIds()) {
            updateDominator(state, j, j3, true);
        }
    }

    private final void visitClassRecord(State state, HeapObject.HeapClass heapClass, ReferencePathNode referencePathNode) throws IllegalArgumentException {
        ReferencePathNode.ChildNode.LibraryLeakChildNode libraryLeakChildNode;
        if (kotlin.text.StringsKt.startsWith$default(heapClass.getName(), "android.R$", false, 2, (Object) null)) {
            return;
        }
        Map<String, ReferenceMatcher> mapEmptyMap = this.staticFieldNameByClassName.get(heapClass.getName());
        if (mapEmptyMap == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        Map<String, ReferenceMatcher> map = mapEmptyMap;
        for (HeapField heapField : heapClass.readStaticFields()) {
            if (heapField.getValue().isNonNullReference()) {
                String name = heapField.getName();
                if (!Intrinsics.areEqual(name, "$staticOverhead") && !Intrinsics.areEqual(name, "$classOverhead") && !kotlin.text.StringsKt.startsWith$default(name, "$class$", false, 2, (Object) null)) {
                    Long asObjectId = heapField.getValue().getAsObjectId();
                    if (asObjectId == null) {
                        Intrinsics.throwNpe();
                    }
                    long jLongValue = asObjectId.longValue();
                    if (state.getComputeRetainedHeapSize()) {
                        undominateWithSkips(state, jLongValue);
                    }
                    ReferenceMatcher referenceMatcher = map.get(name);
                    if (referenceMatcher == null) {
                        libraryLeakChildNode = new ReferencePathNode.ChildNode.NormalNode(jLongValue, referencePathNode, LeakTraceReference.ReferenceType.STATIC_FIELD, name, heapField.getDeclaringClass().getName());
                    } else if (referenceMatcher instanceof LibraryLeakReferenceMatcher) {
                        libraryLeakChildNode = new ReferencePathNode.ChildNode.LibraryLeakChildNode(jLongValue, referencePathNode, LeakTraceReference.ReferenceType.STATIC_FIELD, name, (LibraryLeakReferenceMatcher) referenceMatcher, heapField.getDeclaringClass().getName());
                    } else {
                        if (!(referenceMatcher instanceof IgnoredReferenceMatcher)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        libraryLeakChildNode = null;
                    }
                    if (libraryLeakChildNode != null && libraryLeakChildNode.getObjectId() != 0 && this.graph.findObjectByIdOrNull(libraryLeakChildNode.getObjectId()) != null) {
                        enqueue$default(this, state, libraryLeakChildNode, null, null, 6, null);
                    }
                }
            }
        }
    }

    private final void visitInstance(State state, HeapObject.HeapInstance heapInstance, ReferencePathNode referencePathNode) throws IllegalArgumentException {
        ReferencePathNode.ChildNode.LibraryLeakChildNode libraryLeakChildNode;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<HeapObject.HeapClass> it = heapInstance.getInstanceClass().getClassHierarchy().iterator();
        while (it.hasNext()) {
            Map<String, ReferenceMatcher> map = this.fieldNameByClassName.get(it.next().getName());
            if (map != null) {
                for (Map.Entry<String, ReferenceMatcher> entry : map.entrySet()) {
                    String key = entry.getKey();
                    ReferenceMatcher value = entry.getValue();
                    if (!linkedHashMap.containsKey(key)) {
                        linkedHashMap.put(key, value);
                    }
                }
            }
        }
        List<HeapField> mutableList = SequencesKt.toMutableList(SequencesKt.filter(heapInstance.readFields(), new Function1<HeapField, Boolean>() {
            @Override
            public Boolean invoke(HeapField heapField) {
                return Boolean.valueOf(invoke2(heapField));
            }

            public final boolean invoke2(HeapField heapField) {
                Intrinsics.checkParameterIsNotNull(heapField, "it");
                return heapField.getValue().isNonNullReference();
            }
        }));
        if (mutableList.size() > 1) {
            CollectionsKt.sortWith(mutableList, new Comparator<T>() {
                @Override
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((HeapField) t).getName(), ((HeapField) t2).getName());
                }
            });
        }
        for (HeapField heapField : mutableList) {
            Long asObjectId = heapField.getValue().getAsObjectId();
            if (asObjectId == null) {
                Intrinsics.throwNpe();
            }
            long jLongValue = asObjectId.longValue();
            if (state.getComputeRetainedHeapSize()) {
                updateDominatorWithSkips(state, referencePathNode.getObjectId(), jLongValue);
            }
            ReferenceMatcher referenceMatcher = (ReferenceMatcher) linkedHashMap.get(heapField.getName());
            if (referenceMatcher == null) {
                libraryLeakChildNode = new ReferencePathNode.ChildNode.NormalNode(jLongValue, referencePathNode, LeakTraceReference.ReferenceType.INSTANCE_FIELD, heapField.getName(), heapField.getDeclaringClass().getName());
            } else if (referenceMatcher instanceof LibraryLeakReferenceMatcher) {
                libraryLeakChildNode = new ReferencePathNode.ChildNode.LibraryLeakChildNode(jLongValue, referencePathNode, LeakTraceReference.ReferenceType.INSTANCE_FIELD, heapField.getName(), (LibraryLeakReferenceMatcher) referenceMatcher, heapField.getDeclaringClass().getName());
            } else {
                if (!(referenceMatcher instanceof IgnoredReferenceMatcher)) {
                    throw new NoWhenBranchMatchedException();
                }
                libraryLeakChildNode = null;
            }
            if (libraryLeakChildNode != null && libraryLeakChildNode.getObjectId() != 0 && this.graph.findObjectByIdOrNull(libraryLeakChildNode.getObjectId()) != null) {
                enqueue(state, libraryLeakChildNode, heapInstance.getInstanceClassName(), heapField.getName());
            }
        }
    }

    private final void visitObjectArray(State state, HeapObject.HeapObjectArray heapObjectArray, ReferencePathNode referencePathNode) throws IllegalArgumentException {
        long[] elementIds = heapObjectArray.readRecord().getElementIds();
        ArrayList arrayList = new ArrayList();
        int length = elementIds.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            long j = elementIds[i2];
            if (j != 0 && this.graph.objectExists(j)) {
                arrayList.add(Long.valueOf(j));
            }
        }
        for (Object obj : arrayList) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            long jLongValue = ((Number) obj).longValue();
            if (state.getComputeRetainedHeapSize()) {
                updateDominatorWithSkips(state, referencePathNode.getObjectId(), jLongValue);
            }
            enqueue$default(this, state, new ReferencePathNode.ChildNode.NormalNode(jLongValue, referencePathNode, LeakTraceReference.ReferenceType.ARRAY_ENTRY, String.valueOf(i), ""), null, null, 6, null);
            i = i3;
        }
    }

    public final PathFindingResults findPathsFromGcRoots(Set<Long> leakingObjectIds, boolean computeRetainedHeapSize) {
        Intrinsics.checkParameterIsNotNull(leakingObjectIds, "leakingObjectIds");
        SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
        if (logger != null) {
            logger.d("findPathsFromGcRoots");
        }
        this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.FINDING_PATHS_TO_RETAINED_OBJECTS);
        return findPathsFromGcRoots(new State(leakingObjectIds, determineSizeOfObjectInstances(this.graph), computeRetainedHeapSize));
    }
}
