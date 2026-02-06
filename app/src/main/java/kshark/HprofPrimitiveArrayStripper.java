package kshark;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kshark.HprofRecord;
import kshark.OnHprofRecordListener;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"Lkshark/HprofPrimitiveArrayStripper;", "", "()V", "stripPrimitiveArrays", "Ljava/io/File;", "inputHprofFile", "outputHprofFile", "shark"}, k = 1, mv = {1, 1, 15})
public final class HprofPrimitiveArrayStripper {
    public static File stripPrimitiveArrays$default(HprofPrimitiveArrayStripper hprofPrimitiveArrayStripper, File file, File file2, int i, Object obj) {
        if ((i & 2) != 0) {
            String parent = file.getParent();
            String name = file.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "inputHprofFile.name");
            String strReplace$default = StringsKt.replace$default(name, ".hprof", "-stripped.hprof", false, 4, (Object) null);
            if (!(!Intrinsics.areEqual(strReplace$default, file.getName()))) {
                strReplace$default = file.getName() + "-stripped";
            }
            file2 = new File(parent, strReplace$default);
        }
        return hprofPrimitiveArrayStripper.stripPrimitiveArrays(file, file2);
    }

    public final File stripPrimitiveArrays(File inputHprofFile, File outputHprofFile) throws IOException {
        Intrinsics.checkParameterIsNotNull(inputHprofFile, "inputHprofFile");
        Intrinsics.checkParameterIsNotNull(outputHprofFile, "outputHprofFile");
        HprofWriter hprofWriterOpen = Hprof.INSTANCE.open(inputHprofFile);
        Throwable th = (Throwable) null;
        try {
            Hprof hprof = hprofWriterOpen;
            HprofReader reader = hprof.getReader();
            hprofWriterOpen = HprofWriter.INSTANCE.open(outputHprofFile, reader.getIdentifierByteSize(), hprof.getHprofVersion());
            Throwable th2 = (Throwable) null;
            try {
                final HprofWriter hprofWriter = hprofWriterOpen;
                Set<? extends KClass<? extends HprofRecord>> of = SetsKt.setOf(Reflection.getOrCreateKotlinClass(HprofRecord.class));
                OnHprofRecordListener.Companion companion = OnHprofRecordListener.INSTANCE;
                reader.readHprofRecords(of, new OnHprofRecordListener() {
                    @Override
                    public void onHprofRecord(long position, HprofRecord record) throws IOException {
                        Intrinsics.checkParameterIsNotNull(record, "record");
                        if (record instanceof HprofRecord.HeapDumpEndRecord) {
                            return;
                        }
                        HprofWriter hprofWriter2 = hprofWriter;
                        if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump booleanArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump) record;
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.BooleanArrayDump(booleanArrayDump.getId(), booleanArrayDump.getStackTraceSerialNumber(), new boolean[booleanArrayDump.getArray().length]);
                        } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump charArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump) record;
                            long id = charArrayDump.getId();
                            int stackTraceSerialNumber = charArrayDump.getStackTraceSerialNumber();
                            int length = charArrayDump.getArray().length;
                            char[] cArr = new char[length];
                            for (int i = 0; i < length; i++) {
                                cArr[i] = '?';
                            }
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.CharArrayDump(id, stackTraceSerialNumber, cArr);
                        } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump floatArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump) record;
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.FloatArrayDump(floatArrayDump.getId(), floatArrayDump.getStackTraceSerialNumber(), new float[floatArrayDump.getArray().length]);
                        } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump doubleArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump) record;
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.DoubleArrayDump(doubleArrayDump.getId(), doubleArrayDump.getStackTraceSerialNumber(), new double[doubleArrayDump.getArray().length]);
                        } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump byteArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump) record;
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ByteArrayDump(byteArrayDump.getId(), byteArrayDump.getStackTraceSerialNumber(), new byte[byteArrayDump.getArray().length]);
                        } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump shortArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump) record;
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.ShortArrayDump(shortArrayDump.getId(), shortArrayDump.getStackTraceSerialNumber(), new short[shortArrayDump.getArray().length]);
                        } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump intArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump) record;
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.IntArrayDump(intArrayDump.getId(), intArrayDump.getStackTraceSerialNumber(), new int[intArrayDump.getArray().length]);
                        } else if (record instanceof HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump) {
                            HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump longArrayDump = (HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump) record;
                            record = new HprofRecord.HeapDumpRecord.ObjectRecord.PrimitiveArrayDumpRecord.LongArrayDump(longArrayDump.getId(), longArrayDump.getStackTraceSerialNumber(), new long[longArrayDump.getArray().length]);
                        }
                        hprofWriter2.write(record);
                    }
                });
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(hprofWriterOpen, th2);
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(hprofWriterOpen, th);
                return outputHprofFile;
            } finally {
            }
        } finally {
        }
    }
}
