package kshark;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkshark/FilteringLeakingObjectFinder;", "Lkshark/LeakingObjectFinder;", "filters", "", "Lkshark/FilteringLeakingObjectFinder$LeakingObjectFilter;", "(Ljava/util/List;)V", "findLeakingObjectIds", "", "", "graph", "Lkshark/HeapGraph;", "LeakingObjectFilter", "shark"}, k = 1, mv = {1, 1, 15})
public final class FilteringLeakingObjectFinder implements LeakingObjectFinder {
    private final List<LeakingObjectFilter> filters;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lkshark/FilteringLeakingObjectFinder$LeakingObjectFilter;", "", "isLeakingObject", "", "heapObject", "Lkshark/HeapObject;", "shark"}, k = 1, mv = {1, 1, 15})
    public interface LeakingObjectFilter {
        boolean isLeakingObject(HeapObject heapObject);
    }

    public FilteringLeakingObjectFinder(List<? extends LeakingObjectFilter> list) {
        Intrinsics.checkParameterIsNotNull(list, "filters");
        this.filters = list;
    }

    @Override
    public Set<Long> findLeakingObjectIds(HeapGraph graph) {
        Intrinsics.checkParameterIsNotNull(graph, "graph");
        return SequencesKt.toSet(SequencesKt.map(SequencesKt.filter(graph.getObjects(), new Function1<HeapObject, Boolean>() {
            {
                super(1);
            }

            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                List list = FilteringLeakingObjectFinder.this.filters;
                if ((list instanceof Collection) && list.isEmpty()) {
                    return false;
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((LeakingObjectFilter) it.next()).isLeakingObject(heapObject)) {
                        return true;
                    }
                }
                return false;
            }
        }), new Function1<HeapObject, Long>() {
            public final long invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "it");
                return heapObject.getObjectId();
            }

            @Override
            public Long invoke(HeapObject heapObject) {
                return Long.valueOf(invoke2(heapObject));
            }
        }));
    }
}
