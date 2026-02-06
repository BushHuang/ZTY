package kshark;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kshark.HeapObject;
import kshark.HprofRecord;
import kshark.ValueHolder;
import kshark.internal.FieldValuesReader;
import kshark.internal.IndexedObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b6\u0018\u0000 \u001d2\u00020\u0001:\u0005\u001d\u001e\u001f !B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH&R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0001\u0004\u0004\b\f\u0010¨\u0006\""}, d2 = {"Lkshark/HeapObject;", "", "()V", "asClass", "Lkshark/HeapObject$HeapClass;", "getAsClass", "()Lkshark/HeapObject$HeapClass;", "asInstance", "Lkshark/HeapObject$HeapInstance;", "getAsInstance", "()Lkshark/HeapObject$HeapInstance;", "asObjectArray", "Lkshark/HeapObject$HeapObjectArray;", "getAsObjectArray", "()Lkshark/HeapObject$HeapObjectArray;", "asPrimitiveArray", "Lkshark/HeapObject$HeapPrimitiveArray;", "getAsPrimitiveArray", "()Lkshark/HeapObject$HeapPrimitiveArray;", "graph", "Lkshark/HeapGraph;", "getGraph", "()Lkshark/HeapGraph;", "objectId", "", "getObjectId", "()J", "readRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord;", "Companion", "HeapClass", "HeapInstance", "HeapObjectArray", "HeapPrimitiveArray", "shark"}, k = 1, mv = {1, 1, 15})
public abstract class HeapObject {

