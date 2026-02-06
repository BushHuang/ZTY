package kshark;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kshark.LeakTrace;
import kshark.LeakTraceObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0003\u0014\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkshark/LeakTraceElement;", "Ljava/io/Serializable;", "()V", "className", "", "holder", "Lkshark/LeakTraceElement$Holder;", "labels", "", "leakStatus", "Lkshark/LeakNodeStatus;", "leakStatusReason", "reference", "Lkshark/LeakReference;", "gcRootTypeFromV20", "Lkshark/LeakTrace$GcRootType;", "originObjectFromV20", "Lkshark/LeakTraceObject;", "referencePathElementFromV20", "Lkshark/LeakTraceReference;", "Companion", "Holder", "Type", "shark"}, k = 1, mv = {1, 1, 15})
public final class LeakTraceElement implements Serializable {
    private static final long serialVersionUID = -6795139831875582552L;
    private final String className;
    private final Holder holder;
    private final Set<String> labels;
    private final LeakNodeStatus leakStatus;
    private final String leakStatusReason;
    private final LeakReference reference;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkshark/LeakTraceElement$Holder;", "", "(Ljava/lang/String;I)V", "OBJECT", "CLASS", "THREAD", "ARRAY", "shark"}, k = 1, mv = {1, 1, 15})
    public enum Holder {
        OBJECT,
        CLASS,
        THREAD,
        ARRAY
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkshark/LeakTraceElement$Type;", "", "(Ljava/lang/String;I)V", "INSTANCE_FIELD", "STATIC_FIELD", "LOCAL", "ARRAY_ENTRY", "shark"}, k = 1, mv = {1, 1, 15})
    public enum Type {
        INSTANCE_FIELD,
        STATIC_FIELD,
        LOCAL,
        ARRAY_ENTRY
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Holder.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Holder.OBJECT.ordinal()] = 1;
            $EnumSwitchMapping$0[Holder.CLASS.ordinal()] = 2;
            $EnumSwitchMapping$0[Holder.THREAD.ordinal()] = 3;
            $EnumSwitchMapping$0[Holder.ARRAY.ordinal()] = 4;
            int[] iArr2 = new int[LeakNodeStatus.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[LeakNodeStatus.NOT_LEAKING.ordinal()] = 1;
            $EnumSwitchMapping$1[LeakNodeStatus.LEAKING.ordinal()] = 2;
            $EnumSwitchMapping$1[LeakNodeStatus.UNKNOWN.ordinal()] = 3;
        }
    }

    public final LeakTrace.GcRootType gcRootTypeFromV20() {
        Set<String> set = this.labels;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        for (String str : set) {
            if (StringsKt.startsWith$default(str, "GC Root: ", false, 2, (Object) null)) {
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring = str.substring(9);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
                if (Intrinsics.areEqual(strSubstring, "Thread object")) {
                    return LeakTrace.GcRootType.THREAD_OBJECT;
                }
                if (Intrinsics.areEqual(strSubstring, "Global variable in native code")) {
                    return LeakTrace.GcRootType.JNI_GLOBAL;
                }
                if (Intrinsics.areEqual(strSubstring, "Local variable in native code")) {
                    return LeakTrace.GcRootType.JNI_LOCAL;
                }
                if (Intrinsics.areEqual(strSubstring, "Java local variable")) {
                    return LeakTrace.GcRootType.JAVA_FRAME;
                }
                if (Intrinsics.areEqual(strSubstring, "Input or output parameters in native code")) {
                    return LeakTrace.GcRootType.NATIVE_STACK;
                }
                if (Intrinsics.areEqual(strSubstring, "System class")) {
                    return LeakTrace.GcRootType.STICKY_CLASS;
                }
                if (Intrinsics.areEqual(strSubstring, "Thread block")) {
                    return LeakTrace.GcRootType.THREAD_BLOCK;
                }
                if (Intrinsics.areEqual(strSubstring, "Monitor (anything that called the wait() or notify() methods, or that is synchronized.)")) {
                    return LeakTrace.GcRootType.MONITOR_USED;
                }
                if (Intrinsics.areEqual(strSubstring, "Root JNI monitor")) {
                    return LeakTrace.GcRootType.JNI_MONITOR;
                }
                throw new IllegalStateException("Unexpected gc root label " + strSubstring);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final LeakTraceObject originObjectFromV20() {
        LeakTraceObject.ObjectType objectType;
        LeakTraceObject.LeakingStatus leakingStatus;
        Holder holder = this.holder;
        if (holder == null) {
            Intrinsics.throwNpe();
        }
        int i = WhenMappings.$EnumSwitchMapping$0[holder.ordinal()];
        if (i == 1) {
            objectType = LeakTraceObject.ObjectType.INSTANCE;
        } else if (i == 2) {
            objectType = LeakTraceObject.ObjectType.CLASS;
        } else if (i == 3) {
            objectType = LeakTraceObject.ObjectType.INSTANCE;
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            objectType = LeakTraceObject.ObjectType.ARRAY;
        }
        LeakTraceObject.ObjectType objectType2 = objectType;
        String str = this.className;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        Set<String> set = this.labels;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : set) {
            if (!StringsKt.startsWith$default((String) obj, "GC Root: ", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        Set set2 = CollectionsKt.toSet(arrayList);
        LeakNodeStatus leakNodeStatus = this.leakStatus;
        if (leakNodeStatus == null) {
            Intrinsics.throwNpe();
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[leakNodeStatus.ordinal()];
        if (i2 == 1) {
            leakingStatus = LeakTraceObject.LeakingStatus.NOT_LEAKING;
        } else if (i2 == 2) {
            leakingStatus = LeakTraceObject.LeakingStatus.LEAKING;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            leakingStatus = LeakTraceObject.LeakingStatus.UNKNOWN;
        }
        LeakTraceObject.LeakingStatus leakingStatus2 = leakingStatus;
        String str2 = this.leakStatusReason;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        return new LeakTraceObject(0L, objectType2, str, set2, leakingStatus2, str2);
    }

    public final LeakTraceReference referencePathElementFromV20() {
        LeakReference leakReference = this.reference;
        if (leakReference == null) {
            Intrinsics.throwNpe();
        }
        return leakReference.fromV20(originObjectFromV20());
    }
}
