package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public final class ClassData extends TableOfContents.Section.Item<ClassData> {
    public Method[] directMethods;
    public Field[] instanceFields;
    public Field[] staticFields;
    public Method[] virtualMethods;

    public static class Field implements Comparable<Field> {
        public int accessFlags;
        public int fieldIndex;

        public Field(int i, int i2) {
            this.fieldIndex = i;
            this.accessFlags = i2;
        }

        @Override
        public int compareTo(Field field) {
            int iUCompare = CompareUtils.uCompare(this.fieldIndex, field.fieldIndex);
            return iUCompare != 0 ? iUCompare : CompareUtils.sCompare(this.accessFlags, field.accessFlags);
        }

        public boolean equals(Object obj) {
            return (obj instanceof Field) && compareTo((Field) obj) == 0;
        }

        public int hashCode() {
            return HashCodeHelper.hash(Integer.valueOf(this.fieldIndex), Integer.valueOf(this.accessFlags));
        }
    }

    public static class Method implements Comparable<Method> {
        public int accessFlags;
        public int codeOffset;
        public int methodIndex;

        public Method(int i, int i2, int i3) {
            this.methodIndex = i;
            this.accessFlags = i2;
            this.codeOffset = i3;
        }

        @Override
        public int compareTo(Method method) {
            int iUCompare = CompareUtils.uCompare(this.methodIndex, method.methodIndex);
            if (iUCompare != 0) {
                return iUCompare;
            }
            int iSCompare = CompareUtils.sCompare(this.accessFlags, method.accessFlags);
            return iSCompare != 0 ? iSCompare : CompareUtils.sCompare(this.codeOffset, method.codeOffset);
        }

        public boolean equals(Object obj) {
            return (obj instanceof Method) && compareTo((Method) obj) == 0;
        }

        public int hashCode() {
            return HashCodeHelper.hash(Integer.valueOf(this.methodIndex), Integer.valueOf(this.accessFlags), Integer.valueOf(this.codeOffset));
        }
    }

    public ClassData(int i, Field[] fieldArr, Field[] fieldArr2, Method[] methodArr, Method[] methodArr2) {
        super(i);
        this.staticFields = fieldArr;
        this.instanceFields = fieldArr2;
        this.directMethods = methodArr;
        this.virtualMethods = methodArr2;
    }

    private int calcFieldsSize(Field[] fieldArr) {
        int iUnsignedLeb128Size = 0;
        int i = 0;
        for (Field field : fieldArr) {
            int i2 = field.fieldIndex - i;
            i = field.fieldIndex;
            iUnsignedLeb128Size += Leb128.unsignedLeb128Size(i2) + Leb128.unsignedLeb128Size(field.accessFlags);
        }
        return iUnsignedLeb128Size;
    }

    private int calcMethodsSize(Method[] methodArr) {
        int iUnsignedLeb128Size = 0;
        int i = 0;
        for (Method method : methodArr) {
            int i2 = method.methodIndex - i;
            i = method.methodIndex;
            iUnsignedLeb128Size += Leb128.unsignedLeb128Size(i2) + Leb128.unsignedLeb128Size(method.accessFlags) + Leb128.unsignedLeb128Size(method.codeOffset);
        }
        return iUnsignedLeb128Size;
    }

    @Override
    public int byteCountInDex() {
        return Leb128.unsignedLeb128Size(this.staticFields.length) + Leb128.unsignedLeb128Size(this.instanceFields.length) + Leb128.unsignedLeb128Size(this.directMethods.length) + Leb128.unsignedLeb128Size(this.virtualMethods.length) + calcFieldsSize(this.staticFields) + calcFieldsSize(this.instanceFields) + calcMethodsSize(this.directMethods) + calcMethodsSize(this.virtualMethods);
    }

    @Override
    public int compareTo(ClassData classData) {
        int iAArrCompare = CompareUtils.aArrCompare(this.staticFields, classData.staticFields);
        if (iAArrCompare != 0) {
            return iAArrCompare;
        }
        int iAArrCompare2 = CompareUtils.aArrCompare(this.instanceFields, classData.instanceFields);
        if (iAArrCompare2 != 0) {
            return iAArrCompare2;
        }
        int iAArrCompare3 = CompareUtils.aArrCompare(this.directMethods, classData.directMethods);
        return iAArrCompare3 != 0 ? iAArrCompare3 : CompareUtils.aArrCompare(this.virtualMethods, classData.virtualMethods);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ClassData) && compareTo((ClassData) obj) == 0;
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(this.staticFields, this.instanceFields, this.directMethods, this.virtualMethods);
    }
}
