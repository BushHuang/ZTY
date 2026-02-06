package kshark;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import kshark.HeapAnalyzer;
import kshark.HeapObject;
import kshark.HprofHeapGraph;
import kshark.LeakTrace;
import kshark.LeakTraceObject;
import kshark.OnAnalysisProgressListener;
import kshark.SharkLog;
import kshark.internal.PathFinder;
import kshark.internal.ReferencePathNode;
import kshark.internal.StringsKt;
import kshark.internal.hppc.LongLongScatterMap;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002LMB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JR\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u0017JV\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019J(\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0010J(\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00102\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00102\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0010J&\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$0\u00102\f\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u0010J\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00102\f\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u0010J\u001c\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020*01J\u000e\u00102\u001a\u00020&2\u0006\u00103\u001a\u00020\u001dJ\"\u00104\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$2\u0006\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\u0013J\u000e\u00107\u001a\u0002082\u0006\u00109\u001a\u000208J,\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020*2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002080\u00102\u0006\u0010=\u001a\u00020>2\u0006\u0010.\u001a\u00020/J*\u0010?\u001a\u00020@*\u00020A2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u00109\u001a\u000208J*\u0010B\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00100$*\u00020A2\u0006\u0010E\u001a\u00020FJ\u001a\u0010G\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\u0010*\u00020A2\u0006\u0010E\u001a\u00020FJ8\u0010H\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00100$*\u00020A2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002080J2\u0006\u0010K\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006N"}, d2 = {"Lkshark/HeapAnalyzer;", "", "listener", "Lkshark/OnAnalysisProgressListener;", "(Lkshark/OnAnalysisProgressListener;)V", "getListener", "()Lkshark/OnAnalysisProgressListener;", "analyze", "Lkshark/HeapAnalysis;", "heapDumpFile", "Ljava/io/File;", "graph", "Lkshark/HeapGraph;", "leakingObjectFinder", "Lkshark/LeakingObjectFinder;", "referenceMatchers", "", "Lkshark/ReferenceMatcher;", "computeRetainedHeapSize", "", "objectInspectors", "Lkshark/ObjectInspector;", "metadataExtractor", "Lkshark/MetadataExtractor;", "proguardMapping", "Lkshark/ProguardMapping;", "buildLeakTraceObjects", "Lkshark/LeakTraceObject;", "pathHeapObjects", "Lkshark/HeapObject;", "buildReferencePath", "Lkshark/LeakTraceReference;", "shortestChildPath", "Lkshark/internal/ReferencePathNode$ChildNode;", "leakTraceObjects", "computeLeakStatuses", "Lkotlin/Pair;", "Lkshark/LeakTraceObject$LeakingStatus;", "", "leakReporters", "Lkshark/ObjectReporter;", "deduplicateShortestPaths", "Lkshark/internal/ReferencePathNode;", "inputPathResults", "findResultsInTrie", "", "parentNode", "Lkshark/HeapAnalyzer$TrieNode$ParentNode;", "outputPathResults", "", "recordClassName", "heap", "resolveStatus", "reporter", "leakingWins", "since", "", "analysisStartNanoTime", "updateTrie", "pathNode", "path", "pathIndex", "", "analyzeGraph", "Lkshark/HeapAnalysisSuccess;", "Lkshark/HeapAnalyzer$FindLeakInput;", "buildLeakTraces", "Lkshark/ApplicationLeak;", "Lkshark/LibraryLeak;", "pathFindingResults", "Lkshark/internal/PathFinder$PathFindingResults;", "computeRetainedSizes", "findLeaks", "leakingObjectIds", "", "enableSameInstanceThreshold", "FindLeakInput", "TrieNode", "shark"}, k = 1, mv = {1, 1, 15})
public final class HeapAnalyzer {
    private final OnAnalysisProgressListener listener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u0013"}, d2 = {"Lkshark/HeapAnalyzer$FindLeakInput;", "", "graph", "Lkshark/HeapGraph;", "referenceMatchers", "", "Lkshark/ReferenceMatcher;", "computeRetainedHeapSize", "", "objectInspectors", "Lkshark/ObjectInspector;", "(Lkshark/HeapGraph;Ljava/util/List;ZLjava/util/List;)V", "getComputeRetainedHeapSize", "()Z", "getGraph", "()Lkshark/HeapGraph;", "getObjectInspectors", "()Ljava/util/List;", "getReferenceMatchers", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class FindLeakInput {
        private final boolean computeRetainedHeapSize;
        private final HeapGraph graph;
        private final List<ObjectInspector> objectInspectors;
        private final List<ReferenceMatcher> referenceMatchers;

        public FindLeakInput(HeapGraph heapGraph, List<? extends ReferenceMatcher> list, boolean z, List<? extends ObjectInspector> list2) {
            Intrinsics.checkParameterIsNotNull(heapGraph, "graph");
            Intrinsics.checkParameterIsNotNull(list, "referenceMatchers");
            Intrinsics.checkParameterIsNotNull(list2, "objectInspectors");
            this.graph = heapGraph;
            this.referenceMatchers = list;
            this.computeRetainedHeapSize = z;
            this.objectInspectors = list2;
        }

        public final boolean getComputeRetainedHeapSize() {
            return this.computeRetainedHeapSize;
        }

