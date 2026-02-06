package kshark.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kshark.GcRoot;
import kshark.Hprof;
import kshark.HprofReader;
import kshark.HprofRecord;
import kshark.OnHprofRecordListener;
import kshark.PrimitiveType;
import kshark.ProguardMapping;
import kshark.SharkLog;
import kshark.internal.IndexedObject;
import kshark.internal.UnsortedByteEntries;
import kshark.internal.hppc.LongLongScatterMap;
import kshark.internal.hppc.LongObjectScatterMap;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 02\u00020\u0001:\u0002/0Bk\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\u0010\u0016J\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001a\u001a\u00020\u0006¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0015J\u0016\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0015J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0015H\u0002J\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\"0!0 J\u0018\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020$0!0 J\u0018\u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020&0!0 J\u0010\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u0015J\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020(0!0 J\u0018\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020,0!0 J\u000e\u0010-\u001a\u00020.2\u0006\u0010)\u001a\u00020\u0015R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lkshark/internal/HprofInMemoryIndex;", "", "positionSize", "", "hprofStringCache", "Lkshark/internal/hppc/LongObjectScatterMap;", "", "classNames", "Lkshark/internal/hppc/LongLongScatterMap;", "classIndex", "Lkshark/internal/SortedBytesMap;", "instanceIndex", "objectArrayIndex", "primitiveArrayIndex", "gcRoots", "", "Lkshark/GcRoot;", "proguardMapping", "Lkshark/ProguardMapping;", "primitiveWrapperTypes", "", "", "(ILkshark/internal/hppc/LongObjectScatterMap;Lkshark/internal/hppc/LongLongScatterMap;Lkshark/internal/SortedBytesMap;Lkshark/internal/SortedBytesMap;Lkshark/internal/SortedBytesMap;Lkshark/internal/SortedBytesMap;Ljava/util/List;Lkshark/ProguardMapping;Ljava/util/Set;)V", "getPrimitiveWrapperTypes", "()Ljava/util/Set;", "classId", "className", "(Ljava/lang/String;)Ljava/lang/Long;", "fieldName", "id", "hprofStringById", "indexedClassSequence", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "Lkshark/internal/IndexedObject$IndexedClass;", "indexedInstanceSequence", "Lkshark/internal/IndexedObject$IndexedInstance;", "indexedObjectArraySequence", "Lkshark/internal/IndexedObject$IndexedObjectArray;", "indexedObjectOrNull", "Lkshark/internal/IndexedObject;", "objectId", "indexedObjectSequence", "indexedPrimitiveArraySequence", "Lkshark/internal/IndexedObject$IndexedPrimitiveArray;", "objectIdIsIndexed", "", "Builder", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class HprofInMemoryIndex {

    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> PRIMITIVE_WRAPPER_TYPES;
    private final SortedBytesMap classIndex;
    private final LongLongScatterMap classNames;
    private final List<GcRoot> gcRoots;
    private final LongObjectScatterMap<String> hprofStringCache;
    private final SortedBytesMap instanceIndex;
    private final SortedBytesMap objectArrayIndex;
    private final int positionSize;
    private final SortedBytesMap primitiveArrayIndex;
    private final Set<Long> primitiveWrapperTypes;
    private final ProguardMapping proguardMapping;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0014\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r0\f¢\u0006\u0002\u0010\u000fJ\u0010\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$J\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)H\u0016R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lkshark/internal/HprofInMemoryIndex$Builder;", "Lkshark/OnHprofRecordListener;", "longIdentifiers", "", "fileLength", "", "classCount", "", "instanceCount", "objectArrayCount", "primitiveArrayCount", "indexedGcRootsTypes", "", "Lkotlin/reflect/KClass;", "Lkshark/GcRoot;", "(ZJIIIILjava/util/Set;)V", "classIndex", "Lkshark/internal/UnsortedByteEntries;", "classNames", "Lkshark/internal/hppc/LongLongScatterMap;", "gcRoots", "", "hprofStringCache", "Lkshark/internal/hppc/LongObjectScatterMap;", "", "identifierSize", "instanceIndex", "objectArrayIndex", "positionSize", "primitiveArrayIndex", "primitiveWrapperClassNames", "", "primitiveWrapperTypes", "buildIndex", "Lkshark/internal/HprofInMemoryIndex;", "proguardMapping", "Lkshark/ProguardMapping;", "onHprofRecord", "", "position", "record", "Lkshark/HprofRecord;", "shark"}, k = 1, mv = {1, 1, 15})
    private static final class Builder implements OnHprofRecordListener {
        private final UnsortedByteEntries classIndex;
        private final LongLongScatterMap classNames;
        private final List<GcRoot> gcRoots;
        private final LongObjectScatterMap<String> hprofStringCache;
        private final int identifierSize;
        private final Set<KClass<? extends GcRoot>> indexedGcRootsTypes;
        private final UnsortedByteEntries instanceIndex;
        private final UnsortedByteEntries objectArrayIndex;
        private final int positionSize;
        private final UnsortedByteEntries primitiveArrayIndex;
        private final Set<Long> primitiveWrapperClassNames;
        private final Set<Long> primitiveWrapperTypes;

        public Builder(boolean z, long j, int i, int i2, int i3, int i4, Set<? extends KClass<? extends GcRoot>> set) {
            Intrinsics.checkParameterIsNotNull(set, "indexedGcRootsTypes");
            this.indexedGcRootsTypes = set;
            this.identifierSize = z ? 8 : 4;
            this.positionSize = HprofInMemoryIndex.INSTANCE.byteSizeForUnsigned(j);
            this.hprofStringCache = new LongObjectScatterMap<>();
            this.classNames = new LongLongScatterMap();
            this.classIndex = new UnsortedByteEntries(this.positionSize + this.identifierSize + 4, z, i, 0.0d, 8, null);
            this.instanceIndex = new UnsortedByteEntries(this.identifierSize + this.positionSize, z, i2, 0.0d, 8, null);
            this.objectArrayIndex = new UnsortedByteEntries(this.positionSize + this.identifierSize + 4, z, i3, 0.0d, 8, null);
            this.primitiveArrayIndex = new UnsortedByteEntries(this.positionSize + 1 + 4, z, i4, 0.0d, 8, null);
            this.primitiveWrapperTypes = new LinkedHashSet();
            this.primitiveWrapperClassNames = new LinkedHashSet();
            this.gcRoots = new ArrayList();
        }

        public final HprofInMemoryIndex buildIndex(ProguardMapping proguardMapping) {
            SortedBytesMap sortedBytesMapMoveToSortedMap = this.instanceIndex.moveToSortedMap();
            SortedBytesMap sortedBytesMapMoveToSortedMap2 = this.objectArrayIndex.moveToSortedMap();
            SortedBytesMap sortedBytesMapMoveToSortedMap3 = this.primitiveArrayIndex.moveToSortedMap();
            return new HprofInMemoryIndex(this.positionSize, this.hprofStringCache, this.classNames, this.classIndex.moveToSortedMap(), sortedBytesMapMoveToSortedMap, sortedBytesMapMoveToSortedMap2, sortedBytesMapMoveToSortedMap3, this.gcRoots, proguardMapping, this.primitiveWrapperTypes, null);
        }

        @Override
        public void onHprofRecord(long position, HprofRecord record) {
            Intrinsics.checkParameterIsNotNull(record, "record");
            if (record instanceof HprofRecord.StringRecord) {
                HprofRecord.StringRecord stringRecord = (HprofRecord.StringRecord) record;
                if (HprofInMemoryIndex.PRIMITIVE_WRAPPER_TYPES.contains(stringRecord.getString())) {
                    this.primitiveWrapperClassNames.add(Long.valueOf(stringRecord.getId()));
                }
                this.hprofStringCache.set(stringRecord.getId(), kotlin.text.StringsKt.replace$default(stringRecord.getString(), '/', '.', false, 4, (Object) null));
                return;
            }
            if (record instanceof HprofRecord.LoadClassRecord) {
                HprofRecord.LoadClassRecord loadClassRecord = (HprofRecord.LoadClassRecord) record;
                this.classNames.set(loadClassRecord.getId(), loadClassRecord.getClassNameStringId());
                if (this.primitiveWrapperClassNames.contains(Long.valueOf(loadClassRecord.getClassNameStringId()))) {
                    this.primitiveWrapperTypes.add(Long.valueOf(loadClassRecord.getId()));
                    return;
                }
                return;
            }
            if (record instanceof HprofRecord.HeapDumpRecord.GcRootRecord) {
                GcRoot gcRoot = ((HprofRecord.HeapDumpRecord.GcRootRecord) record).getGcRoot();
                if (gcRoot.getId() == 0 || !this.indexedGcRootsTypes.contains(Reflection.getOrCreateKotlinClass(gcRoot.getClass()))) {
                    return;
                }
                this.gcRoots.add(gcRoot);
                return;
            }
            if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.ClassSkipContentRecord) {
                HprofRecord.HeapDumpRecord.ObjectRecord.ClassSkipContentRecord classSkipContentRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.ClassSkipContentRecord) record;
                UnsortedByteEntries.MutableByteSubArray mutableByteSubArrayAppend = this.classIndex.append(classSkipContentRecord.getId());
                mutableByteSubArrayAppend.writeTruncatedLong(position, this.positionSize);
                mutableByteSubArrayAppend.writeId(classSkipContentRecord.getSuperclassId());
                mutableByteSubArrayAppend.writeInt(classSkipContentRecord.getInstanceSize());
                return;
            }
            if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord) {
                HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord instanceSkipContentRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord) record;
                UnsortedByteEntries.MutableByteSubArray mutableByteSubArrayAppend2 = this.instanceIndex.append(instanceSkipContentRecord.getId());
                mutableByteSubArrayAppend2.writeTruncatedLong(position, this.positionSize);
                mutableByteSubArrayAppend2.writeId(instanceSkipContentRecord.getClassId());
                return;
            }
            if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord) {
                HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord objectArraySkipContentRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord) record;
                UnsortedByteEntries.MutableByteSubArray mutableByteSubArrayAppend3 = this.objectArrayIndex.append(objectArraySkipContentRecord.getId());
                mutableByteSubArrayAppend3.writeTruncatedLong(position, this.positionSize);
                mutableByteSubArrayAppend3.writeId(objectArraySkipContentRecord.getArrayClassId());
                mutableByteSubArrayAppend3.writeInt(objectArraySkipContentRecord.getSize());
                return;
            }
            if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord) {
                HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord primitiveArraySkipContentRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord) record;
                UnsortedByteEntries.MutableByteSubArray mutableByteSubArrayAppend4 = this.primitiveArrayIndex.append(primitiveArraySkipContentRecord.getId());
                mutableByteSubArrayAppend4.writeTruncatedLong(position, this.positionSize);
                mutableByteSubArrayAppend4.writeByte((byte) primitiveArraySkipContentRecord.getType().ordinal());
                mutableByteSubArrayAppend4.writeInt(primitiveArraySkipContentRecord.getSize());
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J.\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u00110\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkshark/internal/HprofInMemoryIndex$Companion;", "", "()V", "PRIMITIVE_WRAPPER_TYPES", "", "", "byteSizeForUnsigned", "", "maxValue", "", "createReadingHprof", "Lkshark/internal/HprofInMemoryIndex;", "hprof", "Lkshark/Hprof;", "proguardMapping", "Lkshark/ProguardMapping;", "indexedGcRootTypes", "Lkotlin/reflect/KClass;", "Lkshark/GcRoot;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int byteSizeForUnsigned(long maxValue) {
            int i = 0;
            while (maxValue != 0) {
                maxValue >>= 8;
                i++;
            }
            return i;
        }

        public final HprofInMemoryIndex createReadingHprof(Hprof hprof, ProguardMapping proguardMapping, Set<? extends KClass<? extends GcRoot>> indexedGcRootTypes) throws IOException {
            Intrinsics.checkParameterIsNotNull(hprof, "hprof");
            Intrinsics.checkParameterIsNotNull(indexedGcRootTypes, "indexedGcRootTypes");
            Set<? extends KClass<? extends HprofRecord>> of = SetsKt.setOf((Object[]) new KClass[]{Reflection.getOrCreateKotlinClass(HprofRecord.StringRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.LoadClassRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.ClassSkipContentRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.GcRootRecord.class)});
            HprofReader reader = hprof.getReader();
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 0;
            final Ref.IntRef intRef2 = new Ref.IntRef();
            intRef2.element = 0;
            final Ref.IntRef intRef3 = new Ref.IntRef();
            intRef3.element = 0;
            final Ref.IntRef intRef4 = new Ref.IntRef();
            intRef4.element = 0;
            Set<? extends KClass<? extends HprofRecord>> of2 = SetsKt.setOf((Object[]) new KClass[]{Reflection.getOrCreateKotlinClass(HprofRecord.LoadClassRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord.class), Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord.class)});
            OnHprofRecordListener.Companion companion = OnHprofRecordListener.INSTANCE;
            reader.readHprofRecords(of2, new OnHprofRecordListener() {
                @Override
                public void onHprofRecord(long position, HprofRecord record) {
                    Intrinsics.checkParameterIsNotNull(record, "record");
                    if (record instanceof HprofRecord.LoadClassRecord) {
                        intRef.element++;
                        return;
                    }
                    if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord) {
                        intRef2.element++;
                    } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord) {
                        intRef3.element++;
                    } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord) {
                        intRef4.element++;
                    }
                }
            });
            SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
            if (logger != null) {
                logger.d("classCount:" + intRef.element + " instanceCount:" + intRef2.element + " objectArrayCount:" + intRef3.element + " primitiveArrayCount:" + intRef4.element);
            }
            hprof.moveReaderTo(reader.getStartPosition());
            Builder builder = new Builder(reader.getIdentifierByteSize() == 8, hprof.getFileLength(), intRef.element, intRef2.element, intRef3.element, intRef4.element, indexedGcRootTypes);
            reader.readHprofRecords(of, builder);
            return builder.buildIndex(proguardMapping);
        }
    }

    static {
        String name = Boolean.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "Boolean::class.java.name");
        String name2 = Character.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name2, "Char::class.java.name");
        String name3 = Float.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name3, "Float::class.java.name");
        String name4 = Double.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name4, "Double::class.java.name");
        String name5 = Byte.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name5, "Byte::class.java.name");
        String name6 = Short.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name6, "Short::class.java.name");
        String name7 = Integer.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name7, "Int::class.java.name");
        String name8 = Long.TYPE.getName();
        Intrinsics.checkExpressionValueIsNotNull(name8, "Long::class.java.name");
        PRIMITIVE_WRAPPER_TYPES = SetsKt.setOf((Object[]) new String[]{name, name2, name3, name4, name5, name6, name7, name8});
    }

    private HprofInMemoryIndex(int i, LongObjectScatterMap<String> longObjectScatterMap, LongLongScatterMap longLongScatterMap, SortedBytesMap sortedBytesMap, SortedBytesMap sortedBytesMap2, SortedBytesMap sortedBytesMap3, SortedBytesMap sortedBytesMap4, List<? extends GcRoot> list, ProguardMapping proguardMapping, Set<Long> set) {
        this.positionSize = i;
        this.hprofStringCache = longObjectScatterMap;
        this.classNames = longLongScatterMap;
        this.classIndex = sortedBytesMap;
        this.instanceIndex = sortedBytesMap2;
        this.objectArrayIndex = sortedBytesMap3;
        this.primitiveArrayIndex = sortedBytesMap4;
        this.gcRoots = list;
        this.proguardMapping = proguardMapping;
        this.primitiveWrapperTypes = set;
    }

    public HprofInMemoryIndex(int i, LongObjectScatterMap longObjectScatterMap, LongLongScatterMap longLongScatterMap, SortedBytesMap sortedBytesMap, SortedBytesMap sortedBytesMap2, SortedBytesMap sortedBytesMap3, SortedBytesMap sortedBytesMap4, List list, ProguardMapping proguardMapping, Set set, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, longObjectScatterMap, longLongScatterMap, sortedBytesMap, sortedBytesMap2, sortedBytesMap3, sortedBytesMap4, list, proguardMapping, set);
    }

    private final String hprofStringById(long id) {
        String str = this.hprofStringCache.get(id);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Hprof string " + id + " not in cache");
    }

    public final Long classId(String className) {
        Pair<Long, String> next;
        Pair<Long, Long> next2;
        Intrinsics.checkParameterIsNotNull(className, "className");
        Iterator<Pair<Long, String>> it = this.hprofStringCache.entrySequence().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(next.getSecond(), className)) {
                break;
            }
        }
        Pair<Long, String> pair = next;
        Long first = pair != null ? pair.getFirst() : null;
        if (first == null) {
            return null;
        }
        long jLongValue = first.longValue();
        Iterator<Pair<Long, Long>> it2 = this.classNames.entrySequence().iterator();
        while (true) {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
            if (next2.getSecond().longValue() == jLongValue) {
                break;
            }
        }
        Pair<Long, Long> pair2 = next2;
        if (pair2 != null) {
            return pair2.getFirst();
        }
        return null;
    }

    public final String className(long classId) {
        String strDeobfuscateClassName;
        String strHprofStringById = hprofStringById(this.classNames.get(classId));
        ProguardMapping proguardMapping = this.proguardMapping;
        return (proguardMapping == null || (strDeobfuscateClassName = proguardMapping.deobfuscateClassName(strHprofStringById)) == null) ? strHprofStringById : strDeobfuscateClassName;
    }

    public final String fieldName(long classId, long id) {
        String strHprofStringById = hprofStringById(id);
        if (this.proguardMapping == null) {
            return strHprofStringById;
        }
        String strDeobfuscateFieldName = this.proguardMapping.deobfuscateFieldName(hprofStringById(this.classNames.get(classId)), strHprofStringById);
        return strDeobfuscateFieldName != null ? strDeobfuscateFieldName : strHprofStringById;
    }

    public final List<GcRoot> gcRoots() {
        return this.gcRoots;
    }

    public final Set<Long> getPrimitiveWrapperTypes() {
        return this.primitiveWrapperTypes;
    }

    public final Sequence<Pair<Long, IndexedObject.IndexedClass>> indexedClassSequence() {
        return SequencesKt.map(this.classIndex.entrySequence(), new Function1<Pair<? extends Long, ? extends ByteSubArray>, Pair<? extends Long, ? extends IndexedObject.IndexedClass>>() {
            {
                super(1);
            }

            @Override
            public Pair<? extends Long, ? extends IndexedObject.IndexedClass> invoke(Pair<? extends Long, ? extends ByteSubArray> pair) {
                return invoke2((Pair<Long, ByteSubArray>) pair);
            }

            public final Pair<Long, IndexedObject.IndexedClass> invoke2(Pair<Long, ByteSubArray> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                ByteSubArray second = pair.getSecond();
                return TuplesKt.to(Long.valueOf(jLongValue), new IndexedObject.IndexedClass(second.readTruncatedLong(HprofInMemoryIndex.this.positionSize), second.readId(), second.readInt()));
            }
        });
    }

    public final Sequence<Pair<Long, IndexedObject.IndexedInstance>> indexedInstanceSequence() {
        return SequencesKt.map(this.instanceIndex.entrySequence(), new Function1<Pair<? extends Long, ? extends ByteSubArray>, Pair<? extends Long, ? extends IndexedObject.IndexedInstance>>() {
            {
                super(1);
            }

            @Override
            public Pair<? extends Long, ? extends IndexedObject.IndexedInstance> invoke(Pair<? extends Long, ? extends ByteSubArray> pair) {
                return invoke2((Pair<Long, ByteSubArray>) pair);
            }

            public final Pair<Long, IndexedObject.IndexedInstance> invoke2(Pair<Long, ByteSubArray> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                ByteSubArray second = pair.getSecond();
                return TuplesKt.to(Long.valueOf(jLongValue), new IndexedObject.IndexedInstance(second.readTruncatedLong(HprofInMemoryIndex.this.positionSize), second.readId()));
            }
        });
    }

    public final Sequence<Pair<Long, IndexedObject.IndexedObjectArray>> indexedObjectArraySequence() {
        return SequencesKt.map(this.objectArrayIndex.entrySequence(), new Function1<Pair<? extends Long, ? extends ByteSubArray>, Pair<? extends Long, ? extends IndexedObject.IndexedObjectArray>>() {
            {
                super(1);
            }

            @Override
            public Pair<? extends Long, ? extends IndexedObject.IndexedObjectArray> invoke(Pair<? extends Long, ? extends ByteSubArray> pair) {
                return invoke2((Pair<Long, ByteSubArray>) pair);
            }

            public final Pair<Long, IndexedObject.IndexedObjectArray> invoke2(Pair<Long, ByteSubArray> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                ByteSubArray second = pair.getSecond();
                return TuplesKt.to(Long.valueOf(jLongValue), new IndexedObject.IndexedObjectArray(second.readTruncatedLong(HprofInMemoryIndex.this.positionSize), second.readId(), second.readInt()));
            }
        });
    }

    public final IndexedObject indexedObjectOrNull(long objectId) {
        ByteSubArray byteSubArray = this.classIndex.get(objectId);
        if (byteSubArray != null) {
            return new IndexedObject.IndexedClass(byteSubArray.readTruncatedLong(this.positionSize), byteSubArray.readId(), byteSubArray.readInt());
        }
        ByteSubArray byteSubArray2 = this.instanceIndex.get(objectId);
        if (byteSubArray2 != null) {
            return new IndexedObject.IndexedInstance(byteSubArray2.readTruncatedLong(this.positionSize), byteSubArray2.readId());
        }
        ByteSubArray byteSubArray3 = this.objectArrayIndex.get(objectId);
        if (byteSubArray3 != null) {
            return new IndexedObject.IndexedObjectArray(byteSubArray3.readTruncatedLong(this.positionSize), byteSubArray3.readId(), byteSubArray3.readInt());
        }
        ByteSubArray byteSubArray4 = this.primitiveArrayIndex.get(objectId);
        if (byteSubArray4 != null) {
            return new IndexedObject.IndexedPrimitiveArray(byteSubArray4.readTruncatedLong(this.positionSize), PrimitiveType.values()[byteSubArray4.readByte()], byteSubArray4.readInt());
        }
        return null;
    }

    public final Sequence<Pair<Long, IndexedObject>> indexedObjectSequence() {
        return SequencesKt.plus(SequencesKt.plus(SequencesKt.plus((Sequence) indexedClassSequence(), (Sequence) indexedInstanceSequence()), (Sequence) indexedObjectArraySequence()), (Sequence) indexedPrimitiveArraySequence());
    }

    public final Sequence<Pair<Long, IndexedObject.IndexedPrimitiveArray>> indexedPrimitiveArraySequence() {
        return SequencesKt.map(this.primitiveArrayIndex.entrySequence(), new Function1<Pair<? extends Long, ? extends ByteSubArray>, Pair<? extends Long, ? extends IndexedObject.IndexedPrimitiveArray>>() {
            {
                super(1);
            }

            @Override
            public Pair<? extends Long, ? extends IndexedObject.IndexedPrimitiveArray> invoke(Pair<? extends Long, ? extends ByteSubArray> pair) {
                return invoke2((Pair<Long, ByteSubArray>) pair);
            }

            public final Pair<Long, IndexedObject.IndexedPrimitiveArray> invoke2(Pair<Long, ByteSubArray> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "it");
                long jLongValue = pair.getFirst().longValue();
                ByteSubArray second = pair.getSecond();
                return TuplesKt.to(Long.valueOf(jLongValue), new IndexedObject.IndexedPrimitiveArray(second.readTruncatedLong(HprofInMemoryIndex.this.positionSize), PrimitiveType.values()[second.readByte()], second.readInt()));
            }
        });
    }

    public final boolean objectIdIsIndexed(long objectId) {
        return (this.classIndex.get(objectId) == null && this.instanceIndex.get(objectId) == null && this.objectArrayIndex.get(objectId) == null && this.primitiveArrayIndex.get(objectId) == null) ? false : true;
    }
}
