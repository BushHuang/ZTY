package kshark;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kshark.internal.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J-\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u0012HÖ\u0001J\r\u0010!\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\"J\b\u0010#\u001a\u00020\bH\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u0014\u0010\u0016\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000b¨\u0006%"}, d2 = {"Lkshark/LibraryLeak;", "Lkshark/Leak;", "leakTraces", "", "Lkshark/LeakTrace;", "pattern", "Lkshark/ReferencePattern;", "description", "", "(Ljava/util/List;Lkshark/ReferencePattern;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "leakTrace", "getLeakTraces", "()Ljava/util/List;", "getPattern", "()Lkshark/ReferencePattern;", "retainedHeapByteSize", "", "Ljava/lang/Integer;", "shortDescription", "getShortDescription", "signature", "getSignature", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "leakTraceFromV20", "leakTraceFromV20$shark", "toString", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class LibraryLeak extends Leak {
    private static final long serialVersionUID = 3943636164568681903L;
    private final String description;
    private final LeakTrace leakTrace;
    private final List<LeakTrace> leakTraces;
    private final ReferencePattern pattern;
    private final Integer retainedHeapByteSize;

    public LibraryLeak(List<LeakTrace> list, ReferencePattern referencePattern, String str) {
        super(null);
        Intrinsics.checkParameterIsNotNull(list, "leakTraces");
        Intrinsics.checkParameterIsNotNull(referencePattern, "pattern");
        Intrinsics.checkParameterIsNotNull(str, "description");
        this.leakTraces = list;
        this.pattern = referencePattern;
        this.description = str;
    }

    public static LibraryLeak copy$default(LibraryLeak libraryLeak, List list, ReferencePattern referencePattern, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = libraryLeak.getLeakTraces();
        }
        if ((i & 2) != 0) {
            referencePattern = libraryLeak.pattern;
        }
        if ((i & 4) != 0) {
            str = libraryLeak.description;
        }
        return libraryLeak.copy(list, referencePattern, str);
    }

    public final List<LeakTrace> component1() {
        return getLeakTraces();
    }

    public final ReferencePattern getPattern() {
        return this.pattern;
    }

    public final String getDescription() {
        return this.description;
    }

    public final LibraryLeak copy(List<LeakTrace> leakTraces, ReferencePattern pattern, String description) {
        Intrinsics.checkParameterIsNotNull(leakTraces, "leakTraces");
        Intrinsics.checkParameterIsNotNull(pattern, "pattern");
        Intrinsics.checkParameterIsNotNull(description, "description");
        return new LibraryLeak(leakTraces, pattern, description);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryLeak)) {
            return false;
        }
        LibraryLeak libraryLeak = (LibraryLeak) other;
        return Intrinsics.areEqual(getLeakTraces(), libraryLeak.getLeakTraces()) && Intrinsics.areEqual(this.pattern, libraryLeak.pattern) && Intrinsics.areEqual(this.description, libraryLeak.description);
    }

    public final String getDescription() {
        return this.description;
    }

    @Override
    public List<LeakTrace> getLeakTraces() {
        return this.leakTraces;
    }

    public final ReferencePattern getPattern() {
        return this.pattern;
    }

    @Override
    public String getShortDescription() {
        return this.pattern.toString();
    }

    @Override
    public String getSignature() {
        return StringsKt.createSHA1Hash(this.pattern.toString());
    }

    public int hashCode() {
        List<LeakTrace> leakTraces = getLeakTraces();
        int iHashCode = (leakTraces != null ? leakTraces.hashCode() : 0) * 31;
        ReferencePattern referencePattern = this.pattern;
        int iHashCode2 = (iHashCode + (referencePattern != null ? referencePattern.hashCode() : 0)) * 31;
        String str = this.description;
        return iHashCode2 + (str != null ? str.hashCode() : 0);
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
        return "Leak pattern: " + this.pattern + "\nDescription: " + this.description + '\n' + super.toString() + '\n';
    }
}