    public static final Companion INSTANCE = new Companion(null);
    private static final Map<String, PrimitiveType> primitiveArrayClassesByName;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkshark/HeapObject$Companion;", "", "()V", "primitiveArrayClassesByName", "", "", "Lkshark/PrimitiveType;", "classSimpleName", "className", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String classSimpleName(String className) {
            int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) className, '.', 0, false, 6, (Object) null);
            if (iLastIndexOf$default == -1) {
                return className;
            }
            int i = iLastIndexOf$default + 1;
            if (className == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = className.substring(i);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return strSubstring;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020#H\u0086\u0002J\u0006\u00108\u001a\u00020\u0016J\b\u00109\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020#J\f\u0010<\u001a\b\u0012\u0004\u0012\u0002060\nJ\u0011\u0010=\u001a\u00020\u001e2\u0006\u00102\u001a\u00020\u0000H\u0086\u0004J\u0011\u0010>\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020\u0000H\u0086\u0004J\b\u0010@\u001a\u00020#H\u0016R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\n8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\n8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR\u0011\u0010\u001b\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0011\u0010 \u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b \u0010\u001fR\u0011\u0010!\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001fR\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\n8F¢\u0006\u0006\u001a\u0004\b(\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\n8F¢\u0006\u0006\u001a\u0004\b-\u0010\rR\u0011\u0010.\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b/\u0010%R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00000\n8F¢\u0006\u0006\u001a\u0004\b1\u0010\rR\u0013\u00102\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b3\u00104¨\u0006A"}, d2 = {"Lkshark/HeapObject$HeapClass;", "Lkshark/HeapObject;", "hprofGraph", "Lkshark/HprofHeapGraph;", "indexedObject", "Lkshark/internal/IndexedObject$IndexedClass;", "objectId", "", "(Lkshark/HprofHeapGraph;Lkshark/internal/IndexedObject$IndexedClass;J)V", "_classHierarchy", "Lkotlin/sequences/Sequence;", "classHierarchy", "getClassHierarchy", "()Lkotlin/sequences/Sequence;", "directInstances", "Lkshark/HeapObject$HeapInstance;", "getDirectInstances", "graph", "Lkshark/HeapGraph;", "getGraph", "()Lkshark/HeapGraph;", "instanceByteSize", "", "getInstanceByteSize", "()I", "instances", "getInstances", "instancesCount", "getInstancesCount", "isArrayClass", "", "()Z", "isObjectArrayClass", "isPrimitiveArrayClass", "name", "", "getName", "()Ljava/lang/String;", "objectArrayInstances", "Lkshark/HeapObject$HeapObjectArray;", "getObjectArrayInstances", "getObjectId", "()J", "primitiveArrayInstances", "Lkshark/HeapObject$HeapPrimitiveArray;", "getPrimitiveArrayInstances", "simpleName", "getSimpleName", "subclasses", "getSubclasses", "superclass", "getSuperclass", "()Lkshark/HeapObject$HeapClass;", "get", "Lkshark/HeapField;", "fieldName", "readFieldsByteSize", "readRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ClassDumpRecord;", "readStaticField", "readStaticFields", "subclassOf", "superclassOf", "subclass", "toString", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class HeapClass extends HeapObject {
        private Sequence<HeapClass> _classHierarchy;
        private final HprofHeapGraph hprofGraph;
        private final IndexedObject.IndexedClass indexedObject;
        private final long objectId;

        public HeapClass(HprofHeapGraph hprofHeapGraph, IndexedObject.IndexedClass indexedClass, long j) {
            super(null);
            Intrinsics.checkParameterIsNotNull(hprofHeapGraph, "hprofGraph");
            Intrinsics.checkParameterIsNotNull(indexedClass, "indexedObject");
            this.hprofGraph = hprofHeapGraph;
            this.indexedObject = indexedClass;
            this.objectId = j;
        }

        public final HeapField get(String fieldName) {
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            return readStaticField(fieldName);
        }

        public final Sequence<HeapClass> getClassHierarchy() {
            if (this._classHierarchy == null) {
                this._classHierarchy = SequencesKt.generateSequence(this, new Function1<HeapClass, HeapClass>() {
                    @Override
                    public final HeapObject.HeapClass invoke(HeapObject.HeapClass heapClass) {
                        Intrinsics.checkParameterIsNotNull(heapClass, "it");
                        return heapClass.getSuperclass();
                    }
                });
            }
            Sequence<HeapClass> sequence = this._classHierarchy;
            if (sequence == null) {
                Intrinsics.throwNpe();
            }
            return sequence;
        }

        public final Sequence<HeapInstance> getDirectInstances() {
            return SequencesKt.filter(this.hprofGraph.getInstances(), new Function1<HeapInstance, Boolean>() {
                {
                    super(1);
                }

                @Override
                public Boolean invoke(HeapObject.HeapInstance heapInstance) {
                    return Boolean.valueOf(invoke2(heapInstance));
                }

                public final boolean invoke2(HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                    return heapInstance.getIndexedObject().getClassId() == this.this$0.getObjectId();
                }
            });
        }

        @Override
        public HeapGraph getGraph() {
            return this.hprofGraph;
        }

        public final int getInstanceByteSize() {
            return this.indexedObject.getInstanceSize();
        }

        public final Sequence<HeapInstance> getInstances() {
            return !isArrayClass() ? SequencesKt.filter(this.hprofGraph.getInstances(), new Function1<HeapInstance, Boolean>() {
                {
                    super(1);
                }

                @Override
                public Boolean invoke(HeapObject.HeapInstance heapInstance) {
                    return Boolean.valueOf(invoke2(heapInstance));
                }

                public final boolean invoke2(HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                    return heapInstance.instanceOf(this.this$0);
                }
            }) : SequencesKt.emptySequence();
        }

        public final int getInstancesCount() {
            if (isArrayClass()) {
                return 0;
            }
            return SequencesKt.count(SequencesKt.filter(this.hprofGraph.getInstances(), new Function1<HeapInstance, Boolean>() {
                {
                    super(1);
                }

                @Override
                public Boolean invoke(HeapObject.HeapInstance heapInstance) {
                    return Boolean.valueOf(invoke2(heapInstance));
                }

                public final boolean invoke2(HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                    return heapInstance.instanceOf(this.this$0);
                }
            }));
        }

        public final String getName() {
            return this.hprofGraph.className$shark(getObjectId());
        }

        public final Sequence<HeapObjectArray> getObjectArrayInstances() {
            return isObjectArrayClass() ? SequencesKt.filter(this.hprofGraph.getObjectArrays(), new Function1<HeapObjectArray, Boolean>() {
                {
                    super(1);
                }

                @Override
                public Boolean invoke(HeapObject.HeapObjectArray heapObjectArray) {
                    return Boolean.valueOf(invoke2(heapObjectArray));
                }

                public final boolean invoke2(HeapObject.HeapObjectArray heapObjectArray) {
                    Intrinsics.checkParameterIsNotNull(heapObjectArray, "it");
                    return heapObjectArray.getIndexedObject().getArrayClassId() == this.this$0.getObjectId();
                }
            }) : SequencesKt.emptySequence();
        }

        @Override
        public long getObjectId() {
            return this.objectId;
        }

        public final Sequence<HeapPrimitiveArray> getPrimitiveArrayInstances() {
            if (!isPrimitiveArrayClass()) {
                return SequencesKt.emptySequence();
            }
            final PrimitiveType primitiveType = (PrimitiveType) HeapObject.primitiveArrayClassesByName.get(getName());
            return SequencesKt.filter(this.hprofGraph.getPrimitiveArrays(), new Function1<HeapPrimitiveArray, Boolean>() {
                {
                    super(1);
                }

                @Override
                public Boolean invoke(HeapObject.HeapPrimitiveArray heapPrimitiveArray) {
                    return Boolean.valueOf(invoke2(heapPrimitiveArray));
                }

                public final boolean invoke2(HeapObject.HeapPrimitiveArray heapPrimitiveArray) {
                    Intrinsics.checkParameterIsNotNull(heapPrimitiveArray, "it");
                    return heapPrimitiveArray.getPrimitiveType() == primitiveType;
                }
            });
        }

        public final String getSimpleName() {
            return HeapObject.INSTANCE.classSimpleName(getName());
        }

        public final Sequence<HeapClass> getSubclasses() {
            return SequencesKt.filter(this.hprofGraph.getClasses(), new Function1<HeapClass, Boolean>() {
                {
                    super(1);
                }

                @Override
                public Boolean invoke(HeapObject.HeapClass heapClass) {
                    return Boolean.valueOf(invoke2(heapClass));
                }

                public final boolean invoke2(HeapObject.HeapClass heapClass) {
                    Intrinsics.checkParameterIsNotNull(heapClass, "it");
                    return heapClass.subclassOf(this.this$0);
                }
            });
        }

        public final HeapClass getSuperclass() {
            if (this.indexedObject.getSuperclassId() == 0) {
                return null;
            }
            HeapObject heapObjectFindObjectById = this.hprofGraph.findObjectById(this.indexedObject.getSuperclassId());
            if (heapObjectFindObjectById != null) {
                return (HeapClass) heapObjectFindObjectById;
            }
            throw new TypeCastException("null cannot be cast to non-null type kshark.HeapObject.HeapClass");
        }

        public final boolean isArrayClass() {
            return StringsKt.endsWith$default(getName(), "[]", false, 2, (Object) null);
        }

        public final boolean isObjectArrayClass() {
            return isArrayClass() && !isPrimitiveArrayClass();
        }

        public final boolean isPrimitiveArrayClass() {
            return HeapObject.primitiveArrayClassesByName.containsKey(getName());
        }

        public final int readFieldsByteSize() {
            int identifierByteSize = 0;
            for (HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.FieldRecord fieldRecord : readRecord().getFields()) {
                identifierByteSize += fieldRecord.getType() == 2 ? this.hprofGraph.getIdentifierByteSize() : ((Number) MapsKt.getValue(PrimitiveType.INSTANCE.getByteSizeByHprofType(), Integer.valueOf(fieldRecord.getType()))).intValue();
            }
            return identifierByteSize;
        }

        @Override
        public HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord readRecord() {
            return this.hprofGraph.readClassDumpRecord$shark(getObjectId(), this.indexedObject);
        }

        public final HeapField readStaticField(String fieldName) {
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            for (HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.StaticFieldRecord staticFieldRecord : readRecord().getStaticFields()) {
                if (Intrinsics.areEqual(this.hprofGraph.staticFieldName$shark(getObjectId(), staticFieldRecord), fieldName)) {
                    return new HeapField(this, this.hprofGraph.staticFieldName$shark(getObjectId(), staticFieldRecord), new HeapValue(this.hprofGraph, staticFieldRecord.getValue()));
                }
            }
            return null;
        }

        public final Sequence<HeapField> readStaticFields() {
            return SequencesKt.map(CollectionsKt.asSequence(readRecord().getStaticFields()), new Function1<HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.StaticFieldRecord, HeapField>() {
                {
                    super(1);
                }

                @Override
                public final HeapField invoke(HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.StaticFieldRecord staticFieldRecord) {
                    Intrinsics.checkParameterIsNotNull(staticFieldRecord, "fieldRecord");
                    HeapObject.HeapClass heapClass = this.this$0;
                    return new HeapField(heapClass, heapClass.hprofGraph.staticFieldName$shark(this.this$0.getObjectId(), staticFieldRecord), new HeapValue(this.this$0.hprofGraph, staticFieldRecord.getValue()));
                }
            });
        }

        public final boolean subclassOf(HeapClass superclass) {
            boolean z;
            Intrinsics.checkParameterIsNotNull(superclass, "superclass");
            Iterator<HeapClass> it = getClassHierarchy().iterator();
            do {
                z = false;
                if (!it.hasNext()) {
                    return false;
                }
                if (it.next().getObjectId() == superclass.getObjectId()) {
                    z = true;
                }
            } while (!z);
            return true;
        }

        public final boolean superclassOf(HeapClass subclass) {
            boolean z;
            Intrinsics.checkParameterIsNotNull(subclass, "subclass");
            Iterator<HeapClass> it = subclass.getClassHierarchy().iterator();
            do {
                z = false;
                if (!it.hasNext()) {
                    return false;
                }
                if (it.next().getObjectId() == getObjectId()) {
                    z = true;
                }
            } while (!z);
            return true;
        }

        public String toString() {
            return "class " + getName();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0018\u00002\u00020\u0001B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u001dH\u0086\u0002J#\u0010$\u001a\u0004\u0018\u00010%2\u000e\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020*0)2\u0006\u0010'\u001a\u00020\u001dH\u0086\u0002J\u0011\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u001dH\u0086\u0004J\u0015\u0010+\u001a\u00020\t2\n\u0010-\u001a\u0006\u0012\u0002\b\u00030)H\u0086\u0004J\u0011\u0010+\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u0016H\u0086\u0004J\b\u0010.\u001a\u0004\u0018\u00010\u001dJ\u0018\u0010/\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u001dJ \u0010/\u001a\u0004\u0018\u00010%2\u000e\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020*0)2\u0006\u0010'\u001a\u00020\u001dJ\f\u00100\u001a\b\u0012\u0004\u0012\u00020%01J\b\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u00020\u001dH\u0016R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\"R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001b¨\u00065²\u0006\n\u00106\u001a\u000207X\u008a\u0084\u0002"}, d2 = {"Lkshark/HeapObject$HeapInstance;", "Lkshark/HeapObject;", "hprofGraph", "Lkshark/HprofHeapGraph;", "indexedObject", "Lkshark/internal/IndexedObject$IndexedInstance;", "objectId", "", "isPrimitiveWrapper", "", "(Lkshark/HprofHeapGraph;Lkshark/internal/IndexedObject$IndexedInstance;JZ)V", "byteSize", "", "getByteSize", "()I", "graph", "Lkshark/HeapGraph;", "getGraph", "()Lkshark/HeapGraph;", "getIndexedObject$shark", "()Lkshark/internal/IndexedObject$IndexedInstance;", "instanceClass", "Lkshark/HeapObject$HeapClass;", "getInstanceClass", "()Lkshark/HeapObject$HeapClass;", "instanceClassId", "getInstanceClassId", "()J", "instanceClassName", "", "getInstanceClassName", "()Ljava/lang/String;", "instanceClassSimpleName", "getInstanceClassSimpleName", "()Z", "getObjectId", "get", "Lkshark/HeapField;", "declaringClassName", "fieldName", "declaringClass", "Lkotlin/reflect/KClass;", "", "instanceOf", "className", "expectedClass", "readAsJavaString", "readField", "readFields", "Lkotlin/sequences/Sequence;", "readRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$InstanceDumpRecord;", "toString", "shark", "fieldReader", "Lkshark/internal/FieldValuesReader;"}, k = 1, mv = {1, 1, 15})
    public static final class HeapInstance extends HeapObject {
        static final KProperty[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinClass(HeapInstance.class), "fieldReader", "<v#0>"))};
        private final HprofHeapGraph hprofGraph;
        private final IndexedObject.IndexedInstance indexedObject;
        private final boolean isPrimitiveWrapper;
        private final long objectId;

        public HeapInstance(HprofHeapGraph hprofHeapGraph, IndexedObject.IndexedInstance indexedInstance, long j, boolean z) {
            super(null);
            Intrinsics.checkParameterIsNotNull(hprofHeapGraph, "hprofGraph");
            Intrinsics.checkParameterIsNotNull(indexedInstance, "indexedObject");
            this.hprofGraph = hprofHeapGraph;
            this.indexedObject = indexedInstance;
            this.objectId = j;
            this.isPrimitiveWrapper = z;
        }

        public final HeapField get(String declaringClassName, String fieldName) {
            Intrinsics.checkParameterIsNotNull(declaringClassName, "declaringClassName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            return readField(declaringClassName, fieldName);
        }

        public final HeapField get(KClass<? extends Object> declaringClass, String fieldName) {
            Intrinsics.checkParameterIsNotNull(declaringClass, "declaringClass");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            return readField(declaringClass, fieldName);
        }

        public final int getByteSize() {
            return getInstanceClass().getInstanceByteSize();
        }

        @Override
        public HeapGraph getGraph() {
            return this.hprofGraph;
        }

        public final IndexedObject.IndexedInstance getIndexedObject() {
            return this.indexedObject;
        }

        public final HeapClass getInstanceClass() {
            HeapObject heapObjectFindObjectById = this.hprofGraph.findObjectById(this.indexedObject.getClassId());
            if (heapObjectFindObjectById != null) {
                return (HeapClass) heapObjectFindObjectById;
            }
            throw new TypeCastException("null cannot be cast to non-null type kshark.HeapObject.HeapClass");
        }

        public final long getInstanceClassId() {
            return this.indexedObject.getClassId();
        }

        public final String getInstanceClassName() {
            return this.hprofGraph.className$shark(this.indexedObject.getClassId());
        }

        public final String getInstanceClassSimpleName() {
            return HeapObject.INSTANCE.classSimpleName(getInstanceClassName());
        }

        @Override
        public long getObjectId() {
            return this.objectId;
        }

        public final boolean instanceOf(String className) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Iterator<HeapClass> it = getInstanceClass().getClassHierarchy().iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(it.next().getName(), className)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean instanceOf(KClass<?> expectedClass) {
            Intrinsics.checkParameterIsNotNull(expectedClass, "expectedClass");
            String name = JvmClassMappingKt.getJavaClass((KClass) expectedClass).getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "expectedClass.java.name");
            return instanceOf(name);
        }

        public final boolean instanceOf(HeapClass expectedClass) {
            boolean z;
            Intrinsics.checkParameterIsNotNull(expectedClass, "expectedClass");
            Iterator<HeapClass> it = getInstanceClass().getClassHierarchy().iterator();
            do {
                z = false;
                if (!it.hasNext()) {
                    return false;
                }
                if (it.next().getObjectId() == expectedClass.getObjectId()) {
                    z = true;
                }
            } while (!z);
            return true;
        }

        public final boolean getIsPrimitiveWrapper() {
            return this.isPrimitiveWrapper;
        }

        public final String readAsJavaString() {
            char[] array;
            HeapValue value;
            HeapValue value2;
            Integer asInt = null;
            if (!Intrinsics.areEqual(getInstanceClassName(), "java.lang.String")) {
                return null;
            }
            HeapField heapField = get("java.lang.String", "count");
            Integer asInt2 = (heapField == null || (value2 = heapField.getValue()) == null) ? null : value2.getAsInt();
            if (asInt2 != null && asInt2.intValue() == 0) {
                return "";
            }
            HeapField heapField2 = get("java.lang.String", "value");
            if (heapField2 == null) {
                Intrinsics.throwNpe();
            }
            HeapObject asObject = heapField2.getValue().getAsObject();
            if (asObject == null) {
                Intrinsics.throwNpe();
            }
            HprofRecord.HeapDumpRecord.ObjectRecord record = asObject.readRecord();
            if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) {
                HeapField heapField3 = get("java.lang.String", "offset");
                if (heapField3 != null && (value = heapField3.getValue()) != null) {
                    asInt = value.getAsInt();
                }
                if (asInt2 == null || asInt == null) {
                    array = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) record).getArray();
                } else {
                    HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump charArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) record;
                    array = ArraysKt.copyOfRange(charArrayDump.getArray(), asInt.intValue(), asInt.intValue() + asInt2.intValue() > charArrayDump.getArray().length ? charArrayDump.getArray().length : asInt2.intValue() + asInt.intValue());
                }
                return new String(array);
            }
            if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) {
                byte[] array2 = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) record).getArray();
                Charset charsetForName = Charset.forName("UTF-8");
                Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(\"UTF-8\")");
                return new String(array2, charsetForName);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("'value' field ");
            HeapField heapField4 = get("java.lang.String", "value");
            if (heapField4 == null) {
                Intrinsics.throwNpe();
            }
            sb.append(heapField4.getValue());
            sb.append(" was expected to be either");
            sb.append(" a char or byte array in string instance with id ");
            sb.append(getObjectId());
            throw new UnsupportedOperationException(sb.toString());
        }

        public final HeapField readField(String declaringClassName, String fieldName) {
            HeapField next;
            Intrinsics.checkParameterIsNotNull(declaringClassName, "declaringClassName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Iterator<HeapField> it = readFields().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                HeapField heapField = next;
                if (Intrinsics.areEqual(heapField.getDeclaringClass().getName(), declaringClassName) && Intrinsics.areEqual(heapField.getName(), fieldName)) {
                    break;
                }
            }
            return next;
        }

        public final HeapField readField(KClass<? extends Object> declaringClass, String fieldName) {
            Intrinsics.checkParameterIsNotNull(declaringClass, "declaringClass");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            String name = JvmClassMappingKt.getJavaClass((KClass) declaringClass).getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "declaringClass.java.name");
            return readField(name, fieldName);
        }

        public final Sequence<HeapField> readFields() {
            final Lazy lazy = LazyKt.lazy(new Function0<FieldValuesReader>() {
                {
                    super(0);
                }

                @Override
                public final FieldValuesReader invoke() {
                    return this.this$0.hprofGraph.createFieldValuesReader$shark(this.this$0.readRecord());
                }
            });
            final KProperty kProperty = $$delegatedProperties[0];
            return SequencesKt.flatten(SequencesKt.map(getInstanceClass().getClassHierarchy(), new Function1<HeapClass, Sequence<? extends HeapField>>() {
                {
                    super(1);
                }

                @Override
                public final Sequence<HeapField> invoke(final HeapObject.HeapClass heapClass) {
                    Intrinsics.checkParameterIsNotNull(heapClass, "heapClass");
                    return SequencesKt.map(CollectionsKt.asSequence(heapClass.readRecord().getFields()), new Function1<HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.FieldRecord, HeapField>() {
                        {
                            super(1);
                        }

                        @Override
                        public final HeapField invoke(HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.FieldRecord fieldRecord) {
                            Intrinsics.checkParameterIsNotNull(fieldRecord, "fieldRecord");
                            String strFieldName$shark = HeapObject$HeapInstance$readFields$1.this.this$0.hprofGraph.fieldName$shark(heapClass.getObjectId(), fieldRecord);
                            Lazy lazy2 = lazy;
                            KProperty kProperty2 = kProperty;
                            return new HeapField(heapClass, strFieldName$shark, new HeapValue(HeapObject$HeapInstance$readFields$1.this.this$0.hprofGraph, ((FieldValuesReader) lazy2.getValue()).readValue(fieldRecord)));
                        }
                    });
                }
            }));
        }

        @Override
        public HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord readRecord() {
            return this.hprofGraph.readInstanceDumpRecord$shark(getObjectId(), this.indexedObject);
        }

        public String toString() {
            return "instance @" + getObjectId() + " of " + getInstanceClassName();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\"\u001a\u00020\u0016J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0010H\u0016R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u0006)"}, d2 = {"Lkshark/HeapObject$HeapObjectArray;", "Lkshark/HeapObject;", "hprofGraph", "Lkshark/HprofHeapGraph;", "indexedObject", "Lkshark/internal/IndexedObject$IndexedObjectArray;", "objectId", "", "isPrimitiveWrapperArray", "", "(Lkshark/HprofHeapGraph;Lkshark/internal/IndexedObject$IndexedObjectArray;JZ)V", "arrayClass", "Lkshark/HeapObject$HeapClass;", "getArrayClass", "()Lkshark/HeapObject$HeapClass;", "arrayClassName", "", "getArrayClassName", "()Ljava/lang/String;", "arrayClassSimpleName", "getArrayClassSimpleName", "arrayLength", "", "getArrayLength", "()I", "graph", "Lkshark/HeapGraph;", "getGraph", "()Lkshark/HeapGraph;", "getIndexedObject$shark", "()Lkshark/internal/IndexedObject$IndexedObjectArray;", "()Z", "getObjectId", "()J", "readByteSize", "readElements", "Lkotlin/sequences/Sequence;", "Lkshark/HeapValue;", "readRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ObjectArrayDumpRecord;", "toString", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class HeapObjectArray extends HeapObject {
        private final HprofHeapGraph hprofGraph;
        private final IndexedObject.IndexedObjectArray indexedObject;
        private final boolean isPrimitiveWrapperArray;
        private final long objectId;

        public HeapObjectArray(HprofHeapGraph hprofHeapGraph, IndexedObject.IndexedObjectArray indexedObjectArray, long j, boolean z) {
            super(null);
            Intrinsics.checkParameterIsNotNull(hprofHeapGraph, "hprofGraph");
            Intrinsics.checkParameterIsNotNull(indexedObjectArray, "indexedObject");
            this.hprofGraph = hprofHeapGraph;
            this.indexedObject = indexedObjectArray;
            this.objectId = j;
            this.isPrimitiveWrapperArray = z;
        }

        public final HeapClass getArrayClass() {
            HeapObject heapObjectFindObjectById = this.hprofGraph.findObjectById(this.indexedObject.getArrayClassId());
            if (heapObjectFindObjectById != null) {
                return (HeapClass) heapObjectFindObjectById;
            }
            throw new TypeCastException("null cannot be cast to non-null type kshark.HeapObject.HeapClass");
        }

        public final String getArrayClassName() {
            return this.hprofGraph.className$shark(this.indexedObject.getArrayClassId());
        }

        public final String getArrayClassSimpleName() {
            return HeapObject.INSTANCE.classSimpleName(getArrayClassName());
        }

        public final int getArrayLength() {
            return this.indexedObject.getSize();
        }

        @Override
        public HeapGraph getGraph() {
            return this.hprofGraph;
        }

        public final IndexedObject.IndexedObjectArray getIndexedObject() {
            return this.indexedObject;
        }

        @Override
        public long getObjectId() {
            return this.objectId;
        }

        public final boolean getIsPrimitiveWrapperArray() {
            return this.isPrimitiveWrapperArray;
        }

        public final int readByteSize() {
            return readRecord().getElementIds().length * this.hprofGraph.getIdentifierByteSize();
        }

        public final Sequence<HeapValue> readElements() {
            return SequencesKt.map(ArraysKt.asSequence(readRecord().getElementIds()), new Function1<Long, HeapValue>() {
                {
                    super(1);
                }

                @Override
                public HeapValue invoke(Long l) {
                    return invoke(l.longValue());
                }

                public final HeapValue invoke(long j) {
                    return new HeapValue(this.this$0.hprofGraph, new ValueHolder.ReferenceHolder(j));
                }
            });
        }

        @Override
        public HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord readRecord() {
            return this.hprofGraph.readObjectArrayDumpRecord$shark(getObjectId(), this.indexedObject);
        }

        public String toString() {
            return "object array @" + getObjectId() + " of " + getArrayClassName();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u001f\u001a\u00020\u0012J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u000eH\u0016R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006#"}, d2 = {"Lkshark/HeapObject$HeapPrimitiveArray;", "Lkshark/HeapObject;", "hprofGraph", "Lkshark/HprofHeapGraph;", "indexedObject", "Lkshark/internal/IndexedObject$IndexedPrimitiveArray;", "objectId", "", "(Lkshark/HprofHeapGraph;Lkshark/internal/IndexedObject$IndexedPrimitiveArray;J)V", "arrayClass", "Lkshark/HeapObject$HeapClass;", "getArrayClass", "()Lkshark/HeapObject$HeapClass;", "arrayClassName", "", "getArrayClassName", "()Ljava/lang/String;", "arrayLength", "", "getArrayLength", "()I", "graph", "Lkshark/HeapGraph;", "getGraph", "()Lkshark/HeapGraph;", "getObjectId", "()J", "primitiveType", "Lkshark/PrimitiveType;", "getPrimitiveType", "()Lkshark/PrimitiveType;", "readByteSize", "readRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$PrimitiveArrayDumpRecord;", "toString", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class HeapPrimitiveArray extends HeapObject {
        private final HprofHeapGraph hprofGraph;
        private final IndexedObject.IndexedPrimitiveArray indexedObject;
        private final long objectId;

        public HeapPrimitiveArray(HprofHeapGraph hprofHeapGraph, IndexedObject.IndexedPrimitiveArray indexedPrimitiveArray, long j) {
            super(null);
            Intrinsics.checkParameterIsNotNull(hprofHeapGraph, "hprofGraph");
            Intrinsics.checkParameterIsNotNull(indexedPrimitiveArray, "indexedObject");
            this.hprofGraph = hprofHeapGraph;
            this.indexedObject = indexedPrimitiveArray;
            this.objectId = j;
        }

        public final HeapClass getArrayClass() {
            HeapClass heapClassFindClassByName = getGraph().findClassByName(getArrayClassName());
            if (heapClassFindClassByName == null) {
                Intrinsics.throwNpe();
            }
            return heapClassFindClassByName;
        }

        public final String getArrayClassName() {
            StringBuilder sb = new StringBuilder();
            String strName = getPrimitiveType().name();
            Locale locale = Locale.US;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
            if (strName == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = strName.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            sb.append(lowerCase);
            sb.append("[]");
            return sb.toString();
        }

        public final int getArrayLength() {
            return this.indexedObject.getSize();
        }

        @Override
        public HeapGraph getGraph() {
            return this.hprofGraph;
        }

        @Override
        public long getObjectId() {
            return this.objectId;
        }

        public final PrimitiveType getPrimitiveType() {
            return this.indexedObject.getPrimitiveType();
        }

        public final int readByteSize() {
            int length;
            int byteSize;
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord record = readRecord();
            if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump) {
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump) record).getArray().length;
                byteSize = PrimitiveType.BOOLEAN.getByteSize();
            } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) {
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) record).getArray().length;
                byteSize = PrimitiveType.CHAR.getByteSize();
            } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump) {
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump) record).getArray().length;
                byteSize = PrimitiveType.FLOAT.getByteSize();
            } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump) {
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump) record).getArray().length;
                byteSize = PrimitiveType.DOUBLE.getByteSize();
            } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) {
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) record).getArray().length;
                byteSize = PrimitiveType.BYTE.getByteSize();
            } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump) {
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump) record).getArray().length;
                byteSize = PrimitiveType.SHORT.getByteSize();
            } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump) {
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump) record).getArray().length;
                byteSize = PrimitiveType.INT.getByteSize();
            } else {
                if (!(record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump)) {
                    throw new NoWhenBranchMatchedException();
                }
                length = ((HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump) record).getArray().length;
                byteSize = PrimitiveType.LONG.getByteSize();
            }
            return length * byteSize;
        }

        @Override
        public HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord readRecord() {
            return this.hprofGraph.readPrimitiveArrayDumpRecord$shark(getObjectId(), this.indexedObject);
        }

        public String toString() {
            return "primitive array @" + getObjectId() + " of " + getArrayClassName();
        }
    }

    static {
        PrimitiveType[] primitiveTypeArrValues = PrimitiveType.values();
        ArrayList arrayList = new ArrayList(primitiveTypeArrValues.length);
        for (PrimitiveType primitiveType : primitiveTypeArrValues) {
            StringBuilder sb = new StringBuilder();
            String strName = primitiveType.name();
            Locale locale = Locale.US;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
            if (strName == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = strName.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            sb.append(lowerCase);
            sb.append("[]");
            arrayList.add(TuplesKt.to(sb.toString(), primitiveType));
        }
        primitiveArrayClassesByName = MapsKt.toMap(arrayList);
    }

    private HeapObject() {
    }

    public HeapObject(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final HeapClass getAsClass() {
        if (this instanceof HeapClass) {
            return (HeapClass) this;
        }
        return null;
    }

    public final HeapInstance getAsInstance() {
        if (this instanceof HeapInstance) {
            return (HeapInstance) this;
        }
        return null;
    }

    public final HeapObjectArray getAsObjectArray() {
        if (this instanceof HeapObjectArray) {
            return (HeapObjectArray) this;
        }
        return null;
    }

    public final HeapPrimitiveArray getAsPrimitiveArray() {
        if (this instanceof HeapPrimitiveArray) {
            return (HeapPrimitiveArray) this;
        }
        return null;
    }

    public abstract HeapGraph getGraph();

    public abstract long getObjectId();

    public abstract HprofRecord.HeapDumpRecord.ObjectRecord readRecord();
}
