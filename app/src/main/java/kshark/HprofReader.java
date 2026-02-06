package kshark;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.Charsets;
import kshark.GcRoot;
import kshark.HprofRecord;
import kshark.ValueHolder;
import okio.BufferedSource;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 c2\u00020\u0001:\u0001cB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020&J\b\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020.2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010/\u001a\u000200H\u0002J$\u00101\u001a\u0002022\u0014\u00103\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020605042\u0006\u00107\u001a\u000208J\b\u00109\u001a\u00020\u0007H\u0002J\u0010\u0010:\u001a\u00020;2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0006\u0010<\u001a\u00020=J\u0006\u0010>\u001a\u00020?J\b\u0010@\u001a\u00020\u0005H\u0002J\u0010\u0010A\u001a\u00020B2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010C\u001a\u00020\u0007H\u0002J\u0010\u0010D\u001a\u00020;2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0006\u0010E\u001a\u00020FJ\u0006\u0010G\u001a\u00020HJ\u0006\u0010I\u001a\u00020JJ\u0006\u0010K\u001a\u00020LJ\b\u0010M\u001a\u00020NH\u0002J\u0010\u0010O\u001a\u00020P2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0018\u0010Q\u001a\u00020R2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010S\u001a\u00020TH\u0002J\b\u0010U\u001a\u00020\u0005H\u0002J\b\u0010V\u001a\u00020\u0007H\u0002J\b\u0010W\u001a\u00020\u0005H\u0002J\u0010\u0010X\u001a\u00020R2\u0006\u0010\u001e\u001a\u00020\u0007H\u0002J\u000e\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u0005J\u0010\u0010\\\u001a\u0002022\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\u0010\u0010\\\u001a\u0002022\u0006\u0010\u001e\u001a\u00020\u0007H\u0002J\b\u0010]\u001a\u000202H\u0002J\b\u0010^\u001a\u000202H\u0002J\b\u0010_\u001a\u000202H\u0002J\b\u0010`\u001a\u000202H\u0002J\b\u0010a\u001a\u000202H\u0002J\u0010\u0010b\u001a\u00020\u00052\u0006\u0010[\u001a\u00020\u0005H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lkshark/HprofReader;", "", "source", "Lokio/BufferedSource;", "identifierByteSize", "", "startPosition", "", "(Lokio/BufferedSource;IJ)V", "getIdentifierByteSize", "()I", "<set-?>", "position", "getPosition", "()J", "setPosition$shark", "(J)V", "getStartPosition", "typeSizes", "", "exhausted", "", "readBoolean", "readBooleanArray", "", "arrayLength", "readByte", "", "readByteArray", "", "byteCount", "readChar", "", "readCharArray", "", "readClassDumpRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ClassDumpRecord;", "readClassSkipContentRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ClassSkipContentRecord;", "readDouble", "", "readDoubleArray", "", "readFloat", "", "readFloatArray", "", "readHeapDumpInfoRecord", "Lkshark/HprofRecord$HeapDumpRecord$HeapDumpInfoRecord;", "readHprofRecords", "", "recordTypes", "", "Lkotlin/reflect/KClass;", "Lkshark/HprofRecord;", "listener", "Lkshark/OnHprofRecordListener;", "readId", "readIdArray", "", "readInstanceDumpRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$InstanceDumpRecord;", "readInstanceSkipContentRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$InstanceSkipContentRecord;", "readInt", "readIntArray", "", "readLong", "readLongArray", "readObjectArrayDumpRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ObjectArrayDumpRecord;", "readObjectArraySkipContentRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ObjectArraySkipContentRecord;", "readPrimitiveArrayDumpRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$PrimitiveArrayDumpRecord;", "readPrimitiveArraySkipContentRecord", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$PrimitiveArraySkipContentRecord;", "readShort", "", "readShortArray", "", "readString", "", "charset", "Ljava/nio/charset/Charset;", "readUnsignedByte", "readUnsignedInt", "readUnsignedShort", "readUtf8", "readValue", "Lkshark/ValueHolder;", "type", "skip", "skipClassDumpRecord", "skipHeapDumpInfoRecord", "skipInstanceDumpRecord", "skipObjectArrayDumpRecord", "skipPrimitiveArrayDumpRecord", "typeSize", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class HprofReader {
    public static final int ALLOC_SITES = 6;
    private static final int BYTE_MASK = 255;
    public static final int CLASS_DUMP = 32;
    public static final int CONTROL_SETTINGS = 14;
    public static final int CPU_SAMPLES = 13;
    public static final int END_THREAD = 11;
    public static final int HEAP_DUMP = 12;
    public static final int HEAP_DUMP_END = 44;
    public static final int HEAP_DUMP_INFO = 254;
    public static final int HEAP_DUMP_SEGMENT = 28;
    public static final int HEAP_SUMMARY = 7;
    public static final int INSTANCE_DUMP = 33;
    private static final long INT_MASK = 4294967295L;
    public static final int LOAD_CLASS = 2;
    public static final int OBJECT_ARRAY_DUMP = 34;
    public static final int PRIMITIVE_ARRAY_DUMP = 35;
    public static final int PRIMITIVE_ARRAY_NODATA = 195;
    public static final int ROOT_DEBUGGER = 139;
    public static final int ROOT_FINALIZING = 138;
    public static final int ROOT_INTERNED_STRING = 137;
    public static final int ROOT_JAVA_FRAME = 3;
    public static final int ROOT_JNI_GLOBAL = 1;
    public static final int ROOT_JNI_LOCAL = 2;
    public static final int ROOT_JNI_MONITOR = 142;
    public static final int ROOT_MONITOR_USED = 7;
    public static final int ROOT_NATIVE_STACK = 4;
    public static final int ROOT_REFERENCE_CLEANUP = 140;
    public static final int ROOT_STICKY_CLASS = 5;
    public static final int ROOT_THREAD_BLOCK = 6;
    public static final int ROOT_THREAD_OBJECT = 8;
    public static final int ROOT_UNKNOWN = 255;
    public static final int ROOT_UNREACHABLE = 144;
    public static final int ROOT_VM_INTERNAL = 141;
    public static final int STACK_FRAME = 4;
    public static final int STACK_TRACE = 5;
    public static final int START_THREAD = 10;
    public static final int STRING_IN_UTF8 = 1;
    public static final int UNLOAD_CLASS = 3;
    private final int identifierByteSize;
    private long position;
    private BufferedSource source;
    private final long startPosition;
    private final Map<Integer, Integer> typeSizes;
    private static final int BOOLEAN_SIZE = PrimitiveType.BOOLEAN.getByteSize();
    private static final int CHAR_SIZE = PrimitiveType.CHAR.getByteSize();
    private static final int FLOAT_SIZE = PrimitiveType.FLOAT.getByteSize();
    private static final int DOUBLE_SIZE = PrimitiveType.DOUBLE.getByteSize();
    private static final int BYTE_SIZE = PrimitiveType.BYTE.getByteSize();
    private static final int SHORT_SIZE = PrimitiveType.SHORT.getByteSize();
    private static final int INT_SIZE = PrimitiveType.INT.getByteSize();
    private static final int LONG_SIZE = PrimitiveType.LONG.getByteSize();
    private static final int BOOLEAN_TYPE = PrimitiveType.BOOLEAN.getHprofType();
    private static final int CHAR_TYPE = PrimitiveType.CHAR.getHprofType();
    private static final int FLOAT_TYPE = PrimitiveType.FLOAT.getHprofType();
    private static final int DOUBLE_TYPE = PrimitiveType.DOUBLE.getHprofType();
    private static final int BYTE_TYPE = PrimitiveType.BYTE.getHprofType();
    private static final int SHORT_TYPE = PrimitiveType.SHORT.getHprofType();
    private static final int INT_TYPE = PrimitiveType.INT.getHprofType();
    private static final int LONG_TYPE = PrimitiveType.LONG.getHprofType();

    public HprofReader(BufferedSource bufferedSource, int i, long j) {
        Intrinsics.checkParameterIsNotNull(bufferedSource, "source");
        this.source = bufferedSource;
        this.identifierByteSize = i;
        this.startPosition = j;
        this.position = j;
        this.typeSizes = MapsKt.plus(PrimitiveType.INSTANCE.getByteSizeByHprofType(), TuplesKt.to(2, Integer.valueOf(this.identifierByteSize)));
    }

    public HprofReader(BufferedSource bufferedSource, int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bufferedSource, i, (i2 & 4) != 0 ? 0L : j);
    }

    private final boolean exhausted() {
        return this.source.exhausted();
    }

    private final boolean readBoolean() {
        this.position += BOOLEAN_SIZE;
        return this.source.readByte() != 0;
    }

    private final boolean[] readBooleanArray(int arrayLength) {
        boolean[] zArr = new boolean[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            zArr[i] = readByte() != 0;
        }
        return zArr;
    }

    private final byte readByte() {
        this.position += BYTE_SIZE;
        return this.source.readByte();
    }

    private final byte[] readByteArray(int byteCount) throws IOException {
        long j = byteCount;
        this.position += j;
        byte[] byteArray = this.source.readByteArray(j);
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "source.readByteArray(byteCount.toLong())");
        return byteArray;
    }

    private final char readChar() {
        return readString(CHAR_SIZE, Charsets.UTF_16BE).charAt(0);
    }

    private final char[] readCharArray(int arrayLength) throws IOException {
        String string = readString(CHAR_SIZE * arrayLength, Charsets.UTF_16BE);
        if (string == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        char[] charArray = string.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
        return charArray;
    }

    private final double readDouble() {
        DoubleCompanionObject doubleCompanionObject = DoubleCompanionObject.INSTANCE;
        return Double.longBitsToDouble(readLong());
    }

    private final double[] readDoubleArray(int arrayLength) {
        double[] dArr = new double[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            dArr[i] = readDouble();
        }
        return dArr;
    }

    private final float readFloat() {
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        return Float.intBitsToFloat(readInt());
    }

    private final float[] readFloatArray(int arrayLength) {
        float[] fArr = new float[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            fArr[i] = readFloat();
        }
        return fArr;
    }

    private final HprofRecord.HeapDumpRecord.HeapDumpInfoRecord readHeapDumpInfoRecord() {
        return new HprofRecord.HeapDumpRecord.HeapDumpInfoRecord(readInt(), readId());
    }

    private final long readId() {
        int i;
        int i2 = this.identifierByteSize;
        if (i2 == 1) {
            i = readByte();
        } else if (i2 == 2) {
            i = readShort();
        } else {
            if (i2 != 4) {
                if (i2 == 8) {
                    return readLong();
                }
                throw new IllegalArgumentException("ID Length must be 1, 2, 4, or 8");
            }
            i = readInt();
        }
        return i;
    }

    private final long[] readIdArray(int arrayLength) {
        long[] jArr = new long[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            jArr[i] = readId();
        }
        return jArr;
    }

    private final int readInt() {
        this.position += INT_SIZE;
        return this.source.readInt();
    }

    private final int[] readIntArray(int arrayLength) {
        int[] iArr = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            iArr[i] = readInt();
        }
        return iArr;
    }

    private final long readLong() {
        this.position += LONG_SIZE;
        return this.source.readLong();
    }

    private final long[] readLongArray(int arrayLength) {
        long[] jArr = new long[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            jArr[i] = readLong();
        }
        return jArr;
    }

    private final short readShort() {
        this.position += SHORT_SIZE;
        return this.source.readShort();
    }

    private final short[] readShortArray(int arrayLength) {
        short[] sArr = new short[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            sArr[i] = readShort();
        }
        return sArr;
    }

    private final String readString(int byteCount, Charset charset) throws IOException {
        long j = byteCount;
        this.position += j;
        String string = this.source.readString(j, charset);
        Intrinsics.checkExpressionValueIsNotNull(string, "source.readString(byteCount.toLong(), charset)");
        return string;
    }

    private final int readUnsignedByte() {
        return readByte() & 255;
    }

    private final long readUnsignedInt() {
        return readInt() & 4294967295L;
    }

    private final int readUnsignedShort() {
        return readShort() & 65535;
    }

    private final String readUtf8(long byteCount) throws IOException {
        this.position += byteCount;
        String utf8 = this.source.readUtf8(byteCount);
        Intrinsics.checkExpressionValueIsNotNull(utf8, "source.readUtf8(byteCount)");
        return utf8;
    }

    private final void skip(int byteCount) throws IOException {
        long j = byteCount;
        this.position += j;
        this.source.skip(j);
    }

    private final void skip(long byteCount) throws IOException {
        this.position += byteCount;
        this.source.skip(byteCount);
    }

    private final void skipClassDumpRecord() throws IOException {
        int i = this.identifierByteSize;
        int i2 = INT_SIZE;
        skip(i + i2 + i + i + i + i + i + i + i2);
        int unsignedShort = readUnsignedShort();
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            skip(SHORT_SIZE);
            skip(typeSize(readUnsignedByte()));
        }
        int unsignedShort2 = readUnsignedShort();
        for (int i4 = 0; i4 < unsignedShort2; i4++) {
            skip(this.identifierByteSize);
            skip(typeSize(readUnsignedByte()));
        }
        skip(readUnsignedShort() * (this.identifierByteSize + BYTE_SIZE));
    }

    private final void skipHeapDumpInfoRecord() throws IOException {
        int i = this.identifierByteSize;
        skip(i + i);
    }

    private final void skipInstanceDumpRecord() throws IOException {
        int i = this.identifierByteSize;
        skip(INT_SIZE + i + i);
        skip(readInt());
    }

    private final void skipObjectArrayDumpRecord() throws IOException {
        skip(this.identifierByteSize + INT_SIZE);
        int i = readInt();
        int i2 = this.identifierByteSize;
        skip(i2 + (i * i2));
    }

    private final void skipPrimitiveArrayDumpRecord() throws IOException {
        skip(this.identifierByteSize + INT_SIZE);
        skip(readInt() * typeSize(readUnsignedByte()));
    }

    private final int typeSize(int type) {
        return ((Number) MapsKt.getValue(this.typeSizes, Integer.valueOf(type))).intValue();
    }

    public final int getIdentifierByteSize() {
        return this.identifierByteSize;
    }

    public final long getPosition() {
        return this.position;
    }

    public final long getStartPosition() {
        return this.startPosition;
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord readClassDumpRecord() throws IOException {
        HprofReader hprofReader = this;
        long id = readId();
        int i = readInt();
        long id2 = readId();
        long id3 = readId();
        long id4 = readId();
        long id5 = readId();
        readId();
        readId();
        int i2 = readInt();
        int unsignedShort = readUnsignedShort();
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            hprofReader.skip(SHORT_SIZE);
            hprofReader.skip(hprofReader.typeSize(readUnsignedByte()));
        }
        int unsignedShort2 = readUnsignedShort();
        ArrayList arrayList = new ArrayList(unsignedShort2);
        int i4 = 0;
        while (i4 < unsignedShort2) {
            long j = id5;
            long id6 = readId();
            int i5 = unsignedShort2;
            int unsignedByte = readUnsignedByte();
            arrayList.add(new HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.StaticFieldRecord(id6, unsignedByte, hprofReader.readValue(unsignedByte)));
            i4++;
            hprofReader = this;
            id5 = j;
            unsignedShort2 = i5;
            i2 = i2;
        }
        long j2 = id5;
        int i6 = i2;
        int unsignedShort3 = readUnsignedShort();
        ArrayList arrayList2 = new ArrayList(unsignedShort3);
        int i7 = 0;
        while (i7 < unsignedShort3) {
            arrayList2.add(new HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.FieldRecord(readId(), readUnsignedByte()));
            i7++;
            id4 = id4;
        }
        return new HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord(id, i, id2, id3, id4, j2, i6, arrayList, arrayList2);
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.ClassSkipContentRecord readClassSkipContentRecord() throws IOException {
        long id = readId();
        int i = readInt();
        long id2 = readId();
        long id3 = readId();
        long id4 = readId();
        long id5 = readId();
        readId();
        readId();
        int i2 = readInt();
        int unsignedShort = readUnsignedShort();
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            skip(SHORT_SIZE);
            skip(typeSize(readUnsignedByte()));
        }
        int unsignedShort2 = readUnsignedShort();
        int i4 = 0;
        while (i4 < unsignedShort2) {
            skip(this.identifierByteSize);
            int unsignedByte = readUnsignedByte();
            int i5 = unsignedShort2;
            skip(unsignedByte == 2 ? this.identifierByteSize : ((Number) MapsKt.getValue(PrimitiveType.INSTANCE.getByteSizeByHprofType(), Integer.valueOf(unsignedByte))).intValue());
            i4++;
            unsignedShort2 = i5;
        }
        int unsignedShort3 = readUnsignedShort();
        skip((this.identifierByteSize + 1) * unsignedShort3);
        return new HprofRecord.HeapDumpRecord.ObjectRecord.ClassSkipContentRecord(id, i, id2, id3, id4, id5, i2, unsignedShort2, unsignedShort3);
    }

    public final void readHprofRecords(Set<? extends KClass<? extends HprofRecord>> recordTypes, OnHprofRecordListener listener) throws IOException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        long j;
        int i;
        long j2;
        Intrinsics.checkParameterIsNotNull(recordTypes, "recordTypes");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        boolean zContains = recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.class));
        boolean z6 = zContains || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.StringRecord.class));
        boolean z7 = zContains || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.LoadClassRecord.class));
        boolean z8 = zContains || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpEndRecord.class));
        boolean z9 = zContains || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.StackFrameRecord.class));
        boolean z10 = zContains || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.StackTraceRecord.class));
        boolean z11 = zContains || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.class));
        boolean z12 = z11 || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.GcRootRecord.class));
        boolean z13 = zContains || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.HeapDumpInfoRecord.class));
        boolean z14 = z11 || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.class));
        boolean z15 = z14 || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.class));
        boolean zContains2 = recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.ClassSkipContentRecord.class));
        boolean z16 = z14 || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord.class));
        boolean zContains3 = recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord.class));
        boolean z17 = z14 || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord.class));
        boolean z18 = z6;
        boolean zContains4 = recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord.class));
        boolean z19 = z14 || recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.class));
        boolean z20 = z7;
        boolean zContains5 = recordTypes.contains(Reflection.getOrCreateKotlinClass(HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord.class));
        int byteSize = PrimitiveType.INT.getByteSize();
        while (!exhausted()) {
            boolean z21 = z9;
            int unsignedByte = readUnsignedByte();
            skip(byteSize);
            boolean z22 = z15;
            boolean z23 = zContains2;
            long unsignedInt = readUnsignedInt();
            boolean z24 = z10;
            if (unsignedByte == 1) {
                z = z13;
                z2 = zContains3;
                z3 = z17;
                z4 = zContains4;
                z5 = z8;
                if (z18) {
                    listener.onHprofRecord(this.position, new HprofRecord.StringRecord(readId(), readUtf8(unsignedInt - this.identifierByteSize)));
                } else {
                    skip(unsignedInt);
                }
            } else if (unsignedByte == 2) {
                z = z13;
                z2 = zContains3;
                z3 = z17;
                z4 = zContains4;
                z5 = z8;
                if (z20) {
                    listener.onHprofRecord(this.position, new HprofRecord.LoadClassRecord(readInt(), readId(), readInt(), readId()));
                } else {
                    skip(unsignedInt);
                }
            } else if (unsignedByte == 4) {
                z = z13;
                z2 = zContains3;
                z3 = z17;
                z4 = zContains4;
                z5 = z8;
                if (z21) {
                    listener.onHprofRecord(this.position, new HprofRecord.StackFrameRecord(readId(), readId(), readId(), readId(), readInt(), readInt()));
                } else {
                    skip(unsignedInt);
                }
            } else if (unsignedByte == 5) {
                z = z13;
                z2 = zContains3;
                z3 = z17;
                z4 = zContains4;
                z5 = z8;
                if (z24) {
                    listener.onHprofRecord(this.position, new HprofRecord.StackTraceRecord(readInt(), readInt(), readIdArray(readInt())));
                } else {
                    skip(unsignedInt);
                }
            } else if (unsignedByte == 12 || unsignedByte == 28) {
                long j3 = this.position;
                z = z13;
                z2 = zContains3;
                z4 = zContains4;
                long j4 = 0;
                int i2 = 0;
                z3 = z17;
                while (true) {
                    long j5 = this.position;
                    z5 = z8;
                    if (j5 - j3 < unsignedInt) {
                        int unsignedByte2 = readUnsignedByte();
                        long j6 = j3;
                        if (unsignedByte2 == 144) {
                            j = j5;
                            i = unsignedByte2;
                            j2 = unsignedInt;
                            if (z12) {
                                listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.Unreachable(readId())));
                            } else {
                                skip(this.identifierByteSize);
                            }
                        } else {
                            if (unsignedByte2 == 195) {
                                throw new UnsupportedOperationException("PRIMITIVE_ARRAY_NODATA cannot be parsed");
                            }
                            if (unsignedByte2 == 254) {
                                j = j5;
                                i = unsignedByte2;
                                j2 = unsignedInt;
                                if (z) {
                                    listener.onHprofRecord(this.position, readHeapDumpInfoRecord());
                                } else {
                                    skipHeapDumpInfoRecord();
                                }
                            } else if (unsignedByte2 != 255) {
                                switch (unsignedByte2) {
                                    case 1:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            int i3 = this.identifierByteSize;
                                            skip(i3 + i3);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.JniGlobal(readId(), readId())));
                                            break;
                                        }
                                    case 2:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            skip(this.identifierByteSize + byteSize + byteSize);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.JniLocal(readId(), readInt(), readInt())));
                                            break;
                                        }
                                    case 3:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            skip(this.identifierByteSize + byteSize + byteSize);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.JavaFrame(readId(), readInt(), readInt())));
                                            break;
                                        }
                                    case 4:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            skip(this.identifierByteSize + byteSize);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.NativeStack(readId(), readInt())));
                                            break;
                                        }
                                    case 5:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            skip(this.identifierByteSize);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.StickyClass(readId())));
                                            break;
                                        }
                                    case 6:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            skip(this.identifierByteSize + byteSize);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.ThreadBlock(readId(), readInt())));
                                            break;
                                        }
                                    case 7:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            skip(this.identifierByteSize);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.MonitorUsed(readId())));
                                            break;
                                        }
                                    case 8:
                                        j = j5;
                                        i = unsignedByte2;
                                        j2 = unsignedInt;
                                        if (!z12) {
                                            skip(this.identifierByteSize + byteSize + byteSize);
                                            break;
                                        } else {
                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.ThreadObject(readId(), readInt(), readInt())));
                                            break;
                                        }
                                    default:
                                        switch (unsignedByte2) {
                                            case 32:
                                                j = j5;
                                                i = unsignedByte2;
                                                j2 = unsignedInt;
                                                if (!z22) {
                                                    if (!z23) {
                                                        skipClassDumpRecord();
                                                        break;
                                                    } else {
                                                        listener.onHprofRecord(this.position, readClassSkipContentRecord());
                                                        break;
                                                    }
                                                } else {
                                                    listener.onHprofRecord(this.position, readClassDumpRecord());
                                                    break;
                                                }
                                            case 33:
                                                j = j5;
                                                i = unsignedByte2;
                                                j2 = unsignedInt;
                                                if (!z16) {
                                                    if (!z2) {
                                                        skipInstanceDumpRecord();
                                                        break;
                                                    } else {
                                                        listener.onHprofRecord(this.position, readInstanceSkipContentRecord());
                                                        break;
                                                    }
                                                } else {
                                                    listener.onHprofRecord(this.position, readInstanceDumpRecord());
                                                    break;
                                                }
                                            case 34:
                                                j = j5;
                                                i = unsignedByte2;
                                                j2 = unsignedInt;
                                                if (!z3) {
                                                    if (!z4) {
                                                        skipObjectArrayDumpRecord();
                                                        break;
                                                    } else {
                                                        listener.onHprofRecord(this.position, readObjectArraySkipContentRecord());
                                                        break;
                                                    }
                                                } else {
                                                    listener.onHprofRecord(this.position, readObjectArrayDumpRecord());
                                                    break;
                                                }
                                            case 35:
                                                j = j5;
                                                i = unsignedByte2;
                                                j2 = unsignedInt;
                                                if (!z19) {
                                                    if (!zContains5) {
                                                        skipPrimitiveArrayDumpRecord();
                                                        break;
                                                    } else {
                                                        listener.onHprofRecord(this.position, readPrimitiveArraySkipContentRecord());
                                                        break;
                                                    }
                                                } else {
                                                    listener.onHprofRecord(this.position, readPrimitiveArrayDumpRecord());
                                                    break;
                                                }
                                            default:
                                                switch (unsignedByte2) {
                                                    case 137:
                                                        j = j5;
                                                        i = unsignedByte2;
                                                        j2 = unsignedInt;
                                                        if (!z12) {
                                                            skip(this.identifierByteSize);
                                                            break;
                                                        } else {
                                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.InternedString(readId())));
                                                            break;
                                                        }
                                                    case 138:
                                                        j = j5;
                                                        i = unsignedByte2;
                                                        j2 = unsignedInt;
                                                        if (!z12) {
                                                            skip(this.identifierByteSize);
                                                            break;
                                                        } else {
                                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.Finalizing(readId())));
                                                            break;
                                                        }
                                                    case 139:
                                                        j = j5;
                                                        i = unsignedByte2;
                                                        j2 = unsignedInt;
                                                        if (!z12) {
                                                            skip(this.identifierByteSize);
                                                            break;
                                                        } else {
                                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.Debugger(readId())));
                                                            break;
                                                        }
                                                    case 140:
                                                        j = j5;
                                                        i = unsignedByte2;
                                                        j2 = unsignedInt;
                                                        if (!z12) {
                                                            skip(this.identifierByteSize);
                                                            break;
                                                        } else {
                                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.ReferenceCleanup(readId())));
                                                            break;
                                                        }
                                                    case 141:
                                                        j = j5;
                                                        i = unsignedByte2;
                                                        j2 = unsignedInt;
                                                        if (!z12) {
                                                            skip(this.identifierByteSize);
                                                            break;
                                                        } else {
                                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.VmInternal(readId())));
                                                            break;
                                                        }
                                                    case 142:
                                                        if (!z12) {
                                                            j = j5;
                                                            i = unsignedByte2;
                                                            j2 = unsignedInt;
                                                            skip(this.identifierByteSize + byteSize + byteSize);
                                                            break;
                                                        } else {
                                                            j = j5;
                                                            i = unsignedByte2;
                                                            j2 = unsignedInt;
                                                            listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.JniMonitor(readId(), readInt(), readInt())));
                                                            break;
                                                        }
                                                    default:
                                                        StringBuilder sb = new StringBuilder();
                                                        sb.append("Unknown tag ");
                                                        String str = String.format("0x%02x", Arrays.copyOf(new Object[]{Integer.valueOf(unsignedByte2)}, 1));
                                                        Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(this, *args)");
                                                        sb.append(str);
                                                        sb.append(" at ");
                                                        sb.append(j5);
                                                        sb.append(" after ");
                                                        String str2 = String.format("0x%02x", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
                                                        Intrinsics.checkExpressionValueIsNotNull(str2, "java.lang.String.format(this, *args)");
                                                        sb.append(str2);
                                                        sb.append(" at ");
                                                        sb.append(j4);
                                                        throw new IllegalStateException(sb.toString());
                                                }
                                        }
                                }
                            } else {
                                j = j5;
                                i = unsignedByte2;
                                j2 = unsignedInt;
                                if (z12) {
                                    listener.onHprofRecord(this.position, new HprofRecord.HeapDumpRecord.GcRootRecord(new GcRoot.Unknown(readId())));
                                } else {
                                    skip(this.identifierByteSize);
                                }
                            }
                        }
                        i2 = i;
                        z8 = z5;
                        j3 = j6;
                        j4 = j;
                        unsignedInt = j2;
                    }
                }
            } else {
                if (unsignedByte != 44) {
                    skip(unsignedInt);
                } else if (z8) {
                    listener.onHprofRecord(this.position, HprofRecord.HeapDumpEndRecord.INSTANCE);
                }
                z = z13;
                z2 = zContains3;
                z3 = z17;
                z4 = zContains4;
                z5 = z8;
            }
            z9 = z21;
            z15 = z22;
            zContains2 = z23;
            z10 = z24;
            z17 = z3;
            z13 = z;
            zContains3 = z2;
            zContains4 = z4;
            z8 = z5;
        }
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord readInstanceDumpRecord() {
        return new HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord(readId(), readInt(), readId(), readByteArray(readInt()));
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord readInstanceSkipContentRecord() throws IOException {
        long id = readId();
        int i = readInt();
        long id2 = readId();
        skip(readInt());
        return new HprofRecord.HeapDumpRecord.ObjectRecord.InstanceSkipContentRecord(id, i, id2);
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord readObjectArrayDumpRecord() {
        long id = readId();
        int i = readInt();
        int i2 = readInt();
        return new HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord(id, i, readId(), readIdArray(i2), i2);
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord readObjectArraySkipContentRecord() throws IOException {
        long id = readId();
        int i = readInt();
        int i2 = readInt();
        long id2 = readId();
        skip(this.identifierByteSize * i2);
        return new HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArraySkipContentRecord(id, i, id2, i2);
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord readPrimitiveArrayDumpRecord() {
        long id = readId();
        int i = readInt();
        int i2 = readInt();
        int unsignedByte = readUnsignedByte();
        if (unsignedByte == BOOLEAN_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump(id, i, readBooleanArray(i2));
        }
        if (unsignedByte == CHAR_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump(id, i, readCharArray(i2));
        }
        if (unsignedByte == FLOAT_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump(id, i, readFloatArray(i2));
        }
        if (unsignedByte == DOUBLE_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump(id, i, readDoubleArray(i2));
        }
        if (unsignedByte == BYTE_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump(id, i, readByteArray(i2));
        }
        if (unsignedByte == SHORT_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump(id, i, readShortArray(i2));
        }
        if (unsignedByte == INT_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump(id, i, readIntArray(i2));
        }
        if (unsignedByte == LONG_TYPE) {
            return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump(id, i, readLongArray(i2));
        }
        throw new IllegalStateException("Unexpected type " + unsignedByte);
    }

    public final HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord readPrimitiveArraySkipContentRecord() throws IOException {
        long id = readId();
        int i = readInt();
        int i2 = readInt();
        PrimitiveType primitiveType = (PrimitiveType) MapsKt.getValue(PrimitiveType.INSTANCE.getPrimitiveTypeByHprofType(), Integer.valueOf(readUnsignedByte()));
        skip(primitiveType.getByteSize() * i2);
        return new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArraySkipContentRecord(id, i, i2, primitiveType);
    }

    public final ValueHolder readValue(int type) {
        if (type == 2) {
            return new ValueHolder.ReferenceHolder(readId());
        }
        if (type == BOOLEAN_TYPE) {
            return new ValueHolder.BooleanHolder(readBoolean());
        }
        if (type == CHAR_TYPE) {
            return new ValueHolder.CharHolder(readChar());
        }
        if (type == FLOAT_TYPE) {
            return new ValueHolder.FloatHolder(readFloat());
        }
        if (type == DOUBLE_TYPE) {
            return new ValueHolder.DoubleHolder(readDouble());
        }
        if (type == BYTE_TYPE) {
            return new ValueHolder.ByteHolder(readByte());
        }
        if (type == SHORT_TYPE) {
            return new ValueHolder.ShortHolder(readShort());
        }
        if (type == INT_TYPE) {
            return new ValueHolder.IntHolder(readInt());
        }
        if (type == LONG_TYPE) {
            return new ValueHolder.LongHolder(readLong());
        }
        throw new IllegalStateException("Unknown type " + type);
    }

    public final void setPosition$shark(long j) {
        this.position = j;
    }
}
