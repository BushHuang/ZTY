package kshark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kshark.HeapObject;
import kshark.SharkLog;
import kshark.internal.KeyedWeakReferenceMirror;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lkshark/KeyedWeakReferenceFinder;", "Lkshark/LeakingObjectFinder;", "()V", "findKeyedWeakReferences", "", "Lkshark/internal/KeyedWeakReferenceMirror;", "graph", "Lkshark/HeapGraph;", "findKeyedWeakReferences$shark", "findLeakingObjectIds", "", "", "shark"}, k = 1, mv = {1, 1, 15})
public final class KeyedWeakReferenceFinder implements LeakingObjectFinder {
    public static final KeyedWeakReferenceFinder INSTANCE = new KeyedWeakReferenceFinder();

    private KeyedWeakReferenceFinder() {
    }

    public final List<KeyedWeakReferenceMirror> findKeyedWeakReferences$shark(final HeapGraph graph) {
        Intrinsics.checkParameterIsNotNull(graph, "graph");
        return (List) graph.getContext().getOrPut(ObjectInspectors.KEYED_WEAK_REFERENCE.name(), new Function0<List<? extends KeyedWeakReferenceMirror>>() {
            {
                super(0);
            }

            @Override
            public final List<? extends KeyedWeakReferenceMirror> invoke() {
                SharkLog.Logger logger;
                HeapField heapField;
                HeapValue value;
                HeapObject.HeapClass heapClassFindClassByName = graph.findClassByName("leakcanary.KeyedWeakReference");
                final Long asLong = null;
                if (heapClassFindClassByName != null && (heapField = heapClassFindClassByName.get("heapDumpUptimeMillis")) != null && (value = heapField.getValue()) != null) {
                    asLong = value.getAsLong();
                }
                if (asLong == null && (logger = SharkLog.INSTANCE.getLogger()) != null) {
                    logger.d("leakcanary.KeyedWeakReference.heapDumpUptimeMillis field not found, this must be a heap dump from an older version of LeakCanary.");
                }
                List<? extends KeyedWeakReferenceMirror> list = SequencesKt.toList(SequencesKt.filter(SequencesKt.map(SequencesKt.filter(graph.getInstances(), new Function1<HeapObject.HeapInstance, Boolean>() {
                    @Override
                    public Boolean invoke(HeapObject.HeapInstance heapInstance) {
                        return Boolean.valueOf(invoke2(heapInstance));
                    }

                    public final boolean invoke2(HeapObject.HeapInstance heapInstance) {
                        Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                        String instanceClassName = heapInstance.getInstanceClassName();
                        return Intrinsics.areEqual(instanceClassName, "leakcanary.KeyedWeakReference") || Intrinsics.areEqual(instanceClassName, "com.squareup.leakcanary.KeyedWeakReference");
                    }
                }), new Function1<HeapObject.HeapInstance, KeyedWeakReferenceMirror>() {
                    {
                        super(1);
                    }

                    @Override
                    public final KeyedWeakReferenceMirror invoke(HeapObject.HeapInstance heapInstance) {
                        Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                        return KeyedWeakReferenceMirror.INSTANCE.fromInstance(heapInstance, asLong);
                    }
                }), new Function1<KeyedWeakReferenceMirror, Boolean>() {
                    @Override
                    public Boolean invoke(KeyedWeakReferenceMirror keyedWeakReferenceMirror) {
                        return Boolean.valueOf(invoke2(keyedWeakReferenceMirror));
                    }

                    public final boolean invoke2(KeyedWeakReferenceMirror keyedWeakReferenceMirror) {
                        Intrinsics.checkParameterIsNotNull(keyedWeakReferenceMirror, "it");
                        return keyedWeakReferenceMirror.getHasReferent();
                    }
                }));
                graph.getContext().set(ObjectInspectors.KEYED_WEAK_REFERENCE.name(), list);
                return list;
            }
        });
    }

    @Override
    public Set<Long> findLeakingObjectIds(HeapGraph graph) {
        Intrinsics.checkParameterIsNotNull(graph, "graph");
        List<KeyedWeakReferenceMirror> listFindKeyedWeakReferences$shark = findKeyedWeakReferences$shark(graph);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFindKeyedWeakReferences$shark, 10));
        Iterator<T> it = listFindKeyedWeakReferences$shark.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((KeyedWeakReferenceMirror) it.next()).getReferent().getValue()));
        }
        return CollectionsKt.toSet(arrayList);
    }
}
