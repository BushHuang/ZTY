package kshark;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lkshark/LeakingObjectFinder;", "", "findLeakingObjectIds", "", "", "graph", "Lkshark/HeapGraph;", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public interface LeakingObjectFinder {

    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u001a\b\u0004\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006H\u0086\n¨\u0006\n"}, d2 = {"Lkshark/LeakingObjectFinder$Companion;", "", "()V", "invoke", "Lkshark/LeakingObjectFinder;", "block", "Lkotlin/Function1;", "Lkshark/HeapGraph;", "", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final LeakingObjectFinder invoke(final Function1<? super HeapGraph, ? extends Set<Long>> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new LeakingObjectFinder() {
                @Override
                public Set<Long> findLeakingObjectIds(HeapGraph graph) {
                    Intrinsics.checkParameterIsNotNull(graph, "graph");
                    return (Set) block.invoke(graph);
                }
            };
        }
    }

    Set<Long> findLeakingObjectIds(HeapGraph graph);
}
