package com.tencent.tinker.android.dex.io;

import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.AnnotationSetRefList;
import com.tencent.tinker.android.dex.AnnotationsDirectory;
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.android.dex.EncodedValueReader;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.Leb128;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.Mutf8;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dex.SizeOf;
import com.tencent.tinker.android.dex.StringData;
import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.util.ByteInput;
import com.tencent.tinker.android.dex.util.ByteOutput;
import java.io.ByteArrayOutputStream;
import java.io.UTFDataFormatException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DexDataBuffer implements ByteInput, ByteOutput {
    public static final int DEFAULT_BUFFER_SIZE = 512;
    private ByteBuffer data;
    private int dataBound;
    private boolean isResizeAllowed;
    private static final short[] EMPTY_SHORT_ARRAY = new short[0];
    private static final Code.Try[] EMPTY_TRY_ARRAY = new Code.Try[0];
    private static final Code.CatchHandler[] EMPTY_CATCHHANDLER_ARRAY = new Code.CatchHandler[0];

    public DexDataBuffer() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(512);
        this.data = byteBufferAllocate;
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = this.data.position();
        ByteBuffer byteBuffer = this.data;
        byteBuffer.limit(byteBuffer.capacity());
        this.isResizeAllowed = true;
    }

    public DexDataBuffer(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = byteBuffer.limit();
        this.isResizeAllowed = false;
    }

    public DexDataBuffer(ByteBuffer byteBuffer, boolean z) {
        this.data = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = byteBuffer.limit();
        this.isResizeAllowed = z;
    }

    private void ensureBufferSize(int i) {
        if (this.data.position() + i <= this.data.limit() || !this.isResizeAllowed) {
            return;
        }
        byte[] bArrArray = this.data.array();
        byte[] bArr = new byte[bArrArray.length + i + (bArrArray.length >> 1)];
        System.arraycopy(bArrArray, 0, bArr, 0, this.data.position());
        int iPosition = this.data.position();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.data = byteBufferWrap;
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        this.data.position(iPosition);
        ByteBuffer byteBuffer = this.data;
        byteBuffer.limit(byteBuffer.capacity());
    }

    private int findCatchHandlerIndex(Code.CatchHandler[] catchHandlerArr, int i) {
        for (int i2 = 0; i2 < catchHandlerArr.length; i2++) {
            if (catchHandlerArr[i2].offset == i) {
                return i2;
            }
        }
        throw new IllegalArgumentException();
    }

    private byte[] getBytesFrom(int i) {
        byte[] bArr = new byte[this.data.position() - i];
        this.data.position(i);
        this.data.get(bArr);
        return bArr;
    }

    private Code.CatchHandler readCatchHandler(int i) {
        int sleb128 = readSleb128();
        int iAbs = Math.abs(sleb128);
        int[] iArr = new int[iAbs];
        int[] iArr2 = new int[iAbs];
        for (int i2 = 0; i2 < iAbs; i2++) {
            iArr[i2] = readUleb128();
            iArr2[i2] = readUleb128();
        }
        return new Code.CatchHandler(iArr, iArr2, sleb128 <= 0 ? readUleb128() : -1, i);
    }

    private Code.CatchHandler[] readCatchHandlers() {
        int iPosition = this.data.position();
        int uleb128 = readUleb128();
        Code.CatchHandler[] catchHandlerArr = new Code.CatchHandler[uleb128];
        for (int i = 0; i < uleb128; i++) {
            catchHandlerArr[i] = readCatchHandler(this.data.position() - iPosition);
        }
        return catchHandlerArr;
    }

    private ClassData.Field[] readFields(int i) {
        ClassData.Field[] fieldArr = new ClassData.Field[i];
        int uleb128 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            uleb128 += readUleb128();
            fieldArr[i2] = new ClassData.Field(uleb128, readUleb128());
        }
        return fieldArr;
    }

    private ClassData.Method[] readMethods(int i) {
        ClassData.Method[] methodArr = new ClassData.Method[i];
        int uleb128 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            uleb128 += readUleb128();
            methodArr[i2] = new ClassData.Method(uleb128, readUleb128(), readUleb128());
        }
        return methodArr;
    }

    private Code.Try[] readTries(int i, Code.CatchHandler[] catchHandlerArr) {
        Code.Try[] tryArr = new Code.Try[i];
        for (int i2 = 0; i2 < i; i2++) {
            tryArr[i2] = new Code.Try(readInt(), readUnsignedShort(), findCatchHandlerIndex(catchHandlerArr, readUnsignedShort()));
        }
        return tryArr;
    }

    private void writeCatchHandler(Code.CatchHandler catchHandler) {
        int i = catchHandler.catchAllAddress;
        int[] iArr = catchHandler.typeIndexes;
        int[] iArr2 = catchHandler.addresses;
        if (i != -1) {
            writeSleb128(-iArr.length);
        } else {
            writeSleb128(iArr.length);
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            writeUleb128(iArr[i2]);
            writeUleb128(iArr2[i2]);
        }
        if (i != -1) {
            writeUleb128(i);
        }
    }

    private int[] writeCatchHandlers(Code.CatchHandler[] catchHandlerArr) {
        int iPosition = this.data.position();
        writeUleb128(catchHandlerArr.length);
        int[] iArr = new int[catchHandlerArr.length];
        for (int i = 0; i < catchHandlerArr.length; i++) {
            iArr[i] = this.data.position() - iPosition;
            writeCatchHandler(catchHandlerArr[i]);
        }
        return iArr;
    }

    private void writeFields(ClassData.Field[] fieldArr) {
        int i = 0;
        for (ClassData.Field field : fieldArr) {
            writeUleb128(field.fieldIndex - i);
            i = field.fieldIndex;
            writeUleb128(field.accessFlags);
        }
    }

    private void writeMethods(ClassData.Method[] methodArr) {
        int i = 0;
        for (ClassData.Method method : methodArr) {
            writeUleb128(method.methodIndex - i);
            i = method.methodIndex;
            writeUleb128(method.accessFlags);
            writeUleb128(method.codeOffset);
        }
    }

    private void writeTries(Code.Try[] tryArr, int[] iArr) {
        for (Code.Try r2 : tryArr) {
            writeInt(r2.startAddress);
            writeUnsignedShort(r2.instructionCount);
            writeUnsignedShort(iArr[r2.catchHandlerIndex]);
        }
    }

    public void alignToFourBytes() {
        ByteBuffer byteBuffer = this.data;
        byteBuffer.position((byteBuffer.position() + 3) & (-4));
    }

    public void alignToFourBytesWithZeroFill() {
        ensureBufferSize((SizeOf.roundToTimesOfFour(this.data.position()) - this.data.position()) * 1);
        while ((this.data.position() & 3) != 0) {
            this.data.put((byte) 0);
        }
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public byte[] array() {
        byte[] bArr = new byte[this.dataBound];
        System.arraycopy(this.data.array(), 0, bArr, 0, this.dataBound);
        return bArr;
    }

    public int available() {
        return this.dataBound - this.data.position();
    }

    public int position() {
        return this.data.position();
    }

    public void position(int i) {
        this.data.position(i);
    }

    public Annotation readAnnotation() {
        int iPosition = this.data.position();
        byte b = readByte();
        int iPosition2 = this.data.position();
        new EncodedValueReader(this, 29).skipValue();
        return new Annotation(iPosition, b, new EncodedValue(iPosition2, getBytesFrom(iPosition2)));
    }

    public AnnotationSet readAnnotationSet() {
        int iPosition = this.data.position();
        int i = readInt();
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readInt();
        }
        return new AnnotationSet(iPosition, iArr);
    }

    public AnnotationSetRefList readAnnotationSetRefList() {
        int iPosition = this.data.position();
        int i = readInt();
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readInt();
        }
        return new AnnotationSetRefList(iPosition, iArr);
    }

    public AnnotationsDirectory readAnnotationsDirectory() {
        int iPosition = this.data.position();
        int i = readInt();
        int i2 = readInt();
        int i3 = readInt();
        int i4 = readInt();
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, i2, 2);
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5][0] = readInt();
            iArr[i5][1] = readInt();
        }
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) int.class, i3, 2);
        for (int i6 = 0; i6 < i3; i6++) {
            iArr2[i6][0] = readInt();
            iArr2[i6][1] = readInt();
        }
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) int.class, i4, 2);
        for (int i7 = 0; i7 < i4; i7++) {
            iArr3[i7][0] = readInt();
            iArr3[i7][1] = readInt();
        }
        return new AnnotationsDirectory(iPosition, i, iArr, iArr2, iArr3);
    }

    @Override
    public byte readByte() {
        return this.data.get();
    }

    public byte[] readByteArray(int i) {
        byte[] bArr = new byte[i];
        this.data.get(bArr);
        return bArr;
    }

    public ClassData readClassData() {
        return new ClassData(this.data.position(), readFields(readUleb128()), readFields(readUleb128()), readMethods(readUleb128()), readMethods(readUleb128()));
    }

    public ClassDef readClassDef() {
        return new ClassDef(position(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt());
    }

    public Code readCode() {
        Code.Try[] tryArr;
        Code.CatchHandler[] catchHandlerArr;
        int iPosition = this.data.position();
        int unsignedShort = readUnsignedShort();
        int unsignedShort2 = readUnsignedShort();
        int unsignedShort3 = readUnsignedShort();
        int unsignedShort4 = readUnsignedShort();
        int i = readInt();
        short[] shortArray = readShortArray(readInt());
        if (unsignedShort4 > 0) {
            if ((shortArray.length & 1) == 1) {
                skip(2);
            }
            int iPosition2 = this.data.position();
            skip(unsignedShort4 * 8);
            Code.CatchHandler[] catchHandlers = readCatchHandlers();
            int iPosition3 = this.data.position();
            this.data.position(iPosition2);
            Code.Try[] tries = readTries(unsignedShort4, catchHandlers);
            this.data.position(iPosition3);
            catchHandlerArr = catchHandlers;
            tryArr = tries;
        } else {
            tryArr = EMPTY_TRY_ARRAY;
            catchHandlerArr = EMPTY_CATCHHANDLER_ARRAY;
        }
        return new Code(iPosition, unsignedShort, unsignedShort2, unsignedShort3, i, shortArray, tryArr, catchHandlerArr);
    }

    public DebugInfoItem readDebugInfoItem() throws Throwable {
        final ByteArrayOutputStream byteArrayOutputStream;
        ByteOutput byteOutput;
        int iPosition = this.data.position();
        int uleb128 = readUleb128();
        int uleb1282 = readUleb128();
        int[] iArr = new int[uleb1282];
        for (int i = 0; i < uleb1282; i++) {
            iArr[i] = readUleb128p1();
        }
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(64);
            try {
                byteOutput = new ByteOutput() {
                    @Override
                    public void writeByte(int i2) {
                        byteArrayOutputStream.write(i2);
                    }
                };
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream2 = byteArrayOutputStream;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        while (true) {
            byte b = readByte();
            byteArrayOutputStream.write(b);
            if (b != 9) {
                switch (b) {
                    case 0:
                        DebugInfoItem debugInfoItem = new DebugInfoItem(iPosition, uleb128, iArr, byteArrayOutputStream.toByteArray());
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused) {
                        }
                        return debugInfoItem;
                    case 1:
                        Leb128.writeUnsignedLeb128(byteOutput, readUleb128());
                        continue;
                    case 2:
                        Leb128.writeSignedLeb128(byteOutput, readSleb128());
                        continue;
                    case 3:
                    case 4:
                        Leb128.writeUnsignedLeb128(byteOutput, readUleb128());
                        Leb128.writeUnsignedLeb128p1(byteOutput, readUleb128p1());
                        Leb128.writeUnsignedLeb128p1(byteOutput, readUleb128p1());
                        if (b == 4) {
                            Leb128.writeUnsignedLeb128p1(byteOutput, readUleb128p1());
                            break;
                        } else {
                            continue;
                        }
                    case 5:
                    case 6:
                        Leb128.writeUnsignedLeb128(byteOutput, readUleb128());
                        continue;
                    default:
                        continue;
                }
                th = th;
                byteArrayOutputStream2 = byteArrayOutputStream;
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
            Leb128.writeUnsignedLeb128p1(byteOutput, readUleb128p1());
        }
    }

    public EncodedValue readEncodedArray() {
        int iPosition = this.data.position();
        new EncodedValueReader(this, 28).skipValue();
        return new EncodedValue(iPosition, getBytesFrom(iPosition));
    }

    public FieldId readFieldId() {
        return new FieldId(this.data.position(), readUnsignedShort(), readUnsignedShort(), readInt());
    }

    public int readInt() {
        return this.data.getInt();
    }

    public MethodId readMethodId() {
        return new MethodId(this.data.position(), readUnsignedShort(), readUnsignedShort(), readInt());
    }

    public ProtoId readProtoId() {
        return new ProtoId(this.data.position(), readInt(), readInt(), readInt());
    }

    public short readShort() {
        return this.data.getShort();
    }

    public short[] readShortArray(int i) {
        if (i == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readShort();
        }
        return sArr;
    }

    public int readSleb128() {
        return Leb128.readSignedLeb128(this);
    }

    public StringData readStringData() {
        int iPosition = this.data.position();
        try {
            int uleb128 = readUleb128();
            String strDecode = Mutf8.decode(this, new char[uleb128]);
            if (strDecode.length() == uleb128) {
                return new StringData(iPosition, strDecode);
            }
            throw new DexException("Declared length " + uleb128 + " doesn't match decoded length of " + strDecode.length());
        } catch (UTFDataFormatException e) {
            throw new DexException(e);
        }
    }

    public TypeList readTypeList() {
        return new TypeList(this.data.position(), readShortArray(readInt()));
    }

    public int readUleb128() {
        return Leb128.readUnsignedLeb128(this);
    }

    public int readUleb128p1() {
        return Leb128.readUnsignedLeb128(this) - 1;
    }

    public int readUnsignedByte() {
        return readByte() & 255;
    }

    public int readUnsignedShort() {
        return readShort() & 65535;
    }

    public void skip(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        ByteBuffer byteBuffer = this.data;
        byteBuffer.position(byteBuffer.position() + i);
    }

    public void skipWithAutoExpand(int i) {
        ensureBufferSize(i * 1);
        skip(i);
    }

    public void write(byte[] bArr) {
        ensureBufferSize(bArr.length * 1);
        this.data.put(bArr);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void write(short[] sArr) {
        ensureBufferSize(sArr.length * 2);
        for (short s : sArr) {
            writeShort(s);
        }
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public int writeAnnotation(Annotation annotation) {
        int iPosition = this.data.position();
        writeByte(annotation.visibility);
        writeEncodedArray(annotation.encodedAnnotation);
        return iPosition;
    }

    public int writeAnnotationSet(AnnotationSet annotationSet) {
        int iPosition = this.data.position();
        writeInt(annotationSet.annotationOffsets.length);
        for (int i : annotationSet.annotationOffsets) {
            writeInt(i);
        }
        return iPosition;
    }

    public int writeAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
        int iPosition = this.data.position();
        writeInt(annotationSetRefList.annotationSetRefItems.length);
        for (int i : annotationSetRefList.annotationSetRefItems) {
            writeInt(i);
        }
        return iPosition;
    }

    public int writeAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
        int iPosition = this.data.position();
        writeInt(annotationsDirectory.classAnnotationsOffset);
        writeInt(annotationsDirectory.fieldAnnotations.length);
        writeInt(annotationsDirectory.methodAnnotations.length);
        writeInt(annotationsDirectory.parameterAnnotations.length);
        for (int[] iArr : annotationsDirectory.fieldAnnotations) {
            writeInt(iArr[0]);
            writeInt(iArr[1]);
        }
        for (int[] iArr2 : annotationsDirectory.methodAnnotations) {
            writeInt(iArr2[0]);
            writeInt(iArr2[1]);
        }
        for (int[] iArr3 : annotationsDirectory.parameterAnnotations) {
            writeInt(iArr3[0]);
            writeInt(iArr3[1]);
        }
        return iPosition;
    }

    @Override
    public void writeByte(int i) {
        ensureBufferSize(1);
        this.data.put((byte) i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public int writeClassData(ClassData classData) {
        int iPosition = this.data.position();
        writeUleb128(classData.staticFields.length);
        writeUleb128(classData.instanceFields.length);
        writeUleb128(classData.directMethods.length);
        writeUleb128(classData.virtualMethods.length);
        writeFields(classData.staticFields);
        writeFields(classData.instanceFields);
        writeMethods(classData.directMethods);
        writeMethods(classData.virtualMethods);
        return iPosition;
    }

    public int writeClassDef(ClassDef classDef) {
        int iPosition = this.data.position();
        writeInt(classDef.typeIndex);
        writeInt(classDef.accessFlags);
        writeInt(classDef.supertypeIndex);
        writeInt(classDef.interfacesOffset);
        writeInt(classDef.sourceFileIndex);
        writeInt(classDef.annotationsOffset);
        writeInt(classDef.classDataOffset);
        writeInt(classDef.staticValuesOffset);
        return iPosition;
    }

    public int writeCode(Code code) {
        int iPosition = this.data.position();
        writeUnsignedShort(code.registersSize);
        writeUnsignedShort(code.insSize);
        writeUnsignedShort(code.outsSize);
        writeUnsignedShort(code.tries.length);
        writeInt(code.debugInfoOffset);
        writeInt(code.instructions.length);
        write(code.instructions);
        if (code.tries.length > 0) {
            if ((code.instructions.length & 1) == 1) {
                writeShort((short) 0);
            }
            int iPosition2 = this.data.position();
            skipWithAutoExpand(code.tries.length * 8);
            int[] iArrWriteCatchHandlers = writeCatchHandlers(code.catchHandlers);
            int iPosition3 = this.data.position();
            this.data.position(iPosition2);
            writeTries(code.tries, iArrWriteCatchHandlers);
            this.data.position(iPosition3);
        }
        return iPosition;
    }

    public int writeDebugInfoItem(DebugInfoItem debugInfoItem) {
        int iPosition = this.data.position();
        writeUleb128(debugInfoItem.lineStart);
        int length = debugInfoItem.parameterNames.length;
        writeUleb128(length);
        for (int i = 0; i < length; i++) {
            writeUleb128p1(debugInfoItem.parameterNames[i]);
        }
        write(debugInfoItem.infoSTM);
        return iPosition;
    }

    public int writeEncodedArray(EncodedValue encodedValue) {
        int iPosition = this.data.position();
        write(encodedValue.data);
        return iPosition;
    }

    public int writeFieldId(FieldId fieldId) {
        int iPosition = this.data.position();
        writeUnsignedShort(fieldId.declaringClassIndex);
        writeUnsignedShort(fieldId.typeIndex);
        writeInt(fieldId.nameIndex);
        return iPosition;
    }

    public void writeInt(int i) {
        ensureBufferSize(4);
        this.data.putInt(i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public int writeMethodId(MethodId methodId) {
        int iPosition = this.data.position();
        writeUnsignedShort(methodId.declaringClassIndex);
        writeUnsignedShort(methodId.protoIndex);
        writeInt(methodId.nameIndex);
        return iPosition;
    }

    public int writeProtoId(ProtoId protoId) {
        int iPosition = this.data.position();
        writeInt(protoId.shortyIndex);
        writeInt(protoId.returnTypeIndex);
        writeInt(protoId.parametersOffset);
        return iPosition;
    }

    public void writeShort(short s) {
        ensureBufferSize(2);
        this.data.putShort(s);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeSleb128(int i) {
        Leb128.writeSignedLeb128(this, i);
    }

    public int writeStringData(StringData stringData) {
        int iPosition = this.data.position();
        try {
            writeUleb128(stringData.value.length());
            write(Mutf8.encode(stringData.value));
            writeByte(0);
            return iPosition;
        } catch (UTFDataFormatException e) {
            throw new AssertionError(e);
        }
    }

    public int writeTypeList(TypeList typeList) {
        int iPosition = this.data.position();
        short[] sArr = typeList.types;
        writeInt(sArr.length);
        for (short s : sArr) {
            writeShort(s);
        }
        return iPosition;
    }

    public void writeUleb128(int i) {
        Leb128.writeUnsignedLeb128(this, i);
    }

    public void writeUleb128p1(int i) {
        writeUleb128(i + 1);
    }

    public void writeUnsignedShort(int i) {
        short s = (short) i;
        if (i == (65535 & s)) {
            writeShort(s);
            return;
        }
        throw new IllegalArgumentException("Expected an unsigned short: " + i);
    }
}
