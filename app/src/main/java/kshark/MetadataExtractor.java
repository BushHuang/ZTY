package kshark;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u001c\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lkshark/MetadataExtractor;", "", "extractMetadata", "", "", "graph", "Lkshark/HeapGraph;", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public interface MetadataExtractor {

    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0007\u001a\u00020\u00042 \b\u0004\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b0\tH\u0086\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lkshark/MetadataExtractor$Companion;", "", "()V", "NO_OP", "Lkshark/MetadataExtractor;", "getNO_OP", "()Lkshark/MetadataExtractor;", "invoke", "block", "Lkotlin/Function1;", "Lkshark/HeapGraph;", "", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();
        private static final MetadataExtractor NO_OP = new MetadataExtractor() {
            @Override
            public Map<String, String> extractMetadata(HeapGraph graph) {
                Intrinsics.checkParameterIsNotNull(graph, "graph");
                return MapsKt.emptyMap();
            }
        };

        private Companion() {
        }

        public final MetadataExtractor getNO_OP() {
            return NO_OP;
        }

        public final MetadataExtractor invoke(final Function1<? super HeapGraph, ? extends Map<String, String>> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new MetadataExtractor() {
                @Override
                public Map<String, String> extractMetadata(HeapGraph graph) {
                    Intrinsics.checkParameterIsNotNull(graph, "graph");
                    return (Map) block.invoke(graph);
                }
            };
        }
    }

    Map<String, String> extractMetadata(HeapGraph graph);
}
