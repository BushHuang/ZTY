package kshark;

import java.util.List;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import kshark.HeapObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010 \u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020\"H&J\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%H&J\u0012\u0010&\u001a\u0004\u0018\u00010\u001b2\u0006\u0010$\u001a\u00020%H&J\u0010\u0010'\u001a\u00020(2\u0006\u0010$\u001a\u00020%H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0006R\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0006R\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0006R\u0018\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0006¨\u0006)"}, d2 = {"Lkshark/HeapGraph;", "", "classes", "Lkotlin/sequences/Sequence;", "Lkshark/HeapObject$HeapClass;", "getClasses", "()Lkotlin/sequences/Sequence;", "context", "Lkshark/GraphContext;", "getContext", "()Lkshark/GraphContext;", "gcRoots", "", "Lkshark/GcRoot;", "getGcRoots", "()Ljava/util/List;", "identifierByteSize", "", "getIdentifierByteSize", "()I", "instances", "Lkshark/HeapObject$HeapInstance;", "getInstances", "objectArrays", "Lkshark/HeapObject$HeapObjectArray;", "getObjectArrays", "objects", "Lkshark/HeapObject;", "getObjects", "primitiveArrays", "Lkshark/HeapObject$HeapPrimitiveArray;", "getPrimitiveArrays", "findClassByName", "className", "", "findObjectById", "objectId", "", "findObjectByIdOrNull", "objectExists", "", "shark"}, k = 1, mv = {1, 1, 15})
public interface HeapGraph {
    HeapObject.HeapClass findClassByName(String className);

    HeapObject findObjectById(long objectId) throws IllegalArgumentException;

    HeapObject findObjectByIdOrNull(long objectId);

    Sequence<HeapObject.HeapClass> getClasses();

    GraphContext getContext();

    List<GcRoot> getGcRoots();

    int getIdentifierByteSize();

    Sequence<HeapObject.HeapInstance> getInstances();

    Sequence<HeapObject.HeapObjectArray> getObjectArrays();

    Sequence<HeapObject> getObjects();

    Sequence<HeapObject.HeapPrimitiveArray> getPrimitiveArrays();

    boolean objectExists(long objectId);
}