        public final HeapGraph getGraph() {
            return this.graph;
        }

        public final List<ObjectInspector> getObjectInspectors() {
            return this.objectInspectors;
        }

        public final List<ReferenceMatcher> getReferenceMatchers() {
            return this.referenceMatchers;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lkshark/HeapAnalyzer$TrieNode;", "", "()V", "objectId", "", "getObjectId", "()J", "LeafNode", "ParentNode", "Lkshark/HeapAnalyzer$TrieNode$ParentNode;", "Lkshark/HeapAnalyzer$TrieNode$LeafNode;", "shark"}, k = 1, mv = {1, 1, 15})
    public static abstract class TrieNode {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkshark/HeapAnalyzer$TrieNode$LeafNode;", "Lkshark/HeapAnalyzer$TrieNode;", "objectId", "", "pathNode", "Lkshark/internal/ReferencePathNode;", "(JLkshark/internal/ReferencePathNode;)V", "getObjectId", "()J", "getPathNode", "()Lkshark/internal/ReferencePathNode;", "shark"}, k = 1, mv = {1, 1, 15})
        public static final class LeafNode extends TrieNode {
            private final long objectId;
            private final ReferencePathNode pathNode;

            public LeafNode(long j, ReferencePathNode referencePathNode) {
                super(null);
                Intrinsics.checkParameterIsNotNull(referencePathNode, "pathNode");
                this.objectId = j;
                this.pathNode = referencePathNode;
            }

            @Override
            public long getObjectId() {
                return this.objectId;
            }

            public final ReferencePathNode getPathNode() {
                return this.pathNode;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lkshark/HeapAnalyzer$TrieNode$ParentNode;", "Lkshark/HeapAnalyzer$TrieNode;", "objectId", "", "(J)V", "children", "", "getChildren", "()Ljava/util/Map;", "getObjectId", "()J", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
        public static final class ParentNode extends TrieNode {
            private final Map<Long, TrieNode> children;
            private final long objectId;

            public ParentNode(long j) {
                super(null);
                this.objectId = j;
                this.children = new LinkedHashMap();
            }

            public final Map<Long, TrieNode> getChildren() {
                return this.children;
            }

            @Override
            public long getObjectId() {
                return this.objectId;
            }

            public String toString() {
                return "ParentNode(objectId=" + getObjectId() + ", children=" + this.children + ')';
            }
        }

        private TrieNode() {
        }

        public TrieNode(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract long getObjectId();
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;
        public static final int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[LeakTraceObject.LeakingStatus.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LeakTraceObject.LeakingStatus.LEAKING.ordinal()] = 1;
            $EnumSwitchMapping$0[LeakTraceObject.LeakingStatus.UNKNOWN.ordinal()] = 2;
            $EnumSwitchMapping$0[LeakTraceObject.LeakingStatus.NOT_LEAKING.ordinal()] = 3;
            int[] iArr2 = new int[LeakTraceObject.LeakingStatus.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[LeakTraceObject.LeakingStatus.UNKNOWN.ordinal()] = 1;
            $EnumSwitchMapping$1[LeakTraceObject.LeakingStatus.NOT_LEAKING.ordinal()] = 2;
            $EnumSwitchMapping$1[LeakTraceObject.LeakingStatus.LEAKING.ordinal()] = 3;
            int[] iArr3 = new int[LeakTraceObject.LeakingStatus.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[LeakTraceObject.LeakingStatus.UNKNOWN.ordinal()] = 1;
            $EnumSwitchMapping$2[LeakTraceObject.LeakingStatus.LEAKING.ordinal()] = 2;
            $EnumSwitchMapping$2[LeakTraceObject.LeakingStatus.NOT_LEAKING.ordinal()] = 3;
        }
    }

    public HeapAnalyzer(OnAnalysisProgressListener onAnalysisProgressListener) {
        Intrinsics.checkParameterIsNotNull(onAnalysisProgressListener, "listener");
        this.listener = onAnalysisProgressListener;
    }

    public final HeapAnalysis analyze(File heapDumpFile, HeapGraph graph, LeakingObjectFinder leakingObjectFinder, List<? extends ReferenceMatcher> referenceMatchers, boolean computeRetainedHeapSize, List<? extends ObjectInspector> objectInspectors, MetadataExtractor metadataExtractor) {
        Intrinsics.checkParameterIsNotNull(heapDumpFile, "heapDumpFile");
        Intrinsics.checkParameterIsNotNull(graph, "graph");
        Intrinsics.checkParameterIsNotNull(leakingObjectFinder, "leakingObjectFinder");
        Intrinsics.checkParameterIsNotNull(referenceMatchers, "referenceMatchers");
        Intrinsics.checkParameterIsNotNull(objectInspectors, "objectInspectors");
        Intrinsics.checkParameterIsNotNull(metadataExtractor, "metadataExtractor");
        long jNanoTime = System.nanoTime();
        try {
            return analyzeGraph(new FindLeakInput(graph, referenceMatchers, computeRetainedHeapSize, objectInspectors), metadataExtractor, leakingObjectFinder, heapDumpFile, jNanoTime);
        } catch (Throwable th) {
            return new HeapAnalysisFailure(heapDumpFile, System.currentTimeMillis(), since(jNanoTime), new HeapAnalysisException(th));
        }
    }

    public final HeapAnalysis analyze(File heapDumpFile, LeakingObjectFinder leakingObjectFinder, List<? extends ReferenceMatcher> referenceMatchers, boolean computeRetainedHeapSize, List<? extends ObjectInspector> objectInspectors, MetadataExtractor metadataExtractor, ProguardMapping proguardMapping) {
        Intrinsics.checkParameterIsNotNull(heapDumpFile, "heapDumpFile");
        Intrinsics.checkParameterIsNotNull(leakingObjectFinder, "leakingObjectFinder");
        Intrinsics.checkParameterIsNotNull(referenceMatchers, "referenceMatchers");
        Intrinsics.checkParameterIsNotNull(objectInspectors, "objectInspectors");
        Intrinsics.checkParameterIsNotNull(metadataExtractor, "metadataExtractor");
        long jNanoTime = System.nanoTime();
        if (!heapDumpFile.exists()) {
            return new HeapAnalysisFailure(heapDumpFile, System.currentTimeMillis(), since(jNanoTime), new HeapAnalysisException(new IllegalArgumentException("File does not exist: " + heapDumpFile)));
        }
        try {
            this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.PARSING_HEAP_DUMP);
            Hprof hprofOpen = Hprof.INSTANCE.open(heapDumpFile);
            Throwable th = (Throwable) null;
            try {
                HeapAnalysisSuccess heapAnalysisSuccessAnalyzeGraph = analyzeGraph(new FindLeakInput(HprofHeapGraph.Companion.indexHprof$default(HprofHeapGraph.INSTANCE, hprofOpen, proguardMapping, null, 4, null), referenceMatchers, computeRetainedHeapSize, objectInspectors), metadataExtractor, leakingObjectFinder, heapDumpFile, jNanoTime);
                CloseableKt.closeFinally(hprofOpen, th);
                return heapAnalysisSuccessAnalyzeGraph;
            } finally {
            }
        } catch (Throwable th2) {
            return new HeapAnalysisFailure(heapDumpFile, System.currentTimeMillis(), since(jNanoTime), new HeapAnalysisException(th2));
        }
    }

    public final HeapAnalysisSuccess analyzeGraph(FindLeakInput findLeakInput, MetadataExtractor metadataExtractor, LeakingObjectFinder leakingObjectFinder, File file, long j) {
        Intrinsics.checkParameterIsNotNull(findLeakInput, "$this$analyzeGraph");
        Intrinsics.checkParameterIsNotNull(metadataExtractor, "metadataExtractor");
        Intrinsics.checkParameterIsNotNull(leakingObjectFinder, "leakingObjectFinder");
        Intrinsics.checkParameterIsNotNull(file, "heapDumpFile");
        this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.EXTRACTING_METADATA);
        Map<String, String> mapExtractMetadata = metadataExtractor.extractMetadata(findLeakInput.getGraph());
        this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.FINDING_RETAINED_OBJECTS);
        Pair<List<ApplicationLeak>, List<LibraryLeak>> pairFindLeaks = findLeaks(findLeakInput, leakingObjectFinder.findLeakingObjectIds(findLeakInput.getGraph()), false);
        return new HeapAnalysisSuccess(file, System.currentTimeMillis(), since(j), mapExtractMetadata, pairFindLeaks.component1(), pairFindLeaks.component2());
    }

    public final List<LeakTraceObject> buildLeakTraceObjects(List<? extends ObjectInspector> objectInspectors, List<? extends HeapObject> pathHeapObjects) {
        Intrinsics.checkParameterIsNotNull(objectInspectors, "objectInspectors");
        Intrinsics.checkParameterIsNotNull(pathHeapObjects, "pathHeapObjects");
        List<? extends HeapObject> list = pathHeapObjects;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ObjectReporter((HeapObject) it.next()));
        }
        ArrayList arrayList2 = arrayList;
        for (ObjectInspector objectInspector : objectInspectors) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                objectInspector.inspect((ObjectReporter) it2.next());
            }
        }
        List<Pair<LeakTraceObject.LeakingStatus, String>> listComputeLeakStatuses = computeLeakStatuses(arrayList2);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            HeapObject heapObject = (HeapObject) obj;
            ObjectReporter objectReporter = arrayList2.get(i);
            Pair<LeakTraceObject.LeakingStatus, String> pair = listComputeLeakStatuses.get(i);
            LeakTraceObject.LeakingStatus leakingStatusComponent1 = pair.component1();
            String strComponent2 = pair.component2();
            arrayList3.add(new LeakTraceObject(heapObject.getObjectId(), heapObject instanceof HeapObject.HeapClass ? LeakTraceObject.ObjectType.CLASS : ((heapObject instanceof HeapObject.HeapObjectArray) || (heapObject instanceof HeapObject.HeapPrimitiveArray)) ? LeakTraceObject.ObjectType.ARRAY : LeakTraceObject.ObjectType.INSTANCE, recordClassName(heapObject), objectReporter.getLabels(), leakingStatusComponent1, strComponent2));
            i = i2;
        }
        return arrayList3;
    }

    public final Pair<List<ApplicationLeak>, List<LibraryLeak>> buildLeakTraces(FindLeakInput findLeakInput, PathFinder.PathFindingResults pathFindingResults) {
        ReferencePathNode.LibraryLeakNode libraryLeakNode;
        Intrinsics.checkParameterIsNotNull(findLeakInput, "$this$buildLeakTraces");
        Intrinsics.checkParameterIsNotNull(pathFindingResults, "pathFindingResults");
        SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
        if (logger != null) {
            logger.d("start buildLeakTraces");
        }
        List<Integer> listComputeRetainedSizes = computeRetainedSizes(findLeakInput, pathFindingResults);
        this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.BUILDING_LEAK_TRACES);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        List<ReferencePathNode> listDeduplicateShortestPaths = deduplicateShortestPaths(pathFindingResults.getPathsToLeakingObjects());
        if (listDeduplicateShortestPaths.size() != pathFindingResults.getPathsToLeakingObjects().size()) {
            SharkLog.Logger logger2 = SharkLog.INSTANCE.getLogger();
            if (logger2 != null) {
                logger2.d("Found " + pathFindingResults.getPathsToLeakingObjects().size() + " paths to retained objects, down to " + listDeduplicateShortestPaths.size() + " after removing duplicated paths");
            }
        } else {
            SharkLog.Logger logger3 = SharkLog.INSTANCE.getLogger();
            if (logger3 != null) {
                logger3.d("Found " + listDeduplicateShortestPaths.size() + " paths to retained objects");
            }
        }
        int i = 0;
        for (Object obj : listDeduplicateShortestPaths) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ReferencePathNode parent = (ReferencePathNode) obj;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (parent instanceof ReferencePathNode.ChildNode) {
                arrayList2.add(0, parent);
                arrayList.add(0, findLeakInput.getGraph().findObjectById(parent.getObjectId()));
                parent = ((ReferencePathNode.ChildNode) parent).getParent();
            }
            if (parent == null) {
                throw new TypeCastException("null cannot be cast to non-null type kshark.internal.ReferencePathNode.RootNode");
            }
            ReferencePathNode.RootNode rootNode = (ReferencePathNode.RootNode) parent;
            arrayList.add(0, findLeakInput.getGraph().findObjectById(rootNode.getObjectId()));
            List<LeakTraceObject> listBuildLeakTraceObjects = buildLeakTraceObjects(findLeakInput.getObjectInspectors(), arrayList);
            Object obj2 = null;
            LeakTrace leakTrace = new LeakTrace(LeakTrace.GcRootType.INSTANCE.fromGcRoot(rootNode.getGcRoot()), buildReferencePath(arrayList2, listBuildLeakTraceObjects), (LeakTraceObject) CollectionsKt.last((List) listBuildLeakTraceObjects), listComputeRetainedSizes != null ? listComputeRetainedSizes.get(i) : null);
            if (rootNode instanceof ReferencePathNode.LibraryLeakNode) {
                libraryLeakNode = (ReferencePathNode.LibraryLeakNode) rootNode;
            } else {
                Iterator it = arrayList2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (((ReferencePathNode.ChildNode) next) instanceof ReferencePathNode.LibraryLeakNode) {
                        obj2 = next;
                        break;
                    }
                }
                libraryLeakNode = (ReferencePathNode.LibraryLeakNode) obj2;
            }
            if (libraryLeakNode != null) {
                LibraryLeakReferenceMatcher matcher = libraryLeakNode.getMatcher();
                String strCreateSHA1Hash = StringsKt.createSHA1Hash(matcher.getPattern().toString());
                Object obj3 = linkedHashMap2.get(strCreateSHA1Hash);
                if (obj3 == null) {
                    obj3 = TuplesKt.to(matcher, new ArrayList());
                    linkedHashMap2.put(strCreateSHA1Hash, obj3);
                }
                ((List) ((Pair) obj3).getSecond()).add(leakTrace);
            } else {
                String signature = leakTrace.getSignature();
                Object obj4 = linkedHashMap.get(signature);
                if (obj4 == null) {
                    obj4 = (List) new ArrayList();
                    linkedHashMap.put(signature, obj4);
                }
                ((List) obj4).add(leakTrace);
            }
            i = i2;
        }
        ArrayList arrayList3 = new ArrayList(linkedHashMap.size());
        Iterator it2 = linkedHashMap.entrySet().iterator();
        while (it2.hasNext()) {
            arrayList3.add(new ApplicationLeak((List) ((Map.Entry) it2.next()).getValue()));
        }
        ArrayList arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList(linkedHashMap2.size());
        Iterator it3 = linkedHashMap2.entrySet().iterator();
        while (it3.hasNext()) {
            Pair pair = (Pair) ((Map.Entry) it3.next()).getValue();
            LibraryLeakReferenceMatcher libraryLeakReferenceMatcher = (LibraryLeakReferenceMatcher) pair.component1();
            arrayList5.add(new LibraryLeak((List) pair.component2(), libraryLeakReferenceMatcher.getPattern(), libraryLeakReferenceMatcher.getDescription()));
        }
        ArrayList arrayList6 = arrayList5;
        SharkLog.Logger logger4 = SharkLog.INSTANCE.getLogger();
        if (logger4 != null) {
            logger4.d("end buildLeakTraces");
        }
        return TuplesKt.to(arrayList4, arrayList6);
    }

    public final List<LeakTraceReference> buildReferencePath(List<? extends ReferencePathNode.ChildNode> shortestChildPath, List<LeakTraceObject> leakTraceObjects) {
        Intrinsics.checkParameterIsNotNull(shortestChildPath, "shortestChildPath");
        Intrinsics.checkParameterIsNotNull(leakTraceObjects, "leakTraceObjects");
        List<? extends ReferencePathNode.ChildNode> list = shortestChildPath;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ReferencePathNode.ChildNode childNode = (ReferencePathNode.ChildNode) obj;
            arrayList.add(new LeakTraceReference(leakTraceObjects.get(i), childNode.getRefFromParentType(), childNode.getRefFromParentName(), childNode.getDeclaredClassName()));
            i = i2;
        }
        return arrayList;
    }

    public final List<Pair<LeakTraceObject.LeakingStatus, String>> computeLeakStatuses(List<ObjectReporter> leakReporters) {
        int i;
        Pair pair;
        Pair pair2;
        Intrinsics.checkParameterIsNotNull(leakReporters, "leakReporters");
        int size = leakReporters.size() - 1;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = size;
        ArrayList arrayList = new ArrayList();
        List<ObjectReporter> list = leakReporters;
        Iterator<T> it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Pair<LeakTraceObject.LeakingStatus, String> pairResolveStatus = resolveStatus((ObjectReporter) it.next(), i2 == size);
            if (i2 == size) {
                int i3 = WhenMappings.$EnumSwitchMapping$0[pairResolveStatus.getFirst().ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        pairResolveStatus = TuplesKt.to(LeakTraceObject.LeakingStatus.LEAKING, "This is the leaking object");
                    } else {
                        if (i3 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        pairResolveStatus = TuplesKt.to(LeakTraceObject.LeakingStatus.LEAKING, "This is the leaking object. Conflicts with " + pairResolveStatus.getSecond());
                    }
                }
            }
            arrayList.add(pairResolveStatus);
            LeakTraceObject.LeakingStatus leakingStatusComponent1 = pairResolveStatus.component1();
            if (leakingStatusComponent1 == LeakTraceObject.LeakingStatus.NOT_LEAKING) {
                intRef.element = i2;
                intRef2.element = size;
            } else if (leakingStatusComponent1 == LeakTraceObject.LeakingStatus.LEAKING && intRef2.element == size) {
                intRef2.element = i2;
            }
            i2++;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(StringsKt.lastSegment(recordClassName(((ObjectReporter) it2.next()).getHeapObject()), '.'));
        }
        ArrayList arrayList3 = arrayList2;
        int i4 = intRef.element;
        int i5 = 0;
        while (i5 < i4) {
            Pair pair3 = (Pair) arrayList.get(i5);
            LeakTraceObject.LeakingStatus leakingStatus = (LeakTraceObject.LeakingStatus) pair3.component1();
            String str = (String) pair3.component2();
            int i6 = i5 + 1;
            for (Number number : SequencesKt.generateSequence(Integer.valueOf(i6), new Function1<Integer, Integer>() {
                {
                    super(1);
                }

                public final Integer invoke(int i7) {
                    if (i7 < intRef.element) {
                        return Integer.valueOf(i7 + 1);
                    }
                    return null;
                }

                @Override
                public Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            })) {
                if (((LeakTraceObject.LeakingStatus) ((Pair) arrayList.get(number.intValue())).getFirst()) == LeakTraceObject.LeakingStatus.NOT_LEAKING) {
                    String str2 = (String) arrayList3.get(number.intValue());
                    int i7 = WhenMappings.$EnumSwitchMapping$1[leakingStatus.ordinal()];
                    if (i7 == 1) {
                        pair2 = TuplesKt.to(LeakTraceObject.LeakingStatus.NOT_LEAKING, str2 + "↓ is not leaking");
                    } else if (i7 == 2) {
                        pair2 = TuplesKt.to(LeakTraceObject.LeakingStatus.NOT_LEAKING, str2 + "↓ is not leaking and " + str);
                    } else {
                        if (i7 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        pair2 = TuplesKt.to(LeakTraceObject.LeakingStatus.NOT_LEAKING, str2 + "↓ is not leaking. Conflicts with " + str);
                    }
                    arrayList.set(i5, pair2);
                    i5 = i6;
                }
            }
            throw new NoSuchElementException("Sequence contains no element matching the predicate.");
        }
        int i8 = size - 1;
        if (intRef2.element < i8 && i8 >= (i = intRef2.element + 1)) {
            while (true) {
                Pair pair4 = (Pair) arrayList.get(i8);
                LeakTraceObject.LeakingStatus leakingStatus2 = (LeakTraceObject.LeakingStatus) pair4.component1();
                String str3 = (String) pair4.component2();
                for (Number number2 : SequencesKt.generateSequence(Integer.valueOf(i8 - 1), new Function1<Integer, Integer>() {
                    {
                        super(1);
                    }

                    public final Integer invoke(int i9) {
                        if (i9 > intRef2.element) {
                            return Integer.valueOf(i9 - 1);
                        }
                        return null;
                    }

                    @Override
                    public Integer invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                })) {
                    if (((LeakTraceObject.LeakingStatus) ((Pair) arrayList.get(number2.intValue())).getFirst()) == LeakTraceObject.LeakingStatus.LEAKING) {
                        String str4 = (String) arrayList3.get(number2.intValue());
                        int i9 = WhenMappings.$EnumSwitchMapping$2[leakingStatus2.ordinal()];
                        if (i9 == 1) {
                            pair = TuplesKt.to(LeakTraceObject.LeakingStatus.LEAKING, str4 + "↑ is leaking");
                        } else {
                            if (i9 != 2) {
                                if (i9 != 3) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                throw new IllegalStateException("Should never happen");
                            }
                            pair = TuplesKt.to(LeakTraceObject.LeakingStatus.LEAKING, str4 + "↑ is leaking and " + str3);
                        }
                        arrayList.set(i8, pair);
                        if (i8 == i) {
                            break;
                        }
                        i8--;
                    }
                }
                throw new NoSuchElementException("Sequence contains no element matching the predicate.");
            }
        }
        return arrayList;
    }

    public final List<Integer> computeRetainedSizes(final FindLeakInput findLeakInput, PathFinder.PathFindingResults pathFindingResults) {
        HeapField heapField;
        HeapValue value;
        Long asLong;
        HeapValue value2;
        HeapValue value3;
        Intrinsics.checkParameterIsNotNull(findLeakInput, "$this$computeRetainedSizes");
        Intrinsics.checkParameterIsNotNull(pathFindingResults, "pathFindingResults");
        if (!findLeakInput.getComputeRetainedHeapSize()) {
            return null;
        }
        SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
        if (logger != null) {
            logger.d("start computeRetainedSizes");
        }
        List<ReferencePathNode> pathsToLeakingObjects = pathFindingResults.getPathsToLeakingObjects();
        LongLongScatterMap dominatedObjectIds = pathFindingResults.getDominatedObjectIds();
        this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.COMPUTING_NATIVE_RETAINED_SIZE);
        final Map mapWithDefaultMutable = MapsKt.withDefaultMutable(new LinkedHashMap(), new Function1<Long, Integer>() {
            public final int invoke(long j) {
                return 0;
            }

            @Override
            public Integer invoke(Long l) {
                return Integer.valueOf(invoke(l.longValue()));
            }
        });
        Iterator it = SequencesKt.filter(findLeakInput.getGraph().getInstances(), new Function1<HeapObject.HeapInstance, Boolean>() {
            @Override
            public Boolean invoke(HeapObject.HeapInstance heapInstance) {
                return Boolean.valueOf(invoke2(heapInstance));
            }

            public final boolean invoke2(HeapObject.HeapInstance heapInstance) {
                Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                return Intrinsics.areEqual(heapInstance.getInstanceClassName(), "sun.misc.Cleaner");
            }
        }).iterator();
        while (true) {
            int iLongValue = 0;
            if (!it.hasNext()) {
                break;
            }
            HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) it.next();
            HeapField heapField2 = heapInstance.get("sun.misc.Cleaner", "thunk");
            Long asNonNullObjectId = (heapField2 == null || (value3 = heapField2.getValue()) == null) ? null : value3.getAsNonNullObjectId();
            HeapField heapField3 = heapInstance.get("java.lang.ref.Reference", "referent");
            Long asNonNullObjectId2 = (heapField3 == null || (value2 = heapField3.getValue()) == null) ? null : value2.getAsNonNullObjectId();
            if (asNonNullObjectId != null && asNonNullObjectId2 != null) {
                HeapObject asObject = heapField2.getValue().getAsObject();
                if (asObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance2 = (HeapObject.HeapInstance) asObject;
                    if (heapInstance2.instanceOf("libcore.util.NativeAllocationRegistry$CleanerThunk") && (heapField = heapInstance2.get("libcore.util.NativeAllocationRegistry$CleanerThunk", "this$0")) != null && heapField.getValue().isNonNullReference()) {
                        HeapObject asObject2 = heapField.getValue().getAsObject();
                        if (asObject2 instanceof HeapObject.HeapInstance) {
                            HeapObject.HeapInstance heapInstance3 = (HeapObject.HeapInstance) asObject2;
                            if (heapInstance3.instanceOf("libcore.util.NativeAllocationRegistry")) {
                                int iIntValue = ((Number) MapsKt.getValue(mapWithDefaultMutable, asNonNullObjectId2)).intValue();
                                HeapField heapField4 = heapInstance3.get("libcore.util.NativeAllocationRegistry", "size");
                                if (heapField4 != null && (value = heapField4.getValue()) != null && (asLong = value.getAsLong()) != null) {
                                    iLongValue = (int) asLong.longValue();
                                }
                                mapWithDefaultMutable.put(asNonNullObjectId2, Integer.valueOf(iIntValue + iLongValue));
                            }
                        }
                    }
                }
            }
        }
        this.listener.onAnalysisProgress(OnAnalysisProgressListener.Step.COMPUTING_RETAINED_SIZE);
        final Map mapWithDefaultMutable2 = MapsKt.withDefaultMutable(new LinkedHashMap(), new Function1<Long, Integer>() {
            public final int invoke(long j) {
                return 0;
            }

            @Override
            public Integer invoke(Long l) {
                return Integer.valueOf(invoke(l.longValue()));
            }
        });
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<ReferencePathNode> list = pathsToLeakingObjects;
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            long objectId = ((ReferencePathNode) it2.next()).getObjectId();
            linkedHashSet.add(Long.valueOf(objectId));
            HeapObject.HeapInstance asInstance = findLeakInput.getGraph().findObjectById(objectId).getAsInstance();
            if (asInstance == null) {
                Intrinsics.throwNpe();
            }
            mapWithDefaultMutable2.put(Long.valueOf(objectId), Integer.valueOf(((Number) MapsKt.getValue(mapWithDefaultMutable2, Long.valueOf(objectId))).intValue() + asInstance.getInstanceClass().getInstanceByteSize()));
        }
        dominatedObjectIds.forEach(new Function2<Long, Long, Unit>() {
            {
                super(2);
            }

            @Override
            public Unit invoke(Long l, Long l2) throws IllegalArgumentException {
                invoke(l.longValue(), l2.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j, long j2) throws IllegalArgumentException {
                int byteSize;
                if (linkedHashSet.contains(Long.valueOf(j))) {
                    return;
                }
                int iIntValue2 = ((Number) MapsKt.getValue(mapWithDefaultMutable2, Long.valueOf(j2))).intValue();
                int iIntValue3 = ((Number) MapsKt.getValue(mapWithDefaultMutable, Long.valueOf(j))).intValue();
                HeapObject heapObjectFindObjectById = findLeakInput.getGraph().findObjectById(j);
                if (heapObjectFindObjectById instanceof HeapObject.HeapInstance) {
                    byteSize = ((HeapObject.HeapInstance) heapObjectFindObjectById).getByteSize();
                } else if (heapObjectFindObjectById instanceof HeapObject.HeapObjectArray) {
                    byteSize = ((HeapObject.HeapObjectArray) heapObjectFindObjectById).readByteSize();
                } else {
                    if (!(heapObjectFindObjectById instanceof HeapObject.HeapPrimitiveArray)) {
                        if (!(heapObjectFindObjectById instanceof HeapObject.HeapClass)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        throw new IllegalStateException("Unexpected class record " + heapObjectFindObjectById);
                    }
                    byteSize = ((HeapObject.HeapPrimitiveArray) heapObjectFindObjectById).readByteSize();
                }
                mapWithDefaultMutable2.put(Long.valueOf(j2), Integer.valueOf(iIntValue2 + iIntValue3 + byteSize));
            }
        });
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        do {
            booleanRef.element = false;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it3 = list.iterator();
            while (it3.hasNext()) {
                arrayList.add(Long.valueOf(((ReferencePathNode) it3.next()).getObjectId()));
            }
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                long jLongValue = ((Number) it4.next()).longValue();
                int slot = dominatedObjectIds.getSlot(jLongValue);
                if (slot != -1) {
                    long slotValue = dominatedObjectIds.getSlotValue(slot);
                    int iIntValue2 = ((Number) MapsKt.getValue(mapWithDefaultMutable2, Long.valueOf(jLongValue))).intValue();
                    if (iIntValue2 > 0) {
                        mapWithDefaultMutable2.put(Long.valueOf(jLongValue), 0);
                        mapWithDefaultMutable2.put(Long.valueOf(slotValue), Integer.valueOf(iIntValue2 + ((Number) MapsKt.getValue(mapWithDefaultMutable2, Long.valueOf(slotValue))).intValue()));
                        booleanRef.element = true;
                    }
                }
            }
        } while (booleanRef.element);
        dominatedObjectIds.release();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it5 = list.iterator();
        while (it5.hasNext()) {
            Object obj = mapWithDefaultMutable2.get(Long.valueOf(((ReferencePathNode) it5.next()).getObjectId()));
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            arrayList2.add(Integer.valueOf(((Number) obj).intValue()));
        }
        return arrayList2;
    }

    public final List<ReferencePathNode> deduplicateShortestPaths(List<? extends ReferencePathNode> inputPathResults) {
        Intrinsics.checkParameterIsNotNull(inputPathResults, "inputPathResults");
        SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
        if (logger != null) {
            logger.d("start deduplicateShortestPaths");
        }
        TrieNode.ParentNode parentNode = new TrieNode.ParentNode(0L);
        for (ReferencePathNode referencePathNode : inputPathResults) {
            ArrayList arrayList = new ArrayList();
            ReferencePathNode parent = referencePathNode;
            while (parent instanceof ReferencePathNode.ChildNode) {
                arrayList.add(0, Long.valueOf(parent.getObjectId()));
                parent = ((ReferencePathNode.ChildNode) parent).getParent();
            }
            arrayList.add(0, Long.valueOf(parent.getObjectId()));
            updateTrie(referencePathNode, arrayList, 0, parentNode);
        }
        ArrayList arrayList2 = new ArrayList();
        findResultsInTrie(parentNode, arrayList2);
        SharkLog.Logger logger2 = SharkLog.INSTANCE.getLogger();
        if (logger2 != null) {
            logger2.d("end deduplicateShortestPaths");
        }
        return arrayList2;
    }

    public final Pair<List<ApplicationLeak>, List<LibraryLeak>> findLeaks(FindLeakInput findLeakInput, Set<Long> set, boolean z) {
        Intrinsics.checkParameterIsNotNull(findLeakInput, "$this$findLeaks");
        Intrinsics.checkParameterIsNotNull(set, "leakingObjectIds");
        SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
        if (logger != null) {
            logger.d("start findLeaks");
        }
        PathFinder.PathFindingResults pathFindingResultsFindPathsFromGcRoots = new PathFinder(findLeakInput.getGraph(), this.listener, findLeakInput.getReferenceMatchers(), z).findPathsFromGcRoots(set, findLeakInput.getComputeRetainedHeapSize());
        SharkLog.Logger logger2 = SharkLog.INSTANCE.getLogger();
        if (logger2 != null) {
            logger2.d("Found " + set.size() + " retained objects");
        }
        return buildLeakTraces(findLeakInput, pathFindingResultsFindPathsFromGcRoots);
    }

    public final void findResultsInTrie(TrieNode.ParentNode parentNode, List<ReferencePathNode> outputPathResults) {
        Intrinsics.checkParameterIsNotNull(parentNode, "parentNode");
        Intrinsics.checkParameterIsNotNull(outputPathResults, "outputPathResults");
        for (TrieNode trieNode : parentNode.getChildren().values()) {
            if (trieNode instanceof TrieNode.ParentNode) {
                findResultsInTrie((TrieNode.ParentNode) trieNode, outputPathResults);
            } else if (trieNode instanceof TrieNode.LeafNode) {
                outputPathResults.add(((TrieNode.LeafNode) trieNode).getPathNode());
            }
        }
    }

    public final OnAnalysisProgressListener getListener() {
        return this.listener;
    }

    public final String recordClassName(HeapObject heap) {
        Intrinsics.checkParameterIsNotNull(heap, "heap");
        if (heap instanceof HeapObject.HeapClass) {
            return ((HeapObject.HeapClass) heap).getName();
        }
        if (heap instanceof HeapObject.HeapInstance) {
            return ((HeapObject.HeapInstance) heap).getInstanceClassName();
        }
        if (heap instanceof HeapObject.HeapObjectArray) {
            return ((HeapObject.HeapObjectArray) heap).getArrayClassName();
        }
        if (heap instanceof HeapObject.HeapPrimitiveArray) {
            return ((HeapObject.HeapPrimitiveArray) heap).getArrayClassName();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Pair<LeakTraceObject.LeakingStatus, String> resolveStatus(ObjectReporter reporter, boolean leakingWins) {
        String strJoinToString$default;
        Intrinsics.checkParameterIsNotNull(reporter, "reporter");
        LeakTraceObject.LeakingStatus leakingStatus = LeakTraceObject.LeakingStatus.UNKNOWN;
        if (!reporter.getNotLeakingReasons().isEmpty()) {
            leakingStatus = LeakTraceObject.LeakingStatus.NOT_LEAKING;
            strJoinToString$default = CollectionsKt.joinToString$default(reporter.getNotLeakingReasons(), " and ", null, null, 0, null, null, 62, null);
        } else {
            strJoinToString$default = "";
        }
        Set<String> leakingReasons = reporter.getLeakingReasons();
        if (!leakingReasons.isEmpty()) {
            String strJoinToString$default2 = CollectionsKt.joinToString$default(leakingReasons, " and ", null, null, 0, null, null, 62, null);
            if (leakingStatus != LeakTraceObject.LeakingStatus.NOT_LEAKING) {
                leakingStatus = LeakTraceObject.LeakingStatus.LEAKING;
                strJoinToString$default = strJoinToString$default2;
            } else if (leakingWins) {
                leakingStatus = LeakTraceObject.LeakingStatus.LEAKING;
                strJoinToString$default = strJoinToString$default2 + ". Conflicts with " + strJoinToString$default;
            } else {
                strJoinToString$default = strJoinToString$default + ". Conflicts with " + strJoinToString$default2;
            }
        }
        return TuplesKt.to(leakingStatus, strJoinToString$default);
    }

    public final long since(long analysisStartNanoTime) {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - analysisStartNanoTime);
    }

    public final void updateTrie(ReferencePathNode pathNode, List<Long> path, int pathIndex, final TrieNode.ParentNode parentNode) {
        Intrinsics.checkParameterIsNotNull(pathNode, "pathNode");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(parentNode, "parentNode");
        final long jLongValue = path.get(pathIndex).longValue();
        if (pathIndex == CollectionsKt.getLastIndex(path)) {
            parentNode.getChildren().put(Long.valueOf(jLongValue), new TrieNode.LeafNode(jLongValue, pathNode));
            return;
        }
        TrieNode.ParentNode parentNodeInvoke = parentNode.getChildren().get(Long.valueOf(jLongValue));
        if (parentNodeInvoke == null) {
            parentNodeInvoke = new Function0<TrieNode.ParentNode>() {
                {
                    super(0);
                }

                @Override
                public final HeapAnalyzer.TrieNode.ParentNode invoke() {
                    HeapAnalyzer.TrieNode.ParentNode parentNode2 = new HeapAnalyzer.TrieNode.ParentNode(jLongValue);
                    parentNode.getChildren().put(Long.valueOf(jLongValue), parentNode2);
                    return parentNode2;
                }
            }.invoke();
        }
        if (parentNodeInvoke instanceof TrieNode.ParentNode) {
            updateTrie(pathNode, path, pathIndex + 1, (TrieNode.ParentNode) parentNodeInvoke);
        }
    }
}
