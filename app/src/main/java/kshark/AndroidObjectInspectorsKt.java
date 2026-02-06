package kshark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kshark.HeapObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002\u001a\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0082\u0004\u001a\u000e\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u000bH\u0000¨\u0006\f"}, d2 = {"applyFromField", "", "Lkshark/ObjectReporter;", "inspector", "Lkshark/ObjectInspector;", "field", "Lkshark/HeapField;", "describedWithValue", "", "valueDescription", "unwrapActivityContext", "Lkshark/HeapObject$HeapInstance;", "shark"}, k = 2, mv = {1, 1, 15})
public final class AndroidObjectInspectorsKt {
    private static final void applyFromField(ObjectReporter objectReporter, ObjectInspector objectInspector, HeapField heapField) {
        if (heapField == null || heapField.getValue().isNullReference()) {
            return;
        }
        HeapObject asObject = heapField.getValue().getAsObject();
        if (asObject == null) {
            Intrinsics.throwNpe();
        }
        ObjectReporter objectReporter2 = new ObjectReporter(asObject);
        objectInspector.inspect(objectReporter2);
        String str = heapField.getDeclaringClass().getSimpleName() + '#' + heapField.getName() + ':';
        LinkedHashSet<String> labels = objectReporter.getLabels();
        LinkedHashSet<String> labels2 = objectReporter2.getLabels();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(labels2, 10));
        Iterator<T> it = labels2.iterator();
        while (it.hasNext()) {
            arrayList.add(str + ' ' + ((String) it.next()));
        }
        CollectionsKt.addAll(labels, arrayList);
        Set<String> leakingReasons = objectReporter.getLeakingReasons();
        Set<String> leakingReasons2 = objectReporter2.getLeakingReasons();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(leakingReasons2, 10));
        Iterator<T> it2 = leakingReasons2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(str + ' ' + ((String) it2.next()));
        }
        CollectionsKt.addAll(leakingReasons, arrayList2);
        Set<String> notLeakingReasons = objectReporter.getNotLeakingReasons();
        Set<String> notLeakingReasons2 = objectReporter2.getNotLeakingReasons();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(notLeakingReasons2, 10));
        Iterator<T> it3 = notLeakingReasons2.iterator();
        while (it3.hasNext()) {
            arrayList3.add(str + ' ' + ((String) it3.next()));
        }
        CollectionsKt.addAll(notLeakingReasons, arrayList3);
    }

    private static final String describedWithValue(HeapField heapField, String str) {
        return heapField.getDeclaringClass().getSimpleName() + '#' + heapField.getName() + " is " + str;
    }

    public static final HeapObject.HeapInstance unwrapActivityContext(HeapObject.HeapInstance heapInstance) {
        HeapObject.HeapInstance asInstance;
        HeapField heapField;
        Intrinsics.checkParameterIsNotNull(heapInstance, "$this$unwrapActivityContext");
        if (heapInstance.instanceOf("android.app.Activity")) {
            return heapInstance;
        }
        if (!heapInstance.instanceOf("android.content.ContextWrapper")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            boolean z = true;
            while (z) {
                arrayList.add(Long.valueOf(heapInstance.getObjectId()));
                z = false;
                HeapField heapField2 = heapInstance.get("android.content.ContextWrapper", "mBase");
                if (heapField2 == null) {
                    Intrinsics.throwNpe();
                }
                HeapValue value = heapField2.getValue();
                if (value.isNonNullReference()) {
                    HeapObject asObject = value.getAsObject();
                    if (asObject == null) {
                        Intrinsics.throwNpe();
                    }
                    asInstance = asObject.getAsInstance();
                    if (asInstance == null) {
                        Intrinsics.throwNpe();
                    }
                    if (asInstance.instanceOf("android.app.Activity")) {
                        return asInstance;
                    }
                    if (heapInstance.instanceOf("com.android.internal.policy.DecorContext") && (heapField = heapInstance.get("com.android.internal.policy.DecorContext", "mPhoneWindow")) != null) {
                        HeapObject.HeapInstance valueAsInstance = heapField.getValueAsInstance();
                        if (valueAsInstance == null) {
                            Intrinsics.throwNpe();
                        }
                        HeapField heapField3 = valueAsInstance.get("android.view.Window", "mContext");
                        if (heapField3 == null) {
                            Intrinsics.throwNpe();
                        }
                        asInstance = heapField3.getValueAsInstance();
                        if (asInstance == null) {
                            Intrinsics.throwNpe();
                        }
                        if (asInstance.instanceOf("android.app.Activity")) {
                            return asInstance;
                        }
                    }
                    if (!asInstance.instanceOf("android.content.ContextWrapper") || arrayList.contains(Long.valueOf(asInstance.getObjectId()))) {
                        heapInstance = asInstance;
                    }
                }
            }
            return null;
            heapInstance = asInstance;
        }
    }
}
