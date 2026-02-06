package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.android.dex.util.FileUtils;
import com.tencent.tinker.android.dx.util.Hex;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.zip.Adler32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class Dex {
    private static final int CHECKSUM_OFFSET = 8;
    static final short[] EMPTY_SHORT_ARRAY = new short[0];
    private static final int SIGNATURE_OFFSET = 12;
    private final ClassDefTable classDefs;
    private ByteBuffer data;
    private final FieldIdTable fieldIds;
    private final MethodIdTable methodIds;
    private int nextSectionStart;
    private final ProtoIdTable protoIds;
    private byte[] signature;
    private final StringTable strings;
    private final TableOfContents tableOfContents;
    private final TypeIndexToDescriptorIndexTable typeIds;
    private final TypeIndexToDescriptorTable typeNames;

    private final class ClassDefIterable implements Iterable<ClassDef> {
        private ClassDefIterable() {
        }

        @Override
        public Iterator<ClassDef> iterator() {
            return !Dex.this.tableOfContents.classDefs.exists() ? Collections.emptySet().iterator() : new ClassDefIterator();
        }
    }

    private final class ClassDefIterator implements Iterator<ClassDef> {
        private int count;
        private final Section in;

        private ClassDefIterator() {
            Dex dex = Dex.this;
            this.in = dex.openSection(dex.tableOfContents.classDefs);
            this.count = 0;
        }

        @Override
        public boolean hasNext() {
            return this.count < Dex.this.tableOfContents.classDefs.size;
        }

        @Override
        public ClassDef next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.count++;
            return this.in.readClassDef();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final class ClassDefTable extends AbstractList<ClassDef> implements RandomAccess {
        private ClassDefTable() {
        }

        @Override
        public ClassDef get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.classDefs.size);
            Dex dex = Dex.this;
            return dex.openSection(dex.tableOfContents.classDefs.off + (i * 32)).readClassDef();
        }

        @Override
        public int size() {
            return Dex.this.tableOfContents.classDefs.size;
        }
    }

    private final class FieldIdTable extends AbstractList<FieldId> implements RandomAccess {
        private FieldIdTable() {
        }

        @Override
        public FieldId get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.fieldIds.size);
            Dex dex = Dex.this;
            return dex.openSection(dex.tableOfContents.fieldIds.off + (i * 8)).readFieldId();
        }

        @Override
        public int size() {
            return Dex.this.tableOfContents.fieldIds.size;
        }
    }

    private final class MethodIdTable extends AbstractList<MethodId> implements RandomAccess {
        private MethodIdTable() {
        }

        @Override
        public MethodId get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.methodIds.size);
            Dex dex = Dex.this;
            return dex.openSection(dex.tableOfContents.methodIds.off + (i * 8)).readMethodId();
        }

        @Override
        public int size() {
            return Dex.this.tableOfContents.methodIds.size;
        }
    }

    private final class ProtoIdTable extends AbstractList<ProtoId> implements RandomAccess {
        private ProtoIdTable() {
        }

        @Override
        public ProtoId get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.protoIds.size);
            Dex dex = Dex.this;
            return dex.openSection(dex.tableOfContents.protoIds.off + (i * 12)).readProtoId();
        }

        @Override
        public int size() {
            return Dex.this.tableOfContents.protoIds.size;
        }
    }

    public final class Section extends DexDataBuffer {
        private final String name;

        private Section(String str, ByteBuffer byteBuffer) {
            super(byteBuffer);
            this.name = str;
        }

        private void ensureFourBytesAligned(TableOfContents.Section section, boolean z) {
            if (section.isElementFourByteAligned) {
                if (z) {
                    alignToFourBytesWithZeroFill();
                } else {
                    alignToFourBytes();
                }
            }
        }

        @Override
        public Annotation readAnnotation() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotations, false);
            return super.readAnnotation();
        }

        @Override
        public AnnotationSet readAnnotationSet() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSets, false);
            return super.readAnnotationSet();
        }

        @Override
        public AnnotationSetRefList readAnnotationSetRefList() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSetRefLists, false);
            return super.readAnnotationSetRefList();
        }

        @Override
        public AnnotationsDirectory readAnnotationsDirectory() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationsDirectories, false);
            return super.readAnnotationsDirectory();
        }

        @Override
        public ClassData readClassData() {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDatas, false);
            return super.readClassData();
        }

        @Override
        public ClassDef readClassDef() {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDefs, false);
            return super.readClassDef();
        }

        @Override
        public Code readCode() {
            ensureFourBytesAligned(Dex.this.tableOfContents.codes, false);
            return super.readCode();
        }

        @Override
        public DebugInfoItem readDebugInfoItem() {
            ensureFourBytesAligned(Dex.this.tableOfContents.debugInfos, false);
            return super.readDebugInfoItem();
        }

        @Override
        public EncodedValue readEncodedArray() {
            ensureFourBytesAligned(Dex.this.tableOfContents.encodedArrays, false);
            return super.readEncodedArray();
        }

        @Override
        public FieldId readFieldId() {
            ensureFourBytesAligned(Dex.this.tableOfContents.fieldIds, false);
            return super.readFieldId();
        }

        @Override
        public MethodId readMethodId() {
            ensureFourBytesAligned(Dex.this.tableOfContents.methodIds, false);
            return super.readMethodId();
        }

        @Override
        public ProtoId readProtoId() {
            ensureFourBytesAligned(Dex.this.tableOfContents.protoIds, false);
            return super.readProtoId();
        }

        @Override
        public StringData readStringData() {
            ensureFourBytesAligned(Dex.this.tableOfContents.stringDatas, false);
            return super.readStringData();
        }

        @Override
        public TypeList readTypeList() {
            ensureFourBytesAligned(Dex.this.tableOfContents.typeLists, false);
            return super.readTypeList();
        }

        @Override
        public int writeAnnotation(Annotation annotation) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotations, true);
            return super.writeAnnotation(annotation);
        }

        @Override
        public int writeAnnotationSet(AnnotationSet annotationSet) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSets, true);
            return super.writeAnnotationSet(annotationSet);
        }

        @Override
        public int writeAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSetRefLists, true);
            return super.writeAnnotationSetRefList(annotationSetRefList);
        }

        @Override
        public int writeAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationsDirectories, true);
            return super.writeAnnotationsDirectory(annotationsDirectory);
        }

        @Override
        public int writeClassData(ClassData classData) {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDatas, true);
            return super.writeClassData(classData);
        }

        @Override
        public int writeClassDef(ClassDef classDef) {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDefs, true);
            return super.writeClassDef(classDef);
        }

        @Override
        public int writeCode(Code code) {
            ensureFourBytesAligned(Dex.this.tableOfContents.codes, true);
            return super.writeCode(code);
        }

        @Override
        public int writeDebugInfoItem(DebugInfoItem debugInfoItem) {
            ensureFourBytesAligned(Dex.this.tableOfContents.debugInfos, true);
            return super.writeDebugInfoItem(debugInfoItem);
        }

        @Override
        public int writeEncodedArray(EncodedValue encodedValue) {
            ensureFourBytesAligned(Dex.this.tableOfContents.encodedArrays, true);
            return super.writeEncodedArray(encodedValue);
        }

        @Override
        public int writeFieldId(FieldId fieldId) {
            ensureFourBytesAligned(Dex.this.tableOfContents.fieldIds, true);
            return super.writeFieldId(fieldId);
        }

        @Override
        public int writeMethodId(MethodId methodId) {
            ensureFourBytesAligned(Dex.this.tableOfContents.methodIds, true);
            return super.writeMethodId(methodId);
        }

        @Override
        public int writeProtoId(ProtoId protoId) {
            ensureFourBytesAligned(Dex.this.tableOfContents.protoIds, true);
            return super.writeProtoId(protoId);
        }

        @Override
        public int writeStringData(StringData stringData) {
            ensureFourBytesAligned(Dex.this.tableOfContents.stringDatas, true);
            return super.writeStringData(stringData);
        }

        @Override
        public int writeTypeList(TypeList typeList) {
            ensureFourBytesAligned(Dex.this.tableOfContents.typeLists, true);
            return super.writeTypeList(typeList);
        }
    }

    private final class StringTable extends AbstractList<String> implements RandomAccess {
        private StringTable() {
        }

        @Override
        public String get(int i) {
            Dex.checkBounds(i, Dex.this.tableOfContents.stringIds.size);
            Dex dex = Dex.this;
            return Dex.this.openSection(dex.openSection(dex.tableOfContents.stringIds.off + (i * 4)).readInt()).readStringData().value;
        }

        @Override
        public int size() {
            return Dex.this.tableOfContents.stringIds.size;
        }
    }

    private final class TypeIndexToDescriptorIndexTable extends AbstractList<Integer> implements RandomAccess {
        private TypeIndexToDescriptorIndexTable() {
        }

        @Override
        public Integer get(int i) {
            return Integer.valueOf(Dex.this.descriptorIndexFromTypeIndex(i));
        }

        @Override
        public int size() {
            return Dex.this.tableOfContents.typeIds.size;
        }
    }

    private final class TypeIndexToDescriptorTable extends AbstractList<String> implements RandomAccess {
        private TypeIndexToDescriptorTable() {
        }

        @Override
        public String get(int i) {
            return Dex.this.strings.get(Dex.this.descriptorIndexFromTypeIndex(i));
        }

        @Override
        public int size() {
            return Dex.this.tableOfContents.typeIds.size;
        }
    }

    public Dex(int i) {
        this.tableOfContents = new TableOfContents();
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        this.classDefs = new ClassDefTable();
        this.nextSectionStart = 0;
        this.signature = null;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[i]);
        this.data = byteBufferWrap;
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.fileSize = i;
    }

    public Dex(File file) throws Throwable {
        this.tableOfContents = new TableOfContents();
        ?? r1 = 0;
        bufferedInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        this.classDefs = new ClassDefTable();
        this.nextSectionStart = 0;
        this.signature = null;
        if (file == null) {
            throw new IllegalArgumentException("file is null.");
        }
        try {
            if (FileUtils.hasArchiveSuffix(file.getName())) {
                try {
                    ZipFile zipFile = new ZipFile(file);
                    try {
                        ZipEntry entry = zipFile.getEntry("classes.dex");
                        if (entry == null) {
                            throw new DexException("Expected classes.dex in " + file);
                        }
                        try {
                            inputStream = zipFile.getInputStream(entry);
                            loadFrom(inputStream, (int) entry.getSize());
                            zipFile.close();
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        r1 = zipFile;
                        if (r1 != 0) {
                            try {
                                r1.close();
                            } catch (Exception unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                if (!file.getName().endsWith(".dex")) {
                    throw new DexException("unknown output extension: " + file);
                }
                try {
                    try {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                        try {
                            loadFrom(bufferedInputStream2, (int) file.length());
                            bufferedInputStream2.close();
                        } catch (Exception e) {
                            e = e;
                            bufferedInputStream = bufferedInputStream2;
                            throw new DexException(e);
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedInputStream = bufferedInputStream2;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception unused2) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception unused3) {
        }
    }

    public Dex(InputStream inputStream) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        this.classDefs = new ClassDefTable();
        this.nextSectionStart = 0;
        this.signature = null;
        loadFrom(inputStream);
    }

    public Dex(InputStream inputStream, int i) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        this.classDefs = new ClassDefTable();
        this.nextSectionStart = 0;
        this.signature = null;
        loadFrom(inputStream, i);
    }

    private Dex(ByteBuffer byteBuffer) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.strings = new StringTable();
        this.typeIds = new TypeIndexToDescriptorIndexTable();
        this.typeNames = new TypeIndexToDescriptorTable();
        this.protoIds = new ProtoIdTable();
        this.fieldIds = new FieldIdTable();
        this.methodIds = new MethodIdTable();
        this.classDefs = new ClassDefTable();
        this.nextSectionStart = 0;
        this.signature = null;
        this.data = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.readFrom(this);
    }

    public Dex(byte[] bArr) throws IOException {
        this(ByteBuffer.wrap(bArr));
    }

    private String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length << 1);
        for (byte b : bArr) {
            sb.append(Hex.u1(b));
        }
        return sb.toString();
    }

    private static void checkBounds(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("index:" + i + ", length=" + i2);
        }
    }

    private void loadFrom(InputStream inputStream) throws IOException {
        loadFrom(inputStream, 0);
    }

    private void loadFrom(InputStream inputStream, int i) throws IOException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(FileUtils.readStream(inputStream, i));
        this.data = byteBufferWrap;
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.readFrom(this);
    }

    public int annotationDirectoryOffsetFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        return this.data.getInt(this.tableOfContents.classDefs.off + (i * 32) + 4 + 4 + 4 + 4 + 4);
    }

    public Section appendSection(int i, String str) {
        int i2 = this.nextSectionStart + i;
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferDuplicate.position(this.nextSectionStart);
        byteBufferDuplicate.limit(i2);
        Section section = new Section(str, byteBufferDuplicate);
        this.nextSectionStart = i2;
        return section;
    }

    public Iterable<ClassDef> classDefIterable() {
        return new ClassDefIterable();
    }

    public List<ClassDef> classDefs() {
        return this.classDefs;
    }

    public int computeChecksum() throws IOException {
        Adler32 adler32 = new Adler32();
        byte[] bArr = new byte[8192];
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.limit(byteBufferDuplicate.capacity());
        byteBufferDuplicate.position(12);
        while (byteBufferDuplicate.hasRemaining()) {
            int iMin = Math.min(8192, byteBufferDuplicate.remaining());
            byteBufferDuplicate.get(bArr, 0, iMin);
            adler32.update(bArr, 0, iMin);
        }
        return (int) adler32.getValue();
    }

    public byte[] computeSignature(boolean z) throws NoSuchAlgorithmException {
        byte[] bArr = this.signature;
        if (bArr != null && !z) {
            return bArr;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bArr2 = new byte[8192];
            ByteBuffer byteBufferDuplicate = this.data.duplicate();
            byteBufferDuplicate.limit(byteBufferDuplicate.capacity());
            byteBufferDuplicate.position(32);
            while (byteBufferDuplicate.hasRemaining()) {
                int iMin = Math.min(8192, byteBufferDuplicate.remaining());
                byteBufferDuplicate.get(bArr2, 0, iMin);
                messageDigest.update(bArr2, 0, iMin);
            }
            byte[] bArrDigest = messageDigest.digest();
            this.signature = bArrDigest;
            return bArrDigest;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public int declaringClassIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        return this.data.getShort(this.tableOfContents.methodIds.off + (i * 8)) & 65535;
    }

    public int descriptorIndexFromTypeIndex(int i) {
        checkBounds(i, this.tableOfContents.typeIds.size);
        return this.data.getInt(this.tableOfContents.typeIds.off + (i * 4));
    }

    public List<FieldId> fieldIds() {
        return this.fieldIds;
    }

    public int findClassDefIndexFromTypeIndex(int i) {
        checkBounds(i, this.tableOfContents.typeIds.size);
        if (!this.tableOfContents.classDefs.exists()) {
            return -1;
        }
        for (int i2 = 0; i2 < this.tableOfContents.classDefs.size; i2++) {
            if (typeIndexFromClassDefIndex(i2) == i) {
                return i2;
            }
        }
        return -1;
    }

    public int findFieldIndex(FieldId fieldId) {
        return Collections.binarySearch(this.fieldIds, fieldId);
    }

    public int findMethodIndex(MethodId methodId) {
        return Collections.binarySearch(this.methodIds, methodId);
    }

    public int findStringIndex(String str) {
        return Collections.binarySearch(this.strings, str);
    }

    public int findTypeIndex(String str) {
        return Collections.binarySearch(this.typeNames, str);
    }

    public byte[] getBytes() {
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byte[] bArr = new byte[byteBufferDuplicate.capacity()];
        byteBufferDuplicate.position(0);
        byteBufferDuplicate.get(bArr);
        return bArr;
    }

    public int getLength() {
        return this.data.capacity();
    }

    public int getNextSectionStart() {
        return this.nextSectionStart;
    }

    public TableOfContents getTableOfContents() {
        return this.tableOfContents;
    }

    public short[] interfaceTypeIndicesFromClassDef(ClassDef classDef) {
        int i = this.data.getInt(classDef.off + 4 + 4 + 4);
        if (i == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i2 = this.data.getInt(i);
        if (i2 <= 0) {
            throw new AssertionError("Unexpected interfaces list size: " + i2);
        }
        int i3 = i + 4;
        short[] sArr = new short[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            sArr[i4] = this.data.getShort(i3);
            i3 += 2;
        }
        return sArr;
    }

    public short[] interfaceTypeIndicesFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        int i2 = this.data.getInt(this.tableOfContents.classDefs.off + (i * 32) + 4 + 4 + 4);
        if (i2 == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i3 = this.data.getInt(i2);
        if (i3 <= 0) {
            throw new AssertionError("Unexpected interfaces list size: " + i3);
        }
        int i4 = i2 + 4;
        short[] sArr = new short[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            sArr[i5] = this.data.getShort(i4);
            i4 += 2;
        }
        return sArr;
    }

    public List<MethodId> methodIds() {
        return this.methodIds;
    }

    public int nameIndexFromFieldIndex(int i) {
        checkBounds(i, this.tableOfContents.fieldIds.size);
        return this.data.getInt(this.tableOfContents.fieldIds.off + (i * 8) + 2 + 2);
    }

    public int nameIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        return this.data.getInt(this.tableOfContents.methodIds.off + (i * 8) + 2 + 2);
    }

    public Section openSection(int i) {
        if (i < 0 || i >= this.data.capacity()) {
            throw new IllegalArgumentException("position=" + i + " length=" + this.data.capacity());
        }
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferDuplicate.position(i);
        byteBufferDuplicate.limit(this.data.capacity());
        return new Section("temp-section", byteBufferDuplicate);
    }

    public Section openSection(TableOfContents.Section section) {
        int i = section.off;
        if (i < 0 || i >= this.data.capacity()) {
            throw new IllegalArgumentException("position=" + i + " length=" + this.data.capacity());
        }
        ByteBuffer byteBufferDuplicate = this.data.duplicate();
        byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferDuplicate.position(i);
        byteBufferDuplicate.limit(i + section.byteCount);
        return new Section("section", byteBufferDuplicate);
    }

    public short[] parameterTypeIndicesFromMethodId(MethodId methodId) {
        int i = methodId.protoIndex & 65535;
        checkBounds(i, this.tableOfContents.protoIds.size);
        int i2 = this.data.getInt(this.tableOfContents.protoIds.off + (i * 12) + 4 + 4);
        if (i2 == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i3 = this.data.getInt(i2);
        if (i3 <= 0) {
            throw new AssertionError("Unexpected parameter type list size: " + i3);
        }
        int i4 = i2 + 4;
        short[] sArr = new short[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            sArr[i5] = this.data.getShort(i4);
            i4 += 2;
        }
        return sArr;
    }

    public short[] parameterTypeIndicesFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        int i2 = this.data.getShort(this.tableOfContents.methodIds.off + (i * 8) + 2) & 65535;
        checkBounds(i2, this.tableOfContents.protoIds.size);
        int i3 = this.data.getInt(this.tableOfContents.protoIds.off + (i2 * 12) + 4 + 4);
        if (i3 == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i4 = this.data.getInt(i3);
        if (i4 <= 0) {
            throw new AssertionError("Unexpected parameter type list size: " + i4);
        }
        int i5 = i3 + 4;
        short[] sArr = new short[i4];
        for (int i6 = 0; i6 < i4; i6++) {
            sArr[i6] = this.data.getShort(i5);
            i5 += 2;
        }
        return sArr;
    }

    public List<ProtoId> protoIds() {
        return this.protoIds;
    }

    public ClassData readClassData(ClassDef classDef) {
        int i = classDef.classDataOffset;
        if (i != 0) {
            return openSection(i).readClassData();
        }
        throw new IllegalArgumentException("offset == 0");
    }

    public Code readCode(ClassData.Method method) {
        int i = method.codeOffset;
        if (i != 0) {
            return openSection(i).readCode();
        }
        throw new IllegalArgumentException("offset == 0");
    }

    public int returnTypeIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        int i2 = this.data.getShort(this.tableOfContents.methodIds.off + (i * 8) + 2) & 65535;
        checkBounds(i2, this.tableOfContents.protoIds.size);
        return this.data.getInt(this.tableOfContents.protoIds.off + (i2 * 12) + 4);
    }

    public List<String> strings() {
        return this.strings;
    }

    public List<Integer> typeIds() {
        return this.typeIds;
    }

    public int typeIndexFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        return this.data.getInt(this.tableOfContents.classDefs.off + (i * 32));
    }

    public int typeIndexFromFieldIndex(int i) {
        checkBounds(i, this.tableOfContents.fieldIds.size);
        return this.data.getShort(this.tableOfContents.fieldIds.off + (i * 8) + 2) & 65535;
    }

    public List<String> typeNames() {
        return this.typeNames;
    }

    public void writeHashes() throws IOException {
        openSection(12).write(computeSignature(true));
        openSection(8).writeInt(computeChecksum());
    }

    public void writeTo(File file) throws Throwable {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    writeTo(bufferedOutputStream2);
                    try {
                        bufferedOutputStream2.close();
                    } catch (Exception unused) {
                    }
                } catch (Exception e) {
                    e = e;
                    bufferedOutputStream = bufferedOutputStream2;
                    throw new DexException(e);
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.data.array());
        outputStream.flush();
    }
}
