package kshark;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\tH\u0016R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0001\u0002\u0014\u0015¨\u0006\u0016"}, d2 = {"Lkshark/Leak;", "Ljava/io/Serializable;", "()V", "leakTraces", "", "Lkshark/LeakTrace;", "getLeakTraces", "()Ljava/util/List;", "shortDescription", "", "getShortDescription", "()Ljava/lang/String;", "signature", "getSignature", "totalRetainedHeapByteSize", "", "getTotalRetainedHeapByteSize", "()Ljava/lang/Integer;", "toString", "Companion", "Lkshark/LibraryLeak;", "Lkshark/ApplicationLeak;", "shark"}, k = 1, mv = {1, 1, 15})
public abstract class Leak implements Serializable {
    private static final long serialVersionUID = -2287572510360910916L;

    private Leak() {
    }

    public Leak(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract List<LeakTrace> getLeakTraces();

    public abstract String getShortDescription();

    public abstract String getSignature();

    public final Integer getTotalRetainedHeapByteSize() {
        if (((LeakTrace) CollectionsKt.first((List) getLeakTraces())).getRetainedHeapByteSize() == null) {
            return null;
        }
        int iIntValue = 0;
        Iterator<T> it = getLeakTraces().iterator();
        while (it.hasNext()) {
            Integer retainedHeapByteSize = ((LeakTrace) it.next()).getRetainedHeapByteSize();
            if (retainedHeapByteSize == null) {
                Intrinsics.throwNpe();
            }
            iIntValue += retainedHeapByteSize.intValue();
        }
        return Integer.valueOf(iIntValue);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (getTotalRetainedHeapByteSize() != null) {
            str = getTotalRetainedHeapByteSize() + " bytes retained by leaking objects\n";
        } else {
            str = "";
        }
        sb.append(str);
        if (getLeakTraces().size() > 1) {
            str2 = "Displaying only 1 leak trace out of " + getLeakTraces().size() + " with the same signature\n";
        }
        sb.append(str2);
        sb.append("Signature: ");
        sb.append(getSignature());
        sb.append('\n');
        sb.append((LeakTrace) CollectionsKt.first((List) getLeakTraces()));
        return sb.toString();
    }
}
