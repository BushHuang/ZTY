package kshark;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kshark.GcRoot;
import kshark.Hprof;
import kshark.HprofRecord;
import kshark.ValueHolder;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\u0010\u0019\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 62\u00020\u0001:\u00016B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\f\u0010\u0019\u001a\u00020\u0010*\u00020\u0003H\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001cH\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001dH\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001eH\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001fH\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020 H\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020!H\u0002J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0014\u0010\"\u001a\u00020\u0010*\u00020\u00032\u0006\u0010#\u001a\u00020$H\u0002J\u0014\u0010%\u001a\u00020\u0010*\u00020\u00032\u0006\u0010#\u001a\u00020&H\u0002J\u0014\u0010'\u001a\u00020\u0010*\u00020\u00032\u0006\u0010#\u001a\u00020(H\u0002J\u0014\u0010)\u001a\u00020\u0010*\u00020\u00032\u0006\u0010*\u001a\u00020+H\u0002J\u0014\u0010,\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u001a\u001a\u00020 H\u0002J-\u0010-\u001a\u00020\u0010*\u00020\u00032\u0006\u0010.\u001a\u00020\u00052\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001000¢\u0006\u0002\b1H\u0002J\u001c\u00102\u001a\u00020\u0010*\u00020\u00032\u0006\u0010.\u001a\u00020\u00052\u0006\u00103\u001a\u00020+H\u0002J\u0014\u00104\u001a\u00020\u0010*\u00020\u00032\u0006\u00105\u001a\u00020\u0015H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lkshark/HprofWriter;", "Ljava/io/Closeable;", "sink", "Lokio/BufferedSink;", "identifierByteSize", "", "hprofVersion", "Lkshark/Hprof$HprofVersion;", "(Lokio/BufferedSink;ILkshark/Hprof$HprofVersion;)V", "getHprofVersion", "()Lkshark/Hprof$HprofVersion;", "getIdentifierByteSize", "()I", "workBuffer", "Lokio/Buffer;", "close", "", "valuesToBytes", "", "values", "", "Lkshark/ValueHolder;", "write", "record", "Lkshark/HprofRecord;", "flushHeapBuffer", "array", "", "", "", "", "", "", "", "writeBoolean", "value", "", "writeDouble", "", "writeFloat", "", "writeId", "id", "", "writeIdArray", "writeNonHeapRecord", "tag", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "writeTagHeader", "length", "writeValue", "wrapper", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class HprofWriter implements Closeable {

    public static final Companion INSTANCE = new Companion(null);
    private final Hprof.HprofVersion hprofVersion;
    private final int identifierByteSize;
    private final BufferedSink sink;
    private final Buffer workBuffer;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lkshark/HprofWriter$Companion;", "", "()V", "open", "Lkshark/HprofWriter;", "hprofFile", "Ljava/io/File;", "identifierByteSize", "", "hprofVersion", "Lkshark/Hprof$HprofVersion;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static HprofWriter open$default(Companion companion, File file, int i, Hprof.HprofVersion hprofVersion, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 4;
            }
            if ((i2 & 4) != 0) {
                hprofVersion = Hprof.HprofVersion.ANDROID;
            }
            return companion.open(file, i, hprofVersion);
        }

        public final HprofWriter open(File hprofFile, int identifierByteSize, Hprof.HprofVersion hprofVersion) throws IOException {
            Intrinsics.checkParameterIsNotNull(hprofFile, "hprofFile");
            Intrinsics.checkParameterIsNotNull(hprofVersion, "hprofVersion");
            BufferedSink bufferedSinkBuffer = Okio.buffer(Okio.sink(new FileOutputStream(hprofFile)));
            bufferedSinkBuffer.writeUtf8(hprofVersion.getVersionString());
            bufferedSinkBuffer.writeByte(0);
            bufferedSinkBuffer.writeInt(identifierByteSize);
            bufferedSinkBuffer.writeLong(System.currentTimeMillis());
            Intrinsics.checkExpressionValueIsNotNull(bufferedSinkBuffer, "sink");
            return new HprofWriter(bufferedSinkBuffer, identifierByteSize, hprofVersion, null);
        }
    }

    private HprofWriter(BufferedSink bufferedSink, int i, Hprof.HprofVersion hprofVersion) {
        this.sink = bufferedSink;
        this.identifierByteSize = i;
        this.hprofVersion = hprofVersion;
        this.workBuffer = new Buffer();
    }

    public HprofWriter(BufferedSink bufferedSink, int i, Hprof.HprofVersion hprofVersion, DefaultConstructorMarker defaultConstructorMarker) {
        this(bufferedSink, i, hprofVersion);
    }

    private final void flushHeapBuffer(BufferedSink bufferedSink) throws IOException {
        if (this.workBuffer.size() > 0) {
            writeTagHeader(bufferedSink, 12, this.workBuffer.size());
            bufferedSink.writeAll(this.workBuffer);
            writeTagHeader(bufferedSink, 44, 0L);
        }
    }

    private final void write(BufferedSink bufferedSink, final HprofRecord hprofRecord) throws IOException {
        if (hprofRecord instanceof HprofRecord.StringRecord) {
            writeNonHeapRecord(bufferedSink, 1, new Function1<BufferedSink, Unit>() {
                {
                    super(1);
                }

                @Override
                public Unit invoke(BufferedSink bufferedSink2) throws IOException {
                    invoke2(bufferedSink2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(BufferedSink bufferedSink2) throws IOException {
                    Intrinsics.checkParameterIsNotNull(bufferedSink2, "$receiver");
                    HprofWriter.this.writeId(bufferedSink2, ((HprofRecord.StringRecord) hprofRecord).getId());
                    bufferedSink2.writeUtf8(((HprofRecord.StringRecord) hprofRecord).getString());
                }
            });
            return;
        }
        if (hprofRecord instanceof HprofRecord.LoadClassRecord) {
            writeNonHeapRecord(bufferedSink, 2, new Function1<BufferedSink, Unit>() {
                {
                    super(1);
                }

                @Override
                public Unit invoke(BufferedSink bufferedSink2) throws IOException {
                    invoke2(bufferedSink2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(BufferedSink bufferedSink2) throws IOException {
                    Intrinsics.checkParameterIsNotNull(bufferedSink2, "$receiver");
                    bufferedSink2.writeInt(((HprofRecord.LoadClassRecord) hprofRecord).getClassSerialNumber());
                    HprofWriter.this.writeId(bufferedSink2, ((HprofRecord.LoadClassRecord) hprofRecord).getId());
                    bufferedSink2.writeInt(((HprofRecord.LoadClassRecord) hprofRecord).getStackTraceSerialNumber());
                    HprofWriter.this.writeId(bufferedSink2, ((HprofRecord.LoadClassRecord) hprofRecord).getClassNameStringId());
                }
            });
            return;
        }
        if (hprofRecord instanceof HprofRecord.StackTraceRecord) {
            writeNonHeapRecord(bufferedSink, 5, new Function1<BufferedSink, Unit>() {
                {
                    super(1);
                }

                @Override
                public Unit invoke(BufferedSink bufferedSink2) throws IOException {
                    invoke2(bufferedSink2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(BufferedSink bufferedSink2) throws IOException {
                    Intrinsics.checkParameterIsNotNull(bufferedSink2, "$receiver");
                    bufferedSink2.writeInt(((HprofRecord.StackTraceRecord) hprofRecord).getStackTraceSerialNumber());
                    bufferedSink2.writeInt(((HprofRecord.StackTraceRecord) hprofRecord).getThreadSerialNumber());
                    bufferedSink2.writeInt(((HprofRecord.StackTraceRecord) hprofRecord).getStackFrameIds().length);
                    HprofWriter.this.writeIdArray(bufferedSink2, ((HprofRecord.StackTraceRecord) hprofRecord).getStackFrameIds());
                }
            });
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.GcRootRecord) {
            Buffer buffer = this.workBuffer;
            GcRoot gcRoot = ((HprofRecord.HeapDumpRecord.GcRootRecord) hprofRecord).getGcRoot();
            if (gcRoot instanceof GcRoot.Unknown) {
                buffer.writeByte(255);
                writeId(buffer, gcRoot.getId());
                return;
            }
            if (gcRoot instanceof GcRoot.JniGlobal) {
                buffer.writeByte(1);
                Buffer buffer2 = buffer;
                writeId(buffer2, gcRoot.getId());
                writeId(buffer2, ((GcRoot.JniGlobal) gcRoot).getJniGlobalRefId());
                return;
            }
            if (gcRoot instanceof GcRoot.JniLocal) {
                buffer.writeByte(2);
                writeId(buffer, gcRoot.getId());
                GcRoot.JniLocal jniLocal = (GcRoot.JniLocal) gcRoot;
                buffer.writeInt(jniLocal.getThreadSerialNumber());
                buffer.writeInt(jniLocal.getFrameNumber());
                return;
            }
            if (gcRoot instanceof GcRoot.JavaFrame) {
                buffer.writeByte(3);
                writeId(buffer, gcRoot.getId());
                GcRoot.JavaFrame javaFrame = (GcRoot.JavaFrame) gcRoot;
                buffer.writeInt(javaFrame.getThreadSerialNumber());
                buffer.writeInt(javaFrame.getFrameNumber());
                return;
            }
            if (gcRoot instanceof GcRoot.NativeStack) {
                buffer.writeByte(4);
                writeId(buffer, gcRoot.getId());
                buffer.writeInt(((GcRoot.NativeStack) gcRoot).getThreadSerialNumber());
                return;
            }
            if (gcRoot instanceof GcRoot.StickyClass) {
                buffer.writeByte(5);
                writeId(buffer, gcRoot.getId());
                return;
            }
            if (gcRoot instanceof GcRoot.ThreadBlock) {
                buffer.writeByte(6);
                writeId(buffer, gcRoot.getId());
                buffer.writeInt(((GcRoot.ThreadBlock) gcRoot).getThreadSerialNumber());
                return;
            }
            if (gcRoot instanceof GcRoot.MonitorUsed) {
                buffer.writeByte(7);
                writeId(buffer, gcRoot.getId());
                return;
            }
            if (gcRoot instanceof GcRoot.ThreadObject) {
                buffer.writeByte(8);
                writeId(buffer, gcRoot.getId());
                GcRoot.ThreadObject threadObject = (GcRoot.ThreadObject) gcRoot;
                buffer.writeInt(threadObject.getThreadSerialNumber());
                buffer.writeInt(threadObject.getStackTraceSerialNumber());
                return;
            }
            if (gcRoot instanceof GcRoot.ReferenceCleanup) {
                buffer.writeByte(140);
                writeId(buffer, gcRoot.getId());
                return;
            }
            if (gcRoot instanceof GcRoot.VmInternal) {
                buffer.writeByte(141);
                writeId(buffer, gcRoot.getId());
                return;
            }
            if (gcRoot instanceof GcRoot.JniMonitor) {
                buffer.writeByte(142);
                writeId(buffer, gcRoot.getId());
                GcRoot.JniMonitor jniMonitor = (GcRoot.JniMonitor) gcRoot;
                buffer.writeInt(jniMonitor.getStackTraceSerialNumber());
                buffer.writeInt(jniMonitor.getStackDepth());
                return;
            }
            if (gcRoot instanceof GcRoot.InternedString) {
                buffer.writeByte(137);
                writeId(buffer, gcRoot.getId());
                return;
            }
            if (gcRoot instanceof GcRoot.Finalizing) {
                buffer.writeByte(138);
                writeId(buffer, gcRoot.getId());
                return;
            } else if (gcRoot instanceof GcRoot.Debugger) {
                buffer.writeByte(139);
                writeId(buffer, gcRoot.getId());
                return;
            } else {
                if (!(gcRoot instanceof GcRoot.Unreachable)) {
                    throw new NoWhenBranchMatchedException();
                }
                buffer.writeByte(144);
                writeId(buffer, gcRoot.getId());
                return;
            }
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord) {
            Buffer buffer3 = this.workBuffer;
            buffer3.writeByte(32);
            Buffer buffer4 = buffer3;
            HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord classDumpRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord) hprofRecord;
            writeId(buffer4, classDumpRecord.getId());
            buffer3.writeInt(classDumpRecord.getStackTraceSerialNumber());
            writeId(buffer4, classDumpRecord.getSuperclassId());
            writeId(buffer4, classDumpRecord.getClassLoaderId());
            writeId(buffer4, classDumpRecord.getSignersId());
            writeId(buffer4, classDumpRecord.getProtectionDomainId());
            writeId(buffer4, 0L);
            writeId(buffer4, 0L);
            buffer3.writeInt(classDumpRecord.getInstanceSize());
            buffer3.writeShort(0);
            buffer3.writeShort(classDumpRecord.getStaticFields().size());
            for (HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.StaticFieldRecord staticFieldRecord : classDumpRecord.getStaticFields()) {
                writeId(buffer4, staticFieldRecord.getNameStringId());
                buffer3.writeByte(staticFieldRecord.getType());
                writeValue(buffer4, staticFieldRecord.getValue());
            }
            buffer3.writeShort(classDumpRecord.getFields().size());
            for (HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.FieldRecord fieldRecord : classDumpRecord.getFields()) {
                writeId(buffer4, fieldRecord.getNameStringId());
                buffer3.writeByte(fieldRecord.getType());
            }
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord) {
            Buffer buffer5 = this.workBuffer;
            buffer5.writeByte(33);
            Buffer buffer6 = buffer5;
            HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord instanceDumpRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord) hprofRecord;
            writeId(buffer6, instanceDumpRecord.getId());
            buffer5.writeInt(instanceDumpRecord.getStackTraceSerialNumber());
            writeId(buffer6, instanceDumpRecord.getClassId());
            buffer5.writeInt(instanceDumpRecord.getFieldValues().length);
            buffer5.write(instanceDumpRecord.getFieldValues());
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord) {
            Buffer buffer7 = this.workBuffer;
            buffer7.writeByte(34);
            Buffer buffer8 = buffer7;
            HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord objectArrayDumpRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.ObjectArrayDumpRecord) hprofRecord;
            writeId(buffer8, objectArrayDumpRecord.getId());
            buffer7.writeInt(objectArrayDumpRecord.getStackTraceSerialNumber());
            buffer7.writeInt(objectArrayDumpRecord.getElementIds().length);
            writeId(buffer8, objectArrayDumpRecord.getArrayClassId());
            writeIdArray(buffer8, objectArrayDumpRecord.getElementIds());
            return;
        }
        if (!(hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord)) {
            if (!(hprofRecord instanceof HprofRecord.HeapDumpRecord.HeapDumpInfoRecord)) {
                if (hprofRecord instanceof HprofRecord.HeapDumpEndRecord) {
                    throw new IllegalArgumentException("HprofWriter automatically emits HeapDumpEndRecord");
                }
                return;
            }
            Buffer buffer9 = this.workBuffer;
            buffer9.writeByte(254);
            HprofRecord.HeapDumpRecord.HeapDumpInfoRecord heapDumpInfoRecord = (HprofRecord.HeapDumpRecord.HeapDumpInfoRecord) hprofRecord;
            buffer9.writeInt(heapDumpInfoRecord.getHeapId());
            writeId(buffer9, heapDumpInfoRecord.getHeapNameStringId());
            return;
        }
        Buffer buffer10 = this.workBuffer;
        buffer10.writeByte(35);
        Buffer buffer11 = buffer10;
        HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord primitiveArrayDumpRecord = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord) hprofRecord;
        writeId(buffer11, primitiveArrayDumpRecord.getId());
        buffer10.writeInt(primitiveArrayDumpRecord.getStackTraceSerialNumber());
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump) {
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump booleanArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump) hprofRecord;
            buffer10.writeInt(booleanArrayDump.getArray().length);
            buffer10.writeByte(PrimitiveType.BOOLEAN.getHprofType());
            write(buffer11, booleanArrayDump.getArray());
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) {
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump charArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) hprofRecord;
            buffer10.writeInt(charArrayDump.getArray().length);
            buffer10.writeByte(PrimitiveType.CHAR.getHprofType());
            write((BufferedSink) buffer11, charArrayDump.getArray());
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump) {
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump floatArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump) hprofRecord;
            buffer10.writeInt(floatArrayDump.getArray().length);
            buffer10.writeByte(PrimitiveType.FLOAT.getHprofType());
            write((BufferedSink) buffer11, floatArrayDump.getArray());
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump) {
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump doubleArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump) hprofRecord;
            buffer10.writeInt(doubleArrayDump.getArray().length);
            buffer10.writeByte(PrimitiveType.DOUBLE.getHprofType());
            write(buffer11, doubleArrayDump.getArray());
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) {
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump byteArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) hprofRecord;
            buffer10.writeInt(byteArrayDump.getArray().length);
            buffer10.writeByte(PrimitiveType.BYTE.getHprofType());
            buffer10.write(byteArrayDump.getArray());
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump) {
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump shortArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump) hprofRecord;
            buffer10.writeInt(shortArrayDump.getArray().length);
            buffer10.writeByte(PrimitiveType.SHORT.getHprofType());
            write((BufferedSink) buffer11, shortArrayDump.getArray());
            return;
        }
        if (hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump) {
            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump intArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump) hprofRecord;
            buffer10.writeInt(intArrayDump.getArray().length);
            buffer10.writeByte(PrimitiveType.INT.getHprofType());
            write((BufferedSink) buffer11, intArrayDump.getArray());
            return;
        }
        if (!(hprofRecord instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump)) {
            throw new NoWhenBranchMatchedException();
        }
        HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump longArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump) hprofRecord;
        buffer10.writeInt(longArrayDump.getArray().length);
        buffer10.writeByte(PrimitiveType.LONG.getHprofType());
        write((BufferedSink) buffer11, longArrayDump.getArray());
    }

    private final void write(BufferedSink bufferedSink, char[] cArr) throws IOException {
        bufferedSink.writeString(new String(cArr), Charsets.UTF_16BE);
    }

    private final void write(BufferedSink bufferedSink, double[] dArr) throws IOException {
        for (double d : dArr) {
            writeDouble(bufferedSink, d);
        }
    }

    private final void write(BufferedSink bufferedSink, float[] fArr) throws IOException {
        for (float f : fArr) {
            writeFloat(bufferedSink, f);
        }
    }

    private final void write(BufferedSink bufferedSink, int[] iArr) throws IOException {
        for (int i : iArr) {
            bufferedSink.writeInt(i);
        }
    }

    private final void write(BufferedSink bufferedSink, long[] jArr) throws IOException {
        for (long j : jArr) {
            bufferedSink.writeLong(j);
        }
    }

    private final void write(BufferedSink bufferedSink, short[] sArr) throws IOException {
        for (short s : sArr) {
            bufferedSink.writeShort(s);
        }
    }

    private final void write(BufferedSink bufferedSink, boolean[] zArr) throws IOException {
        for (boolean z : zArr) {
            bufferedSink.writeByte(z ? 1 : 0);
        }
    }

    private final void writeBoolean(BufferedSink bufferedSink, boolean z) throws IOException {
        bufferedSink.writeByte(z ? 1 : 0);
    }

    private final void writeDouble(BufferedSink bufferedSink, double d) throws IOException {
        bufferedSink.writeLong(Double.doubleToLongBits(d));
    }

    private final void writeFloat(BufferedSink bufferedSink, float f) throws IOException {
        bufferedSink.writeInt(Float.floatToIntBits(f));
    }

    private final void writeId(BufferedSink bufferedSink, long j) throws IOException {
        int i = this.identifierByteSize;
        if (i == 1) {
            bufferedSink.writeByte((int) j);
            return;
        }
        if (i == 2) {
            bufferedSink.writeShort((int) j);
        } else if (i == 4) {
            bufferedSink.writeInt((int) j);
        } else {
            if (i != 8) {
                throw new IllegalArgumentException("ID Length must be 1, 2, 4, or 8");
            }
            bufferedSink.writeLong(j);
        }
    }

    private final void writeIdArray(BufferedSink bufferedSink, long[] jArr) throws IOException {
        for (long j : jArr) {
            writeId(bufferedSink, j);
        }
    }

    private final void writeNonHeapRecord(BufferedSink bufferedSink, int i, Function1<? super BufferedSink, Unit> function1) throws IOException {
        flushHeapBuffer(bufferedSink);
        function1.invoke(this.workBuffer);
        writeTagHeader(bufferedSink, i, this.workBuffer.size());
        bufferedSink.writeAll(this.workBuffer);
    }

    private final void writeTagHeader(BufferedSink bufferedSink, int i, long j) throws IOException {
        bufferedSink.writeByte(i);
        bufferedSink.writeInt(0);
        bufferedSink.writeInt((int) j);
    }

    private final void writeValue(BufferedSink bufferedSink, ValueHolder valueHolder) throws IOException {
        if (valueHolder instanceof ValueHolder.ReferenceHolder) {
            writeId(bufferedSink, ((ValueHolder.ReferenceHolder) valueHolder).getValue());
            return;
        }
        if (valueHolder instanceof ValueHolder.BooleanHolder) {
            writeBoolean(bufferedSink, ((ValueHolder.BooleanHolder) valueHolder).getValue());
            return;
        }
        if (valueHolder instanceof ValueHolder.CharHolder) {
            write(bufferedSink, new char[]{((ValueHolder.CharHolder) valueHolder).getValue()});
            return;
        }
        if (valueHolder instanceof ValueHolder.FloatHolder) {
            writeFloat(bufferedSink, ((ValueHolder.FloatHolder) valueHolder).getValue());
            return;
        }
        if (valueHolder instanceof ValueHolder.DoubleHolder) {
            writeDouble(bufferedSink, ((ValueHolder.DoubleHolder) valueHolder).getValue());
            return;
        }
        if (valueHolder instanceof ValueHolder.ByteHolder) {
            bufferedSink.writeByte(((ValueHolder.ByteHolder) valueHolder).getValue());
            return;
        }
        if (valueHolder instanceof ValueHolder.ShortHolder) {
            bufferedSink.writeShort(((ValueHolder.ShortHolder) valueHolder).getValue());
        } else if (valueHolder instanceof ValueHolder.IntHolder) {
            bufferedSink.writeInt(((ValueHolder.IntHolder) valueHolder).getValue());
        } else if (valueHolder instanceof ValueHolder.LongHolder) {
            bufferedSink.writeLong(((ValueHolder.LongHolder) valueHolder).getValue());
        }
    }

    @Override
    public void close() throws IOException {
        flushHeapBuffer(this.sink);
        this.sink.close();
    }

    public final Hprof.HprofVersion getHprofVersion() {
        return this.hprofVersion;
    }

    public final int getIdentifierByteSize() {
        return this.identifierByteSize;
    }

    public final byte[] valuesToBytes(List<? extends ValueHolder> values) throws IOException {
        Intrinsics.checkParameterIsNotNull(values, "values");
        Buffer buffer = new Buffer();
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            writeValue(buffer, (ValueHolder) it.next());
        }
        byte[] byteArray = buffer.readByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "valuesBuffer.readByteArray()");
        return byteArray;
    }

    public final void write(HprofRecord record) throws IOException {
        Intrinsics.checkParameterIsNotNull(record, "record");
        write(this.sink, record);
    }
}
