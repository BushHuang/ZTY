package kshark;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\nHÖ\u0001J\r\u0010\u0019\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\rH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u001d"}, d2 = {"Lkshark/ApplicationLeak;", "Lkshark/Leak;", "leakTraces", "", "Lkshark/LeakTrace;", "(Ljava/util/List;)V", "leakTrace", "getLeakTraces", "()Ljava/util/List;", "retainedHeapByteSize", "", "Ljava/lang/Integer;", "shortDescription", "", "getShortDescription", "()Ljava/lang/String;", "signature", "getSignature", "component1", "copy", "equals", "", "other", "", "hashCode", "leakTraceFromV20", "leakTraceFromV20$shark", "toString", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class ApplicationLeak extends Leak {
    private static final long serialVersionUID = 524928276700576863L;
    private final LeakTrace leakTrace;
    private final List<LeakTrace> leakTraces;
    private final Integer retainedHeapByteSize;

    public ApplicationLeak(List<LeakTrace> list) {
        super(null);
        Intrinsics.checkParameterIsNotNull(list, "leakTraces");
        this.leakTraces = list;
    }

    public static ApplicationLeak copy$default(ApplicationLeak applicationLeak, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = applicationLeak.getLeakTraces();
        }
        return applicationLeak.copy(list);
    }

    public final List<LeakTrace> component1() {
        return getLeakTraces();
    }

    public final ApplicationLeak copy(List<LeakTrace> leakTraces) {
        Intrinsics.checkParameterIsNotNull(leakTraces, "leakTraces");
        return new ApplicationLeak(leakTraces);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof ApplicationLeak) && Intrinsics.areEqual(getLeakTraces(), ((ApplicationLeak) other).getLeakTraces());
        }
        return true;
    }

    @Override
    public List<LeakTrace> getLeakTraces() {
        return this.leakTraces;
    }

    @Override
    public String getShortDescription() {
        LeakTrace leakTrace = (LeakTrace) CollectionsKt.first((List) getLeakTraces());
        LeakTraceReference leakTraceReference = (LeakTraceReference) SequencesKt.firstOrNull(leakTrace.getSuspectReferenceSubpath());
        if (leakTraceReference != null) {
            String str = leakTraceReference.getOriginObject().getClassSimpleName() + "." + leakTraceReference.getReferenceGenericName();
            if (str != null) {
                return str;
            }
        }
        return leakTrace.getLeakingObject().getClassName();
    }

    @Override
    public String getSignature() {
        return ((LeakTrace) CollectionsKt.first((List) getLeakTraces())).getSignature();
    }

    public int hashCode() {
        List<LeakTrace> leakTraces = getLeakTraces();
        if (leakTraces != null) {
            return leakTraces.hashCode();
        }
        return 0;
    }

    public final LeakTrace leakTraceFromV20$shark() {
        LeakTrace leakTrace = this.leakTrace;
        if (leakTrace == null) {
            Intrinsics.throwNpe();
        }
        return leakTrace.fromV20$shark(this.retainedHeapByteSize);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
