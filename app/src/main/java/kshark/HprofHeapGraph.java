package kshark;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kshark.GcRoot;
import kshark.HeapObject;
import kshark.HprofRecord;
import kshark.internal.FieldValuesReader;
import kshark.internal.HprofInMemoryIndex;
import kshark.internal.IndexedObject;
import kshark.internal.LruCache;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \\2\u00020\u0001:\u0001\\B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\tH\u0000¢\u0006\u0002\b3J\u0015\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0000¢\u0006\u0002\b8J\u001d\u00109\u001a\u0002012\u0006\u00102\u001a\u00020\t2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\u0012\u0010=\u001a\u0004\u0018\u00010\u00112\u0006\u00100\u001a\u000201H\u0016J\u0010\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020\tH\u0016J\u0012\u0010@\u001a\u0004\u0018\u00010+2\u0006\u0010?\u001a\u00020\tH\u0016J\u0010\u0010A\u001a\u00020B2\u0006\u0010?\u001a\u00020\tH\u0016J\u001d\u0010C\u001a\u00020\n2\u0006\u0010?\u001a\u00020\t2\u0006\u0010D\u001a\u00020EH\u0000¢\u0006\u0002\bFJ\u001d\u0010G\u001a\u0002072\u0006\u0010?\u001a\u00020\t2\u0006\u0010D\u001a\u00020HH\u0000¢\u0006\u0002\bIJ\u001d\u0010J\u001a\u00020K2\u0006\u0010?\u001a\u00020\t2\u0006\u0010D\u001a\u00020LH\u0000¢\u0006\u0002\bMJ5\u0010N\u001a\u0002HO\"\b\b\u0000\u0010O*\u00020)2\u0006\u0010?\u001a\u00020\t2\u0006\u0010D\u001a\u00020P2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002HO0RH\u0002¢\u0006\u0002\u0010SJ\u001d\u0010T\u001a\u00020U2\u0006\u0010?\u001a\u00020\t2\u0006\u0010D\u001a\u00020VH\u0000¢\u0006\u0002\bWJ\u001d\u0010X\u001a\u0002012\u0006\u00102\u001a\u00020\t2\u0006\u0010:\u001a\u00020YH\u0000¢\u0006\u0002\bZJ\u0018\u0010[\u001a\u00020+2\u0006\u0010D\u001a\u00020P2\u0006\u0010?\u001a\u00020\tH\u0002R&\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0013R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0013R\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020)0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0013R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0013¨\u0006]"}, d2 = {"Lkshark/HprofHeapGraph;", "Lkshark/HeapGraph;", "hprof", "Lkshark/Hprof;", "index", "Lkshark/internal/HprofInMemoryIndex;", "(Lkshark/Hprof;Lkshark/internal/HprofInMemoryIndex;)V", "classMap", "", "", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ClassDumpRecord;", "getClassMap", "()Ljava/util/Map;", "setClassMap", "(Ljava/util/Map;)V", "classes", "Lkotlin/sequences/Sequence;", "Lkshark/HeapObject$HeapClass;", "getClasses", "()Lkotlin/sequences/Sequence;", "context", "Lkshark/GraphContext;", "getContext", "()Lkshark/GraphContext;", "gcRoots", "", "Lkshark/GcRoot;", "getGcRoots", "()Ljava/util/List;", "identifierByteSize", "", "getIdentifierByteSize", "()I", "instances", "Lkshark/HeapObject$HeapInstance;", "getInstances", "objectArrays", "Lkshark/HeapObject$HeapObjectArray;", "getObjectArrays", "objectCache", "Lkshark/internal/LruCache;", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord;", "objects", "Lkshark/HeapObject;", "getObjects", "primitiveArrays", "Lkshark/HeapObject$HeapPrimitiveArray;", "getPrimitiveArrays", "className", "", "classId", "className$shark", "createFieldValuesReader", "Lkshark/internal/FieldValuesReader;", "record", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$InstanceDumpRecord;", "createFieldValuesReader$shark", "fieldName", "fieldRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ClassDumpRecord$FieldRecord;", "fieldName$shark", "findClassByName", "findObjectById", "objectId", "findObjectByIdOrNull", "objectExists", "", "readClassDumpRecord", "indexedObject", "Lkshark/internal/IndexedObject$IndexedClass;", "readClassDumpRecord$shark", "readInstanceDumpRecord", "Lkshark/internal/IndexedObject$IndexedInstance;", "readInstanceDumpRecord$shark", "readObjectArrayDumpRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ObjectArrayDumpRecord;", "Lkshark/internal/IndexedObject$IndexedObjectArray;", "readObjectArrayDumpRecord$shark", "readObjectRecord", "T", "Lkshark/internal/IndexedObject;", "readBlock", "Lkotlin/Function0;", "(JLkshark/internal/IndexedObject;Lkotlin/jvm/functions/Function0;)Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord;", "readPrimitiveArrayDumpRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$PrimitiveArrayDumpRecord;", "Lkshark/internal/IndexedObject$IndexedPrimitiveArray;", "readPrimitiveArrayDumpRecord$shark", "staticFieldName", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ClassDumpRecord$StaticFieldRecord;", "staticFieldName$shark", "wrapIndexedObject", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class HprofHeapGraph implements HeapGraph {

    public static final Companion INSTANCE = new Companion(null);
    private Map<Long, HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord> classMap;
    private final GraphContext context;
    private final Hprof hprof;
    private final HprofInMemoryIndex index;
    private final LruCache<Long, HprofRecord.HeapDumpRecord.ObjectRecord> objectCache;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b0\n¨\u0006\r"}, d2 = {"Lkshark/HprofHeapGraph$Companion;", "", "()V", "indexHprof", "Lkshark/HeapGraph;", "hprof", "Lkshark/Hprof;", "proguardMapping", "Lkshark/ProguardMapping;", "indexedGcRootTypes", "", "Lkotlin/reflect/KClass;", "Lkshark/GcRoot;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static HeapGraph indexHprof$default(Companion companion, Hprof hprof, ProguardMapping proguardMapping, Set set, int i, Object obj) {
            if ((i & 2) != 0) {
                proguardMapping = (ProguardMapping) null;
            }
            if ((i & 4) != 0) {
                set = SetsKt.setOf((Object[]) new KClass[]{Reflection.getOrCreateKotlinClass(GcRoot.JniGlobal.class), Reflection.getOrCreateKotlinClass(GcRoot.JavaFrame.class), Reflection.getOrCreateKotlinClass(GcRoot.JniLocal.class), Reflection.getOrCreateKotlinClass(GcRoot.MonitorUsed.class), Reflection.getOrCreateKotlinClass(GcRoot.NativeStack.class), Reflection.getOrCreateKotlinClass(GcRoot.StickyClass.class), Reflection.getOrCreateKotlinClass(GcRoot.ThreadBlock.class), Reflection.getOrCreateKotlinClass(GcRoot.ThreadObject.class), Reflection.getOrCreateKotlinClass(GcRoot.JniMonitor.class)});
            }
            return companion.indexHprof(hprof, proguardMapping, set);
        }

        public final HeapGraph indexHprof(Hprof hprof, ProguardMapping proguardMapping, Set<? extends KClass<? extends GcRoot>> indexedGcRootTypes) {
            Intrinsics.checkParameterIsNotNull(hprof, "hprof");
            Intrinsics.checkParameterIsNotNull(indexedGcRootTypes, "indexedGcRootTypes");
            return new HprofHeapGraph(hprof, HprofInMemoryIndex.INSTANCE.createReadingHprof(hprof, proguardMapping, indexedGcRootTypes));
        }
    }

    public HprofHeapGraph(Hprof hprof, HprofInMemoryIndex hprofInMemoryIndex) {
        Intrinsics.checkParameterIsNotNull(hprof, "hprof");
        Intrinsics.checkParameterIsNotNull(hprofInMemoryIndex, "index");
        this.hprof = hprof;
        this.index = hprofInMemoryIndex;
        this.context = new GraphContext();
        this.objectCache = new LruCache<>(3000);
        this.classMap = new LinkedHashMap();
    }

    private final <T extends HprofRecord.HeapDumpRecord.ObjectRecord> T readObjectRecord(long objectId, IndexedObject indexedObject, Function0<? extends T> readBlock) {
        T t = (T) this.objectCache.get(Long.valueOf(objectId));
        if (t != null) {
            return t;
        }
        this.hprof.moveReaderTo(indexedObject.getPosition());
        T tInvoke = readBlock.invoke();
        this.objectCache.put(Long.valueOf(objectId), tInvoke);
        return tInvoke;
    }

    private final HeapObject wrapIndexedObject(IndexedObject indexedObject, long objectId) {
        if (indexedObject instanceof IndexedObject.IndexedClass) {
            return new HeapObject.HeapClass(this, (IndexedObject.IndexedClass) indexedObject, objectId);
        }
        if (indexedObject instanceof IndexedObject.IndexedInstance) {
            IndexedObject.IndexedInstance indexedInstance = (IndexedObject.IndexedInstance) indexedObject;
            return new HeapObject.HeapInstance(this, indexedInstance, objectId, this.index.getPrimitiveWrapperTypes().contains(Long.valueOf(indexedInstance.getClassId())));
        }
        if (indexedObject instanceof IndexedObject.IndexedObjectArray) {
            IndexedObject.IndexedObjectArray indexedObjectArray = (IndexedObject.IndexedObjectArray) indexedObject;
            return new HeapObject.HeapObjectArray(this, indexedObjectArray, objectId, this.index.getPrimitiveWrapperTypes().contains(Long.valueOf(indexedObjectArray.getArrayClassId())));
        }
        if (indexedObject instanceof IndexedObject.IndexedPrimitiveArray) {
            return new HeapObject.HeapPrimitiveArray(this, (IndexedObject.IndexedPrimitiveArray) indexedObject, objectId);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String className$shark(long classId) {
        return this.index.className(classId);
    }

    public final FieldValuesReader createFieldValuesReader$shark(HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord record) {
        Intrinsics.checkParameterIsNotNull(record, "record");
        return new FieldValuesReader(record, getIdentifierByteSize());
    }

    public final String fieldName$shark(long classId, HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.FieldRecord fieldRecord) {
        Intrinsics.checkParameterIsNotNull(fieldRecord, "fieldRecord");
        return this.index.fieldName(classId, fieldRecord.getNameStringId());
    }

    @Override
    public HeapObject.HeapClass findClassByName(String className) {
        Intrinsics.checkParameterIsNotNull(className, "className");
        Long lClassId = this.index.classId(className);
        if (lClassId == null) {
            return null;
        }
        HeapObject heapObjectFindObjectById = findObjectById(lClassId.longValue());
        if (heapObjectFindObjectById != null) {
            return (HeapObject.HeapClass) heapObjectFindObjectById;
        }
        throw new TypeCastException("null cannot be cast to non-null type kshark.HeapObject.HeapClass");
    }

    @Override
    public HeapObject findObjectById(long objectId) {
        HeapObject heapObjectFindObjectByIdOrNull = findObjectByIdOrNull(objectId);
        if (heapObjectFindObjectByIdOrNull != null) {
            return heapObjectFindObjectByIdOrNull;
        }
        throw new IllegalArgumentException("Object id " + objectId + " not found in heap dump.");
    }

    @Override
    public HeapObject findObjectByIdOrNull(long objectId) {
        IndexedObject indexedObjectIndexedObjectOrNull = this.index.indexedObjectOrNull(objectId);
        if (indexedObjectIndexedObjectOrNull != null) {
            return wrapIndexedObject(indexedObjectIndexedObjectOrNull, objectId);
        }
        return null;
    }

    public final Map<Long, HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord> getClassMap() {
        return this.classMap;
    }

    @Override
    public Sequence<HeapObject.HeapClass> getClasses() {
        return SequencesKt.map(this.index.indexedClassSequence(), new Function1<Pair<? extends Long, ? extends IndexedObject.IndexedClass>, HeapObject.HeapClass>() {
            {
                super(1);
            }

            @Override
            public HeapObject.HeapClass invoke(Pair<? extends Long, ? extends IndexedObject.IndexedClass> pair) {
                return invoke2((Pair<Long, IndexedObject.IndexedClass>) pair);
            }

            public final HeapObject.HeapClass invoke2(Pair<Long, IndexedObject.IndexedClass> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                return new HeapObject.HeapClass(this.this$0, pair.getSecond(), jLongValue);
            }
        });
    }

    @Override
    public GraphContext getContext() {
        return this.context;
    }

    @Override
    public List<GcRoot> getGcRoots() {
        return this.index.gcRoots();
    }

    @Override
    public int getIdentifierByteSize() {
        return this.hprof.getReader().getIdentifierByteSize();
    }

    @Override
    public Sequence<HeapObject.HeapInstance> getInstances() {
        return SequencesKt.map(this.index.indexedInstanceSequence(), new Function1<Pair<? extends Long, ? extends IndexedObject.IndexedInstance>, HeapObject.HeapInstance>() {
            {
                super(1);
            }

            @Override
            public HeapObject.HeapInstance invoke(Pair<? extends Long, ? extends IndexedObject.IndexedInstance> pair) {
                return invoke2((Pair<Long, IndexedObject.IndexedInstance>) pair);
            }

            public final HeapObject.HeapInstance invoke2(Pair<Long, IndexedObject.IndexedInstance> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                IndexedObject.IndexedInstance second = pair.getSecond();
                return new HeapObject.HeapInstance(this.this$0, second, jLongValue, this.this$0.index.getPrimitiveWrapperTypes().contains(Long.valueOf(second.getClassId())));
            }
        });
    }

    @Override
    public Sequence<HeapObject.HeapObjectArray> getObjectArrays() {
        return SequencesKt.map(this.index.indexedObjectArraySequence(), new Function1<Pair<? extends Long, ? extends IndexedObject.IndexedObjectArray>, HeapObject.HeapObjectArray>() {
            {
                super(1);
            }

            @Override
            public HeapObject.HeapObjectArray invoke(Pair<? extends Long, ? extends IndexedObject.IndexedObjectArray> pair) {
                return invoke2((Pair<Long, IndexedObject.IndexedObjectArray>) pair);
            }

            public final HeapObject.HeapObjectArray invoke2(Pair<Long, IndexedObject.IndexedObjectArray> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                IndexedObject.IndexedObjectArray second = pair.getSecond();
                return new HeapObject.HeapObjectArray(this.this$0, second, jLongValue, this.this$0.index.getPrimitiveWrapperTypes().contains(Long.valueOf(second.getArrayClassId())));
            }
        });
    }

    @Override
    public Sequence<HeapObject> getObjects() {
        return SequencesKt.map(this.index.indexedObjectSequence(), new Function1<Pair<? extends Long, ? extends IndexedObject>, HeapObject>() {
            {
                super(1);
            }

            @Override
            public HeapObject invoke(Pair<? extends Long, ? extends IndexedObject> pair) {
                return invoke2((Pair<Long, ? extends IndexedObject>) pair);
            }

            public final HeapObject invoke2(Pair<Long, ? extends IndexedObject> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                return this.this$0.wrapIndexedObject(pair.getSecond(), pair.getFirst().longValue());
            }
        });
    }

    @Override
    public Sequence<HeapObject.HeapPrimitiveArray> getPrimitiveArrays() {
        return SequencesKt.map(this.index.indexedPrimitiveArraySequence(), new Function1<Pair<? extends Long, ? extends IndexedObject.IndexedPrimitiveArray>, HeapObject.HeapPrimitiveArray>() {
            {
                super(1);
            }

            @Override
            public HeapObject.HeapPrimitiveArray invoke(Pair<? extends Long, ? extends IndexedObject.IndexedPrimitiveArray> pair) {
                return invoke2((Pair<Long, IndexedObject.IndexedPrimitiveArray>) pair);
            }

            public final HeapObject.HeapPrimitiveArray invoke2(Pair<Long, IndexedObject.IndexedPrimitiveArray> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                return new HeapObject.HeapPrimitiveArray(this.this$0, pair.getSecond(), jLongValue);
            }
        });
    }

    @Override
    public boolean objectExists(long objectId) {
        return this.index.objectIdIsIndexed(objectId);
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord readClassDumpRecord$shark(long objectId, IndexedObject.IndexedClass indexedObject) {
        Intrinsics.checkParameterIsNotNull(indexedObject, "indexedObject");
        HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord classDumpRecord = this.classMap.get(Long.valueOf(objectId));
        if (classDumpRecord != null) {
            return classDumpRecord;
        }
        HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord classDumpRecord2 = (HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord) readObjectRecord(objectId, indexedObject, new Function0<HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord>() {
            {
                super(0);
            }

            @Override
            public final HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord invoke() {
                return this.this$0.hprof.getReader().readClassDumpRecord();
            }
        });
        this.classMap.put(Long.valueOf(objectId), classDumpRecord2);
        return classDumpRecord2;
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord readInstanceDumpRecord$shark(long objectId, IndexedObject.IndexedInstance indexedObject) {
        Intrinsics.checkParameterIsNotNull(indexedObject, "indexedObject");
        return (HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord) readObjectRecord(objectId, indexedObject, new Function0<HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord>() {
            {
                super(0);
            }

            @Override
            public final HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord invoke() {
                return this.this$0.hprof.getReader().readInstanceDumpRecord();
            }
        });
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord readObjectArrayDumpRecord$shark(long objectId, IndexedObject.IndexedObjectArray indexedObject) {
        Intrinsics.checkParameterIsNotNull(indexedObject, "indexedObject");
        return (HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord) readObjectRecord(objectId, indexedObject, new Function0<HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord>() {
            {
                super(0);
            }

            @Override
            public final HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord invoke() {
                return this.this$0.hprof.getReader().readObjectArrayDumpRecord();
            }
        });
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord readPrimitiveArrayDumpRecord$shark(long objectId, IndexedObject.IndexedPrimitiveArray indexedObject) {
        Intrinsics.checkParameterIsNotNull(indexedObject, "indexedObject");
        return (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord) readObjectRecord(objectId, indexedObject, new Function0<HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord>() {
            {
                super(0);
            }

            @Override
            public final HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord invoke() {
                return this.this$0.hprof.getReader().readPrimitiveArrayDumpRecord();
            }
        });
    }

    public final void setClassMap(Map<Long, HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.classMap = map;
    }

    public final String staticFieldName$shark(long classId, HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.StaticFieldRecord fieldRecord) {
        Intrinsics.checkParameterIsNotNull(fieldRecord, "fieldRecord");
        return this.index.fieldName(classId, fieldRecord.getNameStringId());
    }
}
