package kshark;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kshark.HeapObject;
import kshark.HprofRecord;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001d\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkshark/AndroidResourceIdNames;", "", "resourceIds", "", "names", "", "", "([I[Ljava/lang/String;)V", "[Ljava/lang/String;", "get", "id", "", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class AndroidResourceIdNames {

    public static final Companion INSTANCE = new Companion(null);
    public static final int FIRST_APP_RESOURCE_ID = 2130771968;
    public static final int RESOURCE_ID_TYPE_ITERATOR = 65536;
    private static volatile AndroidResourceIdNames holderField;
    private final String[] names;
    private final int[] resourceIds;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\t\u001a\u0004\u0018\u00010\u00042\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0002¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0010J\r\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J2\u0010\u0014\u001a\u00020\u00122\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\u0016"}, d2 = {"Lkshark/AndroidResourceIdNames$Companion;", "", "()V", "FIRST_APP_RESOURCE_ID", "", "RESOURCE_ID_TYPE_ITERATOR", "holderField", "Lkshark/AndroidResourceIdNames;", "holderField$annotations", "findIdTypeResourceIdStart", "getResourceTypeName", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Integer;", "readFromHeap", "graph", "Lkshark/HeapGraph;", "resetForTests", "", "resetForTests$shark", "saveToMemory", "getResourceEntryName", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Integer findIdTypeResourceIdStart(Function1<? super Integer, String> getResourceTypeName) {
            int i = 2130771968;
            while (true) {
                String strInvoke = getResourceTypeName.invoke(Integer.valueOf(i));
                if (strInvoke == null) {
                    return null;
                }
                if (strInvoke.hashCode() == 3355 && strInvoke.equals("id")) {
                    return Integer.valueOf(i);
                }
                i += 65536;
            }
        }

        @JvmStatic
        private static void holderField$annotations() {
        }

        public final AndroidResourceIdNames readFromHeap(final HeapGraph graph) {
            Intrinsics.checkParameterIsNotNull(graph, "graph");
            GraphContext context = graph.getContext();
            String name = AndroidResourceIdNames.class.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "AndroidResourceIdNames::class.java.name");
            return (AndroidResourceIdNames) context.getOrPut(name, new Function0<AndroidResourceIdNames>() {
                {
                    super(0);
                }

                @Override
                public final AndroidResourceIdNames invoke() {
                    String name2 = AndroidResourceIdNames.class.getName();
                    HeapGraph heapGraph = graph;
                    Intrinsics.checkExpressionValueIsNotNull(name2, "className");
                    HeapObject.HeapClass heapClassFindClassByName = heapGraph.findClassByName(name2);
                    DefaultConstructorMarker defaultConstructorMarker = null;
                    if (heapClassFindClassByName == null) {
                        return null;
                    }
                    HeapField heapField = heapClassFindClassByName.get("holderField");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject.HeapInstance valueAsInstance = heapField.getValueAsInstance();
                    if (valueAsInstance == null) {
                        return null;
                    }
                    HeapField heapField2 = valueAsInstance.get(name2, "resourceIds");
                    if (heapField2 == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject.HeapPrimitiveArray valueAsPrimitiveArray = heapField2.getValueAsPrimitiveArray();
                    if (valueAsPrimitiveArray == null) {
                        Intrinsics.throwNpe();
                    }
                    HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord record = valueAsPrimitiveArray.readRecord();
                    if (record == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kshark.HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump");
                    }
                    int[] array = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump) record).getArray();
                    HeapField heapField3 = valueAsInstance.get(name2, "names");
                    if (heapField3 == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject.HeapObjectArray valueAsObjectArray = heapField3.getValueAsObjectArray();
                    if (valueAsObjectArray == null) {
                        Intrinsics.throwNpe();
                    }
                    Object[] array2 = SequencesKt.toList(SequencesKt.map(valueAsObjectArray.readElements(), new Function1<HeapValue, String>() {
                        @Override
                        public final String invoke(HeapValue heapValue) {
                            Intrinsics.checkParameterIsNotNull(heapValue, "it");
                            String asJavaString = heapValue.readAsJavaString();
                            if (asJavaString == null) {
                                Intrinsics.throwNpe();
                            }
                            return asJavaString;
                        }
                    })).toArray(new String[0]);
                    if (array2 != null) {
                        return new AndroidResourceIdNames(array, (String[]) array2, defaultConstructorMarker);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            });
        }

        public final void resetForTests$shark() {
            AndroidResourceIdNames.holderField = (AndroidResourceIdNames) null;
        }

        public final synchronized void saveToMemory(Function1<? super Integer, String> getResourceTypeName, Function1<? super Integer, String> getResourceEntryName) {
            Intrinsics.checkParameterIsNotNull(getResourceTypeName, "getResourceTypeName");
            Intrinsics.checkParameterIsNotNull(getResourceEntryName, "getResourceEntryName");
            if (AndroidResourceIdNames.holderField != null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            Integer numFindIdTypeResourceIdStart = findIdTypeResourceIdStart(getResourceTypeName);
            if (numFindIdTypeResourceIdStart != null) {
                int iIntValue = numFindIdTypeResourceIdStart.intValue();
                while (true) {
                    String strInvoke = getResourceEntryName.invoke(Integer.valueOf(iIntValue));
                    if (strInvoke == null) {
                        break;
                    }
                    arrayList.add(TuplesKt.to(Integer.valueOf(iIntValue), strInvoke));
                    iIntValue++;
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(Integer.valueOf(((Number) ((Pair) it.next()).getFirst()).intValue()));
            }
            int[] intArray = CollectionsKt.toIntArray(arrayList3);
            ArrayList arrayList4 = arrayList;
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                arrayList5.add((String) ((Pair) it2.next()).getSecond());
            }
            Object[] array = arrayList5.toArray(new String[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            AndroidResourceIdNames.holderField = new AndroidResourceIdNames(intArray, (String[]) array, null);
        }
    }

    private AndroidResourceIdNames(int[] iArr, String[] strArr) {
        this.resourceIds = iArr;
        this.names = strArr;
    }

    public AndroidResourceIdNames(int[] iArr, String[] strArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(iArr, strArr);
    }

    public final String get(int id) {
        int iBinarySearch$default = ArraysKt.binarySearch$default(this.resourceIds, id, 0, 0, 6, (Object) null);
        if (iBinarySearch$default >= 0) {
            return this.names[iBinarySearch$default];
        }
        return null;
    }
}
