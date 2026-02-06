package kshark.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kshark.HprofRecord;
import kshark.PrimitiveType;
import kshark.ValueHolder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0005H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lkshark/internal/FieldValuesReader;", "", "record", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$InstanceDumpRecord;", "identifierByteSize", "", "(Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$InstanceDumpRecord;I)V", "position", "readBoolean", "", "readByte", "", "readChar", "readDouble", "readFloat", "readId", "", "readInt", "readLong", "readShort", "readValue", "Lkshark/ValueHolder;", "field", "Lkshark/HprofRecord$HeapDumpRecord$ObjectRecord$ClassDumpRecord$FieldRecord;", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class FieldValuesReader {
    private final int identifierByteSize;
    private int position;
    private final HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord record;
    private static final int BOOLEAN_TYPE = PrimitiveType.BOOLEAN.getHprofType();
    private static final int CHAR_TYPE = PrimitiveType.CHAR.getHprofType();
    private static final int FLOAT_TYPE = PrimitiveType.FLOAT.getHprofType();
    private static final int DOUBLE_TYPE = PrimitiveType.DOUBLE.getHprofType();
    private static final int BYTE_TYPE = PrimitiveType.BYTE.getHprofType();
    private static final int SHORT_TYPE = PrimitiveType.SHORT.getHprofType();
    private static final int INT_TYPE = PrimitiveType.INT.getHprofType();
    private static final int LONG_TYPE = PrimitiveType.LONG.getHprofType();

    public FieldValuesReader(HprofRecord.HeapDumpRecord.ObjectRecord.InstanceDumpRecord instanceDumpRecord, int i) {
        Intrinsics.checkParameterIsNotNull(instanceDumpRecord, "record");
        this.record = instanceDumpRecord;
        this.identifierByteSize = i;
    }

    private final boolean readBoolean() {
        byte[] fieldValues = this.record.getFieldValues();
        int i = this.position;
        byte b = fieldValues[i];
        this.position = i + 1;
        return b != ((byte) 0);
    }

    private final void readByte() {
        this.position++;
    }

    private final void readChar() {
        this.position += 2;
    }

    private final void readDouble() {
        this.position += 8;
    }

    private final void readFloat() {
        this.position += 4;
    }

    private final long readId() {
        int i = this.identifierByteSize;
        if (i == 4) {
            return readInt();
        }
        if (i == 8) {
            return readLong();
        }
        throw new IllegalArgumentException("ID Length must be 1, 2, 4, or 8");
    }

    private final int readInt() {
        int i = ByteSubArrayKt.readInt(this.record.getFieldValues(), this.position);
        this.position += 4;
        return i;
    }

    private final long readLong() {
        long j = ByteSubArrayKt.readLong(this.record.getFieldValues(), this.position);
        this.position += 8;
        return j;
    }

    private final void readShort() {
        this.position += 2;
    }

    public final ValueHolder readValue(HprofRecord.HeapDumpRecord.ObjectRecord.ClassDumpRecord.FieldRecord field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        int type = field.getType();
        if (type == 2) {
            return new ValueHolder.ReferenceHolder(readId());
        }
        if (type == BOOLEAN_TYPE) {
            return new ValueHolder.BooleanHolder(readBoolean());
        }
        if (type == CHAR_TYPE) {
            readChar();
            return null;
        }
        if (type == FLOAT_TYPE) {
            readFloat();
            return null;
        }
        if (type == DOUBLE_TYPE) {
            readDouble();
            return null;
        }
        if (type == BYTE_TYPE) {
            readByte();
            return null;
        }
        if (type == SHORT_TYPE) {
            readShort();
            return null;
        }
        if (type == INT_TYPE) {
            return new ValueHolder.IntHolder(readInt());
        }
        if (type == LONG_TYPE) {
            return new ValueHolder.LongHolder(readLong());
        }
        throw new IllegalStateException("Unknown type " + field.getType());
    }
}
