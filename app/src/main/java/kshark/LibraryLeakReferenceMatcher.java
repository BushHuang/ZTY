package kshark;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\b\u0010\u001a\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lkshark/LibraryLeakReferenceMatcher;", "Lkshark/ReferenceMatcher;", "pattern", "Lkshark/ReferencePattern;", "description", "", "patternApplies", "Lkotlin/Function1;", "Lkshark/HeapGraph;", "", "(Lkshark/ReferencePattern;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getDescription", "()Ljava/lang/String;", "getPattern", "()Lkshark/ReferencePattern;", "getPatternApplies", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "shark"}, k = 1, mv = {1, 1, 15})
public final class LibraryLeakReferenceMatcher extends ReferenceMatcher {
    private final String description;
    private final ReferencePattern pattern;
    private final Function1<HeapGraph, Boolean> patternApplies;

    public LibraryLeakReferenceMatcher(ReferencePattern referencePattern, String str, Function1<? super HeapGraph, Boolean> function1) {
        super(null);
        Intrinsics.checkParameterIsNotNull(referencePattern, "pattern");
        Intrinsics.checkParameterIsNotNull(str, "description");
        Intrinsics.checkParameterIsNotNull(function1, "patternApplies");
        this.pattern = referencePattern;
        this.description = str;
        this.patternApplies = function1;
    }

    public LibraryLeakReferenceMatcher(ReferencePattern referencePattern, String str, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(referencePattern, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? new Function1<HeapGraph, Boolean>() {
            @Override
            public Boolean invoke(HeapGraph heapGraph) {
                return Boolean.valueOf(invoke2(heapGraph));
            }

            public final boolean invoke2(HeapGraph heapGraph) {
                Intrinsics.checkParameterIsNotNull(heapGraph, "it");
                return true;
            }
        } : anonymousClass1);
    }

    public static LibraryLeakReferenceMatcher copy$default(LibraryLeakReferenceMatcher libraryLeakReferenceMatcher, ReferencePattern referencePattern, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            referencePattern = libraryLeakReferenceMatcher.getPattern();
        }
        if ((i & 2) != 0) {
            str = libraryLeakReferenceMatcher.description;
        }
        if ((i & 4) != 0) {
            function1 = libraryLeakReferenceMatcher.patternApplies;
        }
        return libraryLeakReferenceMatcher.copy(referencePattern, str, function1);
    }

    public final ReferencePattern component1() {
        return getPattern();
    }

    public final String getDescription() {
        return this.description;
    }

    public final Function1<HeapGraph, Boolean> component3() {
        return this.patternApplies;
    }

    public final LibraryLeakReferenceMatcher copy(ReferencePattern pattern, String description, Function1<? super HeapGraph, Boolean> patternApplies) {
        Intrinsics.checkParameterIsNotNull(pattern, "pattern");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(patternApplies, "patternApplies");
        return new LibraryLeakReferenceMatcher(pattern, description, patternApplies);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryLeakReferenceMatcher)) {
            return false;
        }
        LibraryLeakReferenceMatcher libraryLeakReferenceMatcher = (LibraryLeakReferenceMatcher) other;
        return Intrinsics.areEqual(getPattern(), libraryLeakReferenceMatcher.getPattern()) && Intrinsics.areEqual(this.description, libraryLeakReferenceMatcher.description) && Intrinsics.areEqual(this.patternApplies, libraryLeakReferenceMatcher.patternApplies);
    }

    public final String getDescription() {
        return this.description;
    }

    @Override
    public ReferencePattern getPattern() {
        return this.pattern;
    }

    public final Function1<HeapGraph, Boolean> getPatternApplies() {
        return this.patternApplies;
    }

    public int hashCode() {
        ReferencePattern pattern = getPattern();
        int iHashCode = (pattern != null ? pattern.hashCode() : 0) * 31;
        String str = this.description;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        Function1<HeapGraph, Boolean> function1 = this.patternApplies;
        return iHashCode2 + (function1 != null ? function1.hashCode() : 0);
    }

    public String toString() {
        return "library leak: " + getPattern();
    }
}
