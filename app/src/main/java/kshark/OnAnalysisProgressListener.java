package kshark;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kshark.OnAnalysisProgressListener;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00062\u00020\u0001:\u0002\u0006\u0007J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lkshark/OnAnalysisProgressListener;", "", "onAnalysisProgress", "", "step", "Lkshark/OnAnalysisProgressListener$Step;", "Companion", "Step", "shark"}, k = 1, mv = {1, 1, 15})
public interface OnAnalysisProgressListener {

    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0007\u001a\u00020\u00042\u0014\b\u0004\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH\u0086\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lkshark/OnAnalysisProgressListener$Companion;", "", "()V", "NO_OP", "Lkshark/OnAnalysisProgressListener;", "getNO_OP", "()Lkshark/OnAnalysisProgressListener;", "invoke", "block", "Lkotlin/Function1;", "Lkshark/OnAnalysisProgressListener$Step;", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();
        private static final OnAnalysisProgressListener NO_OP = new OnAnalysisProgressListener() {
            @Override
            public void onAnalysisProgress(OnAnalysisProgressListener.Step step) {
                Intrinsics.checkParameterIsNotNull(step, "step");
            }
        };

        private Companion() {
        }

        public final OnAnalysisProgressListener getNO_OP() {
            return NO_OP;
        }

        public final OnAnalysisProgressListener invoke(final Function1<? super Step, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new OnAnalysisProgressListener() {
                @Override
                public void onAnalysisProgress(OnAnalysisProgressListener.Step step) {
                    Intrinsics.checkParameterIsNotNull(step, "step");
                    block.invoke(step);
                }
            };
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lkshark/OnAnalysisProgressListener$Step;", "", "(Ljava/lang/String;I)V", "PARSING_HEAP_DUMP", "EXTRACTING_METADATA", "FINDING_RETAINED_OBJECTS", "FINDING_PATHS_TO_RETAINED_OBJECTS", "FINDING_DOMINATORS", "COMPUTING_NATIVE_RETAINED_SIZE", "COMPUTING_RETAINED_SIZE", "BUILDING_LEAK_TRACES", "REPORTING_HEAP_ANALYSIS", "shark"}, k = 1, mv = {1, 1, 15})
    public enum Step {
        PARSING_HEAP_DUMP,
        EXTRACTING_METADATA,
        FINDING_RETAINED_OBJECTS,
        FINDING_PATHS_TO_RETAINED_OBJECTS,
        FINDING_DOMINATORS,
        COMPUTING_NATIVE_RETAINED_SIZE,
        COMPUTING_RETAINED_SIZE,
        BUILDING_LEAK_TRACES,
        REPORTING_HEAP_ANALYSIS
    }

    void onAnalysisProgress(Step step);
}
